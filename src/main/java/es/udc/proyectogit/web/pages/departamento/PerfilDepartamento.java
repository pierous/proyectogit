/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.departamento;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class PerfilDepartamento {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    @Property
    private String nombre;
    @Property
    private String supervisor;
    
    @Inject
    private DepartamentoServicio departamentoServicio;
    @Inject
    private AdministradorServicio administradorServicio;
    
    @InjectPage
    private CrearDepartamento crearDepartamento;
    
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
        Departamento departamento = departamentoServicio.buscar(clave);
        
        nombre = departamento.getNombre();
        if (departamentoServicio.recuperarSupervisor(clave) != null)
            supervisor = departamentoServicio.recuperarSupervisor(clave).getNombreCompleto();
    }//fin onPrepareForRender()
    
    
    Object onActionFromEditar() {
        crearDepartamento.setClave(clave);
        
        return crearDepartamento;
    }//fin onSelectedFromBotonEditar()
    
    
    Object onActionFromEliminar() throws InstanciaNoEncontradaExcepcion {
        administradorServicio.eliminarDepartamento(clave);
        
        return Departamentos.class;
    }//fin onSelectedFromBotonEliminar()

    /*------------------------------------------------------------------------*/


}//fin Clase PerfilDepartamento