package com.nttdata.steps;

import com.microsoft.playwright.Page;
import com.nttdata.page.CheckoutCompletePage;
import com.nttdata.page.CheckoutStepOnePage;
import com.nttdata.page.CheckoutStepTwoPage;

public class CheckoutSteps {

    private final CheckoutStepOnePage stepOne;
    private final CheckoutStepTwoPage stepTwo;
    private final CheckoutCompletePage complete;

    public CheckoutSteps(Page page) {
        this.stepOne = new CheckoutStepOnePage(page);
        this.stepTwo = new CheckoutStepTwoPage(page);
        this.complete = new CheckoutCompletePage(page);
    }

    public void llenarFormulario(String nombre, String apellido, String codigoPostal) {
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

    public void cancelar() {
        if (stepOne.cancelButton.isVisible()) {
            stepOne.cancelButton.click();
        } else {
            stepTwo.cancelButton.click();
        }
    }

    public String metodoDePago() {
        return stepTwo.paymentInfo.textContent();
    }

    public String informacionDeEnvio() {
        return stepTwo.shippingInfo.textContent();
    }

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

    public String mensajeConfirmacion() {
        return complete.header.textContent();
    }
}
