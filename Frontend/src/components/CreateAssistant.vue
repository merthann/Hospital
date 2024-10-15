<template>
  <div class="container">
    <h1>Create Assistant</h1>
    <form @submit.prevent="createAssistant" class="create-form">
      <div>
        <label for="fname">First Name:</label>
        <input type="text" id="fname" v-model="assistant.fname" required />
      </div>
      <div>
        <label for="lname">Last Name:</label>
        <input type="text" id="lname" v-model="assistant.lname" required />
      </div>
      <div>
        <label for="workdays">Workdays:</label>
        <input type="text" id="workdays" v-model="assistant.workdays" placeholder="e.g., Monday, Tuesday" required />
      </div>
      <button type="submit" class="fancy-button add-button">Create</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      assistant: {
        fname: '',
        lname: '',
        workdays: ''
      }
    };
  },
  methods: {
    createAssistant() {
      axios.post('http://localhost:8080/asistan', this.assistant)
        .then(response => {
          console.log('Assistant created:', response.data);
          this.$router.push({ name: 'AssistantList' });
        })
        .catch(error => {
          console.error('Error creating assistant:', error);
        });
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
  text-align: center;
}

.create-form {
  width: 300px;
  padding: 20px;
  background-color: #f0f0f0;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-top: 20px;
}

label {
  display: block;
  text-align: left;
  margin-bottom: 5px;
}

input, select {
  width: 100%;
  padding: 5px;
  margin-bottom: 10px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.error-message {
  color: red;
  margin-top: 10px;
}
</style>
