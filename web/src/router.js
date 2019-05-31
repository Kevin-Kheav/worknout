import Vue from "vue";
import Router from "vue-router";
import EventsList from "./components/EventsList.vue";
import AddEvent from "./components/AddEvent.vue";
import Event from "./components/Event.vue";
import Home from "./components/Home.vue"

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "homepage",
      component: Home
    },
    {
      path: "/event",
      name: "events",
      component: EventsList,
      children: [
        {
          path: "/event/:id",
          name: "event-details",
          component: Event,
          props: true
        }
      ]
    },
    {
      path: "/add",
      name: "add",
      component: AddEvent
    }
  ]
});