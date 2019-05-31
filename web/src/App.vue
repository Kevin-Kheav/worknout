<template>
    <div id="app" class="container-fluid">
        <div class="site-info">
            <h1>Work'n'out</h1>
            <h3>Gestionnaire d'évènements</h3>
        </div>
        <nav>
            <router-link class="btn btn-primary" to="/">Acceuil</router-link>
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
</style>
