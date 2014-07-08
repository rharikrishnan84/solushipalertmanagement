
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ReturnShipmentInformation
 * 
 * <p>Java class for ReturnShipmentInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnShipmentInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumberOfReturnShipments" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ReturnShipment" type="{http://purolator.com/pws/datatypes/v1}ReturnShipment"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnShipmentInformation", propOrder = {
    "numberOfReturnShipments",
    "returnShipment"
})
public class ReturnShipmentInformation {

    @XmlElement(name = "NumberOfReturnShipments")
    protected int numberOfReturnShipments;
    @XmlElement(name = "ReturnShipment", required = true, nillable = true)
    protected ReturnShipment returnShipment;

    /**
     * Gets the value of the numberOfReturnShipments property.
     * 
     */
    public int getNumberOfReturnShipments() {
        return numberOfReturnShipments;
    }

    /**
     * Sets the value of the numberOfReturnShipments property.
     * 
     */
    public void setNumberOfReturnShipments(int value) {
        this.numberOfReturnShipments = value;
    }

    /**
     * Gets the value of the returnShipment property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnShipment }
     *     
     */
    public ReturnShipment getReturnShipment() {
        return returnShipment;
    }

    /**
     * Sets the value of the returnShipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnShipment }
     *     
     */
    public void setReturnShipment(ReturnShipment value) {
        this.returnShipment = value;
    }

}
