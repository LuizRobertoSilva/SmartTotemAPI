Feature: Testar as funcionalidades principais da tela de Sensores

  Scenario: Tentar realizar o cadastro de um sensor que já existe no sistema
    Given Que o usuario esta na url http://localhost:4200/
    And Que é armazenado o numero de sensores cadastrado
    When O usuario preencher o campo Name com o valor Teste A
    And  O usuario preencher o campo Region com o valor Regiao A
    And  O usuario preencher o campo Latitude com o valor 11
    And  O usuario preencher o campo Longitude com o valor 21
    And  O usuario clicar no botão Criar Device
    Then Nenhum valor deve ter sido adicionado na tabela

  Scenario: Tentar realizar o cadastro de um sensor sem preencher nenhum campo
    Given Que o usuario esta na url http://localhost:4200/
    And Que é armazenado o numero de sensores cadastrado
    When O usuario clicar no botão Criar Device
    Then Nenhum valor deve ter sido adicionado na tabela

    Scenario: Validar o Cadastro de um sensor
      Given Que o usuario esta na url http://localhost:4200/
      When O usuario preencher o campo Name com o valor Teste1
        And  O usuario preencher o campo Region com o valor Regiao1
        And  O usuario preencher o campo Latitude com o valor 11
        And  O usuario preencher o campo Longitude com o valor 21
       And  O usuario clicar no botão Criar Device
      Then Um device com nome Teste1, Regiao Regiao1, Latitude/Longitude 11, 21 foi cadastrado no sistema





