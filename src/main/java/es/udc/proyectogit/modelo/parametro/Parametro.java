/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.parametro;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.entidad.Entidad;

/*----------------------------------------------------------------------------*/


public class Parametro implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private String nombre;
    private String unidad;
    private String unidadAbreviada;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Parametro() {}//fin Parametro()

    public Parametro(String nombre, String unidad, String unidadAbreviada) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.unidadAbreviada = unidadAbreviada;
    }//fin Parametro(String nombre, String unidad)

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
    
    public String getNombre() {
        return nombre;
    }//fin getNombre()
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }//fin setNombre()

    public String getUnidad() {
        return unidad;
    }//fin getUnidad()
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }//fin setUnidad()
    
    public String getUnidadAbreviada() {
        return unidadAbreviada;
    }//fin getUnidad()
    public void setUnidadAbreviada(String unidadAbreviada) {
        this.unidadAbreviada = unidadAbreviada;
    }//fin setUnidad()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parametro other = (Parametro) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.unidad == null) ? (other.unidad != null) : !this.unidad.equals(other.unidad)) {
            return false;
        }
        return true;
    }//fin equals(Object obj)


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 79 * hash + (this.unidad != null ? this.unidad.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return (nombre + " " + unidad);
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase Parametro