/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.informe;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("informeDao")
public class InformeDaoHibernate extends GenericoDaoHibernate<Informe, Long> implements InformeDao {


    /*----------------------------Constructores-------------------------------*/
    
    public InformeDaoHibernate() {
        super(Informe.class);
    }//fin DepartamentoDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Informe");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
        orden.add("fechaInicio");
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public List<Prueba> pruebasDeInforme(Long clave) {
        return getSession().createQuery("select pruebas from Informe p where p.clave = :clave order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasDeInforme(Long clave)
    
    
    public List<Prueba> pruebasIncompletasDeInforme(Long clave) {
        return getSession().createQuery("from Prueba p where p.informe.clave = :clave and fechaFin is null order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasIncompletasDeInforme(Long clave)
    
    
    public List<Prueba> pruebasFinalizadasDeInforme(Long clave) {
        return getSession().createQuery("from Prueba p where p.informe.clave = :clave and fechaFin is not null order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasFinalizadasDeInforme(Long clave)
    
    
    public Paciente pacienteDeInforme(Long clave) {
        return (Paciente) getSession().createQuery("select paciente from Informe p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin pacienteDeInforme(Long clave)
    
    
    public Medico medicoDeInforme(Long clave) {
        return (Medico) getSession().createQuery("select medico from Informe p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin medicoDeInforme(Long clave)
    
    
    @Override
    public List<Informe> listar() {
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


}//fin Clase InformeDaoHibernate