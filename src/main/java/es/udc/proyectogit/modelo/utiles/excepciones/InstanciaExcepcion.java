/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.excepciones;


public abstract class InstanciaExcepcion extends Exception {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    private Object clave;
    private String nombreClase;
    
    /*------------------------------------------------------------------------*/
    
    
    /*--------------------------Constructores---------------------------------*/
    
    protected InstanciaExcepcion(String mensajeEspecifico, Object clave, String nombreClase) {       
        super(mensajeEspecifico + " (clave = '" + clave + "' - nombreClase = '" + nombreClase + "')");
        this.clave = clave;
        this.nombreClase = nombreClase;       
    }//fin InstanciaExcepcion(String mensajeEspecifico, Object clave, String nombreClase)
    
    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Metodos------------------------------------*/
    
    public Object getClave() {
        return clave;
    }//fin getClave()
    
    
    public String getNombreClase() {
        return nombreClase;
    }//fin getNombreClase()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin clase InstanciaExcepcion