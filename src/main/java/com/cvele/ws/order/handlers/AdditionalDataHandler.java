package com.cvele.ws.order.handlers;

import java.io.ByteArrayOutputStream;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nikola Cvetkovic
 */

public class AdditionalDataHandler implements SOAPHandler<SOAPMessageContext>{
    
    private static final Logger LOG = LogManager.getLogger(AdditionalDataHandler.class);

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean b = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        try{
            if(!b){
                LOG.info("Request");
                SOAPMessage message = context.getMessage();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                message.writeTo(out);
                String m = new String(out.toByteArray());
                LOG.info(m);
                SOAPBody body = message.getSOAPBody();
                NodeList child = body.getElementsByTagName("id");
                
                LOG.info(child.item(0).getNodeValue());
            } else {
                LOG.info("Response");
                SOAPMessage message = context.getMessage();
                if(message.getSOAPHeader() == null) message.getSOAPPart().getEnvelope().addHeader();
                SOAPHeader header = message.getSOAPHeader();
                header.addHeaderElement(new QName("http://www.cvele.com/Order", "additinionalData", "nikola"));
                Element e = header.getFirstChild().getOwnerDocument().createElement("Broj_telefona");
                e.setTextContent("0658151911");
                header.getFirstChild().appendChild(e);
                message.saveChanges();
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
        
    }

}
