
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipperType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipperType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Account" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}AccountType" minOccurs="0"/>
 *         &lt;element name="ChargeCard" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}ChargeCardType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipperType", propOrder = {
    "account",
    "chargeCard"
})
public class ShipperType {

    @XmlElement(name = "Account")
    protected AccountType account;
    @XmlElement(name = "ChargeCard")
    protected ChargeCardType chargeCard;

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link AccountType }
     *     
     */
    public AccountType getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountType }
     *     
     */
    public void setAccount(AccountType value) {
        this.account = value;
    }

    /**
     * Gets the value of the chargeCard property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeCardType }
     *     
     */
    public ChargeCardType getChargeCard() {
        return chargeCard;
    }

    /**
     * Sets the value of the chargeCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeCardType }
     *     
     */
    public void setChargeCard(ChargeCardType value) {
        this.chargeCard = value;
    }

}
