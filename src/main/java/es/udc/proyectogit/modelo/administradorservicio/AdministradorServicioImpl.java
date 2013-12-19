/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.administradorservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.PasswordIncorrectoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.VerificacionPasswordExcepcion;
import es.udc.proyectogit.modelo.administrador.Administrador;
import es.udc.proyectogit.modelo.administrador.AdministradorDao;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamento.DepartamentoDao;
import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.imagen.ImagenDao;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medico.MedicoDao;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.paciente.PacienteDao;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametro.ParametroDao;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.plantilla.PlantillaDao;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.busqueda.NormalizarString;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.dni.Dni;
import es.udc.proyectogit.modelo.utiles.password.Encriptador;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("administradorServicio")
@Transactional
public class AdministradorServicioImpl extends GenericoServicioImp<Administrador, Long> implements AdministradorServicio {


    /*------------------------------Atributos---------------------------------*/
    
    @Autowired
    private DepartamentoDao departamentoDao;
    @Autowired
    private MedicoDao medicoDao;
    @Autowired
    private PacienteDao pacienteDao;
    @Autowired
    private ParametroDao parametroDao;
    @Autowired
    private PlantillaDao plantillaDao;
    @Autowired
    private ImagenDao imagenDao;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    @Autowired
    public void setAdministradorDao(AdministradorDao administradorDao) {
		this.dao = administradorDao;
    }//fin setAdministradorDao(AdministradorDao administradorDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Long agregarDepartamento(String nombre) throws InstanciaDuplicadaExcepcion {
        Departamento departamento = new Departamento(nombre);
        
        if (departamentoDao.buscarPorCriterio("nombre", nombre) != null)
            throw new InstanciaDuplicadaExcepcion(nombre, "nombre", Departamento.class.getSimpleName());
        
        departamentoDao.guardar(departamento);
        return departamento.getClave();
    }//fin agregarDepartamento(String nombre)
    
    
    public void eliminarDepartamento(Long clave) throws InstanciaNoEncontradaExcepcion {
        Departamento departamento = departamentoDao.buscarPorCriterio("clave", clave);
        
        if (departamento == null)
            throw new InstanciaNoEncontradaExcepcion(clave, "clave", Departamento.class.getSimpleName());
        
        Set<Medico> medicos = departamento.getMedicos();
        if (medicos != null)
            for (Iterator<Medico> i = medicos.iterator(); i.hasNext();)
                i.next().setDepartamento(null);
        if (departamento.getSupervisor() != null)
            departamento.getSupervisor().setDepSupervisado(null);
        List<Plantilla> plantillas = departamentoDao.plantillasDeDepartamento(clave);
        if (plantillas != null)
            for (Iterator<Plantilla> i = plantillas.iterator(); i.hasNext();)
                i.next().setDepartamento(null);
        departamentoDao.eliminar(departamento.getClave());
    }//fin eliminarDepartamento(String nombre)
    
    
    public List<Departamento> listarDepartamentos() {
        return departamentoDao.listar();
    }//fin listarDepartamentos()
    
    
    public Long agregarMedico(String dni, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, Integer telefono, Direccion direccion)
            throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        
        dni = dni.toLowerCase();
        Dni dni1 = new Dni(dni);
        String passwordEncriptado = Encriptador.encriptar(dni);
        String nombreLogin = dni;
        
        Medico medico = new Medico(dni1, nombre, apellido1, apellido2, fechaNacimiento, telefono, direccion, nombreLogin, passwordEncriptado);
        
        if (medicoDao.buscarPorCriterio("dni", new Dni(dni)) != null)
            throw new InstanciaDuplicadaExcepcion(dni, "dni", Medico.class.getSimpleName());
        
        medicoDao.guardar(medico);
        return medico.getClave();
    }//fin agregarMedico(int nss, String nombre, String apellido1, String apellido2, int telefono, String direccion)
    
    
    public void resetearLoginMedico(Long clave) throws InstanciaNoEncontradaExcepcion {
        Medico medico = medicoDao.buscar(clave);
        
        String nombreLogin = medico.getDni().toString();
        nombreLogin = nombreLogin.toLowerCase();
        
        medico.setNombreLogin(nombreLogin);
        medicoDao.actualizar(medico);
    }//fin resetearLoginMedico(Long clave)
    
    
    public void resetearPasswordMedico(Long clave) throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        Medico medico = medicoDao.buscar(clave);
        
        String dni = medico.getDni().toString();
        dni = dni.toLowerCase();
        String passwordEncriptado = Encriptador.encriptar(dni);
        
        medico.setPasswordEncriptado(passwordEncriptado);
        medicoDao.actualizar(medico);
    }//fin resetearPasswordMedico(Long clave)
    
    
    public void eliminarMedico(Long clave) throws InstanciaNoEncontradaExcepcion {
        Medico medico = medicoDao.buscar(clave);
        if (medico.getDepSupervisado() != null)
            medico.getDepSupervisado().setSupervisor(null);
        if (medico.getDepartamento() != null)
            medico.getDepartamento().eliminarMedico(medico);
        Set<Paciente> pacientes = medico.getPacientes();
        if (pacientes != null) {
            for (Iterator<Paciente> i = pacientes.iterator(); i.hasNext();)
                i.next().setMedico(null);
        }
        Set<Informe> informes = medico.getInformes();
        if (informes != null) {
            for (Iterator<Informe> i = informes.iterator(); i.hasNext();)
                i.next().setMedico(null);
        }
        Set<Prueba> pruebas = medico.getPruebas();
        if (pruebas != null) {
            for (Iterator<Prueba> p = pruebas.iterator(); p.hasNext();)
                p.next().setMedico(null);
        }
        Set<Prueba> pruebasSolicitadas = medico.getPruebasSolicitadas();
        if (pruebasSolicitadas != null) {
            for (Iterator<Prueba> p = pruebasSolicitadas.iterator(); p.hasNext();)
                p.next().setSolicitante(null);
        }
        medicoDao.actualizar(medico);
        medicoDao.eliminar(clave);
    }//fin eliminarMedico(Medico medicoEj)
    
    
    public List<Medico> listarMedicos() {
        return medicoDao.listar();
    }//fin listarMedicos()
    
    
    public void asignarMedicoDepartamento(Long idDepartamento, Long idMedico) throws InstanciaNoEncontradaExcepcion {
        if (idDepartamento != null) medicoDao.buscar(idMedico).setDepartamento(departamentoDao.buscar(idDepartamento));
        else medicoDao.buscar(idMedico).setDepartamento(null);
    }//fin asignarMedicoDepartamento()
    
    
    public void asignarSupervisorDepartamento(Long idDepartamento, Long idMedico) throws InstanciaNoEncontradaExcepcion {
        if (idMedico != null) departamentoDao.buscar(idDepartamento).setSupervisor(medicoDao.buscar(idMedico));
        else departamentoDao.buscar(idDepartamento).setSupervisor(null);
    }//fin asignarSupervisorDepartamento(Long idDepartamento, Long idMedico)
    
    
    public Long agregarPaciente(String dni, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, Integer telefono, Direccion direccion)
            throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        dni = dni.toLowerCase();
        Dni dni1 = new Dni(dni);
        
        Paciente paciente = new Paciente(dni1, nombre, apellido1, apellido2, fechaNacimiento, telefono, direccion);
        
        if (pacienteDao.buscarPorCriterio("dni", new Dni(dni)) != null)
            throw new InstanciaDuplicadaExcepcion(dni, "dni", Paciente.class.getSimpleName());
        
        pacienteDao.guardar(paciente);
        return paciente.getClave();
    }//fin agregarPaciente(int nss, String nombre, String apellido1, String apellido2, int telefono, String direccion)
    
    
    public void eliminarPaciente(Long clave) throws InstanciaNoEncontradaExcepcion {
        Set<Prueba> pruebas = pacienteDao.buscar(clave).getPruebas();
        if (pruebas != null) {
            for (Iterator<Prueba> p = pruebas.iterator(); p.hasNext();) {
                Prueba prueba = p.next();
                Set<Imagen> imagenes = prueba.getImagenes();
                if (imagenes != null)
                    for (Iterator<Imagen> i = imagenes.iterator(); i.hasNext();)
                        imagenDao.eliminar(i.next().getClave());
            }//fin for (Iterator<Prueba> p = pruebas.iterator(); p.hasNext();)
        }//fin if (pruebas != null)
        pacienteDao.eliminar(clave);
    }//fin eliminarPaciente(Paciente pacienteEj)
    
    public List<Paciente> listarPacientes() {
        return pacienteDao.listar();
    }//fin listarPacientes()
    
    
    public Long registrarAdministrador(String nombreLogin, String password, String dni, String nombre, String apellido1, String apellido2)
            throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        
        dni = dni.toLowerCase();
        Dni dni1 = new Dni(dni);
        if (password == null) password = dni;
        String passwordEncriptado = Encriptador.encriptar(password);
        
        if (nombreLogin == null) nombreLogin = dni;
        Administrador administrador = new Administrador(nombreLogin, dni1, nombre, apellido1, apellido2, passwordEncriptado);
        
        if (dao.buscarPorCriterio("dni", new Dni(dni)) != null)
            throw new InstanciaDuplicadaExcepcion(dni, "dni", Administrador.class.getSimpleName());
        if (dao.buscarPorCriterio("nombreLogin", nombreLogin) != null)
            throw new InstanciaDuplicadaExcepcion(nombreLogin, "nombre login", Administrador.class.getSimpleName());
        
        dao.guardar(administrador);
        
        return administrador.getClave();
    }//fin registrarAdministrador(String nombreLogin, String password, int nss, String nombre, String apellido1, String apellido2)
    
    
    public Administrador loginAdministrador(String nombreLogin, String password, boolean passwordEstaEncriptado)
            throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion {
        
        Administrador administrador = (Administrador) dao.buscarPorCriterio("nombreLogin", nombreLogin); 
        if (administrador == null)
            throw new InstanciaNoEncontradaExcepcion(nombreLogin, "nombre login", Administrador.class.getSimpleName());
        String passwordGuardado = administrador.getPasswordEncriptado();
        
        if (passwordEstaEncriptado){
            if (!password.equals(passwordGuardado))
                throw new PasswordIncorrectoExcepcion();
        }//fin if (passwordEstaEncriptado)
        else {
            if (!Encriptador.esPasswordCorrecto(password, passwordGuardado))
                throw new PasswordIncorrectoExcepcion();
        }//fin else
        
        return administrador;
    }//fin loginAdministrador(String nombreLogin, String password, boolean passwordEstaEncriptado)
    
    
    public void modificarAdministrador(Long clave, String dni, String nombre, String apellido1, String apellido2, String nombreLogin, String password, String passwordRepetido)
            throws VerificacionPasswordExcepcion, FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion {
        Administrador administrador = (Administrador) dao.buscar(clave);
        
        if (password != null || passwordRepetido != null)
            if (password== null || !password.equals(passwordRepetido))
                throw new VerificacionPasswordExcepcion();
            else administrador.setPasswordEncriptado(Encriptador.encriptar(password));
        
        if (dao.buscarPorCriterio("dni", new Dni(dni)) != null &&
                !((Administrador) dao.buscar(administrador.getClave())).getDni().equals(new Dni(dni)))
            throw new InstanciaDuplicadaExcepcion(new Dni(dni), "dni", AdministradorServicioImpl.class.getSimpleName());
        
        administrador.setDni(new Dni(dni));
        administrador.setNombre(nombre);
        administrador.setApellido1(apellido1);
        administrador.setApellido2(apellido2);
        administrador.setNombreLogin(nombreLogin);
        
        dao.actualizar(administrador);
    }//fin modificarAdministrador(Long clave, String dni, String nombre, String apellido1, String apellido2, String nombreLogin, String password, String passwordRepetido)
    
    
    public void resetearLoginAdministrador(Long clave) throws InstanciaNoEncontradaExcepcion {
        Administrador administrador = (Administrador) dao.buscar(clave);
        
        String nombreLogin = administrador.getDni().toString();
        nombreLogin = nombreLogin.toLowerCase();
        
        administrador.setNombreLogin(nombreLogin);
        dao.actualizar(administrador);
    }//fin resetearLoginMedico(Long clave)
    
    
    public void resetearPasswordAdministrador(Long clave) throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        Administrador administrador = (Administrador) dao.buscar(clave);
        
        String dni = administrador.getDni().toString();
        dni = dni.toLowerCase();
        String passwordEncriptado = Encriptador.encriptar(dni);
        
        administrador.setPasswordEncriptado(passwordEncriptado);
        dao.actualizar(administrador);
    }//fin resetearPasswordMedico(Long clave)
    
    
    public void eliminarAdministrador(Long clave) throws InstanciaNoEncontradaExcepcion {
        dao.eliminar(clave);
    }//fin eliminarAdministrador(Long clave)
    
    
    public Long agregarParametro(String nombre, String unidad, String unidadAbreviada)
            throws InstanciaDuplicadaExcepcion {
        if (parametroDao.buscarPorCriterio("nombre", nombre) != null)
            throw new InstanciaDuplicadaExcepcion(nombre, "nombre", Parametro.class.getSimpleName());
        
        Parametro parametro = new Parametro(nombre, unidad, unidadAbreviada);
        parametroDao.guardar(parametro);
        
        return parametro.getClave();
    }//fin agregarParametro(String nombre, String unidad, String unidadAbreviada)
    
    
    public List<Parametro> listarParametros() {
        return parametroDao.listar();
    }//fin listarParametros()
    
    
    public void eliminarParametro(Long clave) throws InstanciaNoEncontradaExcepcion {
        parametroDao.eliminar(clave);
    }//fin eliminarParametro(Long clave)
    
    
    public Long agregarPlantilla(String nombre, Departamento departamento, List<Parametro> parametros)
            throws InstanciaDuplicadaExcepcion {
        if (plantillaDao.buscarPorCriterio("nombre", nombre) != null)
            throw new InstanciaDuplicadaExcepcion(nombre, "nombre", Plantilla.class.getSimpleName());
        
        Plantilla plantilla = new Plantilla(nombre, departamento);
        if (parametros != null) plantilla.setParametros(new HashSet<Parametro>(parametros));
        plantillaDao.guardar(plantilla);
        
        return plantilla.getClave();
    }//fin agregarPlantilla(String nombre, Calendar fecha, List<Parametro> parametros)
    
    
    public List<Plantilla> listarPlantillas() {
        return plantillaDao.listar();
    }//fin listarPlantillas()
    
    
    public void eliminarPlantilla(Long clave) throws InstanciaNoEncontradaExcepcion {
        plantillaDao.eliminar(clave);
    }//fin eliminarPlantilla(Long clave)
    
    
    public List<Administrador> filtro(List<Administrador> administradores, String[] campos) {
        List<Administrador> coincidencias = new ArrayList<Administrador>();
        for (Iterator i = administradores.iterator(); i.hasNext();) {
            Administrador pac = (Administrador) i.next();
            if (campos[0] == null || (pac.getDni() != null && pac.getDni().getDni().toLowerCase().startsWith(campos[0].toLowerCase())))
                if (campos[1] == null || (pac.getNombre() != null && NormalizarString.normalizar(pac.getNombre()).toLowerCase().startsWith(NormalizarString.normalizar(campos[1]).toLowerCase())))
                    if (campos[2] == null || (pac.getApellido1() != null && NormalizarString.normalizar(pac.getApellido1()).toLowerCase().startsWith(NormalizarString.normalizar(campos[2]).toLowerCase())))
                        if (campos[3] == null || (pac.getApellido2() != null && NormalizarString.normalizar(pac.getApellido2()).toLowerCase().startsWith(NormalizarString.normalizar(campos[3]).toLowerCase())))
                            coincidencias.add(pac);
        }//fin for (Iterator i = administradores.iterator(); i.hasNext();)
        return coincidencias;
    }//fin filtro(List<Administrador> administrador)

    /*------------------------------------------------------------------------*/


}//fin Clase AdministradorServicioImpl