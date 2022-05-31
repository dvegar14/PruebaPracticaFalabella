Feature: Feature A

  @ready @featureA
  Scenario Outline:
    When se llama a /users y se obtiene userId en posicion "<NumeroRegistro>"
    Then se llama a /posts y se valida title y body desde respuesta consultando con userID
    Examples:
      |NumeroRegistro|
      |10            |
