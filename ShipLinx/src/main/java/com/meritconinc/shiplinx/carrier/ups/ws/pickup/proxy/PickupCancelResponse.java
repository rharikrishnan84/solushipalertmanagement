
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

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
 *         &lt;element ref="{http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0}Response"/>
 *         &lt;element name="PickupType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GWNStatus" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}StatusCodeDescriptionType" minOccurs="0"/>
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
    "response",
    "pickupType",
    "gwnStatus"
})
@XmlRootElement(name = "PickupCancelResponse")
public class PickupCancelResponse {

    @XmlElement(name = "Response", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected ResponseType response;
    @XmlElement(name = "PickupType", required = true)
    protected String pickupType;
    @XmlElement(name = "GWNStatus")
    protected StatusCodeDescriptionType gwnStatus;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseType }
     *     
     */
    public ResponseType getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseType }
     *     
     */
    public void setResponse(ResponseType value) {
        this.response = value;
    }

    /**
     * Gets the value of the pickupType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupType() {
        return pickupType;
    }

    /**
     * Sets the value of the pickupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupType(String value) {
        this.pickupType = value;
    }

    /**
     * Gets the value of the gwnStatus property.
     * 
     * @return
     *     possible object is
     *     {@link StatusCodeDescriptionType }
     *     
     */
    public StatusCodeDescriptionType getGWNStatus() {
        return gwnStatus;
    }

    /**
     * Sets the value of the gwnStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusCodeDescriptionType }
     *     
     */
    public void setGWNStatus(StatusCodeDescriptionType value) {
        this.gwnStatus = value;
    }

}
