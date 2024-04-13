<template>
  <div class="chat-container">
    <h1 class="chat-title" style="align-content: center">Wxsj-GPT  万象生机问答大模型</h1>
<!--    <p class="chat-subtitle" text-align="middle">万象生机问答大模型</p>-->
    <div class="chat-box" ref="chatBox">
      <!-- Messages will be appended here -->
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
      api_key: 'fastgpt-yxQqHp0kTB9TQLruX6QeCPng43U6HTy7fDp9XTJWfCqnE6UT6gfhBzZbj',
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
  width: 100%;
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
  flex-direction: column;
  align-items: flex-end;
}

.user-message .text {
  background: #007bff;
  color: white;
  padding: 10px;
  border-radius: 5px;
  max-width: 70%;
  text-align: left;
}

.bot-message .text {
  background: #e9ecef;
  padding: 10px;
  border-radius: 5px;
  max-width: 70%;
  text-align: right;
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
