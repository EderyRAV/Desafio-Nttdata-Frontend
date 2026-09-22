package com.nttdata.core;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Administra el ciclo de vida de Playwright para cada escenario de Cucumber.
 *
 * Cucumber instancia esta clase (y cualquier otra clase con @Before/@After en el
 * "glue path") una vez por escenario, así que cada Escenario del .feature arranca
 * con un navegador limpio y lo cierra al terminar (aislamiento entre pruebas).
 */
public class DriverManager {

    // static: se comparte entre esta clase y las *StepsDef que llaman a getPage()
    // dentro del mismo escenario (Cucumber crea una instancia nueva de cada clase
    // de step definitions por escenario, por eso no se puede pasar por constructor).
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;
    private static Scenario scenario;

    public static Page getPage() {
        return page;
    }

    // order = 0 corre antes que cualquier otro @Before: sin esto no hay "page" para nada.
    @Before(order = 0)
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) // true para correr sin ver el navegador (ej. en CI)
        );
        context = browser.newContext();
        page = context.newPage();
    }

    // guarda la referencia al Scenario actual para poder adjuntarle capturas (screenShot())
    @Before(order = 1)
    public void setScenario(Scenario scenario) {
        DriverManager.scenario = scenario;
    }

    // se ejecuta SIEMPRE al terminar el escenario (pase o falle), por eso el cierre de
    // recursos va aqui y no en cada step: evita procesos de navegador huerfanos.
    @After
    public void quitDriver() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    /**
     * Adjunta una captura de pantalla al reporte de Cucumber (aparece embebida en el
     * cucumber-report.html). Util para dejar evidencia en pasos clave, no en todos.
     */
    public static void screenShot() {
        if (scenario != null && page != null) {
            byte[] evidencia = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(evidencia, "image/png", "evidencia");
        }
    }
}
