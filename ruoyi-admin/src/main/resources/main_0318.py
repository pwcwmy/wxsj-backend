import torch
import torch.nn as nn
from PIL import Image
import torch.nn.functional as F
from torchvision import transforms, models
from torch.nn import Parameter
from torchvision.models import ResNet50_Weights
import argparse
import sys
from torchvision.models import DenseNet121_Weights
sys.stdout = open(sys.stdout.fileno(), mode='w', encoding='utf-8', buffering=1)


class Resnet(nn.Module):
    def __init__(self, model, num_classes):
        super(Resnet, self).__init__()
        self.features = nn.Sequential(
            model.conv1,
            model.bn1,
            model.relu,
            model.maxpool,
            model.layer1,
            model.layer2,
            model.layer3,
            model.layer4,
        )
        self.num_classes = num_classes
        self.pooling = nn.MaxPool2d((14, 14))
        self.image_normalization_mean = [0.485, 0.456, 0.406]
        self.image_normalization_std = [0.229, 0.224, 0.225]
        self.classifier = nn.Linear(2048, num_classes)

    def forward(self, feature):  # bs, 3, h, w
        feature = self.features(feature)
        feature = self.pooling(feature)
        feature = feature.view(feature.size(0), -1)  # bs 2048

        output = self.classifier(feature)
        return output, feature

    def get_config_optim(self, lr, lrp):
        return [
            {'params': self.features.parameters(), 'lr': lr * lrp},
            {'params': self.classifier.parameters(), 'lr': lr}
        ]
    



class Densenet(nn.Module):
    def __init__(self, model, num_classes):
        super(Densenet, self).__init__()
        self.features = model.features
        # num_ftrs = model.classifier.in_features
        self.num_classes = num_classes
        self.classifier = nn.Linear(1024, num_classes)

    def forward(self, x):
        features = self.features(x)

        out = F.relu(features, inplace=True)
        out = F.avg_pool2d(out, kernel_size=14, stride=1).view(features.size(0), -1)
        # print(out.shape)
        out = self.classifier(out)
        return out

    def get_config_optim(self, lr, lrp):
        return [
            {'params': self.features.parameters(), 'lr': lr * lrp},
            {'params': self.classifier.parameters(),'lr': lr}
        ]


def load_densenet121_model():
    num_classes = 54
    weights = DenseNet121_Weights.IMAGENET1K_V1
    model = models.densenet121(weights=weights)
    densenet_model = Densenet(model=model,num_classes=num_classes)
    
    model_path = 'checkpoint/densenet121_best_0.9639.pth.tar'
    checkpoint = torch.load(model_path,map_location='cpu')
    densenet_model.load_state_dict(checkpoint['state_dict'])
    
    return densenet_model

def load_resnet50_model():
    num_classes = 13
    weights = ResNet50_Weights.IMAGENET1K_V1
    model = models.resnet50(weights=weights)
    resnet_model = Resnet(model, num_classes)

    model_path = "E:\\admin\\src\\main\\resources\\checkpoint\\resnet50_best_0.9800.pth.tar"
    checkpoint = torch.load(model_path, map_location='cpu')
    resnet_model.load_state_dict(checkpoint['state_dict'])

    return resnet_model


def predict(image_path, label_lis, model, t=0.9):
    model.eval()
    normalize = transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
    preprocess = transforms.Compose([
        transforms.Resize((448, 448)),
        transforms.ToTensor(),
        normalize
    ])

    image = Image.open(image_path).convert('RGB')
    input_tensor = preprocess(image).unsqueeze(0)

    with torch.no_grad():
        output = model(input_tensor)

    out = F.softmax(output[0].squeeze(), dim=0)
    score = out.max().item()

    if score <= t:
        return '非入侵物种！', score

    out = torch.argmax(out, dim=-1)
    return label_lis[out], score


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Predict species from an image.')
    parser.add_argument('--image_path',type=str, help='Path to the image file.')
    args = parser.parse_args()

    label_lis = ['加拿大一枝黄花','五爪金龙','紫茎泽兰','凤眼莲（水葫芦）',
                     '薇甘菊','紫茉莉','垂序商陆','福寿螺','美国白蛾','红耳彩龟（巴西龟）',
                     '草地贪夜蛾','稻水象甲','红火蚁','锈色棕榈象','黄花刺茄',
                     '鬼针草','马缨丹','假苍耳','豹纹翼甲鲇','刺果瓜','大鳄龟',
                     '大薸','光荚含羞草','空心莲子草','马铃薯甲虫','齐氏罗非鱼',
                     '银胶菊','苏门白酒草','落葵薯','野燕麦','德国小蠊','肿柄菊',
                     '假烟叶树','松树蜂','圆叶牵牛','水茄','白花三叶草','颠茄','虞美人',
                     '吊竹梅','纳氏臀点脂鲤','悬铃木方翅网蝽','蓖麻','牛蛙','红车轴草',
                     '梨果仙人掌','裂叶月见草','曼陀罗','南苜蓿','麦仙翁',
                     '小花山桃草','山羊草','满江红','龙珠果'
                     ]


    model = load_densenet121_model()
    model = model.eval()
    t = 0.95
    print(len(label_lis))
    image_path = args.image_path
    result, score = predict(image_path, label_lis, model, t)

    if score <= t:
        print("非入侵物种!")
    else:
        # print("疑似：", result, '相似度：', score)
        print(result,score)
