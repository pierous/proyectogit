/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.medicoservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.PasswordIncorrectoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.VerificacionPasswordExcepcion;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.imagen.ImagenDao;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.informe.InformeDao;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medico.MedicoDao;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.paciente.PacienteDao;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametro.ParametroDao;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.plantilla.PlantillaDao;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.prueba.PruebaDao;
import es.udc.proyectogit.modelo.utiles.busqueda.NormalizarString;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.dni.Dni;
import es.udc.proyectogit.modelo.utiles.password.Encriptador;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import es.udc.proyectogit.modelo.valor.Valor;
import es.udc.proyectogit.modelo.valor.ValorDao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("medicoServicio")
@Transactional
public class MedicoServicioImpl extends GenericoServicioImp<Medico, Long> implements MedicoServicio {


    /*------------------------------Atributos---------------------------------*/
    
    @Autowired
    private PacienteDao pacienteDao;
    @Autowired
    private InformeDao informeDao;
    @Autowired
    private PruebaDao pruebaDao;
    @Autowired
    private ImagenDao imagenDao;
    @Autowired
    private ParametroDao parametroDao;
    @Autowired
    private ValorDao valorDao;
    @Autowired
    private PlantillaDao plantillaDao;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setMedicoDao(MedicoDao medicoDao) {
		this.dao = medicoDao;
    }//fin setMedicoDao(MedicoDao medicoDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Departamento recuperarDepartamento(Long clave) throws InstanciaNoEncontradaExcepcion {
        return ((MedicoDao) dao).departamentoDeMedico(clave);
    }//fin recuperarParametros(Long clave)
    
    
    public Departamento recuperarDepSupervisado(Long clave) {
        return ((MedicoDao) dao).depSupervisadoDeMedico(clave);
    }//fin recuperarDepSupervisado(Long clave)
    
    
    public List<Informe> recuperarInformes(Long clave) {
        return ((MedicoDao) dao).informesDeMedico(clave);
    }//fin recuperarInformes(Long clave)
    
    
    public List<Informe> recuperarInformesIncompletos(Long clave) {
        return ((MedicoDao) dao).informesPendientesDeMedico(clave);
    }//fin recuperarInformesFinalizados(Long clave)
    
    
    public List<Informe> recuperarInformesFinalizados(Long clave) {
        return ((MedicoDao) dao).informesFinalizadosDeMedico(clave);
    }//fin recuperarInformesFinalizados(Long clave)
    
    
    public List<Prueba> recuperarPruebas(Long clave) {
        return ((MedicoDao) dao).pruebasDeMedico(clave);
    }//fin recuperarPruebas(Long clave)
    
    
    public List<Prueba> recuperarPruebasIncompletas(Long clave) {
        return ((MedicoDao) dao).pruebasPendientesDeMedico(clave);
    }//fin recuperarPruebasIncompletas()
    
    
    public List<Prueba> recuperarPruebasFinalizadas(Long clave) {
        return ((MedicoDao) dao).pruebasFinalizadasDeMedico(clave);
    }//fin recuperarPruebasFinalizadas(Long clave)
    
    
    public List<Prueba> recuperarPruebasSolicitadas(Long clave) {
        return ((MedicoDao) dao).pruebasSolicitadasDeMedico(clave);
    }//fin recuperarPruebasSolicitadas(Long clave)
    
    
    public List<Prueba> recuperarPruebasSolicitadasIncompletas(Long clave) {
        return ((MedicoDao) dao).pruebasSolicitadasPendientesDeMedico(clave);
    }//fin recuperarPruebasSolicitadasIncompletas()
    
    
    public List<Prueba> recuperarPruebasSolicitadasFinalizadas(Long clave) {
        return ((MedicoDao) dao).pruebasSolicitadasFinalizadasDeMedico(clave);
    }//fin recuperarPruebasSolicitadasFinalizadas(Long clave)
    
    
    public List<Paciente> recuperarPacientes(Long clave) {
        return ((MedicoDao) dao).pacientesDeMedico(clave);
    }//fin recuperarPacientes(Long clave)
    
    
    public Medico loginMedico(String nombreLogin, String password, boolean passwordEstaEncriptado)
            throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion {
        
        Medico medico = (Medico) dao.buscarPorCriterio("nombreLogin", nombreLogin); 
        if (medico == null)
            throw new InstanciaNoEncontradaExcepcion(nombreLogin, "nombre login", Medico.class.getSimpleName());
        String passwordGuardado = medico.getPasswordEncriptado();
        
        if (passwordEstaEncriptado){
            if (!password.equals(passwordGuardado))
                throw new PasswordIncorrectoExcepcion();
        }//fin if (passwordEstaEncriptado)
        else {
            if (!Encriptador.esPasswordCorrecto(password, passwordGuardado))
                throw new PasswordIncorrectoExcepcion();
        }//fin else
        
        return medico;
    }//fin loginMedico(String nombreLogin, String password, boolean passwordEstaEncriptado)
    
    
    public void modificarMedico(Long clave, String dni, String nombre, String apellido1, String apellido2, Calendar fecha, Integer telefono, Direccion direccion, String nombreLogin, String password, String passwordRepetido)
            throws VerificacionPasswordExcepcion, FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion {
        Medico medico = (Medico) dao.buscar(clave);
        
        if (password != null || passwordRepetido != null)
            if (password== null || !password.equals(passwordRepetido))
                throw new VerificacionPasswordExcepcion();
            else medico.setPasswordEncriptado(Encriptador.encriptar(password));
        
        if (dao.buscarPorCriterio("dni", new Dni(dni)) != null &&
                !((Medico) dao.buscar(medico.getClave())).getDni().equals(new Dni(dni)))
            throw new InstanciaDuplicadaExcepcion(new Dni(dni), "dni", MedicoServicioImpl.class.getSimpleName());
        
        medico.setDni(new Dni(dni));
        medico.setNombre(nombre);
        medico.setApellido1(apellido1);
        medico.setApellido2(apellido2);
        medico.setTelefono(telefono);
        medico.setNombreLogin(nombreLogin);
        medico.setDireccion(direccion);
        medico.setFechaNacimiento(fecha);
        
        dao.actualizar(medico);
    }//fin modificarMedico(Long clave, String dni, String nombre, String apellido1, String apellido2, Calendar fecha, Integer telefono, Direccion direccion, String nombreLogin, String password, String passwordRepetido)
    
    
    public void asignarPacienteMedico(Long idMedico, Long idPaciente) throws InstanciaNoEncontradaExcepcion {
        if (idMedico != null) pacienteDao.buscar(idPaciente).setMedico((Medico) dao.buscar(idMedico));
        else pacienteDao.buscar(idPaciente).setMedico(null);
    }//fin asignarPacienteMedico(Long idMedico, Long idPaciente)
    
    
    public void asignarPruebaMedico(Long idPrueba, Long idMedico) throws InstanciaNoEncontradaExcepcion {
        if (idMedico != null) pruebaDao.buscar(idPrueba).setMedico((Medico) dao.buscar(idMedico));
        else pruebaDao.buscar(idPrueba).setMedico(null);
    }//fin asignarPruebaMedico(Long idPrueba, Long idMedico)
    
    
    public Long crearInforme(Long idPaciente, Long idMedico, String observacionesPrevias, List<Plantilla> plantillas) throws InstanciaNoEncontradaExcepcion {
        Paciente paciente = pacienteDao.buscar(idPaciente);
        Medico medico = (Medico) dao.buscar(idMedico);
        
        Informe informe = new Informe(paciente, medico, observacionesPrevias, null);
        informeDao.guardar(informe);
        
        if (plantillas != null) {
            for (Iterator<Plantilla> i = plantillas.iterator(); i.hasNext();) {
                Plantilla plantilla = i.next();
                agregarPrueba(informe.getClave(), plantilla.getClave(), null);
            }//fin for (plantillas != null)
        }//fin if (plantillas != null)
        return informe.getClave();
    }//fin crearInforme(Long idPaciente, Long idMedico, String observacionesPrevias, List<Plantilla> plantillas)
    
    
    public void eliminarInforme(Long idInforme) throws InstanciaNoEncontradaExcepcion {
        Informe informe = informeDao.buscar(idInforme);
        for (Iterator<Prueba> pruebas = informe.getPruebas().iterator(); pruebas.hasNext();)
            eliminarPrueba(pruebas.next().getClave());
        informeDao.eliminar(idInforme);
    }//fin eliminarInforme(Long idInforme)
    
    
    public Long agregarPrueba(Long idInforme, Long idPlantilla, String observacionesSolicitante) throws InstanciaNoEncontradaExcepcion {
        Informe informe = informeDao.buscar(idInforme);
        Plantilla plantilla = plantillaDao.buscar(idPlantilla);
        
        Prueba prueba = new Prueba(informe.getPaciente(), informe.getMedico(), informe, plantilla);
        prueba.setObservacionesSolicitante(observacionesSolicitante);
        pruebaDao.guardar(prueba);
        for (Iterator<Parametro> i = plantilla.getParametros().iterator(); i.hasNext();) {
            Valor valor = new Valor(null, i.next());
            valorDao.guardar(valor);
            prueba.anhadirValor(valor);
        }//fin for (Iterator<Parametro> x = plantilla.getParametros().iterator(); x.hasNext();)
        return prueba.getClave();
    }//fin agregarPrueba(Long idInforme, Long idPlantilla)
    
    
    public void desvincularPrueba(Long clave) throws InstanciaNoEncontradaExcepcion {
        Prueba prueba = pruebaDao.buscar(clave);
        prueba.setMedico(null);
    }//fin desvincularPrueba(Long clave)
    
    
    public void eliminarPrueba(Long clave) throws InstanciaNoEncontradaExcepcion {
        Prueba prueba = pruebaDao.buscar(clave);
        for (Iterator<Imagen> imagenes = prueba.getImagenes().iterator(); imagenes.hasNext();)
            eliminarImagen(imagenes.next().getClave());
        
        pruebaDao.eliminar(clave);
    }//fin eliminarPrueba()
    
    
    public void agregarImagen(Long clave, UploadedFile archivo) throws InstanciaNoEncontradaExcepcion {
        Prueba prueba = (Prueba) pruebaDao.buscar(clave);
        if (prueba.getFechaInicio() == null) prueba.setFechaInicio(Calendar.getInstance());
        Imagen imagen = new Imagen();
        imagen.setPrueba(prueba);
        imagen.setImagenUrl(imagenDao.guardar(imagen, archivo));
        dao.actualizar(prueba);
    }//fin agregarImagen(Long clave, File archivo)
    
    
    public void eliminarImagen(Long clave) throws InstanciaNoEncontradaExcepcion {
        imagenDao.eliminar(clave);
    }//fin eliminarImagen(Long clave)
    
    
    public boolean esAdministrador(Long clave) throws InstanciaNoEncontradaExcepcion {
        Medico medico = (Medico) dao.buscar(clave);
        return (medico.getDepSupervisado() != null);  
    }//fin esAdministrador(Long clave)
    
    
    public List<Medico> filtro(List<Medico> medicos, String[] campos) {
        List<Medico> coincidencias = new ArrayList<Medico>();
        for (Iterator i = medicos.iterator(); i.hasNext();) {
            Medico pac = (Medico) i.next();
            if (campos[0] == null || (pac.getDni() != null && pac.getDni().getDni().toLowerCase().startsWith(campos[0].toLowerCase())))
                if (campos[1] == null || (pac.getNombre() != null && NormalizarString.normalizar(pac.getNombre()).toLowerCase().startsWith(NormalizarString.normalizar(campos[1]).toLowerCase())))
                    if (campos[2] == null || (pac.getApellido1() != null && NormalizarString.normalizar(pac.getApellido1()).toLowerCase().startsWith(NormalizarString.normalizar(campos[2]).toLowerCase())))
                        if (campos[3] == null || (pac.getApellido2() != null && NormalizarString.normalizar(pac.getApellido2()).toLowerCase().startsWith(NormalizarString.normalizar(campos[3]).toLowerCase())))
                            if (campos[4] == null || (pac.getDepartamento() != null && pac.getDepartamento().getClave().toString().equals(campos[4])))
                            coincidencias.add(pac);
        }//fin for (Iterator i = medicos.iterator(); i.hasNext();)
        return coincidencias;
    }//fin filtro(List<Medico> medico)

    /*------------------------------------------------------------------------*/


}//fin Clase MedicoServicioImpl