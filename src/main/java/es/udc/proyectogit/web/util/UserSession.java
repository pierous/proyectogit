/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.util;


/*----------------------------------Imports-----------------------------------*/

/*----------------------------------------------------------------------------*/


public class UserSession {


    /*------------------------------Atributos---------------------------------*/
    
    private Long userId;
    private String nombre;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Long getUserId() {
        return userId;
    }//fin getUserId()
    public void setUserId(Long userId) {
        this.userId = userId;
    }//fin setUserId(Long userId)

    public String getNombre() {
        return nombre;
    }//fin getNombre()
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }//fin setNombre(String nombre)

    /*------------------------------------------------------------------------*/


}//fin Clase UserSession