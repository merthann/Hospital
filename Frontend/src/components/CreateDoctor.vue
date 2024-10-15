<template>
  <div class="container">
    <h1>Create Doctor</h1>
    <form @submit.prevent="createDoctor" class="create-doctor-form">
      <div>
        <label for="title">Title:</label>
        <input type="text" v-model="doctor.title" id="title" required />
      </div>
      <div>
        <label for="fname">First Name:</label>
        <input type="text" v-model="doctor.fname" id="fname" required />
      </div>
      <div>
        <label for="lname">Last Name:</label>
        <input type="text" v-model="doctor.lname" id="lname" required />
      </div>
      <div>
        <label for="policlinic">Policlinic:</label>
        <select v-model="doctor.policlinicId" id="policlinic" required>
          <option v-for="policlinic in policlinics" :key="policlinic.id" :value="policlinic.id">
            {{ policlinic.name }}
          </option>
        </select>
      </div>
      <div>
        <label for="asistan">Assistant:</label>
        <select v-model="doctor.asistanId" id="asistan" required>
          <option v-for="asistan in asistans" :key="asistan.id" :value="asistan.id">
            {{ asistan.fname }} {{ asistan.lname }}
          </option>
        </select>
      </div>
      <button type="submit" class="fancy-button add-button">Create Doctor</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      doctor: {
        title: '',
        fname: '',
        lname: '',
        policlinicId: '',
        asistanId: ''
      },
      policlinics: [],
      asistans: []
    };
  },
  created() {
    this.fetchPoliclinics();
    this.fetchAsistans();
  },
  methods: {
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
    createDoctor() {
      axios.post('http://localhost:8080/doctor', this.doctor)
        .then(response => {
          console.log('Doctor created:', response.data);
          this.$router.push({ name: 'DoctorList' });
        })
        .catch(error => {
          console.error('Error creating doctor:', error);
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
  padding-top: 20px; /* Adjust the top padding as needed */
  text-align: center; /* Align text to center */
}

.create-doctor-form {
  width: 300px;
  padding: 20px;
  background-color: #f0f0f0;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-top: 20px; /* Space between the heading and form */
}

label {
  display: block;
  text-align: left; /* Align labels to the left */
  margin-bottom: 5px;
}

input, select {
  width: 100%;
  padding: 5px;
  margin-bottom: 10px;
  box-sizing: border-box; /* Ensure padding is included in the element's width */
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
