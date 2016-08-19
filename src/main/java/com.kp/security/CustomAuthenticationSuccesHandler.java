package com.kp.security;//package com.kp.security;
//
//import com.kp.model.user.ActiveUserStore;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.WebAttributes;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Collection;
//
///**
// * Created by diman on 11.08.16.
// */
//
//@Component("authenticationSuccessHandler")
//public class CustomAuthenticationSuccesHandler implements AuthenticationSuccessHandler {
//	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationSuccesHandler.class);
//	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//	@Autowired
//	ActiveUserStore activeUserStore;
//
//	@Override
//	public void onAuthenticationSuccess(final HttpServletRequest request,
//																			final HttpServletResponse response,
//																			final Authentication authentication) throws IOException, ServletException {
////		 param 'false' need for check session. if session doesnt exist request return null
//		final HttpSession session = request.getSession(false);
////		if (session != null) {
////			session.setMaxInactiveInterval(30 * 60);
////			LoggedUser user = new LoggedUser(authentication.getName(),activeUserStore);
////			session.setAttribute("user", user);
////		}
//		handle(request,response,authentication);
//		clearAuthenticationAttributes(request);
//	}
//
//	protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
//		final String targetUrl = determineTargetUrl(authentication);
//
//		if (response.isCommitted()) {
//			LOGGER.debug("Response has already been committed. Unable to redirect to " + targetUrl);
//			return;
//		}
//
//		redirectStrategy.sendRedirect(request, response, targetUrl);
//	}
//
//	protected String determineTargetUrl(final Authentication authentication) {
//		boolean isUser = false;
//		boolean isAdmin = false;
//		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		for (final GrantedAuthority grantedAuthority : authorities) {
//			if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
//				isUser = true;
//			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
//				isAdmin = true;
//				isUser = false;
//				break;
//			}
//		}
//		if (isUser) {
//			LOGGER.debug("GEt name auth user when he goes to homepage " + authentication.getName());
//			LOGGER.debug("is user");
//			return "/homepage";/*.html?user=" + authentication.getName();*/
//		} else if (isAdmin) {
//			LOGGER.debug("is admin");
//			return "/admin";
//		} else {
//			throw new IllegalStateException();
//		}
//	}
//
//	protected void clearAuthenticationAttributes(final HttpServletRequest request) {
//		final HttpSession session = request.getSession(false);
//		if (session == null) {
//			return;
//		}
//		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//	}
//
//	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
//		this.redirectStrategy = redirectStrategy;
//	}
//
//	protected RedirectStrategy getRedirectStrategy() {
//		return redirectStrategy;
//	}
//}
