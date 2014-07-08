
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentChargesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentChargesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RateChart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransportationCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipChargeType"/>
 *         &lt;element name="ServiceOptionsCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipChargeType"/>
 *         &lt;element name="TotalCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipChargeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentChargesType", propOrder = {
    "rateChart",
    "transportationCharges",
    "serviceOptionsCharges",
    "totalCharges"
})
public class ShipmentChargesType {

    @XmlElement(name = "RateChart")
    protected String rateChart;
    @XmlElement(name = "TransportationCharges", required = true)
    protected ShipChargeType transportationCharges;
    @XmlElement(name = "ServiceOptionsCharges", required = true)
    protected ShipChargeType serviceOptionsCharges;
    @XmlElement(name = "TotalCharges", required = true)
    protected ShipChargeType totalCharges;

    /**
     * Gets the value of the rateChart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateChart() {
        return rateChart;
    }

    /**
     * Sets the value of the rateChart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateChart(String value) {
        this.rateChart = value;
    }

    /**
     * Gets the value of the transportationCharges property.
     * 
     * @return
     *     possible object is
     *     {@link ShipChargeType }
     *     
     */
    public ShipChargeType getTransportationCharges() {
        return transportationCharges;
    }

    /**
     * Sets the value of the transportationCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipChargeType }
     *     
     */
    public void setTransportationCharges(ShipChargeType value) {
        this.transportationCharges = value;
    }

    /**
     * Gets the value of the serviceOptionsCharges property.
     * 
     * @return
     *     possible object is
     *     {@link ShipChargeType }
     *     
     */
    public ShipChargeType getServiceOptionsCharges() {
        return serviceOptionsCharges;
    }

    /**
     * Sets the value of the serviceOptionsCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipChargeType }
     *     
     */
    public void setServiceOptionsCharges(ShipChargeType value) {
        this.serviceOptionsCharges = value;
    }

    /**
     * Gets the value of the totalCharges property.
     * 
     * @return
     *     possible object is
     *     {@link ShipChargeType }
     *     
     */
    public ShipChargeType getTotalCharges() {
        return totalCharges;
    }

    /**
     * Sets the value of the totalCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipChargeType }
     *     
     */
    public void setTotalCharges(ShipChargeType value) {
        this.totalCharges = value;
    }

}
