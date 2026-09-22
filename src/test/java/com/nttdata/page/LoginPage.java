package com.nttdata.page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

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
        this.menuButton = page.locator("#react-burger-menu-btn");
        this.logoutLink = page.locator("[data-test='logout-sidebar-link']");
    }
}
