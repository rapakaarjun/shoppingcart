package org.o7planning.cart.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer
{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext=	new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.register(ApplicationContextConfig.class);
		
		Dynamic dispatcher=	servletContext.addServlet("springDispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		ContextLoaderListener contextLoaderListener=	new ContextLoaderListener(annotationConfigWebApplicationContext);
		servletContext.addListener(contextLoaderListener);
		
		FilterRegistration.Dynamic filter=servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.addMappingForUrlPatterns(null, true, "/*");
		filter.setInitParameter("encoding", "UTF-8");
		filter.setInitParameter("forceEncoding", "true");
	}

}
