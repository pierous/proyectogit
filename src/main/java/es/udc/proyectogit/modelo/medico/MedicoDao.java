/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface MedicoDao extends GenericoDao<Medico, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Departamento departamentoDeMedico(Long clave) throws InstanciaNoEncontradaExcepcion;
    Departamento depSupervisadoDeMedico(Long clave);
    List<Informe> informesDeMedico(Long clave);
    List<Informe> informesPendientesDeMedico(Long clave);
    List<Informe> informesFinalizadosDeMedico(Long clave);
    List<Prueba> pruebasDeMedico(Long clave);
    List<Prueba> pruebasPendientesDeMedico(Long clave);
    List<Prueba> pruebasFinalizadasDeMedico(Long clave);
    List<Prueba> pruebasSolicitadasDeMedico(Long clave);
    List<Prueba> pruebasSolicitadasPendientesDeMedico(Long clave);
    List<Prueba> pruebasSolicitadasFinalizadasDeMedico(Long clave);
    List<Paciente> pacientesDeMedico(Long clave);

    /*------------------------------------------------------------------------*/


}//fin Interface MedicoDao