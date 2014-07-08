
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackageResultsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageResultsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceOptionsCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipChargeType" minOccurs="0"/>
 *         &lt;element name="ShippingLabel" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}LabelType" minOccurs="0"/>
 *         &lt;element name="ShippingReceipt" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ReceiptType" minOccurs="0"/>
 *         &lt;element name="USPSPICNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageResultsType", propOrder = {
    "trackingNumber",
    "serviceOptionsCharges",
    "shippingLabel",
    "shippingReceipt",
    "uspspicNumber"
})
public class PackageResultsType {

    @XmlElement(name = "TrackingNumber", required = true)
    protected String trackingNumber;
    @XmlElement(name = "ServiceOptionsCharges")
    protected ShipChargeType serviceOptionsCharges;
    @XmlElement(name = "ShippingLabel")
    protected LabelType shippingLabel;
    @XmlElement(name = "ShippingReceipt")
    protected ReceiptType shippingReceipt;
    @XmlElement(name = "USPSPICNumber")
    protected String uspspicNumber;

    /**
     * Gets the value of the trackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Sets the value of the trackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNumber(String value) {
        this.trackingNumber = value;
    }

    /**
     * Gets the value of the serviceOptionsCharges property.
     * 
     * @return
     *     possible object is
     *     {@link ShipChargeType }
     *     
     */
    public ShipChargeType getServiceOptionsCharges() {
        return serviceOptionsCharges;
    }

    /**
     * Sets the value of the serviceOptionsCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipChargeType }
     *     
     */
    public void setServiceOptionsCharges(ShipChargeType value) {
        this.serviceOptionsCharges = value;
    }

    /**
     * Gets the value of the shippingLabel property.
     * 
     * @return
     *     possible object is
     *     {@link LabelType }
     *     
     */
    public LabelType getShippingLabel() {
        return shippingLabel;
    }

    /**
     * Sets the value of the shippingLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelType }
     *     
     */
    public void setShippingLabel(LabelType value) {
        this.shippingLabel = value;
    }

    /**
     * Gets the value of the shippingReceipt property.
     * 
     * @return
     *     possible object is
     *     {@link ReceiptType }
     *     
     */
    public ReceiptType getShippingReceipt() {
        return shippingReceipt;
    }

    /**
     * Sets the value of the shippingReceipt property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceiptType }
     *     
     */
    public void setShippingReceipt(ReceiptType value) {
        this.shippingReceipt = value;
    }

    /**
     * Gets the value of the uspspicNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSPSPICNumber() {
        return uspspicNumber;
    }

    /**
     * Sets the value of the uspspicNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSPSPICNumber(String value) {
        this.uspspicNumber = value;
    }

}
