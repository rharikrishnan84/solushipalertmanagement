
package com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceSummaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceSummaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Service" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}CodeDescriptionType"/>
 *         &lt;element name="GuaranteedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Disclaimer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstimatedArrival" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}EstimatedArrivalType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceSummaryType", propOrder = {
    "service",
    "guaranteedIndicator",
    "disclaimer",
    "estimatedArrival"
})
public class ServiceSummaryType {

    @XmlElement(name = "Service", required = true)
    protected TNTCodeDescriptionType service;
    @XmlElement(name = "GuaranteedIndicator")
    protected String guaranteedIndicator;
    @XmlElement(name = "Disclaimer")
    protected String disclaimer;
    @XmlElement(name = "EstimatedArrival", required = true)
    protected EstimatedArrivalType estimatedArrival;

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link TNTCodeDescriptionType }
     *     
     */
    public TNTCodeDescriptionType getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link TNTCodeDescriptionType }
     *     
     */
    public void setService(TNTCodeDescriptionType value) {
        this.service = value;
    }

    /**
     * Gets the value of the guaranteedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteedIndicator() {
        return guaranteedIndicator;
    }

    /**
     * Sets the value of the guaranteedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteedIndicator(String value) {
        this.guaranteedIndicator = value;
    }

    /**
     * Gets the value of the disclaimer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisclaimer() {
        return disclaimer;
    }

    /**
     * Sets the value of the disclaimer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisclaimer(String value) {
        this.disclaimer = value;
    }

    /**
     * Gets the value of the estimatedArrival property.
     * 
     * @return
     *     possible object is
     *     {@link EstimatedArrivalType }
     *     
     */
    public EstimatedArrivalType getEstimatedArrival() {
        return estimatedArrival;
    }

    /**
     * Sets the value of the estimatedArrival property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstimatedArrivalType }
     *     
     */
    public void setEstimatedArrival(EstimatedArrivalType value) {
        this.estimatedArrival = value;
    }

}
