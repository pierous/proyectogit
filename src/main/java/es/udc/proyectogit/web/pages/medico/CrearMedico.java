/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.VerificacionPasswordExcepcion;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericSelectionModel;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericValueEncoder;
import es.udc.proyectogit.modelo.utiles.meses.Meses;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class CrearMedico {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private String nombre;
    @Property
    private String apellido1;
    @Property
    private String apellido2;
    @Property
    private String dia;
    @Property
    private Meses mes;
    @Property
    private String anho;
    @Property
    private String dni;
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
    @Property
    private String nombreLogin;
    @Property
    private String password;
    @Property
    private String password2;
    
    @Component
    private Form formulario;
    @Inject
    private Messages mensajes;
    @Inject
    private PropertyAccess access;
    @Inject
    private Locale locale;

    
    @Inject
    private AdministradorServicio administradorServicio;
    @Inject
    private MedicoServicio medicoServicio;
    @Inject
    private DepartamentoServicio departamentoServicio;
    private GenericoServicio servicio = departamentoServicio;
    
    @Property
    private List<String> dias;
    @Property
    private List<String> anhos;
    @Property
    private GenericSelectionModel<Departamento> modeloDepartamento;
    @Property
    private GenericValueEncoder<Departamento> encoderDepartamento = new GenericValueEncoder<Departamento>(servicio, CrearMedico.class);
    
    @Property
    @SessionState(create=false)
    private MedSession medSession;
    
    @InjectPage
    private PerfilMedico perfilMedico;
    
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
    
    
    public boolean esLogueado() {
        return (clave != null && medSession != null && medSession.getUserId().equals(clave));
    }//fin esLogueado()
    
    
    public boolean mostrarSegundoApellido() {
        return ((locale.getLanguage().equals("es")) || (locale.getLanguage().equals("gl")));
    }//fin mostrarSegundoApellido()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        dias = new ArrayList<String>();
        anhos = new ArrayList<String>();
        Calendar fecha = Calendar.getInstance();
        for (int i = 1; i < 32; i++) dias.add(String.valueOf(i));
        for (int i = 1900; i <= fecha.get(Calendar.YEAR); i++) anhos.add(String.valueOf(i));
        modeloDepartamento = new GenericSelectionModel<Departamento>(administradorServicio.listarDepartamentos(), "nombre", access);
        
        if (clave != null) {
            Medico medico = medicoServicio.buscar(clave);
        
            dni = medico.getDni().toString();
            nombre = medico.getNombre();
            apellido1 = medico.getApellido1();
            apellido2 = medico.getApellido2();
            dia = String.valueOf(medico.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
            mes = Meses.values()[medico.getFechaNacimiento().get(Calendar.MONTH)];
            anho = String.valueOf(medico.getFechaNacimiento().get(Calendar.YEAR));
            telefono = medico.getTelefono();
            departamento = medicoServicio.recuperarDepartamento(clave);
            nombreLogin = medico.getNombreLogin();
            if (medico.getDireccion() != null) {
                calle = medico.getDireccion().getCalle();
                numero = medico.getDireccion().getNumero();
                piso = medico.getDireccion().getPiso();
                letra = medico.getDireccion().getLetra();
                codigoPostal = medico.getDireccion().getCodigoPostal();
                localidad = medico.getDireccion().getLocalidad();
                provincia = medico.getDireccion().getProvincia();
            }//fin if (medico.getDireccion() != null)
        }//fin if (clave != null)
    }//fin onPrepareForRender()
    
    
    void onSelectedFromBotonResetLogin() throws InstanciaNoEncontradaExcepcion {
        administradorServicio.resetearLoginMedico(clave);
        Medico medico = medicoServicio.buscar(clave);
        nombreLogin = medico.getNombreLogin();
    }//fin onSelectedFromBotonReset()
    
    
    void onSelectedFromBotonResetPassword() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        administradorServicio.resetearPasswordMedico(clave);
        Medico medico = medicoServicio.buscar(clave);
        nombreLogin = medico.getNombreLogin();
    }//fin onSelectedFromBotonReset()
    
    
    void onValidateFromFormulario() throws InstanciaNoEncontradaExcepcion {
        Long id = clave;
        Calendar fecha = null;
        
        //Aqui se copia la fecha a una variable Calendar
        if (dia != null && mes!=null && anho!=null) {
            fecha = Calendar.getInstance();
            fecha.clear();
            fecha.setLenient(false); //Esto hace que salte una excepción cuando la fecha no existe.
            fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
            fecha.set(Calendar.MONTH, mes.getNum());
            fecha.set(Calendar.YEAR, Integer.parseInt(anho));
        }//fin if
        
        try {
            if (fecha == null) throw new IllegalArgumentException();
            Direccion direccion = new Direccion(calle, provincia, localidad, codigoPostal, numero, piso, letra);
            
            if (clave != null) {
                Medico medico = medicoServicio.buscar(clave);
                medicoServicio.modificarMedico(clave, dni, nombre, apellido1, apellido2, fecha, telefono, direccion, nombreLogin, password, password2);
            }//fin if (clave != null)
            
            else {
                id = administradorServicio.agregarMedico(dni, nombre, apellido1, apellido2, fecha, telefono, direccion);
            }//fin else (clave != null)
            
            if (departamento != null) administradorServicio.asignarMedicoDepartamento(departamento.getClave(), id);
            
        } catch (VerificacionPasswordExcepcion ex) {
            formulario.recordError(mensajes.get("verificacionIncorrecta-error"));
        } catch (InstanciaDuplicadaExcepcion ex) {
            formulario.recordError(mensajes.get("medicoExistente-error"));
        } catch (FormatoInvalidoExcepcion ex) {
            formulario.recordError(mensajes.get("dniIncorrecto-error"));
        } catch (IllegalArgumentException ex) {
            formulario.recordError(mensajes.get("fechaIncorrecta-error"));
        }//fin try
    }//fin onValidateFromFormulario()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        if (clave == null)
            return Medicos.class;
        else {
            perfilMedico.setClave(clave);
            return perfilMedico;
        }//fin else
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase CrearMedico