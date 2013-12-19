/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.valor;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;

/*----------------------------------------------------------------------------*/


public class Valor implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private Double dato;
    private Parametro tipo;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Valor() {}//fin Valor()

    public Valor(Double dato, Parametro tipo) {
        this.dato = dato;
        this.tipo = tipo;
    }//fin Valor(Double dato, Parametro tipo)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    @Override
    public Long getClave() {
        return clave;
    }//fin getClave()
    @Override
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setClave(Long clave)
    
    public Double getDato() {
        return dato;
    }//fin getDato()
    public void setDato(Double dato) {
        this.dato = dato;
    }//fin setDato(Double dato)
    
    public Parametro getTipo() {
        return tipo;
    }//fin getTipo()
    public void setTipo(Parametro tipo) {
        this.tipo = tipo;
    }//fin setTipo(Parametro tipo)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    public String toString() {
        return (clave.toString());
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase Valor