const { createApp } = Vue

  createApp({
    data() {
      return {
        message: 'Hello Vue!',
        clientes: [],
        nombre: "",
        apellido: "",
        email: "",
        datajson: [],
        cuentaGuardada: [],
        cuentas:[],
        cuenta:[],
        id: new URLSearchParams(location.search).get("id"), 
        transacciones: [],
        parametro: `/api/account/`,
      }
    },
    created(){
      this.loadData(this.parametro + this.id)
    },
    methods:{
      loadData(url){
        axios.get(url).then(response =>{
          this.cuentas=response.data
          this.transacciones=this.cuentas.transactions
          console.log(this.cuentas);
        })
      },
      guardarCuenta(cuenta){
        this.cuentaGuardada = cuenta
      }
    }
  }).mount('#app')
