package com.nttdata.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page Object del PASO 1 del checkout (/checkout-step-one.html): el formulario
 * de "Nombre / Apellido / Código postal". Sauce Demo valida estos 3 campos UNO A
 * LA VEZ (si falta el nombre, ni siquiera revisa el apellido) — por eso el
 * mensaje de error reutiliza el mismo locator que el login (h3[data-test='error']),
 * es el mismo componente de UI en toda la app.
 */
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
