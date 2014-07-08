
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
 *         &lt;element ref="{http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0}Request"/>
 *         &lt;element name="ShipFrom" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}ShipFromType"/>
 *         &lt;element name="ShipTo" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}ShipToType"/>
 *         &lt;element name="PaymentInformation" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}PaymentInformationType"/>
 *         &lt;element name="Service" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateCodeDescriptionType"/>
 *         &lt;element name="HandlingUnitOne" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}HandlingUnitType"/>
 *         &lt;element name="HandlingUnitTwo" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}HandlingUnitType" minOccurs="0"/>
 *         &lt;element name="Commodity" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}CommodityType" maxOccurs="unbounded"/>
 *         &lt;element name="ShipmentServiceOptions" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}ShipmentServiceOptionsType" minOccurs="0"/>
 *         &lt;element name="PickupRequest" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}PickupRequestType" minOccurs="0"/>
 *         &lt;element name="AlternateRateOptions" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateCodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="AccountType" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}AccountType" minOccurs="0"/>
 *         &lt;element name="ShipmentTotalWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}ShipmentTotalWeightType" minOccurs="0"/>
 *         &lt;element name="HandlingUnitWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}HandlingUnitWeightType" minOccurs="0"/>
 *         &lt;element name="AdjustedWeightIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "shipFrom",
    "shipTo",
    "paymentInformation",
    "service",
    "handlingUnitOne",
    "handlingUnitTwo",
    "commodity",
    "shipmentServiceOptions",
    "pickupRequest",
    "alternateRateOptions",
    "accountType",
    "shipmentTotalWeight",
    "handlingUnitWeight",
    "adjustedWeightIndicator"
})
@XmlRootElement(name = "FreightRateRequest")
public class FreightRateRequest {

    @XmlElement(name = "Request", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected RequestType request;
    @XmlElement(name = "ShipFrom", required = true)
    protected ShipFromType shipFrom;
    @XmlElement(name = "ShipTo", required = true)
    protected ShipToType shipTo;
    @XmlElement(name = "PaymentInformation", required = true)
    protected PaymentInformationType paymentInformation;
    @XmlElement(name = "Service", required = true)
    protected RateCodeDescriptionType service;
    @XmlElement(name = "HandlingUnitOne", required = true)
    protected HandlingUnitType handlingUnitOne;
    @XmlElement(name = "HandlingUnitTwo")
    protected HandlingUnitType handlingUnitTwo;
    @XmlElement(name = "Commodity", required = true)
    protected List<CommodityType> commodity;
    @XmlElement(name = "ShipmentServiceOptions")
    protected ShipmentServiceOptionsType shipmentServiceOptions;
    @XmlElement(name = "PickupRequest")
    protected PickupRequestType pickupRequest;
    @XmlElement(name = "AlternateRateOptions")
    protected RateCodeDescriptionType alternateRateOptions;
    @XmlElement(name = "AccountType")
    protected AccountType accountType;
    @XmlElement(name = "ShipmentTotalWeight")
    protected ShipmentTotalWeightType shipmentTotalWeight;
    @XmlElement(name = "HandlingUnitWeight")
    protected HandlingUnitWeightType handlingUnitWeight;
    @XmlElement(name = "AdjustedWeightIndicator")
    protected String adjustedWeightIndicator;

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
     * Gets the value of the shipFrom property.
     * 
     * @return
     *     possible object is
     *     {@link ShipFromType }
     *     
     */
    public ShipFromType getShipFrom() {
        return shipFrom;
    }

    /**
     * Sets the value of the shipFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipFromType }
     *     
     */
    public void setShipFrom(ShipFromType value) {
        this.shipFrom = value;
    }

    /**
     * Gets the value of the shipTo property.
     * 
     * @return
     *     possible object is
     *     {@link ShipToType }
     *     
     */
    public ShipToType getShipTo() {
        return shipTo;
    }

    /**
     * Sets the value of the shipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipToType }
     *     
     */
    public void setShipTo(ShipToType value) {
        this.shipTo = value;
    }

    /**
     * Gets the value of the paymentInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentInformationType }
     *     
     */
    public PaymentInformationType getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * Sets the value of the paymentInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInformationType }
     *     
     */
    public void setPaymentInformation(PaymentInformationType value) {
        this.paymentInformation = value;
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
     * Gets the value of the handlingUnitOne property.
     * 
     * @return
     *     possible object is
     *     {@link HandlingUnitType }
     *     
     */
    public HandlingUnitType getHandlingUnitOne() {
        return handlingUnitOne;
    }

    /**
     * Sets the value of the handlingUnitOne property.
     * 
     * @param value
     *     allowed object is
     *     {@link HandlingUnitType }
     *     
     */
    public void setHandlingUnitOne(HandlingUnitType value) {
        this.handlingUnitOne = value;
    }

    /**
     * Gets the value of the handlingUnitTwo property.
     * 
     * @return
     *     possible object is
     *     {@link HandlingUnitType }
     *     
     */
    public HandlingUnitType getHandlingUnitTwo() {
        return handlingUnitTwo;
    }

    /**
     * Sets the value of the handlingUnitTwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HandlingUnitType }
     *     
     */
    public void setHandlingUnitTwo(HandlingUnitType value) {
        this.handlingUnitTwo = value;
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
     * {@link CommodityType }
     * 
     * 
     */
    public List<CommodityType> getCommodity() {
        if (commodity == null) {
            commodity = new ArrayList<CommodityType>();
        }
        return this.commodity;
    }

    /**
     * Gets the value of the shipmentServiceOptions property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentServiceOptionsType }
     *     
     */
    public ShipmentServiceOptionsType getShipmentServiceOptions() {
        return shipmentServiceOptions;
    }

    /**
     * Sets the value of the shipmentServiceOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentServiceOptionsType }
     *     
     */
    public void setShipmentServiceOptions(ShipmentServiceOptionsType value) {
        this.shipmentServiceOptions = value;
    }

    /**
     * Gets the value of the pickupRequest property.
     * 
     * @return
     *     possible object is
     *     {@link PickupRequestType }
     *     
     */
    public PickupRequestType getPickupRequest() {
        return pickupRequest;
    }

    /**
     * Sets the value of the pickupRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupRequestType }
     *     
     */
    public void setPickupRequest(PickupRequestType value) {
        this.pickupRequest = value;
    }

    /**
     * Gets the value of the alternateRateOptions property.
     * 
     * @return
     *     possible object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public RateCodeDescriptionType getAlternateRateOptions() {
        return alternateRateOptions;
    }

    /**
     * Sets the value of the alternateRateOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public void setAlternateRateOptions(RateCodeDescriptionType value) {
        this.alternateRateOptions = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link AccountType }
     *     
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountType }
     *     
     */
    public void setAccountType(AccountType value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the shipmentTotalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentTotalWeightType }
     *     
     */
    public ShipmentTotalWeightType getShipmentTotalWeight() {
        return shipmentTotalWeight;
    }

    /**
     * Sets the value of the shipmentTotalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentTotalWeightType }
     *     
     */
    public void setShipmentTotalWeight(ShipmentTotalWeightType value) {
        this.shipmentTotalWeight = value;
    }

    /**
     * Gets the value of the handlingUnitWeight property.
     * 
     * @return
     *     possible object is
     *     {@link HandlingUnitWeightType }
     *     
     */
    public HandlingUnitWeightType getHandlingUnitWeight() {
        return handlingUnitWeight;
    }

    /**
     * Sets the value of the handlingUnitWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link HandlingUnitWeightType }
     *     
     */
    public void setHandlingUnitWeight(HandlingUnitWeightType value) {
        this.handlingUnitWeight = value;
    }

    /**
     * Gets the value of the adjustedWeightIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustedWeightIndicator() {
        return adjustedWeightIndicator;
    }

    /**
     * Sets the value of the adjustedWeightIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustedWeightIndicator(String value) {
        this.adjustedWeightIndicator = value;
    }

}
