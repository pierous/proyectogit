/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.parametro;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.utiles.configuracion.Configuracion;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { Configuracion.SPRING_CONFIG_PRUEBAS })
@Transactional
public class ParametroDaoHibernateTest {
    
    
    /*----------------------------Constructores-------------------------------*/
    
    public ParametroDaoHibernateTest() {
    }//fin ParametroDaoHibernateTest()

    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }//fin setUpClass()

    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }//fin tearDownClass()
    
    
    @Before
    public void setUp() {
    }//fin setUp()
    
    
    @After
    public void tearDown() {
    }//fin tearDown()
    
    /*------------------------------------------------------------------------*/

    
    /*-------------------------------Tests------------------------------------*/
    
    @Test
    public void testNombreTabla() {
        System.out.println("nombreTabla");
        
//        PlatformTransactionManager transactionManager = baseDatos.transactionManager;
//        ParametroDao parametroDao = baseDatos.parametroDao;
//        ValorDao valorDao = baseDatos.valorDao;
//        PlantillaDao plantillaDao = baseDatos.plantillaDao;
//        PruebaDao pruebaDao = baseDatos.pruebaDao;
//        
//        Parametro parametro = new Parametro("Volumnen", "litros");
//        Parametro parametro2 = new Parametro("Peso", "gramos");
//        Valor valor = new Valor(1, parametro);
//        Plantilla plantilla = new Plantilla("Plantilla1");
//        plantilla.anhadirParametro(parametro);
//        plantilla.anhadirParametro(parametro2);
//        Prueba prueba = new Prueba(plantilla);
//        prueba.anhadirValor(valor);
//        
//        TransactionStatus transactionStatus = transactionManager.getTransaction(null);
//        parametroDao.guardar(parametro);
//        parametroDao.guardar(parametro2);
//        valorDao.guardar(valor);
//        plantillaDao.guardar(plantilla);
//        pruebaDao.guardar(prueba);
//        
//        
//        System.out.println("Aqui parametro--->>>>" + parametro.getClave());
//        System.out.println("Aqui valor--->>>>" + valor.getClave());
//        System.out.println("Aqui plantilla--->>>>" + plantilla.getClave());
//        //System.out.println("Aqui prueba--->>>>" + prueba.getClave());
//        
//        System.out.println(parametroDao.listar());
//        System.out.println(valorDao.listar());
//        System.out.println(plantillaDao.listar());
//        //System.out.println(pruebaDao.listar());
//        
//        transactionManager.commit(transactionStatus);
//        
//        parametroDao.getClave(parametro);
    }//fin testNombreTabla()
    
    
    @Test
    public void testGetClave() {
        System.out.println("getClave");
    }//fin testGetClave()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase ParametroDaoHibernateTest