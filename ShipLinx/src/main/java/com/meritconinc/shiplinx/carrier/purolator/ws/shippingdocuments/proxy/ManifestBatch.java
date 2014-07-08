
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ManifestBatch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ManifestBatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentManifestDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ManifestCloseDateTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ManifestBatchDetails" type="{http://purolator.com/pws/datatypes/v1}ArrayOfManifestBatchDetail"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManifestBatch", propOrder = {
    "shipmentManifestDate",
    "manifestCloseDateTime",
    "manifestBatchDetails"
})
public class ManifestBatch {

    @XmlElement(name = "ShipmentManifestDate", required = true, nillable = true)
    protected String shipmentManifestDate;
    @XmlElement(name = "ManifestCloseDateTime", required = true, nillable = true)
    protected String manifestCloseDateTime;
    @XmlElement(name = "ManifestBatchDetails", required = true, nillable = true)
    protected ArrayOfManifestBatchDetail manifestBatchDetails;

    /**
     * Gets the value of the shipmentManifestDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentManifestDate() {
        return shipmentManifestDate;
    }

    /**
     * Sets the value of the shipmentManifestDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentManifestDate(String value) {
        this.shipmentManifestDate = value;
    }

    /**
     * Gets the value of the manifestCloseDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManifestCloseDateTime() {
        return manifestCloseDateTime;
    }

    /**
     * Sets the value of the manifestCloseDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManifestCloseDateTime(String value) {
        this.manifestCloseDateTime = value;
    }

    /**
     * Gets the value of the manifestBatchDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfManifestBatchDetail }
     *     
     */
    public ArrayOfManifestBatchDetail getManifestBatchDetails() {
        return manifestBatchDetails;
    }

    /**
     * Sets the value of the manifestBatchDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfManifestBatchDetail }
     *     
     */
    public void setManifestBatchDetails(ArrayOfManifestBatchDetail value) {
        this.manifestBatchDetails = value;
    }

}
