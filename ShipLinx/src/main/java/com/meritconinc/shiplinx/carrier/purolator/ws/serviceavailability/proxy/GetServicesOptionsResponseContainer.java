
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * GetServicesOptionsResponse
 * 
 * <p>Java class for GetServicesOptionsResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetServicesOptionsResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="Services" type="{http://purolator.com/pws/datatypes/v1}ArrayOfService" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetServicesOptionsResponseContainer", propOrder = {
    "services"
})
public class GetServicesOptionsResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "Services", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfService> services;

    /**
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfService }{@code >}
     *     
     */
    public JAXBElement<ArrayOfService> getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfService }{@code >}
     *     
     */
    public void setServices(JAXBElement<ArrayOfService> value) {
        this.services = value;
    }

}
