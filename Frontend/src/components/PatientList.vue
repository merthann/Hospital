<template>
  <div>
    <h1>Patient List</h1>
    <button @click="navigateToCreatePatient" class="fancy-button create-button">Create Patient</button>
    <ul v-if="patients.length > 0">
      <li v-for="patient in sortedPatients" :key="patient.id">
        {{ capitalize(patient.fname) }} {{ capitalize(patient.lname) }}
        <button @click="navigateToUpdatePatient(patient.id)" class="fancy-button update-button">Update</button>
        <button @click="deletePatient(patient.id)" class="fancy-button delete-button">Delete</button>
      </li>
    </ul>
    <p v-else>No patients found</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      patients: []
    };
  },
  created() {
    this.fetchPatients();
  },
  computed: {
    sortedPatients() {
      return this.patients.slice().sort((a, b) => a.id - b.id);
    }
  },
  methods: {
    fetchPatients() {
      axios.get('http://localhost:8080/patient')
        .then(response => {
          this.patients = response.data;
          console.log('Fetched patients:', this.patients);
        })
        .catch(error => {
          console.error('Error fetching patients:', error);
        });
    },
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    },
    navigateToUpdatePatient(id) {
      this.$router.push({ name: 'PatientUpdate', params: { id: id } })
        .then(() => console.log('Navigation to update patient successful'))
        .catch(err => console.error('Navigation to update patient failed:', err));
    },
    navigateToCreatePatient() {
      this.$router.push({ name: 'CreatePatient' });
    },
    deletePatient(id) {
      axios.delete(`http://localhost:8080/patient/${id}`)
        .then(() => {
          this.patients = this.patients.filter(patient => patient.id !== id);
          console.log(`Deleted patient with ID: ${id}`);
        })
        .catch(error => {
          console.error('Error deleting patient:', error);
        });
    }
  }
};
</script>
