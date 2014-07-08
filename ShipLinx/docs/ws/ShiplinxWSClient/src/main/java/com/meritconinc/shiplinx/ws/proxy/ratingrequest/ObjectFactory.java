
package com.meritconinc.shiplinx.ws.proxy.ratingrequest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.meritconinc.shiplinx.ws.proxy.ratingrequest package. 
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

    private final static QName _RatingRequest_QNAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/RatingRequest", "RatingRequest");
    private final static QName _Security_QNAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/RatingRequest", "Security");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.meritconinc.shiplinx.ws.proxy.ratingrequest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RatingRequestWSType }
     * 
     */
    public RatingRequestWSType createRatingRequestWSType() {
        return new RatingRequestWSType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RatingRequestWSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.proxy.ws.shiplinx.meritconinc.com/RatingRequest", name = "RatingRequest")
    public JAXBElement<RatingRequestWSType> createRatingRequest(RatingRequestWSType value) {
        return new JAXBElement<RatingRequestWSType>(_RatingRequest_QNAME, RatingRequestWSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityWSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.proxy.ws.shiplinx.meritconinc.com/RatingRequest", name = "Security")
    public JAXBElement<SecurityWSType> createSecurity(SecurityWSType value) {
        return new JAXBElement<SecurityWSType>(_Security_QNAME, SecurityWSType.class, null, value);
    }

}
