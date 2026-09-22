package com.nttdata.steps;

import com.microsoft.playwright.Page;
import com.nttdata.page.CartPage;

public class CartSteps {

    private final CartPage cartPage;

    public CartSteps(Page page) {
        this.cartPage = new CartPage(page);
    }

    public boolean contieneProducto(String nombreProducto) {
        return cartPage.filaDelProducto(nombreProducto).isVisible();
    }

    public void quitarProducto(String nombreProducto) {
        cartPage.botonQuitar(nombreProducto).click();
    }

    public void irAPagar() {
        cartPage.checkoutButton.click();
    }
}
