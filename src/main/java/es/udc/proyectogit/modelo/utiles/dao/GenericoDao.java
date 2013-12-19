/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.dao;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import java.io.Serializable;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface GenericoDao<E extends Entidad, CP extends Serializable> {


    /*-------------------------------Metodos----------------------------------*/
    
    void guardar(E elemento);
    void actualizar(E elemento);
    CP getClave(E elemento);
    E buscar(CP id) throws InstanciaNoEncontradaExcepcion;
    E buscarPorCriterio(String columna, Object criterio);
    E buscarPorObjeto(E elemento);
    List<E> buscarPorObjeto_Lista(E elemento);
    boolean existe(CP id);
    void eliminar(CP id) throws InstanciaNoEncontradaExcepcion;
    void eliminarTodo();
    public List<E> listar();

    /*------------------------------------------------------------------------*/


}//fin Interface GenericoDao