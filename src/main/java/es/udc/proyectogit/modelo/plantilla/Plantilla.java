/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.plantilla;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/*----------------------------------------------------------------------------*/


public class Plantilla implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private String nombre;
    private Calendar fecha;
    private Set<Parametro> parametros;
    private Departamento departamento;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Plantilla() {
        this.parametros = new HashSet<Parametro>();
        this.fecha = Calendar.getInstance();
    }//fin Plantilla()

    
    public Plantilla(String nombre, Departamento departamento) {
        this();
        this.nombre = nombre;
        this.departamento = departamento;
    }//fin Plantilla(String nombre)

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
    }//fin setNombre()
    
    public Calendar getFecha() {
        return fecha;
    }//fin getFecha()
    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
        this.fecha.set(Calendar.MILLISECOND, 0);
    }//fin setFecha(Calendar fecha)

    public Set<Parametro> getParametros() {
        return parametros;
    }//fin getParametros()
    public void setParametros(Set<Parametro> parametros) {
        this.parametros = parametros;
    }//fin setParametros(List<Parametro> parametros)
    
    public Departamento getDepartamento() {
        return departamento;
    }//fin getDepartamento()
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }//fin setDepartamento(Departamento departamento)

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
        final Plantilla other = (Plantilla) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }//fin equals(Object obj)

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }//fin hashCode()

    
    @Override
    public String toString() {
        return (nombre);
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void anhadirParametro(Parametro parametro) {
        parametros.add(parametro);
    }//fin anhadirParametro(Parametro parametro)
    
    
    public void eliminarParametro(Parametro parametro) {
        if (parametros.contains(parametro)) parametros.remove(parametro);
    }//fin eliminarParametros(Parametro parametro)

    /*------------------------------------------------------------------------*/


}//fin Clase Plantilla