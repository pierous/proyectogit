/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.parametroservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface ParametroServicio extends GenericoServicio<Parametro, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    List<Parametro> filtro(List<Parametro> parametros, String nombre);
    void modificarParametro(Long clave, String nombre, String unidad, String unidadAbreviada) throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion;

    /*------------------------------------------------------------------------*/


}//fin Interface ParametroServicio