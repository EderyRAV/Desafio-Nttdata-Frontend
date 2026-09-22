package com.nttdata.page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

/**
 * Page Object de la pantalla de login (https://www.saucedemo.com/).
 *
 * Un Page Object SOLO conoce los localizadores del HTML de esa página. No hace
 * asserts ni contiene lógica de "qué probar" — eso vive en la capa de Steps.
 * Si mañana Sauce Demo cambia un id/clase, este es el ÚNICO archivo que hay que tocar.
 */
public class LoginPage {

    public Locator userInput;
    public Locator passInput;
    public Locator loginButton;
    public Locator errorMessage;
    public Locator menuButton;
    public Locator logoutLink;

    public LoginPage(Page page) {
        this.userInput = page.locator("#user-name");
        this.passInput = page.locator("#password");
        this.loginButton = page.locator("#login-button");
        this.errorMessage = page.locator("h3[data-test='error']");
        // menú hamburguesa (arriba-izquierda) y el link "Logout" dentro de ese menú
        this.menuButton = page.locator("#react-burger-menu-btn");
        this.logoutLink = page.locator("[data-test='logout-sidebar-link']");
    }
}
