<template>
  <div class="container">
    <h1>Create Medicine</h1>
    <form @submit.prevent="createMedicine" class="create-medicine-form">
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="medicine.name" id="name" required />
      </div>
      <div>
        <label for="description">Description:</label>
        <input type="text" v-model="medicine.description" id="description" required />
      </div>
      <div>
        <label for="category">Category:</label>
        <select v-model="medicine.category" id="category" required>
          <option v-for="category in categories" :key="category.value" :value="category.value">
            {{ category.label }}
          </option>
        </select>
      </div>
      <button type="submit" class="fancy-button add-button">Create Medicine</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      medicine: {
        name: '',
        description: '',
        category: '' 
      },
      categories: [
        { value: 'Antienfeksiyon', label: 'Antienfeksiyon' },
        { value: 'Hormon', label: 'Hormon' },
        { value: 'Immun_sistem', label: 'Immun Sistem' },
        { value: 'Kalp_ve_damar', label: 'Kalp ve Damar' },
        { value: 'Kan', label: 'Kan' },
        { value: 'Mide_bagirsak', label: 'Mide Bağırsak' }
      ]
    };
  },
  methods: {
    createMedicine() {
      axios.post('http://localhost:8080/medicine', this.medicine)
        .then(response => {
          console.log('Medicine created:', response.data);
          this.$router.push({ name: 'MedicineList' });
        })
        .catch(error => {
          console.error('Error creating medicine:', error);
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

.create-medicine-form {
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
