/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.administrador;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("administradorDao")
public class AdministradorDaoHibernate extends GenericoDaoHibernate<Administrador, Long> implements AdministradorDao {


    /*----------------------------Constructores-------------------------------*/
    
    public AdministradorDaoHibernate() {
        super(Administrador.class);
    }//fin DepartamentoDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Administrador");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
        orden.add("apellido1");
        orden.add("apellido2");
        orden.add("nombre");
        orden.add("dni");
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    

    /*------------------------------------------------------------------------*/


}//fin Clase AdministradorDaoHibernate