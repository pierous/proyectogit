/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.imagen;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import java.io.File;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("imagenDao")
public class ImagenDaoHibernate extends GenericoDaoHibernate<Imagen, Long> implements ImagenDao {


    /*----------------------------Constructores-------------------------------*/
    
    public ImagenDaoHibernate() {
        super(Imagen.class);
    }//fin ImagenDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Imagen");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/
    
    private String terminacion(String nombre) {
        String[] partes = nombre.split("\\.");
        return partes[partes.length - 1];
    }//fin terminacion(String nombre)
    
    
    private void borrarImagen (Long clave) throws InstanciaNoEncontradaExcepcion {
        Imagen imagen = buscar(clave);
        File archivo = new File(imagen.getImagenUrl());
        archivo.delete();
    }//fin borrarImagen (Long clave)


    /*-------------------------------Metodos----------------------------------*/
    
    public String guardar(Imagen imagen, UploadedFile archivo) {
        super.guardar(imagen);
        File folder = new File("GestorDocumental/imagenes");
        if (!folder.exists()) folder.mkdirs();
        File archivoCopiado = new File("GestorDocumental/imagenes/" + imagen.getClave().toString() + "." + terminacion(archivo.getFileName()));
        archivo.write(archivoCopiado);
        return archivoCopiado.getPath();
    }//fin guardar(Imagen imagen, UploadedFile archivo)
    
    
    @Override
    public void eliminar(Long clave) throws InstanciaNoEncontradaExcepcion {
        borrarImagen(clave);
        super.eliminar(clave);
    }//fin eliminar(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase ImagenDaoHibernate