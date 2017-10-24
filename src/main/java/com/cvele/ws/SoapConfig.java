package com.cvele.ws;

import com.cvele.ws.order.OrderServiceImpl;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Nikola Cvetkovic
 */

@Configuration
public class SoapConfig {
    
    @Autowired
    private Bus bus;
    
    @Autowired
    private OrderServiceImpl orderService;
    
    @Bean
    public Endpoint endpoint(){
        Endpoint endpoint = new EndpointImpl(bus, orderService);
        endpoint.publish("/soap");
        
        return endpoint;
    }
}
