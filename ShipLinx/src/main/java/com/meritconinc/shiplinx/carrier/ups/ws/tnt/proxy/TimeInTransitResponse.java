
package com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy;

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
 *         &lt;choice>
 *           &lt;element name="TransitResponse" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}TransitResponseType" minOccurs="0"/>
 *           &lt;element name="CandidateResponse" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}CandidateResponseType" minOccurs="0"/>
 *         &lt;/choice>
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
    "transitResponse",
    "candidateResponse"
})
@XmlRootElement(name = "TimeInTransitResponse")
public class TimeInTransitResponse {

    @XmlElement(name = "Response", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected ResponseType response;
    @XmlElement(name = "TransitResponse")
    protected TransitResponseType transitResponse;
    @XmlElement(name = "CandidateResponse")
    protected CandidateResponseType candidateResponse;

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
     * Gets the value of the transitResponse property.
     * 
     * @return
     *     possible object is
     *     {@link TransitResponseType }
     *     
     */
    public TransitResponseType getTransitResponse() {
        return transitResponse;
    }

    /**
     * Sets the value of the transitResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransitResponseType }
     *     
     */
    public void setTransitResponse(TransitResponseType value) {
        this.transitResponse = value;
    }

    /**
     * Gets the value of the candidateResponse property.
     * 
     * @return
     *     possible object is
     *     {@link CandidateResponseType }
     *     
     */
    public CandidateResponseType getCandidateResponse() {
        return candidateResponse;
    }

    /**
     * Sets the value of the candidateResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link CandidateResponseType }
     *     
     */
    public void setCandidateResponse(CandidateResponseType value) {
        this.candidateResponse = value;
    }

}
