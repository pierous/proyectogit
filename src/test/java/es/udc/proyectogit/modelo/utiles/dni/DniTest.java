/** @author Ulloa Sobrino, David */

package es.udc.proyectogit.modelo.utiles.dni;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import org.junit.*;

/*----------------------------------------------------------------------------*/


public class DniTest {
    
    
    /*----------------------------Constructores-------------------------------*/
    
    public DniTest() {
    }

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
    public void testGetLetra() throws FormatoInvalidoExcepcion {
        System.out.println("getLetra");
        Dni dni = new Dni("47365744g");
        System.out.println(dni);
    }//fin testGetLetra()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase DniTest