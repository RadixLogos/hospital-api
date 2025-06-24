# Hospital API
Para o desenvolvimento do projeto se fez uso das seguintes tecnologias, frameworks e
padrões:

•Java 21

•Spring Boot 3.5.0

•Maven

•Layered Architecture com separação clara de responsabilidades

•Spring Security para autenticação, auditoria e encriptação de senhas

•JWT para geração de token do usuário

•Banco de dados H2 para testes

•Spring JPA para persistência de dados

•Uso do arquivo de recurso import.sql para popular o banco de dados

•Padrão DTO para transferência de dados entre camadas

•Pageable para busca paginada de recursos

•Tratamento de exceções de forma centralizada com Controller Advice e
Exception Handler

•Respostas consistentes e coerentes com os Status HTTP esperados para o cliente
consumidor da API

Buscou-se seguir as melhores práticas de desenvolvimento de sistemas.

## Modelo de domínio
![Hospital and User domain](https://github.com/user-attachments/assets/4550345b-1848-4915-88ce-b1cacf405082)
![Patient MedicalRecord Domain](https://github.com/user-attachments/assets/ef494163-3029-43f1-9c90-0faa14e97d9c)
![Employee and Department Domain](https://github.com/user-attachments/assets/8c76e0ec-fe0a-4d4c-a615-f577c6587831)

## Plano de testes:
Para testar os endpoints da aplicação pode-se utilizar o postman com as essas requisições:
### Pacientes:
```HTTP
GET /patients?size=1&page=0

GET /patients/2

POST /hospitals/1/users/12/patients
{
    "mothersName":"Fabiana",
    "profession":"engenheiro",
    "person":{
        "name": "Marcos",
        "lastName": "Aurélio",
        "gender":0,
        "cpf":"113.210.669-96",
        "birthDate":"1997-05-31",
        "telephoneNumber":"+55 (47) 34568219",
        "cellphoneNumber":"+55 (47) 95562548",
        "email":"marcopedro@gmail.com",
        "address":{
            "city":"Itajaí",
            "state":"Santa Catarina",
            "streetName":"Capivaras",
            "neighborhood":"Italia",
            "number":"3548",
            "zipCode":"84795445",
            "complement":"n/a"
        },
        "nationality":0
    }
}

PUT /patients/2
{
    "hospitalId":1,
    "mothersName": "Maria da Silva",
    "profession": "Programador",
    "person": {
        "name": "Ricardo",
        "lastName": "Fernandes",
        "gender": "MALE",
        "cpf": "11111111111",
        "birthDate": "1980-01-01",
        "telephoneNumber": "+55 (41) 32255489",
        "cellphoneNumber": "+55 (41) 998852009",
        "email": "ricardo.fernandes@example.com",
        "address": {
            "city": "Campinas",
            "state": "São Paulo",
            "streetName": "Rua das Hortas",
            "neighborhood": "Jardim do Sol",
            "number": "201",
            "zipCode": "13000000",
            "complement": "Em frente à padaria"
        },
        "nationality": "BRAZILIAN"
        }
}

DELETE /patients/2

```




