/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.valor;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDaoHibernate;
import org.springframework.stereotype.Repository;

/*----------------------------------------------------------------------------*/


@Repository("valorDao")
public class ValorDaoHibernate extends GenericoDaoHibernate<Valor, Long> implements ValorDao {


    /*----------------------------Constructores-------------------------------*/
    
    public ValorDaoHibernate() {
        super(Valor.class);
    }//fin ValorDaoHibernate()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    @Override
    protected String nombreTabla() {
        return("Valor");
    }//fin nombreTabla()
    
    
    @Override
    protected void setOrdenListado() {
    }//fin setOrdenListado()

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Parametro tipoDeValor(Long clave) {
        return (Parametro) getSession().createQuery("select tipo from Valor p where p.clave = :clave")
                .setParameter("clave", clave).uniqueResult();
    }//fin tipoDeValor(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase ValorDaoHibernate