/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.pruebaservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.imagen.ImagenDao;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medico.MedicoDao;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.prueba.PruebaDao;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import es.udc.proyectogit.modelo.valor.Valor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("pruebaServicio")
@Transactional
public class PruebaServicioImpl extends GenericoServicioImp<Prueba, Long> implements PruebaServicio {


    /*------------------------------Atributos---------------------------------*/
    
    @Autowired
    private MedicoDao medicoDao;
    @Autowired
    private ImagenDao imagenDao;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setPruebaDao(PruebaDao pruebaDao) {
		this.dao = pruebaDao;
    }//fin setPruebaDao(PruebaDao PruebaDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    public boolean esEditable(Long idPrueba, Long idMedico) {
        Prueba prueba = null;
        try {
            prueba = this.buscar(idPrueba);
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(PruebaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        if (prueba != null && prueba.getMedico() != null && prueba.getMedico().getClave().equals(idMedico))
            return true;
        else return false;
    }//fin esEditable(Long idPrueba, Long idMedico)
    
    
    public boolean esEliminable(Long idPrueba, Long idMedico) {
        Prueba prueba = null;
        try {
            prueba = this.buscar(idPrueba);
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(PruebaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        if (prueba != null && prueba.getSolicitante() != null && prueba.getSolicitante().getClave().equals(idMedico) && prueba.getFechaInicio() == null)
            return true;
        else return false;
    }//fin esEliminable(Long clave)
    
    
    private boolean valoresRellenados(Long clave) {
        List<Valor> valores = this.recuperarValores(clave);
        if (valores != null)
            for (Iterator i = valores.iterator(); i.hasNext();)
                if (((Valor) i.next()).getDato() == null)
                    return false;
        return true;
    }//fin valoresRellenados(Long clave)
    
    
    public boolean esFinalizable(Long idPrueba, Long idMedico) {
        if (esEditable(idPrueba, idMedico) && valoresRellenados(idPrueba) && !esFinalizado(idPrueba))
            return true;
        else return false;
    }//fin esFinalizable(Long idPrueba, Long idMedico)
    
    
    public boolean esAgregable(Long idPrueba, Long idMedico) {
        boolean resultado = false;
        try {
            Prueba prueba = this.buscar(idPrueba);
            Medico medico = medicoDao.buscar(idMedico);
            if (prueba.getMedico() == null && prueba.getPlantilla().getDepartamento().getClave().equals(medico.getDepartamento().getClave()))
                resultado = true;
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(PruebaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        return resultado;
    }//fin esAgregable(Long idPrueba, Long idMedico)
    
    
    public boolean esDesvinculable(Long idPrueba, Long idMedico) {
        boolean resultado = false;
        try {
            Prueba prueba = this.buscar(idPrueba);
            if (prueba.getMedico() != null && prueba.getMedico().getClave().equals(idMedico) && !esFinalizado(idPrueba))
                resultado = true;
            else
            if (prueba.getMedico() != null &&
                medicoDao.depSupervisadoDeMedico(idMedico) != null &&
                medicoDao.depSupervisadoDeMedico(idMedico).getClave().equals(recuperarPlantilla(idPrueba).getDepartamento().getClave()) &&
                !esFinalizado(idPrueba))
                resultado = true;
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(PruebaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        return resultado;
    }//fin esDesvinculable(Long idPrueba, Long idMedico)
    
    
    public boolean esIniciado(Long clave) {
        boolean resultado = false;
        try {
            resultado = ((Prueba) dao.buscar(clave)).getFechaInicio() != null;
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(PruebaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        return resultado;
    }//fin
    
    
    public boolean esFinalizado(Long clave) {
        boolean resultado = false;
        try {
            resultado = ((Prueba) dao.buscar(clave)).getFechaFin() != null;
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(PruebaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }//fin try
        return resultado;
    }//fin esFinalizado(Long clave)
    
    
    public List<Prueba> filtro(int tipoFecha, List<Prueba> pruebas, Calendar desde, Calendar hasta) {
        if (desde != null) desde.add(Calendar.MILLISECOND, -1);
        if (hasta != null) hasta.add(Calendar.DAY_OF_MONTH, 1);
        List<Prueba> resultado = new ArrayList<Prueba>();
        if (tipoFecha == 1) {
            for (int i = 0; i < pruebas.size(); i++) {
                Prueba prueba = pruebas.get(i);
                if ((desde == null || desde.before(prueba.getFechaInicio())) && (hasta == null || hasta.after(prueba.getFechaInicio())))
                    resultado.add(prueba);
            }//fin for (int i = 0; i < pruebas.size(); i++)
            return resultado;
        }//fin if (tipoFecha == 1)
        if (tipoFecha == 2) {
            for (int i = 0; i < pruebas.size(); i++) {
                Prueba prueba = pruebas.get(i);
                if ((desde == null || desde.before(prueba.getFechaFin())) && (hasta == null || hasta.after(prueba.getFechaFin())))
                    resultado.add(prueba);
            }//fin for (int i = 0; i < pruebas.size(); i++)
            return resultado;
        }//fin if (tipoFecha == 2)
        if (tipoFecha == 3) {
            for (int i = 0; i < pruebas.size(); i++) {
                Prueba prueba = pruebas.get(i);
                if ((desde == null || desde.before(prueba.getFechaSolicitud())) && (hasta == null || hasta.after(prueba.getFechaSolicitud())))
                    resultado.add(prueba);
            }//fin for (int i = 0; i < pruebas.size(); i++)
            return resultado;
        }//fin if (tipoFecha == 3)
        return pruebas;
    }//fin filtro(List<Informe> informes)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void modificarPrueba(Prueba prueba) {
        dao.actualizar(prueba);
    }//fin modificarPrueba()
    
    
    public List<Imagen> recuperarImagenes(Long clave) {
        return ((PruebaDao) dao).imagenesDePrueba(clave);
    }//fin recuperarImagenes(Long clave)
    
    
    public List<Prueba> recuperarPruebasIncompletas() {
        List<Prueba> pruebas = this.listar();
        List<Prueba> incompletas = new ArrayList<Prueba>();
        for (Iterator i = pruebas.iterator(); i.hasNext();) {
            Prueba prueba = (Prueba) i.next();
            if (prueba.getFechaFin() == null) incompletas.add(prueba);
        }//fin for (Iterator i = pruebas.iterator(); i.hasNext();)
        return incompletas;
    }//fin recuperarPruebasIncompletas()
    
    
    public List<Prueba> recuperarPruebasFinalizadas() {
        List<Prueba> pruebas = this.listar();
        List<Prueba> finalizadas = new ArrayList<Prueba>();
        for (Iterator i = pruebas.iterator(); i.hasNext();) {
            Prueba prueba = (Prueba) i.next();
            if (prueba.getFechaFin() != null) finalizadas.add(prueba);
        }//fin for (Iterator i = pruebas.iterator(); i.hasNext();)
        return finalizadas;
    }//fin recuperarPruebasFinalizadas()
    
    
    public Plantilla recuperarPlantilla(Long clave) {
        return ((PruebaDao) dao).platillaDePrueba(clave);
    }//fin recuperarPlantilla(Long clave)
    
    
    public Paciente recuperarPaciente(Long clave) {
        return ((PruebaDao) dao).pacienteDePrueba(clave);
    }//fin recuperarPaciente(Long clave)
    
    
    public Medico recuperarSolicitante(Long clave) {
        return ((PruebaDao) dao).solicitanteDePrueba(clave);
    }//fin recuperarSolicitante(Long clave)
    
    
    public Medico recuperarMedico(Long clave) {
        return ((PruebaDao) dao).medicoDePrueba(clave);
    }//fin recuperarMedico(Long clave)
    
    
    public Informe recuperarInforme(Long clave) {
        return ((PruebaDao) dao).informeDePrueba(clave);
    }//fin recuperarInforme(Long clave)
    
    
    public List<Valor> recuperarValores(Long clave) {
        return ((PruebaDao) dao).valoresDePrueba(clave);
    }//fin recuperarValores(Long clave)
    
    
    public Departamento recuperarDepartamento(Long clave) {
        Plantilla plantilla = recuperarPlantilla(clave);
        return plantilla.getDepartamento();
    }//fin recuperarDepartamento(Long clave)
    
    
    public void iniciarPrueba(Long clave) throws InstanciaNoEncontradaExcepcion {
        Prueba prueba = (Prueba) dao.buscar(clave);
        prueba.setFechaInicio(Calendar.getInstance());
    }//fin iniciarPrueba(Long clave)
    
    
    public void finalizarPrueba(Long clave) throws InstanciaNoEncontradaExcepcion {
        Prueba prueba = (Prueba) dao.buscar(clave);
        prueba.setFechaFin(Calendar.getInstance());
    }//fin finalizarPrueba()

    /*------------------------------------------------------------------------*/


}//fin Clase PruebaServicioImpl