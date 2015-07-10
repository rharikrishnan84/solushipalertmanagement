
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerPONumbers" type="{http://www.eshipplus.com/}ArrayOfWSCustomerPONumber" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customerPONumbers"
})
@XmlRootElement(name = "AddPONumbers")
public class AddPONumbers {

    protected ArrayOfWSCustomerPONumber customerPONumbers;

    /**
     * Gets the value of the customerPONumbers property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSCustomerPONumber }
     *     
     */
    public ArrayOfWSCustomerPONumber getCustomerPONumbers() {
        return customerPONumbers;
    }

    /**
     * Sets the value of the customerPONumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSCustomerPONumber }
     *     
     */
    public void setCustomerPONumbers(ArrayOfWSCustomerPONumber value) {
        this.customerPONumbers = value;
    }

}
