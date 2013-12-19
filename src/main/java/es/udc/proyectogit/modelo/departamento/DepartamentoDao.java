/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.departamento;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.dao.GenericoDao;
import java.util.List;

/*----------------------------------------------------------------------------*/


public interface DepartamentoDao extends GenericoDao<Departamento, Long> {


    /*-------------------------------Metodos----------------------------------*/
    
    List<Medico> medicosDeDepartamento(Long clave);
    Medico supervisorDeDepartamento(Long clave);
    List<Paciente> pacientesDeDepartamento(Long clave);
    List<Informe> informesDeDepartamento(Long clave);
    List<Prueba> pruebasDeDepartamento(Long clave);
    List<Plantilla> plantillasDeDepartamento(Long clave);

    /*------------------------------------------------------------------------*/


}//fin Interface DepartamentoDao