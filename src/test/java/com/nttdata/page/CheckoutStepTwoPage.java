package com.nttdata.page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

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
