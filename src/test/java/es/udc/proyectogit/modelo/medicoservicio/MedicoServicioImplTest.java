/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.medicoservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.informeservicio.InformeServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.pacienteservicio.PacienteServicio;
import es.udc.proyectogit.modelo.pruebaservicio.PruebaServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.*;
import es.udc.proyectogit.utiles.baseDatos.baseDatos;
import es.udc.proyectogit.utiles.configuracion.Configuracion;
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
public class MedicoServicioImplTest {
    
    
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
    
    public MedicoServicioImplTest() {
    }//fin MedicoServicioImplTest()
    
    
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
    public void testLoginMedico() throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion {
        System.out.println("loginMedico==========================");
        
        assertEquals(medicoServicio.buscar(claveMedico1), medicoServicio.loginMedico("47365744g", "47365744g", false));
    }//fin testLoginMedico()
    
    
    @Test (expected = InstanciaNoEncontradaExcepcion.class)
    public void testLoginMedicoInexistente() throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion {
        System.out.println("loginMedicoInexistente==========================");
        
        medicoServicio.loginMedico("47365744h", "47365744h", false);
    }//fin testLoginMedicoInexistente()
    
    
    @Test (expected = PasswordIncorrectoExcepcion.class)
    public void testLoginMedicoIncorrecto() throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion {
        System.out.println("loginMedicoIncorrecto==========================");
        
        medicoServicio.loginMedico("47365744g", "47365744h", false);
    }//fin testLoginMedicoIncorrecto()
    
    
    @Test
    public void testModificarMedico() throws InstanciaNoEncontradaExcepcion, VerificacionPasswordExcepcion, FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("modificarMedico==========================");
        
        Medico medico = medicoServicio.buscar(claveMedico1);
        assertEquals("Alberto", medico.getNombre());
        medicoServicio.modificarMedico(medico.getClave(), medico.getDni().toString(), "David", medico.getApellido1(), medico.getApellido2(), medico.getFechaNacimiento(), medico.getTelefono(), medico.getDireccion(), medico.getNombreLogin(), null, null);
        assertEquals("David", medicoServicio.buscar(claveMedico1).getNombre());
    }//fin testModificarMedico()
    
    
    @Test
    public void testAsignarPacienteMedico() throws InstanciaNoEncontradaExcepcion {
        System.out.println("asignarPacienteMedico==========================");
        
        assertNull(pacienteServicio.buscar(clavePaciente).getMedico());
        medicoServicio.asignarPacienteMedico(claveMedico1, clavePaciente);
        assertEquals(claveMedico1, pacienteServicio.buscar(clavePaciente).getMedico().getClave());
    }//fin testAsignarPacienteMedico()
    
    
    @Test
    public void testAsignarPruebaMedico() throws InstanciaNoEncontradaExcepcion {
        System.out.println("asignarPruebaMedico==========================");
        
        assertNull(pruebaServicio.buscar(clavePrueba).getMedico());
        medicoServicio.asignarPruebaMedico(clavePrueba, claveMedico1);
        assertEquals(claveMedico1, pruebaServicio.buscar(clavePrueba).getMedico().getClave());
    }//fin testAsignarPruebaMedico()
    
    
    @Test
    public void testCrearInforme() throws InstanciaNoEncontradaExcepcion {
        System.out.println("crearInforme==========================");
        
        assertTrue(informeServicio.listar().size() == 2);
        medicoServicio.crearInforme(clavePaciente, claveMedico3, "Observaciones previas", null);
        assertTrue(informeServicio.listar().size() == 3);
    }//fin testCrearInforme()
    
    
    @Test
    public void testEliminarInforme() throws InstanciaNoEncontradaExcepcion {
        System.out.println("eliminarInforme==========================");
        
        assertTrue(informeServicio.listar().size() == 2);
        Long clave = medicoServicio.crearInforme(clavePaciente, claveMedico3, "Observaciones previas", null);
        assertTrue(informeServicio.listar().size() == 3);
        assertTrue(informeServicio.existe(clave));
        medicoServicio.eliminarInforme(clave);
        assertTrue(informeServicio.listar().size() == 2);
        assertFalse(informeServicio.existe(clave));
    }//fin testEliminarInforme()
    
    
    @Test
    public void testAgregarPrueba() throws InstanciaNoEncontradaExcepcion {
        System.out.println("agregarPrueba==========================");
        
        assertTrue(pruebaServicio.listar().size() == 1);
        medicoServicio.agregarPrueba(claveInforme1, clavePlantilla, "Prueba a realizar");
        assertTrue(pruebaServicio.listar().size() == 2);
    }//fin testAgregarPrueba()
    
    
    @Test
    public void testDesvincularPrueba() throws InstanciaNoEncontradaExcepcion {
        System.out.println("desvincularPrueba==========================");
        
        assertNull(pruebaServicio.buscar(clavePrueba).getMedico());
        medicoServicio.asignarPruebaMedico(clavePrueba, claveMedico1);
        assertEquals(claveMedico1, pruebaServicio.buscar(clavePrueba).getMedico().getClave());
        medicoServicio.desvincularPrueba(clavePrueba);
        assertNull(pruebaServicio.buscar(clavePrueba).getMedico());
    }//fin testDesvincularPrueba()
    
    
    @Test
    public void testEliminarPrueba() throws InstanciaNoEncontradaExcepcion {
        System.out.println("eliminarPrueba==========================");
        
        assertTrue(pruebaServicio.listar().size() == 1);
        medicoServicio.eliminarPrueba(clavePrueba);
        assertTrue(pruebaServicio.listar().isEmpty());
    }//fin testEliminarPrueba()
    
    
    @Test
    public void testEsAdministrador() throws InstanciaNoEncontradaExcepcion {
        System.out.println("esAdministrador==========================");
        
        //assertTrue(medicoServicio.esAdministrador(claveMedico1));
        administradorServicio.asignarSupervisorDepartamento(claveDepartamento1, claveMedico1);
        Medico medico = medicoServicio.buscar(claveMedico1);
        if (medico.getDepSupervisado() != null) System.out.println("Es supervisor");
        else System.out.println("No es supervisor");
        if (departamentoServicio.buscar(claveDepartamento1).getSupervisor() != null) System.out.println("Tiene supervisor");
        else System.out.println("No tiene supervisor");
        assertFalse(medicoServicio.esAdministrador(claveMedico2));
    }//fin testEsAdministrador()
    
    
    @Test
    public void testFiltro() {
        System.out.println("filtro==========================");
        
        String campos[] = {"47365744A", null, null, null, null};
        assertTrue(medicoServicio.filtro(medicoServicio.listar(), campos).isEmpty());
        campos[0] = "47365744g";
        assertTrue(medicoServicio.filtro(medicoServicio.listar(), campos).size() == 1);
        campos[0] = "4736574";
        assertTrue(medicoServicio.filtro(medicoServicio.listar(), campos).size() == 2);
        campos[0] = "473657";
        assertTrue(medicoServicio.filtro(medicoServicio.listar(), campos).size() == 3);
        campos[0] = "47365";
        assertTrue(medicoServicio.filtro(medicoServicio.listar(), campos).size() == 4);
    }//fin testFiltro()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase MedicoServicioImplTest