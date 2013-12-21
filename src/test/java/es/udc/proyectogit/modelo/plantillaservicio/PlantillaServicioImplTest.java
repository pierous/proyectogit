/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.plantillaservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.plantilla.Plantilla;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaDuplicadaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.utiles.baseDatos.baseDatos;
import es.udc.proyectogit.utiles.configuracion.Configuracion;
import java.util.ArrayList;
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
public class PlantillaServicioImplTest {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private MedicoServicio medicoServicio;
    @Autowired
    private DepartamentoServicio departamentoServicio;
    @Autowired
    private PlantillaServicio plantillaServicio;
    
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
    
    public PlantillaServicioImplTest() {
    }//fin PlantillaServicioImplTest()
    
    
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
    public void testFiltro() {
        System.out.println("filtro==========================");
        
        assertTrue(plantillaServicio.filtro(plantillaServicio.listar(), "Prueba1", claveDepartamento1).size() == 1);
        assertTrue(plantillaServicio.filtro(plantillaServicio.listar(), "Prue", claveDepartamento1).size() == 1);
        assertTrue(plantillaServicio.filtro(plantillaServicio.listar(), "Prueba2", claveDepartamento1).isEmpty());
    }//fin testFiltro()
    
    
    @Test
    public void testModificarPlantilla() throws InstanciaNoEncontradaExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("modificarPlantilla==========================");
        
        Plantilla plantilla = plantillaServicio.buscar(clavePlantilla);
        assertEquals("Prueba1", plantilla.getNombre());
        plantilla.setNombre("Prueba2");
        plantillaServicio.modificarPlantilla(plantilla.getClave(), "Prueba2", plantilla.getDepartamento(), new ArrayList(plantilla.getParametros()));
        assertEquals("Prueba2", plantillaServicio.buscar(clavePlantilla).getNombre());
    }//fin testModificarPlantilla()
    
/*------------------------------------------------------------------------*/
    
    
}//fin Clase PlantillaServicioImplTest
