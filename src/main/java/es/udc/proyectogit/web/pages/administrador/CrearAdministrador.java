/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.administrador;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administrador.Administrador;
import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.VerificacionPasswordExcepcion;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.AdminSession;
import java.util.Locale;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class CrearAdministrador {


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
    private String password;
    @Property
    private String password2;
    
    @Component
    private Form formulario;
    @Inject
    private Messages mensajes;
    
    @Inject
    private Locale locale;
    
    @SessionState(create=false)
    private AdminSession adminSession;
    
    @Inject
    private AdministradorServicio administradorServicio;
    
    @InjectPage
    private PerfilAdministrador perfilAdministrador;
    
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
    
    
    public boolean esLogueado() {
        return (clave != null && adminSession != null && adminSession.getUserId().equals(clave));
    }//fin esLogueado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        if (clave != null) {
            Administrador administrador = administradorServicio.buscar(clave);
        
            dni = administrador.getDni().toString();
            nombre = administrador.getNombre();
            apellido1 = administrador.getApellido1();
            apellido2 = administrador.getApellido2();
            nombreLogin = administrador.getNombreLogin();
        }//fin if (clave != null)
    }//fin onPrepareForRender()
    
    
    void onSelectedFromBotonResetLogin() throws InstanciaNoEncontradaExcepcion {
        administradorServicio.resetearLoginAdministrador(clave);
        Administrador administrador = administradorServicio.buscar(clave);
        nombreLogin = administrador.getNombreLogin();
    }//fin onSelectedFromBotonReset()
    
    
    void onSelectedFromBotonResetPassword() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        administradorServicio.resetearPasswordAdministrador(clave);
        Administrador administrador = administradorServicio.buscar(clave);
        nombreLogin = administrador.getNombreLogin();
    }//fin onSelectedFromBotonReset()
    
    
    void onValidateFromFormulario() throws InstanciaNoEncontradaExcepcion {
        try {
            if (clave != null) {
                Administrador administrador = administradorServicio.buscar(clave);
                nombreLogin = administrador.getNombreLogin();
                administradorServicio.modificarAdministrador(clave, dni, nombre, apellido1, apellido2, nombreLogin, password, password2);
            }//fin if (clave != null)
            
            else administradorServicio.registrarAdministrador(null, null, dni, nombre, apellido1, apellido2);
        } catch (InstanciaDuplicadaExcepcion ex) {
            formulario.recordError(mensajes.get("administradorExistente-error"));
        } catch (VerificacionPasswordExcepcion ex) {
            formulario.recordError(mensajes.get("verificacionIncorrecta-error"));
        } catch (FormatoInvalidoExcepcion ex) {
            formulario.recordError(mensajes.get("dniIncorrecto-error"));
        }//fin try
    }//fin onValidateFromFormulario()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        if (esLogueado())
            adminSession.setNombre(administradorServicio.buscar(clave).getNombreCompleto());
        
        if (clave == null)
            return Administradores.class;
        else {
            perfilAdministrador.setClave(clave);
            return perfilAdministrador;
        }//fin else
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase CrearAdministrador