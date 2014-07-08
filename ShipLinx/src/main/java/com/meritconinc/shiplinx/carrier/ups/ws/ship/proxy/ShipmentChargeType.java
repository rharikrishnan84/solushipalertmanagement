
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentChargeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentChargeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BillShipper" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}BillShipperType" minOccurs="0"/>
 *         &lt;element name="BillReceiver" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}BillReceiverType" minOccurs="0"/>
 *         &lt;element name="BillThirdParty" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}BillThirdPartyChargeType" minOccurs="0"/>
 *         &lt;element name="ConsigneeBilledIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentChargeType", propOrder = {
    "type",
    "billShipper",
    "billReceiver",
    "billThirdParty",
    "consigneeBilledIndicator"
})
public class ShipmentChargeType {

    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "BillShipper")
    protected BillShipperType billShipper;
    @XmlElement(name = "BillReceiver")
    protected BillReceiverType billReceiver;
    @XmlElement(name = "BillThirdParty")
    protected BillThirdPartyChargeType billThirdParty;
    @XmlElement(name = "ConsigneeBilledIndicator")
    protected String consigneeBilledIndicator;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the billShipper property.
     * 
     * @return
     *     possible object is
     *     {@link BillShipperType }
     *     
     */
    public BillShipperType getBillShipper() {
        return billShipper;
    }

    /**
     * Sets the value of the billShipper property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillShipperType }
     *     
     */
    public void setBillShipper(BillShipperType value) {
        this.billShipper = value;
    }

    /**
     * Gets the value of the billReceiver property.
     * 
     * @return
     *     possible object is
     *     {@link BillReceiverType }
     *     
     */
    public BillReceiverType getBillReceiver() {
        return billReceiver;
    }

    /**
     * Sets the value of the billReceiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillReceiverType }
     *     
     */
    public void setBillReceiver(BillReceiverType value) {
        this.billReceiver = value;
    }

    /**
     * Gets the value of the billThirdParty property.
     * 
     * @return
     *     possible object is
     *     {@link BillThirdPartyChargeType }
     *     
     */
    public BillThirdPartyChargeType getBillThirdParty() {
        return billThirdParty;
    }

    /**
     * Sets the value of the billThirdParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillThirdPartyChargeType }
     *     
     */
    public void setBillThirdParty(BillThirdPartyChargeType value) {
        this.billThirdParty = value;
    }

    /**
     * Gets the value of the consigneeBilledIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeBilledIndicator() {
        return consigneeBilledIndicator;
    }

    /**
     * Sets the value of the consigneeBilledIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeBilledIndicator(String value) {
        this.consigneeBilledIndicator = value;
    }

}
