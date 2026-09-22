# language: es
# "@carrito" es un tag: sirve para poder correr SOLO este archivo con
# "mvn test -Dcucumber.filter.tags=@carrito" en vez de toda la suite.
@carrito
Característica: Carrito de compras
  Como cliente de Sauce Demo
  Quiero agregar y quitar productos del carrito
  Para poder armar mi pedido antes de pagar

  # "Antecedentes" (Background) se ejecuta ANTES de cada Escenario de este archivo.
  # Aquí garantiza que todo escenario arranca con una sesión iniciada, sin repetir
  # el login en cada uno.
  Antecedentes:
    Dado que inicié sesión como "standard_user"

  # @smoke: la prueba más básica posible — "¿la funcionalidad principal responde?".
  # No revisa detalles finos, solo que agregar un producto SÍ hace algo visible.
  @smoke
  Escenario: Agregar un producto actualiza el contador del carrito
    Cuando agrego el producto "Sauce Labs Backpack" al carrito
    Entonces el contador del carrito debería mostrar "1"

  # @happy-path: el camino feliz con varias acciones encadenadas — agregar 2
  # productos distintos y comprobar que AMBOS aparecen en el carrito (no solo el
  # contador, sino el contenido real).
  @happy-path
  Escenario: Agregar varios productos y verlos listados en el carrito
    Cuando agrego el producto "Sauce Labs Backpack" al carrito
    Y agrego el producto "Sauce Labs Bike Light" al carrito
    Y voy al carrito
    Entonces el carrito debería contener el producto "Sauce Labs Backpack"
    Y el carrito debería contener el producto "Sauce Labs Bike Light"
    Y el contador del carrito debería mostrar "2"

  # Se puede quitar un producto desde DOS lugares distintos de la app: el propio
  # listado de productos (sin entrar al carrito) y la página del carrito. Son dos
  # escenarios separados porque prueban dos botones distintos, aunque el
  # resultado final (carrito vacío) sea el mismo.
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

  # "Esquema del escenario" (Scenario Outline) es un escenario que se repite una
  # vez POR CADA FILA de la tabla "Ejemplos" de abajo, reemplazando cada <valor>
  # por el de esa fila. Sirve para no copiar/pegar el mismo escenario 4 veces
  # (uno por cada forma de ordenar el catálogo).
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

  # @unhappy-path: no es un dato mal escrito, es una REGLA DE NEGOCIO que se rompe
  # a propósito (intentar entrar a una página protegida sin haber iniciado sesión).
  # Verificado a mano en el navegador antes de escribirlo: la app SÍ redirige al
  # login y SÍ muestra este mensaje exacto (incluye la ruta a la que intentaste entrar).
  @unhappy-path
  Escenario: No se puede acceder al carrito sin haber iniciado sesión
    Dado que cerré sesión
    Cuando intento entrar directamente a la página del carrito
    Entonces debería ver el mensaje "Epic sadface: You can only access '/cart.html' when you are logged in."
