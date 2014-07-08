
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetShipmentManifestDocumentRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetShipmentManifestDocumentRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="ShipmentManifestDocumentCriterium" type="{http://purolator.com/pws/datatypes/v1}ArrayOfShipmentManifestDocumentCriteria"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetShipmentManifestDocumentRequestContainer", propOrder = {
    "shipmentManifestDocumentCriterium"
})
public class GetShipmentManifestDocumentRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "ShipmentManifestDocumentCriterium", required = true, nillable = true)
    protected ArrayOfShipmentManifestDocumentCriteria shipmentManifestDocumentCriterium;

    /**
     * Gets the value of the shipmentManifestDocumentCriterium property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfShipmentManifestDocumentCriteria }
     *     
     */
    public ArrayOfShipmentManifestDocumentCriteria getShipmentManifestDocumentCriterium() {
        return shipmentManifestDocumentCriterium;
    }

    /**
     * Sets the value of the shipmentManifestDocumentCriterium property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfShipmentManifestDocumentCriteria }
     *     
     */
    public void setShipmentManifestDocumentCriterium(ArrayOfShipmentManifestDocumentCriteria value) {
        this.shipmentManifestDocumentCriterium = value;
    }

}
