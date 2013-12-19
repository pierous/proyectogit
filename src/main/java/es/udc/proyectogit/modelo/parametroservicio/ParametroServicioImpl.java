/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.parametroservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametro.ParametroDao;
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


@Service("parametroServicio")
@Transactional
public class ParametroServicioImpl extends GenericoServicioImp<Parametro, Long> implements ParametroServicio {


    /*------------------------------Atributos---------------------------------*/
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setParametroDao(ParametroDao parametroDao) {
		this.dao = parametroDao;
    }//fin setParametroDao(ParametroDao parametroDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    public List<Parametro> filtro(List<Parametro> parametros, String nombre) {
        List<Parametro> coincidencias = new ArrayList<Parametro>();
        for (Iterator i = parametros.iterator(); i.hasNext();) {
            Parametro pac = (Parametro) i.next();
            if (nombre == null || (pac.getNombre() != null && NormalizarString.normalizar(pac.getNombre()).toLowerCase().startsWith(NormalizarString.normalizar(nombre).toLowerCase())))
                coincidencias.add(pac);
        }//fin for (Iterator i = parametros.iterator(); i.hasNext();)
        return coincidencias;
    }//fin iltro(List<Parametro> parametros, String nombre)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void modificarParametro(Long clave, String nombre, String unidad, String unidadAbreviada)
            throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion {
        Parametro parametro = (Parametro) dao.buscar(clave);
        
        if (dao.buscarPorCriterio("nombre", nombre) != null &&
                !((Parametro) dao.buscar(parametro.getClave())).getNombre().equals(nombre))
            throw new InstanciaDuplicadaExcepcion(nombre, "nombre", ParametroServicioImpl.class.getSimpleName());
        
        parametro.setNombre(nombre);
        parametro.setUnidad(unidad);
        parametro.setUnidadAbreviada(unidadAbreviada);
        
        dao.actualizar(parametro);
    }//fin modificarParametro()

    /*------------------------------------------------------------------------*/


}//fin Clase ParametroServicioImpl