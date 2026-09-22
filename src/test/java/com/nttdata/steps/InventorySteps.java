package com.nttdata.steps;

import com.microsoft.playwright.Page;
import com.nttdata.page.InventoryPage;

import java.util.List;

public class InventorySteps {

    private final Page page;
    private final InventoryPage inventoryPage;

    public InventorySteps(Page page) {
        this.page = page;
        this.inventoryPage = new InventoryPage(page);
    }

    public String getTitle() {
        return inventoryPage.title.textContent();
    }

    public void agregarProducto(String nombreProducto) {
        inventoryPage.botonAgregarOQuitar(nombreProducto).click();
    }

    public void quitarProducto(String nombreProducto) {
        inventoryPage.botonAgregarOQuitar(nombreProducto).click();
    }

    public void irAlCarrito() {
        inventoryPage.cartLink.click();
    }

    /**
     * Traduce la etiqueta visible del combo (la que aparece en el Gherkin, en
     * español legible) al "value" real del <option> en el HTML. Mantener esta
     * traducción en un solo lugar evita repetir los 4 valores en cada step.
     */
    public void ordenarCatalogoPor(String etiquetaVisible) {
        String value = switch (etiquetaVisible) {
            case "Name (A to Z)" -> "az";
            case "Name (Z to A)" -> "za";
            case "Price (low to high)" -> "lohi";
            case "Price (high to low)" -> "hilo";
            default -> throw new IllegalArgumentException("Opción de orden desconocida: " + etiquetaVisible);
        };
        inventoryPage.sortDropdown.selectOption(value);
    }

    public String primerProducto() {
        List<String> nombres = inventoryPage.productNames.allTextContents();
        return nombres.get(0);
    }

    public String ultimoProducto() {
        List<String> nombres = inventoryPage.productNames.allTextContents();
        return nombres.get(nombres.size() - 1);
    }

    /**
     * Lee el numerito rojo del carrito ("shopping_cart_badge"). Sauce Demo NO
     * renderiza ese <span> cuando el carrito está vacío (no es que diga "0"),
     * por eso "obtenerContadorCarrito" devuelve "" en ese caso en vez de fallar.
     */
    public String obtenerContadorCarrito() {
        if (inventoryPage.cartBadge.count() == 0) {
            return "";
        }
        return inventoryPage.cartBadge.textContent();
    }
}
