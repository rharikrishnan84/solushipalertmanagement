//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.21 at 10:03:06 PM EDT 
//


package com.meritconinc.shiplinx.ws.proxy.datatypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

 
/**
 * <p>Java class for OrderWSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderWSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Carrier" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}CarrierWSType" minOccurs="0"/>
 *         &lt;element name="Charges" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}ChargesWSType" minOccurs="0"/>
 *         &lt;element name="Accessorial" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}AccessorialWSType" minOccurs="0"/>
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}StatusWSType" minOccurs="0"/>
 *         &lt;element name="QuotedWeight" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}WeightWSType" minOccurs="0"/>
 *         &lt;element name="BilledWeight" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}WeightWSType" minOccurs="0"/>
 *         &lt;element name="DutiableAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="FromAddress" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}AddressWSType"/>
 *         &lt;element name="ToAddress" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}AddressWSType" minOccurs="0"/>
 *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="ENV"/>
 *               &lt;enumeration value="PACK"/>
 *               &lt;enumeration value="PACKAGE"/>
 *               &lt;enumeration value="PALLET"/>
 *               &lt;maxLength value="7"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Packages" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}PackagesWSType"/>
 *         &lt;element name="References" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="RequiredDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ScheduledShipDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Service" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}ServiceWSType" minOccurs="0"/>
 *         &lt;element name="Pickup" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}PickupWSType" minOccurs="0"/>
 *         &lt;element name="POD" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}PODWSType" minOccurs="0"/>
 *         &lt;element name="ShippingLabel" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="CustomsInvoice" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}CustomsInvoiceWSType" minOccurs="0"/>
 *         &lt;element name="TrackingURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderWSType", propOrder = {
    "orderID",
    "createdDate",
    "carrier",
    "charges",
    "accessorial",
    "currency",
    "status",
    "quotedWeight",
    "billedWeight",
    "dutiableAmount",
    "fromAddress",
    "toAddress",
    "trackingNumber",
    "orderNumber",
    "packageType",
    "packages",
    "references",
    "requiredDeliveryDate",
    "scheduledShipDate",
    "service",
    "pickup",
    "pod",
    "shippingLabel",
    "customsInvoice",
    "trackingURL"
})
public class OrderWSType {

    @XmlElement(name = "OrderID")
    protected Long orderID;
    @XmlElement(name = "CreatedDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "Carrier")
    protected CarrierWSType carrier;
    @XmlElement(name = "Charges")
    protected ChargesWSType charges;
    @XmlElement(name = "Accessorial")
    protected AccessorialWSType accessorial;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "Status")
    protected StatusWSType status;
    @XmlElement(name = "QuotedWeight")
    protected WeightWSType quotedWeight;
    @XmlElement(name = "BilledWeight")
    protected WeightWSType billedWeight;
    @XmlElement(name = "DutiableAmount")
    protected BigDecimal dutiableAmount;
    @XmlElement(name = "FromAddress", required = true)
    protected AddressWSType fromAddress;
    @XmlElement(name = "ToAddress")
    protected AddressWSType toAddress;
    @XmlElement(name = "TrackingNumber")
    protected String trackingNumber;
    @XmlElement(name = "OrderNumber")
    protected String orderNumber;
    @XmlElement(name = "PackageType", required = true)
    protected String packageType;
    @XmlElement(name = "Packages", required = true)
    protected PackagesWSType packages;
    @XmlElement(name = "References")
    protected List<String> references;
    @XmlElement(name = "RequiredDeliveryDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar requiredDeliveryDate;
    @XmlElement(name = "ScheduledShipDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scheduledShipDate;
    @XmlElement(name = "Service")
    protected ServiceWSType service;
    @XmlElement(name = "Pickup")
    protected PickupWSType pickup;
    @XmlElement(name = "POD")
    protected PODWSType pod;
    @XmlElement(name = "ShippingLabel", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] shippingLabel;
    @XmlElement(name = "CustomsInvoice")
    protected CustomsInvoiceWSType customsInvoice;
    @XmlElement(name = "TrackingURL")
    protected String trackingURL;

    /**
     * Gets the value of the orderID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrderID() {
        return orderID;
    }

    /**
     * Sets the value of the orderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrderID(Long value) {
        this.orderID = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the carrier property.
     * 
     * @return
     *     possible object is
     *     {@link CarrierWSType }
     *     
     */
    public CarrierWSType getCarrier() {
        return carrier;
    }

    /**
     * Sets the value of the carrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarrierWSType }
     *     
     */
    public void setCarrier(CarrierWSType value) {
        this.carrier = value;
    }

    /**
     * Gets the value of the charges property.
     * 
     * @return
     *     possible object is
     *     {@link ChargesWSType }
     *     
     */
    public ChargesWSType getCharges() {
        return charges;
    }

    /**
     * Sets the value of the charges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargesWSType }
     *     
     */
    public void setCharges(ChargesWSType value) {
        this.charges = value;
    }

    /**
     * Gets the value of the accessorial property.
     * 
     * @return
     *     possible object is
     *     {@link AccessorialWSType }
     *     
     */
    public AccessorialWSType getAccessorial() {
        return accessorial;
    }

    /**
     * Sets the value of the accessorial property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessorialWSType }
     *     
     */
    public void setAccessorial(AccessorialWSType value) {
        this.accessorial = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusWSType }
     *     
     */
    public StatusWSType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusWSType }
     *     
     */
    public void setStatus(StatusWSType value) {
        this.status = value;
    }

    /**
     * Gets the value of the quotedWeight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightWSType }
     *     
     */
    public WeightWSType getQuotedWeight() {
        return quotedWeight;
    }

    /**
     * Sets the value of the quotedWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightWSType }
     *     
     */
    public void setQuotedWeight(WeightWSType value) {
        this.quotedWeight = value;
    }

    /**
     * Gets the value of the billedWeight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightWSType }
     *     
     */
    public WeightWSType getBilledWeight() {
        return billedWeight;
    }

    /**
     * Sets the value of the billedWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightWSType }
     *     
     */
    public void setBilledWeight(WeightWSType value) {
        this.billedWeight = value;
    }

    /**
     * Gets the value of the dutiableAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public BigDecimal getDutiableAmount() {
        return dutiableAmount;
    }

    /**
     * Sets the value of the dutiableAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDutiableAmount(BigDecimal value) {
        this.dutiableAmount = value;
    }

    /**
     * Gets the value of the fromAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressWSType }
     *     
     */
    public AddressWSType getFromAddress() {
        return fromAddress;
    }

    /**
     * Sets the value of the fromAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressWSType }
     *     
     */
    public void setFromAddress(AddressWSType value) {
        this.fromAddress = value;
    }

    /**
     * Gets the value of the toAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressWSType }
     *     
     */
    public AddressWSType getToAddress() {
        return toAddress;
    }

    /**
     * Sets the value of the toAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressWSType }
     *     
     */
    public void setToAddress(AddressWSType value) {
        this.toAddress = value;
    }

    /**
     * Gets the value of the trackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Sets the value of the trackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNumber(String value) {
        this.trackingNumber = value;
    }

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the packageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageType() {
        return packageType;
    }

    /**
     * Sets the value of the packageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageType(String value) {
        this.packageType = value;
    }

    /**
     * Gets the value of the packages property.
     * 
     * @return
     *     possible object is
     *     {@link PackagesWSType }
     *     
     */
    public PackagesWSType getPackages() {
        return packages;
    }

    /**
     * Sets the value of the packages property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackagesWSType }
     *     
     */
    public void setPackages(PackagesWSType value) {
        this.packages = value;
    }

    /**
     * Gets the value of the references property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the references property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getReferences() {
        if (references == null) {
            references = new ArrayList<String>();
        }
        return this.references;
    }

    /**
     * Gets the value of the requiredDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequiredDeliveryDate() {
        return requiredDeliveryDate;
    }

    /**
     * Sets the value of the requiredDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequiredDeliveryDate(XMLGregorianCalendar value) {
        this.requiredDeliveryDate = value;
    }

    /**
     * Gets the value of the scheduledShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduledShipDate() {
        return scheduledShipDate;
    }

    /**
     * Sets the value of the scheduledShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduledShipDate(XMLGregorianCalendar value) {
        this.scheduledShipDate = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceWSType }
     *     
     */
    public ServiceWSType getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceWSType }
     *     
     */
    public void setService(ServiceWSType value) {
        this.service = value;
    }

    /**
     * Gets the value of the pickup property.
     * 
     * @return
     *     possible object is
     *     {@link PickupWSType }
     *     
     */
    public PickupWSType getPickup() {
        return pickup;
    }

    /**
     * Sets the value of the pickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupWSType }
     *     
     */
    public void setPickup(PickupWSType value) {
        this.pickup = value;
    }

    /**
     * Gets the value of the pod property.
     * 
     * @return
     *     possible object is
     *     {@link PODWSType }
     *     
     */
    public PODWSType getPOD() {
        return pod;
    }

    /**
     * Sets the value of the pod property.
     * 
     * @param value
     *     allowed object is
     *     {@link PODWSType }
     *     
     */
    public void setPOD(PODWSType value) {
        this.pod = value;
    }

    /**
     * Gets the value of the shippingLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getShippingLabel() {
        return shippingLabel;
    }

    /**
     * Sets the value of the shippingLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingLabel(byte[] value) {
        this.shippingLabel = value;
    }

    /**
     * Gets the value of the customsInvoice property.
     * 
     * @return
     *     possible object is
     *     {@link CustomsInvoiceWSType }
     *     
     */
    public CustomsInvoiceWSType getCustomsInvoice() {
        return customsInvoice;
    }

    /**
     * Sets the value of the customsInvoice property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomsInvoiceWSType }
     *     
     */
    public void setCustomsInvoice(CustomsInvoiceWSType value) {
        this.customsInvoice = value;
    }

    /**
     * Gets the value of the trackingURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingURL() {
        return trackingURL;
    }

    /**
     * Sets the value of the trackingURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingURL(String value) {
        this.trackingURL = value;
    }

}
