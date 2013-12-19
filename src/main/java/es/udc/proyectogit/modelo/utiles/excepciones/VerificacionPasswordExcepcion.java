/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.excepciones;


public class VerificacionPasswordExcepcion extends Exception {


    /*----------------------------Constructores-------------------------------*/
    
    public VerificacionPasswordExcepcion() {
        super("(Verificacion Incorrecta): La verificacion del password no se corresponde con el password.");
    }//fin FormatoInvalidoExcepcion()

    /*------------------------------------------------------------------------*/


}//fin Clase VerificacionPasswordExcepcion