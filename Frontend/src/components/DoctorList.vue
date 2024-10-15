<template>
  <div>
    <h1>Doctor List</h1>
    <button @click="navigateToCreateDoctor" class="fancy-button create-button">Create Doctor</button>
    <ul v-if="doctors.length > 0">
      <li v-for="doctor in doctors" :key="doctor.id">
        {{ getPoliclinicName(doctor.policlinic.id) }} - {{ doctor.title }} {{ capitalize(doctor.fname) }} {{ capitalize(doctor.lname) }}
        <button @click="navigateToUpdateDoctor(doctor.id)" class="fancy-button update-button">Update</button>
        <button @click="deleteDoctor(doctor.id)" class="fancy-button delete-button">Delete</button>
      </li>
    </ul>
    <p v-else>No doctors found</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      doctors: [],
      policlinics: {}
    };
  },
  created() {
    this.fetchDoctors();
    this.fetchPoliclinics();
  },
  methods: {
    fetchDoctors() {
      axios.get('http://localhost:8080/doctor')
        .then(response => {
          this.doctors = response.data;
          this.sortDoctorsById();
          console.log('Fetched doctors:', this.doctors);
        })
        .catch(error => {
          console.error('Error fetching doctors:', error);
        });
    },
    fetchPoliclinics() {
      axios.get('http://localhost:8080/policlinic')
        .then(response => {
          response.data.forEach(policlinic => {
            this.policlinics[policlinic.id] = policlinic.name;
          });
        })
        .catch(error => {
          console.error('Error fetching policlinics:', error);
        });
    },
    sortDoctorsById() {
      this.doctors.sort((a, b) => a.id - b.id);
    },
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    },
    getPoliclinicName(policlinicId) {
      return this.policlinics[policlinicId] || 'Unknown';
    },
    navigateToUpdateDoctor(id) {
      console.log('Navigating to update doctor with ID:', id);
      if (id) {
        this.$router.push({ name: 'DoctorUpdate', params: { id: id.toString() } })
          .then(() => console.log('Navigation successful'))
          .catch(err => console.error('Navigation failed:', err));
      } else {
        console.error('Doctor ID is undefined');
      }
    },
    deleteDoctor(id) {
      console.log('Deleting doctor with ID:', id);
      axios.delete(`http://localhost:8080/doctor/${id}`)
        .then(() => {
          this.doctors = this.doctors.filter(doctor => doctor.id !== id);
          console.log(`Deleted doctor with ID: ${id}`);
        })
        .catch(error => {
          console.error('Error deleting doctor:', error);
        });
    },
    navigateToCreateDoctor() {
      this.$router.push({ name: 'CreateDoctor' });
    }
  }
};
</script>

<style>
body {
  background-color: lightblue;
}
h1 {
  color: white;
  text-align: center;
}
ul {
  text-align: center;
}
li {
  text-align: center;
  list-style-type: none;
}
div {
  text-align: center;
}
.fancy-button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.fancy-button:hover {
  background-color: #45a049;
  transform: scale(1.05);
}

.create-button {
  background-color: #008CBA;
}

.create-button:hover {
  background-color: #007bb5;
}

.update-button {
  background-color: #FFA500;
}

.update-button:hover {
  background-color: #e59400;
}

.delete-button {
  background-color: #f44336;
}

.delete-button:hover {
  background-color: #da190b;
}

.add-button {
  background-color: #4CAF50;
}

.add-button:hover {
  background-color: #45a049;
}
</style>
