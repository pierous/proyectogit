/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.excepciones;


public class InstanciaNoEncontradaExcepcion extends Exception {
    
    
    /*--------------------------Constructores---------------------------------*/
    
    public InstanciaNoEncontradaExcepcion(Object elemento, String nombreAtributo, String nombreClase) {
        super("(Instancia no encontrada): No existe ningun " + nombreClase + " con " + nombreAtributo + " = " + elemento);
    }//fin InstanciaNoEncontradaExcepcion(Object clave, String nombreClase)
    
    public InstanciaNoEncontradaExcepcion(String nombreClase) {
        super("(Instancia no encontrada): No existe ningun " + nombreClase + " con los atributos descritos.");
    }//fin InstanciaNoEncontradaExcepcion(Object clave, String nombreClase)
    
    /*------------------------------------------------------------------------*/
    
    
}//fin clase InstanciaNoEncontradaExcepcion