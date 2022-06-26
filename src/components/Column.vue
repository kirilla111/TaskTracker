<template>
  <div class="column">

    <div class="column__name">
      {{name}}
    </div>

    <div class="column__card" v-for="item in pillar.cards" :key="item">
   
        <Card :card_id="item.id" 
              :name="item.title"
              :owner="item.owner.login"
              :shortDescription="item.shortDescription"
              :description="item.description"
              :pillar_id="pillar.id"
              draggable="true"
              @dragstart="startDrag($event, item)">
        </Card>
    </div>
  </div>
</template>

<script>
  import Card from '@/components/Card.vue'

  export default {
    name: 'Column',
    props: {
      name: String,
      pillar: Object
    },
    components: {
      Card,
    },
    data() {
      //это персональные данные
      return {
        card_name: ''
      };
    },
    methods: {
      startDrag(event, item){
        console.log("card: "+item);
        event.dataTransfer.dropEffect = 'move'
        event.dataTransfer.effectAllowed = 'move'
        event.dataTransfer.setData('cardId', item.id)
        event.dataTransfer.setData('card', item)
        event.dataTransfer.setData('pillarId', this.pillar.id)
        console.log("star event:");
        console.log(event);
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .column {
    background: #FFFFFF;
    border: 2px solid #FFFFFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), inset 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 64px;
    width: 382px;
    min-height: 200px;
    padding: 20px;
  }

    @media screen and (max-width: 400px) {
        .column{
           width: 300px;
           text-align: center;
        }
    }

  .column__name {
    padding: 34px;
    font-weight: bold;
    font-size: 24px;
line-height: 29px;
  }

  .column__card {
    /*display: flex;
    flex-direction: column;
    text-align: center;
    align-items: center;
    margin: 10px;*/
    display: grid;
    margin: 10px;
    max-width: 100%;
    grid-column-gap: 10px;
    grid-row-gap: 20px;
    grid-template-columns: repeat(auto-fill, 1fr);
    flex-wrap: wrap;
    justify-content: space-around;
    align-items: center;
  }

   @media screen and (max-width: 400px) {
        .column__card{
            grid-template-columns: repeat(auto-fill,250px);
        }
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


  button {
    background-color: #5199dd;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 20%;
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
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
  }

  /* Extra styles for the cancel button */
  .cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
  }

  /* Center the image and position the close button */
  .imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
    position: relative;
  }

  img.avatar {
    width: 40%;
    border-radius: 50%;
  }

  .container {
    padding: 16px;
  }

  span.psw {
    float: right;
    padding-top: 16px;
  }

  /* The Modal (background) */
  .modal {
    display: none;
    /* Hidden by default */
    position: fixed;
    /* Stay in place */
    z-index: 1;
    /* Sit on top */
    left: 0;
    top: 0;
    width: 100%;
    /* Full width */
    height: 100%;
    /* Full height */
    overflow: auto;
    /* Enable scroll if needed */
    background-color: rgb(0, 0, 0);
    /* Fallback color */
    background-color: rgba(0, 0, 0, 0.4);
    /* Black w/ opacity */
    padding-top: 60px;
  }

  /* Modal Content/Box */
  .modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto;
    /* 5% from the top, 15% from the bottom and centered */
    border: 1px solid #888;
    width: 80%;
    /* Could be more or less, depending on screen size */
  }

  /* The Close Button (x) */
  .close {
    position: absolute;
    right: 25px;
    top: 0;
    color: #000;
    font-size: 35px;
    font-weight: bold;
  }

  .close:hover,
  .close:focus {
    color: red;
    cursor: pointer;
  }

  /* Add Zoom Animation */
  .animate {
    -webkit-animation: animatezoom 0.6s;
    animation: animatezoom 0.6s
  }

  @-webkit-keyframes animatezoom {
    from {
      -webkit-transform: scale(0)
    }

    to {
      -webkit-transform: scale(1)
    }
  }

  @keyframes animatezoom {
    from {
      transform: scale(0)
    }

    to {
      transform: scale(1)
    }
  }

  /* Change styles for span and cancel button on extra small screens */
  @media screen and (max-width: 300px) {
    span.psw {
      display: block;
      float: none;
    }

    .cancelbtn {
      width: 100%;
    }
  }
</style>