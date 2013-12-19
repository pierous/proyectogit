/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.departamento;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import java.util.HashSet;
import java.util.Set;

/*----------------------------------------------------------------------------*/


public class Departamento implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private String nombre;
    private Medico supervisor;
    private Set<Medico> medicos;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Departamento() {
        medicos = new HashSet<Medico>();
    }//fin Departamento()

    public Departamento(String nombre) {
        this();
        this.nombre = nombre;
    }//fin Departamento(String nombre)

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
    
    public String getNombre() {
        return nombre;
    }//fin getNombre()
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }//fin setNombre(String nombre)

    public Medico getSupervisor() {
        return supervisor;
    }//fin getSupervisor()
    public void setSupervisor(Medico supervisor) {
        this.supervisor = supervisor;
    }//fin setSupervisor(Medico supervisor)

    public Set<Medico> getMedicos() {
        return medicos;
    }//fin getMedicos()
    public void setMedicos(Set<Medico> medicos) {
        this.medicos = medicos;
    }//fin setMedicos(Set<Medico> medicos)

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
        final Departamento other = (Departamento) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }//fin equals(Object obj)

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return (nombre);
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void anhadirMedico(Medico medico) {
        medicos.add(medico);
    }//fin anhadirMedico(Medico medico)
    
    
    public void eliminarMedico(Medico medico) {
        if (medicos.contains(medico)) medicos.remove(medico);
    }//fin eliminarMedico(Medico medico)

    /*------------------------------------------------------------------------*/


}//fin Clase Departamento