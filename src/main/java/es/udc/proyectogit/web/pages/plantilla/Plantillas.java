/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.plantilla;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.plantillaservicio.PlantillaServicio;
import es.udc.proyectogit.modelo.utiles.busqueda.FormatoFecha;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericSelectionModel;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericValueEncoder;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import java.util.List;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.apache.tapestry5.services.BeanModelSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class Plantillas {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private List<Plantilla> plantillas;
    @Property
    private Plantilla plantilla;
    
    @Property
    private FormatoFecha formatoFecha = new FormatoFecha();
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String nombreBusqueda;
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Departamento departamentoBusqueda;
    
    @Property
    private BeanModel<Plantilla> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    @Inject
    private PropertyAccess access;
    
    @InjectPage
    private PerfilPlantilla perfilPlantilla;
    @InjectPage
    private CrearPlantilla crearPlantilla;
    
    @Inject
    private PlantillaServicio plantillaServicio;
    @Inject
    private DepartamentoServicio departamentoServicio;
    private GenericoServicio servicio = departamentoServicio;
    
    @Property
    private GenericSelectionModel<Departamento> modeloDepartamento;
    @Property
    private GenericValueEncoder<Departamento> encoderDepartamento = new GenericValueEncoder<Departamento>(servicio, Plantillas.class);
    
    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Gets y Sets--------------------------------*/
    
    public String getDepartamento(Long clave) throws InstanciaNoEncontradaExcepcion {
        if (plantillaServicio.recuperarDepartamento(clave) != null)
            return plantillaServicio.recuperarDepartamento(clave).getNombre();
        else return null;
    }//fin getDepartamento(Long clave)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Object onActionFromAgregarPlantilla() {
        crearPlantilla.setNuevaPlantilla();
        return crearPlantilla;
    }//fin onActionFromAgregarPlantilla()
    
    
    void setupRender() {
        modeloDepartamento = new GenericSelectionModel<Departamento>(departamentoServicio.listar(), "nombre", access);
        modelo = beanModelSource.createDisplayModel(Plantilla.class, messages);
        
        modelo.add("departamento", null);
        
        modelo.include("nombre", "fecha", "departamento");
        
        for (String propertyName : modelo.getPropertyNames()) {
            modelo.get(propertyName).sortable(false);
        }//fin for (String propertyName : modelo.getPropertyNames())
        
        if (departamentoBusqueda != null)
            plantillas = plantillaServicio.filtro(plantillaServicio.listar(), nombreBusqueda, departamentoBusqueda.getClave());
        else plantillas = plantillaServicio.filtro(plantillaServicio.listar(), nombreBusqueda, null);
    }//fin setupRender()
    
    
    public Object onRowClicked(Long clave) {
        perfilPlantilla.setClave(clave);
        return perfilPlantilla;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase Plantillas