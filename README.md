# Gerenciamento de Clientes e Endereços

Este é um projeto Java baseado no framework **Spring Boot** que permite o gerenciamento de clientes e seus endereços associados. A aplicação utiliza JPA para persistência de dados e integração com o serviço externo **ViaCEP** para busca de informações de endereço com base no CEP.

---

## **Funcionalidades**

1. **Gerenciamento de Clientes**
   - Criar, atualizar, buscar e excluir clientes.

2. **Gerenciamento de Endereços**
   - Associar endereços a clientes.
   - Buscar informações detalhadas de endereços utilizando o CEP.

3. **Integração com ViaCEP**
   - Consulta automática de dados de endereço por meio da API ViaCEP.

---

## **Tecnologias Utilizadas**

- **Java 17**
- **Spring Boot** 2.7.x
- **Spring Data JPA**
- **Spring Cloud OpenFeign**
- **Postgres Database**
- **H2 Database** (para testes)
- **Lombok** (para redução de boilerplate code)
- **Maven**
- **Docker** e **Docker Compose**

---

## **Configuração do Projeto**

### **1. Requisitos Pré-execução**
- Tenha o **Docker** e o **Docker Compose** instalados na sua máquina.

### **2. Clonar o Repositório**
```bash
$ git clone https://github.com/willjrcom/springProjectMeta.git
$ cd springProjectMeta
```

### **3. Iniciar os Contêineres com Docker Compose**
- No diretório raiz do projeto, execute o comando:

```bash
docker-compose up -d
```

Esse comando iniciará os contêineres necessários, como o banco de dados, para que o projeto funcione corretamente.

### **4. Configuração do Banco de Dados**

O projeto utiliza o banco de dados em memória **H2** por padrão para testes. Quando utilizando o banco via Docker, as credenciais e configurações estão definidas no arquivo `application.properties`.

### **5. Execução da Aplicação**

```bash
$ mvn spring-boot:run
```

### **6. Acessar a Documentação da API**

Após iniciar a aplicação, você pode acessar a documentação Swagger:

- URL: `http://localhost:8080/swagger-ui.html`

---

## **Endpoints Principais**

### **Clientes**

| Método | Endpoint                   | Descrição                           |
|--------|----------------------------|-------------------------------------|
| POST   | `/clients`                 | Cria um novo cliente.               |
| PUT    | `/clients/{id}`            | Atualiza os dados de um cliente.    |
| GET    | `/clients/{id}`            | Busca um cliente pelo ID.           |
| DELETE | `/clients/{id}`            | Exclui um cliente pelo ID.          |
| GET    | `/clients?page=0&size=10`  | Lista todos os clientes.            |
| POST   | `/clients/{id}/address/{addressId}` | Adiciona um endereço ao cliente.      |
| DELETE | `/clients/{id}/address` | Remove o endereço do cliente      |

### **Endereços**

| Método | Endpoint         | Descrição                                     |
|--------|------------------|---------------------------------------------|
| POST   | `/addresses`       | Cria um novo endereço.       |
| PUT    | `/addresses/{id}`  | Atualiza os dados de um endereço.    |
| GET    | `/addresses/{id}`  | Busca um endereço detalhado utilizando o ID. |
| GET    | `/addresses/zip-code/{zipCode}` | Busca um endereço detalhado utilizando o CEP. |
| GET    | `/addresses/search-zip-code/{zipCode}` | Busca um endereço direto na integração ViaCEP. |
| DELETE | `/addresses/{id}`            | Exclui um endereço pelo ID.          |
| GET    | `/addresses?page=0&size=10`  | Lista todos os endereços.            |

---

## **Arquitetura do Projeto**

O projeto segue o padrão **Hexagonal Architecture (Ports and Adapters)**:

### **Camadas Principais**

1. **Domain**
   - Contém as entidades principais como `Client` e `Address`.

2. **Application**
   - Inclui as regras de negócio e casos de uso.

3. **Infrastructure**
   - Responsável pela comunicação com sistemas externos e persistência.
   - Exemplo: `ViaCepClient` (integração com o ViaCEP).

4. **Shared**
   - Contém componentes compartilhados como tratamento de exceção.

---