/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.departamentoservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamento.DepartamentoDao;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicioImpl;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.busqueda.NormalizarString;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("departamentoServicio")
@Transactional
public class DepartamentoServicioImpl extends GenericoServicioImp<Departamento, Long> implements DepartamentoServicio {


    /*------------------------------Atributos---------------------------------*/
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setDepartamentoDao(DepartamentoDao departamentoDao) {
		this.dao = departamentoDao;
    }//fin setDepartamentoDao(DepartamentoDao departamentoDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    public List<Departamento> filtro(List<Departamento> departamentos, String nombre) {
        List<Departamento> coincidencias = new ArrayList<Departamento>();
        for (Iterator i = departamentos.iterator(); i.hasNext();) {
            Departamento pac = (Departamento) i.next();
            if (nombre == null || (pac.getNombre() != null &&
                    NormalizarString.normalizar(pac.getNombre()).toLowerCase().startsWith(NormalizarString.normalizar(nombre).toLowerCase())))
                coincidencias.add(pac);
        }//fin for (Iterator i = departamentos.iterator(); i.hasNext();)
        return coincidencias;
    }//fin filtro(List<Departamento> departamentos, String nombre)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void modificarDepartamento(Long clave, String nombre, Medico supervisor)
            throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion {
        Departamento departamento = (Departamento) dao.buscar(clave);
        
        if (dao.buscarPorCriterio("nombre", nombre) != null &&
                !((Departamento) dao.buscar(departamento.getClave())).getNombre().equals(nombre))
            throw new InstanciaDuplicadaExcepcion(nombre, "nombre", MedicoServicioImpl.class.getSimpleName());
        
        departamento.setNombre(nombre);
        departamento.setSupervisor(supervisor);
        
        dao.actualizar(departamento);
    }//fin modificarAdministrador()
    
    
    public List<Medico> recuperarMedicos(Long clave) {
        return ((DepartamentoDao) dao).medicosDeDepartamento(clave);
    }//fin medicosDeDepartamento(Long clave)
    
    
    public Medico recuperarSupervisor(Long clave) {
        return ((DepartamentoDao) dao).supervisorDeDepartamento(clave);
    }//fin supervisorDeDepartamento(Long clave)
    
    
    public List<Paciente> recuperarPacientes(Long clave) {
        return ((DepartamentoDao) dao).pacientesDeDepartamento(clave);
    }//fin recuperarPacientes(Long clave)
    
    
    public List<Informe> recuperarInformes(Long clave) {
        return ((DepartamentoDao) dao).informesDeDepartamento(clave);
    }//fin recuperarPacientes(Long clave)
    
    
    public List<Informe> recuperarInformesIncompletos(Long clave) {
        List<Informe> incompletos = new ArrayList<Informe>();
        for (Iterator i = recuperarInformes(clave).iterator(); i.hasNext();) {
            Informe informe = (Informe) i.next();
            if (informe.getFechaFin() == null) incompletos.add(informe);
        }//fin for (Iterator i = recuperarInformes(clave).iterator(); i.hasNext();)
        return incompletos;
    }//fin recuperarInformesIncompletos(Long clave)
    
    
    public List<Informe> recuperarInformesFinalizados(Long clave) {
        List<Informe> finalizados = new ArrayList<Informe>();
        for (Iterator i = recuperarInformes(clave).iterator(); i.hasNext();) {
            Informe informe = (Informe) i.next();
            if (informe.getFechaFin() != null) finalizados.add(informe);
        }//fin for (Iterator i = recuperarInformes(clave).iterator(); i.hasNext();)
        return finalizados;
    }//fin recuperarInformesFinalizados(Long clave)
    
    
    public List<Prueba> recuperarPruebas(Long clave) {
        return ((DepartamentoDao) dao).pruebasDeDepartamento(clave);
    }//fin recuperarPruebas(Long clave)
    
    
    public List<Prueba> recuperarPruebasIncompletas(Long clave) {
        List<Prueba> incompletas = new ArrayList<Prueba>();
        for (Iterator i = recuperarPruebas(clave).iterator(); i.hasNext();) {
            Prueba prueba = (Prueba) i.next();
            if (prueba.getFechaFin() == null) incompletas.add(prueba);
        }//fin for (Iterator i = recuperarPruebas(clave).iterator(); i.hasNext();)
        return incompletas;
    }//fin recuperarPruebasIncompletas(Long clave)
    
    
    public List<Prueba> recuperarPruebasFinalizadas(Long clave) {
        List<Prueba> finalizadas = new ArrayList<Prueba>();
        for (Iterator i = recuperarPruebas(clave).iterator(); i.hasNext();) {
            Prueba prueba = (Prueba) i.next();
            if (prueba.getFechaFin() != null) finalizadas.add(prueba);
        }//fin for (Iterator i = recuperarPruebas(clave).iterator(); i.hasNext();)
        return finalizadas;
    }//fin recuperarPruebasFinalizadas(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase DepartamentoServicioImpl