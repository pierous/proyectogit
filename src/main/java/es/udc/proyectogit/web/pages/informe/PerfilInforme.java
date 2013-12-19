/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.informe;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.informeservicio.InformeServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.pruebaservicio.PruebaServicio;
import es.udc.proyectogit.modelo.utiles.busqueda.FormatoFecha;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.meses.Meses;
import es.udc.proyectogit.web.pages.medico.AsignarPrueba;
import es.udc.proyectogit.web.pages.medico.DocumentacionMedico;
import es.udc.proyectogit.web.pages.paciente.DocumentacionPaciente;
import es.udc.proyectogit.web.pages.prueba.CrearPrueba;
import es.udc.proyectogit.web.pages.prueba.PerfilPrueba;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.apache.tapestry5.services.BeanModelSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_MEDS)
public class PerfilInforme {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    private int opcion = 1;
    @Property
    private boolean opcionInfo, opcionPruebas;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Integer visible;
    
    @Property
    private String fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento;
    @Property
    private Medico medico;
    @Property
    private Paciente paciente;
    
    @Property
    private Prueba prueba;
    @Property
    private List<Prueba> pruebas;
    
    @Property
    private FormatoFecha formatoFecha = new FormatoFecha();
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Integer diaDesde, anhoDesde, diaHasta, anhoHasta;
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Meses mesDesde, mesHasta;
    @Persist(PersistenceConstants.FLASH)
    @Property
    private int tipoFecha;
    @Persist(PersistenceConstants.FLASH)
    private Calendar fechaDesde, fechaHasta;
    
    @Property
    private List<String> dias;
    @Property
    private List<String> anhos;
    
    @Component
    private Form formularioBusqueda;
    @Property
    private BeanModel<Prueba> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    @Inject
    private PropertyAccess access;
    
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private InformeServicio informeServicio;
    @Inject
    private PruebaServicio pruebaServicio;
    @Inject
    private MedicoServicio medicoServicio;
    
    @InjectPage
    private CrearInforme crearInforme;
    @InjectPage
    private CrearPrueba crearPrueba;
    @InjectPage
    private PerfilPrueba perfilPrueba;
    @InjectPage
    private DocumentacionMedico documentacionMedico;
    @InjectPage
    private DocumentacionPaciente documentacionPaciente;
    @InjectPage
    private AsignarPrueba asignarPrueba;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Long getClave() {
        return clave;
    }//fin getId()
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setId(Long id)
    
    
    public int getOpcion() {
        return opcion;
    }//fin getOpcion()
    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }//fin setOpcion(int opcion)
    
    
    public Medico getMedico(Long clave) {
        if (pruebaServicio.recuperarMedico(clave) != null)
            return pruebaServicio.recuperarMedico(clave);
        else return null;
    }//fin getMedico(Long clave)
    
    
    public Medico getSolicitante(Long clave) {
        if (pruebaServicio.recuperarSolicitante(clave) != null)
            return pruebaServicio.recuperarSolicitante(clave);
        else return null;
    }//fin getSolicitante(Long clave)
    
    
    public String getNombrePlantilla(Long clave) {
        if (pruebaServicio.recuperarPlantilla(clave) != null)
            return pruebaServicio.recuperarPlantilla(clave).getNombre();
        else return null;
    }//fin nombrePlantilla(Long idPrueba)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    Object onPassivate() {
        return new Object[] {clave, + opcion}; 
    }//fin onPassivate()
    
    
    void onActivate(Long clave, int opcion) {
        this.clave = clave;
        this.opcion = opcion;
    }//fin onActivate(Long id)
    
    
    public Object onLinkMedico(Long clave) {
        documentacionMedico.setClave(clave);
        return documentacionMedico;
    }//fin onLinkMedico(Long clave)
    
    
    public Object onLinkPaciente(Long clave) {
        documentacionPaciente.setClave(clave);
        documentacionPaciente.setOpcion(2);
        return documentacionPaciente;
    }//fin onLinkPaciente(Long clave)
    
    
    public boolean pruebaAgregable() {
        return (esEditable() && !informeServicio.esFinalizado(clave));
    }//fin pruebaAgregable()
    
    
    public Object onLinkCrearPrueba() {
        crearPrueba.setClaveInforme(clave);
        return crearPrueba;
    }//fin onLinkCrearPrueba()
    
    
    public boolean pruebaAsignable(Long clavePrueba) {
        return pruebaServicio.esAgregable(clavePrueba, medSession.getUserId());
    }//fin pruebaAsignable(Long clavePrueba)
    
    
    public Object onAgregarPrueba(Long clavePrueba) throws InstanciaNoEncontradaExcepcion {
        medicoServicio.asignarPruebaMedico(clavePrueba, medSession.getUserId());
        return this;
    }//fin onAgregarPrueba(Long clavePrueba)
    
    
    public boolean pruebaDesvinculable(Long clavePrueba) {
        return pruebaServicio.esDesvinculable(clavePrueba, medSession.getUserId());
    }//fin pruebaDesvinculable(Long clavePrueba)
    
    
    public Object onDesvincularPrueba(Long clavePrueba) throws InstanciaNoEncontradaExcepcion {
        medicoServicio.desvincularPrueba(clavePrueba);
        return this;
    }//fin onDesvincularPrueba(Long clavePrueba)
    
    
    public boolean pruebaEliminable(Long clavePrueba) throws InstanciaNoEncontradaExcepcion {
        return (pruebaServicio.esEliminable(clavePrueba, medSession.getUserId()));
    }//fin pruebaEliminable(Long clavePrueba)
    
    
    public Object onEliminarPrueba(Long clavePrueba) throws InstanciaNoEncontradaExcepcion {
        medicoServicio.eliminarPrueba(clavePrueba);
        return this;
    }//fin onEliminarInforme()
    
    
    public boolean esAdministrador() throws InstanciaNoEncontradaExcepcion {
        if (medSession != null)
            return medicoServicio.esAdministrador(medSession.getUserId());
        else return false;
    }//fin esAdministrador()
    
    
    public Object onAsignarPrueba(Long clavePrueba) {
        asignarPrueba.setClave(clavePrueba);
        return asignarPrueba;
    }//fin onAsignarPrueba(Long clavePrueba)
    
    
    public boolean esEditable() {
        return informeServicio.esEditable(clave, medSession.getUserId());
    }//fin esEditable()
    
    
    public boolean esFinalizable() throws InstanciaNoEncontradaExcepcion {
        return informeServicio.esFinalizable(clave, medSession.getUserId());
    }//fin esFinalizable()
    
    
    public void onCambiarOpcion(int i) {
        opcion = i;
        opcionInfo = opcionPruebas = false;
        switch (opcion) {
            case 1 :
                opcionInfo = true; break;
            case 2 :
                opcionPruebas = true; break;
        }//fin switch (opcion)
    }//fin onCambiarOpcion(int i)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        if (visible == null) visible = 1;
        onCambiarOpcion(opcion);
        dias = new ArrayList<String>();
        anhos = new ArrayList<String>();
        Calendar fech = Calendar.getInstance();
        for (int i = 1; i < 32; i++) dias.add(String.valueOf(i));
        for (int i = 1900; i <= fech.get(Calendar.YEAR); i++) anhos.add(String.valueOf(i));
        Informe informe = informeServicio.buscar(clave);
        
        fechaInicio = formatoFecha.formatearFecha(informe.getFechaInicio());
        fechaFin = formatoFecha.formatearFecha(informe.getFechaFin());
        medico = informeServicio.recuperarMedico(clave);
        paciente = informeServicio.recuperarPaciente(clave);
        observacionesPrevias = informe.getObservacionesPrevias();
        diagnostico = informe.getDiagnostico();
        tratamiento = informe.getTratamiento();
        
        if (opcionPruebas) {
            modelo = beanModelSource.createDisplayModel(Prueba.class, messages);
            modelo.add("acciones", null);
            modelo.add("tipo", null);
            modelo.add("medico", null);
            modelo.include("fechaSolicitud", "tipo", "medico", "fechaInicio", "fechaFin", "acciones");
        
            for (String propertyName : modelo.getPropertyNames()) {
                modelo.get(propertyName).sortable(false);
            }//fin for (String propertyName : modelo.getPropertyNames())
        
            switch (visible) {
                case 1:
                    pruebas = pruebaServicio.filtro(tipoFecha, informeServicio.recuperarPruebas(clave), fechaDesde, fechaHasta); break;
                case 2:
                    pruebas = pruebaServicio.filtro(tipoFecha, informeServicio.recuperarPruebasIncompletas(clave), fechaDesde, fechaHasta); break;
                case 3:
                    pruebas = pruebaServicio.filtro(tipoFecha, informeServicio.recuperarPruebasFinalizadas(clave), fechaDesde, fechaHasta); break;
                default:
                    pruebas = null; break;
            }//fin switch (visible)
        }//fin if (opcionPruebas)
    }//fin onPrepareForRender()
    
    
    void onValidateFromFormularioBusqueda() {
        if (anhoDesde != null && mesDesde != null && diaDesde != null) {
            fechaDesde = Calendar.getInstance();
            fechaDesde.clear();
            fechaDesde.set(anhoDesde.intValue(), mesDesde.getNum(), diaDesde.intValue());
        }//fin if (anhoDesde != null && mesDesde != null && diaDesde != null)
        if (anhoHasta != null && mesHasta != null && diaHasta != null) {
            fechaHasta = Calendar.getInstance();
            fechaHasta.clear();
            fechaHasta.set(anhoHasta.intValue(), mesHasta.getNum(), diaHasta.intValue());
        }//fin if (anhoHasta != null && mesHasta != null && diaHasta != null)
    }//fin onValidateFromFormulario()
    
    
    Object onActionFromEditar() {
        if (esEditable()) {
            crearInforme.setClave(clave);
            crearInforme.setClavePaciente(informeServicio.recuperarPaciente(clave).getClave());
            return crearInforme;
        }//fin if (esEditable())
        else return null;
    }//fin onSelectedFromBotonEditar()
    
    
    Object onActionFromFinalizar() throws InstanciaNoEncontradaExcepcion {
        if (esFinalizable()) {
            informeServicio.finalizarInforme(clave);
            return this;
        }//fin if (esFinalizable())
        else return null;
    }//fin onSelectedFromBotonEditar()
    
    
    public Object onRowClicked(Long clave) {
        perfilPrueba.setClave(clave);
        return perfilPrueba;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase PerfilInforme