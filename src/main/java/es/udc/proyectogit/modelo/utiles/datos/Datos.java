/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.datos;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;

/*----------------------------------------------------------------------------*/


public class Datos {


    /*------------------------------Atributos---------------------------------*/
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public static void administradorPorDefecto(AdministradorServicio administradorServicio) {
        if (administradorServicio == null) System.out.println("Es nulo");
        if (administradorServicio.listar().isEmpty())
            try {
                administradorServicio.registrarAdministrador("admin", "admin", "00000000a", "Admin", "Admin", null);
            } catch (InstanciaDuplicadaExcepcion ex) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            }//fin try
    }//fin administradorPorDefecto()

    /*------------------------------------------------------------------------*/


}//fin Clase Datos