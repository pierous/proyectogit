/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administrador.Administrador;
import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.utiles.datos.Datos;
import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.PasswordIncorrectoExcepcion;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.AdminSession;
import es.udc.proyectogit.web.util.CookiesManager;
import es.udc.proyectogit.web.util.MedSession;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Login {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private int esAdmin;
    
    @Property
    private String nombreLogin;
    @Property
    private String password;
    @Property
    private boolean recordarMiPassword;
    
    @SessionState(create=false)
    private AdminSession adminSession;
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private Cookies cookies;
    @Component
    private Form formulario;
    @Inject
    private Messages mensajes;
    
    @Inject
    private AdministradorServicio administradorServicio;
    @Inject
    private MedicoServicio medicoServicio;
    
    private Medico medico;
    private Administrador administrador;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    int onPassivate() {
        return this.esAdmin;
    }//fin onPassivate()

    
    void onActivate(int esAdmin) {
        this.esAdmin = esAdmin;
    }//fin onActivate(boolean esAdmin)
    
    
    public boolean esAdministrador() {
        return esAdmin == 1;
    }//fin esAdministrador()
    
    
    void onActionFromCambiarLogin() {
        if (esAdmin == 0)
            this.esAdmin = 1;
        else this.esAdmin = 0;
    }//fin onActionFromLogin()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void onValidateFromFormulario() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        Datos.administradorPorDefecto(administradorServicio);       //Se inserta un administrador por defecto en el caso de que no exista
                                                                    //ninguno (Como en el caso de la primera vez que se inicia la aplicación)
        
        try {
            if (esAdmin == 1) {
                administrador = administradorServicio.loginAdministrador(nombreLogin, password, false);
            }
            else {
                medico = medicoServicio.loginMedico(nombreLogin, password, false);
            }
        } catch (InstanciaNoEncontradaExcepcion ex) {
            formulario.recordError(mensajes.get("error-autentificacionFallida"));
        } catch (PasswordIncorrectoExcepcion ex) {
            formulario.recordError(mensajes.get("error-autentificacionFallida"));
        }//fin try
    }//fin onValidateFromFormularioLogin()
    
    
    Object onSuccess() {
        String passwordEncriptado;
        if (esAdmin == 1) {
            adminSession = new AdminSession();
            adminSession.setUserId(administrador.getClave());
            adminSession.setNombre(administrador.getNombreCompleto());
            passwordEncriptado = administrador.getPasswordEncriptado();
        }
        else {
            medSession = new MedSession();
            medSession.setUserId(medico.getClave());
            medSession.setNombre(medico.getNombreCompleto());
            passwordEncriptado = medico.getPasswordEncriptado();
        }
        if (recordarMiPassword)
            CookiesManager.leaveCookies(cookies, nombreLogin, passwordEncriptado);
        
        return Index.class;
    }//fin onSuccess()
    
    /*------------------------------------------------------------------------*/


}//fin Clase Login