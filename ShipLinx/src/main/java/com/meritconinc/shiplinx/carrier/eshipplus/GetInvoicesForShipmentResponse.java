
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GetInvoicesForShipmentResult" type="{http://www.eshipplus.com/}ArrayOfWSInvoiceSummary" minOccurs="0"/>
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
    "getInvoicesForShipmentResult"
})
@XmlRootElement(name = "GetInvoicesForShipmentResponse")
public class GetInvoicesForShipmentResponse {

    @XmlElement(name = "GetInvoicesForShipmentResult")
    protected ArrayOfWSInvoiceSummary getInvoicesForShipmentResult;

    /**
     * Gets the value of the getInvoicesForShipmentResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSInvoiceSummary }
     *     
     */
    public ArrayOfWSInvoiceSummary getGetInvoicesForShipmentResult() {
        return getInvoicesForShipmentResult;
    }

    /**
     * Sets the value of the getInvoicesForShipmentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSInvoiceSummary }
     *     
     */
    public void setGetInvoicesForShipmentResult(ArrayOfWSInvoiceSummary value) {
        this.getInvoicesForShipmentResult = value;
    }

}
