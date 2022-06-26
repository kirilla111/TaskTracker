<template>
    <div v-if="show" class="modal-shadow" @click.self="closeModal">
        <div class="modal">
            <div class="modal-close" @click="closeModal">&#10006;</div>
            <!-- <slot name="title">
                <h3 class="modal-title">Заголовок</h3>
            </slot> -->
            <slot name="body">
                <div class="modal-content" >{{this.message}}</div>
            </slot>
        </div>
    </div>
</template>
<script>
    export default {
        name: "ModalWindowInformation",
        props:['message'],
        data: function () {
                return {
                    show: false
                }
            },
        methods: {
            closeModal: function () {
                this.show = false
            }
        },
        created() {
            console.log(this.message);
        },
    }
</script>

<style>
    .modal-shadow {
        position: absolute;
        top: 0;
        left: 0;
        min-height: 100%;
        width: 100%;
        background: rgba(0, 0, 0, 0.39);
    }

    .modal {
        background: #fff;
        border-radius: 67px;
        padding: 70px;
        min-width: 420px;
        max-width: 480px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    .modal-title {
        font-family: 'Inter';
        font-weight: 400;
        color: #383838;
        font-size: 32px;
        margin-bottom: 26px;
         color: #383838;
    }

    .modal-content {
        margin-bottom: 15px;
        margin-top: 15px;
    }


    input {
        border: none;
        background: #EEEEEE;
        box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
        border-radius: 60px;
    }

    .modal-footer__button {
        background-color: #0971c7;
        color: rgb(0, 0, 0);
        border: none;
        text-align: center;
        padding: 8px;
        font-size: 17px;
        font-weight: 500;
        border-radius: 8px;
        min-width: 150px;

    }

    .modal-close {
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        top: 27px;
        right: 27px;
        width: 30px;
        height: 30px;
        cursor: pointer;
    }

    .modal-enter-active,
    .modal-leave-active {
        transition: opacity 2s
    }

    .modal-enter,
    .modal-leave-to {
        opacity: 0
    }

      @media screen and (max-width: 400px) {
    .modal {
      min-width:300px;
      max-width: 300px;
      padding: 35px;
    }
     .modal-title {
        margin-bottom: 5px;
        font-size: 24px;
    }

    .modal-close {

        top: 15px;
        right:15px;

    }
    
    }
</style>