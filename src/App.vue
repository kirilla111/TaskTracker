<template>
 <div>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/board">Board</router-link> |
    <router-link to="/login">Login</router-link>
   
  </nav> -->

 <router-view></router-view>
 </div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Inter&display=swap');

html {
    min-height: 100% !important;
    height: 100%;
}

*{
  box-sizing: border-box;
  padding: 0px;
  margin: 0px;
  font-family: 'Inter', sans-serif;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 20px;
  background-color: rgb(77, 152, 213);
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #ffffff;
}
</style>
