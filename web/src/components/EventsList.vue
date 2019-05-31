<template>
    <div class="list row">
        <div class="event-col">
            <h4>Voici les prochains évènements à venir :</h4>
            <ul>
                <li v-for="(event, index) in events" :key="index">
                    <router-link :to="{
                            name: 'event-details',
                            params: { event: event, id: event.id }
                        }">
                            {{event.title}}
                    </router-link>
                </li>
            </ul>
        </div>
        <div class="event-det">
            <router-view @refreshData="refreshList"></router-view>
        </div>
    </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "events-list",
  data() {
    return {
      events: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    retrieveEvents() {
      http
        .get("/events")
        .then(response => {
          this.events = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveEvents();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.retrieveEvents();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 850px;
  margin: auto;
}
.event-col{
  flex: 0 0 50%;
  max-width: 40%;
}
.event-det{
  flex: 0 0 50%;
  max-width: 60%;
  padding-left: 1rem;
}
h4{
  font-size: 1.1rem;
  font-weight: bold;
}
</style>
