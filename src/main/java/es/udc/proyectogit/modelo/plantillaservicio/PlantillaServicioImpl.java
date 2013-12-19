/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.plantillaservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicioImpl;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametro.ParametroDao;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.plantilla.PlantillaDao;
import es.udc.proyectogit.modelo.utiles.busqueda.NormalizarString;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("plantillaServicio")
@Transactional
public class PlantillaServicioImpl extends GenericoServicioImp<Plantilla, Long> implements PlantillaServicio {


    /*------------------------------Atributos---------------------------------*/
    
    @Autowired
    private ParametroDao parametroDao;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setPlantillaDao(PlantillaDao plantillaDao) {
		this.dao = plantillaDao;
    }//fin setPlantillaDao(PlantillaDao plantillaDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    public List<Plantilla> filtro(List<Plantilla> plantillas, String nombre, Long claveDepartamento) {
        List<Plantilla> coincidencias = new ArrayList<Plantilla>();
        for (Iterator i = plantillas.iterator(); i.hasNext();) {
            Plantilla pac = (Plantilla) i.next();
            if ((nombre == null || (pac.getNombre() != null &&
                    NormalizarString.normalizar(pac.getNombre()).toLowerCase().startsWith(NormalizarString.normalizar(nombre).toLowerCase()))) &&
                    (claveDepartamento == null || (pac.getDepartamento().getClave().equals(claveDepartamento))))
                coincidencias.add(pac);
        }//fin for (Iterator i = plantillas.iterator(); i.hasNext();)
        return coincidencias;
    }//fin filtro(List<Plantilla> plantillas, String nombre)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void modificarPlantilla(Long clave, String nombre, Departamento departamento, List<Parametro> parametros)
            throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion {
        Plantilla plantilla = (Plantilla) dao.buscar(clave);
        
        if (dao.buscarPorCriterio("nombre", nombre) != null &&
                !((Plantilla) dao.buscar(plantilla.getClave())).getNombre().equals(nombre))
            throw new InstanciaDuplicadaExcepcion(nombre, "nombre", MedicoServicioImpl.class.getSimpleName());
        
        plantilla.setNombre(nombre);
        plantilla.setDepartamento(departamento);
        plantilla.setParametros(new HashSet<Parametro>(parametros));
        
        dao.actualizar(plantilla);
    }//fin modificarPlantilla()
    
    
    public List<Parametro> recuperarParametros(Long clave) {
        return parametroDao.parametrosDePlantilla(clave);
    }//fin recuperarParametros(Long clave)
    
    
    public Departamento recuperarDepartamento(Long clave) {
        return ((PlantillaDao) dao).departamentoDePlantilla(clave);
    }//fin recuperarDepartamento(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase PlantillaServicioImpl