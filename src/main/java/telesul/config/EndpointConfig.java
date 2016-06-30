/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.config;

import telesul.service.published.ZapWSPrimaryImpl;
import telesul.service.published.ZapWSSecondaryImpl;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import telesul.service.CRMService;
import telesul.service.ZapService;

/**
 *
 * @author lmohan
 */
@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class EndpointConfig {

    @Autowired
    Bus cxfBus;
    @Autowired
    ZapService zapService;
    @Autowired
    CRMService cRMService;

    @Bean
    public Endpoint wscall() {
        EndpointImpl endpoint = new EndpointImpl(cxfBus, new ZapWSPrimaryImpl(zapService, cRMService));
        endpoint.setAddress("/wscall");
        endpoint.setDataBinding(new JAXBDataBinding());
        endpoint.publish();
        return endpoint;
    }

    @Bean
    public Endpoint wscallbackup() {
        EndpointImpl endpoint = new EndpointImpl(cxfBus, new ZapWSSecondaryImpl(zapService, cRMService));
        endpoint.setAddress("/wscallbackup");
        endpoint.setDataBinding(new JAXBDataBinding());
        endpoint.publish();
        return endpoint;
    }
}
