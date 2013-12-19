/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.prueba;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.plantillaservicio.PlantillaServicio;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.pruebaservicio.PruebaServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericSelectionModel;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericValueEncoder;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.modelo.valor.Valor;
import es.udc.proyectogit.modelo.valorservicio.ValorServicio;
import es.udc.proyectogit.web.pages.informe.PerfilInforme;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import java.util.HashSet;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Submit;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.apache.tapestry5.services.BeanModelSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_MEDS)
public class CrearPrueba {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private Long claveInforme;
    
    @Property
    private String observacionesSolicitante, observaciones, valorVacio;
    @Property
    private Plantilla plantilla;
    @Property
    private Valor valor;
    @Property
    private List<Valor> valores;
    
    @Component
    private Form formulario;
    @Component
    private Submit crear, editar;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages mensajes;
    @Inject
    private PropertyAccess access;
    
    @Inject
    private MedicoServicio medicoServicio;
    @Inject
    private PruebaServicio pruebaServicio;
    @Inject
    private ValorServicio valorServicio;
    @Inject
    private PlantillaServicio plantillaServicio;
    private GenericoServicio servicio = plantillaServicio;
    
    @Property
    private BeanModel<Valor> modeloValor;
    @Property
    private GenericSelectionModel<Plantilla> modeloPlantilla;
    @Property
    private GenericValueEncoder<Plantilla> encoderPlantilla = new GenericValueEncoder<Plantilla>(servicio, CrearPrueba.class);
    
    @InjectPage
    private PerfilPrueba perfilPrueba;
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
    
    
    public Long getClaveInforme() {
        return claveInforme;
    }//fin getInforme()
    public void setClaveInforme(Long claveInforme) {
        this.claveInforme = claveInforme;
    }//fin setClaveInforme(Long claveInforme)
    
    
    public String getNombreValor(Long claveValor) {
        return valorServicio.recuperarTipo(claveValor).getNombre();
    }//fin getNombreValor(Long claveValor)
    
    
    public String getUnidadesValor(Long claveValor) {
        if (valorServicio.existe(claveValor)) return valorServicio.recuperarTipo(claveValor).getUnidadAbreviada();
        else return null;
    }//fin getUnidadesValor(Long claveValor)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    Object onPassivate() {
        return new Object[] {clave, + claveInforme};
    }//fin onPassivate()
    
    
    void onActivate(Long clave, Long claveInforme) {
        this.clave = clave;
        this.claveInforme = claveInforme;
    }//fin onActivate(Long clave, Long claveInforme)
    
    
    public boolean esFinalizado() {
        return pruebaServicio.esFinalizado(clave);
    }//fin esFinalizado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        modeloPlantilla = new GenericSelectionModel<Plantilla>(plantillaServicio.listar(), "nombre", access);
        
        if (clave != null) {
            Prueba prueba = pruebaServicio.buscar(clave);
            if (prueba.getFechaInicio() == null) pruebaServicio.iniciarPrueba(clave);
        
            observacionesSolicitante = prueba.getObservacionesSolicitante();
            observaciones = prueba.getObservaciones();
            plantilla = pruebaServicio.recuperarPlantilla(clave);
            
            modeloValor = beanModelSource.createDisplayModel(Valor.class, mensajes);
            modeloValor.add("tipo", null);
            modeloValor.add("unidades", null);
            modeloValor.include("tipo", "dato", "unidades");
            
            for (String propertyName : modeloValor.getPropertyNames()) {
                modeloValor.get(propertyName).sortable(false);
            }//fin for (String propertyName : modelo.getPropertyNames())
        
            valores = pruebaServicio.recuperarValores(clave);
        }//fin if (clave != null)
    }//fin onPrepareForRender()
    
    
    void onPrepareForSubmit() {
        valores = pruebaServicio.recuperarValores(clave);
    }//fin onPrepareForSubmit()
    
    
    void onSelectedFromCrear() throws InstanciaNoEncontradaExcepcion {
        medicoServicio.agregarPrueba(claveInforme, plantilla.getClave(), observacionesSolicitante);
    }//fin onSelectedFromCrear()
    
    
    void onSelectedFromEditar() throws InstanciaNoEncontradaExcepcion {
        Prueba prueba = pruebaServicio.buscar(clave);
        prueba.setObservaciones(observaciones);
        prueba.setValores(new HashSet(valores));
        pruebaServicio.modificarPrueba(prueba);
        if (esFinalizado()) pruebaServicio.finalizarPrueba(clave);
    }//finonSelectedFromEditar()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        if (clave == null) {
            perfilInforme.setClave(claveInforme);
            perfilInforme.setOpcion(2);
            return perfilInforme;
        }//fin if (clave == null)
        else {
            perfilPrueba.setClave(clave);
            return perfilPrueba;
        }//fin else (clave == null)
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase CrearPrueba