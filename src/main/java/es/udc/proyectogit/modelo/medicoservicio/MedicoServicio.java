/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.medicoservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.PasswordIncorrectoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.VerificacionPasswordExcepcion;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import java.util.Calendar;
import java.util.List;
import org.apache.tapestry5.upload.services.UploadedFile;

/*----------------------------------------------------------------------------*/


public interface MedicoServicio extends GenericoServicio<Medico, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Departamento recuperarDepartamento(Long clave) throws InstanciaNoEncontradaExcepcion;
    Departamento recuperarDepSupervisado(Long clave);
    List<Informe> recuperarInformes(Long clave);
    List<Informe> recuperarInformesIncompletos(Long clave);
    List<Informe> recuperarInformesFinalizados(Long clave);
    List<Prueba> recuperarPruebas(Long clave);
    List<Prueba> recuperarPruebasIncompletas(Long clave);
    List<Prueba> recuperarPruebasFinalizadas(Long clave);
    List<Prueba> recuperarPruebasSolicitadas(Long clave);
    List<Prueba> recuperarPruebasSolicitadasIncompletas(Long clave);
    List<Prueba> recuperarPruebasSolicitadasFinalizadas(Long clave);
    List<Paciente> recuperarPacientes(Long clave);
    Medico loginMedico(String nombreLogin, String password, boolean passwordEstaEncriptado) throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion;
    public void modificarMedico(Long clave, String dni, String nombre, String apellido1, String apellido2, Calendar fecha, Integer telefono, Direccion direccion, String nombreLogin, String password, String passwordRepetido) throws VerificacionPasswordExcepcion, FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion;
    void asignarPacienteMedico(Long idMedico, Long idPaciente) throws InstanciaNoEncontradaExcepcion;
    void asignarPruebaMedico(Long idPrueba, Long idMedico) throws InstanciaNoEncontradaExcepcion;
    Long crearInforme(Long idPaciente, Long idMedico, String observacionesPrevias, List<Plantilla> plantillas) throws InstanciaNoEncontradaExcepcion;
    void eliminarInforme(Long idInforme) throws InstanciaNoEncontradaExcepcion;
    Long agregarPrueba(Long idInforme, Long idPlantilla, String observacionesSolicitante) throws InstanciaNoEncontradaExcepcion;
    void desvincularPrueba(Long clave) throws InstanciaNoEncontradaExcepcion;
    void eliminarPrueba(Long clave) throws InstanciaNoEncontradaExcepcion;
    void agregarImagen(Long clave, UploadedFile archivo) throws InstanciaNoEncontradaExcepcion;
    void eliminarImagen(Long clave) throws InstanciaNoEncontradaExcepcion;
    boolean esAdministrador(Long clave) throws InstanciaNoEncontradaExcepcion;
    List<Medico> filtro(List<Medico> medicos, String[] campos);

    /*------------------------------------------------------------------------*/


}//fin Interface MedicoServicio