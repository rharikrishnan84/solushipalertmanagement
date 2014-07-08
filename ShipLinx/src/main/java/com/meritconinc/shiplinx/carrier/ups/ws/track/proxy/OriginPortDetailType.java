
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OriginPortDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OriginPortDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OriginPort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstimatedDeparture" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DateTimeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OriginPortDetailType", propOrder = {
    "originPort",
    "estimatedDeparture"
})
public class OriginPortDetailType {

    @XmlElement(name = "OriginPort")
    protected String originPort;
    @XmlElement(name = "EstimatedDeparture")
    protected DateTimeType estimatedDeparture;

    /**
     * Gets the value of the originPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginPort() {
        return originPort;
    }

    /**
     * Sets the value of the originPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginPort(String value) {
        this.originPort = value;
    }

    /**
     * Gets the value of the estimatedDeparture property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getEstimatedDeparture() {
        return estimatedDeparture;
    }

    /**
     * Sets the value of the estimatedDeparture property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setEstimatedDeparture(DateTimeType value) {
        this.estimatedDeparture = value;
    }

}
