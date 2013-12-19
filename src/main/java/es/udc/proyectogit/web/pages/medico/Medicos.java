/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericSelectionModel;
import es.udc.proyectogit.modelo.utiles.genericselectionmodel.GenericValueEncoder;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import es.udc.proyectogit.web.pages.plantilla.Plantillas;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import java.util.List;
import java.util.Locale;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.apache.tapestry5.services.BeanModelSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_ADMINS)
public class Medicos {


    /*------------------------------Atributos---------------------------------*/
    
    @Property
    private List<Medico> medicos;
    @Property
    private Medico medico;
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String dni, nombre, apellido1, apellido2;
    @Persist(PersistenceConstants.FLASH)
    @Property
    private Departamento departamentoBusqueda;
    
    @Property
    private BeanModel<Medico> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    @Inject
    private PropertyAccess access;
    
    @Inject
    private Locale locale;
    
    @InjectPage
    private PerfilMedico perfilMedico;
    
    @Inject
    private MedicoServicio medicoServicio;
    @Inject
    private DepartamentoServicio departamentoServicio;
    private GenericoServicio servicio = departamentoServicio;
    
    @Property
    private GenericSelectionModel<Departamento> modeloDepartamento;
    @Property
    private GenericValueEncoder<Departamento> encoderDepartamento = new GenericValueEncoder<Departamento>(servicio, Plantillas.class);
    
    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Gets y Sets--------------------------------*/
    
    public String getDepartamento(Long clave) throws InstanciaNoEncontradaExcepcion {
        if (medicoServicio.recuperarDepartamento(clave) != null)
            return medicoServicio.recuperarDepartamento(clave).getNombre();
        else return null;
    }//fin getDepartamento(Long clave)

    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Auxiliares---------------------------------*/
    
    public boolean mostrarSegundoApellido() {
        return ((locale.getLanguage().equals("es")) || (locale.getLanguage().equals("gl")));
    }//fin mostrarSegundoApellido()
    
    
    private List<Medico> seleccionarMedicos() {
        String[] campos = new String[5];
        campos[0] = dni;
        campos[1] = nombre;
        campos[2] = apellido1;
        campos[3] = apellido2;
        if (departamentoBusqueda != null) campos[4] = departamentoBusqueda.getClave().toString();
        else campos[4] = null;
        
        return medicoServicio.filtro(medicoServicio.listar(), campos);
    }//fin List<Medico> seleccionarMedicos()
    
    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        modeloDepartamento = new GenericSelectionModel<Departamento>(departamentoServicio.listar(), "nombre", access);
        modelo = beanModelSource.createDisplayModel(Medico.class, messages);
        
        modelo.add("dni", null);
        modelo.add("departamento", null);
        
        if (mostrarSegundoApellido()) modelo.include("dni", "nombre", "apellido1", "apellido2", "departamento");
        else modelo.include("dni", "nombre", "apellido1", "departamento");
        
        for (String propertyName : modelo.getPropertyNames()) {
            modelo.get(propertyName).sortable(false);
        }//fin for (String propertyName : modelo.getPropertyNames())

        medicos = seleccionarMedicos();
    }//fin setupRender()
    
    
    public Object onRowClicked(Long clave) {
        perfilMedico.setClave(clave);
        return perfilMedico;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase Medicos