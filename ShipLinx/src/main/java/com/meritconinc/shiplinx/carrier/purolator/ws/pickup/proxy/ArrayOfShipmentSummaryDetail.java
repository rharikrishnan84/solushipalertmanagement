
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfShipmentSummaryDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfShipmentSummaryDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentSummaryDetail" type="{http://purolator.com/pws/datatypes/v1}ShipmentSummaryDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfShipmentSummaryDetail", propOrder = {
    "shipmentSummaryDetail"
})
public class ArrayOfShipmentSummaryDetail {

    @XmlElement(name = "ShipmentSummaryDetail", nillable = true)
    protected List<ShipmentSummaryDetail> shipmentSummaryDetail;

    /**
     * Gets the value of the shipmentSummaryDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentSummaryDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentSummaryDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentSummaryDetail }
     * 
     * 
     */
    public List<ShipmentSummaryDetail> getShipmentSummaryDetail() {
        if (shipmentSummaryDetail == null) {
            shipmentSummaryDetail = new ArrayList<ShipmentSummaryDetail>();
        }
        return this.shipmentSummaryDetail;
    }

}
