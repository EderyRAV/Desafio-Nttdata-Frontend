package com.nttdata.stepsdefinitions;
 
  
import com.nttdata.steps.CartSteps;
import com.nttdata.steps.InventorySteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
//sdfsdd
import org.junit.Assert;



import static com.nttdata.core.DriverManager.getPage;
import static com.nttdata.core.DriverManager.screenShot;

public class CarritoStepsDef {

private InventorySteps inventorySteps() {
return new InventorySteps(getPage());
}

private CartSteps cartSteps() {
return new CartSteps(getPage());
}
 


@Dado("agregué el producto {string} al carrito")
@Cuando("agrego el producto {string} al carrito")
public void agrego_el_producto_al_carrito(String nombreProducto) {
inventorySteps().agregarProducto(nombreProducto);
screenShot();
}

@Cuando("quito el producto {string} desde el listado de productos")
public void quito_el_producto_desde_el_listado_de_productos(String nombreProducto) {
    inventorySteps().quitarProducto(nombreProducto);
    screenShot();                    
}

@Cuando("quito el producto {string} desde el carrito")
public void quito_el_producto_desde_el_carrito(String nombreProducto) {
    cartSteps().quitarProducto(nombreProducto);  
    screenShot();
}

@Y("voy al carrito")
public void voy_al_carrito() {
    inventorySteps().irAlCarrito();
    screenShot();
}

@Entonces("el carrito debería contener el producto {string}")
public void el_carrito_deberia_contener_el_producto(String nombreProducto) {
    Assert.assertTrue(
            "No se encontró \"" + nombreProducto + "\" en el carrito",
            cartSteps().contieneProducto(nombreProducto)
    );
}

@Entonces("el contador del carrito debería mostrar {string}")
public void el_contador_del_carrito_deberia_mostrar(String cantidadEsperada) {
    Assert.assertEquals(cantidadEsperada, inventorySteps().obtenerContadorCarrito());
}

@Entonces("el contador del carrito debería estar vacío")
public void el_contador_del_carrito_deberia_estar_vacio() {
    Assert.assertEquals("", inventorySteps().obtenerContadorCarrito());
}

@Cuando("ordeno el catálogo por {string}")
public void ordeno_el_catalogo_por(String opcion) {
    inventorySteps().ordenarCatalogoPor(opcion);
    screenShot();
}

@Entonces("el primer producto del catálogo debería ser {string}")
public void el_primer_producto_del_catalogo_deberia_ser(String nombreEsperado) {
    Assert.assertEquals(nombreEsperado, inventorySteps().primerProducto());     
}

    @Entonces("el último producto del catálogo debería ser {string}")
    public void el_ultimo_producto_del_catalogo_deberia_ser(String nombreEsperado) {
        Assert.assertEquals(nombreEsperado, inventorySteps().ultimoProducto());
    }}














    