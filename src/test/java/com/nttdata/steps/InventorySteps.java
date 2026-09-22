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

public String obtenerContadorCarrito() {
    if (inventoryPage.cartBadge.count() == 0) {
        return "";
    }
    return inventoryPage.cartBadge.textContent();
}
}
