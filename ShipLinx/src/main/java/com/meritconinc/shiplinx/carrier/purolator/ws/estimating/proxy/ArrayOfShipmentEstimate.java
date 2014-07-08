
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfShipmentEstimate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfShipmentEstimate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentEstimate" type="{http://purolator.com/pws/datatypes/v1}ShipmentEstimate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfShipmentEstimate", propOrder = {
    "shipmentEstimate"
})
public class ArrayOfShipmentEstimate {

    @XmlElement(name = "ShipmentEstimate", nillable = true)
    protected List<ShipmentEstimate> shipmentEstimate;

    /**
     * Gets the value of the shipmentEstimate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentEstimate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentEstimate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentEstimate }
     * 
     * 
     */
    public List<ShipmentEstimate> getShipmentEstimate() {
        if (shipmentEstimate == null) {
            shipmentEstimate = new ArrayList<ShipmentEstimate>();
        }
        return this.shipmentEstimate;
    }

}
