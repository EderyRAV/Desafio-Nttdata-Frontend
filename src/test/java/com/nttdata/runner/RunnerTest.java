package com.nttdata.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Punto de entrada que Maven ejecuta con "mvn test" (Surefire detecta esta clase
 * porque JUnit4 la reconoce como test gracias a @RunWith(Cucumber.class)).
 *
 * - features: dónde están los .feature a ejecutar.
 * - glue: paquete(s) donde Cucumber busca las clases con @Dado/@Cuando/@Entonces y los
 *   hooks @Before/@After (por eso "com.nttdata" cubre stepsdefinitions Y core.DriverManager).
 * - plugin: dónde y en qué formato se generan los reportes.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.nttdata",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-report.html",
                "json:target/cucumber-reports/cucumber.json"
        }
)
public class RunnerTest {
}
