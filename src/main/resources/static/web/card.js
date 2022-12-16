const app = Vue.createApp({


    data() {
        return {
            api: "/api/clients/current",
            cliente1: [],
            cartas: [],
            tipoDeCarta: "",
            colorDeCarta: "",
        }
    },
    created() {
        this.loadData(this.api);
    },

    methods: {

        loadData(api) {

            axios.get(api).then(response => {
                this.cliente1 = response.data
                this.cartas = this.cliente1.cards

            }).catch(function (error) {

                console.error(error);

            })
},
cerrarSesion(){
    axios.post("/api/logout").then(() =>{
      window.location.href="/web/index.html"
    })
  },
  createCartas(){
    axios.post("/api/clients/current/cards", `type=${this.tipoDeCarta}&color=${this.colorDeCarta}`).then(() => {
      window.location.href="/web/cards.html" 
    })
    }
    },
    
}).mount('#app')
