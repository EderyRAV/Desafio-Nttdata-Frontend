# language: es
@carrito
Característica: Carrito de compras
  Como cliente de Sauce Demo
  Quiero agregar y quitar productos del carrito
  Para poder armar mi pedido antes de pagar

  Antecedentes:
    Dado que inicié sesión como "standard_user"

  @smoke
  Escenario: Agregar un producto actualiza el contador del carrito
    Cuando agrego el producto "Sauce Labs Backpack" al carrito
    Entonces el contador del carrito debería mostrar "1"

  @happy-path
  Escenario: Agregar varios productos y verlos listados en el carrito
    Cuando agrego el producto "Sauce Labs Backpack" al carrito
    Y agrego el producto "Sauce Labs Bike Light" al carrito
    Y voy al carrito
    Entonces el carrito debería contener el producto "Sauce Labs Backpack"
    Y el carrito debería contener el producto "Sauce Labs Bike Light"
    Y el contador del carrito debería mostrar "2"

  @happy-path
  Escenario: Quitar un producto desde el listado de productos
    Dado agregué el producto "Sauce Labs Backpack" al carrito
    Cuando quito el producto "Sauce Labs Backpack" desde el listado de productos
    Entonces el contador del carrito debería estar vacío

  @happy-path
  Escenario: Quitar un producto desde la página del carrito
    Dado agregué el producto "Sauce Labs Backpack" al carrito
    Y voy al carrito
    Cuando quito el producto "Sauce Labs Backpack" desde el carrito
    Entonces el contador del carrito debería estar vacío

  @data-content
  Esquema del escenario: Ordenar el catálogo cambia el orden de los productos
    Cuando ordeno el catálogo por "<opcion>"
    Entonces el primer producto del catálogo debería ser "<primero>"
    Y el último producto del catálogo debería ser "<ultimo>"

    Ejemplos:
      | opcion               | primero                            | ultimo                              |
      | Name (A to Z)        | Sauce Labs Backpack                | Test.allTheThings() T-Shirt (Red)   |
      | Name (Z to A)        | Test.allTheThings() T-Shirt (Red)  | Sauce Labs Backpack                 |
      | Price (low to high)  | Sauce Labs Onesie                  | Sauce Labs Fleece Jacket            |
      | Price (high to low)  | Sauce Labs Fleece Jacket           | Sauce Labs Onesie                   |

  @unhappy-path
  Escenario: No se puede acceder al carrito sin haber iniciado sesión
    Dado que cerré sesión
    Cuando intento entrar directamente a la página del carrito
    Entonces debería ver el mensaje "Epic sadface: You can only access '/cart.html' when you are logged in."
