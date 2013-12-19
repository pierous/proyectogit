/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.informeservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.informe.InformeDao;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("informeServicio")
@Transactional
public class InformeServicioImpl extends GenericoServicioImp<Informe, Long> implements InformeServicio {


    /*------------------------------Atributos---------------------------------*/
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setInformeDao(InformeDao informeDao) {
		this.dao = informeDao;
    }//fin setInformeDao(InformeDao informeDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    public boolean esEditable(Long idInforme, Long idMedico) {
        Informe informe = null;
        try {
            informe = this.buscar(idInforme);
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(InformeServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        if (informe != null && informe.getMedico() != null && informe.getMedico().getClave().equals(idMedico))
            return true;
        else return false;
    }//fin esEditable(Long idInforme, Long idMedico)
    
    
    public boolean esEliminable(Long idInforme, Long idMedico) {
        Informe informe = null;
        try {
            informe = this.buscar(idInforme);
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(InformeServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        if (informe != null && informe.getMedico() != null && informe.getMedico().getClave().equals(idMedico) && informe.getFechaFin() == null)
            return true;
        else return false;
    }//fin esEliminable(Long idInforme, Long idMedico)
    
    
    private boolean pruebasTerminadas(Long clave) {
        List<Prueba> pruebas = this.recuperarPruebas(clave);
        if (pruebas != null)
            for (Iterator i = pruebas.iterator(); i.hasNext();)
                if (((Prueba) i.next()).getFechaFin() == null)
                    return false;
        return true;
    }//fin pruebasTerminadas(Long clave)
    
    
    public boolean esFinalizable(Long idInforme, Long idMedico) throws InstanciaNoEncontradaExcepcion {
        if (esEditable(idInforme, idMedico) && pruebasTerminadas(idInforme) && !esFinalizado(idInforme) &&
                ((Informe) dao.buscar(idInforme)).getDiagnostico() != null)
            return true;
        else return false;
    }//fin esFinalizable(Long idInforme, Long idMedico)
    
    
    public boolean esFinalizado(Long clave) {
        boolean resultado = false;
        try {
            resultado = ((Informe) dao.buscar(clave)).getFechaFin() != null;
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(InformeServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        return resultado;
    }//fin esFinalizado(Long clave)
    
    
    public List<Informe> filtro(int tipoFecha, List<Informe> informes, Calendar desde, Calendar hasta) {
        if (desde != null) desde.add(Calendar.MILLISECOND, -1);
        if (hasta != null) hasta.add(Calendar.DAY_OF_MONTH, 1);
        List<Informe> resultado = new ArrayList<Informe>();
        if (tipoFecha == 1) {
            for (int i = 0; i < informes.size(); i++) {
                Informe informe = informes.get(i);
                if ((desde == null || desde.before(informe.getFechaInicio())) && (hasta == null || hasta.after(informe.getFechaInicio())))
                    resultado.add(informe);
            }//fin for (Iterator<Informe> i = informes.iterator(); i.hasNext();)
            return resultado;
        }//fin if (tipoFecha == 1)
        if (tipoFecha == 2) {
            for (int i = 0; i < informes.size(); i++) {
                Informe informe = informes.get(i);
                if ((desde == null || desde.before(informe.getFechaFin())) && (hasta == null || hasta.after(informe.getFechaFin())))
                    resultado.add(informe);
            }//fin for (Iterator<Informe> i = informes.iterator(); i.hasNext();)
            return resultado;
        }//fin if (tipoFecha == 2)
        return informes;
    }//fin filtro(List<Informe> informes)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public List<Prueba> recuperarPruebas(Long clave) {
        return ((InformeDao) dao).pruebasDeInforme(clave);
    }//fin recuperarPruebas(Long clave)
    
    
    public List<Prueba> recuperarPruebasIncompletas(Long clave) {
        return ((InformeDao) dao).pruebasIncompletasDeInforme(clave);
    }//fin recuperarPruebasIncompletas(Long clave)
    
    
    public List<Prueba> recuperarPruebasFinalizadas(Long clave) {
        return ((InformeDao) dao).pruebasFinalizadasDeInforme(clave);
    }//fin recuperarPruebasFinalizadas(Long clave)
    
    
    public List<Informe> recuperarInformesIncompletos() {
        List<Informe> informes = this.listar();
        List<Informe> incompletos = new ArrayList<Informe>();
        for (Iterator i = informes.iterator(); i.hasNext();) {
            Informe informe = (Informe) i.next();
            if (informe.getFechaFin() == null) incompletos.add(informe);
        }//fin for (Iterator i = informes.iterator(); i.hasNext();)
        return incompletos;
    }//fin recuperarInformesIncompletos()
    
    
    public List<Informe> recuperarInformesFinalizados() {
        List<Informe> informes = this.listar();
        List<Informe> finalizados = new ArrayList<Informe>();
        for (Iterator i = informes.iterator(); i.hasNext();) {
            Informe informe = (Informe) i.next();
            if (informe.getFechaFin() != null) finalizados.add(informe);
        }//fin for (Iterator i = informes.iterator(); i.hasNext();)
        return finalizados;
    }//fin recuperarInformesFinalizados()
    
    
    public Paciente recuperarPaciente(Long clave) {
        return ((InformeDao) dao).pacienteDeInforme(clave);
    }//fin recuperarPaciente(Long clave)
    
    
    public Medico recuperarMedico(Long clave) {
        return ((InformeDao) dao).medicoDeInforme(clave);
    }//fin recuperarMedico(Long clave)
    
    
    public void finalizarInforme(Long clave) throws InstanciaNoEncontradaExcepcion {
        Informe informe = (Informe) dao.buscar(clave);
        informe.finalizarInforme();
        dao.actualizar(informe);
    }//fin finalizarInforme(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase InformeServicioImpl