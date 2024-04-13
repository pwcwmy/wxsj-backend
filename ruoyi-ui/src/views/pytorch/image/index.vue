<!--<template>-->
<!--  <div class="app-container">-->
<!--    <el-button type="warning" @click="openCamera">打开摄像头</el-button>-->
<!--    <el-button type="danger" @click="switchCamera">翻转摄像头</el-button>-->
<!--    <input type="file" ref="imageInput" @change="uploadImage">-->
<!--    <el-button type="primary" @click="recognizeImage">识别<i class="el-icon-search el-icon&#45;&#45;right"></i></el-button>-->

<!--    <br>-->
<!--    <video ref="video" style="display: none;"></video>-->
<!--    <img :src="img" style="max-width: 100%;" alt="上传的图片">-->
<!--    <div id="result">{{ result }}</div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import axios from 'axios';-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      videoStream: null,-->
<!--      currentCamera: 'user',-->
<!--      result: '',-->
<!--      img: '',-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    openCamera() {-->
<!--      console.log('打开摄像头');-->
<!--      if (this.$refs.video) {-->
<!--        navigator.mediaDevices.getUserMedia({ video: { facingMode: this.currentCamera } })-->
<!--          .then(stream => {-->
<!--            this.videoStream = stream;-->
<!--            this.$refs.video.style.display = 'block';-->
<!--            this.$refs.video.srcObject = stream;-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error accessing camera: ', error);-->
<!--          });-->
<!--      }-->
<!--    },-->
<!--    switchCamera() {-->
<!--      console.log('翻转摄像头');-->
<!--      this.currentCamera = (this.currentCamera === 'user') ? 'environment' : 'user';-->
<!--      this.openCamera();-->
<!--    },-->
<!--    uploadImage(event) {-->
<!--      const file = event.target.files[0];-->
<!--      if (file) {-->
<!--        const imageUrl = URL.createObjectURL(file);-->
<!--        this.img = imageUrl;-->
<!--        //this.recognizeImage(); // 调用识别方法-->
<!--      }-->
<!--    },-->
<!--    recognizeImage() {-->
<!--      const fileInput = this.$refs.imageInput;-->
<!--      if (fileInput) {-->
<!--        const formData = new FormData();-->
<!--        formData.append('image', fileInput.files[0]); // 附加文件对象-->
<!--        // 发送 POST 请求到后端接口-->
<!--        axios.post('/dev-api/pytorch/image/recognize', formData, {-->
<!--          headers: {-->
<!--            'Content-Type': 'multipart/form-data'-->
<!--          }-->
<!--        })-->
<!--          .then(response => {-->
<!--            this.result = response.data;-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error recognizing image: ', error);-->
<!--          });-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.app-container {-->
<!--  margin: 20px;-->
<!--}-->
<!--</style>-->

<template>
  <div class="image-recognition">
    <input type="file" @change="handleFileUpload" style="display: none;" ref="fileInput">
    <button @click="triggerFileUpload">选择图片</button>
    <button @click="recognizeImage">识别图片</button>
    <img :src="displayImage" v-if="displayImage" style="max-width: 100%; margin-top: 20px;" />
    <div v-if="recognitionResult" class="result">
      <strong>识别结果:</strong> {{ recognitionResult }}
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      displayImage: '', // 用于显示上传的图片
      recognitionResult: '', // 用于显示识别结果
    };
  },
  methods: {
    triggerFileUpload() {
      this.$refs.fileInput.click(); // 触发文件选择对话框
    },
    handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.displayImage = e.target.result; // 将图片的URL设置为dataURL
        };
        reader.readAsDataURL(file);
      }
    },
    recognizeImage() {
      this.recognitionResult = '光速识别中...'; // 显示识别中状态
      const formData = new FormData();
      formData.append('image', this.$refs.fileInput.files[0]); // 添加文件到表单数据

      axios.post('/dev-api/pytorch/image/recognize', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then(response => {
          this.recognitionResult = response.data;
        })
        .catch(error => {
          console.error('大模型图像识别过程发生错误:', error);
        });
    }
  }
};
</script>

<style scoped>
.image-recognition {
  max-width: 1000px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.result {
  margin-top: 20px;
  padding-top: 10px;
  border-top: 1px solid #eee;
}
</style>
