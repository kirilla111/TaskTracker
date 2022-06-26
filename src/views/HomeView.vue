<template>
  <div class="home">
    <div class="home__panel">
      <button @click="showModal">Создать доску ✚</button>
    </div>

    <BoardList></BoardList>

    <ModalWindow ref="modalCreateDesk">
      <template v-slot:title>
        <h3 class="modal-title">Создать</h3>
      </template>
      <template v-slot:body>
        <input type="text" placeholder="Название доски" v-model="board_name" required>
        <input type="text" placeholder="Описание доски" v-model="board_description">
      </template>
      <template v-slot:footer>
        <button type="submit" @click="CreateBoard">Создать</button>
      </template>
    </ModalWindow>
  </div>
</template>

<script>
  import BoardList from '@/components/BoardList.vue'
  import ModalWindow from '@/components/Modals/ModalWindow.vue'

  export default {
    name: 'HomeView',
    components: {
      BoardList,
      ModalWindow
    },
    data() {
      //это персональные данные
      return {
        board_name: "",
        board_description: "",
        boardList: []
      };
    },
    methods: {
      showModal() {
        this.$refs.modalCreateDesk.show = true
      },
      closeModal() {
        this.$refs.modalCreateDesk.show = false
      },
      CreateBoard() {
        var vm = this;
        /* запрос */
        //this.$store.commit("addBoard", this.product_info);
        console.log(this.board_name);
        console.log(this.board_description);

        var axios = require('axios');

        var config = {
          method: 'post',
          url: 'http://' + this.$store.getters.ip + '/desk/create?token=' + this.$store.getters.userToken +
            '&title=' + this.board_name + '&description=' + this.board_description,
          headers: {}
        };

        axios(config)
          .then(function (response) {
            console.log(JSON.stringify(response.data));
            vm.$store.dispatch('GETDESKS', vm.$store.getters.userToken);
            vm.closeModal();

          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
  }
</script>

<style scoped>
  .home__panel {
    margin: 36px;
  }

  button {
    padding: 0;
    background: #4D98D5;
    border-radius: 60px;
    color: white;
    font-size: 20px;
    margin: 13px 0;
    border: none;
    cursor: pointer;
    width: 250px;
    height: 52px;
  }

  button[type=submit] {
    background-color: #7DC3FD;
    color: #323232;
  }

  /* Add a hover effect for buttons */
  button:hover {
    opacity: 0.8;
  }

  /* Full-width input fields */
  input[type=text],
  input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 13px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
  }

   @media screen and (max-width: 400px) {
        button{
            width: 230px;
        }
    }
</style>