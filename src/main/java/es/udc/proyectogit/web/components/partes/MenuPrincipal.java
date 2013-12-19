/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.components.partes;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.web.util.AdminSession;
import es.udc.proyectogit.web.util.MedSession;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


public class MenuPrincipal {


    /*------------------------------Atributos---------------------------------*/
    
    @Inject
    private Block menuAdministrador, menuMedico;
    
    @SessionState(create=false)
    private AdminSession adminSession;
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private MedicoServicio medicoServicio;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Object getMenuPrincipal() {
        if (adminSession != null) return menuAdministrador;
        else if (medSession != null) return menuMedico;
        else return null;
    }//fin getMenuPrincipal()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    public boolean esSupervisor() throws InstanciaNoEncontradaExcepcion {
        if (medSession != null)
            return medicoServicio.esAdministrador(medSession.getUserId());
        else return false;
    }//fin esSupervisor()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase MenuPrincipal