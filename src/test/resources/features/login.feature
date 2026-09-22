# language: es
@login
Característica: Login en Sauce Demo
  Como cliente de Sauce Demo
  Quiero iniciar sesión con mis credenciales
  Para poder acceder al catálogo de productos

  Antecedentes:
    Dado que estoy en la página de login de Sauce Demo

  @smoke @happy-path
  Escenario: Inicio de sesión exitoso con un usuario estándar
    Cuando inicio sesión con el usuario "standard_user" y la contraseña "secret_sauce"
    Entonces debería ver el título "Products"

  @unhappy-path
  Escenario: Inicio de sesión rechazado con un usuario bloqueado
    Cuando inicio sesión con el usuario "locked_out_user" y la contraseña "secret_sauce"
    Entonces debería ver el mensaje de error "Epic sadface: Sorry, this user has been locked out."
