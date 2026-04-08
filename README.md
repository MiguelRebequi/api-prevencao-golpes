# api-prevencao-golpes 0.3.0
Projeto da Faculdade sobre uma API de prevenção de Golpes de compras não reconhecidas de Cartão de Crédito.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 21+
* **Framework Backend:** Spring Boot
* **Banco de Dados:** PostgreSQL (Nuvem)
* **Segurança:** Spring Security com autenticação JWT
* **Gerenciamento de Dependências:** Maven

## ⚙️ Como executar o projeto localmente
*(Instruções detalhadas serão adicionadas após a configuração do ambiente Spring Boot)*
1. Clone o repositório: `git clone [link-do-repositorio]`
2. Importe o projeto na sua IDE de preferência.
3. Configure as variáveis de ambiente do banco de dados no arquivo `application.properties`.
4. Execute a classe principal da aplicação Spring Boot.

## ☁️ Link para o Deploy
*(O link do deploy na nuvem será adicionado aqui em breve)*

## 📌 Rota: Criar Novo Usuário

**Método:** `POST`
**URL:** `/usuarios`
**Descrição:** Cadastra um novo usuário no sistema simulando o banco de dados na memória.

### Corpo da Requisição (Request Body)
O Front-end deve enviar um objeto JSON com a seguinte estrutura. 
**Atenção:** O campo `id` não deve ser enviado, pois é gerado automaticamente pelo banco.

```json
{
  "nomeUsuario": "String (Obrigatório) - Nome completo do cliente",
  "senha": "String (Obrigatório) - Senha de acesso",
  "email": "String (Obrigatório, Único) - E-mail para contato",
  "cpf": "String (Obrigatório, Único, 11 dígitos) - Documento de identificação"
}
```
### Exemplo de Envio:
```json
{
  "nomeUsuario": "Joao Silva",
  "senha": "senhaSegura123",
  "email": "joao@email.com",
  "cpf": "12345678900"
}
```
### Respostas Esperadas (Responses)
🟢 200 OK (ou 201 Created): Retorna o mesmo objeto JSON, mas agora com o "id" gerado pelo sistema.

🔴 400 Bad Request: Caso falte algum dado obrigatório.

🔴 500 Internal Server Error: Caso tente cadastrar um CPF ou E-mail que já existe (Unique Constraint).
