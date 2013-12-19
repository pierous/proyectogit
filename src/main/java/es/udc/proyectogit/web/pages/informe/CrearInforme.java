/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.informe;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.informeservicio.InformeServicio;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Submit;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_MEDS)
public class CrearInforme {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private Long clavePaciente;
    
    @Property
    private String observacionesPrevias, diagnostico, tratamiento;
    
    @Component
    private Form formulario;
    @Component
    private Submit crear, editar;
    @Inject
    private Messages mensajes;
    @Inject
    private PropertyAccess access;
    
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private InformeServicio informeServicio;
    @Inject
    private MedicoServicio medicoServicio;
    
    @InjectPage
    private PerfilInforme perfilInforme;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Long getClave() {
        return clave;
    }//fin getId()
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setId(Long id)
    
    
    public Long getClavePaciente() {
        return clavePaciente;
    }//fin getPaciente()
    public void setClavePaciente(Long clavePaciente) {
        this.clavePaciente = clavePaciente;
    }//fin setClavePaciente(Long clavePaciente)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    Object onPassivate() {
        return new Object[] {clave, + clavePaciente};
    }//fin onPassivate()
    
    
    void onActivate(Long clave, Long clavePaciente) {
        this.clave = clave;
        this.clavePaciente = clavePaciente;
    }//fin onActivate(Long clave, Long clavePaciente)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion { 
        if (clave != null) {
            Informe informe = informeServicio.buscar(clave);
        
            observacionesPrevias = informe.getObservacionesPrevias();
            diagnostico = informe.getDiagnostico();
            tratamiento = informe.getTratamiento();
        }//fin if (clave != null)
    }//fin onPrepareForRender()
    
    
    void onSelectedFromCrear() throws InstanciaNoEncontradaExcepcion {
        clave = medicoServicio.crearInforme(clavePaciente, medSession.getUserId(), observacionesPrevias, null);
    }//fin onSelectedFromCrear()
    
    
    void onSelectedFromEditar() throws InstanciaNoEncontradaExcepcion {
        Informe informe = informeServicio.buscar(clave);
        informe.setObservacionesPrevias(observacionesPrevias);
        informe.setDiagnostico(diagnostico);
        informe.setTratamiento(tratamiento);
        informeServicio.actualizar(informe);
        if (informe.getFechaFin() != null) informeServicio.finalizarInforme(clave);
    }//finonSelectedFromEditar()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        perfilInforme.setClave(clave);
        return perfilInforme;
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase CrearInforme