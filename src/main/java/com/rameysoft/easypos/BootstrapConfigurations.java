package com.rameysoft.easypos;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.server.SpringVaadinServlet;

@Configuration
@EnableVaadin
public class BootstrapConfigurations {
	
	@WebListener
    public static class EasyPosContextLoaderListener extends ContextLoaderListener {
    }
	
	@WebServlet(urlPatterns = "/*", name = "EasyPosServlet", asyncSupported = true)
    public static class EasyPosServlet extends SpringVaadinServlet {
		private static final long serialVersionUID = 7273195451574386218L;
    }

}
