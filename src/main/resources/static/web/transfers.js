const app = Vue.createApp({


    data() {
        return {
            urlApi: "/api/clients/current",
            cliente: [],
            origen: "",
            destino: [],
            destino2: "",
            cantidad: "",
            cuentas: [],
            descripcion:"",
            propiedadDeLaCuenta:"",
            destinationAccount: [],
        }
    },
    created() {
        this.loadData(this.urlApi);
    },

    methods: {

        loadData(api) {

            axios.get(api)
                .then(response => {
                    this.cliente = response.data

                    this.cuentas = this.cliente.accounts
                    console.log(this.cuentas);
            })
},
transferir(){
    axios.post(`/api/transactions/create?amount=${this.cantidad}&description=${this.descripcion}&originAccountNumber=${this.origen}&destinationAccountNumber=${this.destino2}`)
    .then(() => { Swal.fire(
        'Great!',
        'Your transaction has succesfull',
    
    ),setTimeout(()=> { 
        window.location.pathname=`/web/accounts.html`
    },"2000")})
},

alerta(){
    Swal.fire({
        title: 'Are you sure you want to transfer?',
        text: "Check that the data are correct",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, transfer!'
      }).then((result) => {
        if (result.isConfirmed) {

            this.transferir()

        }
      })
},


cerrarSesion(){
    axios.post("/api/logout").then(() =>{
      window.location.href="/web/index.html"
    })
  },
    },computed: {

        destinationAccounts(){

            this.destinationAccount = this.cuentas.filter(cuentas => cuentas.number != this.origen)

        }
    },
}).mount('#app')