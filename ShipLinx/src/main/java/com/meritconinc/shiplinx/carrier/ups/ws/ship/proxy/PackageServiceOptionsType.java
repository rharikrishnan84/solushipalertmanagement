
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackageServiceOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageServiceOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeliveryConfirmation" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}DeliveryConfirmationType" minOccurs="0"/>
 *         &lt;element name="DeclaredValue" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PackageDeclaredValueType" minOccurs="0"/>
 *         &lt;element name="COD" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PSOCODType" minOccurs="0"/>
 *         &lt;element name="VerbalConfirmation" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}VerbalConfirmationType" minOccurs="0"/>
 *         &lt;element name="ShipperReleaseIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notification" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PSONotificationType" minOccurs="0"/>
 *         &lt;element name="ReturnsFlexibleAccessIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DryIce" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}DryIceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageServiceOptionsType", propOrder = {
    "deliveryConfirmation",
    "declaredValue",
    "cod",
    "verbalConfirmation",
    "shipperReleaseIndicator",
    "notification",
    "returnsFlexibleAccessIndicator",
    "dryIce"
})
public class PackageServiceOptionsType {

    @XmlElement(name = "DeliveryConfirmation")
    protected DeliveryConfirmationType deliveryConfirmation;
    @XmlElement(name = "DeclaredValue")
    protected PackageDeclaredValueType declaredValue;
    @XmlElement(name = "COD")
    protected PSOCODType cod;
    @XmlElement(name = "VerbalConfirmation")
    protected VerbalConfirmationType verbalConfirmation;
    @XmlElement(name = "ShipperReleaseIndicator")
    protected String shipperReleaseIndicator;
    @XmlElement(name = "Notification")
    protected PSONotificationType notification;
    @XmlElement(name = "ReturnsFlexibleAccessIndicator")
    protected String returnsFlexibleAccessIndicator;
    @XmlElement(name = "DryIce")
    protected DryIceType dryIce;

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
     * Gets the value of the declaredValue property.
     * 
     * @return
     *     possible object is
     *     {@link PackageDeclaredValueType }
     *     
     */
    public PackageDeclaredValueType getDeclaredValue() {
        return declaredValue;
    }

    /**
     * Sets the value of the declaredValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageDeclaredValueType }
     *     
     */
    public void setDeclaredValue(PackageDeclaredValueType value) {
        this.declaredValue = value;
    }

    /**
     * Gets the value of the cod property.
     * 
     * @return
     *     possible object is
     *     {@link PSOCODType }
     *     
     */
    public PSOCODType getCOD() {
        return cod;
    }

    /**
     * Sets the value of the cod property.
     * 
     * @param value
     *     allowed object is
     *     {@link PSOCODType }
     *     
     */
    public void setCOD(PSOCODType value) {
        this.cod = value;
    }

    /**
     * Gets the value of the verbalConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link VerbalConfirmationType }
     *     
     */
    public VerbalConfirmationType getVerbalConfirmation() {
        return verbalConfirmation;
    }

    /**
     * Sets the value of the verbalConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerbalConfirmationType }
     *     
     */
    public void setVerbalConfirmation(VerbalConfirmationType value) {
        this.verbalConfirmation = value;
    }

    /**
     * Gets the value of the shipperReleaseIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperReleaseIndicator() {
        return shipperReleaseIndicator;
    }

    /**
     * Sets the value of the shipperReleaseIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperReleaseIndicator(String value) {
        this.shipperReleaseIndicator = value;
    }

    /**
     * Gets the value of the notification property.
     * 
     * @return
     *     possible object is
     *     {@link PSONotificationType }
     *     
     */
    public PSONotificationType getNotification() {
        return notification;
    }

    /**
     * Sets the value of the notification property.
     * 
     * @param value
     *     allowed object is
     *     {@link PSONotificationType }
     *     
     */
    public void setNotification(PSONotificationType value) {
        this.notification = value;
    }

    /**
     * Gets the value of the returnsFlexibleAccessIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnsFlexibleAccessIndicator() {
        return returnsFlexibleAccessIndicator;
    }

    /**
     * Sets the value of the returnsFlexibleAccessIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnsFlexibleAccessIndicator(String value) {
        this.returnsFlexibleAccessIndicator = value;
    }

    /**
     * Gets the value of the dryIce property.
     * 
     * @return
     *     possible object is
     *     {@link DryIceType }
     *     
     */
    public DryIceType getDryIce() {
        return dryIce;
    }

    /**
     * Sets the value of the dryIce property.
     * 
     * @param value
     *     allowed object is
     *     {@link DryIceType }
     *     
     */
    public void setDryIce(DryIceType value) {
        this.dryIce = value;
    }

}
