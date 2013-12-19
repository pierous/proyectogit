package es.udc.proyectogit.web.services;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthenticationPolicy {
	AuthenticationPolicyType value() default AuthenticationPolicyType.ALL_USERS;
}
