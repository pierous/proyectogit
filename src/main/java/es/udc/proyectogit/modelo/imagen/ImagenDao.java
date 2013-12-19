/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.imagen;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import org.apache.tapestry5.upload.services.UploadedFile;

/*----------------------------------------------------------------------------*/


public interface ImagenDao extends GenericoDao<Imagen, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    String guardar(Imagen imagen, UploadedFile archivo);
    void eliminar(Long clave) throws InstanciaNoEncontradaExcepcion;

    /*------------------------------------------------------------------------*/


}//fin Interface ImagenDao