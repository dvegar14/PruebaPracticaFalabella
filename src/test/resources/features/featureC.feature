@featureC
Feature: Feature C

  @new @featureC @omit
  Scenario Outline:
    When se llama a /users, se obtiene userId de registro "<NumeroRegistro>" validando name y username
    Then se imprimen todos los datos asociados a userId
    Examples:
      |NumeroRegistro|
      |5             |
