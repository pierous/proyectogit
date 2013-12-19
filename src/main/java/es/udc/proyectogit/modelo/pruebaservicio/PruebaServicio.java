/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.pruebaservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.modelo.valor.Valor;
import java.util.Calendar;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface PruebaServicio extends GenericoServicio<Prueba, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    boolean esEditable(Long idPrueba, Long idMedico);
    boolean esEliminable(Long idPrueba, Long idMedico);
    boolean esFinalizable(Long idPrueba, Long idMedico);
    boolean esAgregable(Long idPrueba, Long idMedico);
    boolean esDesvinculable(Long idPrueba, Long idMedico);
    boolean esIniciado(Long clave);
    boolean esFinalizado(Long clave);
    List<Prueba> filtro(int tipoFecha, List<Prueba> pruebas, Calendar desde, Calendar hasta);
    void modificarPrueba(Prueba prueba);
    List<Imagen> recuperarImagenes(Long clave);
    List<Prueba> recuperarPruebasIncompletas();
    List<Prueba> recuperarPruebasFinalizadas();
    Plantilla recuperarPlantilla(Long clave);
    Paciente recuperarPaciente(Long clave);
    Medico recuperarSolicitante(Long clave);
    Medico recuperarMedico(Long clave);
    Informe recuperarInforme(Long clave);
    List<Valor> recuperarValores(Long clave);
    Departamento recuperarDepartamento(Long clave);
    void iniciarPrueba(Long clave) throws InstanciaNoEncontradaExcepcion;
    void finalizarPrueba(Long clave) throws InstanciaNoEncontradaExcepcion;

    /*------------------------------------------------------------------------*/


}//fin Interface PruebaServicio