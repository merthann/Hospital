<template>
  <div>
    <h1>Update Patient</h1>
    <form @submit.prevent="submitUpdate">
      <label for="fname">First Name:</label>
      <input type="text" v-model="patient.fname" id="fname" />

      <label for="lname">Last Name:</label>
      <input type="text" v-model="patient.lname" id="lname" />

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
      patient: {}
    };
  },
  created() {
    this.fetchPatient();
  },
  methods: {
    fetchPatient() {
      const id = this.$route.params.id;
      axios.get(`http://localhost:8080/patient/${id}`)
        .then(response => {
          this.patient = response.data;
        })
        .catch(error => {
          console.error('Error fetching patient:', error);
        });
    },
    submitUpdate() {
      const id = this.$route.params.id;
      axios.put(`http://localhost:8080/patient/${id}`, this.patient)
        .then(() => {
          this.$router.push('/PatientList');
        })
        .catch(error => {
          console.error('Error updating patient:', error);
        });
    }
  }
};
</script>
