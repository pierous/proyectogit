/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.paciente;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import java.util.List;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("pacienteDao")
public class PacienteDaoHibernate extends GenericoDaoHibernate<Paciente, Long> implements PacienteDao {


    /*----------------------------Constructores-------------------------------*/
    
    public PacienteDaoHibernate() {
        super(Paciente.class);
    }//fin DepartamentoDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Paciente");
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
    
    public Medico medicoDePaciente(Long idPaciente) {
        return (Medico) getSession().createQuery("select medico from Paciente p where p.clave = :clave")
                .setParameter("clave", idPaciente).uniqueResult();
    }//fin medicoDePaciente(Long idPaciente)
    
    
    public List<Informe> informesDePaciente(Long idPaciente) {
        return getSession().createQuery("select informes from Paciente p where p.clave = :clave order by fechaInicio desc")
                .setParameter("clave", idPaciente).list();
    }//fin informesDePaciente(Long idPaciente)
    
    
    public List<Informe> informesPendientesDePaciente(Long idPaciente) {
        return getSession().createQuery("from Informe p where p.paciente.clave = :clave and fechaFin is null order by fechaInicio desc")
                .setParameter("clave", idPaciente).list();
    }//fin informesPendientesDePaciente(Long idPaciente)
    
    
    public List<Informe> informesFinalizadosDePaciente(Long idPaciente) {
        return getSession().createQuery("from Informe p where p.paciente.clave = :clave and fechaFin is not null order by fechaInicio desc")
                .setParameter("clave", idPaciente).list();
    }//fin informesFinalizadosDePaciente(Long idPaciente)
    
    
    public List<Prueba> pruebasDePaciente(Long idPaciente) {
        return getSession().createQuery("select pruebas from Paciente p where p.clave = :clave order by fechaSolicitud desc")
                .setParameter("clave", idPaciente).list();
    }//fin pruebasDePaciente(Long idPaciente)
    
    
    public List<Prueba> pruebasPendientesDePaciente(Long idPaciente) {
        return getSession().createQuery("from Prueba p where p.paciente.clave = :clave and fechaFin is null order by fechaSolicitud desc")
                .setParameter("clave", idPaciente).list();
    }//fin pruebasPendientesDePaciente(Long idPaciente)
    
    
    public List<Prueba> pruebasFinalizadasDePaciente(Long idPaciente) {
        return getSession().createQuery("from Prueba p where p.paciente.clave = :clave and fechaFin is not null order by fechaSolicitud desc")
                .setParameter("clave", idPaciente).list();
    }//fin pruebasFinalizadasDePaciente(Long idPaciente)

    /*------------------------------------------------------------------------*/


}//fin Clase PacienteDaoHibernate