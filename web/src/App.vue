<template>
    <div id="app" class="container-fluid">
        <div class="site-info">
          <div id="app">
            <img src="./assets/logo.png">
          </div>
          <h3>Gestionnaire d'évènements</h3>
        </div>
        <nav>
            <router-link class="btn btn-primary" to="/">Accueil</router-link>
            <router-link class="btn btn-primary" to="/event">Voir tous les évènements</router-link>
            <router-link class="btn btn-primary" to="/add">Créer un évènements</router-link>
            
        </nav>
        <br/>
        <router-view/>
    </div>
</template>

<script>

export default {
  name: "app"
};
</script>

<style>
.site-info {
  color: blue;
  margin-bottom: 20px;
}

.btn-primary {
  margin-right: 5px;
}

.container-fluid {
  text-align: center;
}

h3 {
  color: #0ea7c5;
  font-size: 1.5rem;

}
img {
  width: 10rem;
}
</style>
