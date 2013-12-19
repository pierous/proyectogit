/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.departamento;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
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
public class Departamentos {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private List<Departamento> departamentos;
    @Property
    private Departamento departamento;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String nombreBusqueda;
    
    @Property
    private BeanModel<Departamento> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    
    @InjectPage
    private PerfilDepartamento perfilDepartamento;
    
    @Inject
    private DepartamentoServicio departamentoServicio;
    
    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Gets y Sets--------------------------------*/
    
    public String getSupervisor(Long clave) {
        if (departamentoServicio.recuperarSupervisor(clave) != null)
            return departamentoServicio.recuperarSupervisor(clave).getNombreCompleto();
        else
            return null;
    }

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() {
        modelo = beanModelSource.createDisplayModel(Departamento.class, messages);
        
        modelo.add("supervisor", null);
        
        modelo.include("nombre", "supervisor");
        
        for (String propertyName : modelo.getPropertyNames()) {
            modelo.get(propertyName).sortable(false);
        }//fin for (String propertyName : modelo.getPropertyNames())

        departamentos = departamentoServicio.filtro(departamentoServicio.listar(), nombreBusqueda);
    }//fin setupRender()
    
    
    public Object onRowClicked(Long clave) {
        perfilDepartamento.setClave(clave);
        return perfilDepartamento;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase Departamentos