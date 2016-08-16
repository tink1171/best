package com.kp.configuration;//package com.kp.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.social.UserIdSource;
//import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.config.annotation.SocialConfiguration;
//import org.springframework.social.config.annotation.SocialConfigurer;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;
//
///**
// * Created by diman on 12.08.16.
// */
//@Configuration
//@EnableSocial
//public class SocialConfig implements SocialConfigurer {
//	@Override
//	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
//		connectionFactoryConfigurer.addConnectionFactory(new FacebookConnectionFactory(
//						environment.getProperty("spring.social.facebook.appId"),
//						environment.getProperty("spring.social.facebook.appSecret")
//		));
//	}
//
//	@Override
//	public UserIdSource getUserIdSource() {
//		return null;
//	}
//
//	@Override
//	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//		return null;
//	}
//}
