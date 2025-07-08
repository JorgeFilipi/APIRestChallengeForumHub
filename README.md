# 🚀 ForumHub - API REST

Uma API REST completa para gerenciamento de fórum de discussão, desenvolvida com Spring Boot 3 e Spring Security. O projeto permite cadastro de usuários, autenticação JWT e gerenciamento de tópicos de discussão.

## 📋 Índice

- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração](#-configuração)
- [Executando o Projeto](#-executando-o-projeto)
- [Endpoints da API](#-endpoints-da-api)
- [Autenticação](#-autenticação)
- [Exemplos de Uso](#-exemplos-de-uso)
- [Contribuição](#-contribuição)

## ✨ Funcionalidades

- 🔐 **Autenticação JWT**: Sistema seguro de login com tokens
- 👤 **Gestão de Usuários**: Cadastro e gerenciamento de autores
- 📝 **Fórum de Discussão**: CRUD completo de tópicos
- 🔒 **Autorização**: Controle de acesso baseado em roles
- 📊 **Paginação**: Listagem paginada de tópicos
- 🗑️ **Soft Delete**: Exclusão lógica de tópicos
- ✅ **Validação**: Validação de dados com Bean Validation

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT (Auth0)**
- **Lombok**
- **Maven**
- **Bean Validation**

## 📁 Estrutura do Projeto

```
src/main/java/br/com/alura/ForumHub/
├── controller/
│   ├── AuthenticationController.java    # Controle de autenticação
│   ├── AutorController.java             # Controle de autores
│   └── ForumController.java             # Controle de tópicos
├── domain/
│   ├── autores/
│   │   ├── Autor.java                   # Entidade Autor
│   │   ├── AutorRepository.java         # Repository de autores
│   │   ├── Curso.java                   # Enum de cursos
│   │   └── DTOs/                        # Data Transfer Objects
│   └── topicos/
│       ├── Topicos.java                 # Entidade Tópico
│       ├── TopicosRepository.java       # Repository de tópicos
│       └── DTOs/                        # Data Transfer Objects
├── infra/
│   └── security/
│       ├── SecurityConfigurations.java  # Configurações de segurança
│       ├── SecurityFilter.java          # Filtro de segurança
│       └── TokenService.java            # Serviço de tokens JWT
└── ForumHubApplication.java             # Classe principal
```

## ⚙️ Pré-requisitos

- Java 21 ou superior
- Maven 3.6+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Configuração

### 1. Configuração do Banco de Dados

Crie um banco PostgreSQL e configure as variáveis de ambiente:

```bash
# Crie um arquivo .env na raiz do projeto
DB_URL=jdbc:postgresql://localhost:5432/forumhub
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
SECRET_TOKEN=sua_chave_secreta_jwt_super_segura
```

### 2. Configuração do application.properties

O arquivo já está configurado para usar as variáveis de ambiente:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

## 🚀 Executando o Projeto

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd ForumHub
```

### 2. Configure as variáveis de ambiente
```bash
# Crie o arquivo .env com as configurações do banco
cp .env.example .env
# Edite o arquivo .env com suas configurações
```

### 3. Execute o projeto
```bash
# Com Maven
./mvnw spring-boot:run

# Ou compile e execute
./mvnw clean compile
./mvnw spring-boot:run
```

O servidor estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

### 🔐 Autenticação

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| POST | `/login` | Realizar login | Não |

### 👤 Autores

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| POST | `/autores/cadastrar` | Cadastrar novo autor | Sim |
| GET | `/autores` | Listar todos os autores | Sim |
| GET | `/autores/{id}` | Detalhar autor específico | Sim |

### 📝 Tópicos

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| GET | `/topicos` | Listar tópicos (paginação) | Sim |
| POST | `/topicos` | Criar novo tópico | Sim |
| GET | `/topicos/{id}` | Detalhar tópico específico | Sim |
| POST | `/topicos/{id}` | Atualizar tópico | Sim |
| DELETE | `/topicos/{id}` | Excluir tópico (soft delete) | Sim |

## 🔐 Autenticação

### 1. Cadastro de Usuário

```bash
curl -X POST http://localhost:8080/autor/cadastrar \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "123456",
    "telefone": "11999999999",
    "curso": "JAVA"
  }'
```

### 2. Login

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "senha": "123456"
  }'
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 3. Usar Token

```bash
curl -X GET http://localhost:8080/topicos \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

## 💡 Exemplos de Uso

### Criar um Tópico

```bash
curl -X POST http://localhost:8080/topicos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN" \
  -d '{
    "titulo": "Dúvida sobre Spring Boot",
    "mensagem": "Como configurar o Spring Security?",
    "curso": "JAVA",
    "autorId": 1
  }'
```

### Listar Tópicos com Paginação

```bash
curl -X GET "http://localhost:8080/topicos?page=0&size=10" \
  -H "Authorization: Bearer SEU_TOKEN"
```

### Atualizar um Tópico

```bash
curl -X POST http://localhost:8080/topicos/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN" \
  -d '{
    "titulo": "Dúvida sobre Spring Boot - Atualizado",
    "mensagem": "Como configurar o Spring Security? (Versão atualizada)",
    "curso": "JAVA",
    "autorId": 1
  }'
```

## 🎯 Cursos Disponíveis

- `JAVA`
- `PYTHON`
- `JAVASCRIPT`
- `PHP`
- `C_SHARP`
- `RUBY`
- `GO`
- `SWIFT`
- `KOTLIN`
- `RUST`

## 🔧 Configurações de Segurança

- **CSRF**: Desabilitado (API REST)
- **Sessões**: Stateless (JWT)
- **Senhas**: Criptografadas com BCrypt
- **Tokens**: JWT com expiração de 2 horas

## 🐛 Solução de Problemas

### Erro 403 (Forbidden)
- Verifique se o token JWT está sendo enviado corretamente
- Confirme se o token não expirou
- Verifique se o header `Authorization` está no formato: `Bearer TOKEN`

### Erro de Conexão com Banco
- Verifique se o PostgreSQL está rodando
- Confirme as credenciais no arquivo `.env`
- Verifique se o banco de dados existe

### Erro de Compilação
- Verifique se está usando Java 21
- Execute `./mvnw clean compile` para limpar e recompilar

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

<p align="left">
<!--<a href="https://dev.to/fabcovalesci" target="_blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/devto.svg" alt="fabcovalesci" height="30" width="40" /></a>-->
<a href="https://linkedin.com/in/jfdias" target="_blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="jfdias" height="30" width="40" /></a>
<!--<a href="https://stackoverflow.com/users/14102549" target="_blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/stack-overflow.svg" alt="14102549" height="30" width="40" /></a>-->
<a href="https://discord.gg/jorgefelipe1986" target="_blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/discord.svg" alt="jorgefelipe1986" height="30" width="40" /></a>
<a href="mailto:jorgefelipe1986@gmail.com" target="_blank"><img align="center" src="https://img.icons8.com/color/48/000000/gmail-new.png" alt="jorgefelipe1986@gmail.com" height="30" width="40" /></a>
</p>


⭐ Se este projeto te ajudou, deixe uma estrela!
