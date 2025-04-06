import { createRouter, createWebHistory } from 'vue-router'

export const router = createRouter({
    history: createWebHistory(),
    routes:[

        {
            path: '/',
            name: 'Login',
            component: () => import('@/views/LoginView.vue')
        },
        {
            path:'/user',
            name:'User',
            component: () => import('@/views/UserView.vue')
        },
        {
            path:'/register',
            name:'Register',
            component: () => import('@/views/RegisterView.vue')
        }
    ]
})

export default router