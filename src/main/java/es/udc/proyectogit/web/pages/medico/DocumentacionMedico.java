/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
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
public class DocumentacionMedico {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private int opcion = 1;
    @Property
    private boolean info, informes, pruebas, pruebasSolicitadas, pacientes;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Integer visible;
    
    @Property
    private String dni, nombreCompleto, fecha, departamento, calle, letra, localidad, provincia;
    @Property
    private int edad;
    @Property
    private Integer telefono, numero, piso, codigoPostal;
    
    @Property
    private List<Informe> listaInformes;
    @Property
    private Informe informe;
    
    @Property
    private List<Prueba> listaPruebas;
    @Property
    private Prueba prueba;
    
    @Property
    private List<Paciente> listaPacientes;
    @Property
    private Paciente paciente;
    
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
    private BeanModel<Informe> modeloInforme;
    @Property
    private BeanModel<Prueba> modeloPrueba;
    @Property
    private BeanModel<Paciente> modeloPaciente;
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
    private MedicoServicio medicoServicio;
    @Inject
    private InformeServicio informeServicio;
    @Inject
    private PruebaServicio pruebaServicio;
    @Inject
    private PacienteServicio pacienteServicio;
    
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
    
    public Long getClave() {
        return clave;
    }//fin getId()
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setId(Long id)
    
    
    public String getNombrePlantilla(Long clave) {
        return pruebaServicio.recuperarPlantilla(clave).getNombre();
    }//fin nombrePlantilla(Long idPrueba)
    
    
    public Paciente getPaciente(Long clave) {
        if (informes)
            return informeServicio.recuperarPaciente(clave);
        if (pruebas || pruebasSolicitadas)
            return pruebaServicio.recuperarPaciente(clave);
        return null;
    }//fin getPaciente(Long clave)
    
    
    public Medico getMedico(Long clave) {
        if (pruebaServicio.recuperarMedico(clave) != null)
            return pruebaServicio.recuperarMedico(clave);
        else
            return null;
    }//fin getMedico(Long clave)
    
    
    public Medico getSolicitante(Long clave) {
        if (pruebaServicio.recuperarSolicitante(clave) != null)
            return pruebaServicio.recuperarSolicitante(clave);
        else return null;
    }//fin getSolicitante(Long clave)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    Object onPassivate() {
        return new Object[] {clave, + opcion}; 
    }//fin onPassivate()
    
    
    void onActivate(Long clave, int opcion) {
        this.clave = clave;
        this.opcion = opcion;
    }//fin onActivate(Long clave, int opcion)
    
    
    public boolean mostrarSegundoApellido() {
        return ((locale.getLanguage().equals("es")) || (locale.getLanguage().equals("gl")));
    }//fin mostrarSegundoApellido()
    
    
    public boolean hayDireccion() throws InstanciaNoEncontradaExcepcion {
        return medicoServicio.buscar(clave).getDireccion() != null;
    }//fin hayDireccion()
    
    
    public boolean hayPruebas() {
        return (pruebas || pruebasSolicitadas);
    }//fin hayPruebas()
    
    
    public boolean esBusquedaPorFecha() {
        return (informes || pruebas || pruebasSolicitadas);
    }//fin esBusquedaPorFecha()
    
    
    public Object onLinkMedico(Long clave) {
        documentacionMedico.setClave(clave);
        return documentacionMedico;
    }//fin onLinkMedico(Long clave)
    
    
    public Object onLinkPaciente(Long clave) {
        documentacionPaciente.setClave(clave);
        return documentacionPaciente;
    }//fin onLinkPaciente(Long clave)
    
    
    public boolean informeEliminable(Long claveInforme) throws InstanciaNoEncontradaExcepcion {
        return (informeServicio.recuperarMedico(claveInforme) != null &&
                informeServicio.recuperarMedico(claveInforme).getClave().equals(medSession.getUserId()) &&
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
    
    
    public Object onEliminarPrueba(Long clavePrueba) throws InstanciaNoEncontradaExcepcion {
        medicoServicio.eliminarPrueba(clavePrueba);
        return this;
    }//fin onEliminarPrueba(Long clavePrueba)
    
    
    public boolean esAdministrador() throws InstanciaNoEncontradaExcepcion {
        if (medSession != null)
            return medicoServicio.esAdministrador(medSession.getUserId());
        else return false;
    }//fin esAdministrador()
    
    
    public Object onAsignarPrueba(Long clavePrueba) {
        asignarPrueba.setClave(clavePrueba);
        return asignarPrueba;
    }//fin onAsignarPrueba(Long clavePrueba)
    
    
    public boolean pruebaEliminable(Long clavePrueba) throws InstanciaNoEncontradaExcepcion {
        return (pruebaServicio.recuperarSolicitante(clavePrueba) != null &&
                pruebaServicio.recuperarSolicitante(clavePrueba).getClave().equals(medSession.getUserId()) &&
                pruebaServicio.buscar(clavePrueba).getFechaInicio() == null);
    }//fin pruebaEliminable(Long clavePrueba)
    
    
    public boolean esDesvinculable(Long clavePaciente) throws InstanciaNoEncontradaExcepcion {
        Medico medico = pacienteServicio.recuperarMedico(clavePaciente);
        if (medico.getClave().equals(medSession.getUserId()))
            return true;
        Departamento departamentoAux = medicoServicio.recuperarDepSupervisado(medSession.getUserId());
        if (departamento != null)
            if (departamentoAux.getClave().equals(medicoServicio.recuperarDepartamento(medico.getClave()).getClave()))
                return true;
        return false;
    }//fin esDesvinculable(Long clavePaciente)
    
    
    public void onActionFromQuitarMedico(Long clave) throws InstanciaNoEncontradaExcepcion {
        if (esDesvinculable(clave)) medicoServicio.asignarPacienteMedico(null, clave);
    }//fin onActionFromQuitarMedico(Long clave)
    
    
    private List<Paciente> filtroPacientes() {
        String[] campos = new String[4];
        campos[0] = dniBusqueda;
        campos[1] = nombreBusqueda;
        campos[2] = apellido1Busqueda;
        campos[3] = apellido2Busqueda;
        
        return pacienteServicio.filtro(medicoServicio.recuperarPacientes(clave), campos);
    }//fin filtroPacientes()
    
    
    public void onCambiarOpcion(int i) {
        opcion = i;
        info = informes = pruebas = pruebasSolicitadas = pacientes = false;
        switch (opcion) {
            case 1 :
                info = true; break;
            case 2 :
                informes = true; break;
            case 3 :
                pruebas = true; break;
            case 4 :
                pruebasSolicitadas = true; break;
            case 5 :
                pacientes = true; break;
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
        Medico medico = medicoServicio.buscar(clave);
        
        dni = medico.getDni().toString();
        nombreCompleto = medico.getNombreCompleto();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -medico.getFechaNacimiento().get(Calendar.YEAR));
        calendar.add(Calendar.MONTH, -medico.getFechaNacimiento().get(Calendar.MONTH));
        calendar.add(Calendar.DAY_OF_MONTH, -medico.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
        edad = calendar.get(Calendar.YEAR);
        
        if (info) {
            fecha = String.valueOf(medico.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
            fecha = fecha + " " + messages.get(Meses.values()[medico.getFechaNacimiento().get(Calendar.MONTH)].name());
            fecha = fecha + " " + String.valueOf(medico.getFechaNacimiento().get(Calendar.YEAR));
            departamento = medicoServicio.recuperarDepartamento(clave).getNombre();
            telefono = medico.getTelefono();
            if (medico.getDireccion() != null) {
                calle = medico.getDireccion().getCalle();
                numero = medico.getDireccion().getNumero();
                piso = medico.getDireccion().getPiso();
                letra = medico.getDireccion().getLetra();
                codigoPostal = medico.getDireccion().getCodigoPostal();
                localidad = medico.getDireccion().getLocalidad();
                provincia = medico.getDireccion().getProvincia();
            }//fin if (paciente.getDireccion() != null)
        }//fin if (info)
        
        if (informes) {
            modeloInforme = beanModelSource.createDisplayModel(Informe.class, messages);
        
            modeloInforme.add("paciente", null);
            modeloInforme.add("acciones", null);
            modeloInforme.include("fechaInicio", "fechaFin", "paciente", "acciones");
        
            for (String propertyName : modeloInforme.getPropertyNames()) {
                modeloInforme.get(propertyName).sortable(false);
            }//fin for (String propertyName : modelo.getPropertyNames())

            switch (visible) {
                case 1:
                    listaInformes = informeServicio.filtro(tipoFechaInforme, medicoServicio.recuperarInformes(clave), fechaDesde, fechaHasta); break;
                case 2:
                    listaInformes = informeServicio.filtro(tipoFechaInforme, medicoServicio.recuperarInformesIncompletos(clave), fechaDesde, fechaHasta); break;
                case 3:
                    listaInformes = informeServicio.filtro(tipoFechaInforme, medicoServicio.recuperarInformesFinalizados(clave), fechaDesde, fechaHasta); break;
                default:
                    listaInformes = null; break;
            }//fin switch (visible)
        }//fin if (informes)
        
        if (pruebas || pruebasSolicitadas) {
            modeloPrueba = beanModelSource.createDisplayModel(Prueba.class, messages);
        
            modeloPrueba.add("paciente", null);
            modeloPrueba.add("tipo", null);
            modeloPrueba.add("medico", null);
            modeloPrueba.add("solicitante", null);
            modeloPrueba.add("acciones", null);
            if (pruebas) modeloPrueba.include("fechaSolicitud", "tipo", "paciente", "fechaInicio", "fechaFin", "solicitante", "acciones");
            else modeloPrueba.include("fechaSolicitud", "tipo", "paciente", "fechaInicio", "fechaFin", "medico", "acciones");
        
            for (String propertyName : modeloPrueba.getPropertyNames()) {
                modeloPrueba.get(propertyName).sortable(false);
            }//fin for (String propertyName : modelo.getPropertyNames())

            if (pruebas) {
                switch (visible) {
                    case 1:
                        listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, medicoServicio.recuperarPruebas(clave), fechaDesde, fechaHasta); break;
                    case 2:
                        listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, medicoServicio.recuperarPruebasIncompletas(clave), fechaDesde, fechaHasta); break;
                    case 3:
                        listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, medicoServicio.recuperarPruebasFinalizadas(clave), fechaDesde, fechaHasta); break;
                    default:
                        listaPruebas = null; break;
                }//fin switch (visible)
            } else {
                switch (visible) {
                    case 1:
                        listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, medicoServicio.recuperarPruebasSolicitadas(clave), fechaDesde, fechaHasta); break;
                    case 2:
                        listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, medicoServicio.recuperarPruebasSolicitadasIncompletas(clave), fechaDesde, fechaHasta); break;
                    case 3:
                        listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, medicoServicio.recuperarPruebasSolicitadasFinalizadas(clave), fechaDesde, fechaHasta); break;
                    default:
                        listaPruebas = null; break;
                }//fin switch (visible)
            }//fin if (pruebas)
        }//fin if (pruebas || pruebasSolicitadas)
        
        if (pacientes) {
            modeloPaciente = beanModelSource.createDisplayModel(Paciente.class, messages);
        
            modeloPaciente.add("dni", null);
            modeloPaciente.add("acciones", null);
            if (mostrarSegundoApellido()) modeloPaciente.include("dni", "nombre", "apellido1", "apellido2", "acciones");
            else modeloPaciente.include("dni", "nombre", "apellido1", "acciones");
        
            for (String propertyName : modeloPaciente.getPropertyNames()) {
                modeloPaciente.get(propertyName).sortable(false);
            }//fin for (String propertyName : modelo.getPropertyNames())

            listaPacientes = filtroPacientes();
        }//fin if (pruebas)
    }//fin onPrepareForRender()
    
    
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
            case 2 :
                perfilInforme.setClave(clave);
                return perfilInforme;
            case 3 : case 4:
                perfilPrueba.setClave(clave);
                return perfilPrueba;
            case 5 :
                documentacionPaciente.setClave(clave);
                return documentacionPaciente;
            default :
                return this;
        }//fin switch (opcion)
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase DocumentacionMedico