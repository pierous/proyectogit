/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.pacienteservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import java.util.Calendar;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface PacienteServicio extends GenericoServicio<Paciente, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Medico recuperarMedico(Long idPaciente);
    List<Informe> recuperarInformes(Long idPaciente);
    List<Informe> recuperarInformesPendientes(Long idPaciente);
    List<Informe> recuperarInformesFinalizados(Long idPaciente);
    List<Prueba> recuperarPruebas(Long idPaciente);
    List<Prueba> recuperarPruebasPendientes(Long idPaciente);
    List<Prueba> recuperarPruebasFinalizadas(Long idPaciente);
    void modificarPaciente(Long clave, String dni, String nombre, String apellido1, String apellido2, Calendar fecha, Integer telefono, Direccion direccion) throws FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion;
    List<Paciente> filtro(List<Paciente> pacientes, String[] campos);

    /*------------------------------------------------------------------------*/


}//fin Interface PacienteServicio