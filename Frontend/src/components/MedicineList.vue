<template>
  <div>
    <h1>Medicine List</h1>
    <button @click="navigateToCreateMedicine" class="fancy-button create-button">Create Medicine</button>
    <ul v-if="medicines.length > 0">
      <li v-for="medicine in medicines" :key="medicine.id">
        {{ medicine.name }} - {{ medicine.description }}
        <button @click="navigateToUpdateMedicine(medicine.id)" class="fancy-button update-button">Update</button>
        <button @click="deleteMedicine(medicine.id)" class="fancy-button delete-button">Delete</button>
      </li>
    </ul>
    <p v-else>No medicines found</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      medicines: []
    };
  },
  created() {
    this.fetchMedicines();
  },
  methods: {
    fetchMedicines() {
      axios.get('http://localhost:8080/medicine')
        .then(response => {
          this.medicines = response.data;
          this.sortMedicines();
        })
        .catch(error => {
          console.error('Error fetching medicines:', error);
        });
    },
    deleteMedicine(id) {
      axios.delete(`http://localhost:8080/medicine/${id}`)
        .then(() => {
          this.medicines = this.medicines.filter(medicine => medicine.id !== id);
        })
        .catch(error => {
          console.error('Error deleting medicine:', error);
        });
    },
    navigateToCreateMedicine() {
      this.$router.push({ name: 'CreateMedicine' });
    },
    navigateToUpdateMedicine(id) {
      this.$router.push({ name: 'MedicineUpdate', params: { id: id } });
    },
    sortMedicines() {
      this.medicines.sort((a, b) => a.id - b.id); 
    }
  }
};
</script>
