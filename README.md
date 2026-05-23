```Java

readme_content = """# 🐾 Clyvo Vet — Sistema Preditivo Longitudinal para Medicina Veterinária

 **Clyvo Vet** é um ecossistema inteligente voltado para o acompanhamento clínico longitudinal e engajamento contínuo na jornada de saúde do paciente animal. Desenvolvido para o **Challenge 2026 (1º Semestre - FIAP)**, este ecossistema adota práticas rigorosas de persistência manual nativa, tratamento semântico de exceções e metaprogramação estruturada baseada na Reflection API.

---

## 📋 Sumário
- [Problema de Negócio & Visão Estratégica](#-problema-de-negócio--visão-estratégica)
- [Arquitetura Avançada e Diferenciais](#-arquitetura-avançada-e-diferenciais)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Tecnologias e Dependências](#-tecnologias-e-dependências)
- [Configuração e Execução](#-configuração-e-execução)
- [Catálogo Completo de Endpoints (API REST)](#-catálogo-completo-de-endpoints-api-rest)
- [Regras de Negócio e Ciclo de Vida](#-regras-de-negócio-e-ciclo-de-vida)
- [Metaprogramação & Demonstração do Motor de Reflection]
- (#-metaprogramação--demonstração-do-motor-de-reflection)
- [Equipe e Autores (FIAP)](#-equipe-e-autores-fiap)

---

## 🎯 Problema de Negócio & Visão Estratégica

Na medicina veterinária convencional, o histórico de saúde do animal costuma ser fragmentado entre diferentes clínicas, cadernetas físicas de vacinação e relatos informais dos tutores. Essa falta de centralização impede diagnósticos precoces e quebra a continuidade do cuidado.

A **Clyvo Vet** resolve essa dor de mercado ao consolidar um **Histórico Clínico Longitudinal Unificado**. O sistema gerencia:
* **Ficha Cadastral e Prontuário Detalhado:** Acompanhamento físico e clínico contínuo do paciente.
* **Registro Imunológico:** Controle rigoroso de vacinas aplicadas, atrasadas ou agendadas.
* **Atividades Diárias:** Rastreamento de cuidados cotidianos como alimentação, passeios e medicações.
* **Alertas Preditivos:** Um motor dinâmico que gera notificações proativas baseadas em riscos de saúde ou imunizações críticas vencidas, transformando a postura reativa em medicina veterinária estritamente **preventiva**.

---

## 🏗️ Arquitetura Avançada e Diferenciais

O projeto foi construído afastando-se de abstrações genéricas ou automatizadas do Spring Data, focando em requisitos corporativos de infraestrutura de software de alta performance:

* **Persistência Manual (JPA Raiz):** Abolição do uso de interfaces mágicas como `JpaRepository`. Toda a manipulação do ciclo de vida das entidades, controle de escopo transacional e consultas complexas em JPQL foram escritas manualmente em classes **DAO (Data Access Object)** utilizando diretamente o `EntityManager` via `@PersistenceContext`.
* **Segregação Estrita com DTOs:** Total isolamento da camada de persistência. A API expõe apenas contratos imutáveis segregados entre solicitações (`RequestDTO`), respostas de dados (`ResponseDTO`) e visualizações complexas unificadas (`HistoricoDTO`).
* **Suporte Nativo a Bancos Corporativos (Oracle):** Configuração de chaves primárias baseada em estratégias eficientes de `GenerationType.SEQUENCE`, utilizando `@SequenceGenerator` dedicados por tabela. Essa abordagem previne problemas de contenção comuns à estratégia de auto-incremento em ambientes multi-usuário.
* **Tratamento Semântico Global de Erros:** Captura centralizada de falhas usando `@RestControllerAdvice`, traduzida em respostas JSON ricas com carimbo de data/hora, URI acessada e mensagens semânticas em português.

---

## 📂 Estrutura do Projeto

O código está organizado seguindo padrões rígidos de alta coesão e baixo acoplamento:


```

```text
README.md saved successfully!


```
br.com.clyvo.pet/
├── ProjectApplication.java       → Classe principal e gatilho do CommandLineRunner
├── config/                       → Configurações globais (Mecanismo de Cache e OpenAPI Swagger)
│   ├── CacheConfig.java
│   └── SwaggerConfig.java
├── controller/                   → Camada HTTP / Controladores REST com anotações OpenAPI
│   ├── AlertaPreditivoController.java
│   ├── AtividadeDiariaController.java
│   ├── PacienteAnimalController.java
│   └── RegistroImunologicoController.java
├── service/                      → Camada de Serviços / Lógica de negócio e mapeamentos manuais
│   ├── AlertaPreditivoService.java
│   ├── AtividadeDiariaService.java
│   ├── PacienteAnimalService.java
│   └── RegistroImunologicoService.java
├── repository/                   → Camada de Acesso a Dados / DAOs manuais com EntityManager
│   ├── AlertaPreditivoDao.java
│   ├── AtividadeDiariaDao.java
│   ├── PacienteAnimalDao.java
│   └── RegistroImunologicoDao.java
├── entity/                       → Modelos de Domínio mapeados no Hibernate / ORM
│   ├── AlertaPreditivo.java
│   ├── AtividadeDiaria.java
│   ├── PacienteAnimal.java
│   └── RegistroImunologico.java
├── dto/                          → Objetos de Transferência de Dados (Payloads da API)
│   ├── AlertaPreditivoRequestDTO.java / AlertaPreditivoResponseDTO.java
│   ├── AtividadeDiariaRequestDTO.java / AtividadeDiariaResponseDTO.java
│   ├── PacienteRequestDTO.java / PacienteResponseDTO.java / PacienteHistoricoDTO.java
│   └── RegistroImunologicoRequestDTO.java / RegistroImunologicoResponseDTO.java
├── enums/                        → Dominio de Enums estritamente tipados
│   ├── CategoriaEspecie.java
│   ├── StatusImunizacao.java
│   ├── TipoAlertaPreditivo.java
│   └── TipoRotina.java
├── exception/                    → Exceções Customizadas e Manipulador de Erros
│   ├── EntidadeNaoLocalizadaException.java
│   ├── InconsistenciaRegraClyvoException.java
│   └── GlobalExceptionHandler.java
└── core/                         → Infraestrutura Avançada de Metaprogramação
├── annotations/              → Metadados customizados (@TabelaMapeada, @ColunaMapeada, @ChavePrimaria)
└── util/                     → Motor Core de Reflection analítico
└── MotorReflectionClyvo.java

```

---

## 🚀 Tecnologias e Dependências

| Componente | Versão | Função Estratégica |
|:---|:---|:---|
| **Java** | 25 | Uso de recursos modernos da linguagem e LTS de última geração. |
| **Spring Boot** | 3.4.4 | Kernel do ecossistema, injeção de dependências e inversão de controle. |
| **Spring Web** | 3.4.4 | Exposição de endpoints REST corporativos de alta escalabilidade. |
| **Spring Cache** | 3.4.4 | Otimização drástica de performance de leitura de prontuários médicos. |
| **Hibernate Core** | 6.6.11.Final| Mecanismo de ORM subjacente controlado pelos DAOs. |
| **H2 Database** | 2.3.232 | Banco relacional rápido em memória para testes de desenvolvimento. |
| **Jakarta Validation**| 3.0.2 | Validação sintática rigorosa na borda da aplicação (`@Past`, `@NotBlank`). |
| **SpringDoc OpenAPI** | 2.8.6 | Geração automática da documentação Swagger interativa. |
| **Lombok** | 1.18.38 | Eliminação de código boilerplate através de anotações em compilação. |

---

## ▶️ Configuração e Execução

### Pré-requisitos
* **Java JDK 25** configurado corretamente nas variáveis de ambiente (`JAVA_HOME`).
* **Apache Maven 3.x** instalado (ou uso do Maven Wrapper).

### Passo a Passo para Rodar Localmente

```bash
# 1. Clonar o repositório do projeto challenge
git clone [https://github.com/emanuel-italo/challengejava---sprint1.git](https://github.com/emanuel-italo/challengejava---sprint1.git)

# 2. Entrar no diretório do módulo backend
cd challengejava---sprint1/clyvo

# 3. Executar o ciclo de limpeza e compilação do Maven
mvn clean package

# 4. Iniciar a aplicação Spring Boot
mvn spring-boot:run

```

A aplicação subirá com sucesso e estará ouvindo requisições HTTP na porta **`8080`**.

### 📖 Documentação Gráfica da API (Swagger)

Com o sistema operacional, acesse a interface interativa do Swagger para realizar chamadas reais contra os controladores:

* **Swagger UI:** [http://localhost:8080/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui.html)
* **API Docs (JSON):** [http://localhost:8080/api-docs](https://www.google.com/search?q=http://localhost:8080/api-docs)

### 🗄️ Console do Banco de Dados (H2 Console)

O banco de dados relacional em memória expõe um console visual para checagem de tabelas e execução de queries SQL:

* **URL de Acesso:** [http://localhost:8080/h2-console](https://www.google.com/search?q=http://localhost:8080/h2-console)
* **JDBC URL:** `jdbc:h2:mem:petosdb`
* **Usuário Credenciado:** `RM561337`
* **Senha:** *(Deixar em branco)*

---

## 📌 Catálogo Completo de Endpoints (API REST)

### 🐶 Pacientes Animais (`/pacientes`)

* `GET /pacientes` - Recupera todos os animais ativos no sistema *(Mecanismo de Cache habilitado)*.
* `GET /pacientes/{id}` - Retorna a ficha cadastral de um paciente por ID específico.
* `GET /pacientes/busca?nome=Rex` - Busca animais ativos filtrando parcialmente por nome/apelido (ignore-case).
* `GET /pacientes/especie/{especie}` - Lista animais com base na espécie informada (`CACHORRO`, `GATO`, etc.).
* `GET /pacientes/imunizacoes/vencendo` - Localiza animais com vacinas críticas atrasadas ou próximas do prazo.
* `GET /pacientes/{id}/historico-longitudinal` - Consolidação completa da saúde (Ficha + Alertas + Vacinas + Atividades).
* `POST /pacientes` - Insere um novo paciente bicho no ecossistema clínico.
* `PUT /pacientes/{id}` - Altera dados cadastrais e prontuários detalhados.
* `DELETE /pacientes/{id}` - Soft Delete: Modifica o estado do animal para inativo sem perder o histórico longitudinal.

### 💉 Registros Imunológicos (`/imunizacoes`)

* `GET /imunizacoes` - Lista todos os registros históricos de aplicações de vacinas.
* `GET /imunizacoes/{id}` - Busca um único registro vacinal por ID.
* `GET /imunizacoes/paciente/{pacienteId}` - Recupera a carteira vacinal completa do paciente.
* `POST /imunizacoes` - Registra uma nova aplicação de imunizante.
* `PUT /imunizacoes/{id}` - Altera informações como lote ou prazo de vencimento.
* `DELETE /imunizacoes/{id}` - Remove um registro imunológico da base.

### 📅 Atividades Diárias (`/rotinas`)

* `GET /rotinas` - Lista de forma geral todos os registros de rotinas inseridos.
* `GET /rotinas/{id}` - Detalha uma atividade diária por ID.
* `GET /pacientes/{pacienteId}/rotinas` - Cronograma de atividades de um animal específico em ordem cronológica decrescente.
* `POST /rotinas` - Registra um novo cuidado ou rotina diária (Ex: Passeio, Medicação, Banho).
* `PUT /rotinas/{id}` - Modifica descrições ou tipos de atividades.
* `DELETE /rotinas/{id}` - Remove o registro de rotina.

### 🔔 Alertas Preditivos (`/alertas-preditivos`)

* `GET /alertas-preditivos` - Consolida todos os alertas preditivos gerados no sistema.
* `GET /alertas-preditivos/{id}` - Busca uma notificação preditiva por ID.
* `GET /alertas-preditivos/paciente/{pacienteId}` - Lista as ocorrências preditivas emitidas para um animal específico.
* `POST /alertas-preditivos` - Emissão manual de um alerta de risco ou agendamento.
* `PUT /alertas-preditivos/{id}` - Atualização de dados da previsão ou mensagem.
* `PATCH /alertas-preditivos/{id}/enviado` - Altera a flag de envio para verdadeiro, confirmando que o tutor foi notificado.
* `DELETE /alertas-preditivos/{id}` - Exclusão de alertas preditivos.

---

## 🧠 Regras de Negócio e Ciclo de Vida

* **Proteção Histórica Longitudinal:** O acionamento do endpoint de deleção de um paciente não remove seus registros de forma física no banco. O sistema intercepta a chamada no service, desativa o campo `ativo` da entidade e executa um merge manual via DAO. Isso blinda e preserva os históricos médicos vinculados para análises estatísticas e preditivas futuras.
* **Validações na Camada de Entrada:** O payload de entrada passa por triagens rigorosas através do Jakarta Validation. É vedado o cadastro de animais com data de nascimento no futuro, nomes em branco ou pesos nulos/negativos, garantindo a qualidade da base relacional.
* **Callbacks do Ciclo de Vida JPA:** Acoplamento inteligente das anotações `@PrePersist` e `@PreUpdate` diretamente dentro dos modelos. As auditorias de criação (`dataCriacao`) e atualizações de dados (`ultimaAtualizacao`) são calculadas de forma autônoma pela infraestrutura ORM antes do commit da transação, mantendo os Services focados apenas na lógica de negócio.

---

## 📦 Elementos Estritamente Tipados (Enums de Domínio)

Para evitar strings mágicas ou inconsistências na base de dados, todas as categorizações foram modeladas usando Enums explícitos:

* **`CategoriaEspecie`:** `CACHORRO`, `GATO`, `PASSARO`, `PEIXE`, `HAMSTER`, `COELHO`, `REPTIL`, `OUTRO`
* **`StatusImunizacao`:** `AGENDADA`, `APLICADA`, `ATRASADA`, `CANCELADA`
* **`TipoRotina`:** `ALIMENTACAO`, `BANHO_E_TOSA`, `PASSEIO`, `MEDICACAO`, `VISITA_VETERINARIA`, `EXERCICIO`, `OUTRO`
* **`TipoAlertaPreditivo`:** `IMUNIZACAO_VENCIDA`, `IMUNIZACAO_PREVISTA`, `CHECKUP_PREVENTIVO`, `ALERTA_RISCO_SAUDE`, `ACOMPANHAMENTO_CLINICO`, `REVISAO_MEDICAMENTOSA`, `ROTINA_CUIDADO`, `MENSAGEM_RELACIONAMENTO`, `OUTRO`

---

## 🧠 Metaprogramação & Demonstração do Motor de Reflection

Como prova de conceito do uso avançado de metadados, criámos a classe utilitária **`MotorReflectionClyvo`**. No arranque da aplicação, através da interface `CommandLineRunner`, o motor realiza uma introspeção completa de um modelo anotado em tempo de execução, quebrando barreiras de encapsulamento (`setAccessible(true)`) para auditar as configurações estruturais do banco de dados relacional.

### Exemplo de Log Emitido Automaticamente no Terminal no Start do Projeto:

```text
========================================================
⚙️ MOTOR REFLECTION CLYVO VET - ANÁLISE DE ENTIDADE ⚙️
Classe Analisada: PacienteAnimal
Tabela Mapeada no Banco: TB_PACIENTE_ANIMAL
Chave Primária da Tabela: ID_PACIENTE
Mapeamento de Colunas:
 ├─ Coluna BD: ID_PACIENTE | Valor Atual: 999
 ├─ Coluna BD: NOME_ANIMAL | Valor Atual: Rex Reflection
 ├─ Coluna BD: CATEGORIA_ESPECIE | Valor Atual: CACHORRO
 ├─ Coluna BD: RACA_ANIMAL | Valor Atual: Pastor Alemão
 ├─ Coluna BD: RESPONSAVEL_LEGAL | Valor Atual: Professor FIAP
 ├─ Coluna BD: PESO_ATUAL | Valor Atual: 35.0
 ├─ Coluna BD: STATUS_ATIVO | Valor Atual: true
========================================================

```

---

## 👨‍💻 Equipe e Autores (FIAP)

O desenvolvimento deste ecossistema foi conduzido pelo time de engenharia de produto composto pelos seguintes integrantes:

* **Emanuel Italo Leal Trindade Soares** (RM 561337) — *Software Engineer & Automation Architect*
* **Paulo Henrique Alves Estalise** — *Collaborating Engineer & Product Analyst*
* **Gabriel Bebe** — *Collaborating Engineer & Product Specialist*

---

*Desenvolvido sob os preceitos de excelência técnica e rigor acadêmico para a disciplina de Java Advanced da FIAP — Termo de 2026.*
