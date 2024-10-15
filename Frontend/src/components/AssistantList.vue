<template>
  <div>
    <h1>Assistant List</h1>
    <button @click="navigateToCreateAssistant" class="fancy-button create-button">Create Assistant</button>
    <ul>
      <li v-for="asistan in asistans" :key="asistan.id">
        {{ capitalize(asistan.fname) }} {{ capitalize(asistan.lname) }} - Work days:  {{ asistan.workdays }}
        <button @click="navigateToUpdateAsistan(asistan.id)" class="fancy-button update-button">Update</button>
        <button @click="deleteAsistan(asistan.id)" class="fancy-button delete-button">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      asistans: []
    };
  },
  created() {
    this.fetchAsistans();
  },
  methods: {
    fetchAsistans() {
      axios.get('http://localhost:8080/asistan')
        .then(response => {
          this.asistans = response.data;
          this.sortAsistansById();
          console.log('Fetched assistants:', this.asistans); 
        })
        .catch(error => {
          console.error('Error fetching assistants:', error);
        });
    },
    sortAsistansById() {
      this.asistans.sort((a, b) => a.id - b.id);
    },
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    },
    navigateToUpdateAsistan(id) {
      console.log('Attempting to navigate to update with ID:', id);
      if (id) {
        this.$router.push({ name: 'AssistantUpdate', params: { id: id.toString() } })
          .then(() => console.log('Navigation successful'))
          .catch(err => console.error('Navigation failed:', err));
      } else {
        console.error('Asistan ID is undefined');
      }
    },
    deleteAsistan(id) {
      axios.delete(`http://localhost:8080/asistan/${id}`)
        .then(() => {
          this.asistans = this.asistans.filter(asistan => asistan.id !== id);
          console.log(`Deleted asistan with ID: ${id}`);
        })
        .catch(error => {
          console.error('Error deleting asistan:', error);
        });
    },
    navigateToCreateAssistant() {
      this.$router.push({ name: 'CreateAssistant' });
    }
  }
};
</script>
