package com.nttdata.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutStepOnePage {

    public Locator firstNameInput;
    public Locator lastNameInput;
    public Locator postalCodeInput;
    public Locator continueButton;
    public Locator cancelButton;
    public Locator errorMessage;

    public CheckoutStepOnePage(Page page) {
        this.firstNameInput = page.locator("[data-test='firstName']");
        this.lastNameInput = page.locator("[data-test='lastName']");
        this.postalCodeInput = page.locator("[data-test='postalCode']");
        this.continueButton = page.locator("[data-test='continue']");
        this.cancelButton = page.locator("[data-test='cancel']");
        this.errorMessage = page.locator("h3[data-test='error']");
    }
}
