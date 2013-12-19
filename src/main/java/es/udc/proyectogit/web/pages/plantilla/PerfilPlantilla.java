/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.plantilla;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.plantillaservicio.PlantillaServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.meses.Meses;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import java.util.Calendar;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class PerfilPlantilla {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private String nombre;
    @Property
    private String dia;
    @Property
    private String mes;
    @Property
    private String anho;
    @Property
    private String departamento;
    @Property
    private List<Parametro> parametros;
    @Property
    private Parametro parametro;
    
    @Property
    private BeanModel<Parametro> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    
    @Inject
    private PlantillaServicio plantillaServicio;
    @Inject
    private AdministradorServicio administradorServicio;
    
    @InjectPage
    private CrearPlantilla crearPlantilla;
    
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
        Plantilla plantilla = plantillaServicio.buscar(clave);
        
        nombre = plantilla.getNombre();
        dia = String.valueOf(plantilla.getFecha().get(Calendar.DAY_OF_MONTH));
        mes = messages.get(Meses.values()[plantilla.getFecha().get(Calendar.MONTH)].name());
        anho = String.valueOf(plantilla.getFecha().get(Calendar.YEAR));
        if (plantillaServicio.recuperarDepartamento(clave)!= null) departamento = plantillaServicio.recuperarDepartamento(clave).getNombre();
        parametros = plantillaServicio.recuperarParametros(clave);
    }//fin onPrepareForRender()
    
    
    Object onActionFromEditar() {
        crearPlantilla.setClave(clave);
        
        return crearPlantilla;
    }//fin onSelectedFromBotonEditar()
    
    
    Object onActionFromEliminar() throws InstanciaNoEncontradaExcepcion {
        administradorServicio.eliminarPlantilla(clave);
        
        return Plantillas.class;
    }//fin onSelectedFromBotonEliminar()

    /*------------------------------------------------------------------------*/


}//fin Clase PerfilPlantilla