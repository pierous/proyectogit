/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.components.partes;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.web.pages.Index;
import es.udc.proyectogit.web.util.AdminSession;
import es.udc.proyectogit.web.util.CookiesManager;
import es.udc.proyectogit.web.util.MedSession;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

/*----------------------------------------------------------------------------*/


public class Cabecera {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    @SessionState(create=false)
    private AdminSession adminSession;
    @Property
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    Cookies cookies;
    
    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    Object onActionFromLogoutAdmin() {
        if (adminSession != null) adminSession = null;
        CookiesManager.removeCookies(cookies);
        return Index.class;
    }//fin onActionFromLogoutAdmin()
    
    
    Object onActionFromLogoutMed() {
        if (medSession != null) medSession = null;
        CookiesManager.removeCookies(cookies);
        return Index.class;
    }//fin onActionFromLogoutMed()

    /*------------------------------------------------------------------------*/


}//fin Clase Cabecera