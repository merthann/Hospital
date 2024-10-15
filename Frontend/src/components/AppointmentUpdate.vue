<template>
  <div>
    <h1>Update Appointment</h1>
    <form @submit.prevent="updateAppointment">
      <div>
        <label for="patient">Patient:</label>
        <select v-model="appointment.patientId" required>
          <option v-for="patient in patients" :key="patient.id" :value="patient.id">
            {{ capitalizeName(`${patient.fname} ${patient.lname}`) }}
          </option>
        </select>
      </div>
      <div>
        <label for="doctor">Doctor:</label>
        <select v-model="appointment.doctorId" required>
          <option v-for="doctor in doctors" :key="doctor.id" :value="doctor.id">
            {{ capitalizeName(`${doctor.title} ${doctor.fname} ${doctor.lname}`) }}
          </option>
        </select>
      </div>
      <div>
        <label for="time">Time:</label>
        <select v-model="appointment.time" required>
          <option v-for="(time, key) in timeSlots" :key="key" :value="key">
            {{ formatTimeSlot(key) }}
          </option>
        </select>
      </div>
      <div>
        <label for="date">Date:</label>
        <input type="date" v-model="appointment.date" :min="minDate" required />
      </div>
      <button type="submit" class="fancy-button update-button">Update</button>
      <p v-if="error" class="error-message">{{ error }}</p>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: ['id'],
  data() {
    return {
      appointment: {
        id: '',
        patientId: '',
        doctorId: '',
        time: '',
        date: ''
      },
      patients: [],
      doctors: [],
      timeSlots: {
        S0830: 1, S0900: 2, S0930: 3, S1000: 4,
        S1030: 5, S1100: 6, S1130: 7, S1200: 8,
        S1230: 0, S1300: 10, S1330: 11, S1400: 12,
        S1430: 13, S1500: 14, S1530: 15, S1600: 16
      },
      error: ''
    };
  },
  computed: {
    minDate() {
      const today = new Date().toISOString().split('T')[0];
      return today;
    }
  },
  created() {
    this.fetchPatients();
    this.fetchDoctors();
    this.fetchAppointmentDetails();
  },
  methods: {
    fetchPatients() {
      axios.get('http://localhost:8080/patient')
        .then(response => {
          this.patients = response.data;
        })
        .catch(error => {
          console.error('Error fetching patients:', error);
        });
    },
    fetchDoctors() {
      axios.get('http://localhost:8080/doctor')
        .then(response => {
          this.doctors = response.data;
        })
        .catch(error => {
          console.error('Error fetching doctors:', error);
        });
    },
    fetchAppointmentDetails() {
      const id = this.$route.params.id;
      axios.get(`http://localhost:8080/appointment/${id}`)
        .then(response => {
          const appointment = response.data;
          this.appointment.id = appointment.id;
          this.appointment.patientId = appointment.patient.id;
          this.appointment.doctorId = appointment.doctor.id;
          this.appointment.time = appointment.appointmenttime;
          this.appointment.date = this.formatDate(appointment.appointmentDate);
        })
        .catch(error => {
          console.error('Error fetching appointment details:', error);
        });
    },
    updateAppointment() {
      const updatedAppointment = {
        patientid: this.appointment.patientId,
        doctorid: this.appointment.doctorId,
        time: this.appointment.time,
        date: this.appointment.date.split('-').map(Number)
      };

      // Date validation
      const selectedDate = new Date(this.appointment.date);
      const today = new Date();
      today.setHours(0, 0, 0, 0); // Set time to midnight for comparison
      if (selectedDate < today) {
        this.error = 'Please select a date that is today or in the future.';
        return;
      }

      axios.get('http://localhost:8080/appointment')
        .then(response => {
          const allAppointments = response.data;

          const conflictingAppointment = allAppointments.find(appointment =>
            appointment.doctor.id === updatedAppointment.doctorid &&
            appointment.appointmentDate[0] === updatedAppointment.date[0] &&
            appointment.appointmentDate[1] === updatedAppointment.date[1] &&
            appointment.appointmentDate[2] === updatedAppointment.date[2] &&
            appointment.appointmenttime === updatedAppointment.time &&
            appointment.id !== this.appointment.id
          );

          if (conflictingAppointment) {
            console.error('Error: An appointment with the same doctor, date, and time already exists');
            this.error = 'An appointment with the same doctor, date, and time already exists';
          } else {
            axios.put(`http://localhost:8080/appointment/${this.appointment.id}`, updatedAppointment)
              .then(response => {
                console.log('Update successful:', response.data);
                this.$router.push({ name: 'AppointmentList' });
              })
              .catch(error => {
                console.error('Error updating appointment:', error);
                this.error = 'Error updating appointment';
              });
          }
        })
        .catch(error => {
          console.error('Error fetching appointments:', error);
          this.error = 'Error fetching appointments';
        });
    },
    formatDate(dateArray) {
      if (!dateArray || dateArray.length !== 3) {
        return 'Invalid Date';
      }
      const [year, month, day] = dateArray;
      return `${year}-${String(month).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
    },
    formatTimeSlot(timeString) {
      const hour = timeString.slice(1, 3);
      const minute = timeString.slice(3);
      return `${hour}:${minute}`;
    },
    capitalizeName(name) {
      return name.split(' ').map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ');
    }
  }
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
}

div {
  margin-bottom: 10px;
}

label {
  margin-right: 10px;
}

input, select {
  padding: 5px;
  margin-bottom: 10px;
}

.fancy-button {
  padding: 10px;
  background-color: #007BFF;
  color: white;
  border: none;
  cursor: pointer;
}

.fancy-button:hover {
  background-color: #0056b3;
}

.update-button {
  align-self: center;
}

.error-message {
  color: red;
  margin-top: 5px;
}
</style>
