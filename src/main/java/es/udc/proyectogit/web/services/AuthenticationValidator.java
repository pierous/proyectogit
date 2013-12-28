package es.udc.proyectogit.web.services;

import es.udc.proyectogit.web.util.AdminSession;
import es.udc.proyectogit.web.util.MedSession;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.MetaDataLocator;

public class AuthenticationValidator {

	private final static String LOGIN_PAGE_ADMIN = "Login";
        private final static String LOGIN_PAGE_MED = "Login";
	private final static String INIT_PAGE = "Index";

	public static final String PAGE_AUTHENTICATION_TYPE = "proyectogit.page-authentication-type";
	public static final String EVENT_HANDLER_AUTHENTICATION_TYPE = "proyectogit.event-handler-authentication-type";

	public static String checkForPage(String pageName,
			ApplicationStateManager applicationStateManager,
			ComponentSource componentSource, MetaDataLocator locator) {

		String redirectPage = null;
		Component page = componentSource.getPage(pageName);
		try {
			String policyAsString = locator.findMeta(PAGE_AUTHENTICATION_TYPE,
					page.getComponentResources(), String.class);

			AuthenticationPolicyType policy = AuthenticationPolicyType
					.valueOf(policyAsString);
			redirectPage = check(policy, applicationStateManager);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}
		return redirectPage;

	}

	public static String checkForComponentEvent(String pageName,
			String componentId, String eventId, String eventType,
			ApplicationStateManager applicationStateManager,
			ComponentSource componentSource, MetaDataLocator locator) {

		String redirectPage = null;
		String authenticationPolicyMeta = EVENT_HANDLER_AUTHENTICATION_TYPE
				+ "-" + eventId + "-" + eventType;
		authenticationPolicyMeta = authenticationPolicyMeta.toLowerCase();

		Component component = null;
		if (componentId == null) {
			component = componentSource.getPage(pageName);
		} else {
			component = componentSource.getComponent(pageName + ":"
					+ componentId);
		}
		try {
			String policyAsString = locator.findMeta(authenticationPolicyMeta,
					component.getComponentResources(), String.class);
			AuthenticationPolicyType policy = AuthenticationPolicyType
					.valueOf(policyAsString);
			redirectPage = AuthenticationValidator.check(policy,
					applicationStateManager);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}
		return redirectPage;

	}

	public static String check(AuthenticationPolicy policy,
			ApplicationStateManager applicationStateManager) {

		if (policy != null) {
			return check(policy.value(), applicationStateManager);
		} else {
			return null;
		}

	}

	public static String check(AuthenticationPolicyType policyType,
			ApplicationStateManager applicationStateManager) {
		String redirectPage = null;

		boolean adminAuthenticated = applicationStateManager.exists(AdminSession.class);
                boolean medAuthenticated = applicationStateManager.exists(MedSession.class);

		switch (policyType) {

		case AUTHENTICATED_USERS:

			if (!medAuthenticated && !adminAuthenticated) {
				redirectPage = LOGIN_PAGE_MED;
			}
			break;
                    
                case AUTHENTICATED_ADMINS:

			if (!adminAuthenticated) {
				redirectPage = LOGIN_PAGE_ADMIN;
			}
			break;
                    
                case AUTHENTICATED_MEDS:

			if (!medAuthenticated) {
				redirectPage = LOGIN_PAGE_MED;
			}
			break;

		case NON_AUTHENTICATED_USERS:

			if (adminAuthenticated || medAuthenticated) {
				redirectPage = INIT_PAGE;
			}
			break;

		default:
			break;

		}

		return redirectPage;
	}

}