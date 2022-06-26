<template>
    <div class="board">
        <div class="board__panel">
            <div class="panel__settings">
                <button @click="Back">Назад к доскам</button>
            </div>
            <div class="panel__name">
                <p>{{ board_name }}</p>
            </div>
            <div class="panel__settings">
                <button @click="showModalDelete">Удалить доску</button>
                <button @click="showModalCreate">Создать карточку ✚</button>
                <button @click="showModalAddPeople">Пригласить</button>
            </div>
        </div>

        <!-- Модальное окно - Удаление доски -->
        <ModalWindow ref="modalDeleteBoard">
            <template v-slot:title>
                <h3 class="modal-title">Удалить доску?</h3>
            </template>
            <template v-slot:body>
                <div class="modal-content">Отменить действие будет нельзя</div>
            </template>
            <template v-slot:footer>
                <div class="modal-footer">
                    <button type="button" @click="closeModal" class="modal-footer__button">Нет</button>
                    <button class="modal-footer__button" type="button" @click="DeleteBoard">Да</button>
                </div>


            </template>
        </ModalWindow>
        <!-- Модальное окно - Создание карточки -->
        <ModalWindow ref="modalCreateCard">
            <template v-slot:title>
                <h3 class="modal-title">Создать</h3>
            </template>
            <template v-slot:body>
                <input type="text" placeholder="Введите название карточки" v-model="card_name" required>
            </template>
            <template v-slot:footer>
                <div class="modal-footer">
                    <button class="modal-footer__button" type="button" @click="CreateCard">Создать</button>
                </div>


            </template>
        </ModalWindow>
        <!--  -->
        <div class="board__column">
            <Column @dragenter.prevent @dragover.prevent @drop="onDrop($event, item.id)" :pillar="item"
                :name="item.title" v-for="(item, index) in this.$store.getters.pillars" :key="index">
            </Column>
        </div>
        <!-- Модбльное окно - Пригласить людей -->
        <ModalWindow ref="modalAddPeople">
            <template v-slot:title>
                <h3 class="modal-title">Пригласить</h3>
            </template>
            <template v-slot:body>
                <input type="text" placeholder="Введите логин пользователя" v-model="loginToAdd" required>
            </template>
            <template v-slot:footer>
                <div class="modal-footer">
                    <button class="modal-footer__button" type="button" @click="AddPeople()">Пригласить</button>
                </div>
            </template>
        </ModalWindow>

        <ModalWindowInformation ref="modalInformation" :message="this.message">
        </ModalWindowInformation>
    </div>
</template>

<script>
    import Column from '@/components/Column.vue'
    import axios from "axios";
    import router from "../router/index";
    import ModalWindow from '../components/Modals/ModalWindow.vue';
    import ModalWindowInformation from '../components/Modals/ModalWindowInformation.vue';

    export default {
        name: 'BoardView',
        components: {
            Column,
            ModalWindow,
            ModalWindowInformation
        },
        data() {
            //это персональные данные
            return {
                boardList: [],
                id: -1,
                board_name: '',
                card_name: '',
                loginToAdd: '',
                message: ''
            };
        },
        methods: {
            onDrop(event, id) {

                const cardId = event.dataTransfer.getData('cardId');
                const card = event.dataTransfer.getData('card');
                const pillarId = event.dataTransfer.getData('pillarId');
                var dto = {
                    pillarId: id,
                    cardId: cardId,
                    card: card,
                    fromPillarId: pillarId
                }

                // this.$store.commit("pushCard", dto);


                var vm = this;
                var axios = require('axios');

                var config = {
                    method: 'post',
                    url: 'http://' + this.$store.getters.ip + '/card/move?token=' + this.$store.getters.userToken +
                        '&pillarId=' + id + '&fromPillarId=' + pillarId + '&cardId=' + cardId,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                };

                axios(config)
                    .then(function (response) {
                        console.log(JSON.stringify(response.data));
                        vm.$store.dispatch('GETPILLARS', vm.$store.getters.userToken);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            DeleteBoard() {
                var vm = this;

                var axios = require('axios');

                var config = {
                    method: 'delete',
                    url: 'http://' + this.$store.getters.ip + '/desk?token=' + this.$store.getters.userToken +
                        '&deskId=' +
                        this.$store.getters.board_id,
                    headers: {
                        'Cookie': 'JSESSIONID=84ADD4D104E66F3E4CB1F48C77561524'
                    }
                };

                axios(config)
                    .then(function (response) {
                        console.log(JSON.stringify(response.data));
                        vm.$store.dispatch('GETDESKS', vm.$store.getters.userToken);
                        router.replace("/");
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            },
            CreateCard() {
                var vm = this;

                var axios = require('axios');

                var config = {
                    method: 'post',
                    url: 'http://' + this.$store.getters.ip + '/card/create?token=' + this.$store.getters
                        .userToken +
                        '&title=' + this.card_name + '&deskId=' + this.$store.getters.board_id,
                    headers: {}
                };

                axios(config)
                    .then(function (response) {
                        console.log(JSON.stringify(response.data));
                        vm.$store.dispatch('GETPILLARS', vm.$store.getters.userToken);
                        vm.closeModalCreate();
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                this.card_name = '';
            },
            AddPeople() {
                var vm = this;

                var axios = require('axios');

                var config = {
                    method: 'post',
                    url: 'http://' + this.$store.getters.ip + '/desk/add-user?token=' + this.$store.getters
                        .userToken +
                        '&newUserLogin=' + this.loginToAdd + '&deskId=' + this.$store.getters.board_id,
                    headers: {
                        'Cookie': 'JSESSIONID=D8E2E55BE9DE6244AA2C012ED469D21D'
                    }
                };

                axios(config)
                    .then(function (response) {
                        console.log(JSON.stringify(response.data));
                        vm.closeModalAddPeople();
                        vm.message="Приглашение успешно отправлено!";
                    })
                    .catch(function (error) {
                        console.log(error);
                        vm.closeModalAddPeople();
                        vm.message="Ошбика! Повторите попытку.";
                    });

                     this.showModalInfo();
            },
            showModalDelete() {
                this.$refs.modalDeleteBoard.show = true
            },
            showModalCreate() {
                this.$refs.modalCreateCard.show = true
            },
            showModalAddPeople() {
                this.$refs.modalAddPeople.show = true
            },
            closeModal() {
                this.$refs.modalDeleteBoard.show = false
            },
            closeModalCreate() {
                this.$refs.modalCreateCard.show = false
            },
            closeModalAddPeople() {
                this.$refs.modalAddPeople.show = false
            },
            showModalInfo() {
                this.loginToAdd = "";
                this.$refs.modalInformation.show = true;
            },
            closeModalInfo() {
                this.$refs.modalInformation.show = false;
            },
            Back() {
                router.replace("/");
            }
        },

        created() {
            this.id = this.$store.getters.board_id;
            this.board_name = this.$store.getters.board_name;
            console.log("BoardView");
            console.log(this.id);
            console.log(this.board_name);
            /*vm.$watch(this.$store.getters.data, function (newVal, oldVal) {
  // переменная изменилась, сделайте что-нибудь
})*/
        }
    }
</script>

<style scoped>
    .board__column {
        display: grid;
        max-width: 100%;
        grid-column-gap: 42px;
        grid-row-gap: 42px;
        /* grid-template-columns: repeat(auto-fill,387px); */
        /* grid-template-columns:1fr 1fr 1fr;  */
        /* grid-template-columns: repeat(3, 387px); */
        grid-template-columns: repeat(auto-fit, 387px);
        flex-wrap: wrap;
        justify-content: center;
        margin-bottom: 40px;
        text-align: center;

    }



    .board__panel {
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        margin-top: 36px;
        margin-bottom: 40px;
    }



    .panel__name {
        display: flex;
        text-align: center;
        align-content: center;
        align-items: center;
        justify-content: center;
    }

    .panel__name p {
        font-size: 36px;
        font-style: normal;
        color: #545454;
        font-family: 'Inter';
        font-weight: 400;
        text-align: center;
    }

    .panel__settings button {
        margin: 5px;
    }

    /* Set a style for all buttons */
    button {
        background: #4D98D5;
        border-radius: 60px;
        font-size: 20px;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 250px;
        height: 52px;
    }

    button:hover {
        opacity: 0.8;
    }

    input[type=text] {
        width: 100%;
        padding: 12px 20px;
        margin: 20px 0;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }

    @media screen and (max-width: 400px) {
        .board__column {
            grid-template-columns: repeat(auto-fit, 300px);
        }

        button {
            width: 230px;
            font-size: 18px;
        }

        .panel__name {
            padding: 10px;
        }
    }

    @media screen and (max-width: 1300px) {
        .board__panel {
            flex-direction: column;
        }

    }
</style>