/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.paciente;


/*----------------------------------Imports-----------------------------------*/

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
import es.udc.proyectogit.web.pages.informe.CrearInforme;
import es.udc.proyectogit.web.pages.informe.PerfilInforme;
import es.udc.proyectogit.web.pages.medico.AsignarPrueba;
import es.udc.proyectogit.web.pages.medico.DocumentacionMedico;
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
public class DocumentacionPaciente {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    private int opcion = 1;
    @Property
    private boolean info, informes, pruebas;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Integer visible;
    
    @Property
    private String dni, nombreCompleto, fecha, calle, letra, localidad, provincia;
    @Property
    private int edad;
    @Property
    private Medico medico;
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
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    @Inject
    private PropertyAccess access;
    
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private PacienteServicio pacienteServicio;
    @Inject
    private InformeServicio informeServicio;
    @Inject
    private PruebaServicio pruebaServicio;
    @Inject
    private MedicoServicio medicoServicio;
    
    @InjectPage
    private DocumentacionMedico documentacionMedico;
    @InjectPage
    private CrearInforme crearInforme;
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
    
    
    public int getOpcion() {
        return opcion;
    }//fin getOpcion()
    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }//fin setOpcion(int opcion)
    
    
    public Medico getMedico(Long clave) {
        if (informes)
            if (informeServicio.recuperarMedico(clave) != null)
                return informeServicio.recuperarMedico(clave);
        if (pruebas)
            if (pruebaServicio.recuperarMedico(clave) != null)
                return pruebaServicio.recuperarMedico(clave);
        return null;
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
    
    
    public boolean hayDireccion() throws InstanciaNoEncontradaExcepcion {
        return pacienteServicio.buscar(clave).getDireccion() != null;
    }//fin hayDireccion()
    
    
    public Object onLinkMedico(Long clave) {
        documentacionMedico.setClave(clave);
        return documentacionMedico;
    }//fin onLinkMedico(Long clave)
    
    
    public Object onLinkCrearInforme() {
        crearInforme.setClavePaciente(clave);
        return crearInforme;
    }//fin onLinkCrearInforme()
    
    
    public boolean informeEliminable(Long claveInforme) throws InstanciaNoEncontradaExcepcion {
        return (informeServicio.recuperarMedico(claveInforme) != null &&
                informeServicio.recuperarMedico(claveInforme).getClave().equals(medSession.getUserId()) &&
                informeServicio.buscar(claveInforme).getFechaFin() == null);
    }//fin informeEliminable(Long claveInforme)
    
    
    public Object onEliminarInforme(Long claveInforme) throws InstanciaNoEncontradaExcepcion {
        medicoServicio.eliminarInforme(claveInforme);
        return this;
    }//fin onEliminarInforme()
    
    
    public boolean pruebaEliminable(Long clavePrueba) throws InstanciaNoEncontradaExcepcion {
        return (pruebaServicio.recuperarSolicitante(clavePrueba) != null &&
                pruebaServicio.recuperarSolicitante(clavePrueba).getClave().equals(medSession.getUserId()) &&
                pruebaServicio.buscar(clavePrueba).getFechaInicio() == null);
    }//fin pruebaEliminable(Long clavePrueba)
    
    
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
    
    
    public void onCambiarOpcion(int i) {
        opcion = i;
        info = false;
        informes = false;
        pruebas = false;
        switch (opcion) {
            case 1 :
                info = true; break;
            case 2 :
                informes = true; break;
            case 3 :
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
        Paciente paciente = pacienteServicio.buscar(clave);
        
        dni = paciente.getDni().toString();
        nombreCompleto = paciente.getNombreCompleto();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -paciente.getFechaNacimiento().get(Calendar.YEAR));
        calendar.add(Calendar.MONTH, -paciente.getFechaNacimiento().get(Calendar.MONTH));
        calendar.add(Calendar.DAY_OF_MONTH, -paciente.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
        edad = calendar.get(Calendar.YEAR);
        if (pacienteServicio.recuperarMedico(clave) != null) medico = pacienteServicio.recuperarMedico(clave);
        
        if (info) {
            fecha = String.valueOf(paciente.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
            fecha = fecha + " " + messages.get(Meses.values()[paciente.getFechaNacimiento().get(Calendar.MONTH)].name());
            fecha = fecha + " " + String.valueOf(paciente.getFechaNacimiento().get(Calendar.YEAR));
            telefono = paciente.getTelefono();
            if (paciente.getDireccion() != null) {
                calle = paciente.getDireccion().getCalle();
                numero = paciente.getDireccion().getNumero();
                piso = paciente.getDireccion().getPiso();
                letra = paciente.getDireccion().getLetra();
                codigoPostal = paciente.getDireccion().getCodigoPostal();
                localidad = paciente.getDireccion().getLocalidad();
                provincia = paciente.getDireccion().getProvincia();
            }//fin if (paciente.getDireccion() != null)
        }//fin if (info)
        
        if (informes) {
            modeloInforme = beanModelSource.createDisplayModel(Informe.class, messages);
        
            modeloInforme.add("medico", null);
            modeloInforme.add("acciones", null);
            modeloInforme.include("fechaInicio", "fechaFin", "medico", "acciones");
        
            for (String propertyName : modeloInforme.getPropertyNames()) {
                modeloInforme.get(propertyName).sortable(false);
            }//fin for (String propertyName : modelo.getPropertyNames())

            switch (visible) {
                case 1:
                    listaInformes = informeServicio.filtro(tipoFechaInforme, pacienteServicio.recuperarInformes(clave), fechaDesde, fechaHasta); break;
                case 2:
                    listaInformes = informeServicio.filtro(tipoFechaInforme, pacienteServicio.recuperarInformesPendientes(clave), fechaDesde, fechaHasta); break;
                case 3:
                    listaInformes = informeServicio.filtro(tipoFechaInforme, pacienteServicio.recuperarInformesFinalizados(clave), fechaDesde, fechaHasta); break;
                default:
                    listaInformes = null; break;
            }//fin switch (visible)
        }//fin if (informes)
        
        if (pruebas) {
            modeloPrueba = beanModelSource.createDisplayModel(Prueba.class, messages);
        
            modeloPrueba.add("medico", null);
            modeloPrueba.add("solicitante", null);
            modeloPrueba.add("tipo", null);
            modeloPrueba.add("acciones", null);
            modeloPrueba.include("fechaSolicitud", "tipo", "fechaInicio", "fechaFin", "medico", "solicitante", "acciones");
        
            for (String propertyName : modeloPrueba.getPropertyNames()) {
                modeloPrueba.get(propertyName).sortable(false);
            }//fin for (String propertyName : modelo.getPropertyNames())

            switch (visible) {
                case 1:
                    listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, pacienteServicio.recuperarPruebas(clave), fechaDesde, fechaHasta); break;
                case 2:
                    listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, pacienteServicio.recuperarPruebasPendientes(clave), fechaDesde, fechaHasta); break;
                case 3:
                    listaPruebas = pruebaServicio.filtro(tipoFechaPrueba, pacienteServicio.recuperarPruebasFinalizadas(clave), fechaDesde, fechaHasta); break;
                default:
                    listaPruebas = null; break;
            }//fin switch (visible)
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
            case 3 :
                perfilPrueba.setClave(clave);
                return perfilPrueba;
            default :
                return null;
        }//fin switch (opcion)
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase DocumentacionPaciente