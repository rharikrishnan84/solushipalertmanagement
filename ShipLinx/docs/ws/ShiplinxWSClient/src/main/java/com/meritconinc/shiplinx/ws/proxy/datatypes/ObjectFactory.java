
package com.meritconinc.shiplinx.ws.proxy.datatypes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.meritconinc.shiplinx.ws.proxy.datatypes package. 
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

    private final static QName _RatingErrors_QNAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/datatypes", "RatingErrors");
    private final static QName _ShippingErrors_QNAME = new QName("http://www.proxy.ws.shiplinx.meritconinc.com/datatypes", "ShippingErrors");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.meritconinc.shiplinx.ws.proxy.datatypes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SecurityWSType }
     * 
     */
    public SecurityWSType createSecurityWSType() {
        return new SecurityWSType();
    }

    /**
     * Create an instance of {@link ErrorsWSType }
     * 
     */
    public ErrorsWSType createErrorsWSType() {
        return new ErrorsWSType();
    }

    /**
     * Create an instance of {@link AddressWSType }
     * 
     */
    public AddressWSType createAddressWSType() {
        return new AddressWSType();
    }

    /**
     * Create an instance of {@link RatingsWSType }
     * 
     */
    public RatingsWSType createRatingsWSType() {
        return new RatingsWSType();
    }

    /**
     * Create an instance of {@link ChargesWSType }
     * 
     */
    public ChargesWSType createChargesWSType() {
        return new ChargesWSType();
    }

    /**
     * Create an instance of {@link PickupWSType }
     * 
     */
    public PickupWSType createPickupWSType() {
        return new PickupWSType();
    }

    /**
     * Create an instance of {@link ServiceWSType }
     * 
     */
    public ServiceWSType createServiceWSType() {
        return new ServiceWSType();
    }

    /**
     * Create an instance of {@link ErrorWSType }
     * 
     */
    public ErrorWSType createErrorWSType() {
        return new ErrorWSType();
    }

    /**
     * Create an instance of {@link CODWSType }
     * 
     */
    public CODWSType createCODWSType() {
        return new CODWSType();
    }

    /**
     * Create an instance of {@link RatingWSType }
     * 
     */
    public RatingWSType createRatingWSType() {
        return new RatingWSType();
    }

    /**
     * Create an instance of {@link PODWSType }
     * 
     */
    public PODWSType createPODWSType() {
        return new PODWSType();
    }

    /**
     * Create an instance of {@link PackageWSType }
     * 
     */
    public PackageWSType createPackageWSType() {
        return new PackageWSType();
    }

    /**
     * Create an instance of {@link OrderWSType }
     * 
     */
    public OrderWSType createOrderWSType() {
        return new OrderWSType();
    }

    /**
     * Create an instance of {@link AccessorialWSType }
     * 
     */
    public AccessorialWSType createAccessorialWSType() {
        return new AccessorialWSType();
    }

    /**
     * Create an instance of {@link ChargeWSType }
     * 
     */
    public ChargeWSType createChargeWSType() {
        return new ChargeWSType();
    }

    /**
     * Create an instance of {@link PackagesWSType }
     * 
     */
    public PackagesWSType createPackagesWSType() {
        return new PackagesWSType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorsWSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.proxy.ws.shiplinx.meritconinc.com/datatypes", name = "RatingErrors")
    public JAXBElement<ErrorsWSType> createRatingErrors(ErrorsWSType value) {
        return new JAXBElement<ErrorsWSType>(_RatingErrors_QNAME, ErrorsWSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorsWSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.proxy.ws.shiplinx.meritconinc.com/datatypes", name = "ShippingErrors")
    public JAXBElement<ErrorsWSType> createShippingErrors(ErrorsWSType value) {
        return new JAXBElement<ErrorsWSType>(_ShippingErrors_QNAME, ErrorsWSType.class, null, value);
    }

}
