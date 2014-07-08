
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTrackingInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTrackingInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackingInformation" type="{http://purolator.com/pws/datatypes/v1}TrackingInformation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTrackingInformation", propOrder = {
    "trackingInformation"
})
public class ArrayOfTrackingInformation {

    @XmlElement(name = "TrackingInformation", nillable = true)
    protected List<TrackingInformation> trackingInformation;

    /**
     * Gets the value of the trackingInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trackingInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrackingInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrackingInformation }
     * 
     * 
     */
    public List<TrackingInformation> getTrackingInformation() {
        if (trackingInformation == null) {
            trackingInformation = new ArrayList<TrackingInformation>();
        }
        return this.trackingInformation;
    }

}
