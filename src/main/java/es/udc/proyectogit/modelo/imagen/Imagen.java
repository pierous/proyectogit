/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.imagen;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;

/*----------------------------------------------------------------------------*/


public class Imagen implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private String imagenUrl;
    private Prueba prueba;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Imagen() {};

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

    public String getImagenUrl() {
        return imagenUrl;
    }//fin getImagenUrl()
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }//fin setImagenUrl(String imagenUrl)
    
    public Prueba getPrueba() {
        return prueba;
    }//fin getPrueba()
    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }//fin setPrueba(Prueba prueba)
    
    public String getTipo() {
        String[] partes = imagenUrl.split("\\.");
        return partes[partes.length - 1];
    }//fin getTipo(String nombre)
    
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
        final Imagen other = (Imagen) obj;
        if (this.clave != other.clave && (this.clave == null || !this.clave.equals(other.clave))) {
            return false;
        }
        return true;
    }//fin equals(Object obj)

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.clave != null ? this.clave.hashCode() : 0);
        hash = 53 * hash + (this.imagenUrl != null ? this.imagenUrl.hashCode() : 0);
        hash = 53 * hash + (this.prueba != null ? this.prueba.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return (clave.toString());
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase Imagen