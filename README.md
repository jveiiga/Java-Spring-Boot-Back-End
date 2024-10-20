# Projeto: E-Commerce Fullstack com Java e Spring Boot

## Visão Técnica - Back-End

O back-end foi implementado usando Java com Spring Boot e MySQL. As principais tecnologias e recursos utilizados incluem:

- **API Rest**: Construída com Java e Spring Boot.
- **Banco de Dados**: MySQL.
- **ORM**: JPA com Hibernate para mapeamento objeto-relacional.
- **Envio de Email**: SMTP da Google.
- **Armazenamento de Imagens**: S3 da Amazon.
- **Autenticação e Autorização**: Uso de tokens JWT.
- **Deploy na Nuvem**: Heroku.
- **Outros Conceitos**:
  - Validação de dados.
  - Tratamento de exceções personalizadas.
  - Uso adequado do protocolo HTTP seguindo o padrão REST.
  - Segurança (CORS e mais).

**Versões**: Spring Boot 2.0.x e 1.5.x.

## Metodologia

Todo o conteúdo do curso foi explicado em detalhes, com materiais de apoio para facilitar o acompanhamento. O código desenvolvido está disponível nesse repositório.

Os principais pontos da metodologia são:
- Explicação detalhada de cada etapa.
- Materiais de apoio para consulta.
- Código organizado no GitHub com commits relacionados às aulas.

## O curso ofereceu conteúdo adicional, incluindo:

- **Ferramentas de Apoio**: Seção dedicada a ferramentas que auxiliam no desenvolvimento com Spring Boot.

## Público-Alvo

Este curso é ideal para quem deseja:
- Aprender a construir um back-end com domínio complexo usando Java e Spring Boot.
- Aplicar conteúdos acadêmicos em um projeto comercial real.
- Incrementar o portfólio e o currículo.


## O Que Eu Aprendi

- Implementação de modelos de domínio.
- Mapeamento objeto-relacional com JPA.
- Acesso a dados com Spring Data.
- Desenvolvimento em camadas.
- Criação de API REST com serialização JSON.
- Desenvolvimento baseado em casos de uso.
- Autenticação e autorização com tokens JWT.
- Envio de emails.
- Armazenamento de imagens com Amazon S3.
- Implantação do back-end no Heroku.

---

## Tecnologias Utilizadas

### Back-End:
- Java
- Spring Boot
- MySQL
- JPA (Hibernate)
- SMTP (Google)
- AWS S3
- JWT (JSON Web Token)
- Heroku

---

## Estrutura do Projeto

- **Backend**:
    - `src/main/java`: Contém o código-fonte Java do back-end.
    - `src/main/resources`: Arquivos de configuração, incluindo `application.properties`.
    - **Endpoints**: API RESTful organizada por casos de uso.
    - 
---

## Instalação e Configuração

### Pré-requisitos

- JDK 8 ou superior
- Maven
- MySQL
- Conta na AWS para uso do S3
- Conta no Heroku

### Configuração do Back-End

1. Clone o repositório:
   ```bash
   git clone https://github.com/jveiiga/Java-Spring-Boot-Back-End.git
   ```
2. Instale as dependências:
   ```bash
   mvn install
   ```
3. Configure o banco de dados MySQL no arquivo application.properties:
```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/seu-banco-de-dados
  spring.datasource.username=seu-usuario
  spring.datasource.password=sua-senha
```
4.Execute o projeto:
```bash
  mvn spring-boot:run
```
   

