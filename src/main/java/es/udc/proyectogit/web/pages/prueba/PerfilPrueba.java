/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.prueba;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.pruebaservicio.PruebaServicio;
import es.udc.proyectogit.modelo.utiles.busqueda.FormatoFecha;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.valor.Valor;
import es.udc.proyectogit.modelo.valorservicio.ValorServicio;
import es.udc.proyectogit.web.pages.imagen.SubirImagen;
import es.udc.proyectogit.web.pages.informe.PerfilInforme;
import es.udc.proyectogit.web.pages.medico.DocumentacionMedico;
import es.udc.proyectogit.web.pages.paciente.DocumentacionPaciente;
import es.udc.proyectogit.web.pages.utiles.UploadStore;
import es.udc.proyectogit.web.services.AuthenticationPolicy;
import es.udc.proyectogit.web.services.AuthenticationPolicyType;
import es.udc.proyectogit.web.util.MedSession;
import java.util.List;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

/*----------------------------------------------------------------------------*/


@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_MEDS)
public class PerfilPrueba {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    
    private int opcion = 1;
    @Property
    private boolean opcionInfo, opcionValores, opcionImagenes;
    @Property
    private Informe informe;
    
    @Property
    private String plantilla, fechaSolicitud, fechaInicio, fechaFin, observacionesSolicitante, observaciones;
    @Property
    private Medico medico, solicitante;
    @Property
    private Paciente paciente;
    
    @Property
    private Valor valor;
    @Property
    private List<Valor> valores;
    @Property
    private Imagen imagen;
    @Property
    private List<Imagen> imagenes;
    
    @Property
    private FormatoFecha formatoFecha = new FormatoFecha();
    
    @Property
    private BeanModel<Valor> modelo;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;
    
    @SessionState(create=false)
    private MedSession medSession;
    
    @Inject
    private PruebaServicio pruebaServicio;
    @Inject
    private ValorServicio valorServicio;
    @Inject
    private MedicoServicio medicoServicio;
    
    @InjectPage
    private CrearPrueba crearPrueba;
    @InjectPage
    private DocumentacionMedico documentacionMedico;
    @InjectPage
    private DocumentacionPaciente documentacionPaciente;
    @InjectPage
    private PerfilInforme perfilInforme;
    @InjectPage
    private SubirImagen subirImagen;
    @InjectPage
    private UploadStore uploadStore;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Long getClave() {
        return clave;
    }//fin getId()
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setId(Long id)
    
    
    public int getOpcion() {
        return opcion;
    }//fin getOpcion()
    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }//fin setOpcion(int opcion)
    
    
    public String getNombreValor(Long claveValor) {
        return valorServicio.recuperarTipo(claveValor).getNombre();
    }//fin getNombreValor(Long claveValor)
    
    
    public String getUnidadesValor(Long claveValor) {
        if (valorServicio.existe(claveValor)) return valorServicio.recuperarTipo(claveValor).getUnidadAbreviada();
        else return null;
    }//fin getUnidadesValor(Long claveValor)
    
    
    public Link getImagen(String path) {
        return uploadStore.getUploadedFile(path);
    }//fin getUploadedImage()
    
    
    public String getNombrePlantilla() {
        if (pruebaServicio.recuperarPlantilla(clave) != null)
            return pruebaServicio.recuperarPlantilla(clave).getNombre();
        else return null;
    }//fin nombrePlantilla(Long idPrueba)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    Object onPassivate() {
        return new Object[] {clave, + opcion}; 
    }//fin onPassivate()
    
    
    void onActivate(Long clave, int opcion) {
        this.clave = clave;
        this.opcion = opcion;
    }//fin onActivate(Long clave, int opcion)
    
    
    public Object onLinkMedico(Long clave) {
        documentacionMedico.setClave(clave);
        return documentacionMedico;
    }//fin onLinkMedico(Long clave)
    
    
    public Object onLinkPaciente(Long clave) {
        documentacionPaciente.setClave(clave);
        documentacionPaciente.setOpcion(3);
        return documentacionPaciente;
    }//fin onLinkPaciente(Long clave)
    
    
    public Object onLinkInforme() {
        perfilInforme.setClave(pruebaServicio.recuperarInforme(clave).getClave());
        perfilInforme.setOpcion(2);
        return perfilInforme;
    }//fin onLinkPaciente(Long clave)
    
    
    public boolean esEditable() {
        return pruebaServicio.esEditable(clave, medSession.getUserId());
    }//fin esEditable()
    
    
    public boolean esFinalizable() {
        return pruebaServicio.esFinalizable(clave, medSession.getUserId());
    }//fin esFinalizable()
    
    
    public boolean imagenAgregable() {
        return (esEditable() && !pruebaServicio.esFinalizado(clave));
    }//fin imagenAgregable()
    
    
    public Object onLinkAgregarImagen() {
        subirImagen.setClave(clave);
        return subirImagen;
    }//fin onLinkAgregarImagen()
    
    
    public StreamResponse onPaginaImagen(String path) {
        return uploadStore.onActivate(path);
    }//fin onDevolverImagen()
    
    
    public void onEliminarImagen(Long clave) throws InstanciaNoEncontradaExcepcion {
        medicoServicio.eliminarImagen(clave);
    }//fin onDevolverImagen()
    
    
    public void onCambiarOpcion(int i) {
        opcion = i;
        opcionInfo = opcionValores = opcionImagenes = false;
        switch (opcion) {
            case 1 :
                opcionInfo = true; break;
            case 2 :
                opcionValores = true; break;
            case 3 :
                opcionImagenes = true; break;
        }//fin switch (opcion)
    }//fin onCambiarOpcion(int i)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    void setupRender() throws InstanciaNoEncontradaExcepcion {
        onCambiarOpcion(opcion);
        Prueba prueba = pruebaServicio.buscar(clave);
        informe = pruebaServicio.recuperarInforme(clave);
        
        fechaSolicitud = formatoFecha.formatearFecha(prueba.getFechaSolicitud());
        fechaInicio = formatoFecha.formatearFecha(prueba.getFechaInicio());
        fechaFin = formatoFecha.formatearFecha(prueba.getFechaFin());
        medico = pruebaServicio.recuperarMedico(clave);
        solicitante = pruebaServicio.recuperarSolicitante(clave);
        paciente = pruebaServicio.recuperarPaciente(clave);
        observacionesSolicitante = prueba.getObservacionesSolicitante();
        observaciones = prueba.getObservaciones();
        
        if (opcionValores) {
            modelo = beanModelSource.createDisplayModel(Valor.class, messages);
            modelo.add("tipo", null);
            modelo.add("unidades", null);
            modelo.include("tipo", "dato", "unidades");
        
            for (String propertyName : modelo.getPropertyNames()) {
                modelo.get(propertyName).sortable(false);
            }//fin for (String propertyName : modelo.getPropertyNames())
        
            valores = pruebaServicio.recuperarValores(clave);
        }//fin if (opcionPruebas)
        
        if (opcionImagenes) {
            imagenes = pruebaServicio.recuperarImagenes(clave);
        }//fin (opcionImagenes)
    }//fin onPrepareForRender()
    
    
    Object onActionFromEditar() {
        if (esEditable()) {
            crearPrueba.setClave(clave);
            crearPrueba.setClaveInforme(pruebaServicio.recuperarInforme(clave).getClave());
            return crearPrueba;
        }//fin if (esEditable())
        else return null;
    }//fin onSelectedFromBotonEditar()
    
    
    Object onActionFromFinalizar() throws InstanciaNoEncontradaExcepcion {
        if (esFinalizable()) {
            pruebaServicio.finalizarPrueba(clave);
            return this;
        }//fin if (esFinalizable())
        else return null;
    }//fin onSelectedFromBotonEditar()
    
    
    public Object onRowClicked(Long clave) {
        return this;
    }//fin onRowClicked(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase PerfilPrueba