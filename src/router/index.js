import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: function () {
      return import(/* webpackChunkName: "login" */ '../views/LoginView.vue')
    }
  },
  {
    path: '/board',
    name: 'board',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/BoardView.vue')
    }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.onError((error) => {
  console.error(error)
  const pattern =/Loading chunk/g
 //const pattern =/Загрузка блока (\d)+ не удалась/g
  const isChunkLoadFailed = error.message.match(pattern)
  const targetPath = $router.history.pending.fullPath
  if (isChunkLoadFailed && error.type === 'missing') {
   //const targetPath = $router.history.pending.fullPath
    $router.push(targetPath)
  }
})

export default router
