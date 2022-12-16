const { createApp } = Vue

  createApp({
    data() {
      return {
        message: 'Hello Vue!',
        clientes: [],
        cuentas: [],
        nombre: "",
        apellido: "",
        email: "",
        datajson: [],
        melbaLoans: [],
      }
    },
    created(){
      this.loadData("/api/clients/current")
    },
    methods:{
      loadData(url){
        axios.get(url).then(response =>{
          this.clientes=response.data
          this.cuentas=this.clientes.accounts
          console.log(this.clientes);
          this.melbaLoans=this.clientes.clientLoans
          console.log(this.melbaLoans);
        })
      },
      addClient(){
        let clienteNuevo = {
          firstName : this.nombre,
          lastName : this.apellido,
          email : this.email,
       }
       console.log(clienteNuevo);
       this.postClient(clienteNuevo)
       
      },
      postClient(clienteNuevo){
        axios.post("/clients", clienteNuevo).then(() =>{
          this.loadData("/clients")
          
        })

      },

      borrarCliente(clients){ 
        axios.delete(clients._links.self.href)
        .then(()=> this.loadData("/clients"))
      
        },
      
      cerrarSesion(){
        axios.post("/api/logout").then(() =>{
          window.location.href="/web/index.html"
        })
      },

      createAccount(){
        axios.post("/api/clients/current/accounts").then(() => {
          this.loadData("/api/clients/current") 
        })
      }
      
        
    },
  
  }).mount('#app')
