
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OnDeliveryScan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OnDeliveryScan">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}Scan">
 *       &lt;sequence>
 *         &lt;element name="ScanDetails" type="{http://purolator.com/pws/datatypes/v1}OnDeliveryScanDetails"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OnDeliveryScan", propOrder = {
    "scanDetails"
})
public class OnDeliveryScan
    extends Scan
{

    @XmlElement(name = "ScanDetails", required = true, nillable = true)
    protected OnDeliveryScanDetails scanDetails;

    /**
     * Gets the value of the scanDetails property.
     * 
     * @return
     *     possible object is
     *     {@link OnDeliveryScanDetails }
     *     
     */
    public OnDeliveryScanDetails getScanDetails() {
        return scanDetails;
    }

    /**
     * Sets the value of the scanDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link OnDeliveryScanDetails }
     *     
     */
    public void setScanDetails(OnDeliveryScanDetails value) {
        this.scanDetails = value;
    }

}
