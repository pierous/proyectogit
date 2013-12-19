/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.departamento;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.informeservicio.InformeServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.pacienteservicio.PacienteServicio;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.pruebaservicio.PruebaServicio;
import es.udc.proyectogit.modelo.utiles.busqueda.FormatoFecha;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.meses.Meses;
import es.udc.proyectogit.web.pages.informe.PerfilInforme;
import es.udc.proyectogit.web.pages.medico.AsignarPrueba;
import es.udc.proyectogit.web.pages.medico.DocumentacionMedico;
import es.udc.proyectogit.web.pages.paciente.DocumentacionPaciente;
import es.udc.proyectogit.web.pages.prueba.PerfilPrueba;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
public class SupervisionDepartamento {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private int opcion = 1;
    @Property
    private boolean pacientes, medicos, informes, pruebas;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Integer visible;
    
    @Property
    private List lista;
    @Property
    private Paciente paciente;
    @Property
    private Medico medico;
    @Property
    private Informe informe;
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
    private int tipoFechaInforme, tipoFechaPrueba;
    @Persist(PersistenceConstants.FLASH)
    private Calendar fechaDesde, fechaHasta;
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String dniBusqueda, nombreBusqueda, apellido1Busqueda, apellido2Busqueda;
    
    @Property
    private List<String> dias;
    @Property
    private List<String> anhos;
    
    @Component
    private Form formularioBusqueda;
    @Property
    private BeanModel<Paciente> modeloPaciente;
    @Property
    private BeanModel<Medico> modeloMedico;
    @Property
    private BeanModel<Informe> modeloInforme;
    @Property
    private BeanModel<Prueba> modeloPrueba;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    @Inject
    private PropertyAccess access;
    
    @Inject
    private Locale locale;
    
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private DepartamentoServicio departamentoServicio;
    @Inject
    private MedicoServicio medicoServicio;
    @Inject
    private PacienteServicio pacienteServicio;
    @Inject
    private InformeServicio informeServicio;
    @Inject
    private PruebaServicio pruebaServicio;
    
    @InjectPage
    private DocumentacionPaciente documentacionPaciente;
    @InjectPage
    private DocumentacionMedico documentacionMedico;
    @InjectPage
    private PerfilInforme perfilInforme;
    @InjectPage
    private PerfilPrueba perfilPrueba;
    @InjectPage
    private AsignarPrueba asignarPrueba;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Medico getMedicoPaciente(Long clave) {
        return pacienteServicio.recuperarMedico(clave);
    }//fin getMedicoPaciente(Long clave)
    
    
    public Medico getMedicoInforme(Long clave) {
        if (informeServicio.recuperarMedico(clave) != null)
            return informeServicio.recuperarMedico(clave);
        else
            return null;
    }//fin getMedico(Long clave)
    
    
    public Medico getMedicoPrueba(Long clave) {
        if (pruebaServicio.recuperarMedico(clave) != null)
            return pruebaServicio.recuperarMedico(clave);
        else return null;
    }//fin getMedico(Long clave)
    
    
    public Medico getSolicitantePrueba(Long clave) {
        if (pruebaServicio.recuperarSolicitante(clave) != null)
        return pruebaServicio.recuperarSolicitante(clave);
        else return null;
    }//fin getSolicitantePrueba(Long clave)
    
    
    public Paciente getPacienteInforme(Long clave) {
        return informeServicio.recuperarPaciente(clave);
    }//fin getPaciente(Long clave)
    
    
    public Paciente getPacientePrueba(Long clave) {
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
    
    
    public boolean mostrarSegundoApellido() {
        return ((locale.getLanguage().equals("es")) || (locale.getLanguage().equals("gl")));
    }//fin mostrarSegundoApellido()
    
    
    public boolean esBusquedaPorFecha() {
        return (informes || pruebas);
    }//fin esBusquedaPorFecha()
    
    
    public boolean esBusquedaPorNombre() {
        return (pacientes || medicos);
    }//fin esBusquedaPorNombre()
    
    
    public boolean informeEliminable(Long claveInforme) throws InstanciaNoEncontradaExcepcion {
        return (informeServicio.recuperarMedico(claveInforme).getClave().equals(medSession.getUserId()) &&
                informeServicio.buscar(claveInforme).getFechaFin() == null);
    }//fin informeEliminable(Long claveInforme)
    
    
    public Object onEliminarInforme(Long claveInforme) throws InstanciaNoEncontradaExcepcion {
        medicoServicio.eliminarInforme(claveInforme);
        return this;
    }//fin onEliminarInforme()
    
    
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
    
    
    public Object onLinkPaciente(Long clave) {
        documentacionPaciente.setClave(clave);
        return documentacionPaciente;
    }//fin onActionFromLinkPaciente()
    
    
    public Object onLinkMedico(Long clave) {
        documentacionMedico.setClave(clave);
        return documentacionMedico;
    }//fin onActionFromLinkMedico()
    
    
    private List<Paciente> filtroPacientes() {
        String[] campos = new String[4];
        campos[0] = dniBusqueda;
        campos[1] = nombreBusqueda;
        campos[2] = apellido1Busqueda;
        campos[3] = apellido2Busqueda;
        
        return pacienteServicio.filtro(departamentoServicio.recuperarPacientes(clave), campos);
    }//fin List<Medico> seleccionarMedicos()
    
    
    private List<Medico> filtroMedicos() {
        String[] campos = new String[5];
        campos[0] = dniBusqueda;
        campos[1] = nombreBusqueda;
        campos[2] = apellido1Busqueda;
        campos[3] = apellido2Busqueda;
        campos[4] = null;
        
        return medicoServicio.filtro(departamentoServicio.recuperarMedicos(clave), campos);
    }//fin List<Medico> seleccionarMedicos()
    
    
    public void onCambiarOpcion(int i) {
        opcion = i;
        pacientes = medicos = informes = pruebas = false;
        switch (opcion) {
            case 1 :
                pacientes = true; break;
            case 2 :
                medicos = true; break;
            case 3 :
                informes = true; break;
            case 4 :
                pruebas = true; break;
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
        clave = medicoServicio.recuperarDepartamento(medSession.getUserId()).getClave();
        
        switch (opcion) {
            case 1:
                modeloPaciente = beanModelSource.createDisplayModel(Paciente.class, messages);
                modeloPaciente.add("dni", null);
                modeloPaciente.add("medico", null);
                if (mostrarSegundoApellido()) modeloPaciente.include("dni", "nombre", "apellido1", "apellido2", "medico");
                else modeloPaciente.include("dni", "nombre", "apellido1", "medico");
                for (String propertyName : modeloPaciente.getPropertyNames()) modeloPaciente.get(propertyName).sortable(false);
                lista = filtroPacientes();
                break;
            case 2:
                modeloMedico = beanModelSource.createDisplayModel(Medico.class, messages);
                modeloMedico.add("dni", null);
                if (mostrarSegundoApellido()) modeloMedico.include("dni", "nombre", "apellido1", "apellido2");
                else modeloMedico.include("dni", "nombre", "apellido1");
                for (String propertyName : modeloMedico.getPropertyNames()) modeloMedico.get(propertyName).sortable(false);
                lista = filtroMedicos();
                break;
            case 3:
                modeloInforme = beanModelSource.createDisplayModel(Informe.class, messages);
                modeloInforme.add("medico", null);
                modeloInforme.add("paciente", null);
                modeloInforme.add("acciones", null);
                modeloInforme.include("fechaInicio", "fechaFin", "paciente", "medico", "acciones");
                for (String propertyName : modeloInforme.getPropertyNames()) modeloInforme.get(propertyName).sortable(false);
                switch (visible) {
                    case 1 :
                        lista = informeServicio.filtro(tipoFechaInforme, departamentoServicio.recuperarInformes(clave), fechaDesde, fechaHasta); break;
                    case 2 :
                        lista = informeServicio.filtro(tipoFechaInforme, departamentoServicio.recuperarInformesIncompletos(clave), fechaDesde, fechaHasta); break;
                    case 3 :
                        lista = informeServicio.filtro(tipoFechaInforme, departamentoServicio.recuperarInformesFinalizados(clave), fechaDesde, fechaHasta); break;
                    default :
                        lista = null; break;
                }//fin switch (visible)
                break;
            case 4:
                modeloPrueba = beanModelSource.createDisplayModel(Prueba.class, messages);
                modeloPrueba.add("tipo", null);
                modeloPrueba.add("medico", null);
                modeloPrueba.add("solicitante", null);
                modeloPrueba.add("paciente", null);
                modeloPrueba.add("acciones", null);
                modeloPrueba.include("fechaSolicitud", "tipo", "paciente", "fechaInicio", "fechaFin", "medico", "solicitante", "acciones");
                for (String propertyName : modeloPrueba.getPropertyNames()) modeloPrueba.get(propertyName).sortable(false);
                switch (visible) {
                    case 1 :
                        lista = pruebaServicio.filtro(tipoFechaPrueba, departamentoServicio.recuperarPruebas(clave), fechaDesde, fechaHasta); break;
                    case 2 :
                        lista = pruebaServicio.filtro(tipoFechaPrueba, departamentoServicio.recuperarPruebasIncompletas(clave), fechaDesde, fechaHasta); break;
                    case 3 :
                        lista = pruebaServicio.filtro(tipoFechaPrueba, departamentoServicio.recuperarPruebasFinalizadas(clave), fechaDesde, fechaHasta); break;
                    default :
                        lista = null; break;
                }//fin switch (visible)
                break;
        }//fin switch (opcion)
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
        switch (opcion) {
            case 1:
                documentacionPaciente.setClave(clave);
                return documentacionPaciente;
            case 2:
                documentacionMedico.setClave(clave);
                return documentacionMedico;
            case 3:
                perfilInforme.setClave(clave);
                return perfilInforme;
            case 4:
                perfilPrueba.setClave(clave);
                return perfilPrueba;
            default:
                return this;
        }//fin switch (opcion)
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase SupervisionDepartamento