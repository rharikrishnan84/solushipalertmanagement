
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
 *         &lt;element name="PickupAddress" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}AddressType"/>
 *         &lt;element name="AlternateAddressIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceDateOption" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickupDateInfo" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}PickupDateInfoType" minOccurs="0"/>
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
    "pickupAddress",
    "alternateAddressIndicator",
    "serviceDateOption",
    "pickupDateInfo"
})
@XmlRootElement(name = "PickupRateRequest")
public class PickupRateRequest {

    @XmlElement(name = "Request", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected RequestType request;
    @XmlElement(name = "PickupAddress", required = true)
    protected AddressType pickupAddress;
    @XmlElement(name = "AlternateAddressIndicator", required = true, defaultValue = "N")
    protected String alternateAddressIndicator;
    @XmlElement(name = "ServiceDateOption", required = true)
    protected String serviceDateOption;
    @XmlElement(name = "PickupDateInfo")
    protected PickupDateInfoType pickupDateInfo;

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
     * Gets the value of the pickupAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getPickupAddress() {
        return pickupAddress;
    }

    /**
     * Sets the value of the pickupAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setPickupAddress(AddressType value) {
        this.pickupAddress = value;
    }

    /**
     * Gets the value of the alternateAddressIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateAddressIndicator() {
        return alternateAddressIndicator;
    }

    /**
     * Sets the value of the alternateAddressIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateAddressIndicator(String value) {
        this.alternateAddressIndicator = value;
    }

    /**
     * Gets the value of the serviceDateOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDateOption() {
        return serviceDateOption;
    }

    /**
     * Sets the value of the serviceDateOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDateOption(String value) {
        this.serviceDateOption = value;
    }

    /**
     * Gets the value of the pickupDateInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PickupDateInfoType }
     *     
     */
    public PickupDateInfoType getPickupDateInfo() {
        return pickupDateInfo;
    }

    /**
     * Sets the value of the pickupDateInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupDateInfoType }
     *     
     */
    public void setPickupDateInfo(PickupDateInfoType value) {
        this.pickupDateInfo = value;
    }

}
