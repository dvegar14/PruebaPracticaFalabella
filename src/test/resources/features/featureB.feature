Feature: Feature B

  @debug @featureB @omit
  Scenario Outline:
    When se llama a /users obtienendo userId de registro num "<NumeroRegistro>" validando name y username
    And se llama a /posts validando title y body desde respuesta, consulta con userID e imprime ultimo post
    Then se llama a /comments obteniendo registros de postID, validando email y name del primero
    Examples:
      |NumeroRegistro|
      |1             |
