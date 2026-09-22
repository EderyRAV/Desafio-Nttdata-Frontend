# language: es
@checkout
Característica: Checkout
  Como cliente de Sauce Demo
  Quiero completar el proceso de compra
  Para poder adquirir los productos que agregué al carrito

  Antecedentes:
    Dado que inicié sesión como "standard_user"
    Y agregué el producto "Sauce Labs Backpack" al carrito
    Y voy al carrito
    Y voy a pagar

  @fields
  Esquema del escenario: El formulario de datos personales exige los campos uno por uno
    Cuando lleno el formulario de checkout con nombre "<nombre>", apellido "<apellido>" y codigo postal "<codigo>"
    Y hago click en Continuar
    Entonces debería ver el error "<errorEsperado>"

    Ejemplos:
      | nombre | apellido | codigo | errorEsperado                  |
      |        |          |        | Error: First Name is required  |
      | Prueba |          |        | Error: Last Name is required   |
      | Prueba | QA       |        | Error: Postal Code is required |

  @happy-path
  Escenario: Completar los datos válidos muestra el resumen con el total correcto
    Cuando lleno el formulario de checkout con nombre "Prueba", apellido "QA" y codigo postal "11111"
    Y hago click en Continuar
    Entonces debería ver el método de pago "SauceCard #31337"
    Y debería ver el envío "Free Pony Express Delivery!"
    Y el total debería ser igual al subtotal más el impuesto

  @happy-path
  Escenario: Finalizar la compra muestra la confirmación y vacía el carrito
    Dado que lleno el formulario de checkout con nombre "Prueba", apellido "QA" y codigo postal "11111"
    Y hago click en Continuar
    Cuando hago click en Finish
    Entonces debería ver el mensaje de confirmación "Thank you for your order!"
    Y el contador del carrito debería estar vacío

  @happy-path
  Escenario: Cancelar en el formulario de datos personales regresa al carrito sin perder los productos
    Cuando cancelo el checkout
    Entonces debería estar en la página del carrito
    Y el contador del carrito debería mostrar "1"

  @happy-path
  Escenario: Cancelar en el resumen de la compra regresa al catálogo sin perder los productos
    Dado que lleno el formulario de checkout con nombre "Prueba", apellido "QA" y codigo postal "11111"
    Y hago click en Continuar
    Cuando cancelo el checkout
    Entonces debería estar en la página del catálogo
    Y el contador del carrito debería mostrar "1"
