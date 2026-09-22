# Desafio-Nttdata-Frontend

Suite de pruebas automatizadas para [Sauce Demo](https://www.saucedemo.com/) usando **Playwright (binding Java) + Cucumber-JVM + Maven**, con el patrón **Page Object Model**.

## Requisitos

- Java 17+
- Maven

## Instalación

1. Clonar el repo.
2. Instalar los navegadores que usa Playwright (paso único, no corre solo con `mvn test`):

   ```bash
   mvn exec:java@install-playwright-browsers
   ```

   Descarga Chromium, Firefox y WebKit a `~/AppData/Local/ms-playwright` (o `~/.cache/ms-playwright` en Linux/Mac). Solo hace falta repetirlo si se borra ese cache o se sube la versión de `playwright.version` en el `pom.xml`.

## Ejecutar las pruebas

```bash
mvn clean test
```

Mismo comando que en `Desafio-Nttdata-Backend` (también es un proyecto Maven), pero **el motor de pruebas es distinto**: ahí era Karate, acá es Cucumber-JVM + JUnit4 sobre Playwright. Diferencias que importan:

| | Backend (Karate) | Frontend (este repo) |
|---|---|---|
| Runner | `com.intuit.karate.junit5.Karate` (JUnit5) | `io.cucumber.junit.Cucumber` (JUnit4) |
| Reportes | `target/karate-reports/` | `target/cucumber-reports/cucumber-report.html` y `cucumber.json` |
| Filtrar por tag | `-Dkarate.options="--tags @x"` | `-Dcucumber.filter.tags="@x"` |

Por defecto se ejecutan TODOS los `.feature` bajo `src/test/resources/features`. Para correr solo uno:

```bash
mvn test -Dcucumber.filter.tags="@login"
```

El navegador se abre visible (`setHeadless(false)` en `DriverManager`) para que puedas ver la prueba corriendo. Para modo headless (ej. en CI), cambiar ese flag a `true`.

## Estructura del proyecto

```
src/test/java/com/nttdata/
  core/               -> DriverManager: ciclo de vida de Playwright (abre/cierra navegador por escenario, capturas)
  page/                -> Page Objects: SOLO localizadores del HTML de cada página
  steps/               -> Acciones de negocio sobre un Page Object (ej. "hacer login")
  stepsdefinitions/    -> Glue de Cucumber: conecta el Gherkin (@Dado/@Cuando/@Entonces) con las clases de steps
  runner/              -> RunnerTest: punto de entrada que Maven ejecuta con "mvn test"
src/test/resources/features/
  login.feature         -> Escenarios en español (Gherkin)
```

La separación en 3 capas (`page` -> `steps` -> `stepsdefinitions`) es el patrón Page Object Model que pide el reto: si Sauce Demo cambia un selector, solo se toca el Page Object correspondiente.

## Estado actual

**Los 5 criterios de aceptación del reto están cubiertos**: login (válido/inválido), agregar/quitar del carrito, ver el carrito, completar la compra. 18 escenarios, `mvn clean test` → BUILD SUCCESS.

| Feature | Qué prueba |
|---|---|
| `login.feature` | Login válido (`standard_user`) y bloqueado (`locked_out_user`) |
| `carrito.feature` | Agregar/quitar productos (desde el listado y desde el carrito), contenido del carrito, orden del catálogo (4 modos), acceso al carrito sin sesión |
| `checkout.feature` | Validación del formulario campo por campo, cálculo del total (subtotal + impuesto), confirmación de compra + carrito vacío, `Cancel` en los dos pasos del checkout |

Cada `.feature` tiene comentarios (`#`) explicando qué prueba cada bloque y por qué, pensados para alguien sin experiencia previa en Cucumber/Gherkin.

## Créditos

Estructura basada en el repositorio de referencia del capacitador anterior ([uriberodrigo035/DesafioFrontend](https://github.com/uriberodrigo035/DesafioFrontend)), adaptada y comentada como material de aprendizaje.
