/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.direccion;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;

/*----------------------------------------------------------------------------*/


public class Direccion {


    /*------------------------------Atributos---------------------------------*/
    
    private String calle;
    private String provincia;
    private String localidad;
    private Integer codigoPostal;
    private Integer numero;
    private Integer piso;
    private String letra;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Direccion() {};
    
    
    public Direccion(String calle, String provincia, String localidad, Integer codigoPostal, Integer numero, Integer piso, String letra)
            throws FormatoInvalidoExcepcion {
        
        this.calle = calle;
        this.provincia = provincia;
        this.localidad = localidad;
        if (codigoPostal != null && codigoPostal < 0)throw new FormatoInvalidoExcepcion(Direccion.class);
        else this.codigoPostal = codigoPostal;
        if (numero != null && numero < 0)throw new FormatoInvalidoExcepcion(Direccion.class);
        else this.numero = numero;
        if (piso != null && piso < 0) throw new FormatoInvalidoExcepcion(Direccion.class);
        else this.piso = piso;
        this.letra = letra;
    }//fin Direccion(String calle, String provincia, String localidad, int codigoPostal, int numero, int piso, String letra)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public String getCalle() {
        return calle;
    }//fin getCalle()
    public void setCalle(String calle) {
        this.calle = calle;
    }//fin setCalle(String calle)

    public Integer getCodigoPostal() {
        return codigoPostal;
    }//fin getCodigoPostal()
    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }//fin setCodigoPostal(int codigoPostal)

    public String getLetra() {
        return letra;
    }//fin getLetra()
    public void setLetra(String letra) {
        this.letra = letra;
    }//fin setLetra(String letra)

    public String getLocalidad() {
        return localidad;
    }//fin getLocalidad()
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }//fin setLocalidad(String localidad)

    public Integer getNumero() {
        return numero;
    }//fin getNumero()
    public void setNumero(Integer numero) {
        this.numero = numero;
    }//fin setNumero(int numero)

    public Integer getPiso() {
        return piso;
    }//fin getPiso()
    public void setPiso(Integer piso) {
        this.piso = piso;
    }//fin setPiso(int piso)

    public String getProvincia() {
        return provincia;
    }//fin getProvincia()
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }//fin setProvincia(String provincia)

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
        final Direccion other = (Direccion) obj;
        if ((this.calle == null) ? (other.calle != null) : !this.calle.equals(other.calle)) {
            return false;
        }
        if ((this.provincia == null) ? (other.provincia != null) : !this.provincia.equals(other.provincia)) {
            return false;
        }
        if ((this.localidad == null) ? (other.localidad != null) : !this.localidad.equals(other.localidad)) {
            return false;
        }
        if (this.codigoPostal != other.codigoPostal) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (this.piso != other.piso) {
            return false;
        }
        if ((this.letra == null) ? (other.letra != null) : !this.letra.equals(other.letra)) {
            return false;
        }
        return true;
    }//fin equals(Object obj)

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.calle != null ? this.calle.hashCode() : 0);
        hash = 37 * hash + (this.provincia != null ? this.provincia.hashCode() : 0);
        hash = 37 * hash + (this.localidad != null ? this.localidad.hashCode() : 0);
        hash = 37 * hash + this.codigoPostal;
        hash = 37 * hash + this.numero;
        hash = 37 * hash + this.piso;
        hash = 37 * hash + (this.letra != null ? this.letra.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return (calle + " " + numero + " " + piso + " " + letra + ", " + codigoPostal + ", " + localidad + ", " + provincia);
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase Direccion