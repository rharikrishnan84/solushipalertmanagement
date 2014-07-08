
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
 *         &lt;element ref="{http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0}Request"/>
 *         &lt;element name="CancelBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PRN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "request",
    "cancelBy",
    "prn"
})
@XmlRootElement(name = "PickupCancelRequest")
public class PickupCancelRequest {

    @XmlElement(name = "Request", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected RequestType request;
    @XmlElement(name = "CancelBy", required = true)
    protected String cancelBy;
    @XmlElement(name = "PRN")
    protected String prn;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link RequestType }
     *     
     */
    public RequestType getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestType }
     *     
     */
    public void setRequest(RequestType value) {
        this.request = value;
    }

    /**
     * Gets the value of the cancelBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelBy() {
        return cancelBy;
    }

    /**
     * Sets the value of the cancelBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelBy(String value) {
        this.cancelBy = value;
    }

    /**
     * Gets the value of the prn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRN() {
        return prn;
    }

    /**
     * Sets the value of the prn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRN(String value) {
        this.prn = value;
    }

}
