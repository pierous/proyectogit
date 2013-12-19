/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.parametro;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametroservicio.ParametroServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class PerfilParametro {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private String nombre;
    @Property
    private String unidad;
    @Property
    private String unidadAbreviada;
    
    @Inject
    private ParametroServicio parametroServicio;
    
    @InjectPage
    private CrearParametro crearParametro;
    
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

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        Parametro parametro = parametroServicio.buscar(clave);
        
        nombre = parametro.getNombre();
        unidad = parametro.getUnidad();
        unidadAbreviada = parametro.getUnidadAbreviada();
    }//fin onPrepareForRender()
    
    
    Object onActionFromEditar() {
        crearParametro.setClave(clave);
        
        return crearParametro;
    }//fin onSelectedFromBotonEditar()

    /*------------------------------------------------------------------------*/


}//fin Clase PerfilParametro