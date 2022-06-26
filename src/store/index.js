import { createStore } from 'vuex'

export default createStore({
  state: {
    userToken: '',
    data: [],
    pillars: [],
    board_id: -1,
    board_name: '',
    ip: 'localhost:8888'
  },
  getters: {
    data: state => {return state.data;},
    pillars: state => {return state.pillars;},
    board_id: state => {return state.board_id;},
    board_name: state => {return state.board_name;},
    userToken: state => {return state.userToken;},
    ip: state => {return state.ip;},
  },
  mutations: {
    addBoard(state, board_id, board_name){
      
    },
    pushCard(state, dto){
      // var array = new Array();
      // var card = state.pillars[dto.]
      console.log("pillarid: " + dto.pillarId);
      console.log("stored cardId: "+dto.cardId);
      console.log(dto.card.id);
      console.log("frompillarid" + dto.fromPillarId);
      state.pillars[pillar_id].cards.push(card);
      // console.log(state.pillars);
      console.log("data");
      console.log(state.pillars[pillar_id]);
    },
    addData(state, data){
      state.data = data;
    },
    addPillars(state, pillars){
       state.pillars = pillars;
     },
    saveToken(state, token){
      state.userToken = token;
    },
    ChangeBoardId(state,board_id){
      state.board_id=board_id;
    },
    ChangeBoardName(state,name){
      state.board_name=name;
    },
  },
  actions: {
   GETDESKS(state, token){
      var axios = require('axios');

      //console.log("GETDESKS() = "+'http://192.168.31.215:8888/desk/get-all-desks?token='+token);

      var config = {
        method: 'get',
        url: 'http://' + state.getters.ip + '/desk/get-all-desks?token='+token,
        headers: { }
      };

      axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data));
        state.commit("addData", response.data);
        
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    GETPILLARS(state, token){
      var axios = require('axios');

      //console.log("GETPILLARS() = "+'http://192.168.31.215:8888/desk/get-pillars?token='+token+'&deskId='+state.getters.board_id);

      var config = {
        method: 'get',
        url: 'http://' +state.getters.ip + '/desk/get-pillars?token='+token+'&deskId='+state.getters.board_id,
        headers: {  },
      };

      /* `withCredentials` указывает, запрашивает ли межсайтовый контроль доступа или нет */
      axios.defaults.withCredentials = false;

      axios(config)
        .then(function (response) {
          console.log(JSON.stringify(response.data));
          state.commit("addPillars",response.data);
 
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  },
  modules: {
    
  }
})


