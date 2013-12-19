/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.informe;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface InformeDao extends GenericoDao<Informe, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    List<Prueba> pruebasDeInforme(Long clave);
    List<Prueba> pruebasIncompletasDeInforme(Long clave);
    List<Prueba> pruebasFinalizadasDeInforme(Long clave);
    Paciente pacienteDeInforme(Long clave);
    Medico medicoDeInforme(Long clave);
    List<Informe> listar();

    /*------------------------------------------------------------------------*/


}//fin Interface InformeDao