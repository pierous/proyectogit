/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.dao;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

/*----------------------------------------------------------------------------*/


public abstract class GenericoDaoHibernate<E extends Entidad, CP extends Serializable> implements GenericoDao<E, CP> {


    /*------------------------------Atributos---------------------------------*/
    
    protected Class<E> entidadClase;
    private SessionFactory sessionFactory;
    protected List<String> orden;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    protected GenericoDaoHibernate(final Class<E> entidadClase) {
        this.entidadClase = entidadClase;
        orden = new ArrayList<String>();
        setOrdenListado();
    }//fin GenericoDaoHibernate(final Class<E> persistentClass)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }//fin setSessionFactory(SessionFactory sessionFactory)
    
    
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }//fin getSession()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    protected DataAccessException convertHibernateAccessException(HibernateException e) {
        return SessionFactoryUtils.convertHibernateAccessException(e);
    }//fin convertHibernateAccessException(HibernateException e)
    
    
    protected abstract String nombreTabla();
    
    
    protected abstract void setOrdenListado();
    
    
    public E buscarPorCriterio(String columna, Object criterio) {
        try {
            return (E) getSession().createCriteria(entidadClase).add(Restrictions.eq(columna, criterio)).uniqueResult();
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin buscarPorString(String columna, String criterio)
    
    
    public E buscarPorObjeto(E elemento) {
        Example ejemplo = Example.create(elemento).excludeZeroes();
        try {
            return (E) getSession().createCriteria(entidadClase).add(ejemplo).uniqueResult();
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin buscarPorObjeto(E elemento)
    
    
    public List<E> buscarPorObjeto_Lista(E elemento) {
        Example ejemplo = Example.create(elemento).excludeZeroes();
        try {
            return getSession().createCriteria(entidadClase).add(ejemplo).list();
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin buscarPorObjeto(E elemento)
    
    
    public List listarColumna(String columna) {
        try {
            return getSession().createQuery("SELECT " + columna + " FROM " + nombreTabla()).list();
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin listarColumna(String columna)
    
    
    public List<E> listarPorCriterio(String columna, Object criterio) {
        try {
            return getSession().createCriteria(entidadClase).add(Restrictions.eq(columna, criterio)).list();
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin listarPorCriterio()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    @Override
    public void guardar(E elemento) {
         try {
             getSession().save(elemento);
         } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
         }//fin try
    }//fin guardar(E elemento)
    
    
    @Override
    public void actualizar(E elemento) {
        try {
            getSession().update(elemento);
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin actualizar(E elemento)
    
    
    @Override
    public CP getClave(E elemento) {
        E objeto = this.buscarPorObjeto(elemento);
        if (objeto == null) return null;
        else return (CP) objeto.getClave();
    }//fin getClave(E elemento)
    
    
    @Override
    public E buscar(CP id) throws InstanciaNoEncontradaExcepcion {
        try {
            E entidad = (E) getSession().get(entidadClase, id);
            if (entidad == null) throw new InstanciaNoEncontradaExcepcion(id, "clave", entidadClase.getSimpleName());
        return entidad;
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin buscar(CP id)
    
    
    @Override
    public boolean existe(CP id) {
        try {
            return getSession().createCriteria(entidadClase).add(
                    Restrictions.idEq(id)).setProjection(Projections.id())
                    .uniqueResult() != null;
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin existe(CP id)
    
    
    @Override
    public void eliminar(CP id) throws InstanciaNoEncontradaExcepcion {
        try {
            getSession().delete(buscar(id));
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin eliminar(CP id)
    
    
    @Override
    public void eliminarTodo() {
        List<E> lista = listar();

        try {
            for (Iterator i = lista.iterator(); i.hasNext();)
                getSession().delete(i.next());
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
    }//fin eliminarTodo()
    
    
    @Override
    public List<E> listar() {
        Criteria crit;
        try {
            crit =  getSession().createCriteria(entidadClase);
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
        
        //Se ordenan los pares en el orden establecido.
        if (orden.size() > 0)
            for (int i = 0; i < orden.size(); i++) crit.addOrder(Order.asc(orden.get(i)));
        
        return crit.list();
    }//fin listar()

    /*------------------------------------------------------------------------*/


}//fin Clase GenericoDaoHibernate