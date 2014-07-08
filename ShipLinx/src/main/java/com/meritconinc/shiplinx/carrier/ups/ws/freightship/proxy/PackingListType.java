
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackingListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackingListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipFrom" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PackingListContactType" minOccurs="0"/>
 *         &lt;element name="ShipTo" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PackingListContactType" minOccurs="0"/>
 *         &lt;element name="Reference" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PackingListReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HandlingUnit" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PackingListHandlingUnitType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackingListType", propOrder = {
    "shipFrom",
    "shipTo",
    "reference",
    "handlingUnit"
})
public class PackingListType {

    @XmlElement(name = "ShipFrom")
    protected PackingListContactType shipFrom;
    @XmlElement(name = "ShipTo")
    protected PackingListContactType shipTo;
    @XmlElement(name = "Reference")
    protected List<PackingListReferenceType> reference;
    @XmlElement(name = "HandlingUnit")
    protected PackingListHandlingUnitType handlingUnit;

    /**
     * Gets the value of the shipFrom property.
     * 
     * @return
     *     possible object is
     *     {@link PackingListContactType }
     *     
     */
    public PackingListContactType getShipFrom() {
        return shipFrom;
    }

    /**
     * Sets the value of the shipFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackingListContactType }
     *     
     */
    public void setShipFrom(PackingListContactType value) {
        this.shipFrom = value;
    }

    /**
     * Gets the value of the shipTo property.
     * 
     * @return
     *     possible object is
     *     {@link PackingListContactType }
     *     
     */
    public PackingListContactType getShipTo() {
        return shipTo;
    }

    /**
     * Sets the value of the shipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackingListContactType }
     *     
     */
    public void setShipTo(PackingListContactType value) {
        this.shipTo = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackingListReferenceType }
     * 
     * 
     */
    public List<PackingListReferenceType> getReference() {
        if (reference == null) {
            reference = new ArrayList<PackingListReferenceType>();
        }
        return this.reference;
    }

    /**
     * Gets the value of the handlingUnit property.
     * 
     * @return
     *     possible object is
     *     {@link PackingListHandlingUnitType }
     *     
     */
    public PackingListHandlingUnitType getHandlingUnit() {
        return handlingUnit;
    }

    /**
     * Sets the value of the handlingUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackingListHandlingUnitType }
     *     
     */
    public void setHandlingUnit(PackingListHandlingUnitType value) {
        this.handlingUnit = value;
    }

}
