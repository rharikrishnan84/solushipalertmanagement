
package com.meritconinc.shiplinx.ws.proxy.datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccessorialWSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessorialWSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SaturdayDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HoldForPickupRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SaturdayPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="COD" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}CODWSType" minOccurs="0"/>
 *         &lt;element name="NotifyRecipient" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ConfirmDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DangerousGoods" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="YES"/>
 *               &lt;enumeration value="SOME"/>
 *               &lt;enumeration value="NONE"/>
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="HoldForPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DeliveryTailgate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PickupTailgate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="InsideDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SignatureRequired" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="YES"/>
 *               &lt;enumeration value="NO"/>
 *               &lt;enumeration value="ADULT"/>
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SpecialEquipment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessorialWSType", propOrder = {
    "saturdayDelivery",
    "holdForPickupRequired",
    "saturdayPickup",
    "cod",
    "notifyRecipient",
    "confirmDelivery",
    "dangerousGoods",
    "holdForPickup",
    "deliveryTailgate",
    "pickupTailgate",
    "insideDelivery",
    "signatureRequired",
    "specialEquipment"
})
public class AccessorialWSType {

    @XmlElement(name = "SaturdayDelivery")
    protected Boolean saturdayDelivery;
    @XmlElement(name = "HoldForPickupRequired")
    protected Boolean holdForPickupRequired;
    @XmlElement(name = "SaturdayPickup")
    protected Boolean saturdayPickup;
    @XmlElement(name = "COD")
    protected CODWSType cod;
    @XmlElement(name = "NotifyRecipient")
    protected Boolean notifyRecipient;
    @XmlElement(name = "ConfirmDelivery")
    protected Boolean confirmDelivery;
    @XmlElement(name = "DangerousGoods")
    protected String dangerousGoods;
    @XmlElement(name = "HoldForPickup")
    protected Boolean holdForPickup;
    @XmlElement(name = "DeliveryTailgate")
    protected Boolean deliveryTailgate;
    @XmlElement(name = "PickupTailgate")
    protected Boolean pickupTailgate;
    @XmlElement(name = "InsideDelivery")
    protected Boolean insideDelivery;
    @XmlElement(name = "SignatureRequired")
    protected String signatureRequired;
    @XmlElement(name = "SpecialEquipment")
    protected Boolean specialEquipment;

    /**
     * Gets the value of the saturdayDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSaturdayDelivery() {
        return saturdayDelivery;
    }

    /**
     * Sets the value of the saturdayDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSaturdayDelivery(Boolean value) {
        this.saturdayDelivery = value;
    }

    /**
     * Gets the value of the holdForPickupRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHoldForPickupRequired() {
        return holdForPickupRequired;
    }

    /**
     * Sets the value of the holdForPickupRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHoldForPickupRequired(Boolean value) {
        this.holdForPickupRequired = value;
    }

    /**
     * Gets the value of the saturdayPickup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSaturdayPickup() {
        return saturdayPickup;
    }

    /**
     * Sets the value of the saturdayPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSaturdayPickup(Boolean value) {
        this.saturdayPickup = value;
    }

    /**
     * Gets the value of the cod property.
     * 
     * @return
     *     possible object is
     *     {@link CODWSType }
     *     
     */
    public CODWSType getCOD() {
        return cod;
    }

    /**
     * Sets the value of the cod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CODWSType }
     *     
     */
    public void setCOD(CODWSType value) {
        this.cod = value;
    }

    /**
     * Gets the value of the notifyRecipient property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNotifyRecipient() {
        return notifyRecipient;
    }

    /**
     * Sets the value of the notifyRecipient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNotifyRecipient(Boolean value) {
        this.notifyRecipient = value;
    }

    /**
     * Gets the value of the confirmDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConfirmDelivery() {
        return confirmDelivery;
    }

    /**
     * Sets the value of the confirmDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConfirmDelivery(Boolean value) {
        this.confirmDelivery = value;
    }

    /**
     * Gets the value of the dangerousGoods property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDangerousGoods() {
        return dangerousGoods;
    }

    /**
     * Sets the value of the dangerousGoods property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDangerousGoods(String value) {
        this.dangerousGoods = value;
    }

    /**
     * Gets the value of the holdForPickup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHoldForPickup() {
        return holdForPickup;
    }

    /**
     * Sets the value of the holdForPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHoldForPickup(Boolean value) {
        this.holdForPickup = value;
    }

    /**
     * Gets the value of the deliveryTailgate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeliveryTailgate() {
        return deliveryTailgate;
    }

    /**
     * Sets the value of the deliveryTailgate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeliveryTailgate(Boolean value) {
        this.deliveryTailgate = value;
    }

    /**
     * Gets the value of the pickupTailgate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPickupTailgate() {
        return pickupTailgate;
    }

    /**
     * Sets the value of the pickupTailgate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPickupTailgate(Boolean value) {
        this.pickupTailgate = value;
    }

    /**
     * Gets the value of the insideDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInsideDelivery() {
        return insideDelivery;
    }

    /**
     * Sets the value of the insideDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInsideDelivery(Boolean value) {
        this.insideDelivery = value;
    }

    /**
     * Gets the value of the signatureRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureRequired() {
        return signatureRequired;
    }

    /**
     * Sets the value of the signatureRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureRequired(String value) {
        this.signatureRequired = value;
    }

    /**
     * Gets the value of the specialEquipment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSpecialEquipment() {
        return specialEquipment;
    }

    /**
     * Sets the value of the specialEquipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSpecialEquipment(Boolean value) {
        this.specialEquipment = value;
    }

}
