<template>
    <div v-if="show" class="modal-shadow" @click.self="closeModal">
        <div class="modal">
            <div class="modal-close" @click="closeModal">
                <svg width="36" height="36" viewBox="0 0 36 36" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M24.8236 18L35.0581 7.76557C36.314 6.50966 36.314 4.47341 35.0581 3.21648L32.7835 0.941932C31.5276 -0.313977 29.4914 -0.313977 28.2344 0.941932L18 11.1764L7.76557 0.941932C6.50966 -0.313977 4.47341 -0.313977 3.21648 0.941932L0.941932 3.21648C-0.313977 4.47239 -0.313977 6.50864 0.941932 7.76557L11.1764 18L0.941932 28.2344C-0.313977 29.4903 -0.313977 31.5266 0.941932 32.7835L3.21648 35.0581C4.47239 36.314 6.50966 36.314 7.76557 35.0581L18 24.8236L28.2344 35.0581C29.4903 36.314 31.5276 36.314 32.7835 35.0581L35.0581 32.7835C36.314 31.5276 36.314 29.4914 35.0581 28.2344L24.8236 18Z"
                        fill="black" />
                </svg>
            </div>
            <slot name="title">
                <h3 class="modal-title">Заголовок</h3>
            </slot>
            <slot name="body">
                <div class="modal-content">Дефолтный контент модального окна </div>
            </slot>
            <slot name="footer">
                <div class="modal-content">
                    <button class="modal-footer__button"></button>
                </div>
            </slot>
        </div>
    </div>
</template>
<script>
    export default {
        name: "ModalWindow",
        data: function () {
            return {
                show: false
            }
        },
        methods: {
            closeModal: function () {
                this.show = false
            }
        }
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
    
    }
</style>