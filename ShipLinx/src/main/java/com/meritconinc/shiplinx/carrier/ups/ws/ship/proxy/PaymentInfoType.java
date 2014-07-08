
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentCharge" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipmentChargeType" maxOccurs="2"/>
 *         &lt;element name="SplitDutyVATIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentInfoType", propOrder = {
    "shipmentCharge",
    "splitDutyVATIndicator"
})
public class PaymentInfoType {

    @XmlElement(name = "ShipmentCharge", required = true)
    protected List<ShipmentChargeType> shipmentCharge;
    @XmlElement(name = "SplitDutyVATIndicator")
    protected String splitDutyVATIndicator;

    /**
     * Gets the value of the shipmentCharge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentCharge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentCharge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentChargeType }
     * 
     * 
     */
    public List<ShipmentChargeType> getShipmentCharge() {
        if (shipmentCharge == null) {
            shipmentCharge = new ArrayList<ShipmentChargeType>();
        }
        return this.shipmentCharge;
    }

    /**
     * Gets the value of the splitDutyVATIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitDutyVATIndicator() {
        return splitDutyVATIndicator;
    }

    /**
     * Sets the value of the splitDutyVATIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitDutyVATIndicator(String value) {
        this.splitDutyVATIndicator = value;
    }

}
