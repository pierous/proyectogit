/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.excepciones;


public class InstanciaDuplicadaExcepcion extends Exception {
    
    
    /*--------------------------Constructores---------------------------------*/
    
    public InstanciaDuplicadaExcepcion(Object elemento, String nombreAtributo, String nombreClase) {
        super("(Instancia Duplicada): Ya existe un " + nombreClase + " con el " + nombreAtributo + " = " + elemento);
    }//fin InstanciaDuplicadaExcepcion(Object clave, String nombreClase)
    
    /*------------------------------------------------------------------------*/
    
    
}//fin clase InstanciaDuplicadaExcepcion