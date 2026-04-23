import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes:[
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView
        },
        {
            path: '/register',
            name: 'register',
            component: RegisterView
        },
        {
            path: '/room/:id',
            name: 'participant-room',
            component: () => import('../views/ParticipantRoomView.vue')
        },
        {
            path: '/create',
            name: 'host-create',
            component: () => import('../views/HostCreateView.vue')
        }
    ]
})

export default router