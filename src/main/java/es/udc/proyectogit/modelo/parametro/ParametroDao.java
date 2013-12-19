/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.parametro;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface ParametroDao extends GenericoDao<Parametro, Long>{


    /*-------------------------------Metodos----------------------------------*/
    
    List<Parametro> parametrosDePlantilla(Long clave);

    /*------------------------------------------------------------------------*/


}//fin Interface ParametroDao