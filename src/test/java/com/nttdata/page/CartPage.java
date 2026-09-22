package com.nttdata.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page Object de la página del carrito (/cart.html).
 *
 * Nota sobre "productPor(nombre)" y "quitarBotonPor(nombre)": en vez de tener un
 * Locator fijo por cada producto (habría que hardcodear 6 productos), se usa
 * page.locator(...).filter(hasText:...) para encontrar EN TIEMPO DE EJECUCIÓN la
 * fila (.cart_item) que contiene el nombre del producto que nos interesa. Así el
 * mismo método sirve para cualquier producto del catálogo, sin repetir código.
 */
public class CartPage {

    private final Page page;
    public Locator checkoutButton;
    public Locator continueShoppingButton;

    public CartPage(Page page) {
        this.page = page;
        this.checkoutButton = page.locator("[data-test='checkout']");
        this.continueShoppingButton = page.locator("[data-test='continue-shopping']");
    }

    /** Fila completa del carrito (.cart_item) que contiene el producto indicado. */
    public Locator filaDelProducto(String nombreProducto) {
        return page.locator(".cart_item").filter(new Locator.FilterOptions().setHasText(nombreProducto));
    }

    /** Botón "Remove" DENTRO de la fila de ese producto especifico. */
    public Locator botonQuitar(String nombreProducto) {
        return filaDelProducto(nombreProducto).locator("button");
    }
}
