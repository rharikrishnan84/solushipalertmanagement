
package com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransitResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransitResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipFrom" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}ResponseShipFromType"/>
 *         &lt;element name="ShipTo" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}ResponseShipToType"/>
 *         &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipmentWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}ShipmentWeightType" minOccurs="0"/>
 *         &lt;element name="InvoiceLineTotal" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}InvoiceLineTotalType" minOccurs="0"/>
 *         &lt;element name="DocumentsOnlyIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaximumListSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceSummary" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}ServiceSummaryType" maxOccurs="unbounded"/>
 *         &lt;element name="AutoDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Disclaimer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransitResponseType", propOrder = {
    "shipFrom",
    "shipTo",
    "pickupDate",
    "shipmentWeight",
    "invoiceLineTotal",
    "documentsOnlyIndicator",
    "maximumListSize",
    "serviceSummary",
    "autoDutyCode",
    "disclaimer"
})
public class TransitResponseType {

    @XmlElement(name = "ShipFrom", required = true)
    protected ResponseShipFromType shipFrom;
    @XmlElement(name = "ShipTo", required = true)
    protected ResponseShipToType shipTo;
    @XmlElement(name = "PickupDate", required = true)
    protected String pickupDate;
    @XmlElement(name = "ShipmentWeight")
    protected ShipmentWeightType shipmentWeight;
    @XmlElement(name = "InvoiceLineTotal")
    protected InvoiceLineTotalType invoiceLineTotal;
    @XmlElement(name = "DocumentsOnlyIndicator")
    protected String documentsOnlyIndicator;
    @XmlElement(name = "MaximumListSize")
    protected String maximumListSize;
    @XmlElement(name = "ServiceSummary", required = true)
    protected List<ServiceSummaryType> serviceSummary;
    @XmlElement(name = "AutoDutyCode")
    protected String autoDutyCode;
    @XmlElement(name = "Disclaimer")
    protected String disclaimer;

    /**
     * Gets the value of the shipFrom property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseShipFromType }
     *     
     */
    public ResponseShipFromType getShipFrom() {
        return shipFrom;
    }

    /**
     * Sets the value of the shipFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseShipFromType }
     *     
     */
    public void setShipFrom(ResponseShipFromType value) {
        this.shipFrom = value;
    }

    /**
     * Gets the value of the shipTo property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseShipToType }
     *     
     */
    public ResponseShipToType getShipTo() {
        return shipTo;
    }

    /**
     * Sets the value of the shipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseShipToType }
     *     
     */
    public void setShipTo(ResponseShipToType value) {
        this.shipTo = value;
    }

    /**
     * Gets the value of the pickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupDate(String value) {
        this.pickupDate = value;
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

    /**
     * Gets the value of the serviceSummary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceSummary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceSummary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceSummaryType }
     * 
     * 
     */
    public List<ServiceSummaryType> getServiceSummary() {
        if (serviceSummary == null) {
            serviceSummary = new ArrayList<ServiceSummaryType>();
        }
        return this.serviceSummary;
    }

    /**
     * Gets the value of the autoDutyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoDutyCode() {
        return autoDutyCode;
    }

    /**
     * Sets the value of the autoDutyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoDutyCode(String value) {
        this.autoDutyCode = value;
    }

    /**
     * Gets the value of the disclaimer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisclaimer() {
        return disclaimer;
    }

    /**
     * Sets the value of the disclaimer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisclaimer(String value) {
        this.disclaimer = value;
    }

}
