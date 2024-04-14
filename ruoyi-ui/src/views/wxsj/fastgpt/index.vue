<template>
  <div class="chat-container">
    <h1 class="chat-title" style="align-content: center">WXSJ-GPT  万象生机问答大模型</h1>
    <div class="chat-box" ref="chatBox">
      <div v-for="message in messages" :key="message.id" class="message">
        <div v-if="message.role === 'user'" class="user-message">
          <div class="text">{{ message.content }}</div>
        </div>
        <div v-if="message.role === 'bot'" class="bot-message">
          <div class="text">{{ message.content }}</div>
        </div>
      </div>
    </div>
    <input type="text" v-model="userMessage" @keydown.enter="sendMessage" placeholder="请在此处与我畅聊">
    <button @click="sendMessage">Send</button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      userMessage: '',
      messages: [],
      api_key: 'fastgpt-FOSPA5pitOF8FNuamo6lCUeSuurSo2rf2SQ0tSASVWHHO38KFHUx',
      chat_id: 'abcd'
    };
  },
  methods: {
    sendMessage() {
      console.log('send message:', this.userMessage)
      if (this.userMessage.trim() !== '') {
        const newMessage = {
          id: Date.now(), // 简单的唯一标识符
          content: this.userMessage.trim(),
          role: 'user'
        };
        this.messages.push(newMessage);
        this.$refs.chatBox.scrollTop = this.$refs.chatBox.scrollHeight; // Auto-scroll to the bottom

        // Send the message to the API
        this.apiRequest(this.userMessage.trim())
          .then(response => {
            console.log('response:', response)
            const botMessage = {
              id: Date.now(),
              content: response.data.choices[0].message.content,
              role: 'bot'
            };
            this.messages.push(botMessage);
            this.userMessage = '';
          })
          .catch(error => {
            console.error('Error:', error);
          });
      }
    },
    apiRequest(message) {
      const data = {
        chatId: this.chat_id,
        stream: false,
        detail: false,
        variables: {
          uid: 'asdfadsfasfd2323',
          name: 'peter'
        },
        messages: [
          {
            content: message,
            role: "user"
          }
        ]
      };

      return axios.post('https://api.fastgpt.in/api/v1/chat/completions', data, {
        headers: {
          'Authorization': `Bearer ${this.api_key}`,
          'Content-Type': 'application/json'
        },
        timeout: 10000
      });
    }
  }
};
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f4;
}

.chat-container {
  max-width: 1200px;
  margin: auto;
  background: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.chat-box {
  width: 99%;
  max-height: 800px;
  height: 500px;
  overflow-y: auto;
  padding: 20px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
  background-color: rgba(138, 176, 92, 0.1);
}

.message {
  display: flex;
  margin-bottom: 10px;
  align-items: flex-start;
}

.user-message {
  display: flex;
  flex-direction: row;
  align-items: start;
  justify-content: flex-start; /* 这将使 .user-message 内的元素靠左对齐 */
  margin-bottom: 10px;
}

.user-message .text {
  background: #007bff;
  color: white;
  padding: 10px;
  border-radius: 5px;
  /* max-width: 70%; 这里不需要设置，因为我们使用了flex布局 */
  text-align: left;
  margin-right: auto; /* 这将使.text元素靠左对齐，因为它是flex容器内的第一个元素 */
}

.bot-message {
  display: flex;
  flex-direction: row-reverse; /* 这将使flex容器内的内容反向排列 */
  align-items: start;
  justify-content: flex-end; /* 这将使 .bot-message 内的元素靠右对齐 */
  margin-bottom: 10px;
}

.bot-message .text {
  background: #e9ecef;
  padding: 10px;
  border-radius: 5px;
  /* max-width: 70%; 这里不需要设置，因为我们使用了flex布局 */
  text-align: left;
  margin-left: auto; /* 这将使.text元素靠右对齐，因为它是flex容器内的第一个元素 */
}

input[type="text"] {
  width: calc(100% - 93px);
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
