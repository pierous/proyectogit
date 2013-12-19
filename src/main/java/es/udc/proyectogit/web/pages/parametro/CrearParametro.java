/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.parametro;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametroservicio.ParametroServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class CrearParametro {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Persist
    private Link linkPaginaAnterior;
    @Inject
    private ComponentResources componentResources;
    
    @Property
    private String nombre;
    @Property
    private String unidad;
    @Property
    private String unidadAbreviada;
    
    @Component
    private Form formulario;
    @Inject
    private Messages mensajes;
    
    @Inject
    private AdministradorServicio administradorServicio;
    @Inject
    private ParametroServicio parametroServicio;
    
    @InjectPage
    private PerfilParametro perfilParametro;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Long getClave() {
        return clave;
    }//fin getId()
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setId(Long id)
    
    public void set(Link linkPaginaAnterior) {
        this.linkPaginaAnterior= linkPaginaAnterior;
    }//fin set(Link linkPaginaAnterior)


    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    Long onPassivate() {
        return clave;
    }//fin onPassivate()
    
    
    void onActivate(Long clave) {
        this.clave = clave;
    }//fin onActivate(Long id)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        if (clave != null) {
            Parametro parametro = parametroServicio.buscar(clave);
        
            nombre = parametro.getNombre();
            unidad = parametro.getUnidad();
            unidadAbreviada = parametro.getUnidadAbreviada();
        }//fin if (clave != null)
    }//fin onPrepareForRender()
    
    
    void onValidateFromFormulario() throws InstanciaNoEncontradaExcepcion {
        try {
            if (clave != null) {
                parametroServicio.modificarParametro(clave, nombre, unidad, unidadAbreviada);
            }//fin if (clave != null)
            
            else administradorServicio.agregarParametro(nombre, unidad, unidadAbreviada);
        } catch (InstanciaDuplicadaExcepcion ex) {
            formulario.recordError(mensajes.get("parametroExistente-error"));
        }//fin try
    }//fin onValidateFromFormulario()
    
    
    Object onSuccess() throws InstanciaNoEncontradaExcepcion {
        if (linkPaginaAnterior != null) {
            String basePath = linkPaginaAnterior.getBasePath();
            Link linkPaginaAnteriorTmp = linkPaginaAnterior.copyWithBasePath(basePath);
            componentResources.discardPersistentFieldChanges();
            return linkPaginaAnteriorTmp;
        }//fin if (linkPaginaAnterior != null)
        if (clave == null)
            return Parametros.class;
        else {
            perfilParametro.setClave(clave);
            return perfilParametro;
        }//fin else
    }//fin onSuccesss()

    /*------------------------------------------------------------------------*/


}//fin Clase CrearParametro