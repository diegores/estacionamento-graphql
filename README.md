## Projeto de Gerenciamento de Estacionamento
# Descrição do Projeto
Este projeto é um sistema de gerenciamento de estacionamento que utiliza GraphQL como uma API para facilitar operações de CRUD (Criar, Ler, Atualizar e Deletar) para veículos, além de gerenciar a entrada e saída dos mesmos.

# Benefícios de Usar GraphQL
Flexibilidade nas Consultas
O GraphQL permite que os clientes especifiquem exatamente quais dados precisam em suas requisições. Isso reduz a quantidade de dados transferidos entre o cliente e o servidor, evitando problemas de over-fetching (quando se obtém mais dados do que o necessário) e under-fetching (quando se obtém menos dados do que o necessário).

# Consultas Dinâmicas
Com GraphQL, é possível realizar consultas dinâmicas, permitindo que os desenvolvedores criem interfaces de usuário mais responsivas. Isso significa que uma única chamada pode retornar dados complexos de diferentes fontes, otimizando a comunicação com o servidor.

# Menor Número de Requisições
GraphQL permite que os desenvolvedores façam uma única requisição para obter todos os dados necessários, em vez de várias chamadas para diferentes endpoints, como é comum em APIs REST. Isso melhora a performance e a experiência do usuário.

# Como Configurar e Rodar o Projeto Localmente

# Pré-requisitos
Antes de começar, verifique se você possui os seguintes pré-requisitos:

- Java 17 ou superior
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Lombok
- GraphQL
- MapStruct

# Passos para Configuração

1. Clone o Repositório:
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio

2. Configuração do Banco de Dados H2: O projeto utiliza um banco de dados em memória H2, o que facilita a configuração e testes. Não há necessidade de configurar um banco de dados externo. As configurações do H2 estão localizadas no arquivo src/main/resources/application.properties. As principais configurações são as seguintes:

# Configurações de banco de dados H2
spring.datasource.url=jdbc:h2:mem:estacionamento;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

spring.datasource.url: URL de conexão do banco de dados H2 em memória.
spring.h2.console.enabled: Habilita o console H2 para visualização e gerenciamento dos dados.
spring.jpa.hibernate.ddl-auto: Atualiza automaticamente o esquema do banco de dados.

3. Configuração do Swagger: O Swagger é habilitado para documentação da API. As configurações estão no mesmo arquivo:
# Swagger
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true

4. Configurações do Servidor: O servidor será executado na porta 8080. Você pode alterar a porta se necessário:
# Porta do servidor
server.port=8080

5. Configurações do GraphQL: As configurações do GraphQL incluem habilitar a interface GraphiQL para facilitar as consultas:
# Configurações do GraphQL
spring.graphql.path=/graphql
spring.graphql.graphiql.enabled=true
spring.graphql.graphiql.path=/graphiql
spring.main.allow-circular-references=true

6. Executar o Projeto:
   Para iniciar o servidor, use o seguinte comando:
   mvn spring-boot:run
7. Acessar o Console do H2:
   Após o servidor estar em execução, você pode acessar o console do H2 em:
   http://localhost:8080/h2-console

Utilize as credenciais:
    JDBC URL: jdbc:h2:mem:estacionamento
    Username: sa
    Password: password

8. Acessar o GraphQL:
   Após o servidor estar em execução, você pode acessar a interface GraphQL em:
   http://localhost:8080/graphql
9. Acessar a interface GraphiQL:
   Para uma interface interativa de consulta GraphQL, acesse:
   http://localhost:8080/graphiql

# Exemplos de Consultas
Aqui estão alguns exemplos de consultas GraphQL que você pode executar:

Obter todos os veículos:
query {
vehicles {
id
marca
modelo
placa
}
}

Adicionar um novo veículo:

mutation {
addVehicle(marca: "Fusca", modelo: "Volkswagen", placa: "ABC-1234") {
id
marca
modelo
placa
}
}

## DTOs
- **CompanyInputDTO**: Usado para criar ou atualizar uma empresa.
- **CompanyOutputDTO**: Usado para retornar as informações de uma empresa.

## Execução
1. Clone o repositório.
2. Abra o projeto na IDE de sua escolha.
3. Execute a classe `main` da aplicação.
4. Acesse a console do H2 em [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
5. Acesse a GraphQL Playground em [http://localhost:8080/graphql](http://localhost:8080/graphql).

## Endpoints
- `POST /api/companies`: Cadastrar uma nova empresa.
- `GET /api/companies`: Listar todas as empresas.
- `GET /api/companies/{id}`: Obter uma empresa por ID.
- `DELETE /api/companies/{id}`: Deletar uma empresa.