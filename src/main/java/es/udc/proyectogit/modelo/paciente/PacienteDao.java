/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.paciente;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface PacienteDao extends GenericoDao<Paciente, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Medico medicoDePaciente(Long idPaciente);
    List<Informe> informesDePaciente(Long idPaciente);
    List<Informe> informesPendientesDePaciente(Long idPaciente);
    List<Informe> informesFinalizadosDePaciente(Long idPaciente);
    List<Prueba> pruebasDePaciente(Long idPaciente);
    List<Prueba> pruebasPendientesDePaciente(Long idPaciente);
    List<Prueba> pruebasFinalizadasDePaciente(Long idPaciente);

    /*------------------------------------------------------------------------*/


}//fin Interface PacienteDao