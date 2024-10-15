<template>
    <div>
      <h1>Update Medicine</h1>
      <form @submit.prevent="submitUpdate">
        <label for="name">Name:</label>
        <input type="text" v-model="medicine.name" id="name" />
  
        <label for="description">Description:</label>
        <input type="text" v-model="medicine.description" id="description" />
  
        <label for="category">Category:</label>
        <select v-model="medicine.category" id="category">
          <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
        </select>
  
        <button type="submit" class="fancy-button add-button">Update</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    props: ['id'],
    data() {
      return {
        medicine: {},
        categories: ['Antienfeksiyon',
	'Hormon',
	'Immun_sistem',
	'Kalp_ve_damar',
	'Kan',
	'Mide_bagirsak']
      };
    },
    created() {
      this.fetchMedicine();
    },
    methods: {
      fetchMedicine() {
        const id = this.$route.params.id;
        axios.get(`http://localhost:8080/medicine/${id}`)
          .then(response => {
            this.medicine = response.data;
          })
          .catch(error => {
            console.error('Error fetching medicine:', error);
          });
      },
      submitUpdate() {
        const id = this.$route.params.id;
        axios.put(`http://localhost:8080/medicine/${id}`, this.medicine)
          .then(() => {
            this.$router.push('/MedicineList');
          })
          .catch(error => {
            console.error('Error updating medicine:', error);
          });
      }
    }
  };
  </script>
  