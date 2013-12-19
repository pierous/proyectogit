/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.pruebaservicio.PruebaServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericSelectionModel;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericValueEncoder;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.web.pages.prueba.PerfilPrueba;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_MEDS)
public class AsignarPrueba {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private String paciente, codigoPrueba, tipoPrueba;
    @Property
    private Medico medico;
    
    @Inject
    private PropertyAccess access;
    
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private PruebaServicio pruebaServicio;
    @Inject
    private DepartamentoServicio departamentoServicio;
    @Inject
    private MedicoServicio medicoServicio;
    private GenericoServicio servicio = medicoServicio;
    
    @Property
    private GenericSelectionModel<Medico> modeloMedico;
    @Property
    private GenericValueEncoder<Medico> encoderMedico = new GenericValueEncoder<Medico>(servicio, AsignarPaciente.class);
    
    @InjectPage
    private PerfilPrueba perfilPrueba;
    
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
    }//fin onActivate(Long clave)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        modeloMedico = new GenericSelectionModel<Medico>(departamentoServicio.recuperarMedicos(medicoServicio.recuperarDepSupervisado(medSession.getUserId()).getClave()), "nombreCompleto", access);
        codigoPrueba = pruebaServicio.buscar(clave).getClave().toString();
        paciente = pruebaServicio.recuperarPaciente(clave).getNombreCompleto();
        tipoPrueba = pruebaServicio.recuperarPlantilla(clave).getNombre();
        
    }//fin setupRender()
    
    
    void onValidateFromFormulario() throws InstanciaNoEncontradaExcepcion {
        medicoServicio.asignarPruebaMedico(clave, medico.getClave());
    }//fin onValidateFromFormulario()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        perfilPrueba.setClave(clave);
        return perfilPrueba;
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase AsignarPrueba