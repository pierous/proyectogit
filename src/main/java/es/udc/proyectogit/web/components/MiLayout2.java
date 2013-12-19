/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.components;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.web.util.AdminSession;
import es.udc.proyectogit.web.util.MedSession;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

/*----------------------------------------------------------------------------*/


public class MiLayout2 {


    /*------------------------------Atributos---------------------------------*/
    
    //Con esta variable se exige que todas las páginas tengan un nombre de título.
    @SuppressWarnings("unused")
    @Property
    @Parameter(required = true, defaultPrefix = "message")
    private String titulo;
    
    //Variables donde se guardan los estados de los dos posibles tipos de usuario.
    @SuppressWarnings("unused")
    @Property
    @SessionState(create=false)
    private AdminSession adminSession;
    @SuppressWarnings("unused")
    @Property
    @SessionState(create=false)
    private MedSession medSession;
    
    //Variable donde se guadaran las cookies de la web.
    @Inject
    Cookies cookies;
    
    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase MiLayout2