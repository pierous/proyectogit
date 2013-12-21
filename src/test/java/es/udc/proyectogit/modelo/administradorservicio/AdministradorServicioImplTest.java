/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.administradorservicio;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.administrador.Administrador;
import es.udc.proyectogit.modelo.departamento.Departamento;
import es.udc.proyectogit.modelo.departamentoservicio.DepartamentoServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.pacienteservicio.PacienteServicio;
import es.udc.proyectogit.modelo.parametroservicio.ParametroServicio;
import es.udc.proyectogit.modelo.plantillaservicio.PlantillaServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.*;
import es.udc.proyectogit.modelo.utiles.password.Encriptador;
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
public class AdministradorServicioImplTest {
    
    
    /*----------------------------Atributos-----------------------------------*/
    
    private AdministradorServicio administradorServicio;
    @Autowired
    private DepartamentoServicio departamentoServicio;
    @Autowired
    private MedicoServicio medicoServicio;
    @Autowired
    private PacienteServicio pacienteServicio;
    @Autowired
    private ParametroServicio parametroServicio;
    @Autowired
    private PlantillaServicio plantillaServicio;
    
    /*------------------------------------------------------------------------*/
    
    
    /*----------------------------Constructores-------------------------------*/
    
    public AdministradorServicioImplTest() {
    }//fin AdministradorServicioImplTest()
    
    
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
    
    
    /*-----------------------------Gets y Sets--------------------------------*/
    
    @Autowired
    public void setAdministradorServicio(AdministradorServicio administradorServicio) {
        this.administradorServicio = administradorServicio;
    }//fin setAdministradorServicio(AdministradorServicio administradorServicio)
    
    /*------------------------------------------------------------------------*/
    
    
    /*-------------------------------Tests------------------------------------*/
    
    @Test
    public void testAgregarDepartamento() throws InstanciaNoEncontradaExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("agregarDepartamento===============================");
        
        Long clave = administradorServicio.agregarDepartamento("Compras");
        assertEquals("Compras", departamentoServicio.buscar(clave).getNombre());
        System.out.println("Se ha añadido el Departamento: " + departamentoServicio.buscar(clave).getNombre());
    }//fin testAgregarDepartamento()
    
    
    @Test (expected = InstanciaDuplicadaExcepcion.class)
    public void testAgregarDepartamentoDuplicado() throws InstanciaNoEncontradaExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("agregarDepartamentoDuplicado===========================");
        System.out.println("    *Pasa el test si salta la excepcion InstanciaDuplicadaExcepcion");
        
        administradorServicio.agregarDepartamento("Compras");
        administradorServicio.agregarDepartamento("Compras");
    }//fin testAgregarDepartamentoDuplicado()
    
    
    @Test (expected = InstanciaNoEncontradaExcepcion.class)
    public void testEliminarDepartamentoInexistente() throws InstanciaNoEncontradaExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("eliminarDepartamentoInexistente===============================");
        
        Long clave = administradorServicio.agregarDepartamento("Compras");
        assertTrue(departamentoServicio.existe(clave));
        administradorServicio.eliminarDepartamento(departamentoServicio.buscar(clave).getClave());
        assertFalse(departamentoServicio.existe(clave));
        administradorServicio.eliminarDepartamento(departamentoServicio.buscar(clave).getClave());
    }//fin testEliminarDepartamentoInexistente()
    
    
    @Test
    public void testEliminarDepartamento() throws InstanciaNoEncontradaExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("eliminarDepartamento===========================");
        
        Long clave = administradorServicio.agregarDepartamento("Compras");
        assertTrue(departamentoServicio.existe(clave));
        administradorServicio.eliminarDepartamento(departamentoServicio.buscar(clave).getClave());
        assertFalse(departamentoServicio.existe(clave));
    }//fin testEliminarDepartamento()
    
    
    @Test
    public void testAgregarMedico() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("agregarMedico=============================");
        
        Long clave = administradorServicio.agregarMedico("47365744g", "Alberto", "Torres", "Delgado", null, null, null);
        assertEquals("47365744G", medicoServicio.buscar(clave).getDni().toString());
    }//fin testAgregarMedico()
    
    
    @Test (expected = InstanciaDuplicadaExcepcion.class)
    public void testAgregarMedicoDuplicado() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("agregarMedicoDuplicado=======================");
        
        administradorServicio.agregarMedico("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        administradorServicio.agregarMedico("47365744g", "Alberto", "Torres", "Delgado", null, null, null);
    }//fin testAgregarMedicoDuplicado()
    
    
    @Test
    public void testResetearLoginMedico() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion, VerificacionPasswordExcepcion {
        System.out.println("resetearLoginMedico=======================");
        
        Long clave = administradorServicio.agregarMedico("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        Medico medico = medicoServicio.buscar(clave);
        medicoServicio.modificarMedico(medico.getClave(), medico.getDni().toString(), medico.getNombre(), medico.getApellido1(), medico.getApellido2(), medico.getFechaNacimiento(), medico.getTelefono(), medico.getDireccion(), "AAA", null, null);
        assertEquals("AAA", medicoServicio.buscar(clave).getNombreLogin());
        administradorServicio.resetearLoginMedico(clave);
        assertEquals("47365744g", medicoServicio.buscar(clave).getNombreLogin());
    }//fin testResetearLoginMedico()
    
    
    @Test
    public void testResetearPasswordMedico() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion, VerificacionPasswordExcepcion {
        System.out.println("resetearPasswordMedico=======================");
        
        Long clave = administradorServicio.agregarMedico("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        Medico medico = medicoServicio.buscar(clave);
        medico.setPasswordEncriptado(Encriptador.encriptar("AAAAA"));
        medicoServicio.actualizar(medico);
        assertTrue(Encriptador.esPasswordCorrecto("AAAAA", medicoServicio.buscar(clave).getPasswordEncriptado()));
        administradorServicio.resetearPasswordMedico(clave);
        assertTrue(Encriptador.esPasswordCorrecto("47365744g", medicoServicio.buscar(clave).getPasswordEncriptado()));
    }//fin testResetearPasswordMedico()
    
    
    @Test (expected = InstanciaNoEncontradaExcepcion.class)
    public void testEliminarMedicoInexistente() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("eliminarMedicoInexistente==========================");
        
        administradorServicio.eliminarMedico(Long.parseLong("1"));
    }//fin testEliminarMedicoInexistente()
    
    
    @Test
    public void testEliminarMedico() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("eliminarMedico=============================");
        
        Long clave = administradorServicio.agregarMedico("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        assertTrue(medicoServicio.existe(clave));
        administradorServicio.eliminarMedico(medicoServicio.buscar(clave).getClave());
        assertFalse(medicoServicio.existe(clave));
    }//fin testEliminarMedico()
    
    
    @Test
    public void testAsignarMedicoDepartamento() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("asignarMedicoDepartamento=============================");
        
        Long claveMedico = administradorServicio.agregarMedico("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        assertTrue(medicoServicio.existe(claveMedico));
        Long claveDepartamento = administradorServicio.agregarDepartamento("Compras");
        assertTrue(departamentoServicio.existe(claveDepartamento));
        assertEquals(null, medicoServicio.buscar(claveMedico).getDepartamento());
        administradorServicio.asignarMedicoDepartamento(claveDepartamento, claveMedico);
        assertEquals(claveDepartamento, medicoServicio.buscar(claveMedico).getDepartamento().getClave());
    }//fin testAsignarMedicoDepartamento()
    
    
    @Test
    public void testAsignarSupervisorDepartamento() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("asignarSupervisorDepartamento=============================");
        
        Long claveMedico = administradorServicio.agregarMedico("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        assertTrue(medicoServicio.existe(claveMedico));
        Long claveDepartamento = administradorServicio.agregarDepartamento("Compras");
        assertTrue(departamentoServicio.existe(claveDepartamento));
        administradorServicio.asignarMedicoDepartamento(claveDepartamento, claveMedico);
        assertEquals(claveDepartamento, medicoServicio.buscar(claveMedico).getDepartamento().getClave());
        assertEquals(null, departamentoServicio.buscar(claveDepartamento).getSupervisor());
        administradorServicio.asignarSupervisorDepartamento(claveDepartamento, claveMedico);
        assertEquals(claveMedico, departamentoServicio.buscar(claveDepartamento).getSupervisor().getClave());
    }//fin testAsignarSupervisorDepartamento()
    
    
    @Test
    public void testAgregarPaciente() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("agregarPaciente=============================");
        
        Long clave = administradorServicio.agregarPaciente("47365744g", "Alberto", "Torres", "Delgado", null, null, null);
        assertEquals("47365744G", pacienteServicio.buscar(clave).getDni().toString());
    }//fin testAgregarPaciente()
    
    
    @Test (expected = InstanciaDuplicadaExcepcion.class)
    public void testAgregarPacienteDuplicado() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("agregarPacienteDuplicado=======================");
        
        administradorServicio.agregarPaciente("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        administradorServicio.agregarPaciente("47365744g", "Alberto", "Torres", "Delgado", null, null, null);
    }//fin testAgregarPacienteDuplicado()
    
    
    @Test (expected = InstanciaNoEncontradaExcepcion.class)
    public void testEliminarPacienteInexistente() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("eliminarPacienteInexistente==========================");
        
        administradorServicio.eliminarPaciente(Long.parseLong("1"));
    }//fin testEliminarPacienteInexistente()
    
    
    @Test
    public void testEliminarPaciente() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion, InstanciaDuplicadaExcepcion {
        System.out.println("eliminarPaciente=============================");
        
        Long clave = administradorServicio.agregarPaciente("47365744G", "Alberto", "Torres", "Delgado", null, null, null);
        assertTrue(pacienteServicio.existe(clave));
        administradorServicio.eliminarPaciente(pacienteServicio.buscar(clave).getClave());
        assertFalse(pacienteServicio.existe(clave));
    }//fin testEliminarMedico()
    
    
    @Test
    public void testRegistrarAdministrador() throws InstanciaNoEncontradaExcepcion, InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("registrarAdministrador===============================");
        
        Long clave = administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744G", "David", "Ulloa", "Sobrino");
        System.out.println("Se ha registrado: " + administradorServicio.buscar(clave));
        assertEquals("47365744G", administradorServicio.buscar(clave).getDni().toString());
    }//fin testRegistrarAdministrador()
    
    
    @Test (expected = InstanciaDuplicadaExcepcion.class)
    public void testRegistrarAdministradorDuplicado() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("registrarAdministradorDuplicado=======================================");
        System.out.println("    *Pasa el test si salta la excepcion InstanciaDuplicadaExcepcion");
        
        administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744g", "David", "Ulloa", "Sobrino");
        administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744G", "David", "Ulloa", "Sobrino");
    }//fin testRegistrarAdministradorDuplicado()
    
    
    @Test
    public void testLoginAdministrador() throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion, InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("loginAdministrador=================================");
        
        Long clave = administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744G", "David", "Ulloa", "Sobrino");
        assertEquals("47365744G", administradorServicio.buscar(clave).getDni().toString());
        Administrador administrador = administradorServicio.loginAdministrador("pierous", "581985davidus", false);
        assertEquals(administradorServicio.buscar(clave), administrador);
    }//fin testLoginAdministrador()
    
    
    @Test (expected = InstanciaNoEncontradaExcepcion.class)
    public void testLoginUsuarioInexistente() throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion {
        System.out.println("loginUsuarioInexistente=================================");
        System.out.println("    *Pasa el test si salta la excepcion InstanciaNoEncontradaExcepcion");
        
        administradorServicio.loginAdministrador("romoaldo", "12345678", false);
    }//fin testLoginUsuarioInexistente()
    
    
    @Test (expected = PasswordIncorrectoExcepcion.class)
    public void testLoginIncorrecto() throws InstanciaNoEncontradaExcepcion, PasswordIncorrectoExcepcion, InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("loginIncorrecto===========================================");
        System.out.println("    *Pasa el test si salta la excepcion PasswordIncorrectoExcepcion");
        
        administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744G", "David", "Ulloa", "Sobrino");
        administradorServicio.loginAdministrador("pierous", "581985d", false);
    }//fin testLoginIncorrecto()
    
    
    @Test
    public void testModificarAdministrador() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion, VerificacionPasswordExcepcion {
        System.out.println("modificarAdministrador===========================================");
        
        Long clave = administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744g", "David", "Ulloa", "Sobrino");
        Administrador administrador = administradorServicio.buscar(clave);
        assertEquals("David", administrador.getNombre());
        administradorServicio.modificarAdministrador(administrador.getClave(), administrador.getDni().toString(), "Manuel", administrador.getApellido1(), administrador.getApellido2(), administrador.getNombreLogin(), null, null);
        assertEquals("Manuel", administradorServicio.buscar(clave).getNombre());
    }//fin testModificarAdministrador()
    
    
    @Test
    public void testResetearLoginAdministrador() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("resetearLoginAdministrador===========================================");
        
        Long clave = administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744g", "David", "Ulloa", "Sobrino");
        Administrador administrador = administradorServicio.buscar(clave);
        administrador.setNombreLogin("pierous01");
        administradorServicio.actualizar(administrador);
        assertEquals("pierous01", administradorServicio.buscar(clave).getNombreLogin());
        administradorServicio.resetearLoginAdministrador(clave);
        assertEquals("47365744g", administradorServicio.buscar(clave).getNombreLogin());
    }//fin testResetearLoginAdministrador()
    
    
    @Test
    public void testResetearPasswordAdministrador() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("resetearPasswordAdministrador===========================================");
        
        Long clave = administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744g", "David", "Ulloa", "Sobrino");
        Administrador administrador = administradorServicio.buscar(clave);
        administrador.setPasswordEncriptado(Encriptador.encriptar("AAAAA"));
        administradorServicio.actualizar(administrador);
        assertTrue(Encriptador.esPasswordCorrecto("AAAAA", administradorServicio.buscar(clave).getPasswordEncriptado()));
        administradorServicio.resetearPasswordAdministrador(clave);
        assertTrue(Encriptador.esPasswordCorrecto("47365744g", administradorServicio.buscar(clave).getPasswordEncriptado()));
    }//fin testResetearPasswordAdministrador()
    
    
    @Test
    public void testEliminarAdministrador() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("eliminarAdministrador===========================================");
        
        Long clave = administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744g", "David", "Ulloa", "Sobrino");
        assertTrue(administradorServicio.existe(clave));
        administradorServicio.eliminarAdministrador(administradorServicio.buscar(clave).getClave());
        assertFalse(administradorServicio.existe(clave));
    }//fin testEliminarAdministrador()
    
    
    @Test (expected = InstanciaNoEncontradaExcepcion.class)
    public void testEliminarAdministradorInexistente() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("eliminarAdministradorInexistente==========================");
        
        administradorServicio.eliminarAdministrador(Long.parseLong("1"));
    }//fin testEliminarAdministradorInexistente()
    
    
    @Test
    public void testAgregarParametro() throws InstanciaDuplicadaExcepcion {
        System.out.println("agregarParametro==========================");
        
        assertTrue(parametroServicio.listar().isEmpty());
        Long clave = administradorServicio.agregarParametro("Peso", "Kilogramos", "kg.");
        assertTrue(parametroServicio.existe(clave));
    }//fin testAgregarParametro()
    
    
    @Test (expected = InstanciaDuplicadaExcepcion.class)
    public void testAgregarParametroDuplicado() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("agregarParametroDuplicado=======================");
        
        administradorServicio.agregarParametro("Peso", "Kilogramos", "kg.");
        administradorServicio.agregarParametro("Peso", "Kilogramos", "kg.");
    }//fin testAgregarParametroDuplicado()
    
    
    @Test
    public void testEliminarParametro() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("eliminarParametro===========================================");
        
        Long clave = administradorServicio.agregarParametro("Peso", "Kilogramos", "kg.");
        assertTrue(parametroServicio.existe(clave));
        administradorServicio.eliminarParametro(clave);
        assertFalse(administradorServicio.existe(clave));
    }//fin testEliminarParametro()
    
    
    @Test (expected = InstanciaNoEncontradaExcepcion.class)
    public void testEliminarParametroInexistente() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("eliminarParametroInexistente==========================");
        
        administradorServicio.eliminarParametro(Long.parseLong("1"));
    }//fin testEliminarParametroInexistente()
    
    
    @Test
    public void testAgregarPlantilla() throws InstanciaDuplicadaExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("agregarPlantilla==========================");
        
        Long claveDepartamento = administradorServicio.agregarDepartamento("Compras");
        Departamento departamento = departamentoServicio.buscar(claveDepartamento);
        assertTrue(plantillaServicio.listar().isEmpty());
        Long clave = administradorServicio.agregarPlantilla("Radiografía", departamento, null);
        assertTrue(plantillaServicio.existe(clave));
    }//fin testAgregarPlantilla()
    
    
    @Test (expected = InstanciaDuplicadaExcepcion.class)
    public void testAgregarPlantillaDuplicada() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("agregarPlantillaDuplicada=======================");
        
        Long claveDepartamento = administradorServicio.agregarDepartamento("Compras");
        Departamento departamento = departamentoServicio.buscar(claveDepartamento);
        administradorServicio.agregarPlantilla("Radiografía", departamento, null);
        administradorServicio.agregarPlantilla("Radiografía", departamento, null);
    }//fin testAgregarPlantillaDuplicada()
    
    
    @Test
    public void testEliminarPlantilla() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion, InstanciaNoEncontradaExcepcion {
        System.out.println("eliminarPlantilla===========================================");
        
        Long claveDepartamento = administradorServicio.agregarDepartamento("Compras");
        Departamento departamento = departamentoServicio.buscar(claveDepartamento);
        Long clave = administradorServicio.agregarPlantilla("Radiografía", departamento, null);
        assertTrue(plantillaServicio.existe(clave));
        administradorServicio.eliminarPlantilla(clave);
        assertFalse(plantillaServicio.existe(clave));
    }//fin testEliminarPlantilla()
    
    
    @Test (expected = InstanciaNoEncontradaExcepcion.class)
    public void testEliminarPlantillaInexistente() throws InstanciaNoEncontradaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("eliminarPlantillaInexistente==========================");
        
        administradorServicio.eliminarPlantilla(Long.parseLong("1"));
    }//fin testEliminarPlantillaInexistente()
    
    
    @Test
    public void testFiltro() throws InstanciaDuplicadaExcepcion, FormatoInvalidoExcepcion {
        System.out.println("filtro==========================");
        
        administradorServicio.registrarAdministrador("pierous", "581985davidus", "47365744G", "David", "Ulloa", "Sobrino");
        administradorServicio.registrarAdministrador("pieroas", "581985davidus", "47365742G", "David", "Ulloa", "Sobrino");
        administradorServicio.registrarAdministrador("pierouax", "581985davidus", "47365732G", "David", "Ulloa", "Sobrino");
        administradorServicio.registrarAdministrador("pieroxas", "581985davidus", "47365542G", "David", "Ulloa", "Sobrino");
        
        String campos[] = {"47365744A", null, null, null};
        assertTrue(administradorServicio.filtro(administradorServicio.listar(), campos).isEmpty());
        campos[0] = "47365744G";
        assertTrue(administradorServicio.filtro(administradorServicio.listar(), campos).size() == 1);
        campos[0] = "4736574";
        assertTrue(administradorServicio.filtro(administradorServicio.listar(), campos).size() == 2);
        campos[0] = "473657";
        assertTrue(administradorServicio.filtro(administradorServicio.listar(), campos).size() == 3);
        campos[0] = "47365";
        assertTrue(administradorServicio.filtro(administradorServicio.listar(), campos).size() == 4);
    }//fin testFiltro()
    
    /*------------------------------------------------------------------------*/
    
    
}