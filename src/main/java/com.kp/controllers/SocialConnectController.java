package com.kp.controllers;//package com.kp.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.connect.ConnectionRepository;
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.social.facebook.api.PagedList;
//import org.springframework.social.facebook.api.Post;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * Created by diman on 12.08.16.
// */
//public class SocialConnectController {
//
//	@Autowired
//	private Facebook facebook;
//
//	@Autowired
//	private ConnectionRepository connectionRepository;
//
//	@RequestMapping(method= RequestMethod.GET)
//	public String helloFacebook(Model model) {
//		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//			return "redirect:/connect/facebook";
//		}
//
//		model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
//		PagedList<Post> feed = facebook.feedOperations().getFeed();
//		model.addAttribute("feed", feed);
//		return "hello";
//	}
//}
