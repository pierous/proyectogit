/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.servicio;

/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import java.io.Serializable;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface GenericoServicio<E extends Entidad, CP extends Serializable> {
    
    
    /*-----------------------------Metodos------------------------------------*/
    
    void guardar(E elemento) throws InstanciaDuplicadaExcepcion;
    void actualizar(E elemento);
    CP getClave(E elemento);
    E buscar(CP id) throws InstanciaNoEncontradaExcepcion;
    boolean existe(CP id);
    List<E> listar();
    void eliminar(CP id) throws InstanciaNoEncontradaExcepcion;
    void eliminarTodo();
    
    /*------------------------------------------------------------------------*/
    
    
}//fin interface GenericoDao