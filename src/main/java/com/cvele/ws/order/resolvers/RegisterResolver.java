package com.cvele.ws.order.resolvers;

import com.cvele.ws.order.handlers.AdditionalDataHandler;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Nikola Cvetkovic
 */

//na serverskoj strani nije potrebna ova klasa, nju menja xml fajl
public class RegisterResolver implements HandlerResolver{

    private static final Logger LOG = LogManager.getLogger(RegisterResolver.class);

    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> handlers = new ArrayList<>();
        handlers.add(new AdditionalDataHandler());
        LOG.info("Handler registrovan" + AdditionalDataHandler.class.getName());
        return handlers;
    }

}
