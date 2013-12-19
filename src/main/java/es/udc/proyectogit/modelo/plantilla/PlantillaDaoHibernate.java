/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.plantilla;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("plantillaDao")
public class PlantillaDaoHibernate extends GenericoDaoHibernate<Plantilla, Long> implements PlantillaDao {


    /*----------------------------Constructores-------------------------------*/
    
    public PlantillaDaoHibernate() {
        super(Plantilla.class);
    }//fin DepartamentoDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Plantilla");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
        orden.add("nombre");
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Departamento departamentoDePlantilla(Long clave) {
        return (Departamento) getSession().createQuery("select departamento from Plantilla p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin departamentoDePlantilla(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase PlantillaDaoHibernate