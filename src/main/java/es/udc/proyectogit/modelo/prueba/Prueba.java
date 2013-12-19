/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.prueba;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import es.udc.proyectogit.modelo.valor.Valor;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/*----------------------------------------------------------------------------*/


public class Prueba implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private Informe informe;
    private Calendar fechaSolicitud;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private Paciente paciente;
    private Medico solicitante;
    private Medico medico;
    private Plantilla plantilla;
    private Set<Valor> valores;
    private String observacionesSolicitante;
    private String observaciones;
    private Set<Imagen> imagenes;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Prueba() {
        this.fechaSolicitud = Calendar.getInstance();
        valores = new HashSet<Valor>();
        imagenes = new HashSet<Imagen>();
    }//fin Prueba()
    
    public Prueba(Paciente paciente, Medico solicitante, Informe informe, Plantilla plantilla) {
        this();
        this.paciente = paciente;
        this.solicitante = solicitante;
        this.informe = informe;
        this.plantilla = plantilla;
    }//fin Prueba(Plantilla plantilla)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    @Override
    public Long getClave() {
        return clave;
    }//fin getClave()
    @Override
    public void setClave(Long clave) {
        this.clave = clave;
    }//fin setClave(Long clave)
    
    public Informe getInforme() {
        return informe;
    }//fin getInforme()
    public void setInforme(Informe informe) {
        this.informe = informe;
    }//fin setInforme(Informe informe)
    
    public Calendar getFechaSolicitud() {
        return fechaSolicitud;
    }//fin getFechaSolicitud()
    public void setFechaSolicitud(Calendar fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }//fin setFechaSolicitud(Calendar fechaSolicitud)
    
    public Calendar getFechaInicio() {
        return fechaInicio;
    }//fin getFechaInicio()
    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }//fin setFechaInicio(Calendar fechaInicio)
    
    public Calendar getFechaFin() {
        return fechaFin;
    }//fin getFechaFin()
    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }//fin setFechaFin(Calendar fechaFin)
    
    public Paciente getPaciente() {
        return paciente;
    }//fin getPaciente()
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }//fin setPaciente(Paciente paciente)

    public Medico getSolicitante() {
        return solicitante;
    }//fin getSolicitante()
    public void setSolicitante(Medico solicitante) {
        this.solicitante = solicitante;
    }//fin setSolicitante(Medico solicitante)
    
    public Medico getMedico() {
        return medico;
    }//fin getMedico()
    public void setMedico(Medico medico) {
        this.medico = medico;
    }//fin setMedico(Medico medico)
    
    public Plantilla getPlantilla() {
        return plantilla;
    }//fin getPlantilla()
    public void setPlantilla(Plantilla plantilla) {
        this.plantilla = plantilla;
    }//fin setPlantilla(Plantilla plantilla)
    
    public Set<Valor> getValores() {
        return valores;
    }//fin getValores()
    public void setValores(Set<Valor> valores) {
        this.valores = valores;
    }//fin setValores(Set<Valor> valores)
    
    public String getObservacionesSolicitante() {
        return observacionesSolicitante;
    }//fin getObservacionesSolicitante()
    public void setObservacionesSolicitante(String observacionesSolicitante) {
        this.observacionesSolicitante = observacionesSolicitante;
    }//fin setObservacionesSolicitante(String observacionesSolicitante)
    
    public String getObservaciones() {
        return observaciones;
    }//fin getObservaciones()
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }//fin setObservaciones(String observaciones)
    
    public Set<Imagen> getImagenes() {
        return imagenes;
    }//fin getImagenes()
    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }//fin setImagenes(Set<Imagen> imagenes)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void anhadirValor(Valor valor) {
        valores.add(valor);
    }//fin anhadirValor(Valor valor)
    
    
    public void eliminarValor(Valor valor) {
        if (valores.contains(valor)) valores.remove(valor);
    }//fin eliminarValor(Valor valor)
    
    
    public void anhadirImagen(Imagen imagen) {
        imagenes.add(imagen);
    }//fin anhadirImagen(Imagen imagen)
    
    
    public void eliminarImagen(Imagen imagen) {
        if (imagenes.contains(imagen)) imagenes.remove(imagen);
    }//fin eliminarImagen(Imagen imagen)

    /*------------------------------------------------------------------------*/


}//fin Clase Prueba