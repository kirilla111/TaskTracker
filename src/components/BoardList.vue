<template>
  <div class="boardList">
    <BoardItem 
        :name = "item.title"
        :id = "item.id"
        v-for="(item, index) in this.$store.getters.data"
        :key="index">
    </BoardItem>
  </div>
</template>

<script>
import BoardItem from './BoardItem.vue';
export default {
    name: "BoardList",
    components: { BoardItem },
 data() {
    //это персональные данные
    return {
      boardList: []  
    };
  },
  created() {
      this.boardList = this.$store.getters.data;
      console.log(this.$store.getters.data);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.boardList{
  display: grid;
  max-width: 100%;
  grid-column-gap: 20px;
  grid-row-gap: 20px;
  grid-template-columns: repeat(auto-fill, 287px);
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;
  text-align: center;
  margin: 20px;
  
    }
    @media screen and (max-width: 300px) {

.boardList { 
  justify-content: center;
  align-items: center;
  justify-items: center;
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
</style>