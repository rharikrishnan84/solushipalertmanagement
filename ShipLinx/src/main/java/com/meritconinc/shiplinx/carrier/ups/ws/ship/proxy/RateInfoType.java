
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RateInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RateInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NegotiatedRatesIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FRSShipmentIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RateChartIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateInfoType", propOrder = {
    "negotiatedRatesIndicator",
    "frsShipmentIndicator",
    "rateChartIndicator"
})
public class RateInfoType {

    @XmlElement(name = "NegotiatedRatesIndicator")
    protected String negotiatedRatesIndicator;
    @XmlElement(name = "FRSShipmentIndicator")
    protected String frsShipmentIndicator;
    @XmlElement(name = "RateChartIndicator")
    protected String rateChartIndicator;

    /**
     * Gets the value of the negotiatedRatesIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNegotiatedRatesIndicator() {
        return negotiatedRatesIndicator;
    }

    /**
     * Sets the value of the negotiatedRatesIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNegotiatedRatesIndicator(String value) {
        this.negotiatedRatesIndicator = value;
    }

    /**
     * Gets the value of the frsShipmentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFRSShipmentIndicator() {
        return frsShipmentIndicator;
    }

    /**
     * Sets the value of the frsShipmentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFRSShipmentIndicator(String value) {
        this.frsShipmentIndicator = value;
    }

    /**
     * Gets the value of the rateChartIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateChartIndicator() {
        return rateChartIndicator;
    }

    /**
     * Sets the value of the rateChartIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateChartIndicator(String value) {
        this.rateChartIndicator = value;
    }

}
