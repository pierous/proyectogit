/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.medico;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.dni.Dni;
import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/*----------------------------------------------------------------------------*/


public class Medico implements Entidad {


    /*------------------------------Atributos---------------------------------*/
    
    private Long clave;
    private Dni dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Calendar fechaNacimiento;
    private Integer telefono;
    private Direccion direccion;
    private String nombreLogin;
    private String passwordEncriptado;
    private Departamento departamento;
    private Departamento depSupervisado;
    private Set<Informe> informes;
    private Set<Prueba> pruebasSolicitadas;
    private Set<Prueba> pruebas;
    private Set<Paciente> pacientes;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public Medico() {
        pruebas = new HashSet<Prueba>();
        pacientes = new HashSet<Paciente>();
    }//fin Medico()

    
    public Medico(Dni dni, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, Integer telefono, Direccion direccion, String nombreLogin, String passwordEncriptado) {
        this();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono =  telefono;
        this.direccion = direccion;
        this.nombreLogin = nombreLogin;
        this.passwordEncriptado = passwordEncriptado;
    }//fin Medico(int nss, String nombre, String apellido1, String apellido2, int telefono, String direccion)

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
    
    public Direccion getDireccion() {
        return direccion;
    }// fin getDireccion()
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }// fin setDireccion(String direccion)
    
    public Set<Informe> getInformes() {
        return informes;
    }//fin getInformes()
    public void setInformes(Set<Informe> informes) {
        this.informes = informes;
    }//fin setInformes(Set<Informe> informes)
    
    public Set<Prueba> getPruebasSolicitadas() {
        return pruebasSolicitadas;
    }//fin getPruebasSolicitadas()
    public void setPruebasSolicitadas(Set<Prueba> pruebasSolicitadas) {
        this.pruebasSolicitadas = pruebasSolicitadas;
    }//fin setPruebasSolicitadas(Set<Prueba> pruebasSolicitadas)
    
    public Set<Prueba> getPruebas() {
        return pruebas;
    }//fin getPruebas()
    public void setPruebas(Set<Prueba> pruebas) {
        this.pruebas = pruebas;
    }//fin setPruebas(Set<Prueba> pruebas)
    
    public Set<Paciente> getPacientes() {
        return pacientes;
    }//fin getPacientes()
    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }//fin setPacientes(Set<Paciente> pacientes)
    
    public String getNombreLogin() {
        return nombreLogin;
    }//fin getNombreLogin()
    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
    }//fin setNombreLogin(String nombreLogin)
    
    public String getPasswordEncriptado() {
        return passwordEncriptado;
    }//fin getPasswordEncriptado()
    public void setPasswordEncriptado(String passwordEncriptado) {
        this.passwordEncriptado = passwordEncriptado;
    }//fin setPasswordEncriptado(String passwordEncriptado)
    
    public Departamento getDepartamento() {
        return departamento;
    }//fin getDepartamento()
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }//fin setDepartamento(Departamento departamento)
    
    public Departamento getDepSupervisado() {
        return depSupervisado;
    }//fin getDepSupervisado()
    public void setDepSupervisado(Departamento depSupervisado) {
        this.depSupervisado = depSupervisado;
    }//fin setDepSupervisado(Departamento depSupervisado)
    
    public String getNombreCompleto() {
        String cadena = nombre;
        if (apellido1 != null) cadena = cadena + " " + apellido1;
        if (apellido2 != null) cadena = cadena + " " + apellido2;
        
        return cadena;
    }//fin getNombreCompleto()

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
        final Medico other = (Medico) obj;
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
        int hash = 7;
        hash = 17 * hash + (this.clave != null ? this.clave.hashCode() : 0);
        hash = 17 * hash + (this.dni != null ? this.dni.hashCode() : 0);
        return hash;
    }//fin hashCode()
    
    
    @Override
    public String toString() {
        return (dni + " - " + nombre + " " + apellido1 + " " + apellido2);
    }//fin toString()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void anhadirPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }//fin anhadirPaciente(Paciente paciente)
    
    
    public void eliminarPaciente(Paciente paciente) {
        if (pacientes.contains(paciente)) pacientes.remove(paciente);
    }//fin eliminarPaciente(Paciente paciente)

    /*------------------------------------------------------------------------*/


}//fin Clase Medico