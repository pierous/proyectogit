/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.parametro;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("parametroDao")
public class ParametroDaoHibernate extends GenericoDaoHibernate<Parametro, Long> implements ParametroDao {


    /*----------------------------Constructores-------------------------------*/
    
    public ParametroDaoHibernate() {
        super(Parametro.class);
    }//fin EmpleadoDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Parametro");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
        orden.add("nombre");
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public List<Parametro> parametrosDePlantilla(Long clave) {
        Plantilla plantilla = (Plantilla) getSession().createQuery("select p from Plantilla p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
        return new ArrayList(plantilla.getParametros());
    }//fin parametrosDePlantilla(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase ParametroDaoHibernate