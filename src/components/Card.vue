<template>
  <div class="card">

    <div class="card__name" @click="showModalCardInfo()">
      {{name}}
    </div>
    <!-- <div class="card__name-hint">
      {{name}}
    </div> -->
    <div class="card__shordDescription" @click="showModalCardInfo()">
        {{shortDescription}}
      </div>

    <div class="card__info">

      <div class="card__owner" @click="showModalCardInfo()">
        {{owner}}
      </div>

      <div class="card__buttons">
        <button class="edit-button" @click="showModalEditCard()"><svg width="18" height="18" viewBox="0 0 18 18"
            fill="none" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M17.5043 4.9933L15.8835 6.61408C15.7183 6.77932 15.4511 6.77932 15.2858 6.61408L11.3833 2.71155C11.218 2.54631 11.218 2.27911 11.3833 2.11387L13.0041 0.493089C13.6615 -0.164363 14.7303 -0.164363 15.3913 0.493089L17.5043 2.60608C18.1652 3.26353 18.1652 4.33233 17.5043 4.9933ZM9.99104 3.50612L0.758577 12.7386L0.0132301 17.0103C-0.0887278 17.5868 0.41403 18.0861 0.990619 17.9876L5.2623 17.2388L14.4948 8.00633C14.66 7.84109 14.66 7.57389 14.4948 7.40864L10.5922 3.50612C10.4235 3.34088 10.1563 3.34088 9.99104 3.50612ZM4.36226 11.9475C4.16889 11.7542 4.16889 11.4448 4.36226 11.2514L9.77657 5.83709C9.96994 5.64372 10.2793 5.64372 10.4727 5.83709C10.6661 6.03046 10.6661 6.33984 10.4727 6.53321L5.05839 11.9475C4.86502 12.1409 4.55563 12.1409 4.36226 11.9475ZM3.09306 14.9043H4.78064V16.1805L2.51296 16.5778L1.41955 15.4844L1.81683 13.2167H3.09306V14.9043Z"
              fill="black" />
          </svg>
        </button>
        <button class="delete-button" @click="showModal()"><svg width="18" height="18" viewBox="0 0 18 18" fill="none"
            xmlns="http://www.w3.org/2000/svg">
            <path
              d="M12.4118 9L17.529 3.88278C18.157 3.25483 18.157 2.2367 17.529 1.60824L16.3918 0.470966C15.7638 -0.156989 14.7457 -0.156989 14.1172 0.470966L9 5.58818L3.88278 0.470966C3.25483 -0.156989 2.2367 -0.156989 1.60824 0.470966L0.470966 1.60824C-0.156989 2.23619 -0.156989 3.25432 0.470966 3.88278L5.58818 9L0.470966 14.1172C-0.156989 14.7452 -0.156989 15.7633 0.470966 16.3918L1.60824 17.529C2.23619 18.157 3.25483 18.157 3.88278 17.529L9 12.4118L14.1172 17.529C14.7452 18.157 15.7638 18.157 16.3918 17.529L17.529 16.3918C18.157 15.7638 18.157 14.7457 17.529 14.1172L12.4118 9Z"
              fill="black" />
          </svg>
        </button>
      </div>

    </div>
    <!-- Модальное окно - удаление карточки -->
    <ModalWindow ref="modalDeleteCard">
      <template v-slot:title>
        <h3 class="modal-title">Удалить карточку?</h3>
      </template>
      <template v-slot:body>
        <div class="modal-content">Отменить действие будет нельзя</div>
      </template>
      <template v-slot:footer>
        <div class="modal-footer"><button type="button" @click="closeModal"
            class="modal-footer__button save-button">Нет</button>
        </div>
        <button class="modal-footer__button save-button" type="button" @click="DeleteCard">Да</button>
      </template>
    </ModalWindow>
    <!-- Модальное окно - редактирование карточки -->
    <ModalWindow ref="modalEditCard">
      <template v-slot:title>
        <h3 class="modal-title">Редактирование</h3>
      </template>
      <template v-slot:body>
        <input type="text" placeholder="Название карточки" v-model="card_name" required>
        <input type="text" placeholder="Короткое описание карточки" v-model="card_shortDescription">
        <input type="text" placeholder="Описание карточки" v-model="card_description">
        <!-- <input type="text" placeholder="Владелец карточки" v-model="card_owner"> -->
      </template>
      <template v-slot:footer>
        <button type="button" class="modal-footer__button save-button" @click="SaveCardChangeData">Сохранить</button>
      </template>
    </ModalWindow>
    <!-- Модельное окно - просмотр карточки -->
    <ModalCardInformation ref="modalCardInfo" :name="name" :shortDescription="shortDescription" :description="description" :owner="owner"></ModalCardInformation>
  
  </div>
</template>

<script>
  import ModalWindow from './Modals/ModalWindow.vue'
   import ModalCardInformation from './Modals/ModalCardInformation.vue'

  export default {
    name: 'Card',
    components: {
      ModalWindow,
      ModalCardInformation
    },
    // props: {
    //   name: String,
    //   card_id: Number,
    //   cards: Object
    // },
    props: ['name', 'card_id', 'shortDescription', 'description', 'owner', 'pillar_id'],
    data() {
      //это персональные данные
      return {
        card_name: this.name,
        card_description: this.description,
        card_shortDescription: this.shortDescription,
        card_owner: this.owner
        // Ошибка в том что в owner поступает массив с данными владельца , но они не используются
      };
    },
    methods: {
      showModal() {
        this.$refs.modalDeleteCard.show = true
      },
      closeModal() {
        this.$refs.modalDeleteCard.show = false
      },
      closeModalEditCard() {
        this.$refs.modalEditCard.show = false
      },
      showModalEditCard() {
        this.$refs.modalEditCard.show = true
      },
       closeModalCardInfo() {
        this.$refs.modalCardInfo.show = false
      },
      showModalCardInfo() {
        this.$refs.modalCardInfo.show = true
      },
      DeleteCard() {
        var vm = this;
        var axios = require('axios');

        var config = {
          method: 'delete',
          url: 'http://' + this.$store.getters.ip + '/card?token=' + this.$store.getters.userToken + '&cardId=' + this
            .card_id,
          headers: {
            'Cookie': 'JSESSIONID=84ADD4D104E66F3E4CB1F48C77561524'
          }
        };
        axios.defaults.withCredentials = false;

        axios(config)
          .then(function (response) {
            console.log(JSON.stringify(response.data));
            vm.$store.dispatch('GETPILLARS', vm.$store.getters.userToken);
            vm.closeModal();
          })
          .catch(function (error) {
            console.log(error);
          });
      },

      SaveCardChangeData() {
        var vm = this;
        var axios = require('axios');
        var data = JSON.stringify({
          "id": this.card_id,
          "title": this.card_name,
          "shortDescription": this.card_shortDescription,
          "description": this.card_description,
        });

        console.log(this.card);
        var config = {
          method: 'post',
          url: 'http://' + this.$store.getters.ip + '/card/edit?token=' + this.$store.getters.userToken +
            '&pillarId=' + this.pillar_id,
          headers: {
            'Content-Type': 'application/json'
          },
          data: data
        };

        axios(config)
          .then(function (response) {
            console.log(JSON.stringify(response.data));
            vm.$store.dispatch('GETPILLARS', vm.$store.getters.userToken);
            vm.closeModalEditCard();
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    created() {
      // if (this.owner=='null') {
      //   this.card_owner="";
      // }
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .card {
    display: flex;
    flex-direction: column;
    padding: 10px;
    padding-left: 25px;
    background: #FBFBFB;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 55px;
    width: 333px;
    min-height: 90px;
    font-size: 20px;
  }

  @media screen and (max-width: 400px) {
    .card {
      width: 250px;
    }

    .card__name {
      width: 180px;
    }

    /* .card__name-hint {
      padding: 5px;
      width: 180px;
    } */
  }

  .card__name {
    display: flex;
    align-items: center;
    padding-bottom: 5px;
    max-width: 280px;
    max-height: 50px;
    font-size: 22px;
    font-weight: 600;
    color: #545454;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .card__owner {
    display: flex;
    font-size: 20px;
    max-width: 240px;
    max-height: 50px;
    display: flex;
    align-items: center;
   
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    color: #000000;
    background-color: #7DC3FD;
    border-radius: 50px;
    padding: 3px;
  }

  .card__info{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }

  .card__shordDescription{
    display: flex;
    align-items: center;
    text-align: left;
    padding-bottom: 5px;
  }

  .card__name:hover {
    cursor: pointer;
     color: #1b1b1b;
  }

  /* .card__name-hint {
    display: none;
    overflow-wrap: break-word;
    padding: 15px;
    width: 260px;
    min-height: 40px;
    background: #FBFBFB;
    box-shadow: 1px 4px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
  }

  .card__name:hover+.card__name-hint {
    display: block;
    position: absolute;
    color: #151516;
    font-size: 18px;
    overflow: inherit;
    text-overflow: inherit;
    white-space: inherit;
  } */

  .card__buttons {
    padding: 5px;
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

  button {
    padding: 0;
    margin: auto;
    border: none;
    background-color: #FBFBFB;
    ;
    margin-right: 10px;
    cursor: pointer;
  }

  button.save-button {
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

  /* Full-width input fields */
  input[type=text],
  input[type=password] {


    margin: 13px 0;

    width: 100%;
    padding: 12px 20px;

    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
  }
</style>