
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ValidateCityPostalCodeZipRequest
 * 
 * <p>Java class for ValidateCityPostalCodeZipRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateCityPostalCodeZipRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="Addresses" type="{http://purolator.com/pws/datatypes/v1}ArrayOfShortAddress"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateCityPostalCodeZipRequestContainer", propOrder = {
    "addresses"
})
public class ValidateCityPostalCodeZipRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "Addresses", required = true, nillable = true)
    protected ArrayOfShortAddress addresses;

    /**
     * Gets the value of the addresses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfShortAddress }
     *     
     */
    public ArrayOfShortAddress getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfShortAddress }
     *     
     */
    public void setAddresses(ArrayOfShortAddress value) {
        this.addresses = value;
    }

}
