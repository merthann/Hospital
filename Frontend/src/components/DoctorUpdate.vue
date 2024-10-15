<template>
  <div>
    <h1>Update Doctor</h1>
    <form @submit.prevent="submitUpdate">
      <label for="title">Title:</label>
      <input type="text" v-model="doctor.title" id="title" />

      <label for="fname">First Name:</label>
      <input type="text" v-model="doctor.fname" id="fname" />

      <label for="lname">Last Name:</label>
      <input type="text" v-model="doctor.lname" id="lname" />

      <label for="policlinicId">Policlinic:</label>
      <select v-model.number="doctor.policlinicId" id="policlinicId">
        <option v-for="policlinic in policlinics" :key="policlinic.id" :value="policlinic.id">
          {{ policlinic.name }}
        </option>
      </select>

      <label for="asistanId">Asistan:</label>
      <select v-model.number="doctor.asistanId" id="asistanId">
        <option v-for="asistan in asistans" :key="asistan.id" :value="asistan.id">
          {{ asistan.fname }} {{ asistan.lname }}
        </option>
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
      doctor: {
        title: '',
        fname: '',
        lname: '',
        policlinicId: null,
        asistanId: null     
      },
      policlinics: [],
      asistans: []
    };
  },
  created() {
    this.fetchDoctor();
    this.fetchPoliclinics();
    this.fetchAsistans();
  },
  methods: {
    fetchDoctor() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No doctor ID provided');
        return;
      }
      axios.get(`http://localhost:8080/doctor/${id}`)
        .then(response => {
          this.doctor = response.data;
        })
        .catch(error => {
          console.error('Error fetching doctor:', error);
        });
    },
    fetchPoliclinics() {
      axios.get('http://localhost:8080/policlinic')
        .then(response => {
          this.policlinics = response.data;
        })
        .catch(error => {
          console.error('Error fetching policlinics:', error);
        });
    },
    fetchAsistans() {
      axios.get('http://localhost:8080/asistan')
        .then(response => {
          this.asistans = response.data;
        })
        .catch(error => {
          console.error('Error fetching asistans:', error);
        });
    },
    submitUpdate() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No doctor ID provided');
        return;
      }
      axios.put(`http://localhost:8080/doctor/${id}`, this.doctor)
        .then(() => {
          this.$router.push('/DoctorList');
        })
        .catch(error => {
          console.error('Error updating doctor:', error);
        });
    }
  }
};
</script>
