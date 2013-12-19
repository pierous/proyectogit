/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.departamento;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("departamentoDao")
public class DepartamentoDaoHibernate extends GenericoDaoHibernate<Departamento, Long> implements DepartamentoDao {


    /*----------------------------Constructores-------------------------------*/
    
    public DepartamentoDaoHibernate() {
        super(Departamento.class);
    }//fin DepartamentoDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Departamento");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
        orden.add("nombre");
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public List<Medico> medicosDeDepartamento(Long clave) {
        Departamento departamento = (Departamento) getSession().createQuery("from Departamento d where d.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
        Query q = getSession().createFilter(departamento.getMedicos(), "order by apellido1 asc, apellido2 asc, nombre asc");
        
        return q.list();
    }//fin medicosDeDepartamento(Long clave)
    
    
    public Medico supervisorDeDepartamento(Long clave) {
        return (Medico) getSession().createQuery("select supervisor from Departamento p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin supervisorDeDepartamento(Long clave)
    
    
    public List<Paciente> pacientesDeDepartamento(Long clave) {
        return getSession().createQuery("from Paciente p where p.medico.clave in (select m.clave from Medico m where m.departamento.clave = :clave) order by p.apellido1 asc, p.apellido2 asc, p.nombre asc")
                .setParameter("clave", clave).list();
        
//        return getSession().createQuery("select pacientes from Medico m where m.departamento.clave = :clave")
//                .setParameter("clave", clave).list();
    }//fin pacientesDeDepartamento(Long clave)
    
    
    public List<Informe> informesDeDepartamento(Long clave) {
        return getSession().createQuery("select informes from Medico p where p.departamento.clave = :clave order by fechaInicio desc")
                .setParameter("clave", clave).list();
    }//fin informesDeDepartamento(Long clave)
    
    
    public List<Prueba> pruebasDeDepartamento(Long clave) {
        return getSession().createQuery("select p from Prueba p where p.plantilla.clave in (select l.clave from Plantilla l where l.departamento.clave = :clave) order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin medicosDeDepartamento(Long clave)
    
    
    public List<Plantilla> plantillasDeDepartamento(Long clave) {
        return getSession().createQuery("from Plantilla p where p.departamento.clave = :clave order by p.nombre asc")
                .setParameter("clave", clave).list();
    }//fin plantillasDeDepartamento(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase DepartamentoDaoHibernate