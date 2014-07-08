
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RatedShipmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RatedShipmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Service" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}CodeDescriptionType"/>
 *         &lt;element name="RateChart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RatedShipmentAlert" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}RatedShipmentInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BillingWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}BillingWeightType"/>
 *         &lt;element name="TransportationCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}ChargesType"/>
 *         &lt;element name="FRSShipmentData" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}FRSShipmentType" minOccurs="0"/>
 *         &lt;element name="ServiceOptionsCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}ChargesType"/>
 *         &lt;element name="TotalCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}ChargesType"/>
 *         &lt;element name="NegotiatedRateCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}TotalChargeType" minOccurs="0"/>
 *         &lt;element name="GuaranteedDelivery" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}GuaranteedDeliveryType" minOccurs="0"/>
 *         &lt;element name="RatedPackage" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}RatedPackageType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RatedShipmentType", propOrder = {
    "service",
    "rateChart",
    "ratedShipmentAlert",
    "billingWeight",
    "transportationCharges",
    "frsShipmentData",
    "serviceOptionsCharges",
    "totalCharges",
    "negotiatedRateCharges",
    "guaranteedDelivery",
    "ratedPackage"
})
public class RatedShipmentType {

    @XmlElement(name = "Service", required = true)
    protected RateCodeDescriptionType service;
    @XmlElement(name = "RateChart")
    protected String rateChart;
    @XmlElement(name = "RatedShipmentAlert")
    protected List<RatedShipmentInfoType> ratedShipmentAlert;
    @XmlElement(name = "BillingWeight", required = true)
    protected BillingWeightType billingWeight;
    @XmlElement(name = "TransportationCharges", required = true)
    protected ChargesType transportationCharges;
    @XmlElement(name = "FRSShipmentData")
    protected FRSShipmentType frsShipmentData;
    @XmlElement(name = "ServiceOptionsCharges", required = true)
    protected ChargesType serviceOptionsCharges;
    @XmlElement(name = "TotalCharges", required = true)
    protected ChargesType totalCharges;
    @XmlElement(name = "NegotiatedRateCharges")
    protected TotalChargeType negotiatedRateCharges;
    @XmlElement(name = "GuaranteedDelivery")
    protected GuaranteedDeliveryType guaranteedDelivery;
    @XmlElement(name = "RatedPackage", required = true)
    protected List<RatedPackageType> ratedPackage;

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public RateCodeDescriptionType getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public void setService(RateCodeDescriptionType value) {
        this.service = value;
    }

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
     * Gets the value of the ratedShipmentAlert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ratedShipmentAlert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRatedShipmentAlert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RatedShipmentInfoType }
     * 
     * 
     */
    public List<RatedShipmentInfoType> getRatedShipmentAlert() {
        if (ratedShipmentAlert == null) {
            ratedShipmentAlert = new ArrayList<RatedShipmentInfoType>();
        }
        return this.ratedShipmentAlert;
    }

    /**
     * Gets the value of the billingWeight property.
     * 
     * @return
     *     possible object is
     *     {@link BillingWeightType }
     *     
     */
    public BillingWeightType getBillingWeight() {
        return billingWeight;
    }

    /**
     * Sets the value of the billingWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillingWeightType }
     *     
     */
    public void setBillingWeight(BillingWeightType value) {
        this.billingWeight = value;
    }

    /**
     * Gets the value of the transportationCharges property.
     * 
     * @return
     *     possible object is
     *     {@link ChargesType }
     *     
     */
    public ChargesType getTransportationCharges() {
        return transportationCharges;
    }

    /**
     * Sets the value of the transportationCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargesType }
     *     
     */
    public void setTransportationCharges(ChargesType value) {
        this.transportationCharges = value;
    }

    /**
     * Gets the value of the frsShipmentData property.
     * 
     * @return
     *     possible object is
     *     {@link FRSShipmentType }
     *     
     */
    public FRSShipmentType getFRSShipmentData() {
        return frsShipmentData;
    }

    /**
     * Sets the value of the frsShipmentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link FRSShipmentType }
     *     
     */
    public void setFRSShipmentData(FRSShipmentType value) {
        this.frsShipmentData = value;
    }

    /**
     * Gets the value of the serviceOptionsCharges property.
     * 
     * @return
     *     possible object is
     *     {@link ChargesType }
     *     
     */
    public ChargesType getServiceOptionsCharges() {
        return serviceOptionsCharges;
    }

    /**
     * Sets the value of the serviceOptionsCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargesType }
     *     
     */
    public void setServiceOptionsCharges(ChargesType value) {
        this.serviceOptionsCharges = value;
    }

    /**
     * Gets the value of the totalCharges property.
     * 
     * @return
     *     possible object is
     *     {@link ChargesType }
     *     
     */
    public ChargesType getTotalCharges() {
        return totalCharges;
    }

    /**
     * Sets the value of the totalCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargesType }
     *     
     */
    public void setTotalCharges(ChargesType value) {
        this.totalCharges = value;
    }

    /**
     * Gets the value of the negotiatedRateCharges property.
     * 
     * @return
     *     possible object is
     *     {@link TotalChargeType }
     *     
     */
    public TotalChargeType getNegotiatedRateCharges() {
        return negotiatedRateCharges;
    }

    /**
     * Sets the value of the negotiatedRateCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalChargeType }
     *     
     */
    public void setNegotiatedRateCharges(TotalChargeType value) {
        this.negotiatedRateCharges = value;
    }

    /**
     * Gets the value of the guaranteedDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link GuaranteedDeliveryType }
     *     
     */
    public GuaranteedDeliveryType getGuaranteedDelivery() {
        return guaranteedDelivery;
    }

    /**
     * Sets the value of the guaranteedDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuaranteedDeliveryType }
     *     
     */
    public void setGuaranteedDelivery(GuaranteedDeliveryType value) {
        this.guaranteedDelivery = value;
    }

    /**
     * Gets the value of the ratedPackage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ratedPackage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRatedPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RatedPackageType }
     * 
     * 
     */
    public List<RatedPackageType> getRatedPackage() {
        if (ratedPackage == null) {
            ratedPackage = new ArrayList<RatedPackageType>();
        }
        return this.ratedPackage;
    }

}
