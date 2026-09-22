package com.nttdata.steps;

import com.microsoft.playwright.Page;
import com.nttdata.page.LoginPage;

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
