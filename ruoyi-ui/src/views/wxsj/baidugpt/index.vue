<template>
  <div class="chat-container">
    <h1 class="chat-title">WXSJ-GPT 万象生机问答大模型</h1>
    <div class="chat-box" ref="chatBox">
      <div v-for="message in messages" :key="message.id" class="message">
        <div v-if="message.role === 'user'">{{ message.content }}</div>
        <div v-else>{{ message.response }}</div>
      </div>
    </div>
    <!-- 假设你有一个输入框和一个发送按钮来发送用户消息 -->
    <input v-model="userInput" type="text" placeholder="输入问题...">
    <button @click="sendMessage">发送</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      messages: [], // 存储聊天消息的数组
      userInput: '', // 用户输入的文本
      accessToken: '', // 假设你在某个地方已经获取了access token
    };
  },
  methods: {
    async sendMessage() {
      try {
        // 发送请求到后端API
        const response = await axios.post(
          'https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=' + this.accessToken,
          {
            messages: [
              {
                role: 'user',
                content: this.userInput,
              },
            ],
            disable_search: false,
            enable_citation: false,
          }
        );

        // 假设后端响应的格式如下：
        // {
        //   "result": [
        //     {
        //       "role": "assistant",
        //       "content": "响应的内容"
        //     }
        //   ]
        // }
        const assistantMessage = response.data.result[0];

        // 更新本地状态
        this.messages.push({
          id: Date.now(),
          role: 'user',
          content: this.userInput,
        });
        this.messages.push({
          id: Date.now(),
          role: 'assistant',
          response: assistantMessage.content,
        });

        // 清空输入框
        this.userInput = '';
      } catch (error) {
        console.error('Error sending message:', error);
        // 在这里可以添加错误处理逻辑，比如显示错误消息给用户
      }
    },
  },
  mounted() {
    // 假设你在组件挂载后获取access token
    // this.getAccessToken();
  },
};
</script>

<style scoped>
/* 你的样式代码 */
</style>
