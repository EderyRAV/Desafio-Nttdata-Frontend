package com.nttdata.steps;

import com.microsoft.playwright.Page;
import com.nttdata.page.LoginPage;

/**
 * Capa de "acciones de negocio" sobre el LoginPage.
 *
 * Esta clase traduce intenciones ("escribir usuario", "hacer login") a interacciones
 * concretas con los Locator del Page Object. Los *StepsDef (glue de Cucumber) llaman
 * a estos metodos; nunca tocan un Locator directamente. Esta separación en 3 capas
 * (Page -> Steps -> StepsDef) es el patrón de diseño que pide el reto (Page Object Model).
 */
public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(Page page) {
        this.loginPage = new LoginPage(page);
    }

    public void typeUser(String user) {
        loginPage.userInput.fill(user);
    }

    public void typePassword(String password) {
        loginPage.passInput.fill(password);
    }

    public void login() {
        loginPage.loginButton.click();
    }

    /** Login "de un solo paso": usuario + password + click, para usar en Backgrounds. */
    public void loginCompleto(String usuario, String password) {
        typeUser(usuario);
        typePassword(password);
        login();
    }

    public void logout() {
        loginPage.menuButton.click();
        loginPage.logoutLink.click();
    }

    public boolean apareceMensajeError() {
        try {
            loginPage.errorMessage.waitFor();
            return loginPage.errorMessage.isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public String textoMensajeError() {
        return loginPage.errorMessage.textContent();
    }
}
