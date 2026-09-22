package com.nttdata.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutCompletePage {

    public Locator header;
    public Locator text;

    public CheckoutCompletePage(Page page) {
        this.header = page.locator("[data-test='complete-header']");
        this.text = page.locator("[data-test='complete-text']");
    }
}
