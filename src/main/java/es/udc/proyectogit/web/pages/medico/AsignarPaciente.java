/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.pacienteservicio.PacienteServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericSelectionModel;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericValueEncoder;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.web.pages.paciente.Pacientes;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_MEDS)
public class AsignarPaciente {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private String paciente;
    @Property
    private Medico medico;
    
    @Inject
    private PropertyAccess access;
    
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private PacienteServicio pacienteServicio;
    @Inject
    private DepartamentoServicio departamentoServicio;
    @Inject
    private MedicoServicio medicoServicio;
    private GenericoServicio servicio = medicoServicio;
    
    @Property
    private GenericSelectionModel<Medico> modeloMedico;
    @Property
    private GenericValueEncoder<Medico> encoderMedico = new GenericValueEncoder<Medico>(servicio, AsignarPaciente.class);
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Long getClave() {
        return clave;
    }//fin getId()
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setId(Long id)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    Long onPassivate() {
        return clave;
    }//fin onPassivate()
    
    
    void onActivate(Long clave) {
        this.clave = clave;
    }//fin onActivate(Long id)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        modeloMedico = new GenericSelectionModel<Medico>(departamentoServicio.recuperarMedicos(medicoServicio.recuperarDepSupervisado(medSession.getUserId()).getClave()), "nombreCompleto", access);
        Paciente pac = pacienteServicio.buscar(clave);
        
        paciente = pac.getNombreCompleto();
    }//fin setupRender()
    
    
    void onValidateFromFormulario() throws InstanciaNoEncontradaExcepcion {
        medicoServicio.asignarPacienteMedico(medico.getClave(), clave);
    }//fin onValidateFromFormulario()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        return Pacientes.class;
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase AsignarPaciente