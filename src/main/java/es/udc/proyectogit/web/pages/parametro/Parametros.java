/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.parametro;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametroservicio.ParametroServicio;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import java.util.List;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class Parametros {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private List<Parametro> parametros;
    @Property
    private Parametro parametro;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String nombreBusqueda;
    
    @Property
    private BeanModel<Parametro> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    
    @InjectPage
    private PerfilParametro perfilParametro;
    
    @Inject
    private ParametroServicio parametroServicio;
    
    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() {
        modelo = beanModelSource.createDisplayModel(Parametro.class, messages);
        
        modelo.include("nombre", "unidadAbreviada", "unidad");
        
        for (String propertyName : modelo.getPropertyNames()) {
            modelo.get(propertyName).sortable(false);
        }//fin for (String propertyName : modelo.getPropertyNames())

        parametros = parametroServicio.filtro(parametroServicio.listar(), nombreBusqueda);
    }//fin setupRender()
    
    
    public Object onRowClicked(Long clave) {
        perfilParametro.setClave(clave);
        return perfilParametro;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase Parametros