# Compasso Backend API - Interview Test

## Geral
Projeto desenvolvido com Spring Boot 2.1.8. Implementação em idioma Inglês.

## Documentation
Para documentação utilizei o Swagger, disponível através do path `/compasso/api/swagger-ui.html``

## Authentication
Neste projeto utilizou-se JWT como token de autenticação. O token poderá ser obtido através do endpoint POST ``/compasso/api/authentication`` com o seguinte conteúdo:
```json
{
    "username": "compasso",
    "password": "compasso"
}
```
De posse do token deve-se adicioná-lo no header das requisições:
````
Key: Authorization
Value: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2OTMyNDU0NCwidXNlcm5hbWUiOiJhZG1pbiJ9.zJk-fuYjLRlbA6g7YJXI35hqO81idHU7szzIxUqVp_04etxQG2jcp18S20MeKzAOjv-hc_JECUBWccMlJAJPPQ

````

## Arquitetura
Utilizei uma arquitetura básica que inclui uma divisão em camadas:
- **domain**: artefatos relacionados ao negócio. Entidades, fábricas, comuns.
- **repository**: artefatos relacionadas a persistência e banco de dados.
- **processor**: artefatos intermediárias para processos lógicos do negócio, são chamadas pela camada de serviço.
- **rest**: artefatos relacionados ao contexto web RESTful
- **security**: arteaftos de arquitetura de segurança
- **service**: interfaces e implementações de negócio que podem delegar para camada de processadores.
- **util**: utilitários comuns a todo o projeto, exemplo: ExpectThat, EnsuresThat etc.
- **pattern**: artefatos abstratos que estabelecem padrões.

## Banco de Dados
Utilizei banco de dados *embeddable* **Derby**. Defini um **@Component** chamado ``DefaultDataSeeder`` que é responsável por inserir alguns dados padrões no **Derby** no processo de inicialização do Spring através da annotation **@EventListener** sob o tipo ``ContextRefreshedEvent``. Os objetos padrões criados: ``FederativeUnit``, ``City``, ``Client``

# Endpoint's + Exemplos
##### Incluir nova Cidade: 
```
PUT /compasso/api/cities
{
  "newCity": {
    "federativeUnit": {
      "id": 1
    },
    "name": "Maravilha"
  }
}
```

##### Incluir novo Cliente:
```
PUT /compasso/api/clients
{
  "birthDate": "1920-07-20",
  "city": {
  	"id": 1,
    "federativeUnit": {
      "id": 1
    }
  },
  "fullName": "GALADRIEL",
  "gender": "FEMALE"
}
```
##### Cidades por Nome + Paginação:
```
POST /compasso/api/cities/by/name/paginated?name=Chape
{
    "pageSize": 100,
    "pageNumber": 0
}
```
#####  Cidades por sigla do Estado + Paginação:
```
POST /compasso/api/cities/by/federativeUnit/initials/paginated?initialsFederativeUnit=sc
{
    "pageSize": 100,
    "pageNumber": 0
}
```
##### Consultar Cliente pelo ID: 
```
GET /compasso/api/clients/1
```

##### Consultar Cliente pelo Nome + Paginação:
```
POST /compasso/api/clients/by/name/paginated?name=Gan
```

##### Remover um Cliente:
```
DELETE /compasso/api/clients/4
```
##### Alterar Nome do Cliente: 
```
POST /compasso/api/clients/5/update/name

Gandalf, Cinzento
```




