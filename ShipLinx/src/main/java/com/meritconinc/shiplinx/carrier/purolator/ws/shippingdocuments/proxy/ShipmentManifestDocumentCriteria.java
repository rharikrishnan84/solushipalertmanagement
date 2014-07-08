
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentManifestDocumentCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentManifestDocumentCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ManifestDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentManifestDocumentCriteria", propOrder = {
    "manifestDate"
})
public class ShipmentManifestDocumentCriteria {

    @XmlElement(name = "ManifestDate", required = true, nillable = true)
    protected String manifestDate;

    /**
     * Gets the value of the manifestDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManifestDate() {
        return manifestDate;
    }

    /**
     * Sets the value of the manifestDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManifestDate(String value) {
        this.manifestDate = value;
    }

}
