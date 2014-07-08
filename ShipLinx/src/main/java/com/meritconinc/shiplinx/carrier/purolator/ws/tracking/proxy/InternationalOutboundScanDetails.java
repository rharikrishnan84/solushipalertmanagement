
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * InternationalOutboundScanDetails
 * 
 * <p>Java class for InternationalOutboundScanDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InternationalOutboundScanDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DestinationPostalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AirportCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternationalOutboundScanDetails", propOrder = {
    "destinationPostalCode",
    "airportCode"
})
public class InternationalOutboundScanDetails {

    @XmlElement(name = "DestinationPostalCode", required = true, nillable = true)
    protected String destinationPostalCode;
    @XmlElement(name = "AirportCode", required = true, nillable = true)
    protected String airportCode;

    /**
     * Gets the value of the destinationPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationPostalCode() {
        return destinationPostalCode;
    }

    /**
     * Sets the value of the destinationPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationPostalCode(String value) {
        this.destinationPostalCode = value;
    }

    /**
     * Gets the value of the airportCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * Sets the value of the airportCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirportCode(String value) {
        this.airportCode = value;
    }

}
