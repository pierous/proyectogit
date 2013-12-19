package es.udc.proyectogit.web.services;

import es.udc.proyectogit.modelo.administrador.Administrador;
import es.udc.proyectogit.modelo.administradorservicio.AdministradorServicio;
import es.udc.proyectogit.modelo.medico.Medico;
import es.udc.proyectogit.modelo.medicoservicio.MedicoServicio;
import es.udc.proyectogit.modelo.utiles.excepciones.InstanciaNoEncontradaExcepcion;
import es.udc.proyectogit.modelo.utiles.excepciones.PasswordIncorrectoExcepcion;
import es.udc.proyectogit.web.util.AdminSession;
import es.udc.proyectogit.web.util.CookiesManager;
import es.udc.proyectogit.web.util.MedSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tapestry5.services.*;

public class SessionDispatcher implements Dispatcher {

	private ApplicationStateManager applicationStateManager;
	private Cookies cookies;
	private AdministradorServicio administradorServicio;
        private MedicoServicio medicoServicio;

	public SessionDispatcher(ApplicationStateManager applicationStateManager,
			Cookies cookies, AdministradorServicio administradorServicio) {
		this.applicationStateManager = applicationStateManager;
		this.cookies = cookies;
		this.administradorServicio = administradorServicio;
	}
        
        public SessionDispatcher(ApplicationStateManager applicationStateManager,
			Cookies cookies, MedicoServicio medicoServicio) {
		this.applicationStateManager = applicationStateManager;
		this.cookies = cookies;
		this.medicoServicio = medicoServicio;
	}

	public boolean dispatch(Request request, Response response)
			throws IOException {
            if (!applicationStateManager.exists(AdminSession.class) && !applicationStateManager.exists(MedSession.class)) {
            
                String loginName = CookiesManager.getLoginName(cookies);
                if (loginName == null) {
                        return false;
                }

                String encryptedPassword = CookiesManager.getEncryptedPassword(cookies);
                if (encryptedPassword == null) {
                        return false;
                }


            try {
                if (applicationStateManager.exists(AdminSession.class)) {
                    Administrador administrador = administradorServicio.loginAdministrador(loginName, encryptedPassword, true);
                    AdminSession adminSession = new AdminSession();
                    adminSession.setUserId(administrador.getClave());
                    adminSession.setNombre(administrador.getNombreCompleto());
                    applicationStateManager.set(AdminSession.class, adminSession);
                }//fin if (applicationStateManager.exists(AdminSession.class))
                
                if (applicationStateManager.exists(MedSession.class)) {
                    Medico medico = medicoServicio.loginMedico(loginName, encryptedPassword, true);
                    MedSession medSession = new MedSession();
                    medSession.setUserId(medico.getClave());
                    medSession.setNombre(medico.getNombreCompleto());
                    applicationStateManager.set(MedSession.class, medSession);
                }//fin if (applicationStateManager.exists(MedSession.class))
            } catch (InstanciaNoEncontradaExcepcion ex) {
                Logger.getLogger(SessionDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PasswordIncorrectoExcepcion ex) {
                Logger.getLogger(SessionDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }


		}

		return false;

	}

}
