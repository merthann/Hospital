<template>
  <div class="container">
    <h1>Create Policlinic</h1>
    <form @submit.prevent="createPoliclinic" class="create-form">
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="policlinic.name" id="name" required />
      </div>
      <div>
        <label for="floor">Floor:</label>
        <input type="number" v-model="policlinic.floor" id="floor" required />
      </div>
      <button type="submit" class="fancy-button add-button">Create Policlinic</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      policlinic: {
        name: '',
        floor: ''
      }
    };
  },
  methods: {
    createPoliclinic() {
      axios.post('http://localhost:8080/policlinic', this.policlinic)
        .then(response => {
          console.log('Policlinic created:', response.data);
          this.$router.push({ name: 'PoliclinicList' });
        })
        .catch(error => {
          console.error('Error creating policlinic:', error);
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
