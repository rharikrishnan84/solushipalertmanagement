
package com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy;

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
 *         &lt;element name="ShipFrom" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}RequestShipFromType"/>
 *         &lt;element name="ShipTo" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}RequestShipToType"/>
 *         &lt;element name="Pickup" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}PickupType"/>
 *         &lt;element name="ShipmentWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}ShipmentWeightType" minOccurs="0"/>
 *         &lt;element name="TotalPackagesInShipment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InvoiceLineTotal" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}InvoiceLineTotalType" minOccurs="0"/>
 *         &lt;element name="DocumentsOnlyIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaximumListSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pickup",
    "shipmentWeight",
    "totalPackagesInShipment",
    "invoiceLineTotal",
    "documentsOnlyIndicator",
    "maximumListSize"
})
@XmlRootElement(name = "TimeInTransitRequest")
public class TimeInTransitRequest {

    @XmlElement(name = "Request", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected RequestType request;
    @XmlElement(name = "ShipFrom", required = true)
    protected RequestShipFromType shipFrom;
    @XmlElement(name = "ShipTo", required = true)
    protected RequestShipToType shipTo;
    @XmlElement(name = "Pickup", required = true)
    protected PickupType pickup;
    @XmlElement(name = "ShipmentWeight")
    protected ShipmentWeightType shipmentWeight;
    @XmlElement(name = "TotalPackagesInShipment")
    protected String totalPackagesInShipment;
    @XmlElement(name = "InvoiceLineTotal")
    protected InvoiceLineTotalType invoiceLineTotal;
    @XmlElement(name = "DocumentsOnlyIndicator")
    protected String documentsOnlyIndicator;
    @XmlElement(name = "MaximumListSize")
    protected String maximumListSize;

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
     *     {@link RequestShipFromType }
     *     
     */
    public RequestShipFromType getShipFrom() {
        return shipFrom;
    }

    /**
     * Sets the value of the shipFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestShipFromType }
     *     
     */
    public void setShipFrom(RequestShipFromType value) {
        this.shipFrom = value;
    }

    /**
     * Gets the value of the shipTo property.
     * 
     * @return
     *     possible object is
     *     {@link RequestShipToType }
     *     
     */
    public RequestShipToType getShipTo() {
        return shipTo;
    }

    /**
     * Sets the value of the shipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestShipToType }
     *     
     */
    public void setShipTo(RequestShipToType value) {
        this.shipTo = value;
    }

    /**
     * Gets the value of the pickup property.
     * 
     * @return
     *     possible object is
     *     {@link PickupType }
     *     
     */
    public PickupType getPickup() {
        return pickup;
    }

    /**
     * Sets the value of the pickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupType }
     *     
     */
    public void setPickup(PickupType value) {
        this.pickup = value;
    }

    /**
     * Gets the value of the shipmentWeight property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentWeightType }
     *     
     */
    public ShipmentWeightType getShipmentWeight() {
        return shipmentWeight;
    }

    /**
     * Sets the value of the shipmentWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentWeightType }
     *     
     */
    public void setShipmentWeight(ShipmentWeightType value) {
        this.shipmentWeight = value;
    }

    /**
     * Gets the value of the totalPackagesInShipment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPackagesInShipment() {
        return totalPackagesInShipment;
    }

    /**
     * Sets the value of the totalPackagesInShipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPackagesInShipment(String value) {
        this.totalPackagesInShipment = value;
    }

    /**
     * Gets the value of the invoiceLineTotal property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceLineTotalType }
     *     
     */
    public InvoiceLineTotalType getInvoiceLineTotal() {
        return invoiceLineTotal;
    }

    /**
     * Sets the value of the invoiceLineTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceLineTotalType }
     *     
     */
    public void setInvoiceLineTotal(InvoiceLineTotalType value) {
        this.invoiceLineTotal = value;
    }

    /**
     * Gets the value of the documentsOnlyIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentsOnlyIndicator() {
        return documentsOnlyIndicator;
    }

    /**
     * Sets the value of the documentsOnlyIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentsOnlyIndicator(String value) {
        this.documentsOnlyIndicator = value;
    }

    /**
     * Gets the value of the maximumListSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaximumListSize() {
        return maximumListSize;
    }

    /**
     * Sets the value of the maximumListSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaximumListSize(String value) {
        this.maximumListSize = value;
    }

}
