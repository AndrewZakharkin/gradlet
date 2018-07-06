package com.zakharkin.servlets;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    public MyWebInitializer(){
        logger.warn("ZAK: initializer instantiated");
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        logger.warn("ZAK: getRootConfigClasses");
        return new Class<?>[] { RootConfig.class };
    }
    /*@Override protected Class<?> [] getRootConfigClasses() {
        return new Class<?>[]{};
    }*/

    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.warn("ZAK: getServletConfigClasses");
        return new Class<?>[] { RestConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        logger.warn("ZAK: getServletMappings");
        return new String[] { "/" };
    }

    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        logger.warn("ZAK: DISPATCHER");
        return new DispatcherServlet(servletAppContext);
    }
}
