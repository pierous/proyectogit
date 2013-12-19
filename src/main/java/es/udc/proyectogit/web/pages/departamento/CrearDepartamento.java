/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.departamento;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericSelectionModel;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericValueEncoder;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class CrearDepartamento {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private String nombre;
    @Property
    private Medico supervisor;
    
    @Component
    private Form formulario;
    @Inject
    private Messages mensajes;
    @Inject
    private PropertyAccess access;
    
    @Inject
    private AdministradorServicio administradorServicio;
    @Inject
    private DepartamentoServicio departamentoServicio;
    @Inject
    private MedicoServicio medicoServicio;
    private GenericoServicio servicio = medicoServicio;
    
    @Property
    private GenericSelectionModel<Medico> modeloSupervisor;
    @Property
    private GenericValueEncoder<Medico> encoderSupervisor = new GenericValueEncoder<Medico>(servicio, CrearDepartamento.class);
    
    @InjectPage
    private PerfilDepartamento perfilDepartamento;
    
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
        if (clave != null) {
            modeloSupervisor = new GenericSelectionModel<Medico>(departamentoServicio.recuperarMedicos(clave), "nombreCompleto", access);
            Departamento departamento = departamentoServicio.buscar(clave);
        
            nombre = departamento.getNombre();
            supervisor = departamentoServicio.recuperarSupervisor(clave);
        }//fin if (clave != null)
    }//fin onPrepareForRender()
    
    
    void onValidateFromFormulario() throws InstanciaNoEncontradaExcepcion {
        try {
            if (clave != null) {
                departamentoServicio.modificarDepartamento(clave, nombre, supervisor);
            }//fin if (clave != null)
            
            else administradorServicio.agregarDepartamento(nombre);
        } catch (InstanciaDuplicadaExcepcion ex) {
            formulario.recordError(mensajes.get("departamentoExistente-error"));
        }//fin try
    }//fin onValidateFromFormulario()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        if (clave == null)
            return Departamentos.class;
        else {
            perfilDepartamento.setClave(clave);
            return perfilDepartamento;
        }//fin else
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase CrearDepartamento