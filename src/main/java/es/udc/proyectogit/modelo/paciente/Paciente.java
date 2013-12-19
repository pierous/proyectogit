/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.paciente;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.dni.Dni;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/*----------------------------------------------------------------------------*/


public class Paciente implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private Dni dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Calendar fechaNacimiento;
    private Integer telefono;
    private Direccion direccion;
    private Medico medico;
    private Set<Informe> informes;
    private Set<Prueba> pruebas;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Paciente() {
        informes = new HashSet<Informe>();
//        pruebas = new HashSet<Prueba>();
    }//fin Paciente()

    public Paciente(Dni dni, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, Integer telefono, Direccion direccion) {
        this();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono =  telefono;
        this.direccion = direccion;
    }//fin Paciente(Dni dni, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, int telefono, Direccion direccion)

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

    public Dni getDni() {
        return dni;
    }//fin getDni()
    public void setDni(Dni dni) {
        this.dni = dni;
    }//fin setDni(Dni dni)
    
    public String getNombre() {
        return nombre;
    }// fin getNombre()
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }// fin setNombre(String nombre)
    
    public String getApellido1() {
        return apellido1;
    }// fin getApellido1()
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }// fin setApellido1(String apellido1)
    
    public String getApellido2() {
        return apellido2;
    }// fin getApellido2()
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }// fin setApellido2(String apellido2)
    
    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }//fin getFechaNacimiento()
    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }//fin setFechaNacimiento(Date fechaNacimiento)
    
    public Integer getTelefono() {
        return telefono;
    }// fin getTelefono()
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }// fin setTelefono(int telefono)
    
    public String getNombreCompleto() {
        String cadena = nombre;
        if (apellido1 != null) cadena = cadena + " " + apellido1;
        if (apellido2 != null) cadena = cadena + " " + apellido2;
        
        return cadena;
    }//fin getNombreCompleto()

    public Direccion getDireccion() {
        return direccion;
    }// fin getDireccion()
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }// fin setDireccion(Direccion direccion)
    
    public Medico getMedico() {
        return medico;
    }// fin getMedico()
    public void setMedico(Medico medico) {
        this.medico = medico;
    }// fin setMedico(Medico medico)
    
    public Set<Informe> getInformes() {
        return informes;
    }//fin getInformes()
    public void setInformes(Set<Informe> informes) {
        this.informes = informes;
    }//fin setInformes(Set<Informe> informes)
    
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
        final Paciente other = (Paciente) obj;
        if (this.clave != other.clave && (this.clave == null || !this.clave.equals(other.clave))) {
            return false;
        }
        if (this.dni != other.dni && (this.dni == null || !this.dni.equals(other.dni))) {
            return false;
        }
        return true;
    }//fin equals(Object obj)

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.clave != null ? this.clave.hashCode() : 0);
        hash = 67 * hash + (this.dni != null ? this.dni.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return (dni + " - " + nombre + " " + apellido1 + " " + apellido2);
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase Paciente