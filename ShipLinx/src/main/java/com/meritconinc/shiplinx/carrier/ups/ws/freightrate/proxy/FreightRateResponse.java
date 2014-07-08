
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="CustomerServiceCenterPhone" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}PhoneType" minOccurs="0"/>
 *         &lt;element name="Rate" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Commodity" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}CommodityWeightType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TotalShipmentCharge" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}TotalShipmentChargeType" minOccurs="0"/>
 *         &lt;element name="BillableShipmentWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}WeightType" minOccurs="0"/>
 *         &lt;element name="DimensionalWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}WeightType" minOccurs="0"/>
 *         &lt;element name="Service" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateCodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="GuaranteedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MinimumChargeAppliedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AlternateRatesResponse" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}AlternateRatesResponseType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MinimumBillableWeightAppliedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RatingSchedule" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RatingScheduleType" minOccurs="0"/>
 *         &lt;element name="HoldAtAirportPickupDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NextAvailablePickupDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "customerServiceCenterPhone",
    "rate",
    "commodity",
    "totalShipmentCharge",
    "billableShipmentWeight",
    "dimensionalWeight",
    "service",
    "guaranteedIndicator",
    "minimumChargeAppliedIndicator",
    "alternateRatesResponse",
    "minimumBillableWeightAppliedIndicator",
    "ratingSchedule",
    "holdAtAirportPickupDate",
    "nextAvailablePickupDate"
})
@XmlRootElement(name = "FreightRateResponse")
public class FreightRateResponse {

    @XmlElement(name = "Response", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected ResponseType response;
    @XmlElement(name = "CustomerServiceCenterPhone")
    protected PhoneType customerServiceCenterPhone;
    @XmlElement(name = "Rate")
    protected List<RateType> rate;
    @XmlElement(name = "Commodity")
    protected List<CommodityWeightType> commodity;
    @XmlElement(name = "TotalShipmentCharge")
    protected TotalShipmentChargeType totalShipmentCharge;
    @XmlElement(name = "BillableShipmentWeight")
    protected WeightType billableShipmentWeight;
    @XmlElement(name = "DimensionalWeight")
    protected WeightType dimensionalWeight;
    @XmlElement(name = "Service")
    protected RateCodeDescriptionType service;
    @XmlElement(name = "GuaranteedIndicator")
    protected String guaranteedIndicator;
    @XmlElement(name = "MinimumChargeAppliedIndicator")
    protected String minimumChargeAppliedIndicator;
    @XmlElement(name = "AlternateRatesResponse")
    protected List<AlternateRatesResponseType> alternateRatesResponse;
    @XmlElement(name = "MinimumBillableWeightAppliedIndicator")
    protected String minimumBillableWeightAppliedIndicator;
    @XmlElement(name = "RatingSchedule")
    protected RatingScheduleType ratingSchedule;
    @XmlElement(name = "HoldAtAirportPickupDate")
    protected String holdAtAirportPickupDate;
    @XmlElement(name = "NextAvailablePickupDate")
    protected String nextAvailablePickupDate;

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
     * Gets the value of the customerServiceCenterPhone property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneType }
     *     
     */
    public PhoneType getCustomerServiceCenterPhone() {
        return customerServiceCenterPhone;
    }

    /**
     * Sets the value of the customerServiceCenterPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneType }
     *     
     */
    public void setCustomerServiceCenterPhone(PhoneType value) {
        this.customerServiceCenterPhone = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RateType }
     * 
     * 
     */
    public List<RateType> getRate() {
        if (rate == null) {
            rate = new ArrayList<RateType>();
        }
        return this.rate;
    }

    /**
     * Gets the value of the commodity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commodity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommodity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommodityWeightType }
     * 
     * 
     */
    public List<CommodityWeightType> getCommodity() {
        if (commodity == null) {
            commodity = new ArrayList<CommodityWeightType>();
        }
        return this.commodity;
    }

    /**
     * Gets the value of the totalShipmentCharge property.
     * 
     * @return
     *     possible object is
     *     {@link TotalShipmentChargeType }
     *     
     */
    public TotalShipmentChargeType getTotalShipmentCharge() {
        return totalShipmentCharge;
    }

    /**
     * Sets the value of the totalShipmentCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalShipmentChargeType }
     *     
     */
    public void setTotalShipmentCharge(TotalShipmentChargeType value) {
        this.totalShipmentCharge = value;
    }

    /**
     * Gets the value of the billableShipmentWeight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightType }
     *     
     */
    public WeightType getBillableShipmentWeight() {
        return billableShipmentWeight;
    }

    /**
     * Sets the value of the billableShipmentWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightType }
     *     
     */
    public void setBillableShipmentWeight(WeightType value) {
        this.billableShipmentWeight = value;
    }

    /**
     * Gets the value of the dimensionalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightType }
     *     
     */
    public WeightType getDimensionalWeight() {
        return dimensionalWeight;
    }

    /**
     * Sets the value of the dimensionalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightType }
     *     
     */
    public void setDimensionalWeight(WeightType value) {
        this.dimensionalWeight = value;
    }

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
     * Gets the value of the minimumChargeAppliedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumChargeAppliedIndicator() {
        return minimumChargeAppliedIndicator;
    }

    /**
     * Sets the value of the minimumChargeAppliedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumChargeAppliedIndicator(String value) {
        this.minimumChargeAppliedIndicator = value;
    }

    /**
     * Gets the value of the alternateRatesResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternateRatesResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternateRatesResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlternateRatesResponseType }
     * 
     * 
     */
    public List<AlternateRatesResponseType> getAlternateRatesResponse() {
        if (alternateRatesResponse == null) {
            alternateRatesResponse = new ArrayList<AlternateRatesResponseType>();
        }
        return this.alternateRatesResponse;
    }

    /**
     * Gets the value of the minimumBillableWeightAppliedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumBillableWeightAppliedIndicator() {
        return minimumBillableWeightAppliedIndicator;
    }

    /**
     * Sets the value of the minimumBillableWeightAppliedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumBillableWeightAppliedIndicator(String value) {
        this.minimumBillableWeightAppliedIndicator = value;
    }

    /**
     * Gets the value of the ratingSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link RatingScheduleType }
     *     
     */
    public RatingScheduleType getRatingSchedule() {
        return ratingSchedule;
    }

    /**
     * Sets the value of the ratingSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingScheduleType }
     *     
     */
    public void setRatingSchedule(RatingScheduleType value) {
        this.ratingSchedule = value;
    }

    /**
     * Gets the value of the holdAtAirportPickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoldAtAirportPickupDate() {
        return holdAtAirportPickupDate;
    }

    /**
     * Sets the value of the holdAtAirportPickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoldAtAirportPickupDate(String value) {
        this.holdAtAirportPickupDate = value;
    }

    /**
     * Gets the value of the nextAvailablePickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextAvailablePickupDate() {
        return nextAvailablePickupDate;
    }

    /**
     * Sets the value of the nextAvailablePickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextAvailablePickupDate(String value) {
        this.nextAvailablePickupDate = value;
    }

}
