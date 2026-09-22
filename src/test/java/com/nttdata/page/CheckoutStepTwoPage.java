package com.nttdata.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page Object del PASO 2 del checkout (/checkout-step-two.html): el "Overview"
 * (resumen) que se ve justo antes de confirmar la compra. Aquí es donde se puede
 * validar que el total cobrado sea matemáticamente correcto (subtotal + impuesto).
 *
 * Los textos vienen con etiqueta incluida (ej. "Tax: $2.40", no solo "2.40"),
 * así que la conversión a número se hace en la capa de Steps, no aquí — este
 * Page Object solo expone los Locators "en crudo".
 */
public class CheckoutStepTwoPage {

    public Locator paymentInfo;
    public Locator shippingInfo;
    public Locator subtotalLabel;
    public Locator taxLabel;
    public Locator totalLabel;
    public Locator finishButton;
    public Locator cancelButton;

    public CheckoutStepTwoPage(Page page) {
        this.paymentInfo = page.locator("[data-test='payment-info-value']");
        this.shippingInfo = page.locator("[data-test='shipping-info-value']");
        this.subtotalLabel = page.locator("[data-test='subtotal-label']");
        this.taxLabel = page.locator("[data-test='tax-label']");
        this.totalLabel = page.locator("[data-test='total-label']");
        this.finishButton = page.locator("[data-test='finish']");
        this.cancelButton = page.locator("[data-test='cancel']");
    }
}
