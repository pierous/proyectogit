/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.meses.Meses;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import java.util.Calendar;
import java.util.Locale;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class PerfilMedico {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private String dni;
    @Property
    private String nombre;
    @Property
    private String apellido1;
    @Property
    private String apellido2;
    @Property
    private String nombreLogin;
    @Property
    private String dia;
    @Property
    private String mes;
    @Property
    private String anho;
    @Property
    private Integer telefono;
    @Property
    private String calle;
    @Property
    private Integer numero;
    @Property
    private Integer piso;
    @Property
    private String letra;
    @Property
    private Integer codigoPostal;
    @Property
    private String localidad;
    @Property
    private String provincia;
    @Property
    private Departamento departamento;
    
    @Inject
    private Messages messages;
    
    @Inject
    private Locale locale;
    
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private MedicoServicio medicoServicio;
    @Inject
    private AdministradorServicio administradorServicio;
    
    @InjectPage
    private CrearMedico crearMedico;
    
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
    
    
    public boolean mostrarSegundoApellido() {
        return ((locale.getLanguage().equals("es")) || (locale.getLanguage().equals("gl")));
    }//fin mostrarSegundoApellido()
    
    
    public boolean hayDireccion() throws InstanciaNoEncontradaExcepcion {
        return medicoServicio.buscar(clave).getDireccion() != null;
    }//fin hayDireccion()
    
    
    public boolean esEditable() {
        return (medSession == null || esLogueado());
    }//fin esEditable()
    
    
    public boolean esEliminable() {
        return (medSession == null);
    }//fin esEliminable()
    
    
    public boolean esLogueado() {
        return (medSession != null && medSession.getUserId().equals(clave));
    }//fin esLogueado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        if (medSession != null && !medSession.getUserId().equals(clave))
            clave = medSession.getUserId();
        
        Medico medico = medicoServicio.buscar(clave);
        
        dni = medico.getDni().toString();
        nombre = medico.getNombre();
        apellido1 = medico.getApellido1();
        apellido2 = medico.getApellido2();
        nombreLogin = medico.getNombreLogin();
        dia = String.valueOf(medico.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
        mes = messages.get(Meses.values()[medico.getFechaNacimiento().get(Calendar.MONTH)].name());
        anho = String.valueOf(medico.getFechaNacimiento().get(Calendar.YEAR));
        telefono = medico.getTelefono();
        departamento = medicoServicio.recuperarDepartamento(clave);
        if (medico.getDireccion() != null) {
            calle = medico.getDireccion().getCalle();
            numero = medico.getDireccion().getNumero();
            piso = medico.getDireccion().getPiso();
            letra = medico.getDireccion().getLetra();
            codigoPostal = medico.getDireccion().getCodigoPostal();
            localidad = medico.getDireccion().getLocalidad();
            provincia = medico.getDireccion().getProvincia();
        }//fin if (medico.getDireccion() != null)
    }//fin setupRender()
    
    
    Object onActionFromEditar() {
        if (!esEditable()) return this;
        crearMedico.setClave(clave);
        
        return crearMedico;
    }//fin onSelectedFromBotonEditar()
    
    
    Object onActionFromEliminar() throws InstanciaNoEncontradaExcepcion {
        if (!esEliminable()) return this;
        administradorServicio.eliminarMedico(clave);
        
        return Medicos.class;
    }//fin onSelectedFromBotonEliminar()

    /*------------------------------------------------------------------------*/


}//fin Clase PerfilMedico