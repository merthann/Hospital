<template>
  <div>
    <h1>Update Policlinic</h1>
    <form @submit.prevent="submitUpdate">
      <label for="name">Name:</label>
      <input type="text" v-model="policlinic.name" id="name" required />

      <label for="floor">Floor:</label>
      <input type="number" v-model="policlinic.floor" id="floor" required />

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
      policlinic: {
        name: '',
        floor: ''
      }
    };
  },
  created() {
    this.fetchPoliclinic();
  },
  methods: {
    fetchPoliclinic() {
      
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No policlinic ID provided');
        return;
      }
      axios.get(`http://localhost:8080/policlinic/${id}`)
        .then(response => {
          this.policlinic = response.data;
          console.log('Fetched policlinic:', this.policlinic);
        })
        .catch(error => {
          console.error('Error fetching policlinic:', error);
        });
    },
    submitUpdate() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No policlinic ID provided');
        return;
      }
      axios.put(`http://localhost:8080/policlinic/${id}`, this.policlinic)
        .then(response => {
          console.log('Update response:', response.data);
          this.$router.push('/PoliclinicList');
        })
        .catch(error => {
          console.error('Error updating policlinic:', error);
        });
    }
  }
};
</script>
