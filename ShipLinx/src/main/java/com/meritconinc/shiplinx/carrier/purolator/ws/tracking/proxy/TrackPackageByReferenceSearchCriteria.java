
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * TrackPackageByReferenceSearchCriteria
 * 
 * <p>Java class for TrackPackageByReferenceSearchCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrackPackageByReferenceSearchCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DestinationPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestinationCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentFromDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipmentToDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackPackageByReferenceSearchCriteria", propOrder = {
    "reference",
    "destinationPostalCode",
    "destinationCountryCode",
    "billingAccountNumber",
    "shipmentFromDate",
    "shipmentToDate"
})
public class TrackPackageByReferenceSearchCriteria {

    @XmlElement(name = "Reference", required = true, nillable = true)
    protected String reference;
    @XmlElementRef(name = "DestinationPostalCode", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> destinationPostalCode;
    @XmlElementRef(name = "DestinationCountryCode", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> destinationCountryCode;
    @XmlElementRef(name = "BillingAccountNumber", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> billingAccountNumber;
    @XmlElement(name = "ShipmentFromDate", required = true, nillable = true)
    protected String shipmentFromDate;
    @XmlElement(name = "ShipmentToDate", required = true, nillable = true)
    protected String shipmentToDate;

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the destinationPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestinationPostalCode() {
        return destinationPostalCode;
    }

    /**
     * Sets the value of the destinationPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestinationPostalCode(JAXBElement<String> value) {
        this.destinationPostalCode = value;
    }

    /**
     * Gets the value of the destinationCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestinationCountryCode() {
        return destinationCountryCode;
    }

    /**
     * Sets the value of the destinationCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestinationCountryCode(JAXBElement<String> value) {
        this.destinationCountryCode = value;
    }

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBillingAccountNumber(JAXBElement<String> value) {
        this.billingAccountNumber = value;
    }

    /**
     * Gets the value of the shipmentFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentFromDate() {
        return shipmentFromDate;
    }

    /**
     * Sets the value of the shipmentFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentFromDate(String value) {
        this.shipmentFromDate = value;
    }

    /**
     * Gets the value of the shipmentToDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentToDate() {
        return shipmentToDate;
    }

    /**
     * Sets the value of the shipmentToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentToDate(String value) {
        this.shipmentToDate = value;
    }

}
