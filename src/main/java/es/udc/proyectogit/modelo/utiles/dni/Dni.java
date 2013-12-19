/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.dni;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;

/*----------------------------------------------------------------------------*/


public class Dni {


    /*------------------------------Atributos---------------------------------*/
    
    private String dni;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Dni() {};
    
    
    public Dni(String dni) throws FormatoInvalidoExcepcion {
        if (dni.length() != 9){System.out.println("Dni"); throw new FormatoInvalidoExcepcion(Dni.class);}
        int numero;
        char letra;
        try {
            numero = Integer.parseInt(dni.substring(0, 8));
            letra = dni.charAt(8);
        } catch (Exception e) {
            throw new FormatoInvalidoExcepcion(Dni.class);
        }//fin try
        
        if (!Character.isLetter(letra)) throw new FormatoInvalidoExcepcion(Dni.class);
        else this.dni = (Integer.toString(numero) + String.valueOf(letra).toUpperCase());
        
        while (this.dni.length() < 9) this.dni = "0" + this.dni;
    }//fin Dni(String dni)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public char getLetra() {
        return dni.charAt(8);
    }//fin getLetra()
    
    public String getNumero() {
        return dni.substring(0, 8);
    }//fin getNumero()
    
    public String getDni() {
        return dni;
    }//fin getDni()
    public void setDni(String dni) {
        this.dni = dni;
    }//fin setDni(String dni)

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
        final Dni other = (Dni) obj;
        if ((this.dni == null) ? (other.dni != null) : !this.dni.equals(other.dni)) {
            return false;
        }
        return true;
    }//fin equals(Object obj)

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.dni != null ? this.dni.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return (getNumero() + getLetra());
    }//fin toString()
    
    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase Dni