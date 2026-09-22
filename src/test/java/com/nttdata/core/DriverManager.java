package com.nttdata.core;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class DriverManager {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;
    private static Scenario scenario;

    public static Page getPage() {
        return page;
    }

    @Before(order = 0)
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        context = browser.newContext();
        page = context.newPage();
    }

    @Before(order = 1)
    public void setScenario(Scenario scenario) {
        DriverManager.scenario = scenario;
    }

    @After
    public void quitDriver() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public static void screenShot() {
        if (scenario != null && page != null) {
            byte[] evidencia = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(evidencia, "image/png", "evidencia");
        }
    }
}
