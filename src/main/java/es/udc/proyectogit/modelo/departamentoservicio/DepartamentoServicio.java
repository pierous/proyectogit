/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.departamentoservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface DepartamentoServicio extends GenericoServicio<Departamento, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    List<Departamento> filtro(List<Departamento> departamentos, String nombre);
    public void modificarDepartamento(Long clave, String nombre, Medico supervisor) throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion;
    List<Medico> recuperarMedicos(Long clave);
    Medico recuperarSupervisor(Long clave);
    List<Paciente> recuperarPacientes(Long clave);
    List<Informe> recuperarInformes(Long clave);
    List<Informe> recuperarInformesIncompletos(Long clave);
    List<Informe> recuperarInformesFinalizados(Long clave);
    List<Prueba> recuperarPruebas(Long clave);
    List<Prueba> recuperarPruebasIncompletas(Long clave);
    List<Prueba> recuperarPruebasFinalizadas(Long clave);

    /*------------------------------------------------------------------------*/


}//fin Interface DepartamentoServicio