/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.parametroservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.utiles.configuracion.Configuracion;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/*----------------------------------------------------------------------------*/


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { Configuracion.SPRING_CONFIG_PRUEBAS })
@Transactional
public class ParametroServicioImplTest {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    ParametroServicio parametroServicio;
    @Autowired
    private AdministradorServicio administradorServicio;
    
    /*------------------------------------------------------------------------*/
    
    
    /*----------------------------Constructores-------------------------------*/
    
    public ParametroServicioImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception, Throwable {
    }

    @AfterClass
    public static void tearDownClass() throws Exception, Throwable {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Gets y Sets--------------------------------*/
    
    @Autowired
    public void setParametroServicio(ParametroServicio parametroServicio) {
        this.parametroServicio = parametroServicio;
    }//fin setParametroServicio(ParametroServicio parametroServicio)
    
    /*------------------------------------------------------------------------*/
    
    
    /*-------------------------------Tests------------------------------------*/
    
    @Test
    public void testModificarParametro() throws InstanciaDuplicadaExcepcion {
        System.out.println("guardar==========================");
        
        assertTrue(parametroServicio.listar().isEmpty());
        administradorServicio.agregarParametro("Peso", "Gramos", "gr");
        assertTrue(parametroServicio.listar().size() == 1);
    }//fin testModificarParametro()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase ParametroServicioImplTest