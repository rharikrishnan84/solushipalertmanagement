
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * TrackPackagesByReferenceRequest
 * 
 * <p>Java class for TrackPackagesByReferenceRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrackPackagesByReferenceRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="TrackPackageByReferenceSearchCriteria" type="{http://purolator.com/pws/datatypes/v1}TrackPackageByReferenceSearchCriteria"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackPackagesByReferenceRequestContainer", propOrder = {
    "trackPackageByReferenceSearchCriteria"
})
public class TrackPackagesByReferenceRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "TrackPackageByReferenceSearchCriteria", required = true, nillable = true)
    protected TrackPackageByReferenceSearchCriteria trackPackageByReferenceSearchCriteria;

    /**
     * Gets the value of the trackPackageByReferenceSearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link TrackPackageByReferenceSearchCriteria }
     *     
     */
    public TrackPackageByReferenceSearchCriteria getTrackPackageByReferenceSearchCriteria() {
        return trackPackageByReferenceSearchCriteria;
    }

    /**
     * Sets the value of the trackPackageByReferenceSearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackPackageByReferenceSearchCriteria }
     *     
     */
    public void setTrackPackageByReferenceSearchCriteria(TrackPackageByReferenceSearchCriteria value) {
        this.trackPackageByReferenceSearchCriteria = value;
    }

}
