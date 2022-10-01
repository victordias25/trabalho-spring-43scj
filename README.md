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

  1. Abri e executar o projeto “inclusao-cartao” através do IntelliJ IDEA. 

  2. Alterar o caminho do arquivo “lista_alunos.txt” dentro da parta “src”. Alterar conforme sua máquina. 

  3. Ao executar o projeto será feito um  POST na API http://localhost:8090/alunos  

  4. Serão desconsiderados os espaços as linhas em branco, caracteres especiais como: “---------”. E o número do cartão de crédito será retirado os espaços em branco. 
