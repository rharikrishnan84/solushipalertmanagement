
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnService" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ReturnServiceType" minOccurs="0"/>
 *         &lt;element name="DocumentsOnlyIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Shipper" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipperType"/>
 *         &lt;element name="ShipTo" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipToType"/>
 *         &lt;element name="ShipFrom" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipFromType" minOccurs="0"/>
 *         &lt;element name="PaymentInformation" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PaymentInfoType" minOccurs="0"/>
 *         &lt;element name="FRSPaymentInformation" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}FRSPaymentInfoType" minOccurs="0"/>
 *         &lt;element name="GoodsNotInFreeCirculationIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentRatingOptions" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}RateInfoType" minOccurs="0"/>
 *         &lt;element name="MovementReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ReferenceNumberType" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="Service" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ServiceType"/>
 *         &lt;element name="InvoiceLineTotal" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}CurrencyMonetaryType" minOccurs="0"/>
 *         &lt;element name="ShipmentServiceOptions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipmentServiceOptionsType">
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Package" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PackageType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentType", propOrder = {
    "description",
    "returnService",
    "documentsOnlyIndicator",
    "shipper",
    "shipTo",
    "shipFrom",
    "paymentInformation",
    "frsPaymentInformation",
    "goodsNotInFreeCirculationIndicator",
    "shipmentRatingOptions",
    "movementReferenceNumber",
    "referenceNumber",
    "service",
    "invoiceLineTotal",
    "shipmentServiceOptions",
    "_package"
})
public class ShipmentType {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ReturnService")
    protected ReturnServiceType returnService;
    @XmlElement(name = "DocumentsOnlyIndicator")
    protected String documentsOnlyIndicator;
    @XmlElement(name = "Shipper", required = true)
    protected ShipperType shipper;
    @XmlElement(name = "ShipTo", required = true)
    protected ShipToType shipTo;
    @XmlElement(name = "ShipFrom")
    protected ShipFromType shipFrom;
    @XmlElement(name = "PaymentInformation")
    protected PaymentInfoType paymentInformation;
    @XmlElement(name = "FRSPaymentInformation")
    protected FRSPaymentInfoType frsPaymentInformation;
    @XmlElement(name = "GoodsNotInFreeCirculationIndicator")
    protected String goodsNotInFreeCirculationIndicator;
    @XmlElement(name = "ShipmentRatingOptions")
    protected RateInfoType shipmentRatingOptions;
    @XmlElement(name = "MovementReferenceNumber")
    protected String movementReferenceNumber;
    @XmlElement(name = "ReferenceNumber")
    protected List<ReferenceNumberType> referenceNumber;
    @XmlElement(name = "Service", required = true)
    protected ServiceType service;
    @XmlElement(name = "InvoiceLineTotal")
    protected CurrencyMonetaryType invoiceLineTotal;
    @XmlElement(name = "ShipmentServiceOptions")
    protected ShipmentType.ShipmentServiceOptions shipmentServiceOptions;
    @XmlElement(name = "Package", required = true)
    protected List<PackageType> _package;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the returnService property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnServiceType }
     *     
     */
    public ReturnServiceType getReturnService() {
        return returnService;
    }

    /**
     * Sets the value of the returnService property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnServiceType }
     *     
     */
    public void setReturnService(ReturnServiceType value) {
        this.returnService = value;
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
     * Gets the value of the shipper property.
     * 
     * @return
     *     possible object is
     *     {@link ShipperType }
     *     
     */
    public ShipperType getShipper() {
        return shipper;
    }

    /**
     * Sets the value of the shipper property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipperType }
     *     
     */
    public void setShipper(ShipperType value) {
        this.shipper = value;
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
     * Gets the value of the paymentInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentInfoType }
     *     
     */
    public PaymentInfoType getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * Sets the value of the paymentInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInfoType }
     *     
     */
    public void setPaymentInformation(PaymentInfoType value) {
        this.paymentInformation = value;
    }

    /**
     * Gets the value of the frsPaymentInformation property.
     * 
     * @return
     *     possible object is
     *     {@link FRSPaymentInfoType }
     *     
     */
    public FRSPaymentInfoType getFRSPaymentInformation() {
        return frsPaymentInformation;
    }

    /**
     * Sets the value of the frsPaymentInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FRSPaymentInfoType }
     *     
     */
    public void setFRSPaymentInformation(FRSPaymentInfoType value) {
        this.frsPaymentInformation = value;
    }

    /**
     * Gets the value of the goodsNotInFreeCirculationIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsNotInFreeCirculationIndicator() {
        return goodsNotInFreeCirculationIndicator;
    }

    /**
     * Sets the value of the goodsNotInFreeCirculationIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsNotInFreeCirculationIndicator(String value) {
        this.goodsNotInFreeCirculationIndicator = value;
    }

    /**
     * Gets the value of the shipmentRatingOptions property.
     * 
     * @return
     *     possible object is
     *     {@link RateInfoType }
     *     
     */
    public RateInfoType getShipmentRatingOptions() {
        return shipmentRatingOptions;
    }

    /**
     * Sets the value of the shipmentRatingOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateInfoType }
     *     
     */
    public void setShipmentRatingOptions(RateInfoType value) {
        this.shipmentRatingOptions = value;
    }

    /**
     * Gets the value of the movementReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovementReferenceNumber() {
        return movementReferenceNumber;
    }

    /**
     * Sets the value of the movementReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovementReferenceNumber(String value) {
        this.movementReferenceNumber = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceNumberType }
     * 
     * 
     */
    public List<ReferenceNumberType> getReferenceNumber() {
        if (referenceNumber == null) {
            referenceNumber = new ArrayList<ReferenceNumberType>();
        }
        return this.referenceNumber;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceType }
     *     
     */
    public ServiceType getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceType }
     *     
     */
    public void setService(ServiceType value) {
        this.service = value;
    }

    /**
     * Gets the value of the invoiceLineTotal property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyMonetaryType }
     *     
     */
    public CurrencyMonetaryType getInvoiceLineTotal() {
        return invoiceLineTotal;
    }

    /**
     * Sets the value of the invoiceLineTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyMonetaryType }
     *     
     */
    public void setInvoiceLineTotal(CurrencyMonetaryType value) {
        this.invoiceLineTotal = value;
    }

    /**
     * Gets the value of the shipmentServiceOptions property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentType.ShipmentServiceOptions }
     *     
     */
    public ShipmentType.ShipmentServiceOptions getShipmentServiceOptions() {
        return shipmentServiceOptions;
    }

    /**
     * Sets the value of the shipmentServiceOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentType.ShipmentServiceOptions }
     *     
     */
    public void setShipmentServiceOptions(ShipmentType.ShipmentServiceOptions value) {
        this.shipmentServiceOptions = value;
    }

    /**
     * Gets the value of the package property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the package property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageType }
     * 
     * 
     */
    public List<PackageType> getPackage() {
        if (_package == null) {
            _package = new ArrayList<PackageType>();
        }
        return this._package;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipmentServiceOptionsType">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ShipmentServiceOptions
        extends ShipmentServiceOptionsType
    {


    }

}
