/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.pacienteservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.paciente.Paciente;
import es.udc.proyectogit.modelo.utiles.excepciones.FormatoInvalidoExcepcion;
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
public class PacienteServicioImplTest {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private MedicoServicio medicoServicio;
    @Autowired
    private DepartamentoServicio departamentoServicio;
    @Autowired
    private PacienteServicio pacienteServicio;
    
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
    
    public PacienteServicioImplTest() {
    }//fin PacienteServicioImplTest()
    
    
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
    public void testModificarPaciente() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("modificarPaciente==========================");
        
        Paciente paciente = pacienteServicio.buscar(clavePaciente);
        assertEquals("Alberto", paciente.getNombre());
        pacienteServicio.modificarPaciente(paciente.getClave(), paciente.getDni().toString(), "David", paciente.getApellido1(), paciente.getApellido2(), paciente.getFechaNacimiento(), paciente.getTelefono(), paciente.getDireccion());
        assertEquals("David", pacienteServicio.buscar(clavePaciente).getNombre());
    }//fin testModificarPaciente()
    
    
    @Test
    public void testFiltro() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("filtro==========================");
        
        administradorServicio.agregarPaciente("47365123d", "Alberto", "Torres", "Delgado", null, null, null);
        administradorServicio.agregarPaciente("47365743h", "Alberto", "Torres", "Delgado", null, null, null);
        administradorServicio.agregarPaciente("47365753h", "Alberto", "Torres", "Delgado", null, null, null);
        String campos[] = {"47365744A", null, null, null, null};
        assertTrue(pacienteServicio.filtro(pacienteServicio.listar(), campos).isEmpty());
        campos[0] = "47365744g";
        assertTrue(pacienteServicio.filtro(pacienteServicio.listar(), campos).size() == 1);
        campos[0] = "4736574";
        assertTrue(pacienteServicio.filtro(pacienteServicio.listar(), campos).size() == 2);
        campos[0] = "473657";
        assertTrue(pacienteServicio.filtro(pacienteServicio.listar(), campos).size() == 3);
        campos[0] = "47365";
        assertTrue(pacienteServicio.filtro(pacienteServicio.listar(), campos).size() == 4);
    }//fin testFiltro()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase PacienteServicioImplTest
