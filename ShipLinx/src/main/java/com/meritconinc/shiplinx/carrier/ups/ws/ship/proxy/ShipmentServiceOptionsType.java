
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentServiceOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentServiceOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SaturdayDeliveryIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COD" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}CODType" minOccurs="0"/>
 *         &lt;element name="Notification" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}NotificationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LabelDelivery" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}LabelDeliveryType" minOccurs="0"/>
 *         &lt;element name="InternationalForms" type="{http://www.ups.com/XMLSchema/XOLTWS/IF/v1.0}InternationalFormType" minOccurs="0"/>
 *         &lt;element name="DeliveryConfirmation" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}DeliveryConfirmationType" minOccurs="0"/>
 *         &lt;element name="ReturnOfDocumentIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImportControlIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LabelMethod" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}LabelMethodType" minOccurs="0"/>
 *         &lt;element name="CommercialInvoiceRemovalIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UPScarbonneutralIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PreAlertNotification" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PreAlertNotificationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ExchangeForwardIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentServiceOptionsType", propOrder = {
    "saturdayDeliveryIndicator",
    "cod",
    "notification",
    "labelDelivery",
    "internationalForms",
    "deliveryConfirmation",
    "returnOfDocumentIndicator",
    "importControlIndicator",
    "labelMethod",
    "commercialInvoiceRemovalIndicator",
    "upScarbonneutralIndicator",
    "preAlertNotification",
    "exchangeForwardIndicator"
})
@XmlSeeAlso({
    com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentType.ShipmentServiceOptions.class
})
public class ShipmentServiceOptionsType {

    @XmlElement(name = "SaturdayDeliveryIndicator")
    protected String saturdayDeliveryIndicator;
    @XmlElement(name = "COD")
    protected CODType cod;
    @XmlElement(name = "Notification")
    protected List<NotificationType> notification;
    @XmlElement(name = "LabelDelivery")
    protected LabelDeliveryType labelDelivery;
    @XmlElement(name = "InternationalForms")
    protected InternationalFormType internationalForms;
    @XmlElement(name = "DeliveryConfirmation")
    protected DeliveryConfirmationType deliveryConfirmation;
    @XmlElement(name = "ReturnOfDocumentIndicator")
    protected String returnOfDocumentIndicator;
    @XmlElement(name = "ImportControlIndicator")
    protected String importControlIndicator;
    @XmlElement(name = "LabelMethod")
    protected LabelMethodType labelMethod;
    @XmlElement(name = "CommercialInvoiceRemovalIndicator")
    protected String commercialInvoiceRemovalIndicator;
    @XmlElement(name = "UPScarbonneutralIndicator")
    protected String upScarbonneutralIndicator;
    @XmlElement(name = "PreAlertNotification")
    protected List<PreAlertNotificationType> preAlertNotification;
    @XmlElement(name = "ExchangeForwardIndicator")
    protected String exchangeForwardIndicator;

    /**
     * Gets the value of the saturdayDeliveryIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaturdayDeliveryIndicator() {
        return saturdayDeliveryIndicator;
    }

    /**
     * Sets the value of the saturdayDeliveryIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaturdayDeliveryIndicator(String value) {
        this.saturdayDeliveryIndicator = value;
    }

    /**
     * Gets the value of the cod property.
     * 
     * @return
     *     possible object is
     *     {@link CODType }
     *     
     */
    public CODType getCOD() {
        return cod;
    }

    /**
     * Sets the value of the cod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CODType }
     *     
     */
    public void setCOD(CODType value) {
        this.cod = value;
    }

    /**
     * Gets the value of the notification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NotificationType }
     * 
     * 
     */
    public List<NotificationType> getNotification() {
        if (notification == null) {
            notification = new ArrayList<NotificationType>();
        }
        return this.notification;
    }

    /**
     * Gets the value of the labelDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link LabelDeliveryType }
     *     
     */
    public LabelDeliveryType getLabelDelivery() {
        return labelDelivery;
    }

    /**
     * Sets the value of the labelDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelDeliveryType }
     *     
     */
    public void setLabelDelivery(LabelDeliveryType value) {
        this.labelDelivery = value;
    }

    /**
     * Gets the value of the internationalForms property.
     * 
     * @return
     *     possible object is
     *     {@link InternationalFormType }
     *     
     */
    public InternationalFormType getInternationalForms() {
        return internationalForms;
    }

    /**
     * Sets the value of the internationalForms property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternationalFormType }
     *     
     */
    public void setInternationalForms(InternationalFormType value) {
        this.internationalForms = value;
    }

    /**
     * Gets the value of the deliveryConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryConfirmationType }
     *     
     */
    public DeliveryConfirmationType getDeliveryConfirmation() {
        return deliveryConfirmation;
    }

    /**
     * Sets the value of the deliveryConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryConfirmationType }
     *     
     */
    public void setDeliveryConfirmation(DeliveryConfirmationType value) {
        this.deliveryConfirmation = value;
    }

    /**
     * Gets the value of the returnOfDocumentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnOfDocumentIndicator() {
        return returnOfDocumentIndicator;
    }

    /**
     * Sets the value of the returnOfDocumentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnOfDocumentIndicator(String value) {
        this.returnOfDocumentIndicator = value;
    }

    /**
     * Gets the value of the importControlIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportControlIndicator() {
        return importControlIndicator;
    }

    /**
     * Sets the value of the importControlIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportControlIndicator(String value) {
        this.importControlIndicator = value;
    }

    /**
     * Gets the value of the labelMethod property.
     * 
     * @return
     *     possible object is
     *     {@link LabelMethodType }
     *     
     */
    public LabelMethodType getLabelMethod() {
        return labelMethod;
    }

    /**
     * Sets the value of the labelMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelMethodType }
     *     
     */
    public void setLabelMethod(LabelMethodType value) {
        this.labelMethod = value;
    }

    /**
     * Gets the value of the commercialInvoiceRemovalIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommercialInvoiceRemovalIndicator() {
        return commercialInvoiceRemovalIndicator;
    }

    /**
     * Sets the value of the commercialInvoiceRemovalIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommercialInvoiceRemovalIndicator(String value) {
        this.commercialInvoiceRemovalIndicator = value;
    }

    /**
     * Gets the value of the upScarbonneutralIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPScarbonneutralIndicator() {
        return upScarbonneutralIndicator;
    }

    /**
     * Sets the value of the upScarbonneutralIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPScarbonneutralIndicator(String value) {
        this.upScarbonneutralIndicator = value;
    }

    /**
     * Gets the value of the preAlertNotification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preAlertNotification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreAlertNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreAlertNotificationType }
     * 
     * 
     */
    public List<PreAlertNotificationType> getPreAlertNotification() {
        if (preAlertNotification == null) {
            preAlertNotification = new ArrayList<PreAlertNotificationType>();
        }
        return this.preAlertNotification;
    }

    /**
     * Gets the value of the exchangeForwardIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeForwardIndicator() {
        return exchangeForwardIndicator;
    }

    /**
     * Sets the value of the exchangeForwardIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeForwardIndicator(String value) {
        this.exchangeForwardIndicator = value;
    }

}
