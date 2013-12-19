/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.informe;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/*----------------------------------------------------------------------------*/


public class Informe implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private String observacionesPrevias;
    private String diagnostico;
    private String tratamiento;
    private Paciente paciente;
    private Medico medico;
    private Set<Prueba> pruebas;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Informe() {
        this.fechaInicio = Calendar.getInstance();
        pruebas = new HashSet<Prueba>();
    }//fin Informe()
    
    
    public Informe(Paciente paciente, Medico medico, String observacionesPrevias, Set<Prueba> pruebas) {
        this();
        this.paciente = paciente;
        this.medico = medico;
        this.observacionesPrevias = observacionesPrevias;
        if (pruebas != null) this.pruebas = pruebas;
    }//fin Informe(String diagnostico)

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
    
    public String getObservacionesPrevias() {
        return observacionesPrevias;
    }//fin getObservacionesPrevias()
    public void setObservacionesPrevias(String observacionesPrevias) {
        this.observacionesPrevias = observacionesPrevias;
    }//fin setObservacionesPrevias()

    public String getDiagnostico() {
        return diagnostico;
    }//fin getDiagnostico()
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }//fin setDiagnostico()
    
    public String getTratamiento() {
        return tratamiento;
    }//fin getTratamiento()
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }//fin setTratamiento()
    
    public Paciente getPaciente() {
        return paciente;
    }//fin getPaciente()
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }//fin setPaciente(Paciente paciente)
    
    public Medico getMedico() {
        return medico;
    }//fin getMedico()
    public void setMedico(Medico medico) {
        this.medico = medico;
    }//fin setMedico(Medico medico)
    
    public Set<Prueba> getPruebas() {
        return pruebas;
    }//fin getPruebas()
    public void setPruebas(Set<Prueba> pruebas) {
        this.pruebas = pruebas;
    }//fin setPruebas(Set<Prueba> pruebas)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Informe other = (Informe) obj;
        if ((this.diagnostico == null) ? (other.diagnostico != null) : !this.diagnostico.equals(other.diagnostico)) {
            return false;
        }
        return true;
    }//fin equals(Object obj)

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.diagnostico != null ? this.diagnostico.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return (diagnostico);
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void anhadirPrueba(Prueba prueba) {
        pruebas.add(prueba);
    }//fin anhadirPrueba(Prueba prueba)
    
    
    public void eliminarPrueba(Prueba prueba) {
        if (pruebas.contains(prueba)) pruebas.remove(prueba);
    }//fin eliminarParametros(Parametro parametro)
    
    
    public void finalizarInforme() {
        this.fechaFin = Calendar.getInstance();
    }//fin finalizarInforme()

    /*------------------------------------------------------------------------*/


}//fin Clase Informe