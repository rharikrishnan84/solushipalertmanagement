
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * TrackPackagesByPinRequest
 * 
 * <p>Java class for TrackPackagesByPinRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrackPackagesByPinRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="PINs" type="{http://purolator.com/pws/datatypes/v1}ArrayOfPIN"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackPackagesByPinRequestContainer", propOrder = {
    "piNs"
})
public class TrackPackagesByPinRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "PINs", required = true, nillable = true)
    protected ArrayOfPIN piNs;

    /**
     * Gets the value of the piNs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPIN }
     *     
     */
    public ArrayOfPIN getPINs() {
        return piNs;
    }

    /**
     * Sets the value of the piNs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPIN }
     *     
     */
    public void setPINs(ArrayOfPIN value) {
        this.piNs = value;
    }

}
