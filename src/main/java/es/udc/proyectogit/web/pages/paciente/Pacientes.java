/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.paciente;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.pacienteservicio.PacienteServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.web.pages.medico.DocumentacionMedico;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import java.util.List;
import java.util.Locale;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class Pacientes {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private int opcion = 1;
    @Property
    private boolean propios, todos;
    
    @Property
    private List<Paciente> pacientes;
    @Property
    private Paciente paciente;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String dni, nombre, apellido1, apellido2;
    
    @Property
    private BeanModel<Paciente> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    
    @Inject
    private Locale locale;
    
    @Property
    @SessionState(create=false)
    private MedSession medSession;
    
    @InjectPage
    private PerfilPaciente perfilPaciente;
    @InjectPage
    private DocumentacionPaciente documentacionPaciente;
    @InjectPage
    DocumentacionMedico documentacionMedico;
    
    @Inject
    private PacienteServicio pacienteServicio;
    @Inject
    private MedicoServicio medicoServicio;
    
    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Medico getMedico(Long clave) {
        if (pacienteServicio.recuperarMedico(clave) != null)
            return pacienteServicio.recuperarMedico(clave);
        else
            return null;
    }//fin getMedico(Long clave)

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
    
    
    public void onCambiarOpcion(int i) {
        opcion = i;
    }//fin onCambiarOpcion(int i)
    
    
    private List<Paciente> seleccionarPacientes() {
        String[] campos = new String[4];
        campos[0] = dni;
        campos[1] = nombre;
        campos[2] = apellido1;
        campos[3] = apellido2;
        switch (opcion) {
            case 1 :
                propios = true;
                todos = false;
                return pacienteServicio.filtro(medicoServicio.recuperarPacientes(medSession.getUserId()), campos);
            default:
                todos = true;
                propios = false;
                return pacienteServicio.filtro(pacienteServicio.listar(), campos);
        }//fin switch (opcion)
    }//fin seleccionarPacientes()
    
    
    public boolean esAdministrador() throws InstanciaNoEncontradaExcepcion {
        if (medSession != null)
            return medicoServicio.esAdministrador(medSession.getUserId());
        else return false;
    }//fin esAdministrador()
    
    
    public boolean esDesvinculable(Long clavePaciente) throws InstanciaNoEncontradaExcepcion {
        Medico medico = pacienteServicio.recuperarMedico(clavePaciente);
        if (medico.getClave().equals(medSession.getUserId()))
            return true;
        Departamento departamento = medicoServicio.recuperarDepSupervisado(medSession.getUserId());
        if (departamento != null)
            if (departamento.getClave().equals(medicoServicio.recuperarDepartamento(medico.getClave()).getClave()))
                return true;
        return false;
    }//fin esDesvinculable(Long clavePaciente)
    
    
    public Object onActionFromLinkMedico(Long clave) {
        documentacionMedico.setClave(clave);
        return documentacionMedico;
    }//fin onActionFromLinkMedico()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        if (medSession == null) onCambiarOpcion(2);
        modelo = beanModelSource.createDisplayModel(Paciente.class, messages);
        
        modelo.add("dni", null);
        modelo.add("medico", null);
        modelo.add("acciones", null);
        
        if (opcion == 2 && medSession != null) modelo.include("dni", "nombre", "apellido1", "apellido2", "medico", "acciones");
        else if (medSession != null) modelo.include("dni", "nombre", "apellido1", "apellido2", "acciones");
        else modelo.include("dni", "nombre", "apellido1", "apellido2");
        
        if (!mostrarSegundoApellido()) modelo.exclude("apellido2");
        
        for (String propertyName : modelo.getPropertyNames()) {
            modelo.get(propertyName).sortable(false);
        }//fin for (String propertyName : modelo.getPropertyNames())

        pacientes = seleccionarPacientes();
    }//fin setupRender()
    
    
    public Object onRowClicked(Long clave) {
        if (medSession != null) {
            documentacionPaciente.setClave(clave);
            return documentacionPaciente;
        }//fin if (medSession != null)
        else {
            perfilPaciente.setClave(clave);
            return perfilPaciente;
        }//fin else (medSession != null)
    }//fin onRowClicked(Long clave)
    
    
    public void onActionFromAgregarPaciente(Long clave) throws InstanciaNoEncontradaExcepcion {
        if (medSession != null) medicoServicio.asignarPacienteMedico(medSession.getUserId(), clave);
    }//fin onActionFromAgregarPaciente(Long clave)
    
    
    public void onActionFromQuitarMedico(Long clave) throws InstanciaNoEncontradaExcepcion {
        if (esDesvinculable(clave)) medicoServicio.asignarPacienteMedico(null, clave);
    }//fin onActionFromQuitarMedico(Long clave)
    
    
    void onValidateFromFormularioBusqueda() {
    }//fin onValidateFromFormularioBusqueda()

    /*------------------------------------------------------------------------*/


}//fin Clase Pacientes