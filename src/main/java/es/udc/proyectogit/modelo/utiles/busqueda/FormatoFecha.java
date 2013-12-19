/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.busqueda;


/*----------------------------------Imports-----------------------------------*/

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*----------------------------------------------------------------------------*/


public class FormatoFecha {


    /*-------------------------------Metodos----------------------------------*/
    
    public String formatearFecha(Calendar fecha) {
        if (fecha == null) return null;
        else {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return (formato.format(fecha.getTime()));
        }//fin if (fecha == null)
    }//fin formatearFecha(Calendar fecha)
    
    
    public String formatearFechaCorta(Calendar fecha) {
        if (fecha == null) return null;
        else {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return (formato.format(fecha.getTime()));
        }//fin if (fecha == null)
    }//fin formatearFechaCorta(Calendar fecha)

    /*------------------------------------------------------------------------*/


}//fin Clase FormatoFecha