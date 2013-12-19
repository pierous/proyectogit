/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.prueba;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;
import es.udc.proyectogit.modelo.valor.Valor;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface PruebaDao extends GenericoDao<Prueba, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Plantilla platillaDePrueba(Long clave);
    Paciente pacienteDePrueba(Long clave);
    Medico solicitanteDePrueba(Long clave);
    Medico medicoDePrueba(Long clave);
    Informe informeDePrueba(Long clave);
    List<Valor> valoresDePrueba(Long clave);
    List<Imagen> imagenesDePrueba(Long clave);
    List<Prueba> listar();

    /*------------------------------------------------------------------------*/


}//fin Interface PruebaDao