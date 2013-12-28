package es.udc.proyectogit.web.services;

import es.udc.proyectogit.web.util.AdminSession;
import es.udc.proyectogit.web.util.MedSession;
import java.io.IOException;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.*;

public class AuthenticationDispatcher implements Dispatcher {
	
	private final static String LOGIN_PAGE_ADMIN = "../Login/1";
        private final static String LOGIN_PAGE_MED = "../Login/0";
	private final static String INIT_PAGE = "Index";
        
//        @Inject
//        private PersistentLocale persistentLocale;

	private ApplicationStateManager applicationStateManager;
	private ComponentClassResolver componentClassResolver;
	private ComponentSource componentSource;

	public AuthenticationDispatcher(ApplicationStateManager applicationStateManager,
		ComponentClassResolver componentClassResolver,
		ComponentSource componentSource) {
		
		this.applicationStateManager = applicationStateManager;
		this.componentClassResolver = componentClassResolver;
		this.componentSource = componentSource;
	}

	public boolean dispatch(Request request, Response response) throws IOException {
            
		Component page = componentSource.getPage(getPageName(request));
		AuthenticationPolicy policy = page.getClass().getAnnotation(AuthenticationPolicy.class);

		if (policy == null) {
			return false;
		}
		
		AuthenticationPolicyType policyType = policy.value();
		boolean adminAuthenticated = applicationStateManager.exists(AdminSession.class);
                boolean medAuthenticated = applicationStateManager.exists(MedSession.class);
		
		switch (policyType) {
                    
               case AUTHENTICATED_USERS:
			
			if (!medAuthenticated && !adminAuthenticated) {
				response.sendRedirect(request.getContextPath() +
					LOGIN_PAGE_MED);
				return true; // Leave the chain.
			}
			break;
		
		case AUTHENTICATED_ADMINS:
			
			if (!adminAuthenticated) {
				response.sendRedirect(request.getContextPath() +
					LOGIN_PAGE_ADMIN);
				return true; // Leave the chain.
			}
			break;
                    
                case AUTHENTICATED_MEDS:
			
			if (!medAuthenticated) {
				response.sendRedirect(request.getContextPath() +
					LOGIN_PAGE_MED);
				return true; // Leave the chain.
			}
			break;
			
		case NON_AUTHENTICATED_USERS:
			
			if (adminAuthenticated) {
				response.sendRedirect(request.getContextPath() + 
					INIT_PAGE);
                                System.err.println("NON_AUTHENTICATED_USERS");
				return true; // Leave the chain.
			}
			break;
			
		default: 
			break;
		
		}
		
		return false;
		
	}
	
	private String getPageName(Request request) {
		
		String path = request.getPath();

		/* 
		 * Remove leading slash.
		 * 
		 * TAPESTRY-1343: Sometimes path is the empty string (it should always 
		 * be at least a slash, but Tomcat may return the empty string for a 
		 * root context request).
		 */
		path = path.length() == 0 ? path : path.substring(1);
		
		/* Ignore trailing slashes in the path. */
		while (path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}

		/* Remove activation context (if exists). */
		int nextSlash;
		
		do {
			
			if (componentClassResolver.isPageName(path)) {
				return path;
			}
			
			nextSlash = path.lastIndexOf('/', path.length() - 1);
			
			if (nextSlash > 0) {
				path = path.substring(0, nextSlash);
			}
			
		} while (nextSlash > 0);
		
		return "";
		
	}

}
