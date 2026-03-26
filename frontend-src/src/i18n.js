import { reactive, computed } from 'vue'

const messages = {
    de: {
        nav: { login: 'Login', register: 'Registrieren' },
        home: {
            badge: 'Systeme online',
            title: 'Einfache Umfragen für moderne Teams.',
            sub: 'Interaktion in Echtzeit. Ohne Installation, ohne Stress.',
            input: 'Room Code',
            btnJoin: 'Beitreten',
            or: 'oder',
            btnHost: 'Eigene Umfrage starten'
        },
        login: {
            title: 'Willkommen zurück',
            sub: 'Logge dich ein, um deine Umfragen zu verwalten.',
            email: 'E-Mail Adresse',
            pass: 'Passwort',
            btn: 'Einloggen',
            footer: 'Noch kein Konto? Registrieren'
        },
        register: {
            title: 'Account erstellen',
            sub: 'Werde Host und starte deine eigenen Live-Umfragen.',
            user: 'Benutzername',
            email: 'E-Mail Adresse',
            pass: 'Passwort',
            passConfirm: 'Passwort bestätigen',
            btn: 'Account erstellen',
            footer: 'Bereits ein Konto? Einloggen'
        }
    },
    en: {
        nav: { login: 'Login', register: 'Register' },
        home: {
            badge: 'Systems online',
            title: 'Simple polls for modern teams.',
            sub: 'Real-time interaction. No install, no stress.',
            input: 'Room Code',
            btnJoin: 'Join',
            or: 'or',
            btnHost: 'Start own poll'
        },
        login: {
            title: 'Welcome back',
            sub: 'Login to manage your polls.',
            email: 'Email Address',
            pass: 'Password',
            btn: 'Login',
            footer: 'No account yet? Register'
        },
        register: {
            title: 'Create account',
            sub: 'Become a host and start your own live polls.',
            user: 'Username',
            email: 'Email Address',
            pass: 'Password',
            passConfirm: 'Confirm Password',
            btn: 'Create account',
            footer: 'Already have an account? Login'
        }
    }
}

export const i18nState = reactive({
    lang: 'de'
})

export const t = computed(() => messages[i18nState.lang])

export const toggleLang = () => {
    i18nState.lang = i18nState.lang === 'de' ? 'en' : 'de'
}