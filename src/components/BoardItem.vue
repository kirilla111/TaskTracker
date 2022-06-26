<template>
  <div class="boardItem" @click="ClickBoard">
    <div class="boardItem__name">
      {{ name }}
    </div>
  </div>
</template>

<script>
  import router from "../router/index";
  import axios from "axios";

  export default {
    name: 'BoardItem',
    props: {
      id: Number,
      name: String
    },
    data() {
      //это персональные данные
      return {
        board_name: this.name
      };
    },
    methods: {
      ClickBoard() {
        var vm = this;

        this.$store.commit("ChangeBoardId", this.id);
        this.$store.commit("ChangeBoardName", this.board_name);
       

        var axios = require('axios');

        var config = {
          method: 'get',
          url: 'http://' + this.$store.getters.ip + '/desk/get-pillars?token=' + this.$store.getters.userToken + '&deskId=' +
            this.$store.getters.board_id,
          headers: {},
        };

        /* `withCredentials` указывает, запрашивает ли межсайтовый контроль доступа или нет */
        axios.defaults.withCredentials = false;

        axios(config)
          .then(function (response) {
            console.log(JSON.stringify(response.data));
            vm.$store.commit("addPillars", response.data);
            router.replace("/board")
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
  }
</script>
<style scoped>
  .boardItem {
    background: #FFFFFF;
    border: 2px solid #FFFFFF;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.25), inset 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 64px;
    width: 287px;
    height: 147px;
    padding: 20px;
    display: flex;
    text-align: center;
    align-items: center;
    justify-content: center;


  }
   @media screen and (max-width: 400px) {
        .boardItem{
            max-width: 287px;
            height:80px;
        }
    }

  .boardItem__name {
    display: flex;
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: center;

  }

  .boardItem:hover {
    cursor: pointer;
  }

  p {
    padding: 0px;
  }

  h3 {
    margin: 40px 0 0;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>