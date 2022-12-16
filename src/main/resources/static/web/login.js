const app = Vue.createApp({


    data() {
        return {
            email: "",
            password: "",
        }
    },
    created() {
        this.loadData("/api/clients");
    },

    methods: {
        
        loadData(api) {         
},
ingresar(){
    axios.post('/api/login',`email=${this.email}&password=${this.password}`).then(()=>window.location.href="/web/accounts.html")
  }
    },

}).mount('#app')
