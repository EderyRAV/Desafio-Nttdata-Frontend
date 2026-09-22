package com.nttdata.page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;
    public Locator checkoutButton;
    public Locator continueShoppingButton;

    public CartPage(Page page) {
        this.page = page;
        this.checkoutButton = page.locator("[data-test='checkout']");
        this.continueShoppingButton = page.locator("[data-test='continue-shopping']");
    }

    public Locator filaDelProducto(String nombreProducto) {
        return page.locator(".cart_item").filter(new Locator.FilterOptions().setHasText(nombreProducto));
    }

    public Locator botonQuitar(String nombreProducto) {
        return filaDelProducto(nombreProducto).locator("button");
    }
}
