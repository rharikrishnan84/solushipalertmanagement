
package com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackageLevelResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageLevelResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1}CodeDescriptionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageLevelResult", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1", propOrder = {
    "trackingNumber",
    "status"
})
public class PackageLevelResult {

    @XmlElement(name = "TrackingNumber", required = true)
    protected String trackingNumber;
    @XmlElement(name = "Status", required = true)
    protected VoidCodeDescriptionType status;

    /**
     * Gets the value of the trackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Sets the value of the trackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNumber(String value) {
        this.trackingNumber = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link VoidCodeDescriptionType }
     *     
     */
    public VoidCodeDescriptionType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoidCodeDescriptionType }
     *     
     */
    public void setStatus(VoidCodeDescriptionType value) {
        this.status = value;
    }

}
