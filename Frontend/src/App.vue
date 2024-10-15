<!-- src/App.vue -->
<template>
  <div id="app">
    <router-link to="/DoctorList">Doctor List</router-link>
    <router-link to="/PoliclinicList"> - Policlinic List</router-link>
    <router-link to="/MedicineList"> - Medicine List</router-link>
    <router-link to="/AssistantList"> - Assistant List</router-link>
    <router-link to="/PatientList"> - Patient List</router-link>
    <router-link to="/AppointmentList"> - Appointment List</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>