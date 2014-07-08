
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * ValidateCityPostalCodeZipResponse
 * 
 * <p>Java class for ValidateCityPostalCodeZipResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateCityPostalCodeZipResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="SuggestedAddresses" type="{http://purolator.com/pws/datatypes/v1}ArrayOfSuggestedAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateCityPostalCodeZipResponseContainer", propOrder = {
    "suggestedAddresses"
})
public class ValidateCityPostalCodeZipResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "SuggestedAddresses", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSuggestedAddress> suggestedAddresses;

    /**
     * Gets the value of the suggestedAddresses property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSuggestedAddress }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSuggestedAddress> getSuggestedAddresses() {
        return suggestedAddresses;
    }

    /**
     * Sets the value of the suggestedAddresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSuggestedAddress }{@code >}
     *     
     */
    public void setSuggestedAddresses(JAXBElement<ArrayOfSuggestedAddress> value) {
        this.suggestedAddresses = value;
    }

}
