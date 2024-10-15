<template>
  <div>
    <h1>Policlinic List</h1>
    <button @click="navigateToCreatePoliclinic" class="fancy-button create-button">Create Policlinic</button>
    <ul>
      <li v-for="policlinic in policlinics" :key="policlinic.id">
        {{ policlinic.name }} - Floor: {{ policlinic.floor }}
        <button @click="navigateToUpdatePoliclinic(policlinic.id)" class="fancy-button update-button">Update</button>
        <button @click="deletePoliclinic(policlinic.id)" class="fancy-button delete-button">Delete</button>
        <button @click="addDoctorToPoliclinic(policlinic.id)" class="fancy-button add-button"> Add Doctor</button>

      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      policlinics: []
    };
  },
  created() {
    this.fetchPoliclinics();
  },
  methods: {
    fetchPoliclinics() {
      axios.get('http://localhost:8080/policlinic')
        .then(response => {
          this.policlinics = response.data;
          this.sortPoliclinicsById();
        })
        .catch(error => {
          console.error('Error fetching policlinics:', error);
        });
    },
    deletePoliclinic(id) {
      axios.delete(`http://localhost:8080/policlinic/${id}`)
        .then(() => {
          this.policlinics = this.policlinics.filter(policlinic => policlinic.id !== id);
        })
        .catch(error => {
          console.error('Error deleting policlinic:', error);
        });
    },
    navigateToCreatePoliclinic() {
      this.$router.push({ name: 'CreatePoliclinic' });
    },
    addDoctorToPoliclinic(policlinicId) {
      const doctorId = prompt("Enter the Doctor ID to add:");
      if (doctorId) {
        axios.post(`http://localhost:8080/policlinic/${policlinicId}/add-doctor/${doctorId}`)
          .then(() => {
            alert('Doctor added successfully');
            this.fetchPoliclinics();
          })
          .catch(error => {
            console.error('Error adding doctor to policlinic:', error);
            alert('Failed to add doctor to policlinic. Please check the console for details.');
          });
      }
    },
    navigateToUpdatePoliclinic(id) {
      this.$router.push({ name: 'PoliclinicUpdate', params: { id: id.toString() } });
    },
    sortPoliclinicsById() {
      this.policlinics.sort((a, b) => a.id - b.id); 
    }
  }
};
</script>
