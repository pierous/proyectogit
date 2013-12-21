/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.informeservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.pruebaservicio.PruebaServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.utiles.baseDatos.baseDatos;
import es.udc.proyectogit.utiles.configuracion.Configuracion;
import java.util.Calendar;
import static org.junit.Assert.assertFalse;
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
public class InformeServicioImplTest {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    @Autowired
    private InformeServicio informeServicio;
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
    
    /*------------------------------------------------------------------------*/
    
    
    /*----------------------------Constructores-------------------------------*/
    
    public InformeServicioImplTest() {
    }//fin InformeServicioImplTest()
    
    
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
        claveMedico3 = administradorServicio.agregarMedico("47365742g", "Alberto", "Torres", "Delgado", null, null, null);
        claveMedico4 = administradorServicio.agregarMedico("47365741g", "Alberto", "Torres", "Delgado", null, null, null);
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
        
        assertTrue(informeServicio.esEditable(claveInforme1, claveMedico1));
        assertFalse(informeServicio.esEditable(claveInforme1, claveMedico2));
        assertFalse(informeServicio.esEditable(claveInforme1, claveMedico3));
        assertFalse(informeServicio.esEditable(claveInforme1, claveMedico4));
        
        assertFalse(informeServicio.esEditable(claveInforme2, claveMedico1));
        assertTrue(informeServicio.esEditable(claveInforme2, claveMedico2));
        assertFalse(informeServicio.esEditable(claveInforme2, claveMedico3));
        assertFalse(informeServicio.esEditable(claveInforme2, claveMedico4));
    }//fin testEsEditable()
    
    
    @Test
    public void testEsEliminable() throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion, Throwable {
        System.out.println("esEliminable==========================");
        
        assertFalse(medicoServicio.listar().isEmpty());
        assertFalse(informeServicio.listar().isEmpty());
        assertTrue(informeServicio.existe(claveInforme1));
        assertTrue(informeServicio.esEliminable(claveInforme1, claveMedico1));
        assertFalse(informeServicio.esEliminable(claveInforme1, claveMedico2));
        informeServicio.finalizarInforme(claveInforme1);
        assertFalse(informeServicio.esEliminable(claveInforme1, claveMedico1));
        assertFalse(informeServicio.esEliminable(claveInforme1, claveMedico2));
    }//fin testEsEliminable()
    
    
    @Test
    public void testEsFinalizable() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esFinalizable==========================");
        
        informeServicio.buscar(claveInforme1).setDiagnostico("Algo");
        assertTrue(informeServicio.esFinalizable(claveInforme1, claveMedico1));
        assertFalse(informeServicio.esFinalizable(claveInforme1, claveMedico2));
        Long clavePrueba = medicoServicio.agregarPrueba(claveInforme1, clavePlantilla, "Mirar esto");
        assertFalse(informeServicio.esFinalizable(claveInforme1, claveMedico1));
        medicoServicio.asignarPruebaMedico(clavePrueba, claveMedico1);
        pruebaServicio.finalizarPrueba(clavePrueba);
        assertTrue(informeServicio.esFinalizable(claveInforme1, claveMedico1));
    }//fin testEsFinalizable()
    
    
    @Test
    public void testEsFinalizado() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esFinalizado==========================");
        
        assertFalse(informeServicio.esFinalizado(claveInforme1));
        informeServicio.finalizarInforme(claveInforme1);
        assertTrue(informeServicio.esFinalizado(claveInforme1));
    }//fin testEsFinalizado()
    
    
    @Test
    public void testFiltro() {
        System.out.println("filtro==========================");
        
        Calendar desde = Calendar.getInstance(), hasta = Calendar.getInstance();
        desde.add(Calendar.DAY_OF_MONTH, -1);
        hasta.add(Calendar.DAY_OF_MONTH, 1);
        assertFalse(informeServicio.filtro(1, informeServicio.listar(), desde, hasta).isEmpty());
        desde.add(Calendar.DAY_OF_MONTH, -4);
        hasta.add(Calendar.DAY_OF_MONTH, -4);
        System.out.println(desde.getTime());
        System.out.println(hasta.getTime());
        assertTrue(informeServicio.filtro(1, informeServicio.listar(), desde, hasta).isEmpty());
    }//fin testFiltro()
    
    
    @Test
    public void testFinalizarInforme() throws Exception {
        System.out.println("finalizarInforme==========================");
        
        assertFalse(informeServicio.esFinalizado(claveInforme1));
        informeServicio.finalizarInforme(claveInforme1);
        assertTrue(informeServicio.esFinalizado(claveInforme1));
    }//fin testFinalizarInforme()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase InformeServicioImplTest