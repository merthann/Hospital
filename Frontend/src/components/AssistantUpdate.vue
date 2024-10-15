<template>
  <div>
    <h1>Update Assistant</h1>
    <form @submit.prevent="submitUpdate">
      <label for="fname">First Name:</label>
      <input type="text" v-model="asistan.fname" id="fname" />

      <label for="lname">Last Name:</label>
      <input type="text" v-model="asistan.lname" id="lname" />

      <label for="workdays">Workdays:</label>
      <input type="text" v-model="asistan.workdays" id="workdays" />
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
      asistan: {}
    };
  },
  created() {
    this.fetchAsistan();
  },
  methods: {
    fetchAsistan() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No asistan ID provided');
        return;
      }
      axios.get(`http://localhost:8080/asistan/${id}`)
        .then(response => {
          this.asistan = response.data;
        })
        .catch(error => {
          console.error('Error fetching asistan:', error);
        });
    },
    submitUpdate() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No asistan ID provided');
        return;
      }
      axios.put(`http://localhost:8080/asistan/${id}`, this.asistan)
        .then(() => {
          this.$router.push('/AssistantList');
        })
        .catch(error => {
          console.error('Error updating asistan:', error);
        });
    }
  }
};
</script>
