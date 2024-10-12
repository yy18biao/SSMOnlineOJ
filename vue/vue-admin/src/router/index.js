import { createRouter, createWebHistory } from 'vue-router'
import { getToken } from '@/utils/cookie'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/admin/login",
      name: "login",
      component: () => import("@/views/Login.vue"),
    },
    {
      path: "/",
      redirect: '/admin/layout',
    },
    {
      path: "/admin/layout",
      name: "left",
      component: () => import("@/views/Layout.vue"),
      children: [
        {
          path: "/admin/user",
          name: "user",
          component: () => import("@/views/User.vue"),
        },
        {
          path: '/admin/exam/updateExam',
          name: 'updateExam',
          component: () => import('@/views/UpdateExam.vue')
        },
        {
          path: "/admin/question",
          name: "question",
          component: () => import("@/views/Question.vue"),
        },
        {
          path: "/admin/exam",
          name: "exam",
          component: () => import("@/views/Exam.vue"),
        },
      ]
    },
  ]
})

// 路由前置守卫
router.beforeEach((to, from, next) => {
  if (getToken()) {
    // 已经登录并且token没有过期则不需要到登录界面
    if (to.path === '/admin/login') {
      next({ path: '/admin/layout/' })
    } else {
      next()
    }
  } else {
    // 没有登录或者token过期则跳转到登录页
    if (to.path !== '/admin/login') {
      next({ path: '/admin/login' })
    } else {
      next()
    }
  }
})

export default router
