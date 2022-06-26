<template>
  <div class="login">
    <!-- <form action=""> -->
    <div class="container">
      <label class="label"><b>Авторизация</b></label>
      <span>
        <input type="text" placeholder="Логин" v-model="login" required>
      </span>
      <span>
        <input type="password" placeholder="Пароль" v-model="password" required>
      </span>
      <span>
        <button @click="Login">Авторизироваться</button>
      </span>

      <span>
        <!-- <button @click="showModal" class="button__text">Нет аккаунта?</button> -->
        <a @click="showModal">Нет аккаунта?</a>
      </span>
    </div>
    <!-- </form> -->
    <ModalWindowInformation ref="modalInformation" :message="this.message">
    </ModalWindowInformation>

    <ModalWindow ref="modalRegistration">
      <template v-slot:title>
        <h3 class="modal-title">Регистрация</h3>
      </template>
      <template v-slot:body>
        <input type="text" placeholder="Логин" v-model="login" required>
        <input type="email" placeholder="Почта" v-model="email" required>
        <input type="text" placeholder="Имя" v-model="firstName" required>
         <input type="text" placeholder="Отчество" v-model="patronymic" required>
          <input type="text" placeholder="Фамилия" v-model="lastName" required>
           <input type="password" placeholder="Пароль" v-model="password" required>
      </template>
      <template v-slot:footer>
        <button type="submit" @click="Registr">Зарегистрироваться</button>
      </template>
    </ModalWindow>
  </div>

</template>

<script>
  import router from "../router/index";
  import axios from "axios";
  import ModalWindow from "@/components/Modals/ModalWindow.vue";
   import ModalWindowInformation from "@/components/Modals/ModalWindowInformation.vue";


  export default {
    name: "LoginView",
    components: {
      ModalWindow,
      ModalWindowInformation
    },
    props: {},
    data() {
      //это персональные данные
      return {
        login: "",
        password: "",
        email: "",
        firstName: "",
        patronymic: "",
        lastName: "",
        message: ""
      };
    },
    methods: {
      showModal() {
        this.password = "";
        this.$refs.modalRegistration.show = true;
      },
      closeModal() {
        this.$refs.modalRegistration.show = false;
      },
      showModalInfo() {
        this.password = "";
        this.$refs.modalInformation.show = true;
      },
      closeModalInfo() {
        this.$refs.modalInformation.show = false;
      },
      Login() {
        // для тестирования
        this.login = "u1";
        this.password = "u1";
        //
        var vm = this;
        var axios = require("axios");
        var config = {
          method: "get",
          url: "http://" + this.$store.getters.ip + "/user/login?login=" + this.login + "&password=" + this.password,
          headers: {}
        };
        axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
        axios(config)
          .then(function (response) {
            console.log(JSON.stringify(response.data));
            vm.$store.commit("saveToken", response.data);
            vm.$store.dispatch("GETDESKS", response.data);
            router.replace("/");
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      Registr() {
        var vm = this;
   
        var axios = require('axios');
        var data = JSON.stringify({
          "login": this.login,
          "email": this.email,
          "firstName": this.firstName,
          "patronymic": this.patronymic,
          "lastName": this.lastName,
          "password": this.password
        });

        var config = {
          method: 'post',
          url: 'http://' + this.$store.getters.ip + '/user/register',
          headers: {
            'Content-Type': 'application/json',
            'Cookie': 'JSESSIONID=D8E2E55BE9DE6244AA2C012ED469D21D'
          },
          data: data
        };

        axios(config)
          .then(function (response) {
            console.log(JSON.stringify(response.data));
            vm.message="Регистрация пройдена успешно!";
            vm.closeModal();
           
           
          })
          .catch(function (error) {
            console.log(error);
            vm.message="Ошибка! Повторите попытку.";
            vm.closeModal();
          });
          console.log(this.message);
          this.showModalInfo();
      },
    }
  };
</script>

<style scoped>
  form {
    border: 3px solid #f1f1f1;
  }

  .login {
    position: absolute;
    top: 0;
    left: 0;
    min-height: 100%;
    width: 100%;
  }

  .container {
    min-width: 420px;
    max-width: 480px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  /* Full-width inputs */
  input[type="text"],
  input[type="password"],
  input[type="email"] {
    width: 350px;
    height: 48px;
    background: #7DC3FD;
    border-radius: 60px;
    padding: 12px 20px;
    margin: 13px 0;
    color: #2D2D2DA6;
    font-size: 20px;
    border: 1px solid #ccc;
    box-sizing: border-box;
  }



  .label {
    font-family: 'Inter';
    font-style: normal;
    font-weight: 400;
    font-size: 32px;
    line-height: 39px;
    text-align: center;
    margin: 26px;
  }

  a {
    text-decoration: underline;
    font-size: 18px;
    cursor: pointer;
  }

  button {
    background: #7DC3FD;
    border-radius: 60px;
    color: #323232;
    font-size: 20px;
    padding: 14px 20px;
    margin: 13px 0;
    border: none;
    cursor: pointer;
    width: 250px;
    height: 52px;
  }

  button:hover {
    opacity: 0.8;
  }

  .container {
    padding: 16px;
    display: flex;
    flex-direction: column;
  }

  @media screen and (max-width: 400px) {

    input[type="text"],
    input[type="password"],
    input[type="email"] {
      width: 230px;
      margin: 5px;
    }

    button {
      font-size: 18px;
      width: 230px;

    }
  }
</style>