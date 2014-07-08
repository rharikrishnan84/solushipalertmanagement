
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProofOfPickUpScan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProofOfPickUpScan">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}Scan">
 *       &lt;sequence>
 *         &lt;element name="ScanDetails" type="{http://purolator.com/pws/datatypes/v1}ProofOfPickUpScanDetails"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProofOfPickUpScan", propOrder = {
    "scanDetails"
})
public class ProofOfPickUpScan
    extends Scan
{

    @XmlElement(name = "ScanDetails", required = true, nillable = true)
    protected ProofOfPickUpScanDetails scanDetails;

    /**
     * Gets the value of the scanDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ProofOfPickUpScanDetails }
     *     
     */
    public ProofOfPickUpScanDetails getScanDetails() {
        return scanDetails;
    }

    /**
     * Sets the value of the scanDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProofOfPickUpScanDetails }
     *     
     */
    public void setScanDetails(ProofOfPickUpScanDetails value) {
        this.scanDetails = value;
    }

}
