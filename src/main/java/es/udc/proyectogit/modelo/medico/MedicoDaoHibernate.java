/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("medicoDao")
public class MedicoDaoHibernate extends GenericoDaoHibernate<Medico, Long> implements MedicoDao {


    /*----------------------------Constructores-------------------------------*/
    
    public MedicoDaoHibernate() {
        super(Medico.class);
    }//fin DepartamentoDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Medico");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
        orden.add("apellido1");
        orden.add("apellido2");
        orden.add("nombre");
        orden.add("dni");
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Departamento departamentoDeMedico(Long clave) throws InstanciaNoEncontradaExcepcion {
        return (Departamento) getSession().createQuery("select departamento from Medico p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin departamentoDeMedico(Long clave)
    
    
    public Departamento depSupervisadoDeMedico(Long clave) {
        return (Departamento) getSession().createQuery("select depSupervisado from Medico p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin depSupervisadoDeMedico(Long clave)
    
    
    public List<Informe> informesDeMedico(Long clave) {
        return getSession().createQuery("select informes from Medico p where p.clave = :clave order by fechaInicio desc")
                .setParameter("clave", clave).list();
    }//fin informesDeMedico(Long clave)
    
    
    public List<Informe> informesPendientesDeMedico(Long clave) {
        return getSession().createQuery("from Informe p where p.medico.clave = :clave and fechaFin is null order by fechaInicio desc")
                .setParameter("clave", clave).list();
    }//fin informesPendientesDeMedico(Long clave)
    
    
    public List<Informe> informesFinalizadosDeMedico(Long clave) {
        return getSession().createQuery("from Informe p where p.medico.clave = :clave and fechaFin is not null order by fechaInicio desc")
                .setParameter("clave", clave).list();
    }//fin informesFinalizadosDeMedico(Long clave)
    
    
    public List<Prueba> pruebasDeMedico(Long clave) {
        return getSession().createQuery("select pruebas from Medico p where p.clave = :clave order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasDeMedico(Long clave)
    
    
    public List<Prueba> pruebasPendientesDeMedico(Long clave) {
        return getSession().createQuery("from Prueba p where p.medico.clave = :clave and fechaFin is null order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasPendientesDeMedico(Long clave)
    
    
    public List<Prueba> pruebasFinalizadasDeMedico(Long clave) {
        return getSession().createQuery("from Prueba p where p.medico.clave = :clave and fechaFin is not null order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasFinalizadasDeMedico(Long clave)
    
    
    public List<Prueba> pruebasSolicitadasDeMedico(Long clave) {
        return getSession().createQuery("select pruebasSolicitadas from Medico p where p.clave = :clave order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasSolicitadasDeMedico(Long clave)
    
    
    public List<Prueba> pruebasSolicitadasPendientesDeMedico(Long clave) {
        return getSession().createQuery("from Prueba p where p.solicitante.clave = :clave and fechaFin is null order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasSolicitadasPendientesDeMedico(Long clave)
    
    
    public List<Prueba> pruebasSolicitadasFinalizadasDeMedico(Long clave) {
        return getSession().createQuery("from Prueba p where p.solicitante.clave = :clave and fechaFin is not null order by fechaSolicitud desc")
                .setParameter("clave", clave).list();
    }//fin pruebasSolicitadasFinalizadasDeMedico(Long clave)
    
    
    public List<Paciente> pacientesDeMedico(Long clave) {
        Medico medico = (Medico) getSession().createQuery("from Medico m where m.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
        Query q = getSession().createFilter(medico.getPacientes(), "order by apellido1 asc, apellido2 asc, nombre asc");
        
        return q.list();
    }//fin pacientesDeMedico(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase MedicoDaoHibernate