/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.pruebaservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.prueba.Prueba;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.utiles.baseDatos.baseDatos;
import es.udc.proyectogit.utiles.configuracion.Configuracion;
import java.util.Calendar;
import static org.junit.Assert.*;
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
public class PruebaServicioImplTest {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private MedicoServicio medicoServicio;
    @Autowired
    private DepartamentoServicio departamentoServicio;
    @Autowired
    private PruebaServicio pruebaServicio;
    
    Long claveDepartamento1;
    Long claveDepartamento2;
    Long claveMedico1;
    Long claveMedico2;
    Long claveMedico3;
    Long claveMedico4;
    Long clavePaciente;
    Long claveInforme1;
    Long claveInforme2;
    Long clavePlantilla;
    Long clavePrueba;
    
    /*------------------------------------------------------------------------*/
    
    
    /*----------------------------Constructores-------------------------------*/
    
    public PruebaServicioImplTest() {
    }//fin PruebaServicioImplTest()
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }//fin setUpClass()
    
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }//fin tearDownClass()
    
    
    @Before
    public void setUp() throws Throwable {
        baseDatos.vaciarBD();
        claveDepartamento1 = administradorServicio.agregarDepartamento("Compras");
        claveDepartamento2 = administradorServicio.agregarDepartamento("Ventas");
        claveMedico1 = administradorServicio.agregarMedico("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        claveMedico2 = administradorServicio.agregarMedico("47365743g", "Alberto", "Torres", "Delgado", null, null, null);
        claveMedico3 = administradorServicio.agregarMedico("47365732g", "Alberto", "Torres", "Delgado", null, null, null);
        claveMedico4 = administradorServicio.agregarMedico("47365121g", "Alberto", "Torres", "Delgado", null, null, null);
        administradorServicio.asignarMedicoDepartamento(claveDepartamento1, claveMedico1);
        administradorServicio.asignarMedicoDepartamento(claveDepartamento1, claveMedico2);
        administradorServicio.asignarMedicoDepartamento(claveDepartamento2, claveMedico3);
        administradorServicio.asignarMedicoDepartamento(claveDepartamento2, claveMedico4);
        administradorServicio.asignarSupervisorDepartamento(claveDepartamento1, claveMedico1);
        administradorServicio.asignarSupervisorDepartamento(claveDepartamento2, claveMedico3);
        clavePaciente = administradorServicio.agregarPaciente("47365744g", "Alberto", "Torres", "Delgado", null, null, null);
        claveInforme1 = medicoServicio.crearInforme(clavePaciente, claveMedico1, "Observaciones previas", null);
        claveInforme2 = medicoServicio.crearInforme(clavePaciente, claveMedico2, "Observaciones previas", null);
        clavePlantilla = administradorServicio.agregarPlantilla("Prueba1", departamentoServicio.buscar(claveDepartamento1), null);
        clavePrueba = medicoServicio.agregarPrueba(claveInforme1, clavePlantilla, "Prueba a realizar");
    }//fin setUp()
    
    
    @After
    public void tearDown() throws Throwable {
        baseDatos.vaciarBD();
    }//fin tearDown()

    /*------------------------------------------------------------------------*/
    
    
    /*-------------------------------Tests------------------------------------*/
    
    @Test
    public void testEsEditable() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esEditable==========================");
        
        assertFalse(pruebaServicio.esEditable(clavePrueba, claveMedico1));
        medicoServicio.asignarPruebaMedico(clavePrueba, claveMedico1);
        assertTrue(pruebaServicio.esEditable(clavePrueba, claveMedico1));
        pruebaServicio.finalizarPrueba(clavePrueba);
        assertTrue(pruebaServicio.esEditable(clavePrueba, claveMedico1));
        assertFalse(pruebaServicio.esEditable(clavePrueba, claveMedico2));
    }//fin testEsEditable()
    
    
    @Test
    public void testEsEliminable() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esEliminable==========================");
        
        assertTrue(pruebaServicio.esEliminable(clavePrueba, claveMedico1));
        assertFalse(pruebaServicio.esEliminable(clavePrueba, claveMedico2));
        pruebaServicio.iniciarPrueba(clavePrueba);
        assertFalse(pruebaServicio.esEliminable(clavePrueba, claveMedico1));
    }//fin testEsEliminable()
    
    
    @Test
    public void testEsFinalizable() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esFinalizable==========================");
        
        medicoServicio.asignarPruebaMedico(clavePrueba, claveMedico1);
        assertTrue(pruebaServicio.esFinalizable(clavePrueba, claveMedico1));
        assertFalse(pruebaServicio.esFinalizable(clavePrueba, claveMedico2));
    }//fin testEsFinalizable()
    
    
    @Test
    public void testEsAgregable() {
        System.out.println("esAgregable==========================");
        
        assertTrue(pruebaServicio.esAgregable(clavePrueba, claveMedico1));
        assertTrue(pruebaServicio.esAgregable(clavePrueba, claveMedico2));
        assertFalse(pruebaServicio.esAgregable(clavePrueba, claveMedico3));
        assertFalse(pruebaServicio.esAgregable(clavePrueba, claveMedico4));
    }//fin testEsAgregable()
    
    
    @Test
    public void testEsDesvinculable() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esDesvinculable==========================");
        
        assertFalse(pruebaServicio.esDesvinculable(clavePrueba, claveMedico2));
        medicoServicio.asignarPruebaMedico(clavePrueba, claveMedico2);
        assertTrue(pruebaServicio.esDesvinculable(clavePrueba, claveMedico2));
        assertTrue(pruebaServicio.esDesvinculable(clavePrueba, claveMedico1));
        assertFalse(pruebaServicio.esDesvinculable(clavePrueba, claveMedico3));
    }//fin testEsDesvinculable()
    
    
    @Test
    public void testEsIniciado() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esIniciado==========================");
        
        medicoServicio.asignarPruebaMedico(clavePrueba, claveMedico2);
        assertFalse(pruebaServicio.esIniciado(clavePrueba));
        pruebaServicio.iniciarPrueba(clavePrueba);
        assertTrue(pruebaServicio.esIniciado(clavePrueba));
    }//fin testEsIniciado()
    
    
    @Test
    public void testEsFinalizado() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esFinalizado==========================");
        
        assertFalse(pruebaServicio.esFinalizado(clavePrueba));
        pruebaServicio.iniciarPrueba(clavePrueba);
        assertFalse(pruebaServicio.esFinalizado(clavePrueba));
        pruebaServicio.finalizarPrueba(clavePrueba);
        assertTrue(pruebaServicio.esFinalizado(clavePrueba));
    }//fin testEsFinalizado()
    
    
    @Test
    public void testFiltro() throws InstanciaNoEncontradaExcepcion {
        System.out.println("filtro==========================");
        
        pruebaServicio.iniciarPrueba(clavePrueba);
        Calendar desde = Calendar.getInstance(), hasta = Calendar.getInstance();
        desde.add(Calendar.DAY_OF_MONTH, -1);
        hasta.add(Calendar.DAY_OF_MONTH, 1);
        assertFalse(pruebaServicio.filtro(1, pruebaServicio.listar(), desde, hasta).isEmpty());
        desde.add(Calendar.DAY_OF_MONTH, -4);
        hasta.add(Calendar.DAY_OF_MONTH, -4);
        assertTrue(pruebaServicio.filtro(1, pruebaServicio.listar(), desde, hasta).isEmpty());
    }//fin testFiltro()
    
    
    @Test
    public void testModificarPrueba() throws InstanciaNoEncontradaExcepcion {
        System.out.println("modificarPrueba==========================");
        
        Prueba prueba = pruebaServicio.buscar(clavePrueba);
        assertNull(prueba.getObservaciones());
        prueba.setObservaciones("Hola que tal?");
        pruebaServicio.modificarPrueba(prueba);
        assertEquals("Hola que tal?", pruebaServicio.buscar(clavePrueba).getObservaciones());
    }//fin testModificarPrueba()
    
    
    @Test
    public void testIniciarPrueba() throws InstanciaNoEncontradaExcepcion {
        System.out.println("iniciarPrueba==========================");
        
        medicoServicio.asignarPruebaMedico(clavePrueba, claveMedico2);
        assertFalse(pruebaServicio.esIniciado(clavePrueba));
        pruebaServicio.iniciarPrueba(clavePrueba);
        assertTrue(pruebaServicio.esIniciado(clavePrueba));
    }//fin testIniciarPrueba()
    
    
    @Test
    public void testFinalizarPrueba() throws InstanciaNoEncontradaExcepcion {
        System.out.println("finalizarPrueba==========================");
        
        assertFalse(pruebaServicio.esFinalizado(clavePrueba));
        pruebaServicio.iniciarPrueba(clavePrueba);
        assertFalse(pruebaServicio.esFinalizado(clavePrueba));
        pruebaServicio.finalizarPrueba(clavePrueba);
        assertTrue(pruebaServicio.esFinalizado(clavePrueba));
    }//fin testFinalizarPrueba()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase PruebaServicioImplTest
