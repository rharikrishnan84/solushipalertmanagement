
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfShipmentManifestDocumentCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfShipmentManifestDocumentCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentManifestDocumentCriteria" type="{http://purolator.com/pws/datatypes/v1}ShipmentManifestDocumentCriteria" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfShipmentManifestDocumentCriteria", propOrder = {
    "shipmentManifestDocumentCriteria"
})
public class ArrayOfShipmentManifestDocumentCriteria {

    @XmlElement(name = "ShipmentManifestDocumentCriteria", nillable = true)
    protected List<ShipmentManifestDocumentCriteria> shipmentManifestDocumentCriteria;

    /**
     * Gets the value of the shipmentManifestDocumentCriteria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentManifestDocumentCriteria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentManifestDocumentCriteria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentManifestDocumentCriteria }
     * 
     * 
     */
    public List<ShipmentManifestDocumentCriteria> getShipmentManifestDocumentCriteria() {
        if (shipmentManifestDocumentCriteria == null) {
            shipmentManifestDocumentCriteria = new ArrayList<ShipmentManifestDocumentCriteria>();
        }
        return this.shipmentManifestDocumentCriteria;
    }

}
