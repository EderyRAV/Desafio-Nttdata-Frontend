package com.nttdata.stepsdefinitions;

import com.nttdata.steps.InventorySteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import com.microsoft.playwright.Page;

import static com.nttdata.core.DriverManager.getPage;
import static com.nttdata.core.DriverManager.screenShot;

/**
 * "Glue" de Cucumber: conecta cada línea del .feature (en español) con código Java.
 * El texto entre las anotaciones (@Dado/@Cuando/@Entonces) tiene que calzar EXACTO
 * (salvo los {string}, que capturan lo que esté entre comillas en el Gherkin) con
 * la línea del Escenario.
 *
 * Esta clase NO interactúa con Locators directamente -> siempre delega en la capa
 * de Steps (LoginSteps/InventorySteps), que es la que sabe cómo usar el Page Object.
 */
public class LoginStepsDef {

    private Page page;

    private LoginSteps loginSteps() {
        return new LoginSteps(page);
    }

    private InventorySteps inventorySteps() {
        return new InventorySteps(page);
    }

    @Dado("que estoy en la página de login de Sauce Demo")
    public void que_estoy_en_la_pagina_de_login_de_sauce_demo() {
        page = getPage();
        page.navigate("https://www.saucedemo.com/");
        screenShot();
    }

    // Usado como Background en carrito.feature/checkout.feature: navega, llena
    // usuario/contraseña y hace click, todo en un solo step (esos features no
    // necesitan probar el login paso a paso, solo necesitan quedar autenticados).
    @Dado("que inicié sesión como {string}")
    public void que_inicie_sesion_como(String usuario) {
        page = getPage();
        page.navigate("https://www.saucedemo.com/");
        loginSteps().loginCompleto(usuario, "secret_sauce");
        screenShot();
    }

    @Dado("que cerré sesión")
    public void que_cerre_sesion() {
        loginSteps().logout();
        screenShot();
    }

    @Cuando("intento entrar directamente a la página del carrito")
    public void intento_entrar_directamente_a_la_pagina_del_carrito() {
        page.navigate("https://www.saucedemo.com/cart.html");
        screenShot();
    }

    @Cuando("inicio sesión con el usuario {string} y la contraseña {string}")
    public void inicio_sesion_con_el_usuario_y_la_contrasena(String usuario, String password) {
        loginSteps().typeUser(usuario);
        loginSteps().typePassword(password);
        loginSteps().login();
        screenShot();
    }

    @Entonces("debería ver el título {string}")
    public void deberia_ver_el_titulo(String tituloEsperado) {
        String titulo = inventorySteps().getTitle();
        Assert.assertEquals(tituloEsperado, titulo);
    }

    // Genérico para mensajes que NO son de un intento de login (ej. el guard de
    // ruta "Epic sadface: You can only access..."), pero usan el mismo banner rojo.
    @Entonces("debería ver el mensaje {string}")
    public void deberia_ver_el_mensaje(String mensajeEsperado) {
        Assert.assertEquals(mensajeEsperado, loginSteps().textoMensajeError());
    }

    @Entonces("debería ver el mensaje de error {string}")
    public void deberia_ver_el_mensaje_de_error(String mensajeEsperado) {
        boolean apareceError = loginSteps().apareceMensajeError();
        screenShot();
        Assert.assertTrue("No aparecio ningun mensaje de error", apareceError);
        Assert.assertEquals(mensajeEsperado, loginSteps().textoMensajeError());
    }
}
