package com.nttdata.page;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

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
        this.title = page.locator("[data-test='title']");
        this.sortDropdown = page.locator("[data-test='product-sort-container']");
        this.cartLink = page.locator("[data-test='shopping-cart-link']");
        this.cartBadge = page.locator(".shopping_cart_badge");
        this.productNames = page.locator(".inventory_item_name");
        this.productPrices = page.locator(".inventory_item_price");
    }

    public Locator botonAgregarOQuitar(String nombreProducto) {
        return page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(nombreProducto))
                .locator("button");
    }
}
