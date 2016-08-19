package com.kp.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by diman on 12.08.16.
 */
@Controller
public class CustomConnectController extends ConnectController {

	@Inject
	public CustomConnectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	@Override
	protected String connectedView(String providerId){
		return "/homepage";
	}

	@Override
	protected RedirectView connectionStatusRedirect(String providerId, NativeWebRequest request) {
//		HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
//		String path = connectionStatusUrlPath + providerId + getPathExtension(servletRequest);
//		if (prependServletPath(servletRequest)) {
//			path = servletRequest.getServletPath() + path;
//		}
		return new RedirectView("/homepage");
	}

//	@Override
//	protected RedirectView connectionStatusRedirect(String providerId, NativeWebRequest request) {
//		HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
////		String path = connectionStatusUrlPath + providerId + getPathExtension(servletRequest);
////		if (prependServletPath(servletRequest)) {
////			path = servletRequest.getServletPath() + path;
////		}
//		return new RedirectView("/homepage", true);
//	}


}
