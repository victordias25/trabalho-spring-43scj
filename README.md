# <h1 align="center"> Trabalho Final - 43SCJ - SPRING  </h1>

# Objetivo do projeto

Desenvolvimento de uma solução para emissão de cartão de crédito para ser utilizado pelos alunos da FIAP, com um sistema para gerenciamento e integração com outras empresas.

> :construction: Projeto Concluído

## ✔️ APIs

  - ``O trabalho contém 3 APIS:``

  - ``1. API de Alunos usado para emissão do cartão de crédito. Link: http://localhost:8090/alunos``

  - ``2. API da Autorizada usado para validar os dados da transação. Link: http://localhost:8080/autorizada`` 

  - ``3. API de Transação usado para consultar todas transações autorizadas e consultar extrato. Link: http://localhost:8090/transacao``  

## ✔️ Rotas

   - ``Todas as rotas para executar via Postman encontram-se na raiz do repositório no arquivo: “API Cartão, Autorizada e Transação.postman_collection.json”`` 

## 🔨 COMO EXECUTAR O PROJETO
   
## ✔️ Executando o projeto “inclusao-cartao” 

 - ``1. Abri e executar o projeto “inclusao-cartao” através do IntelliJ IDEA.``

 - ``2. Alterar o caminho do arquivo “lista_alunos.txt” dentro da parta “src”. Alterar conforme sua máquina.``

 - ``3. Ao executar o projeto será feito um  POST na API http://localhost:8090/alunos``

 - ``4. Serão desconsiderados os espaços as linhas em branco, caracteres especiais como: “---------”. E o número do cartão de crédito será retirado os espaços em branco.``
  
 - ``Print do POST efetuado com sucesso:``
 <img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/1.PNG">
  
 ## ✔️ Executando o projeto “api-spring-emissao-cartao"  
 
  - ``1. Dentro desse projeto temos duas controller/ APIs: AlunoController e TransacaoController``

 - `` 2. Ao abri e executar o projeto “api-spring-emissao-cartao" através do IntelliJ IDEA você pode acessar a API de Alunos: http://localhost:8090/alunos``

 - `` 3. Essa APIs contém as operações CRUD. Somente é possível cadastrar 1 cartão de crédito com mesmo número. Ao tentar cadastrar o mesmo cartão de crédito irá retornar um BAD REQUEST.``

 - `` 4. A API TransacaoController você pode acessar ela através do Link: http://localhost:8090/transacao. A mesma será mais bem detalhada após a apresentação da API  Autorizada.``

 - `` 5. Essa API retorna somente todas ao transações Autorizadas. Transações que foram feitas na API da Autorizada que será apresentada a seguir``
 
  ## ✔️ Executando o projeto “api-spring-autorizada" 
  
  - ``1. Dentro desse projeto temos uma controller/ API: AutorizadaController.`` 

  - ``2. Ao abri e executar o projeto “api-spring-autorizada" através do IntelliJ IDEA você pode acessar a API da Autorizada: http://localhost:8080/autorizada``  

  - ``3. Essa API recebe toda as tentativas de transação recebendo o número do cartão de crédito e o valor da transação. Ao efetuar uma tentativa de transação será feito uma consulta na API de Alunos para validar os dados do cartão de crédito.``  

  - ``4. Caso o cartão seja invalidado será registrado na API da Transação como: Recusado. Cartão de Crédito incorreto. Conforme print abaixo.`` 
  <img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/2.PNG">

  - ``5. Caso o cartão seja validado será registrado na API da Transação como: Autorizado. Conforme print abaixo:``
  <img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/3.PNG">
   
   - ``6. Consultando a API da Autorizada irá retornar todas as transações autorizadas e recusadas:`` 
   <img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/4.PNG">

   - ``7. As transações autorizadas serão enviada para a API: http://localhost:8090/transacao``   

   - ``8. Essa API retorna somente as transações autorizadas.`` 

   - ``9. Dentro dessa API você consegue acessar o Extrato de um cartão de crédito fazendo um GET para: http://localhost:8090/transacao/api/extrato?cartaoCredito=numeroCartaoCredito``   

   - ``10. Fazendo um GET: http://localhost:8090/transacao/api/extrato?cartaoCredito=861083316026. Irá retornar o extrato desse cartão e será feito o Download do arquivo “Extrato.txt” na raiz do projeto api-spring-emissao-cartao. Conforme print abaixo:`` 
   <img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/5.PNG">

## ✔️ Documentação

- ``Foi feito a documentação da API via Swagger. A documentação da API de Alunos e Transação pode ser acessada através do Link: http://localhost:8090/swagger-ui.html`` 

- ``A documentação da API da Autorizada pode ser acessada através do Link: http://localhost:8080/swagger-ui.html`` 

## ✔️ Teste Unitário e de Integração: 

- ``Foi realizado teste Unitário e de Integração da API de Aluno e da Autoriza. Ambos se encontram na pasta “test” do projeto. Abaixo os prints:``
-
<img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/6.PNG">
<img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/7.PNG">
<img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/8.PNG">
<img src="https://github.com/victordias25/trabalho-spring-43scj/blob/main/img/9.PNG">

## ✔️ Técnicas e tecnologias utilizadas

- ``Spring``
- ``Swagger``
- ``InteliJ IDEA``
- ``Java``

## ✔️ Autores
- ``Ali Tannouri Neto``
- ``Matheus Ciribel``
- ``Pedro Henrique Rossi``
- ``Victor Alves Bugueno``
- ``Victor Augusto Pereira Dias Nicola``

