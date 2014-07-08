
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeCardType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeCardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CardHolderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CardNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SecurityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CardAddress" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}ChargeCardAddressType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeCardType", propOrder = {
    "cardHolderName",
    "cardType",
    "cardNumber",
    "expirationDate",
    "securityCode",
    "cardAddress"
})
public class ChargeCardType {

    @XmlElement(name = "CardHolderName")
    protected String cardHolderName;
    @XmlElement(name = "CardType", required = true)
    protected String cardType;
    @XmlElement(name = "CardNumber", required = true)
    protected String cardNumber;
    @XmlElement(name = "ExpirationDate", required = true)
    protected String expirationDate;
    @XmlElement(name = "SecurityCode", required = true)
    protected String securityCode;
    @XmlElement(name = "CardAddress", required = true)
    protected ChargeCardAddressType cardAddress;

    /**
     * Gets the value of the cardHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Sets the value of the cardHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolderName(String value) {
        this.cardHolderName = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardType(String value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the securityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * Sets the value of the securityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityCode(String value) {
        this.securityCode = value;
    }

    /**
     * Gets the value of the cardAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeCardAddressType }
     *     
     */
    public ChargeCardAddressType getCardAddress() {
        return cardAddress;
    }

    /**
     * Sets the value of the cardAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeCardAddressType }
     *     
     */
    public void setCardAddress(ChargeCardAddressType value) {
        this.cardAddress = value;
    }

}
