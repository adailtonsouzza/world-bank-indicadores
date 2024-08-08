# Projeto Front-end e Back-end com Docker

Este projeto consiste em um front-end Angular e um back-end Java que consome a API do Banco Mundial. O front-end e o back-end estão configurados para rodar em containers Docker e se comunicam entre si.

## Tecnologias

- **Java**: 17
- **Angular**: 17
- **Node.js**: 20.16.0

## Estrutura do Projeto

- **Front-end**: Angular 17
- **Back-end**: Java 17
- **Docker**: Para containerização do front-end e back-end

## Passos para Inicializar a Aplicação via Docker

1. **Clone o Repositório**

   Clone este repositório para sua máquina local:

   ```bash
   git clone https://github.com/adailtonsouzza/world-bank-indicadores.git
   cd world-bank-indicadores

 2. **Certifique-se de que o Docker e o Docker Compose estejam instalados**

      Verifique se o Docker e o Docker Compose estão instalados:
      ```bash
      docker --version
      docker-compose --version

  3. **Construa e Inicie os Containers**
      Navegue até o diretório raiz do projeto onde está o arquivo docker-compose.yml e execute:
     ```bash
     docker-compose up --build
  4. **Acesse a Aplicação**
      ° O front-end estará disponível em http://localhost:4200.
      ° O back-end estará disponível em http://localhost:8080.

  5. **Parar os Containers**
     ```bash
     docker-compose down

   ##Endpoint da API

   **Endereço**
   - URL: http://localhost:8080/api/indicador?codigoPais=br

   **Descrição**
     Este endpoint consome a API do Banco Mundial para obter informações sobre um país baseado no código passado como parâmetro.

   **Parâmetros**

   - codigoPais: Código do país a ser pesquisado (por exemplo, br para Brasil).


  ##Front-end

    O front-end é uma aplicação Angular que permite ao usuário digitar o código do país e clicar em "Pesquisar". Os dados são buscados através do back-end e apresentados em uma tabela. Caso ocorra um erro, um alerta é exibido.

  **Funcionamento**

  1.O usuário acessa http://localhost:4200.
  2.Digita o código do país no campo de pesquisa.
  3.Clica no botão "Pesquisar".
  4.Os dados são exibidos em uma tabela, ou um alerta é mostrado em caso de erro.

  ##Documentação da API com Swagger
    A documentação da API está disponível em:
    Swagger UI (http://localhost:8080/swagger-ui/index.html)

  ##Testes do Back-end
  O back-end possui testes implementados para garantir a funcionalidade e a integridade da API. Certifique-se de que os testes estão sendo executados e passando corretamente para garantir a qualidade da aplicação.

  ## Histórias de Usuário

### História de Usuário 1

**Como** usuário do front-end,  
**Eu quero** visualizar informações sobre um país,  
**Para que** eu possa consultar dados específicos de qualquer país.

#### Critérios de Aceitação

1. A interface deve permitir a entrada do código do país.
2. Após clicar em "Pesquisar", os dados devem ser exibidos em uma tabela.
3. Em caso de erro, um alerta deve ser mostrado ao usuário.

#### Cenários de Testes

1. **Entrada e Pesquisa**
   - Dado que o usuário está na página de pesquisa,
   - Quando o usuário digita um código de país válido e clica em "Pesquisar",
   - Então a tabela deve ser preenchida com os dados correspondentes.

2. **Erro de Pesquisa**
   - Dado que o usuário está na página de pesquisa,
   - Quando o usuário digita um código de país inválido e clica em "Pesquisar",
   - Então um alerta deve ser exibido informando sobre o erro.

3. **Interface de Pesquisa**
   - Dado que o usuário acessa a aplicação,
   - Quando a página é carregada,
   - Então o campo de entrada e o botão "Pesquisar" devem estar visíveis e utilizáveis.

4. **Responsividade**
   - Dado que o usuário redimensiona a tela,
   - Quando a tela é ajustada para diferentes tamanhos,
   - Então a aplicação deve se ajustar e permanecer utilizável.

5. **Acesso ao Endereço da API**
   - Dado que o back-end está em execução,
   - Quando o front-end faz uma solicitação ao endpoint `http://localhost:8080/api/indicador?codigoPais=br`,
   - Então a resposta deve conter dados válidos ou um código de erro apropriado.
