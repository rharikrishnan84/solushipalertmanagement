
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfManifestBatch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfManifestBatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ManifestBatch" type="{http://purolator.com/pws/datatypes/v1}ManifestBatch" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfManifestBatch", propOrder = {
    "manifestBatch"
})
public class ArrayOfManifestBatch {

    @XmlElement(name = "ManifestBatch", nillable = true)
    protected List<ManifestBatch> manifestBatch;

    /**
     * Gets the value of the manifestBatch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the manifestBatch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getManifestBatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ManifestBatch }
     * 
     * 
     */
    public List<ManifestBatch> getManifestBatch() {
        if (manifestBatch == null) {
            manifestBatch = new ArrayList<ManifestBatch>();
        }
        return this.manifestBatch;
    }

}
