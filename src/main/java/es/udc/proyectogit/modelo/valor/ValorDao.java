/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.valor;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;

/*----------------------------------------------------------------------------*/


public interface ValorDao extends GenericoDao<Valor, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Parametro tipoDeValor(Long clave);

    /*------------------------------------------------------------------------*/


}//fin Interface ValorDao