const app = Vue.createApp({


    data() {
        return {
            email: "",
            password: "",
            firstName: "",
            lastName: "",
            emailIngresar: "",
            passwordIngresar: "",
        }
    },
    created() {
        this.loadData("/api/clients");
    },

    methods: {
        
        loadData(api) {         
},
ingresar(){
    axios.post('/api/login',`email=${this.emailIngresar}&password=${this.passwordIngresar}`).then(()=>window.location.href="/web/accounts.html")
  },
  registrar(){
    axios.post('/api/clients', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`)
    .then(()=> {
        this.emailIngresar = this.email
        this.passwordIngresar = this. password 
        this.ingresar()
    })
  }
    },

}).mount('#app')