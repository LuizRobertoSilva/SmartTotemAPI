Feature: Testar as funcionalidades principais da tela

    Scenario: Validar o Cadastro de um sensor
      Given Que o usuario esta na url http://localhost:4200/
      When O usuario preencher o capo Name com o valor Teste3
        And  O usuario preencher o capo Region com o valor Regiao3
        And  O usuario preencher o capo Latitude com o valor 11
        And  O usuario preencher o capo Longitude com o valor 21
       And  O usuario clicar no botão Criar Device
      Then Um device com nome Teste3, Regiao Regiao3, Latitude 11 e Longitude 21 foi cadastrado no sistema