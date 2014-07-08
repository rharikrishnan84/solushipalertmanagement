
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetShipmentManifestDocumentResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetShipmentManifestDocumentResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="ManifestBatches" type="{http://purolator.com/pws/datatypes/v1}ArrayOfManifestBatch" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetShipmentManifestDocumentResponseContainer", propOrder = {
    "manifestBatches"
})
public class GetShipmentManifestDocumentResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "ManifestBatches", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfManifestBatch> manifestBatches;

    /**
     * Gets the value of the manifestBatches property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfManifestBatch }{@code >}
     *     
     */
    public JAXBElement<ArrayOfManifestBatch> getManifestBatches() {
        return manifestBatches;
    }

    /**
     * Sets the value of the manifestBatches property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfManifestBatch }{@code >}
     *     
     */
    public void setManifestBatches(JAXBElement<ArrayOfManifestBatch> value) {
        this.manifestBatches = value;
    }

}
