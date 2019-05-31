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
      <span 
        v-on:click="addUser()"
        class="button is-small btn-primary" id= "particip-btn">Je participe</span>
      
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
      listUsers :[]
    };
  },
  methods: {
    /* eslint-disable no-console */
    addUser() {
      var data = {
        name: this.user.name,
        email: this.user.email,
      };
      console.log(data)
      
      http
        .put("/events/" + this.event.id+"/vote", data)
        .then(response => {
          this.user.id = response.data.id;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrieveUsers() {
      console.log('====================================================')
      console.log(this.event)
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
  }
};
</script>

<style>
.participation{

}
.user-inputs{
    display:flex;
    margin-bottom: 1rem;
}
input{
  margin-right:0.3rem;
}
#particip-btn{
  padding: 0.6 rem;
  padding: 0.3rem;
  border-radius: 0.5rem;
  align-self: flex-end
}
</style>
