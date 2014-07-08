
package com.meritconinc.shiplinx.ws.proxy.shippingresponse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.meritconinc.shiplinx.ws.proxy.shippingresponse package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ShippingResponse_QNAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/ShippingResponse", "ShippingResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.meritconinc.shiplinx.ws.proxy.shippingresponse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShippingResponseWSType }
     * 
     */
    public ShippingResponseWSType createShippingResponseWSType() {
        return new ShippingResponseWSType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingResponseWSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.proxy.ws.shiplinx.meritconinc.com/ShippingResponse", name = "ShippingResponse")
    public JAXBElement<ShippingResponseWSType> createShippingResponse(ShippingResponseWSType value) {
        return new JAXBElement<ShippingResponseWSType>(_ShippingResponse_QNAME, ShippingResponseWSType.class, null, value);
    }

}
