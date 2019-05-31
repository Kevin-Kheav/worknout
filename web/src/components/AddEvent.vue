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
          <div class="moment">
            <input type="number" class=" input-date" min="1" max="31" id="day" required v-model="date.day" name="day">
            <input type="number" class=" input-date" min="1" max="12" id="month" required v-model="date.month" name="month">
            <input type="number" class=" input-date" min="2019" max="2100" id="year" required v-model="date.year" name="year">
          </div>
            <label for="date">Heure*</label>
          <div class="time">
            <input type="number" class=" input-date" min="0" max="23" id="hour" required v-model="date.hour" name="hour">
            <input type="number" class=" input-date" min="0" max="59" id="minute" required v-model="date.minute" name="minute">
          </div>
           </div>
        <div v-if="error">
          <p class="error-message">Veuillez remplir les champs obligatoires</p>
        </div>        
        <p>*Champs obligatoires</p>

        <button v-on:click="saveEvent" class="btn btn-success">Créer</button>
    </div>
    
    <div v-else>
      <h4>Evènement créé!</h4>
      <router-link class="btn btn-success" to="/event">Retour à la liste</router-link>
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
        day:1,
        month:1,
        year:2019,
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
      if((this.event.title==="")){
        this.error=true;
      }else{
        this.error=false
        var data = {
          title: this.event.title,
          description: this.event.description,
          location: this.event.location,
          date:this.date.year+"-"+((this.date.month<10)?"0":"")+this.date.month+"-"+((this.date.day<10)?"0":"")+this.date.day+"T"+((this.date.hour<10)?"0":"")+this.date.hour+":"+ ((this.date.minute<10)?"0":"")+this.date.minute+":00"
        };
      console.log(data)
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
.input-date{
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
  width: 5rem;
}
.moment{
  display:flex,
}
.time{
  display: flex,
}
</style>

