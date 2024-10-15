<template>
  <div>
    <h1>Appointment List</h1>
    <button @click="navigateToCreateAppointment" class="fancy-button create-button">Create Appointment</button>
    <div class="appointment-table">
      <div class="appointment-row header">
        <div class="cell">Patient</div>
        <div class="cell">Doctor</div>
        <div class="cell">Time</div>
        <div class="cell">Date</div>
        <div class="cell">Actions</div>
      </div>
      <div v-for="appointment in appointments" :key="appointment.id" class="appointment-row">
        <div class="cell">{{ capitalizeName(`${appointment.patient.fname} ${appointment.patient.lname}`) }}</div>
        <div class="cell">{{ capitalizeName(`${appointment.doctor.title} ${appointment.doctor.fname} ${appointment.doctor.lname}`) }}</div>
        <div class="cell">{{ formatTime(appointment.appointmenttime) }}</div>
        <div class="cell">{{ formatDate(appointment.appointmentDate) }}</div>
        <div class="cell">
          <button @click="navigateToUpdateAppointment(appointment.id)" class="fancy-button update-button">Update</button>
          <button @click="deleteAppointment(appointment.id)" class="fancy-button delete-button">Delete</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      appointments: []
    };
  },
  created() {
    this.fetchAppointments();
  },
  methods: {
    fetchAppointments() {
      axios.get('http://localhost:8080/appointment')
        .then(response => {
          this.appointments = response.data;
          this.sortAppointments(); // Sort appointments after fetching
        })
        .catch(error => {
          console.error('Error fetching appointments:', error);
        });
    },
    deleteAppointment(id) {
      axios.delete(`http://localhost:8080/appointment/${id}`)
        .then(() => {
          this.appointments = this.appointments.filter(appointment => appointment.id !== id);
        })
        .catch(error => {
          console.error('Error deleting appointment:', error);
        });
    },
    navigateToCreateAppointment() {
      this.$router.push({ name: 'CreateAppointment' });
    },
    navigateToUpdateAppointment(id) {
      this.$router.push({ name: 'AppointmentUpdate', params: { id } });
    },
    formatDate(dateArray) {
      if (!dateArray || dateArray.length !== 3) {
        return 'Invalid Date';
      }
      const [year, month, day] = dateArray;
      const paddedMonth = month.toString().padStart(2, '0');
      const paddedDay = day.toString().padStart(2, '0');
      return `${paddedDay}.${paddedMonth}.${year}`;
    },
    capitalizeName(name) {
      return name.split(' ').map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ');
    },
    formatTime(timeString) {
      if (!timeString.startsWith('S') || timeString.length !== 5) {
        return 'Invalid Time';
      }
      const hour = parseInt(timeString.slice(1, 3), 10);
      const minute = timeString.slice(3);
      return `${hour}:${minute}`;
    },
    sortAppointments() {
      this.appointments.sort((a, b) => a.id - b.id); // Sort appointments by ID ascending
    }
  }
};
</script>

<style scoped>
.appointment-table {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

.appointment-row {
  display: flex;
  border-bottom: 1px solid black; /* Set border color to black */
}

.appointment-row.header {
  font-weight: bold;
  background-color: #f0f0f0;
}

.cell {
  padding: 5px; /* Adjust padding for compact spacing */
  flex: 1;
}

.cell:nth-child(5) {
  flex: 1;
}

.fancy-button {
  margin-right: 5px; /* Adjust margin for compact spacing */
}

.create-button {
  margin-bottom: 10px;
}

.update-button {
  background-color: #FFA500;
  color: white;
}


.delete-button {
  background-color: #f44336; /* Red */
  color: white;
}

.fancy-button:hover {
  opacity: 0.8;
  cursor: pointer;
}

.appointment-table {
  max-width: 800px;
  margin: 0 auto;
}

h1, .fancy-button.create-button {
  text-align: center;
}
</style>
