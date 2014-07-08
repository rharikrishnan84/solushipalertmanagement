
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * This class is used to return information to a WCF client when validation fails on a service parameter.
 * 
 * <p>Java class for ValidationFault complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Details" type="{http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF}ArrayOfValidationDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationFault", namespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", propOrder = {
    "details"
})
public class ValidationFault {

    @XmlElementRef(name = "Details", namespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", type = JAXBElement.class)
    protected JAXBElement<ArrayOfValidationDetail> details;

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfValidationDetail }{@code >}
     *     
     */
    public JAXBElement<ArrayOfValidationDetail> getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfValidationDetail }{@code >}
     *     
     */
    public void setDetails(JAXBElement<ArrayOfValidationDetail> value) {
        this.details = value;
    }

}
