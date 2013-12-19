/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.paciente;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.pacienteservicio.PacienteServicio;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.meses.Meses;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class CrearPaciente {


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
    private Meses mes;
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
    
    @Component
    private Form formulario;
    @Inject
    private Messages mensajes;
    
    @Inject
    private Locale locale;
    
    @Inject
    private AdministradorServicio administradorServicio;
    @Inject
    private PacienteServicio pacienteServicio;
    
    @Property
    private List<String> dias;
    @Property
    private List<String> anhos;
    
    @InjectPage
    private PerfilPaciente perfilPaciente;
    
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

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        dias = new ArrayList<String>();
        anhos = new ArrayList<String>();
        Calendar fecha = Calendar.getInstance();
        for (int i = 1; i < 32; i++) dias.add(String.valueOf(i));
        for (int i = 1900; i <= fecha.get(Calendar.YEAR); i++) anhos.add(String.valueOf(i));
        
        if (clave != null) {
            Paciente paciente = pacienteServicio.buscar(clave);
        
            dni = paciente.getDni().toString();
            nombre = paciente.getNombre();
            apellido1 = paciente.getApellido1();
            apellido2 = paciente.getApellido2();
            dia = String.valueOf(paciente.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
            mes = Meses.values()[paciente.getFechaNacimiento().get(Calendar.MONTH)];
            anho = String.valueOf(paciente.getFechaNacimiento().get(Calendar.YEAR));
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
        }//fin if (clave != null)
    }//fin onPrepareForRender()
    
    
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
                pacienteServicio.modificarPaciente(clave, dni, nombre, apellido1, apellido2, fecha, telefono, direccion);
            }//fin if (clave != null)
            
            else {
                id = administradorServicio.agregarPaciente(dni, nombre, apellido1, apellido2, fecha, telefono, direccion);
            }//fin else (clave != null)
            
        } catch (InstanciaDuplicadaExcepcion ex) {
            formulario.recordError(mensajes.get("pacienteExistente-error"));
        } catch (FormatoInvalidoExcepcion ex) {
            formulario.recordError(mensajes.get("dniIncorrecto-error"));
        } catch (IllegalArgumentException ex) {
            formulario.recordError(mensajes.get("fechaIncorrecta-error"));
        }//fin try
    }//fin onValidateFromFormulario()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        if (clave == null)
            return Pacientes.class;
        else {
            perfilPaciente.setClave(clave);
            return perfilPaciente;
        }//fin else
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase CrearPaciente