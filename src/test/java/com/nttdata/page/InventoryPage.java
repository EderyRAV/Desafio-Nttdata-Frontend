package com.nttdata.page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

/**
 * Page Object de la página de catálogo (/inventory.html), a donde redirige un login exitoso.
 */
public class InventoryPage {

    private final Page page;

    public Locator title;
    public Locator sortDropdown;
    public Locator cartLink;
    public Locator cartBadge;
    public Locator productNames;
    public Locator productPrices;

    public InventoryPage(Page page) {
        this.page = page;

        // "[data-test='title']" en vez de ".title": los atributos data-test están pensados
        // para automatización y no cambian si el equipo de UI retoca estilos (clases CSS).
        this.title = page.locator("[data-test='title']");

        // Combo de ordenamiento: "Name (A to Z)", "Name (Z to A)", "Price (low to high)", "Price (high to low)"
        this.sortDropdown = page.locator("[data-test='product-sort-container']");

        this.cartLink = page.locator("[data-test='shopping-cart-link']");
        this.cartBadge = page.locator(".shopping_cart_badge");

        // Colecciones para leer el catálogo completo (ej. verificar el orden tras ordenar)
        this.productNames = page.locator(".inventory_item_name");
        this.productPrices = page.locator(".inventory_item_price");
    }

    /**
     * Cada tarjeta de producto (.inventory_item) tiene UN SOLO botón que dice
     * "Add to cart" o "Remove" según si ya está en el carrito. En vez de tener un
     * Locator fijo por cada uno de los 6 productos, se busca en tiempo de ejecución
     * la tarjeta que contiene el nombre indicado (filter + hasText) y se toma su
     * botón — así el mismo método funciona para cualquier producto del catálogo.
     */
    public Locator botonAgregarOQuitar(String nombreProducto) {
        return page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(nombreProducto))
                .locator("button");
    }
}
