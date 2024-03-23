<template>
  <div class="app-container">
    <el-button type="warning" @click="openCamera">打开摄像头</el-button>
    <el-button type="danger" @click="switchCamera">翻转摄像头</el-button>
    <input type="file" ref="imageInput" @change="uploadImage">
    <el-button type="primary" @click="recognizeImage">识别<i class="el-icon-search el-icon--right"></i></el-button>

    <br>
    <video ref="video" style="display: none;"></video>
    <img :src="img" style="max-width: 100%;" alt="上传的图片">
    <div id="result">{{ result }}</div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      videoStream: null,
      currentCamera: 'user',
      result: '',
      img: '',
    };
  },
  methods: {
    openCamera() {
      console.log('打开摄像头');
      if (this.$refs.video) {
        navigator.mediaDevices.getUserMedia({ video: { facingMode: this.currentCamera } })
          .then(stream => {
            this.videoStream = stream;
            this.$refs.video.style.display = 'block';
            this.$refs.video.srcObject = stream;
          })
          .catch(error => {
            console.error('Error accessing camera: ', error);
          });
      }
    },
    switchCamera() {
      console.log('翻转摄像头');
      this.currentCamera = (this.currentCamera === 'user') ? 'environment' : 'user';
      this.openCamera();
    },
    uploadImage(event) {
      console.log('上传图片');
      const file = event.target.files[0];
      if (file) {
        const imageUrl = URL.createObjectURL(file);
        this.img = imageUrl;
        console.log('上传图片', imageUrl);
      }
    },
    recognizeImage() {
      console.log('识别图片');
      // const fileInput = this.$refs.imageInput;
      const fileInput =  this.img
      console.log('fileInput')
      console.log(fileInput)
      const formData = new FormData();

      if (fileInput) {
        formData.append('image', fileInput);
        console.log(formData);

        // 发送 POST 请求到后端接口
        axios.post('/dev-api/pytorch/image/recognize', {
            params: fileInput
        })
          .then(response => {
            this.result = response.data;
          })
          .catch(error => {
            console.error('Error recognizing image: ', error);
          });
      }
    },
  },
};
</script>

<style scoped>
.app-container {
  margin: 20px;
}
</style>
