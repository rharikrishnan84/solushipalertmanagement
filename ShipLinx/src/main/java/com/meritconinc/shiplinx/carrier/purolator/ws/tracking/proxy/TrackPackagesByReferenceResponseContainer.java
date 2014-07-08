
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Service Contract Class - TrackPackagesByReferenceResponse
 * 
 * <p>Java class for TrackPackagesByReferenceResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrackPackagesByReferenceResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="TrackingInformationList" type="{http://purolator.com/pws/datatypes/v1}ArrayOfTrackingInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackPackagesByReferenceResponseContainer", propOrder = {
    "trackingInformationList"
})
public class TrackPackagesByReferenceResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "TrackingInformationList", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrackingInformation> trackingInformationList;

    /**
     * Gets the value of the trackingInformationList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrackingInformation }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrackingInformation> getTrackingInformationList() {
        return trackingInformationList;
    }

    /**
     * Sets the value of the trackingInformationList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrackingInformation }{@code >}
     *     
     */
    public void setTrackingInformationList(JAXBElement<ArrayOfTrackingInformation> value) {
        this.trackingInformationList = value;
    }

}
