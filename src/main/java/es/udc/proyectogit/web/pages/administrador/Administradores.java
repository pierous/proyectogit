/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.administrador;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administrador.Administrador;
import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.AdminSession;
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


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class Administradores {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private List<Administrador> administradores;
    @Property
    private Administrador administrador;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String dni, nombre, apellido1, apellido2;
    
    @Property
    private BeanModel<Administrador> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    
    @Inject
    private Locale locale;
    
    @SessionState(create=false)
    private AdminSession adminSession;
    
    @InjectPage
    private PerfilAdministrador perfilAdministrador;
    
    @Inject
    private AdministradorServicio administradorServicio;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    public boolean mostrarSegundoApellido() {
        return ((locale.getLanguage().equals("es")) || (locale.getLanguage().equals("gl")));
    }//fin mostrarSegundoApellido()
    
    
    public boolean getUsuarioLogueado() {
        return (!adminSession.getUserId().equals(administrador.getClave()));
    }//fin getUsuarioLogueado()

    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Auxiliares---------------------------------*/
    
    private List<Administrador> seleccionarAdministradores() {
        String[] campos = new String[4];
        campos[0] = dni;
        campos[1] = nombre;
        campos[2] = apellido1;
        campos[3] = apellido2;
        
        return administradorServicio.filtro(administradorServicio.listar(), campos);
    }//fin List<Medico> seleccionarMedicos()
    
    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() {
        modelo = beanModelSource.createDisplayModel(Administrador.class, messages);
        
        modelo.add("dni", null);
        
        if (mostrarSegundoApellido()) modelo.include("dni", "nombre", "apellido1", "apellido2");
        else modelo.include("dni", "nombre", "apellido1");
        
        for (String propertyName : modelo.getPropertyNames()) {
            modelo.get(propertyName).sortable(false);
        }//fin for (String propertyName : modelo.getPropertyNames())

        administradores = seleccionarAdministradores();
    }//fin setupRender()
    
    
    public Object onRowClicked(Long clave) {
        perfilAdministrador.setClave(clave);
        return perfilAdministrador;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase Administradores