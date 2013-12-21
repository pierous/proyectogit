/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.departamentoservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.utiles.baseDatos.baseDatos;
import es.udc.proyectogit.utiles.configuracion.Configuracion;
import static org.junit.Assert.assertEquals;
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
public class DepartamentoServicioImplTest {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    @Autowired
    private DepartamentoServicio departamentoServicio;
    @Autowired
    private AdministradorServicio administradorServicio;
    
    /*------------------------------------------------------------------------*/
    
    
    /*----------------------------Constructores-------------------------------*/
    
    public DepartamentoServicioImplTest() {
    }//fin DepartamentoServicioImplTest()
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }//fin setUpClass()
    
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }//fin tearDownClass()
    
    
    @Before
    public void setUp() throws Throwable {
        baseDatos.vaciarBD();
    }//fin setUp()
    
    
    @After
    public void tearDown() throws Throwable {
        baseDatos.vaciarBD();
    }//fin tearDown()

    /*------------------------------------------------------------------------*/
    
    
    /*-------------------------------Tests------------------------------------*/
    
    @Test
    public void testFiltro() throws InstanciaDuplicadaExcepcion {
        System.out.println("filtro==========================");
        
        administradorServicio.agregarDepartamento("Compras");
        administradorServicio.agregarDepartamento("Comprass");
        administradorServicio.agregarDepartamento("Comprasss");
        administradorServicio.agregarDepartamento("Comprassss");
        
        String nombre = "Comprasssss";
        assertTrue(departamentoServicio.filtro(departamentoServicio.listar(), nombre).isEmpty());
        nombre = "Comprassss";
        assertTrue(departamentoServicio.filtro(departamentoServicio.listar(), nombre).size() == 1);
        nombre = "Comprasss";
        assertTrue(departamentoServicio.filtro(departamentoServicio.listar(), nombre).size() == 2);
        nombre = "Comprass";
        assertTrue(departamentoServicio.filtro(departamentoServicio.listar(), nombre).size() == 3);
        nombre = "Compras";
        assertTrue(departamentoServicio.filtro(departamentoServicio.listar(), nombre).size() == 4);
    }//fin testFiltro()
    
    
    @Test
    public void testModificarDepartamento() throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("modificarDepartamento==========================");
        
        Long clave = administradorServicio.agregarDepartamento("Compras");
        Departamento departamento = departamentoServicio.buscar(clave);
        assertEquals("Compras", departamento.getNombre());
        departamentoServicio.modificarDepartamento(departamento.getClave(), "Ventas", departamento.getSupervisor());
        assertEquals("Ventas", departamentoServicio.buscar(clave).getNombre());
    }//fin testModificarDepartamento()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase DepartamentoServicioImplTest
