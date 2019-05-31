<template>
  <div class="submitform">
    <div v-if="!submitted">
        <div class="form-group">
          <label for="title">Titre de l'évènement*</label>
          <input type="text" class="form-control" id="title"  v-model="event.title" name="title" required>
        </div>
      <div class="form-group">
          <label for="description">Description</label>
          <input type="text" class="form-control" id="description" required v-model="event.description" name="description">
        </div>
        <div class="form-group">
          <label for="location">Localisation</label>
          <input type="text" class="form-control" id="location" required v-model="event.location" name="location">
        </div>
        <div class="form-group">
          <label for="date">Date*</label>
          <input type="text" class="form-control" id="date" required v-model="event.date" name="date">
        </div>
        <div v-if="error">
          <p class="error-message">Veuillez remplir les champs obligatoires</p>
        </div>        
        <p>*Champs obligatoires</p>

        <button v-on:click="saveEvent" class="btn btn-success">Créer</button>
    </div>
    
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" v-on:click="newEvent">Add</button>
    </div>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "add-event",
  
  data() {
    return {
      event: {
        id: 0,
        title: "",
        description: "",
        location: "",
        date:"",
        listUsers:[]
      },
      date:{
        day:0,
        month:0,
        year:0,
        hour:0,
        minute:0
      },
      submitted: false,
      error:false
    };
  },
  methods: {
    /* eslint-disable no-console */
    saveEvent() {
      if((this.event.title==="")||(this.event.date==="")){
        this.error=true;
      }else{
        this.error=false
        var data = {
          title: this.event.title,
          description: this.event.description,
          location: this.event.location,
          date:this.event.date,
        };

      http
        .post("/events", data)
        .then(response => {
          this.event.id = response.data.id;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

      this.submitted = true;
      }
      
    },
    newEvent() {
      this.submitted = false;
      this.event = {};
    }
    /* eslint-enable no-console */
  }
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
.error-message{
  color: red
}
</style>
