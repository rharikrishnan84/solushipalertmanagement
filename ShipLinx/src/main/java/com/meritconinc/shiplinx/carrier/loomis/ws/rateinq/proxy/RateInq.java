
package com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipperAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FromPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pieces" type="{http://tempuri.org/RATEINQ/Service1}ArrayOfPiece" minOccurs="0"/>
 *         &lt;element name="ValuationAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="IsNonPack" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsDangerousGood" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsSaturdayDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsFragile" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsResidential" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsDutiable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsDTP" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "loginName",
    "password",
    "shipperAccountNumber",
    "fromPostalCode",
    "toPostalCode",
    "toCity",
    "toProvinceCode",
    "toCountryCode",
    "serviceType",
    "pickupDate",
    "uom",
    "pieces",
    "valuationAmount",
    "isNonPack",
    "isDangerousGood",
    "isSaturdayDelivery",
    "isFragile",
    "isResidential",
    "isDutiable",
    "isDTP",
    "language"
})
@XmlRootElement(name = "RateInq")
public class RateInq {

    @XmlElement(name = "LoginName")
    protected String loginName;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "ShipperAccountNumber")
    protected String shipperAccountNumber;
    @XmlElement(name = "FromPostalCode")
    protected String fromPostalCode;
    @XmlElement(name = "ToPostalCode")
    protected String toPostalCode;
    @XmlElement(name = "ToCity")
    protected String toCity;
    @XmlElement(name = "ToProvinceCode")
    protected String toProvinceCode;
    @XmlElement(name = "ToCountryCode")
    protected String toCountryCode;
    @XmlElement(name = "ServiceType")
    protected String serviceType;
    @XmlElement(name = "PickupDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickupDate;
    @XmlElement(name = "UOM")
    protected String uom;
    @XmlElement(name = "Pieces")
    protected ArrayOfPiece pieces;
    @XmlElement(name = "ValuationAmount")
    protected double valuationAmount;
    @XmlElement(name = "IsNonPack")
    protected boolean isNonPack;
    @XmlElement(name = "IsDangerousGood")
    protected boolean isDangerousGood;
    @XmlElement(name = "IsSaturdayDelivery")
    protected boolean isSaturdayDelivery;
    @XmlElement(name = "IsFragile")
    protected boolean isFragile;
    @XmlElement(name = "IsResidential")
    protected boolean isResidential;
    @XmlElement(name = "IsDutiable")
    protected boolean isDutiable;
    @XmlElement(name = "IsDTP")
    protected boolean isDTP;
    @XmlElement(name = "Language")
    protected String language;

    /**
     * Gets the value of the loginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets the value of the loginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginName(String value) {
        this.loginName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the shipperAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperAccountNumber() {
        return shipperAccountNumber;
    }

    /**
     * Sets the value of the shipperAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperAccountNumber(String value) {
        this.shipperAccountNumber = value;
    }

    /**
     * Gets the value of the fromPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromPostalCode() {
        return fromPostalCode;
    }

    /**
     * Sets the value of the fromPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromPostalCode(String value) {
        this.fromPostalCode = value;
    }

    /**
     * Gets the value of the toPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToPostalCode() {
        return toPostalCode;
    }

    /**
     * Sets the value of the toPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToPostalCode(String value) {
        this.toPostalCode = value;
    }

    /**
     * Gets the value of the toCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToCity() {
        return toCity;
    }

    /**
     * Sets the value of the toCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToCity(String value) {
        this.toCity = value;
    }

    /**
     * Gets the value of the toProvinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToProvinceCode() {
        return toProvinceCode;
    }

    /**
     * Sets the value of the toProvinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToProvinceCode(String value) {
        this.toProvinceCode = value;
    }

    /**
     * Gets the value of the toCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToCountryCode() {
        return toCountryCode;
    }

    /**
     * Sets the value of the toCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToCountryCode(String value) {
        this.toCountryCode = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the pickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPickupDate(XMLGregorianCalendar value) {
        this.pickupDate = value;
    }

    /**
     * Gets the value of the uom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOM() {
        return uom;
    }

    /**
     * Sets the value of the uom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOM(String value) {
        this.uom = value;
    }

    /**
     * Gets the value of the pieces property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPiece }
     *     
     */
    public ArrayOfPiece getPieces() {
        return pieces;
    }

    /**
     * Sets the value of the pieces property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPiece }
     *     
     */
    public void setPieces(ArrayOfPiece value) {
        this.pieces = value;
    }

    /**
     * Gets the value of the valuationAmount property.
     * 
     */
    public double getValuationAmount() {
        return valuationAmount;
    }

    /**
     * Sets the value of the valuationAmount property.
     * 
     */
    public void setValuationAmount(double value) {
        this.valuationAmount = value;
    }

    /**
     * Gets the value of the isNonPack property.
     * 
     */
    public boolean isIsNonPack() {
        return isNonPack;
    }

    /**
     * Sets the value of the isNonPack property.
     * 
     */
    public void setIsNonPack(boolean value) {
        this.isNonPack = value;
    }

    /**
     * Gets the value of the isDangerousGood property.
     * 
     */
    public boolean isIsDangerousGood() {
        return isDangerousGood;
    }

    /**
     * Sets the value of the isDangerousGood property.
     * 
     */
    public void setIsDangerousGood(boolean value) {
        this.isDangerousGood = value;
    }

    /**
     * Gets the value of the isSaturdayDelivery property.
     * 
     */
    public boolean isIsSaturdayDelivery() {
        return isSaturdayDelivery;
    }

    /**
     * Sets the value of the isSaturdayDelivery property.
     * 
     */
    public void setIsSaturdayDelivery(boolean value) {
        this.isSaturdayDelivery = value;
    }

    /**
     * Gets the value of the isFragile property.
     * 
     */
    public boolean isIsFragile() {
        return isFragile;
    }

    /**
     * Sets the value of the isFragile property.
     * 
     */
    public void setIsFragile(boolean value) {
        this.isFragile = value;
    }

    /**
     * Gets the value of the isResidential property.
     * 
     */
    public boolean isIsResidential() {
        return isResidential;
    }

    /**
     * Sets the value of the isResidential property.
     * 
     */
    public void setIsResidential(boolean value) {
        this.isResidential = value;
    }

    /**
     * Gets the value of the isDutiable property.
     * 
     */
    public boolean isIsDutiable() {
        return isDutiable;
    }

    /**
     * Sets the value of the isDutiable property.
     * 
     */
    public void setIsDutiable(boolean value) {
        this.isDutiable = value;
    }

    /**
     * Gets the value of the isDTP property.
     * 
     */
    public boolean isIsDTP() {
        return isDTP;
    }

    /**
     * Sets the value of the isDTP property.
     * 
     */
    public void setIsDTP(boolean value) {
        this.isDTP = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

}
