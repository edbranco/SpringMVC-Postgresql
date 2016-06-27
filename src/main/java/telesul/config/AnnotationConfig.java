/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author lmohan This is the starting point of the application,all the initial
 * configuration files need to be configured here
 */
public class AnnotationConfig extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebConfiguration.class,EndpointConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/", "/**"};
    }

    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        super.registerContextLoaderListener(servletContext);

        /*
         * CXF looks for a file /WEB-INF/cxf-servlet.xml by default or whatever
         * file path is set against the key "config-location". In this project,
         * we set the servlet mapping programmatically.
         */
    }

    @Override
    protected String getServletName() {
        return "dispatcher";
    }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        super.onStartup(servletContext);

        registerJAXWSServlet(servletContext);
    }

    private void registerJAXWSServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic jaxWsServlet = servletContext.addServlet(
                "cxf", new CXFServlet());
        jaxWsServlet.addMapping("/ws/*");
    }
}
