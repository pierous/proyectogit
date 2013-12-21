/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.password;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/*----------------------------------------------------------------------------*/


public class EncriptadorTest {
    
    
    /*----------------------------Constructores-------------------------------*/
    
    public EncriptadorTest() {
    }//fin EncriptadorTest()

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
    public void testEncriptar() throws FormatoInvalidoExcepcion {
        System.out.println("encriptar");
        String cadena = "or3/qwa";
        String cadenaEncriptada = Encriptador.encriptar(cadena);
        
        assertEquals(cadena, Encriptador.desencriptar(cadenaEncriptada));
        System.out.println(cadena + " -> " + cadenaEncriptada);
    }//fin testEncriptar()
    
    
    @Test
    public void testDesencriptar() throws FormatoInvalidoExcepcion {
        System.out.println("desencriptar");
        String cadena = "or3/asq1234567890123";
        String cadenaEncriptada = Encriptador.encriptar(cadena);
        String cadenaDesencriptada = Encriptador.desencriptar(cadenaEncriptada);
        
        assertEquals(cadena, cadenaDesencriptada);
        System.out.println(cadena + " -> " + cadenaEncriptada + " -> " + cadenaDesencriptada);
    }//fin testDesencriptar()
    
    
    @Test
    public void testEsPasswordCorrecto() throws FormatoInvalidoExcepcion {
        System.out.println("esPasswordCorrecto");
        String cadena = "or3/asq1234567890123";
        String cadenaEncriptada = Encriptador.encriptar(cadena);
        String cadenaDesencriptada = Encriptador.desencriptar(cadenaEncriptada);
        
        assertTrue(Encriptador.esPasswordCorrecto(cadena, cadenaEncriptada));
        System.out.println(cadena + " -> " + cadenaDesencriptada);
    }//fin testEsPasswordCorrecto()
    
    
    @Test
    public void testEsPasswordIncorrecto() throws FormatoInvalidoExcepcion {
        System.out.println("esPasswordIncorrecto");
        String cadena = "or3/asq1234567890123";
        String cadenaEncriptada = Encriptador.encriptar("Cadena distinta");
        String cadenaDesencriptada = Encriptador.desencriptar(cadenaEncriptada);
        
        assertFalse(Encriptador.esPasswordCorrecto(cadena, cadenaEncriptada));
        System.out.println(cadena + " -> " + cadenaDesencriptada);
    }//fin testEsPasswordCorrecto()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase EncriptadorTest