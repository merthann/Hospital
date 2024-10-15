<template>
  <div class="container">
    <h1>Create Appointment</h1>
    <form @submit.prevent="submitForm" class="create-form">
      <div class="form-group">
        <label for="patientIdentityno">Patient Identity No:</label>
        <input type="text" id="patientIdentityno" v-model="patientIdentityno" required>
      </div>
      <div class="form-group">
        <label for="doctorId">Doctor:</label>
        <select id="doctorId" v-model="doctorId" required>
          <option value="">Select a doctor</option>
          <option v-for="doctor in doctors" :key="doctor.id" :value="doctor.id">
            {{ doctor.fname }} {{ doctor.lname }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="appointmentDate">Appointment Date:</label>
        <input type="date" id="appointmentDate" v-model="appointmentDate" :min="minDate" required>
      </div>
      <div class="form-group">
        <label for="appointmentTime">Appointment Time:</label>
        <select id="appointmentTime" v-model="appointmentTime" required>
          <option value="">Set time</option>
          <option v-for="(label, value) in timeSlots" :key="value" :value="value">{{ label }}</option>
        </select>
      </div>
      <button type="submit" class="fancy-button">Create Appointment</button>
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      patientIdentityno: '',
      doctorId: '',
      appointmentDate: '',
      appointmentTime: '',
      timeSlots: {
        'S0830': '08:30', 'S0900': '09:00', 'S0930': '09:30', 'S1000': '10:00',
        'S1030': '10:30', 'S1100': '11:00', 'S1130': '11:30', 'S1200': '12:00',
        'S1230': '12:30', 'S1300': '13:00', 'S1330': '13:30', 'S1400': '14:00',
        'S1430': '14:30', 'S1500': '15:00', 'S1530': '15:30', 'S1600': '16:00'
      },
      doctors: [],
      patients: [],
      errorMessage: ''
    };
  },
  computed: {
    minDate() {
      const today = new Date().toISOString().split('T')[0];
      return today;
    }
  },
  created() {
    this.fetchDoctors();
    this.fetchPatients();
  },
  methods: {
    fetchDoctors() {
      axios.get('http://localhost:8080/doctor')
        .then(response => {
          this.doctors = response.data;
        })
        .catch(error => {
          console.error('Error fetching doctors:', error);
        });
    },
    fetchPatients() {
      axios.get('http://localhost:8080/patient')
        .then(response => {
          this.patients = response.data;
        })
        .catch(error => {
          console.error('Error fetching patients:', error);
        });
    },
    submitForm() {
      console.log('Patients fetched:', this.patients);
      console.log('Entered identity number:', this.patientIdentityno);

      const selectedPatient = this.patients.find(patient => patient.identityno === parseInt(this.patientIdentityno));

      console.log('Selected patient:', selectedPatient);

      if (!selectedPatient) {
        console.error('Patient with identity number not found');
        this.errorMessage = 'Patient with identity number not found';
        return;
      }

      const requestData = {
        patientid: selectedPatient.id,
        doctorid: this.doctorId,
        date: this.appointmentDate.split('-').map(Number),
        time: this.appointmentTime
      };

      // Date validation
      const selectedDate = new Date(this.appointmentDate);
      const today = new Date();
      today.setHours(0, 0, 0, 0); // Set time to midnight for comparison
      if (selectedDate < today) {
        this.errorMessage = 'Please select a date that is today or in the future.';
        return;
      }

      // Fetch all appointments
      axios.get('http://localhost:8080/appointment')
        .then(response => {
          const allAppointments = response.data;

          // Check for conflicts
          const conflictingAppointment = allAppointments.find(appointment =>
            appointment.doctor.id === requestData.doctorid &&
            appointment.appointmentDate[0] === requestData.date[0] &&
            appointment.appointmentDate[1] === requestData.date[1] &&
            appointment.appointmentDate[2] === requestData.date[2] &&
            appointment.appointmenttime === requestData.time
          );

          if (conflictingAppointment) {
            console.error('Error: An appointment with the same doctor, date, and time already exists');
            this.errorMessage = 'An appointment with the same doctor, date, and time already exists';
          } else {
            // Proceed to create the appointment
            axios.post('http://localhost:8080/appointment', requestData)
              .then(response => {
                console.log('Appointment created successfully:', response.data);
                this.$router.push({ name: 'AppointmentList' });
              })
              .catch(error => {
                console.error('Error creating appointment:', error);
                this.errorMessage = 'Error creating appointment';
              });
          }
        })
        .catch(error => {
          console.error('Error fetching appointments:', error);
          this.errorMessage = 'Error fetching appointments';
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
  padding-top: 20px;
  text-align: center;
}

.create-form {
  width: 300px;
  padding: 20px;
  background-color: #f0f0f0;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-top: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  text-align: left;
  margin-bottom: 5px;
}

input, select {
  width: calc(100% - 12px);
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

select {
  width: 100%;
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
