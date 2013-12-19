/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.administradorservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.PasswordIncorrectoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.VerificacionPasswordExcepcion;
import es.udc.proyectogit.modelo.administrador.Administrador;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import java.util.Calendar;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface AdministradorServicio extends GenericoServicio<Administrador, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    Long agregarDepartamento(String nombre) throws InstanciaDuplicadaExcepcion;
    void eliminarDepartamento(Long clave) throws InstanciaNoEncontradaExcepcion;
    List<Departamento> listarDepartamentos();
    public Long agregarMedico(String dni, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, Integer telefono, Direccion direccion) throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion;
    void resetearLoginMedico(Long clave) throws InstanciaNoEncontradaExcepcion;
    void resetearPasswordMedico(Long clave) throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion;
    void eliminarMedico(Long clave) throws InstanciaNoEncontradaExcepcion;
    List<Medico> listarMedicos();
    void asignarMedicoDepartamento(Long idDepartamento, Long idMedico) throws InstanciaNoEncontradaExcepcion;
    void asignarSupervisorDepartamento(Long idDepartamento, Long idMedico) throws InstanciaNoEncontradaExcepcion;
    Long agregarPaciente(String dni, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, Integer telefono, Direccion direccion) throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion;
    void eliminarPaciente(Long clave) throws InstanciaNoEncontradaExcepcion;
    List<Paciente> listarPacientes();
    Long registrarAdministrador(String nombreLogin, String password, String dni, String nombre, String apellido1, String apellido2) throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion;
    Administrador loginAdministrador(String nombreLogin, String password, boolean passwordEstaEncriptado) throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion;
    void modificarAdministrador(Long clave, String dni, String nombre, String apellido1, String apellido2, String nombreLogin, String password, String passwordRepetido) throws VerificacionPasswordExcepcion, FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion;
    void resetearLoginAdministrador(Long clave) throws InstanciaNoEncontradaExcepcion;
    void resetearPasswordAdministrador(Long clave) throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion;
    void eliminarAdministrador(Long clave) throws InstanciaNoEncontradaExcepcion;
    Long agregarParametro(String nombre, String unidad, String unidadAbreviada) throws InstanciaDuplicadaExcepcion;
    List<Parametro> listarParametros();
    void eliminarParametro(Long clave) throws InstanciaNoEncontradaExcepcion;
    Long agregarPlantilla(String nombre, Departamento departamento, List<Parametro> parametros) throws InstanciaDuplicadaExcepcion;
    List<Plantilla> listarPlantillas();
    void eliminarPlantilla(Long clave) throws InstanciaNoEncontradaExcepcion;
    List<Administrador> filtro(List<Administrador> administradores, String[] campos);
    

    /*------------------------------------------------------------------------*/


}//fin Interface AdministradorServicio