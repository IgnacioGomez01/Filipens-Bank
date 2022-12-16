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

      }
    },
    created(){
      this.loadData("/api/clients")
    },
    methods:{
      loadData(url){
        axios.get(url).then(response =>{
          this.clientes=response.data
          console.log(this.clientes);
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
      
    
    
        
    },
  
  }).mount('#app')
