/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.prueba;


/*----------------------------------Imports-----------------------------------*/

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
public class Pruebas {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private int opcion = 1;
    @Property
    private boolean propias, solicitadas, todas;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Integer visible;
    
    @Property
    private List<Prueba> pruebas;
    @Property
    private Prueba prueba;
    
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
    
    @Property
    @SessionState(create=false)
    private MedSession medSession;
    
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
    
    @Inject
    private PruebaServicio pruebaServicio;
    @Inject
    private MedicoServicio medicoServicio;
    
    @InjectPage
    private DocumentacionMedico documentacionMedico;
    @InjectPage
    private DocumentacionPaciente documentacionPaciente;
    @InjectPage
    private PerfilPrueba perfilPrueba;
    @InjectPage
    private AsignarPrueba asignarPrueba;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Medico getMedico(Long clave) {
        if (pruebaServicio.recuperarMedico(clave) != null)
            return pruebaServicio.recuperarMedico(clave);
        else
            return null;
    }//fin getMedico(Long clave)
    
    
    public Medico getSolicitante(Long clave) {
        return pruebaServicio.recuperarSolicitante(clave);
    }//fin getSolicitante(Long clave)
    
    
    public Paciente getPaciente(Long clave) {
        return pruebaServicio.recuperarPaciente(clave);
    }//fin getPaciente(Long clave)
    
    
    public String getNombrePlantilla(Long clave) {
        if (pruebaServicio.recuperarPlantilla(clave) != null)
            return pruebaServicio.recuperarPlantilla(clave).getNombre();
        else return null;
    }//fin nombrePlantilla(Long idPrueba)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    int onPassivate() {
        return opcion;
    }//fin onPassivate()
    
    
    void onActivate(int opcion) {
        this.opcion = opcion;
    }//fin onActivate(int opcion)
    
    
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
    
    
    public Object onLinkMedico(Long clave) {
        documentacionMedico.setClave(clave);
        return documentacionMedico;
    }//fin onLinkMedico(Long clave)
    
    
    public Object onActionFromLinkPaciente(Long clave) {
        documentacionPaciente.setClave(clave);
        return documentacionPaciente;
    }//fin onActionFromLinkPaciente(Long clave)
    
    
    public void onCambiarOpcion(int i) {
        opcion = i;
        propias = false;
        solicitadas = false;
        todas = false;
        switch (opcion) {
            case 1 :
                propias = true; break;
            case 2 :
                solicitadas = true; break;
            case 3 :
                todas = true; break;
        }//fin switch (opcion)
    }//fin onCambiarOpcion(int i)
    
    
    private List<Prueba> seleccionarPruebas() {
        switch (opcion) {
            case 1 :
                switch (visible) {
                    case 1 :
                        return pruebaServicio.filtro(tipoFecha, medicoServicio.recuperarPruebas(medSession.getUserId()), fechaDesde, fechaHasta);
                    case 2 :
                        return pruebaServicio.filtro(tipoFecha, medicoServicio.recuperarPruebasIncompletas(medSession.getUserId()), fechaDesde, fechaHasta);
                    case 3 :
                        return pruebaServicio.filtro(tipoFecha, medicoServicio.recuperarPruebasFinalizadas(medSession.getUserId()), fechaDesde, fechaHasta);
                    default :
                        return null;
                }//fin switch (visible)
            case 2 :
                switch (visible) {
                    case 1 :
                        return pruebaServicio.filtro(tipoFecha, medicoServicio.recuperarPruebasSolicitadas(medSession.getUserId()), fechaDesde, fechaHasta);
                    case 2 :
                        return pruebaServicio.filtro(tipoFecha, medicoServicio.recuperarPruebasSolicitadasIncompletas(medSession.getUserId()), fechaDesde, fechaHasta);
                    case 3 :
                        return pruebaServicio.filtro(tipoFecha, medicoServicio.recuperarPruebasSolicitadasFinalizadas(medSession.getUserId()), fechaDesde, fechaHasta);
                    default :
                        return null;
                }//fin switch (visible)
            case 3 :
                switch (visible) {
                    case 1 :
                        return pruebaServicio.filtro(tipoFecha, pruebaServicio.listar(), fechaDesde, fechaHasta);
                    case 2 :
                        return pruebaServicio.filtro(tipoFecha, pruebaServicio.recuperarPruebasIncompletas(), fechaDesde, fechaHasta);
                    case 3 :
                        return pruebaServicio.filtro(tipoFecha, pruebaServicio.recuperarPruebasFinalizadas(), fechaDesde, fechaHasta);
                    default :
                        return null;
                }//fin switch (visible)
            default:
                return null;
        }//fin switch (opcion)
    }//fin seleccionarPruebas()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() {
        if (visible == null) visible = 1;
        onCambiarOpcion(opcion);
        dias = new ArrayList<String>();
        anhos = new ArrayList<String>();
        Calendar fecha = Calendar.getInstance();
        for (int i = 1; i < 32; i++) dias.add(String.valueOf(i));
        for (int i = 1900; i <= fecha.get(Calendar.YEAR); i++) anhos.add(String.valueOf(i));
        modelo = beanModelSource.createDisplayModel(Prueba.class, messages);
        
        modelo.add("medico", null);
        modelo.add("solicitante", null);
        modelo.add("tipo", null);
        modelo.add("paciente", null);
        modelo.add("acciones", null);
        if (propias)
            modelo.include("fechaSolicitud", "tipo", "paciente", "fechaInicio", "fechaFin", "solicitante", "acciones");
        else if (solicitadas)
            modelo.include("fechaSolicitud", "tipo", "paciente", "fechaInicio", "fechaFin", "medico", "acciones");
        else modelo.include("fechaSolicitud", "tipo", "paciente", "fechaInicio", "fechaFin", "solicitante", "medico", "acciones");
        
        for (String propertyName : modelo.getPropertyNames()) {
            modelo.get(propertyName).sortable(false);
        }//fin for (String propertyName : modelo.getPropertyNames())
        
        pruebas = seleccionarPruebas();
    }//fin setupRender()
    
    
    void onValidateFromFormularioBusqueda() {
        if (anhoDesde != null) {
            if (mesDesde == null) {
                mesDesde = Meses.ENERO;
                diaDesde = 1;
            }
            else if (diaDesde == null) diaDesde = 1;
            fechaDesde = Calendar.getInstance();
            fechaDesde.clear();
            fechaDesde.set(anhoDesde.intValue(), mesDesde.getNum(), diaDesde.intValue());
        }//fin if (anhoDesde != null)
        if (anhoHasta != null) {
            if (mesHasta == null) {
                mesHasta = Meses.DICIEMBRE;
                diaHasta = 31;
            }
            else if (diaHasta == null) diaHasta = 31;
            fechaHasta = Calendar.getInstance();
            fechaHasta.clear();
            fechaHasta.set(anhoHasta.intValue(), mesHasta.getNum(), diaHasta.intValue());
        }//fin if (anhoHasta != null)
    }//fin onValidateFromFormulario()
    
    
    public Object onRowClicked(Long clave) {
        perfilPrueba.setClave(clave);
        return perfilPrueba;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase Pruebas