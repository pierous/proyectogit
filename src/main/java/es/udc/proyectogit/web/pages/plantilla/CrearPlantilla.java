/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.plantilla;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametroservicio.ParametroServicio;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.plantillaservicio.PlantillaServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericSelectionModel;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericValueEncoder;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.web.pages.parametro.CrearParametro;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.apache.tapestry5.services.PageRenderLinkSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class CrearPlantilla {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property @Persist(PersistenceConstants.FLASH)
    private String nombre;
    @Property @Persist(PersistenceConstants.FLASH)
    private List<Parametro> parametros;
    @Property @Persist(PersistenceConstants.FLASH)
    private Departamento departamento;
    
    @Inject
    private PageRenderLinkSource pageRenderLinkSource;
    private boolean volver = false;
    @Inject
    private ComponentResources componentResources;
    
    @Component
    private Form formulario;
    @Inject
    private Messages mensajes;
    @Inject
    private PropertyAccess access;
    
    @Inject
    private AdministradorServicio administradorServicio;
    @Inject
    private PlantillaServicio plantillaServicio;
    @Inject
    private ParametroServicio parametroServicio;
    private GenericoServicio servicio = parametroServicio;
    @Inject
    private DepartamentoServicio departamentoServicio;
    private GenericoServicio servicio2 = departamentoServicio;
    
    @Property
    private GenericSelectionModel<Parametro> modeloParametro;
    @Property
    private final GenericValueEncoder<Parametro> encoderParametro = new GenericValueEncoder<Parametro>(servicio, CrearPlantilla.class);
    @Property
    private GenericSelectionModel<Departamento> modeloDepartamento;
    @Property
    private final GenericValueEncoder<Departamento> encoderDepartamento = new GenericValueEncoder<Departamento>(servicio2, CrearPlantilla.class);
    
    @InjectPage
    private PerfilPlantilla perfilPlantilla;
    @InjectPage
    private CrearParametro crearParametro;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Long getClave() {
        return clave;
    }//fin getId()
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setId(Long id)
    
    public void setNuevaPlantilla() {
        componentResources.discardPersistentFieldChanges();
    }//fin setNuevaPlantilla()

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
        modeloParametro = new GenericSelectionModel<Parametro>(administradorServicio.listarParametros(), "nombre", access);
        modeloDepartamento = new GenericSelectionModel<Departamento>(administradorServicio.listarDepartamentos(), "nombre", access);
        
        if (clave != null) {
            Plantilla plantilla = plantillaServicio.buscar(clave);
        
            nombre = plantilla.getNombre();
            departamento = plantillaServicio.recuperarDepartamento(clave);
            parametros = plantillaServicio.recuperarParametros(clave);
        }//fin if (clave != null)
    }//fin onPrepareForRender()
    
    
    void onSelectedFromAgregarParametro() {
        volver = true;
    }//fin onActionFromAgregarParametro()
    
    
    void onValidateFromFormulario() throws InstanciaNoEncontradaExcepcion {
        if (!volver) try {
            if (clave != null) {
                plantillaServicio.modificarPlantilla(clave, nombre, departamento, parametros);
            }//fin if (clave != null)
            
            else administradorServicio.agregarPlantilla(nombre, departamento, parametros);
        } catch (InstanciaDuplicadaExcepcion ex) {
            formulario.recordError(mensajes.get("plantillaExistente-error"));
        }//fin try
    }//fin onValidateFromFormulario()
    
    
    Object onSuccess() {
        if (volver) {
            volver = false;
            Link estaPagina = pageRenderLinkSource.createPageRenderLinkWithContext(this.getClass(), onPassivate());
            crearParametro.set(estaPagina);
            return crearParametro;
        }
        if (clave == null)
            return Plantillas.class;
        else {
            perfilPlantilla.setClave(clave);
            return perfilPlantilla;
        }//fin else
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase CrearPlantilla