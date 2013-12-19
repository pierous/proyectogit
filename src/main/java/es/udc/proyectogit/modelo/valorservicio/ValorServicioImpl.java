/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.valorservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.utiles.servicio.GenericoServicioImp;
import es.udc.proyectogit.modelo.valor.Valor;
import es.udc.proyectogit.modelo.valor.ValorDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@Service("valorServicio")
@Transactional
public class ValorServicioImpl extends GenericoServicioImp<Valor, Long> implements ValorServicio {


    /*------------------------------Atributos---------------------------------*/
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public void setValorDao(ValorDao valorDao) {
		this.dao = valorDao;
    }//fin setValorDao(ValorDao valorDao)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public Parametro recuperarTipo(Long clave) {
        return ((ValorDao) dao).tipoDeValor(clave);
    }//fin recuperarTipo(Long clave)

    /*------------------------------------------------------------------------*/


}//fin Clase ValorServicioImpl