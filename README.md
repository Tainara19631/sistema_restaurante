# 🍔 Sistema Restaurante

Sistema de gerenciamento de pedidos de um restaurante, desenvolvido em Java, com foco na aplicação de Programação Orientada a Objetos, padrões de projeto e testes automatizados.

## 📌 Sobre o projeto

O Sistema Restaurante permite cadastrar pedidos, personalizar lanches, adicionar ingredientes extras, modificar pedidos, realizar pagamentos e acompanhar o fechamento do dia.

O projeto foi desenvolvido com o objetivo de praticar conceitos de desenvolvimento Java e organização de aplicações.

## 🚀 Funcionalidades

- Cadastro de pedidos e identificação de clientes.
- Consulta e listagem de pedidos.
- Criação de lanches a partir de um cardápio.
- Personalização de lanches, com remoção de ingredientes.
- Adição de cheddar e bacon extra.
- Modificação de pedidos, incluindo remoção e inclusão de itens.
- Cancelamento de pedidos.
- Pagamento de pedidos e registro de gorjetas.
- Controle de pedidos pagos e pendentes.
- Relatório de fechamento do restaurante.
- Identificação de combinações e lanches base diferentes.

## 🛠️ Tecnologias utilizadas

- **Java 17**
- **Maven**
- **JUnit 5**
- **Git e GitHub**

## 🧠 Conceitos aplicados

### Programação Orientada a Objetos

- Encapsulamento
- Herança
- Polimorfismo
- Abstração
- Interfaces
- Sobrescrita de métodos

### Padrões de projeto

- **Factory:** criação dos lanches do cardápio.
- **Decorator:** personalização dos lanches com adicionais.
- **Singleton:** gerenciamento da instância do caixa.

### Collections

- ArrayList
- HashMap
- HashSet
- ArrayDeque

## 🧪 Testes automatizados

O projeto utiliza JUnit 5 para validar as principais funcionalidades.

**Resultado da última execução:**

- 77 testes executados.
- 0 falhas.
- 0 erros.
- 0 testes ignorados.

Para executar os testes, entre na pasta `demo` e utilize:

```bash
mvn clean test
```

## ▶️ Como executar o projeto

### Pré-requisitos

- Java 17 ou superior.
- Maven instalado (para compilar e executar os testes).

### 1. Clone o repositório

```bash
git clone https://github.com/Tainara19631/sistema_restaurante.git
```

### 2. Entre na pasta do projeto

```bash
cd sistema_restaurante/demo
```

### 3. Compile o projeto e gere o executável

```bash
mvn clean package
```

### 4. Execute o sistema

```bash
java -jar target/demo-1.0-SNAPSHOT.jar
```

Também é possível abrir o sistema pelo arquivo `Abrir Restaurante.bat`, após gerar o executável.

## 📂 Estrutura do projeto

```text
sistema_restaurante/
│
├── README.md
│
└── demo/
    ├── pom.xml
    ├── Abrir Restaurante.bat
    │
    ├── src/
    │   ├── main/
    │   │   └── java/
    │   │       └── com/github/tainara19631/
    │   │           ├── app/
    │   │           ├── cardapio/
    │   │           ├── pedido/
    │   │           └── financeiro/
    │   │
    │   └── test/
    │       └── java/
    │
    └── target/
        └── demo-1.0-SNAPSHOT.jar
```

## 👩‍💻 Desenvolvedora

**Tainara Oliveira Souza**

GitHub: [@Tainara19631](https://github.com/Tainara19631)
Link do Vídeo: https://drive.google.com/file/d/17x7MTGndH5Vm9FU3F3F0NUoNeEW5gMLV/view?usp=sharing

---

Desenvolvido como projeto prático de aprendizado em Java e Programação Orientada a Objetos.