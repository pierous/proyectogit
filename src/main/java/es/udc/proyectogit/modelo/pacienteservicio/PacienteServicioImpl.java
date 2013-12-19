/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.pacienteservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.informe.InformeDao;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.paciente.PacienteDao;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.prueba.PruebaDao;
import es.udc.proyectogit.modelo.utiles.busqueda.NormalizarString;
import es.udc.proyectogit.modelo.utiles.direccion.Direccion;
import es.udc.proyectogit.modelo.utiles.dni.Dni;
import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("pacienteServicio")
@Transactional
public class PacienteServicioImpl extends GenericoServicioImp<Paciente, Long> implements PacienteServicio {


    /*------------------------------Atributos---------------------------------*/
    
    @Autowired
    private InformeDao informeDao;
    @Autowired
    private PruebaDao pruebaDao;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setPacienteDao(PacienteDao pacienteDao) {
		this.dao = pacienteDao;
    }//fin setPacienteDao(PacienteDao pacienteDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Medico recuperarMedico(Long idPaciente) {
        return ((PacienteDao) dao).medicoDePaciente(idPaciente);
    }//fin recuperarMedico(Long idPaciente)
    
    
    public List<Informe> recuperarInformes(Long idPaciente) {
        return ((PacienteDao) dao).informesDePaciente(idPaciente);
    }//fin recuperarInformes(Long idPaciente)
    
    
    public List<Informe> recuperarInformesPendientes(Long idPaciente) {
        return ((PacienteDao) dao).informesPendientesDePaciente(idPaciente);
    }//fin recuperarInformesPendientes(Long idPaciente)
    
    
    public List<Informe> recuperarInformesFinalizados(Long idPaciente) {
        return ((PacienteDao) dao).informesFinalizadosDePaciente(idPaciente);
    }//fin recuperarInformesFinalizados(Long idPaciente)
    
    
    public List<Prueba> recuperarPruebas(Long idPaciente) {
        return ((PacienteDao) dao).pruebasDePaciente(idPaciente);
    }//fin recuperarPruebas(Long idPaciente)
    
    
    public List<Prueba> recuperarPruebasPendientes(Long idPaciente) {
        return ((PacienteDao) dao).pruebasPendientesDePaciente(idPaciente);
    }//fin recuperarPruebasPendientes(Long idPaciente)
    
    
    public List<Prueba> recuperarPruebasFinalizadas(Long idPaciente) {
        return ((PacienteDao) dao).pruebasFinalizadasDePaciente(idPaciente);
    }//fin recuperarPruebasFinalizadas(Long idPaciente)
    
    
    public void modificarPaciente(Long clave, String dni, String nombre, String apellido1, String apellido2, Calendar fecha, Integer telefono, Direccion direccion)
            throws FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion {
        Paciente paciente = (Paciente) dao.buscar(clave);
        
        if (dao.buscarPorCriterio("dni", new Dni(dni)) != null &&
                !((Paciente) dao.buscar(paciente.getClave())).getDni().equals(new Dni(dni)))
            throw new InstanciaDuplicadaExcepcion(new Dni(dni), "dni", PacienteServicioImpl.class.getSimpleName());
        
        paciente.setDni(new Dni(dni));
        paciente.setNombre(nombre);
        paciente.setApellido1(apellido1);
        paciente.setApellido2(apellido2);
        paciente.setTelefono(telefono);
        paciente.setDireccion(direccion);
        paciente.setFechaNacimiento(fecha);
        
        dao.actualizar(paciente);
    }//fin modificarPaciente(Long clave, String dni, String nombre, String apellido1, String apellido2, Calendar fecha, Integer telefono, Direccion direccion)
    
    
    public List<Paciente> filtro(List<Paciente> pacientes, String[] campos) {
        List<Paciente> coincidencias = new ArrayList<Paciente>();
        for (Iterator i = pacientes.iterator(); i.hasNext();) {
            Paciente pac = (Paciente) i.next();
            if (campos[0] == null || (pac.getDni() != null && pac.getDni().getDni().toLowerCase().startsWith(campos[0].toLowerCase())))
                if (campos[1] == null || (pac.getNombre() != null && NormalizarString.normalizar(pac.getNombre()).toLowerCase().startsWith(NormalizarString.normalizar(campos[1]).toLowerCase())))
                    if (campos[2] == null || (pac.getApellido1() != null && NormalizarString.normalizar(pac.getApellido1()).toLowerCase().startsWith(NormalizarString.normalizar(campos[2]).toLowerCase())))
                        if (campos[3] == null || (pac.getApellido2() != null && NormalizarString.normalizar(pac.getApellido2()).toLowerCase().startsWith(NormalizarString.normalizar(campos[3]).toLowerCase())))
                            coincidencias.add(pac);
        }//fin for (Iterator i = administradores.iterator(); i.hasNext();)
        return coincidencias;
    }//fin filtro(List<Paciente> pacientes)

    /*------------------------------------------------------------------------*/


}//fin Clase PacienteServicioImpl