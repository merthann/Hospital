<template>
    <div class="create-patient-container">
      <h1>Create Patient</h1>
      <form @submit.prevent="submitForm" class="create-patient-form">
        <div class="form-group">
          <label for="firstName">First Name:</label>
          <input type="text" id="firstName" v-model="firstName" required>
        </div>
        <div class="form-group">
          <label for="lastName">Last Name:</label>
          <input type="text" id="lastName" v-model="lastName" required>
        </div>
        <div class="form-group">
          <label for="identityNo">Identity Number:</label>
          <input type="text" id="identityNo" v-model="identityNo" required>
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="email" required>
        </div>
        <div class="form-group">
          <label for="mobileNo">Mobile Number:</label>
          <input type="text" id="mobileNo" v-model="mobileNo" required>
        </div>
        <button type="submit" class="fancy-button">Create Patient</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        firstName: '',
        lastName: '',
        identityNo: '',
        email: '',
        mobileNo: ''
      };
    },
    methods: {
      submitForm() {
        const requestData = {
          fname: this.firstName,
          lname: this.lastName,
          identityno: this.identityNo,
          email: this.email,
          mobileno: this.mobileNo
        };
  
        axios.post('http://localhost:8080/patient', requestData)
          .then(response => {
            console.log('Patient created successfully:', response.data);
            this.$router.push({ name: 'PatientList' });
          })
          .catch(error => {
            console.error('Error creating patient:', error);
          });
      }
    }
  };
  </script>
  
  <style scoped>
  .create-patient-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start; 
    height: 100vh;
  }
  
  .create-patient-form {
    width: 250px;
    padding: 50px;
    background-color: #f0f0f0;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin-top: 0; 
  }
  
  .form-group {
    margin-bottom: 10px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input[type="text"],
  input[type="email"] {
    width: 100%;
    padding: 8px;
    font-size: 16px;
    border: 1px solid #ccc;
  }
  
  .fancy-button {
    width: 100%;
    background-color: #007BFF;
    color: white;
    padding: 10px;
    border: none;
    cursor: pointer;
    margin-top: 10px;
  }
  
  .fancy-button:hover {
    opacity: 0.8;
  }
  </style>
  