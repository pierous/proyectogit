/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.administrador;



/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.dni.Dni;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;

/*----------------------------------------------------------------------------*/


public class Administrador implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private String nombreLogin;
    private Long clave;
    private Dni dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String passwordEncriptado;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Administrador() {};
    
    
    public Administrador(String nombreLogin, Dni dni, String nombre, String apellido1, String apellido2, String passwordEncriptado) {
        this();
        this.nombreLogin = nombreLogin;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.passwordEncriptado = passwordEncriptado;
    }//fin Administrador(String nombreLogin, int nss, String nombre, String apellido1, String apellido2, String passwordEncriptado)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/

    public String getNombreLogin() {
        return nombreLogin;
    }//fin getNombreLogin()
    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
    }//fin setNombreLogin(String nombreLogin)
    
    @Override
    public Long getClave() {
        return clave;
    }//fin getClave()
    @Override
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setClave(Long clave)
    
    public Dni getDni() {
        return dni;
    }// fin getDni()
    public void setDni(Dni dni) {
        this.dni = dni;
    }// fin setDni(int dni)
    
    public String getNombre() {
        return nombre;
    }// fin getNombre()
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }// fin setNombre(String nombre)
    
    public String getApellido1() {
        return apellido1;
    }// fin getApellido1()
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }// fin setApellido1(String apellido1)

    public String getApellido2() {
        return apellido2;
    }// fin getApellido2()
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }// fin setApellido2(String apellido2)

    public String getPasswordEncriptado() {
        return passwordEncriptado;
    }//fin getPasswordEncriptado()
    public void setPasswordEncriptado(String passwordEncriptado) {
        this.passwordEncriptado = passwordEncriptado;
    }//fin setPasswordEncriptado(String passwordEncriptado)
    
    public String getNombreCompleto() {
        String cadena = nombre;
        if (apellido1 != null) cadena = cadena + " " + apellido1;
        if (apellido2 != null) cadena = cadena + " " + apellido2;
        
        return cadena;
    }//fin getNombreCompleto()

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
        final Administrador other = (Administrador) obj;
        if (this.clave != other.clave && (this.clave == null || !this.clave.equals(other.clave))) {
            return false;
        }
        if (this.dni != other.dni) {
            return false;
        }
        return true;
    }//fin equals(Object obj)
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.clave != null ? this.clave.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return ("(" + dni + ")" + " " + nombre + " " + apellido1 + " " + apellido2 + " -> " + nombreLogin + " " + passwordEncriptado);
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/



}//fin Clase Administrador