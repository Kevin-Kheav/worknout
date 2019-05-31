<template>
  <div >
    <h4>{{this.event.title}}</h4>
    <div v-if="this.event.description">
      <label >Description: </label> {{this.event.description}}
    </div>
    <div v-if="this.event.location">
      <label>Localisation: </label> {{this.event.location}}
    </div>
    <div>
      <label>Date: </label> {{parseDate(this.event.date)}}
    </div>
    <div>
      <label>Participant(s): </label> 
      <ul id="eventParticipants">
        <li v-for="(user,index) in listUsers" :key="index">
          {{ user.name }}
        </li>
      </ul>
    </div>
  
    <div class="participation">
      <div class="user-inputs">
      <input type="text" class="form-control" v-model="user.name" placeholder="Nom" id="name" required name="name">
      <input type="text" class="form-control" v-model="user.email" placeholder="Mail" id="email" required email="email">
      </div>
      <div v-if="error">
          <p class="error-message">Veuillez remplir tous les champs</p>
      </div> 
      <span 
        v-on:click="addUser()"
        class="btn btn-success" id= "particip-btn">Je participe</span>
      
    </div>
    
    
  </div>

</template>

<script>
import http from "../http-common";

export default {
  name: "event",
  props: ["event"],
  data() {
    return {
      user: {
        id: 0,
        name: "",
        email: "",
      },
      listUsers :[],
      error:false

    };
  },
  methods: {
    /* eslint-disable no-console */
    addUser() {
      if((this.user.name==="")||(this.user.email==="")){
        this.error=true;
      }else{
        console.log("Pass")
        console.log(this.user)
        console.log(this.email)
        this.error=false;
        var data = {
        name: this.user.name,
        email: this.user.email,
      };
      
      http
        .put("/events/" + this.event.id+"/vote", data)
        .then(response => {
          this.user.id = response.data.id;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      this.listUsers.push(data);
      }
      
    },
    retrieveUsers() {
      http
        .get("/events/"+this.event.id+"/users")
        .then(response => {
          this.listUsers = response.data; 
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveEvents();
    },
    parseDate(date){
      let dateSplitted = date.split('T')
      let momentInYear = dateSplitted[0].split('-');
      let momentInDay = dateSplitted[1].split(':');
      return momentInYear[2]+'/'+momentInYear[1]+'/'+momentInYear[0]+" à "+momentInDay[0]+":"+momentInDay[1]
    }
    /* eslint-enable no-console */
  },
   mounted() {
    this.retrieveUsers();
  },
  watch: {
    event: function() {
      this.retrieveUsers()
    }
  } 
};
</script>

<style>

.user-inputs{
    display:flex;
    margin-bottom: 1rem;
}
input{
  margin-right:0.3rem;
}

</style>
