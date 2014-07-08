
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeliveryConfirmationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeliveryConfirmationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DCISType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeliveryConfirmationType", propOrder = {
    "dcisType"
})
public class DeliveryConfirmationType {

    @XmlElement(name = "DCISType", required = true)
    protected String dcisType;

    /**
     * Gets the value of the dcisType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDCISType() {
        return dcisType;
    }

    /**
     * Sets the value of the dcisType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDCISType(String value) {
        this.dcisType = value;
    }

}
