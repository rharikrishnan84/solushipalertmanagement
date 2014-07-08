
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
 *         &lt;element name="PRN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RateStatus" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}StatusCodeDescriptionType"/>
 *         &lt;element name="RateResult" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}RateResultType" minOccurs="0"/>
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
    "prn",
    "rateStatus",
    "rateResult"
})
@XmlRootElement(name = "PickupCreationResponse")
public class PickupCreationResponse {

    @XmlElement(name = "Response", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected ResponseType response;
    @XmlElement(name = "PRN", required = true)
    protected String prn;
    @XmlElement(name = "RateStatus", required = true)
    protected StatusCodeDescriptionType rateStatus;
    @XmlElement(name = "RateResult")
    protected RateResultType rateResult;

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

    /**
     * Gets the value of the rateStatus property.
     * 
     * @return
     *     possible object is
     *     {@link StatusCodeDescriptionType }
     *     
     */
    public StatusCodeDescriptionType getRateStatus() {
        return rateStatus;
    }

    /**
     * Sets the value of the rateStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusCodeDescriptionType }
     *     
     */
    public void setRateStatus(StatusCodeDescriptionType value) {
        this.rateStatus = value;
    }

    /**
     * Gets the value of the rateResult property.
     * 
     * @return
     *     possible object is
     *     {@link RateResultType }
     *     
     */
    public RateResultType getRateResult() {
        return rateResult;
    }

    /**
     * Sets the value of the rateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateResultType }
     *     
     */
    public void setRateResult(RateResultType value) {
        this.rateResult = value;
    }

}
