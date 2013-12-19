/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.plantillaservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface PlantillaServicio extends GenericoServicio<Plantilla, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    List<Plantilla> filtro(List<Plantilla> plantillas, String nombre, Long claveDepartamento);
    void modificarPlantilla(Long clave, String nombre, Departamento departamento, List<Parametro> parametros) throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion;
    List<Parametro> recuperarParametros(Long clave);
    Departamento recuperarDepartamento(Long clave);

    /*------------------------------------------------------------------------*/


}//fin Interface PlantillaServicio