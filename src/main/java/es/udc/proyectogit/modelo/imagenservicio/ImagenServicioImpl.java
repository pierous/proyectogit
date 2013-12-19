/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.imagenservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.imagen.Imagen;
import es.udc.proyectogit.modelo.imagen.ImagenDao;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("imagenServicio")
@Transactional
public class ImagenServicioImpl extends GenericoServicioImp<Imagen, Long> implements ImagenServicio {


    /*------------------------------Atributos---------------------------------*/
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setImagenDao(ImagenDao imagenDao) {
		this.dao = imagenDao;
    }//fin setImagenDao(ImagenDao imagenDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/

    /*------------------------------------------------------------------------*/


}//fin Clase ImagenServicioImpl