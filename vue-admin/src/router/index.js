import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/admin/login",
      name: "login",
      component: () => import("@/views/Login.vue"),
    },
    {
      path: "/admin/system",
      name: "system",
      component: () => import("@/views/System.vue"),
    }
  ]
})

export default router
