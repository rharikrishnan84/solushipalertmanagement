
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * TrackingInformation
 * 
 * <p>Java class for TrackingInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrackingInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PIN" type="{http://purolator.com/pws/datatypes/v1}PIN"/>
 *         &lt;element name="Scans" type="{http://purolator.com/pws/datatypes/v1}ArrayOfScan" minOccurs="0"/>
 *         &lt;element name="ResponseInformation" type="{http://purolator.com/pws/datatypes/v1}ResponseInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackingInformation", propOrder = {
    "pin",
    "scans",
    "responseInformation"
})
public class TrackingInformation {

    @XmlElement(name = "PIN", required = true, nillable = true)
    protected PIN pin;
    @XmlElementRef(name = "Scans", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfScan> scans;
    @XmlElement(name = "ResponseInformation", required = true, nillable = true)
    protected ResponseInformation responseInformation;

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link PIN }
     *     
     */
    public PIN getPIN() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PIN }
     *     
     */
    public void setPIN(PIN value) {
        this.pin = value;
    }

    /**
     * Gets the value of the scans property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfScan }{@code >}
     *     
     */
    public JAXBElement<ArrayOfScan> getScans() {
        return scans;
    }

    /**
     * Sets the value of the scans property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfScan }{@code >}
     *     
     */
    public void setScans(JAXBElement<ArrayOfScan> value) {
        this.scans = value;
    }

    /**
     * Gets the value of the responseInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseInformation }
     *     
     */
    public ResponseInformation getResponseInformation() {
        return responseInformation;
    }

    /**
     * Sets the value of the responseInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseInformation }
     *     
     */
    public void setResponseInformation(ResponseInformation value) {
        this.responseInformation = value;
    }

}
