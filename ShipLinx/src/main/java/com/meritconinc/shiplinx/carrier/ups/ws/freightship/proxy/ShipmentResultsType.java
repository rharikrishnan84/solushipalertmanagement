
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentResultsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentResultsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AirFreightPickupRequest" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}AirFreightStatusType" minOccurs="0"/>
 *         &lt;element name="ShipperNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerServiceInformation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginServiceCenterCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestinationServiceCenterCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LabelServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AirFreightModularID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PickupRequestConfirmationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeliveryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BOLID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuaranteedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MinimumChargeAppliedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Rate" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}RateType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TotalShipmentCharge" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}TotalShipmentChargeType" minOccurs="0"/>
 *         &lt;element name="MinimumBillableWeightAppliedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BillableShipmentWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}WeightType" minOccurs="0"/>
 *         &lt;element name="RatingSchedule" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="DimensionalWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}WeightType" minOccurs="0"/>
 *         &lt;element name="Service" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="Documents" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}DocumentType" minOccurs="0"/>
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
@XmlType(name = "ShipmentResultsType", propOrder = {
    "airFreightPickupRequest",
    "shipperNumber",
    "creationDate",
    "customerServiceInformation",
    "originServiceCenterCode",
    "destinationServiceCenterCode",
    "labelServiceCode",
    "airFreightModularID",
    "pickupRequestConfirmationNumber",
    "deliveryDate",
    "shipmentNumber",
    "bolid",
    "guaranteedIndicator",
    "minimumChargeAppliedIndicator",
    "rate",
    "totalShipmentCharge",
    "minimumBillableWeightAppliedIndicator",
    "billableShipmentWeight",
    "ratingSchedule",
    "dimensionalWeight",
    "service",
    "documents",
    "holdAtAirportPickupDate",
    "nextAvailablePickupDate"
})
public class ShipmentResultsType {

    @XmlElement(name = "AirFreightPickupRequest")
    protected AirFreightStatusType airFreightPickupRequest;
    @XmlElement(name = "ShipperNumber")
    protected String shipperNumber;
    @XmlElement(name = "CreationDate")
    protected String creationDate;
    @XmlElement(name = "CustomerServiceInformation")
    protected String customerServiceInformation;
    @XmlElement(name = "OriginServiceCenterCode")
    protected String originServiceCenterCode;
    @XmlElement(name = "DestinationServiceCenterCode")
    protected String destinationServiceCenterCode;
    @XmlElement(name = "LabelServiceCode")
    protected String labelServiceCode;
    @XmlElement(name = "AirFreightModularID")
    protected String airFreightModularID;
    @XmlElement(name = "PickupRequestConfirmationNumber")
    protected String pickupRequestConfirmationNumber;
    @XmlElement(name = "DeliveryDate")
    protected String deliveryDate;
    @XmlElement(name = "ShipmentNumber")
    protected String shipmentNumber;
    @XmlElement(name = "BOLID")
    protected String bolid;
    @XmlElement(name = "GuaranteedIndicator")
    protected String guaranteedIndicator;
    @XmlElement(name = "MinimumChargeAppliedIndicator")
    protected String minimumChargeAppliedIndicator;
    @XmlElement(name = "Rate")
    protected List<RateType> rate;
    @XmlElement(name = "TotalShipmentCharge")
    protected TotalShipmentChargeType totalShipmentCharge;
    @XmlElement(name = "MinimumBillableWeightAppliedIndicator")
    protected String minimumBillableWeightAppliedIndicator;
    @XmlElement(name = "BillableShipmentWeight")
    protected WeightType billableShipmentWeight;
    @XmlElement(name = "RatingSchedule")
    protected ShipCodeDescriptionType ratingSchedule;
    @XmlElement(name = "DimensionalWeight")
    protected WeightType dimensionalWeight;
    @XmlElement(name = "Service")
    protected ShipCodeDescriptionType service;
    @XmlElement(name = "Documents")
    protected DocumentType documents;
    @XmlElement(name = "HoldAtAirportPickupDate")
    protected String holdAtAirportPickupDate;
    @XmlElement(name = "NextAvailablePickupDate")
    protected String nextAvailablePickupDate;

    /**
     * Gets the value of the airFreightPickupRequest property.
     * 
     * @return
     *     possible object is
     *     {@link AirFreightStatusType }
     *     
     */
    public AirFreightStatusType getAirFreightPickupRequest() {
        return airFreightPickupRequest;
    }

    /**
     * Sets the value of the airFreightPickupRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirFreightStatusType }
     *     
     */
    public void setAirFreightPickupRequest(AirFreightStatusType value) {
        this.airFreightPickupRequest = value;
    }

    /**
     * Gets the value of the shipperNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperNumber() {
        return shipperNumber;
    }

    /**
     * Sets the value of the shipperNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperNumber(String value) {
        this.shipperNumber = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDate(String value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the customerServiceInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerServiceInformation() {
        return customerServiceInformation;
    }

    /**
     * Sets the value of the customerServiceInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerServiceInformation(String value) {
        this.customerServiceInformation = value;
    }

    /**
     * Gets the value of the originServiceCenterCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginServiceCenterCode() {
        return originServiceCenterCode;
    }

    /**
     * Sets the value of the originServiceCenterCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginServiceCenterCode(String value) {
        this.originServiceCenterCode = value;
    }

    /**
     * Gets the value of the destinationServiceCenterCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationServiceCenterCode() {
        return destinationServiceCenterCode;
    }

    /**
     * Sets the value of the destinationServiceCenterCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationServiceCenterCode(String value) {
        this.destinationServiceCenterCode = value;
    }

    /**
     * Gets the value of the labelServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelServiceCode() {
        return labelServiceCode;
    }

    /**
     * Sets the value of the labelServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelServiceCode(String value) {
        this.labelServiceCode = value;
    }

    /**
     * Gets the value of the airFreightModularID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirFreightModularID() {
        return airFreightModularID;
    }

    /**
     * Sets the value of the airFreightModularID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirFreightModularID(String value) {
        this.airFreightModularID = value;
    }

    /**
     * Gets the value of the pickupRequestConfirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupRequestConfirmationNumber() {
        return pickupRequestConfirmationNumber;
    }

    /**
     * Sets the value of the pickupRequestConfirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupRequestConfirmationNumber(String value) {
        this.pickupRequestConfirmationNumber = value;
    }

    /**
     * Gets the value of the deliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Sets the value of the deliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryDate(String value) {
        this.deliveryDate = value;
    }

    /**
     * Gets the value of the shipmentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentNumber() {
        return shipmentNumber;
    }

    /**
     * Sets the value of the shipmentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentNumber(String value) {
        this.shipmentNumber = value;
    }

    /**
     * Gets the value of the bolid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBOLID() {
        return bolid;
    }

    /**
     * Sets the value of the bolid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBOLID(String value) {
        this.bolid = value;
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
     * Gets the value of the ratingSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getRatingSchedule() {
        return ratingSchedule;
    }

    /**
     * Sets the value of the ratingSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setRatingSchedule(ShipCodeDescriptionType value) {
        this.ratingSchedule = value;
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
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setService(ShipCodeDescriptionType value) {
        this.service = value;
    }

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentType }
     *     
     */
    public DocumentType getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentType }
     *     
     */
    public void setDocuments(DocumentType value) {
        this.documents = value;
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
