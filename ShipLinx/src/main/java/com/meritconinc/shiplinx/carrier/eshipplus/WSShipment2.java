
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for WSShipment2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSShipment2">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eshipplus.com/}WSReturn">
 *       &lt;sequence>
 *         &lt;element name="BookingReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BookingReferenceNumberType" type="{http://www.eshipplus.com/}BookingReferenceNumberType"/>
 *         &lt;element name="Origin" type="{http://www.eshipplus.com/}WSLocation2" minOccurs="0"/>
 *         &lt;element name="Destination" type="{http://www.eshipplus.com/}WSLocation2" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PurchaseOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipperBOL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomReferences" type="{http://www.eshipplus.com/}ArrayOfWSReference" minOccurs="0"/>
 *         &lt;element name="ShipmentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EarliestPickup" type="{http://www.eshipplus.com/}WSTime2" minOccurs="0"/>
 *         &lt;element name="LatestPickup" type="{http://www.eshipplus.com/}WSTime2" minOccurs="0"/>
 *         &lt;element name="EarliestDelivery" type="{http://www.eshipplus.com/}WSTime2" minOccurs="0"/>
 *         &lt;element name="LatestDelivery" type="{http://www.eshipplus.com/}WSTime2" minOccurs="0"/>
 *         &lt;element name="Items" type="{http://www.eshipplus.com/}ArrayOfWSItem2" minOccurs="0"/>
 *         &lt;element name="Accessorials" type="{http://www.eshipplus.com/}ArrayOfWSAccessorialService" minOccurs="0"/>
 *         &lt;element name="DeclineAdditionalInsuranceIfApplicable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="HazardousMaterialShipment" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="HazardousMaterialContactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HazardousMaterialContactPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HazardousMaterialContactMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HazardousMaterialContactEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AvailableRates" type="{http://www.eshipplus.com/}ArrayOfWSRate2" minOccurs="0"/>
 *         &lt;element name="SelectedRate" type="{http://www.eshipplus.com/}WSRate2" minOccurs="0"/>
 *         &lt;element name="BookedRate" type="{http://www.eshipplus.com/}WSRate2" minOccurs="0"/>
 *         &lt;element name="ReturnConfirmations" type="{http://www.eshipplus.com/}ArrayOfWSDocument" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSShipment2", propOrder = {
    "bookingReferenceNumber",
    "bookingReferenceNumberType",
    "origin",
    "destination",
    "referenceNumber",
    "purchaseOrder",
    "shipperBOL",
    "customReferences",
    "shipmentDate",
    "earliestPickup",
    "latestPickup",
    "earliestDelivery",
    "latestDelivery",
    "items",
    "accessorials",
    "declineAdditionalInsuranceIfApplicable",
    "hazardousMaterialShipment",
    "hazardousMaterialContactName",
    "hazardousMaterialContactPhone",
    "hazardousMaterialContactMobile",
    "hazardousMaterialContactEmail",
    "availableRates",
    "selectedRate",
    "bookedRate",
    "returnConfirmations"
})
public class WSShipment2
    extends WSReturn
{

    @XmlElement(name = "BookingReferenceNumber")
    protected String bookingReferenceNumber;
    @XmlElement(name = "BookingReferenceNumberType", required = true)
    protected BookingReferenceNumberType bookingReferenceNumberType;
    @XmlElement(name = "Origin")
    protected WSLocation2 origin;
    @XmlElement(name = "Destination")
    protected WSLocation2 destination;
    @XmlElement(name = "ReferenceNumber")
    protected String referenceNumber;
    @XmlElement(name = "PurchaseOrder")
    protected String purchaseOrder;
    @XmlElement(name = "ShipperBOL")
    protected String shipperBOL;
    @XmlElement(name = "CustomReferences")
    protected ArrayOfWSReference customReferences;
    @XmlElement(name = "ShipmentDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar shipmentDate;
    @XmlElement(name = "EarliestPickup")
    protected WSTime2 earliestPickup;
    @XmlElement(name = "LatestPickup")
    protected WSTime2 latestPickup;
    @XmlElement(name = "EarliestDelivery")
    protected WSTime2 earliestDelivery;
    @XmlElement(name = "LatestDelivery")
    protected WSTime2 latestDelivery;
    @XmlElement(name = "Items")
    protected ArrayOfWSItem2 items;
    @XmlElement(name = "Accessorials")
    protected ArrayOfWSAccessorialService accessorials;
    @XmlElement(name = "DeclineAdditionalInsuranceIfApplicable")
    protected boolean declineAdditionalInsuranceIfApplicable;
    @XmlElement(name = "HazardousMaterialShipment")
    protected boolean hazardousMaterialShipment;
    @XmlElement(name = "HazardousMaterialContactName")
    protected String hazardousMaterialContactName;
    @XmlElement(name = "HazardousMaterialContactPhone")
    protected String hazardousMaterialContactPhone;
    @XmlElement(name = "HazardousMaterialContactMobile")
    protected String hazardousMaterialContactMobile;
    @XmlElement(name = "HazardousMaterialContactEmail")
    protected String hazardousMaterialContactEmail;
    @XmlElement(name = "AvailableRates")
    protected ArrayOfWSRate2 availableRates;
    @XmlElement(name = "SelectedRate")
    protected WSRate2 selectedRate;
    @XmlElement(name = "BookedRate")
    protected WSRate2 bookedRate;
    @XmlElement(name = "ReturnConfirmations")
    protected ArrayOfWSDocument returnConfirmations;
   
    /**
     * Gets the value of the bookingReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingReferenceNumber() {
        return bookingReferenceNumber;
    }

    /**
     * Sets the value of the bookingReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingReferenceNumber(String value) {
        this.bookingReferenceNumber = value;
    }

    /**
     * Gets the value of the bookingReferenceNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link BookingReferenceNumberType }
     *     
     */
    public BookingReferenceNumberType getBookingReferenceNumberType() {
        return bookingReferenceNumberType;
    }

    /**
     * Sets the value of the bookingReferenceNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingReferenceNumberType }
     *     
     */
    public void setBookingReferenceNumberType(BookingReferenceNumberType value) {
        this.bookingReferenceNumberType = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link WSLocation2 }
     *     
     */
    public WSLocation2 getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSLocation2 }
     *     
     */
    public void setOrigin(WSLocation2 value) {
        this.origin = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link WSLocation2 }
     *     
     */
    public WSLocation2 getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSLocation2 }
     *     
     */
    public void setDestination(WSLocation2 value) {
        this.destination = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the purchaseOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Sets the value of the purchaseOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseOrder(String value) {
        this.purchaseOrder = value;
    }

    /**
     * Gets the value of the shipperBOL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperBOL() {
        return shipperBOL;
    }

    /**
     * Sets the value of the shipperBOL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperBOL(String value) {
        this.shipperBOL = value;
    }

    /**
     * Gets the value of the customReferences property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSReference }
     *     
     */
    public ArrayOfWSReference getCustomReferences() {
        return customReferences;
    }

    /**
     * Sets the value of the customReferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSReference }
     *     
     */
    public void setCustomReferences(ArrayOfWSReference value) {
        this.customReferences = value;
    }

    /**
     * Gets the value of the shipmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getShipmentDate() {
        return shipmentDate;
    }

    /**
     * Sets the value of the shipmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setShipmentDate(XMLGregorianCalendar value) {
        this.shipmentDate = value;
    }

    /**
     * Gets the value of the earliestPickup property.
     * 
     * @return
     *     possible object is
     *     {@link WSTime2 }
     *     
     */
    public WSTime2 getEarliestPickup() {
        return earliestPickup;
    }

    /**
     * Sets the value of the earliestPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSTime2 }
     *     
     */
    public void setEarliestPickup(WSTime2 value) {
        this.earliestPickup = value;
    }

    /**
     * Gets the value of the latestPickup property.
     * 
     * @return
     *     possible object is
     *     {@link WSTime2 }
     *     
     */
    public WSTime2 getLatestPickup() {
        return latestPickup;
    }

    /**
     * Sets the value of the latestPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSTime2 }
     *     
     */
    public void setLatestPickup(WSTime2 value) {
        this.latestPickup = value;
    }

    /**
     * Gets the value of the earliestDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link WSTime2 }
     *     
     */
    public WSTime2 getEarliestDelivery() {
        return earliestDelivery;
    }

    /**
     * Sets the value of the earliestDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSTime2 }
     *     
     */
    public void setEarliestDelivery(WSTime2 value) {
        this.earliestDelivery = value;
    }

    /**
     * Gets the value of the latestDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link WSTime2 }
     *     
     */
    public WSTime2 getLatestDelivery() {
        return latestDelivery;
    }

    /**
     * Sets the value of the latestDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSTime2 }
     *     
     */
    public void setLatestDelivery(WSTime2 value) {
        this.latestDelivery = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSItem2 }
     *     
     */
    public ArrayOfWSItem2 getItems() {
        return items;
    }

    /**
     * Sets the value of the items property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSItem2 }
     *     
     */
    public void setItems(ArrayOfWSItem2 value) {
        this.items = value;
    }

    /**
     * Gets the value of the accessorials property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSAccessorialService }
     *     
     */
    public ArrayOfWSAccessorialService getAccessorials() {
        return accessorials;
    }

    /**
     * Sets the value of the accessorials property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSAccessorialService }
     *     
     */
    public void setAccessorials(ArrayOfWSAccessorialService value) {
        this.accessorials = value;
    }

    /**
     * Gets the value of the declineAdditionalInsuranceIfApplicable property.
     * 
     */
    public boolean isDeclineAdditionalInsuranceIfApplicable() {
        return declineAdditionalInsuranceIfApplicable;
    }

    /**
     * Sets the value of the declineAdditionalInsuranceIfApplicable property.
     * 
     */
    public void setDeclineAdditionalInsuranceIfApplicable(boolean value) {
        this.declineAdditionalInsuranceIfApplicable = value;
    }

    /**
     * Gets the value of the hazardousMaterialShipment property.
     * 
     */
    public boolean isHazardousMaterialShipment() {
        return hazardousMaterialShipment;
    }

    /**
     * Sets the value of the hazardousMaterialShipment property.
     * 
     */
    public void setHazardousMaterialShipment(boolean value) {
        this.hazardousMaterialShipment = value;
    }

    /**
     * Gets the value of the hazardousMaterialContactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHazardousMaterialContactName() {
        return hazardousMaterialContactName;
    }

    /**
     * Sets the value of the hazardousMaterialContactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHazardousMaterialContactName(String value) {
        this.hazardousMaterialContactName = value;
    }

    /**
     * Gets the value of the hazardousMaterialContactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHazardousMaterialContactPhone() {
        return hazardousMaterialContactPhone;
    }

    /**
     * Sets the value of the hazardousMaterialContactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHazardousMaterialContactPhone(String value) {
        this.hazardousMaterialContactPhone = value;
    }

    /**
     * Gets the value of the hazardousMaterialContactMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHazardousMaterialContactMobile() {
        return hazardousMaterialContactMobile;
    }

    /**
     * Sets the value of the hazardousMaterialContactMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHazardousMaterialContactMobile(String value) {
        this.hazardousMaterialContactMobile = value;
    }

    /**
     * Gets the value of the hazardousMaterialContactEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHazardousMaterialContactEmail() {
        return hazardousMaterialContactEmail;
    }

    /**
     * Sets the value of the hazardousMaterialContactEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHazardousMaterialContactEmail(String value) {
        this.hazardousMaterialContactEmail = value;
    }

    /**
     * Gets the value of the availableRates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSRate2 }
     *     
     */
    public ArrayOfWSRate2 getAvailableRates() {
        return availableRates;
    }

    /**
     * Sets the value of the availableRates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSRate2 }
     *     
     */
    public void setAvailableRates(ArrayOfWSRate2 value) {
        this.availableRates = value;
    }

    /**
     * Gets the value of the selectedRate property.
     * 
     * @return
     *     possible object is
     *     {@link WSRate2 }
     *     
     */
    public WSRate2 getSelectedRate() {
        return selectedRate;
    }

    /**
     * Sets the value of the selectedRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSRate2 }
     *     
     */
    public void setSelectedRate(WSRate2 value) {
        this.selectedRate = value;
    }

    /**
     * Gets the value of the bookedRate property.
     * 
     * @return
     *     possible object is
     *     {@link WSRate2 }
     *     
     */
    public WSRate2 getBookedRate() {
        return bookedRate;
    }

    /**
     * Sets the value of the bookedRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSRate2 }
     *     
     */
    public void setBookedRate(WSRate2 value) {
        this.bookedRate = value;
    }

    /**
     * Gets the value of the returnConfirmations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSDocument }
     *     
     */
    public ArrayOfWSDocument getReturnConfirmations() {
        return returnConfirmations;
    }

    /**
     * Sets the value of the returnConfirmations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSDocument }
     *     
     */
    public void setReturnConfirmations(ArrayOfWSDocument value) {
        this.returnConfirmations = value;
    }

}
