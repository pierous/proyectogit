/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.imagen;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.pruebaservicio.PruebaServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.web.pages.prueba.PerfilPrueba;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.upload.services.UploadedFile;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_MEDS)
public class SubirImagen {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private UploadedFile imagen;
    
    @Component
    private Form formulario;
    @Inject
    private Messages mensajes;
    
    @Inject
    private MedicoServicio medicoServicio;
    @Inject
    private PruebaServicio pruebaServicio;
    
    @InjectPage
    private PerfilPrueba perfilPrueba;
    
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
    }//fin onActivate(Long clave)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        String tipo = imagen.getFilePath().split("\\.")[imagen.getFilePath().split("\\.").length - 1];
        if (!tipo.equals("jpg") && !tipo.equals("png") && !tipo.equals("gif") && !tipo.equals("jpeg")) {
            formulario.recordError(mensajes.get("archivoIncorrecto-error"));
            return this;
        }//fin if (!tipo.equals("jpg") || !tipo.equals("png") || !tipo.equals("gif") || !tipo.equals("jpeg"))
        else {
            medicoServicio.agregarImagen(clave, imagen);
            perfilPrueba.setClave(clave);
            perfilPrueba.setOpcion(3);
            return perfilPrueba;
        }//fin else (!tipo.equals("jpg") || !tipo.equals("png") || !tipo.equals("gif") || !tipo.equals("jpeg"))
    }//fin onSuccess()

    /*------------------------------------------------------------------------*/


}//fin Clase SubirImagen