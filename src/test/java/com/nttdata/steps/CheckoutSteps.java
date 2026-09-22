package com.nttdata.steps;

import com.microsoft.playwright.Page;
import com.nttdata.page.CheckoutCompletePage;
import com.nttdata.page.CheckoutStepOnePage;
import com.nttdata.page.CheckoutStepTwoPage;

/**
 * El checkout de Sauce Demo son 3 páginas distintas (paso 1: datos, paso 2:
 * resumen, paso 3: confirmación) pero, para quien escribe el .feature, es UN
 * solo flujo ("pagar"). Por eso esta clase agrupa los 3 Page Objects: quien
 * escribe un StepsDef no necesita saber en cuál de las 3 páginas está parado,
 * solo llama al método que representa la acción de negocio.
 */
public class CheckoutSteps {

    private final CheckoutStepOnePage stepOne;
    private final CheckoutStepTwoPage stepTwo;
    private final CheckoutCompletePage complete;

    public CheckoutSteps(Page page) {
        this.stepOne = new CheckoutStepOnePage(page);
        this.stepTwo = new CheckoutStepTwoPage(page);
        this.complete = new CheckoutCompletePage(page);
    }

    // ---- Paso 1: datos personales ----

    public void llenarFormulario(String nombre, String apellido, String codigoPostal) {
        // .fill("") en un campo vacio es intencional: asi se prueba el caso
        // "campo requerido" sin tener que escribir un step distinto para "vacio".
        stepOne.firstNameInput.fill(nombre);
        stepOne.lastNameInput.fill(apellido);
        stepOne.postalCodeInput.fill(codigoPostal);
    }

    public void continuar() {
        stepOne.continueButton.click();
    }

    public String obtenerError() {
        return stepOne.errorMessage.textContent();
    }

    // ---- Cancelar (el boton existe igual en el paso 1 y en el paso 2) ----

    public void cancelar() {
        if (stepOne.cancelButton.isVisible()) {
            stepOne.cancelButton.click();
        } else {
            stepTwo.cancelButton.click();
        }
    }

    // ---- Paso 2: resumen (Overview) ----

    public String metodoDePago() {
        return stepTwo.paymentInfo.textContent();
    }

    public String informacionDeEnvio() {
        return stepTwo.shippingInfo.textContent();
    }

    /** Extrae el monto de un texto tipo "Tax: $2.40" -> 2.40 (double). */
    private double extraerMonto(String textoConEtiqueta) {
        String soloNumero = textoConEtiqueta.replaceAll("[^0-9.]", "");
        return Double.parseDouble(soloNumero);
    }

    public double subtotal() {
        return extraerMonto(stepTwo.subtotalLabel.textContent());
    }

    public double impuesto() {
        return extraerMonto(stepTwo.taxLabel.textContent());
    }

    public double total() {
        return extraerMonto(stepTwo.totalLabel.textContent());
    }

    public void finalizarCompra() {
        stepTwo.finishButton.click();
    }

    // ---- Paso 3: confirmación ----

    public String mensajeConfirmacion() {
        return complete.header.textContent();
    }
}
