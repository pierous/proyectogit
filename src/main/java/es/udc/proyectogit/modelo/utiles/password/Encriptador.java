/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.password;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;

/*----------------------------------------------------------------------------*/


public class Encriptador {


    /*-------------------------------Metodos----------------------------------*/
    
    public static String encriptar(String cadena) throws FormatoInvalidoExcepcion {
        String cadenaEncriptada = "";
        
        if (cadena.length()< 5)
            throw new FormatoInvalidoExcepcion(Encriptador.class, "password", "un minimo de 5 caracteres");
        if (cadena.length() > 20)
            throw new FormatoInvalidoExcepcion(Encriptador.class, "password", "un maximo de 20 caracteres");
        
        try {
            cadenaEncriptada = Algoritmo.encriptar(cadena);
        } catch (Exception ex) {
            Logger.getLogger(Encriptador.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        
        return cadenaEncriptada;
    }//fin encriptar(String cadena)
    
    
    public static String desencriptar(String cadenaEncriptada) {
        String cadenaDesencriptada = "";
        
        try {
            cadenaDesencriptada = Algoritmo.desencriptar(cadenaEncriptada);
        } catch (Exception ex) {
            Logger.getLogger(Encriptador.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        
        return cadenaDesencriptada;
    }//fin desencriptar(String cadenaEncriptada)
    
    
    public static boolean esPasswordCorrecto(String password, String passwordGuardado) {
        return password.equals(desencriptar(passwordGuardado));
    }//fin esPasswordCorrecto(String password, String passwordGuardado)

    /*------------------------------------------------------------------------*/


}//fin Clase Encriptador