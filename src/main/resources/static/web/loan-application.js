const app = Vue.createApp({
    data() {
        return {
            apiLoans: "/api/loans",
            apiAccounts:"/api/clients/current/",
            client:[],
            loans: [],
            accounts:[],
            selectedLoan: [],
            payments:[],
            loanName: "",
            contenedor: [],
            request:{

                id: "",
                amount: "",
                payment:"",
                accountDestinit: ""
            }
                         
          

        }


    },created() {
        
        
       this.loanData(this.apiLoans);
       this.accountData(this.apiAccounts);
       
        
    },methods: {
       
        loanData(api){
            axios.get(api)
            .then(response =>{
                
                this.loans = response.data
                console.log(this.selectedLoan);

            }).catch(function (error) {

                console.error(error);


            });


        },accountData(api2){
            axios.get(api2)
            .then(response =>{
               
               this.client = response.data
               this.accounts = this.client.accounts
              


            }).catch(function (error) {

                console.error(error);


            });


        },transfer(){
            
        },alerta(){
            Swal.fire({
                title: 'Are you sure about your loan request?',
                text: "Controls the amount and installments",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, im sure!'
              }).then((result) => {
                if (result.isConfirmed) {

                    this.requestLoan()

                   

                }
              })
        },
        miles(numero){

            return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'USD' }).format(numero)

          },requestLoan(){
            axios.post("/api/loans", this.request).then(()=>{

                Swal.fire(
                    'Great!',
                    'Your loan has aproved'
                    ), setTimeout(()=>{window.location.assign("./accounts.html")},"2000")
            }).catch(error => {Swal.fire(error.response.data, '', 'error'); console.log(error);})
          },
          cerrarSesion(){
            axios.post("/api/logout").then(() =>{
              window.location.href="/web/index.html"
            })
          },
          


    },computed: {
        loanSelector(){
            if (this.loanName){

            this.selectedLoan = this.loans.find(loan => loan.name == this.loanName )
            this.request.id = this.selectedLoan.id
            this.payments = []
            this.selectedLoan.payments.forEach(payment => { this.payments.push(payment)
                
            })
            }


            console.log(this.selectedLoan);
          },

         CalcularPagos(){
            return (this.request.amount/this.request.payment).toFixed(2)
            
          },
    },



}).mount('#app')