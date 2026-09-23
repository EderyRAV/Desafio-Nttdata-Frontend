# Desafio-Nttdata-Frontend

Suite de pruebas automatizadas con **Playwright (Java) + Cucumber-JVM** para [Sauce Demo](https://www.saucedemo.com/), usando Page Object Model.

## Instalación

Instalar los navegadores de Playwright (una sola vez):

```bash
mvn exec:java@install-playwright-browsers
```

## Ejecutar las pruebas

```bash
mvn clean test
```

Para correr solo un archivo:

```bash
mvn test -Dcucumber.filter.tags="@checkout"
```

## Resultados

Reporte HTML en `target/cucumber-reports/cucumber-report.html`.
