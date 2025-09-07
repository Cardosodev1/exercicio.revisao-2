# Exercício Revisão - Spring Boot API

Esta aplicação fornece uma API para gerenciar **clientes, fornecedores, brinquedos, pedidos e pagamentos**, sendo responsável por realizar o CRUD e persistir as informações no banco de dados Oracle.

## Tecnologias

- Java 17  
- Spring Boot (Web, Data JPA, Validation, HATEOAS)  
- Oracle JDBC  

**IDE utilizada:** IntelliJ IDEA

## Requisitos

- Java 17 (JDK)  
- Maven  
- Banco de dados Oracle

## Configuração de ambiente

A API está disponível publicamente em: [https://java-project-3anr.onrender.com](https://java-project-3anr.onrender.com)  

Também é possível utilizar a API localmente:

1. Clonar o repositório:
```bash
git clone https://github.com/hachahine/java_project.git
```

2. Criar e preencher um arquivo `.env` com os dados de conexão:
```env
DB_ORACLE=seu_usuario
DB_PASSWORD=sua_senha
```

3. Inicializar o projeto:
```bash
mvn spring-boot:run
```

## Endpoints

### Customers (Clientes)

- `POST /customers` – cria
- `GET /customers` – lista paginada
- `GET /customers/{id}` – detalhe
- `PUT /customers/{id}` – atualiza
- `DELETE /customers/{id}` – remove

**Request (POST/PUT):**
```json
{
  "name": "Maria Silva",
  "email": "maria@email.com",
  "address": "Rua A, 123",
  "phone": "11999999999",
  "birthDate": "1995-07-20T00:00:00",
  "cpf": "12345678901"
}
```

**Response:**

<img width="1254" height="507" alt="image" src="https://github.com/user-attachments/assets/975b6640-ed7e-4634-a2ae-ccac0d225738" />


### Suppliers (Fornecedores)

- `POST /suppliers` – cria
- `GET /suppliers` – lista paginada
- `GET /suppliers/{id}` – detalhe
- `PUT /suppliers/{id}` – atualiza
- `DELETE /suppliers/{id}` – remove

**Request (POST/PUT):**
```json
{
  "name": "Brinquedos XYZ",
  "email": "contato@xyz.com",
  "address": "Av. Central, 500",
  "cnpj": "11222333000199",
  "phone": "11988887777"
}
```

**Response:**

<img width="1281" height="481" alt="image" src="https://github.com/user-attachments/assets/029d3f37-3db2-455d-ac6d-818790f42b73" />


### Toys (Brinquedos)

- `POST /toys` – cria
- `GET /toys` – lista paginada
- `GET /toys/{id}` – detalhe
- `PUT /toys/{id}` – atualiza
- `DELETE /toys/{id}` – remove

**Enums aceitos:**
- `toyType`: `SPORTS`
- `toyClassification`: `BABY`, `TODDLER`, `PRESCHOOL`, `SCHOOL_AGE`, `ALL_AGES`
- `toySize`: `SMALL`, `MEDIUM`, `LARGE`, `EXTRA_LARGE`

**Request (POST/PUT):**
```json
{
  "name": "Bola de Futebol",
  "toyType": "SPORTS",
  "toyClassification": "SCHOOL_AGE",
  "toySize": "MEDIUM",
  "price": 79.90
}
```

**Response:**

<img width="1280" height="490" alt="image" src="https://github.com/user-attachments/assets/6586eb11-4489-4242-a57f-c4739a3e9876" />


### Orders (Pedidos)

- `POST /orders` – cria
- `GET /orders` – lista paginada
- `GET /orders/{id}` – detalhe
- `PUT /orders/{id}` – atualiza
- `DELETE /orders/{id}` – remove

**Enums aceitos:**
- `orderStatus`: `PENDING`, `PROCESSING`, `SENT`, `DELIVERED`

**Request (POST/PUT):**
```json
{
  "customerId": 1,
  "orderDate": "2025-01-31T10:00:00",
  "orderStatus": "PENDING",
  "total": 199.90,
  "address": "Rua das Flores, 99"
}
```

**Response:**

<img width="1282" height="484" alt="image" src="https://github.com/user-attachments/assets/62fc7d57-ffea-4716-963f-677579ed993e" />


### Payments (Pagamentos)

- `POST /payments` – cria
- `GET /payments` – lista paginada
- `GET /payments/{id}` – detalhe
- `PUT /payments/{id}` – atualiza
- `DELETE /payments/{id}` – remove

**Enums aceitos:**
- `paymentMethod`: `CREDIT_CARD`, `DEBIT_CARD`, `CASH`, `PIX`
- `paymentStatus`: `PAID`, `PENDING`, `FAILED`

**Request (POST/PUT):**
```json
{
  "orderId": 1,
  "amount": 199.90,
  "paymentMethod": "PIX",
  "paymentStatus": "PAID"
}
```

**Response:**

<img width="1278" height="470" alt="image" src="https://github.com/user-attachments/assets/1a8870f7-1c6b-498a-a9a4-0f9905e37304" />


## Paginação

Todos os `GET /{recurso}` aceitam os parâmetros:
- `page` (padrão 0)
- `size` (padrão 10)
- `sort` (ex.: `sort=id,asc`)

**Exemplo:**
```http
GET http://localhost:8081/api/toys?page=0&size=5&sort=id,desc
```
