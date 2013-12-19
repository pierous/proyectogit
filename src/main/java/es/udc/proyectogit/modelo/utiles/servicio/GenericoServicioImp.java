/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.servicio;


/*---------------------------------Imports------------------------------------*/

import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/

@Transactional
public abstract class GenericoServicioImp<E extends Entidad, CP extends Serializable> implements GenericoServicio<E, CP> {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    protected GenericoDao dao;
    
    /*------------------------------------------------------------------------*/
    
    
    /*--------------------------Constructores---------------------------------*/
    
    
    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Metodos------------------------------------*/
    
    @Override
    public void guardar(E elemento) throws InstanciaDuplicadaExcepcion {
        if (dao.getClave(elemento) == null)
            dao.guardar(elemento);
        else throw new InstanciaDuplicadaExcepcion(elemento, "clave", elemento.getClass().getName());
    }//fin guardar(E elemento)
    
    
    @Override
    public void actualizar(E elemento) {
        dao.actualizar(elemento);
    }//fin actualizar(E elemento)
    
    
    @Override
    public CP getClave(E elemento) {
        return (CP) dao.getClave(elemento);
    }//fin getClave(Object elemento)
    
    
    @Override
    public E buscar(CP id) throws InstanciaNoEncontradaExcepcion {
        E elemento = (E) dao.buscar(id);
        return(elemento);
    }//fin buscar(CP id)
    
    
    @Override
    public boolean existe(CP id) {
        return dao.existe(id);
    }//fin existe(CP id)
    
    @Override
    public List<E> listar() {
        return dao.listar();
    }//fin listar()
    
    
    @Override
    public void eliminar(CP id) throws InstanciaNoEncontradaExcepcion {
        dao.eliminar(id);
    }//fin eliminar(CP id)
    
    @Override
    public void eliminarTodo() {
        dao.eliminarTodo();
    }//fin eliminarTodo()
    
    
    /*------------------------------------------------------------------------*/
    
    
}//fin calse GenericoServicioImp