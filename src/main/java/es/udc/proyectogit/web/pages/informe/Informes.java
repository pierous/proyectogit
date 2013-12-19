/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.informe;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.informeservicio.InformeServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.utiles.busqueda.FormatoFecha;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.meses.Meses;
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
public class Informes {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private int opcion = 1;
    @Property
    private boolean propios, todos;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Integer visible;
    
    @Property
    private List<Informe> informes;
    @Property
    private Informe Informe;
    
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
    private BeanModel<Informe> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    @Inject
    private PropertyAccess access;
    
    @Inject
    private InformeServicio informeServicio;
    @Inject
    private MedicoServicio medicoServicio;
    
    @InjectPage
    DocumentacionMedico documentacionMedico;
    @InjectPage
    DocumentacionPaciente documentacionPaciente;
    @InjectPage
    private PerfilInforme perfilInforme;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Medico getMedico(Long clave) {
        if (informeServicio.recuperarMedico(clave) != null)
            return informeServicio.recuperarMedico(clave);
        else
            return null;
    }//fin getMedico(Long clave)
    
    
    public Paciente getPaciente(Long clave) {
        return informeServicio.recuperarPaciente(clave);
    }//fin getPaciente(Long clave)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    int onPassivate() {
        return opcion;
    }//fin onPassivate()
    
    
    void onActivate(int opcion) {
        this.opcion = opcion;
    }//fin onActivate(int opcion)
    
    
    public boolean informeEliminable(Long claveInforme) throws InstanciaNoEncontradaExcepcion {
        return (informeServicio.recuperarMedico(claveInforme).getClave().equals(medSession.getUserId()) &&
                informeServicio.buscar(claveInforme).getFechaFin() == null);
    }//fin informeEliminable(Long claveInforme)
    
    
    public Object onEliminarInforme(Long claveInforme) throws InstanciaNoEncontradaExcepcion {
        medicoServicio.eliminarInforme(claveInforme);
        return this;
    }//fin onEliminarInforme()
    
    
    public Object onActionFromLinkMedico(Long clave) {
        documentacionMedico.setClave(clave);
        return documentacionMedico;
    }//fin onActionFromLinkMedico()
    
    
    public Object onActionFromLinkPaciente(Long clave) {
        documentacionPaciente.setClave(clave);
        return documentacionPaciente;
    }//fin onActionFromLinkMedico()
    
    
    public void onCambiarOpcion(int i) {
        opcion = i;
        propios = false;
        todos = false;
        if (opcion == 1)
            propios = true;
        else todos = true;
    }//fin onCambiarOpcion(int i)
    
    
    private List<Informe> seleccionarInformes() {
        if (opcion == 1) {
            switch (visible) {
                case 1:
                    return informeServicio.filtro(tipoFecha, medicoServicio.recuperarInformes(medSession.getUserId()), fechaDesde, fechaHasta);
                case 2:
                    return informeServicio.filtro(tipoFecha, medicoServicio.recuperarInformesIncompletos(medSession.getUserId()), fechaDesde, fechaHasta);
                case 3:
                    return informeServicio.filtro(tipoFecha, medicoServicio.recuperarInformesFinalizados(medSession.getUserId()), fechaDesde, fechaHasta);
                default:
                    return null;
            }//fin switch (visible)
        } else {
            switch (visible) {
                case 1:
                    return informeServicio.filtro(tipoFecha, informeServicio.listar(), fechaDesde, fechaHasta);
                case 2:
                    return informeServicio.filtro(tipoFecha, informeServicio.recuperarInformesIncompletos(), fechaDesde, fechaHasta);
                case 3:
                    return informeServicio.filtro(tipoFecha, informeServicio.recuperarInformesFinalizados(), fechaDesde, fechaHasta);
                default:
                    return null;
            }//fin switch (visible)
        }//fin if (opcion == 1)
    }//fin seleccionarInformes()

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
        modelo = beanModelSource.createDisplayModel(Informe.class, messages);
        
        modelo.add("medico", null);
        modelo.add("paciente", null);
        modelo.add("acciones", null);
        if (propios)
            modelo.include("fechaInicio", "fechaFin", "paciente", "acciones");
        else modelo.include("fechaInicio", "fechaFin", "paciente", "medico", "acciones");
        
        for (String propertyName : modelo.getPropertyNames()) {
            modelo.get(propertyName).sortable(false);
        }//fin for (String propertyName : modelo.getPropertyNames())
        
        informes = seleccionarInformes();
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
        perfilInforme.setClave(clave);
        return perfilInforme;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase Informes