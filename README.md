# Teste Java Back-End

# :clipboard: Descrição do projeto

Desenvolver um sistema de pagamentos que permite a interação entre Empresas e Clientes. O sistema deverá validar CPFs para Clientes e CNPJs para Empresas, gerenciar saldos das Empresas, aplicar taxas de sistema nas transações, e enviar notificações aos Clientes e Empresas.



## Requisições

- Cadastra usuários, (**POST**) localhost:8080/users
- Lista de usuários cadastrados, (**GET**) localhost:8080/users


  Formato JSON Sugerido :

   
`
{
	"name": "Guilherme",
	"document": "53532312234",
	"saldo": "1000",
	"email": "tempmail@email.com",
	"userType": "CLIENTE"
}
`
- Realizar transações, (**POST**) localhost:8080/transactions


  Formato JSON Sugerido :

   
`
{
	"senderId": 1,
	"receiverId": 2,
	"value": 100,
	"taxa": 20
}
`
