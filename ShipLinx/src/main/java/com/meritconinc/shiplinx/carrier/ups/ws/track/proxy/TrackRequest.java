
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

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
 *         &lt;element name="InquiryNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackingOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CandidateBookmark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;group ref="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ReferenceNumberGroup" minOccurs="0"/>
 *         &lt;element name="ShipperAccountInfo" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ShipperAccountInfoType" minOccurs="0"/>
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
    "inquiryNumber",
    "trackingOption",
    "candidateBookmark",
    "referenceNumber",
    "pickupDateRange",
    "shipperNumber",
    "shipFrom",
    "shipTo",
    "shipmentType",
    "shipperAccountInfo"
})
@XmlRootElement(name = "TrackRequest")
public class TrackRequest {

    @XmlElement(name = "Request", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected RequestType request;
    @XmlElement(name = "InquiryNumber")
    protected String inquiryNumber;
    @XmlElement(name = "TrackingOption")
    protected String trackingOption;
    @XmlElement(name = "CandidateBookmark")
    protected String candidateBookmark;
    @XmlElement(name = "ReferenceNumber")
    protected ReferenceNumberType referenceNumber;
    @XmlElement(name = "PickupDateRange")
    protected PickupDateRangeType pickupDateRange;
    @XmlElement(name = "ShipperNumber")
    protected String shipperNumber;
    @XmlElement(name = "ShipFrom")
    protected ShipFromRequestType shipFrom;
    @XmlElement(name = "ShipTo")
    protected ShipToRequestType shipTo;
    @XmlElement(name = "ShipmentType")
    protected RefShipmentType shipmentType;
    @XmlElement(name = "ShipperAccountInfo")
    protected ShipperAccountInfoType shipperAccountInfo;

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
     * Gets the value of the inquiryNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInquiryNumber() {
        return inquiryNumber;
    }

    /**
     * Sets the value of the inquiryNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInquiryNumber(String value) {
        this.inquiryNumber = value;
    }

    /**
     * Gets the value of the trackingOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingOption() {
        return trackingOption;
    }

    /**
     * Sets the value of the trackingOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingOption(String value) {
        this.trackingOption = value;
    }

    /**
     * Gets the value of the candidateBookmark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCandidateBookmark() {
        return candidateBookmark;
    }

    /**
     * Sets the value of the candidateBookmark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCandidateBookmark(String value) {
        this.candidateBookmark = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceNumberType }
     *     
     */
    public ReferenceNumberType getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceNumberType }
     *     
     */
    public void setReferenceNumber(ReferenceNumberType value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the pickupDateRange property.
     * 
     * @return
     *     possible object is
     *     {@link PickupDateRangeType }
     *     
     */
    public PickupDateRangeType getPickupDateRange() {
        return pickupDateRange;
    }

    /**
     * Sets the value of the pickupDateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupDateRangeType }
     *     
     */
    public void setPickupDateRange(PickupDateRangeType value) {
        this.pickupDateRange = value;
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
     * Gets the value of the shipFrom property.
     * 
     * @return
     *     possible object is
     *     {@link ShipFromRequestType }
     *     
     */
    public ShipFromRequestType getShipFrom() {
        return shipFrom;
    }

    /**
     * Sets the value of the shipFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipFromRequestType }
     *     
     */
    public void setShipFrom(ShipFromRequestType value) {
        this.shipFrom = value;
    }

    /**
     * Gets the value of the shipTo property.
     * 
     * @return
     *     possible object is
     *     {@link ShipToRequestType }
     *     
     */
    public ShipToRequestType getShipTo() {
        return shipTo;
    }

    /**
     * Sets the value of the shipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipToRequestType }
     *     
     */
    public void setShipTo(ShipToRequestType value) {
        this.shipTo = value;
    }

    /**
     * Gets the value of the shipmentType property.
     * 
     * @return
     *     possible object is
     *     {@link RefShipmentType }
     *     
     */
    public RefShipmentType getShipmentType() {
        return shipmentType;
    }

    /**
     * Sets the value of the shipmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefShipmentType }
     *     
     */
    public void setShipmentType(RefShipmentType value) {
        this.shipmentType = value;
    }

    /**
     * Gets the value of the shipperAccountInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ShipperAccountInfoType }
     *     
     */
    public ShipperAccountInfoType getShipperAccountInfo() {
        return shipperAccountInfo;
    }

    /**
     * Sets the value of the shipperAccountInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipperAccountInfoType }
     *     
     */
    public void setShipperAccountInfo(ShipperAccountInfoType value) {
        this.shipperAccountInfo = value;
    }

}
