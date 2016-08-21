package com.kp.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;
import java.util.Locale;

/**
 * Created by diman on 10.08.16.
 */

@Configuration
@ComponentScan(basePackages = { "com.kp" })
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{
	public MvcConfig() {
		super();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/").setViewName("forward:/login");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/homepage").setViewName("homepage");
//		registry.addViewController("/logout").setViewName("logout");
//		registry.addViewController("/admin").setViewName("admin");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Bean
	public LocaleResolver localeResolver() {
		final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		return cookieLocaleResolver;
	}

	 @Bean
	 public MessageSource messageSource() {
	 final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	 messageSource.setBasename("classpath:messages");
	 messageSource.setUseCodeAsDefaultMessage(true);
	 messageSource.setDefaultEncoding("UTF-8");
	 messageSource.setCacheSeconds(0);
	 return messageSource;
	 }


}
