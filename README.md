# 📋 Sistema Gerenciador de Tarefas Online (SGTO)

> **Projeto Integrador III-B** — Pontifícia Universidade Católica de Goiás (PUC Goiás)  
> Curso: Análise e Desenvolvimento de Sistemas — CEAD  
> Ano: 2026

---

## 👥 Integrantes

| Nome |
|------|
| Ariel Jorge da Silva |
| Leandro Batista de Sousa Galdino |
| Victor Hugo Batista Pereira |
| Pedro Marques |

---

## 🎯 Objetivo do Projeto

O **Sistema Gerenciador de Tarefas Online (SGTO)** foi desenvolvido em parceria com a empresa **Twobi**, que atua no ramo de tecnologia desenvolvendo soluções digitais. O projeto tem como finalidade resolver um problema real identificado na organização: a ausência de um sistema centralizado e eficiente para gerenciar tarefas, prazos e responsabilidades internas.

Sem um controle adequado, a empresa enfrentava:

- Retrabalho e atrasos nas entregas
- Falta de visibilidade sobre o andamento dos projetos
- Dificuldade na distribuição e acompanhamento de responsabilidades da equipe

A solução proposta é uma **aplicação web full-stack** que permite cadastro de usuários, autenticação, criação de dashboards (quadros Kanban), criação e atribuição de tarefas, e acompanhamento do progresso em tempo real — promovendo maior organização, controle e produtividade.

---

## 📌 Requisitos do Sistema (conforme proposta do projeto)

O sistema deve contemplar, no mínimo, as seguintes funcionalidades:

- ✅ Cadastro e autenticação de usuários com acesso controlado
- ✅ Criação de projetos/dashboards com atribuição de membros
- ✅ Criação, atribuição, acompanhamento e conclusão de tarefas
- ✅ Visualização em quadro Kanban (colunas: "A fazer", "Em andamento", "Concluído")
- ✅ Organização de tarefas por status ou prioridade
- ✅ Controle de roles/permissões de usuários

---

## 🏗️ Arquitetura do Sistema

O projeto segue a arquitetura **cliente-servidor**, dividido em dois módulos independentes:

```
Sistema-Gerenciador-de-Tarefas-Online/
├── sgto-backend/        # API REST — Java + Spring Boot + Gradle
├── sgto-frontend/       # Interface Web — TypeScript + React + npm
├── docker-compose.yml   # Banco de dados PostgreSQL via Docker
└── README.md
```

### Modelo de Dados

O banco de dados é estruturado com as seguintes entidades principais:

- **USUARIO** — id, username, email, senha_hash, ativo, criado_em, atualizado_em
- **ROLE** — id, nome, descrição
- **ROLES_USUARIOS** — relacionamento N:N entre usuários e papéis
- **DASHBOARD** — id, nome (quadro Kanban do usuário)
- **TASK** — id, nome, descrição, id_usuario_atribuido, criado_em, atualizado_em

---

## 🛠️ Tecnologias Utilizadas

### Backend
| Tecnologia | Finalidade |
|---|---|
| **Java** | Linguagem principal do backend |
| **Spring Boot** | Framework para criação da API REST |
| **Gradle** | Gerenciador de dependências e build |
| **PostgreSQL** | Banco de dados relacional |
| **Docker** | Containerização do banco de dados |

### Frontend
| Tecnologia | Finalidade |
|---|---|
| **TypeScript** | Linguagem principal do frontend |
| **React** | Biblioteca para construção de interfaces |
| **npm** | Gerenciador de pacotes do Node.js |

### Ferramentas de Apoio
| Ferramenta | Finalidade |
|---|---|
| **Trello** | Gestão ágil das atividades do projeto |
| **Figma / Canva** | Prototipação visual das telas |
| **IntelliJ IDEA / JetBrains** | IDEs para desenvolvimento |
| **Git + GitHub** | Controle de versão e repositório remoto |

---

## 📦 Pré-requisitos — O que instalar

Antes de executar o projeto, você precisa ter instalado em sua máquina:

### 1. Git
Necessário para clonar o repositório.

- 🔗 Download: https://git-scm.com/downloads
- Verifique a instalação:
  ```bash
  git --version
  ```

### 2. Docker Desktop
Utilizado para subir o banco de dados PostgreSQL em um container, sem necessidade de instalação manual do banco.

- 🔗 Download: https://www.docker.com/products/docker-desktop/
- Após instalar, certifique-se de que o Docker está em execução.
- Verifique a instalação:
  ```bash
  docker --version
  docker compose version
  ```

> **Atenção:** O Docker Desktop já inclui o Docker Compose. Em versões mais antigas do Docker, o comando pode ser `docker-compose` (com hífen).

### 3. JDK 17 ou superior (Java Development Kit)
Necessário para compilar e executar o backend Spring Boot.

- 🔗 Download (Eclipse Temurin, recomendado): https://adoptium.net/
- 🔗 Alternativa (Oracle JDK): https://www.oracle.com/java/technologies/downloads/
- Verifique a instalação:
  ```bash
  java -version
  ```
  A saída deve indicar a versão `17` ou superior.

### 4. Node.js (versão LTS) + npm
Necessário para executar o frontend React/TypeScript. O npm é instalado automaticamente junto com o Node.js.

- 🔗 Download: https://nodejs.org/en/download
- Escolha a versão **LTS (Long Term Support)** para maior estabilidade.
- Verifique a instalação:
  ```bash
  node --version
  npm --version
  ```

---

## 🚀 Passo a Passo — Como Executar o Projeto

### Etapa 1 — Clonar o Repositório

Abra o terminal e execute:

```bash
git clone https://github.com/arieljorge/Sistema-Gerenciador-de-Tarefas-Online.git
cd Sistema-Gerenciador-de-Tarefas-Online
```

---

### Etapa 2 — Subir o Banco de Dados com Docker

Na raiz do projeto (onde está o arquivo `docker-compose.yml`), execute:

```bash
docker compose up -d
```

Esse comando irá:
- Baixar a imagem `postgres:18-alpine` (apenas na primeira execução)
- Criar e iniciar o container `sgto-database`
- Expor o banco de dados na porta `5432`

As credenciais configuradas no `docker-compose.yml` são:

| Parâmetro | Valor |
|---|---|
| **Usuário** | `admin` |
| **Senha** | `secure_password` |
| **Banco** | `postgres` |
| **Porta** | `5432` |

Para verificar se o container está rodando:

```bash
docker ps
```

Você deve ver o container `sgto-database` com status `Up`.

> Para parar o banco de dados quando quiser: `docker compose down`

---

### Etapa 3 — Executar o Backend (Spring Boot + Gradle)

Abra um novo terminal e navegue até a pasta do backend:

```bash
cd sgto-backend
```

#### No Linux/macOS:
```bash
./gradlew bootRun
```

#### No Windows:
```cmd
gradlew.bat bootRun
```

> O Gradle Wrapper (`gradlew`) baixa automaticamente a versão correta do Gradle, não sendo necessário instalá-lo manualmente.

Aguarde até ver no terminal uma mensagem similar a:
```
Started SgtoBackendApplication in X.XXX seconds
```

Por padrão, o backend estará disponível em: **http://localhost:8080**

#### Configuração da conexão com o banco

Certifique-se de que o arquivo `src/main/resources/application.properties` (ou `application.yml`) do backend está configurado com as mesmas credenciais do Docker:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=admin
spring.datasource.password=secure_password
```

> Se o arquivo de configuração tiver valores diferentes, ajuste para corresponder ao `docker-compose.yml`.

---

### Etapa 4 — Executar o Frontend (React + TypeScript + npm)

Abra outro terminal e navegue até a pasta do frontend:

```bash
cd sgto-frontend
```

Instale as dependências do projeto:

```bash
npm install
```

Inicie o servidor de desenvolvimento:

```bash
npm run dev
```

Aguarde até que o terminal exiba o endereço local, geralmente:

```
Local:   http://localhost:5173
```

Abra o navegador e acesse **http://localhost:5173** para usar o sistema.

---

### Resumo — Ordem de Execução

```
1. docker compose up -d          ← Banco de dados (raiz do projeto)
2. ./gradlew bootRun             ← Backend (pasta sgto-backend/)
3. npm install && npm run dev    ← Frontend (pasta sgto-frontend/)
```

---

## 🗂️ Organização do Projeto (Gestão Ágil)

O desenvolvimento foi organizado utilizando o **Trello**, seguindo uma abordagem ágil com divisão em colunas:

- **Documentação** — Registro dos artefatos e documentação do projeto
- **Vídeo** — Gravação de demonstração das funcionalidades
- **Levantamento de Requisitos** — Reuniões com a empresa parceira e definição de escopo
- **A Fazer** — Tarefas pendentes de desenvolvimento
- **Em Progresso** — Tarefas em andamento
- **Testando** — Funcionalidades em fase de validação
- **Concluído** — Entregas finalizadas

---

## 📚 Referências Bibliográficas

- SOMMERVILLE, I. **Engenharia de Software**. 9. ed. Pearson, 2011.
- PRESSMAN, R. S. **Engenharia de Software: Uma Abordagem Profissional**. 8. ed. McGraw-Hill, 2014.
- SCHILDT, H. **Java: A Beginner's Guide**. 6. ed. McGraw-Hill, 2014.

---

## 📄 Licença

Este projeto está licenciado sob a licença **MIT**. Consulte o arquivo [LICENSE](./LICENSE) para mais detalhes.

---

> Projeto desenvolvido para fins acadêmicos — Projeto Integrador III-B, PUC Goiás, 2026.
