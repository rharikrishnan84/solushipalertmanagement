
package com.meritconinc.shiplinx.ws.proxy.shippingrequest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.meritconinc.shiplinx.ws.proxy.shippingrequest package. 
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

    private final static QName _Security_QNAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/ShippingRequest", "Security");
    private final static QName _ShippingRequest_QNAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/ShippingRequest", "ShippingRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.meritconinc.shiplinx.ws.proxy.shippingrequest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShippingRequestWSType }
     * 
     */
    public ShippingRequestWSType createShippingRequestWSType() {
        return new ShippingRequestWSType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityWSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.proxy.ws.shiplinx.meritconinc.com/ShippingRequest", name = "Security")
    public JAXBElement<SecurityWSType> createSecurity(SecurityWSType value) {
        return new JAXBElement<SecurityWSType>(_Security_QNAME, SecurityWSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingRequestWSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.proxy.ws.shiplinx.meritconinc.com/ShippingRequest", name = "ShippingRequest")
    public JAXBElement<ShippingRequestWSType> createShippingRequest(ShippingRequestWSType value) {
        return new JAXBElement<ShippingRequestWSType>(_ShippingRequest_QNAME, ShippingRequestWSType.class, null, value);
    }

}
