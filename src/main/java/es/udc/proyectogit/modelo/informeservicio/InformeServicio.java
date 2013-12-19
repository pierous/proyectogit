/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.informeservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import java.util.Calendar;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface InformeServicio extends GenericoServicio<Informe, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    boolean esEditable(Long idInforme, Long idMedico);
    boolean esEliminable(Long idInforme, Long idMedico);
    boolean esFinalizable(Long idInforme, Long idMedico) throws InstanciaNoEncontradaExcepcion;
    boolean esFinalizado(Long clave);
    List<Informe> filtro(int tipoFecha, List<Informe> informes, Calendar desde, Calendar hasta);
    List<Prueba> recuperarPruebas(Long clave);
    List<Prueba> recuperarPruebasIncompletas(Long clave);
    public List<Prueba> recuperarPruebasFinalizadas(Long clave);
    List<Informe> recuperarInformesIncompletos();
    List<Informe> recuperarInformesFinalizados();
    Paciente recuperarPaciente(Long clave);
    Medico recuperarMedico(Long clave);
    void finalizarInforme(Long clave) throws InstanciaNoEncontradaExcepcion;

    /*------------------------------------------------------------------------*/


}//fin Interface InformeServicio