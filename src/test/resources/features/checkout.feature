# language: es
@checkout
Característica: Checkout
  Como cliente de Sauce Demo
  Quiero completar el proceso de compra
  Para poder adquirir los productos que agregué al carrito

  # Este Background deja al escenario YA PARADO en el paso 1 del checkout
  # (formulario de datos), con 1 producto en el carrito. Así cada Escenario de
  # abajo puede empezar directo en lo que realmente quiere probar, sin repetir
  # "login -> agregar producto -> ir al carrito -> ir a pagar" en cada uno.
  Antecedentes:
    Dado que inicié sesión como "standard_user"
    Y agregué el producto "Sauce Labs Backpack" al carrito
    Y voy al carrito
    Y voy a pagar

  # @fields: Sauce Demo valida el formulario CAMPO POR CAMPO, no todos a la vez.
  # Esto se descubrió probando a mano antes de automatizarlo: si el nombre está
  # vacío, ni siquiera revisa el apellido o el código postal. El Esquema del
  # escenario prueba esa progresión con 3 filas (falta nombre / falta apellido /
  # falta código postal), cada una esperando un mensaje de error distinto.
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

  # Este @happy-path no solo revisa que aparezcan los datos del resumen, sino que
  # el TOTAL esté bien calculado: Total = subtotal + impuesto. Es una prueba de
  # contenido más fuerte que "el texto existe" — valida la regla de negocio real
  # detrás del número.
  @happy-path
  Escenario: Completar los datos válidos muestra el resumen con el total correcto
    Cuando lleno el formulario de checkout con nombre "Prueba", apellido "QA" y codigo postal "11111"
    Y hago click en Continuar
    Entonces debería ver el método de pago "SauceCard #31337"
    Y debería ver el envío "Free Pony Express Delivery!"
    Y el total debería ser igual al subtotal más el impuesto

  # Verifica el efecto secundario de terminar la compra: el mensaje de éxito Y
  # que el carrito quede vacío. Es fácil escribir solo la primera aserción y
  # olvidarse de la segunda — por eso se deja como recordatorio explícito.
  @happy-path
  Escenario: Finalizar la compra muestra la confirmación y vacía el carrito
    Dado que lleno el formulario de checkout con nombre "Prueba", apellido "QA" y codigo postal "11111"
    Y hago click en Continuar
    Cuando hago click en Finish
    Entonces debería ver el mensaje de confirmación "Thank you for your order!"
    Y el contador del carrito debería estar vacío

  # "Cancel" existe en dos pasos distintos del checkout y hace cosas DIFERENTES
  # en cada uno (verificado a mano): desde el paso 1 vuelve al carrito, desde el
  # paso 2 vuelve al catálogo. En ambos casos el carrito NO se vacía — cancelar
  # no debería perder lo que el cliente ya eligió.
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
