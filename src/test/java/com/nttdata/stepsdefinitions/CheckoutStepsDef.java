package com.nttdata.stepsdefinitions;

import com.nttdata.steps.CartSteps;
import com.nttdata.steps.CheckoutSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;

import static com.nttdata.core.DriverManager.getPage;
import static com.nttdata.core.DriverManager.screenShot;

public class CheckoutStepsDef {

    private CheckoutSteps checkoutSteps() {
        return new CheckoutSteps(getPage());
    }

    private CartSteps cartSteps() {
        return new CartSteps(getPage());
    }

    @Y("voy a pagar")
    public void voy_a_pagar() {
        cartSteps().irAPagar();
        screenShot();
    }

    @Dado("que lleno el formulario de checkout con nombre {string}, apellido {string} y codigo postal {string}")
    @Cuando("lleno el formulario de checkout con nombre {string}, apellido {string} y codigo postal {string}")
    public void lleno_el_formulario_de_checkout(String nombre, String apellido, String codigoPostal) {
        checkoutSteps().llenarFormulario(nombre, apellido, codigoPostal);
        screenShot();
    }

    @Y("hago click en Continuar")
    public void hago_click_en_continuar() {
        checkoutSteps().continuar();
        screenShot();
    }

    @Entonces("debería ver el error {string}")
    public void deberia_ver_el_error(String errorEsperado) {
        Assert.assertEquals(errorEsperado, checkoutSteps().obtenerError());
    }

    @Entonces("debería ver el método de pago {string}")
    public void deberia_ver_el_metodo_de_pago(String esperado) {
        Assert.assertEquals(esperado, checkoutSteps().metodoDePago());
    }

    @Y("debería ver el envío {string}")
    public void deberia_ver_el_envio(String esperado) {
        Assert.assertEquals(esperado, checkoutSteps().informacionDeEnvio());
    }

    @Y("el total debería ser igual al subtotal más el impuesto")
    public void el_total_deberia_ser_igual_al_subtotal_mas_el_impuesto() {
        double subtotal = checkoutSteps().subtotal();
        double impuesto = checkoutSteps().impuesto();
        double total = checkoutSteps().total();
        Assert.assertEquals(subtotal + impuesto, total, 0.01);
    }

    @Cuando("hago click en Finish")
    public void hago_click_en_finish() {
        checkoutSteps().finalizarCompra();
        screenShot();
    }

    @Entonces("debería ver el mensaje de confirmación {string}")
    public void deberia_ver_el_mensaje_de_confirmacion(String mensajeEsperado) {
        Assert.assertEquals(mensajeEsperado, checkoutSteps().mensajeConfirmacion());
    }

    @Cuando("cancelo el checkout")
    public void cancelo_el_checkout() {
        checkoutSteps().cancelar();
        screenShot();
    }

    @Entonces("debería estar en la página del carrito")
    public void deberia_estar_en_la_pagina_del_carrito() {
        Assert.assertTrue(getPage().url().endsWith("/cart.html"));
    }

    @Entonces("debería estar en la página del catálogo")
    public void deberia_estar_en_la_pagina_del_catalogo() {
        Assert.assertTrue(getPage().url().endsWith("/inventory.html"));
    }
}
