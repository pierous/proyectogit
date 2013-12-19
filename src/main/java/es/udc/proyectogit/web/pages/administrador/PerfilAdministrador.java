/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.administrador;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administrador.Administrador;
import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.AdminSession;
import java.util.Locale;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class PerfilAdministrador {


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
    
    @Inject
    private Locale locale;
    
    @SessionState(create=false)
    private AdminSession adminSession;
    
    @Inject
    private AdministradorServicio administradorServicio;
    
    @InjectPage
    private CrearAdministrador crearAdministrador;
    
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
        return (adminSession != null && adminSession.getUserId().equals(clave));
    }//fin esLogueado()
    
    
    public boolean esEliminable() {
        if (adminSession == null || adminSession.getUserId().equals(clave))
            return false;
        else return true;
    }//fin esEliminable()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        Administrador administrador = administradorServicio.buscar(clave);
        
        dni = administrador.getDni().toString();
        nombre = administrador.getNombre();
        apellido1 = administrador.getApellido1();
        apellido2 = administrador.getApellido2();
        nombreLogin = administrador.getNombreLogin();
    }//fin onPrepareForRender()
    
    
    Object onActionFromEditar() {
        crearAdministrador.setClave(clave);
        
        return crearAdministrador;
    }//fin onSelectedFromBotonEditar()
    
    
    Object onActionFromEliminar() throws InstanciaNoEncontradaExcepcion {
        administradorServicio.eliminarAdministrador(clave);
        
        return Administradores.class;
    }//fin onSelectedFromBotonEliminar()

    /*------------------------------------------------------------------------*/


}//fin Clase PerfilAdministrador