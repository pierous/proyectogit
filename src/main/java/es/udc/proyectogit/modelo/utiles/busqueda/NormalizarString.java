/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.busqueda;


/*----------------------------------Imports-----------------------------------*/

/*----------------------------------------------------------------------------*/


public class NormalizarString {


    /*-------------------------------Metodos----------------------------------*/
    
    public static String normalizar(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i=0; i<original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }//fin normalizar(String input)

    /*------------------------------------------------------------------------*/


}//fin Clase NormalizarString