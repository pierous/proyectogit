/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.valorservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.modelo.valor.Valor;

/*----------------------------------------------------------------------------*/


public interface ValorServicio extends GenericoServicio<Valor, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Parametro recuperarTipo(Long clave);

    /*------------------------------------------------------------------------*/


}//fin Interface ValorServicio