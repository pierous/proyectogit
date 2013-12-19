/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.plantilla;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;

/*----------------------------------------------------------------------------*/


public interface PlantillaDao extends GenericoDao<Plantilla, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Departamento departamentoDePlantilla(Long clave);

    /*------------------------------------------------------------------------*/


}//fin Interface PlantillaDao