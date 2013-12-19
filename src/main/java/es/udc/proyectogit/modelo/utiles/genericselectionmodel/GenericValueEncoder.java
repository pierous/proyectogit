/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.genericselectionmodel;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tapestry5.ValueEncoder;

/*----------------------------------------------------------------------------*/


public class GenericValueEncoder<E extends Entidad> implements ValueEncoder<E> {


    /*------------------------------Atributos---------------------------------*/
    
    GenericoServicio servicio;
    Class pagina;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public GenericValueEncoder(GenericoServicio servicio, Class pagina) {
        this.servicio = servicio;
        this.pagina = pagina;
    }//fin GenericValueEncoder(GenericoServicio servicio)

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    @Override
    public String toClient(E value) {
        return String.valueOf(value.getClave());
    }//fin toClient(E value)
    
    
    @Override
    public E toValue(String id) {
        E objeto = null;
        try {
            objeto = (E) servicio.buscar(Long.parseLong(id));
        } catch (InstanciaNoEncontradaExcepcion ex) {
            Logger.getLogger(pagina.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }//fin toValue(String id)

    /*------------------------------------------------------------------------*/


}//fin Clase GenericValueEncoder