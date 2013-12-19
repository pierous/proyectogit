/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.paciente;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.pacienteservicio.PacienteServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.meses.Meses;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import java.util.Calendar;
import java.util.Locale;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class PerfilPaciente {


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
    private String medico;
    
    @Inject
    private Messages messages;
    
    @Inject
    private Locale locale;
    
    @Inject
    private PacienteServicio pacienteServicio;
    @Inject
    private AdministradorServicio administradorServicio;
    
    @InjectPage
    private CrearPaciente crearPaciente;
    
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
        return pacienteServicio.buscar(clave).getDireccion() != null;
    }//fin hayDireccion()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        Paciente paciente = pacienteServicio.buscar(clave);
        
        dni = paciente.getDni().toString();
        nombre = paciente.getNombre();
        apellido1 = paciente.getApellido1();
        apellido2 = paciente.getApellido2();
        dia = String.valueOf(paciente.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
        mes = messages.get(Meses.values()[paciente.getFechaNacimiento().get(Calendar.MONTH)].name());
        anho = String.valueOf(paciente.getFechaNacimiento().get(Calendar.YEAR));
        telefono = paciente.getTelefono();
        if (pacienteServicio.recuperarMedico(clave) != null) medico = pacienteServicio.recuperarMedico(clave).getNombreCompleto();
        if (paciente.getDireccion() != null) {
            calle = paciente.getDireccion().getCalle();
            numero = paciente.getDireccion().getNumero();
            piso = paciente.getDireccion().getPiso();
            letra = paciente.getDireccion().getLetra();
            codigoPostal = paciente.getDireccion().getCodigoPostal();
            localidad = paciente.getDireccion().getLocalidad();
            provincia = paciente.getDireccion().getProvincia();
        }//fin if (paciente.getDireccion() != null)
    }//fin onPrepareForRender()
    
    
    Object onActionFromEditar() {
        crearPaciente.setClave(clave);
        
        return crearPaciente;
    }//fin onSelectedFromBotonEditar()
    
    
    Object onActionFromEliminar() throws InstanciaNoEncontradaExcepcion {
        administradorServicio.eliminarPaciente(clave);
        
        return Pacientes.class;
    }//fin onSelectedFromBotonEliminar()

    /*------------------------------------------------------------------------*/


}//fin Clase PerfilPaciente