/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.utiles.baseDatos;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administrador.AdministradorDao;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamento.DepartamentoDao;
import es.udc.proyectogit.modelo.informe.Informe;
import es.udc.proyectogit.modelo.informe.InformeDao;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medico.MedicoDao;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.paciente.PacienteDao;
import es.udc.proyectogit.modelo.parametro.Parametro;
import es.udc.proyectogit.modelo.parametro.ParametroDao;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.plantilla.PlantillaDao;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.prueba.PruebaDao;
import es.udc.proyectogit.modelo.utiles.dni.Dni;
import es.udc.proyectogit.modelo.valor.Valor;
import es.udc.proyectogit.modelo.valor.ValorDao;
import es.udc.proyectogit.utiles.configuracion.Configuracion;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

/*----------------------------------------------------------------------------*/


public class baseDatos {


    /*------------------------------Atributos---------------------------------*/
    
    static {
        ApplicationContext context = new ClassPathXmlApplicationContext(Configuracion.SPRING_CONFIG_PRUEBAS);
        transactionManager = (PlatformTransactionManager) context.getBean("transactionManager");
        parametroDao = (ParametroDao) context.getBean("parametroDao");
        valorDao = (ValorDao) context.getBean("valorDao");
        departamentoDao = (DepartamentoDao) context.getBean("departamentoDao");
        informeDao = (InformeDao) context.getBean("informeDao");
        medicoDao = (MedicoDao) context.getBean("medicoDao");
        pacienteDao = (PacienteDao) context.getBean("pacienteDao");
        plantillaDao = (PlantillaDao) context.getBean("plantillaDao");
        pruebaDao = (PruebaDao) context.getBean("pruebaDao");
        administradorDao = (AdministradorDao) context.getBean("administradorDao");
    }
    
    public static PlatformTransactionManager transactionManager;
    public static ParametroDao parametroDao;
    public static ValorDao valorDao;
    public static DepartamentoDao departamentoDao;
    public static InformeDao informeDao;
    public static MedicoDao medicoDao;
    public static PacienteDao pacienteDao;
    public static PlantillaDao plantillaDao;
    public static PruebaDao pruebaDao;
    public static AdministradorDao administradorDao;
    
    private static Parametro parametro;
    private static Valor valor;
    private static Departamento departamento;
    private static Informe informe;
    private static Medico medico;
    private static Paciente paciente;
    private static Plantilla plantilla;
    private static Prueba prueba;
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public static Parametro getParametro() {
        return parametro;
    }//fin getParametro()
    
    public static Valor getValor() {
        return valor;
    }//fin getValor()
    
    public static Departamento getDepartamento() {
        return departamento;
    }//fin getDepartamento()
    
    public static Informe getInforme() {
        return informe;
    }//fin getInforme()
    
    public static Medico getMedico() {
        return medico;
    }//fin getMedico()
    
    public static Paciente getPaciente() {
        return paciente;
    }//fin getPaciente()
    
    public static Plantilla getPlantilla() {
        return plantilla;
    }//fin getPlantilla()
    
    public static Prueba getPrueba() {
        return prueba;
    }//fin getPrueba()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public static void llenarBD() throws Throwable {
        TransactionStatus transactionStatus = transactionManager.getTransaction(null);
        
        parametro = new Parametro("Peso", "kilogramos", "kg");
        valor = new Valor(2.0, parametro);
        departamento = new Departamento("Nutricion");
        plantilla = new Plantilla("Plantilla peso", departamento);
        plantilla.anhadirParametro(parametro);
        paciente = new Paciente(new Dni("47365744G"), "David", "Ulloa", "Sobrino", null, 981297097, null);
        medico = new Medico(new Dni("47365744g"), "Carlos", "Barral", "Lopez", null, 981981981, null, "pierous", null);
        medico.anhadirPaciente(paciente);
        informe = new Informe(paciente, medico, "Esta muy mal", null);
        informe.setMedico(medico);
        prueba = new Prueba(paciente, medico, informe, plantilla);
        prueba.anhadirValor(valor);
        prueba.setMedico(medico);
        informe.anhadirPrueba(prueba);
        departamento.anhadirMedico(medico);
        
        try {
            parametroDao.guardar(parametro);
            valorDao.guardar(valor);
            plantillaDao.guardar(plantilla);
            pacienteDao.guardar(paciente);
            medicoDao.guardar(medico);
            pruebaDao.guardar(prueba);
            informeDao.guardar(informe);
            departamentoDao.guardar(departamento);
            
            transactionManager.commit(transactionStatus);
        } catch (Throwable e) {
            transactionManager.rollback(transactionStatus);
            throw e;
        }//fin try
    }//fin llenarBD()
    
    
    public static void vaciarBD() throws Throwable {
        TransactionStatus transactionStatus = transactionManager.getTransaction(null);
        
        try {
            pacienteDao.eliminarTodo();
            medicoDao.eliminarTodo();
            departamentoDao.eliminarTodo();
            informeDao.eliminarTodo();
            pruebaDao.eliminarTodo();
            plantillaDao.eliminarTodo();
            valorDao.eliminarTodo();
            parametroDao.eliminarTodo();
            administradorDao.eliminarTodo();
            
            transactionManager.commit(transactionStatus);
        } catch (Throwable e) {
            transactionManager.rollback(transactionStatus);
            throw e;
        }//fin try
    }//fin vaciarBD()

    /*------------------------------------------------------------------------*/


}//fin Clase baseDatos