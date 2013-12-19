/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.prueba;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import es.udc.proyectogit.modelo.valor.Valor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("pruebaDao")
public class PruebaDaoHibernate extends GenericoDaoHibernate<Prueba, Long> implements PruebaDao {


    /*----------------------------Constructores-------------------------------*/
    
    public PruebaDaoHibernate() {
        super(Prueba.class);
    }//fin DepartamentoDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Prueba");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
        orden.add("fechaSolicitud");
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Plantilla platillaDePrueba(Long clave) {
        return (Plantilla) getSession().createQuery("select plantilla from Prueba p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin platillaDePrueba(Long clave)
    
    
    public Paciente pacienteDePrueba(Long clave) {
        return (Paciente) getSession().createQuery("select paciente from Prueba p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin pacienteDePrueba(Long clave)
    
    
    public Medico solicitanteDePrueba(Long clave) {
        return (Medico) getSession().createQuery("select solicitante from Prueba p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin solicitanteDePrueba(Long clave)
    
    
    public Medico medicoDePrueba(Long clave) {
        return (Medico) getSession().createQuery("select medico from Prueba p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin medicoDePrueba(Long clave)
    
    
    public Informe informeDePrueba(Long clave) {
        return (Informe) getSession().createQuery("select informe from Prueba p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin informeDePrueba(Long clave)
    
    
    public List<Valor> valoresDePrueba(Long clave) {
        return getSession().createQuery("select valores from Prueba p where p.clave = :clave")
                .setParameter("clave", clave).list();
    }//fin valoresDePrueba(Long clave)
    
    
    public List<Imagen> imagenesDePrueba(Long clave) {
        return getSession().createQuery("select imagenes from Prueba p where p.clave = :clave")
                .setParameter("clave", clave).list();
    }//fin imagenesDePrueba(Long clave)
    
    
    @Override
    public List<Prueba> listar() {
        Criteria crit;
        try {
            crit =  getSession().createCriteria(entidadClase);
        } catch (HibernateException e) {
            throw convertHibernateAccessException(e);
        }//fin try
        
        //Se ordenan los pares en el orden establecido.
        if (orden.size() > 0)
            for (int i = 0; i < orden.size(); i++) crit.addOrder(Order.desc(orden.get(i)));
        
        return crit.list();
    }//fin listar()

    /*------------------------------------------------------------------------*/


}//fin Clase PruebaDaoHibernate