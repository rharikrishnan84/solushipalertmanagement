
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0}Response"/>
 *         &lt;element name="ShipmentResults" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipmentResultsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "response",
    "shipmentResults"
})
@XmlRootElement(name = "FreightShipResponse")
public class FreightShipResponse {

    @XmlElement(name = "Response", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected ResponseType response;
    @XmlElement(name = "ShipmentResults", required = true)
    protected ShipmentResultsType shipmentResults;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseType }
     *     
     */
    public ResponseType getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseType }
     *     
     */
    public void setResponse(ResponseType value) {
        this.response = value;
    }

    /**
     * Gets the value of the shipmentResults property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentResultsType }
     *     
     */
    public ShipmentResultsType getShipmentResults() {
        return shipmentResults;
    }

    /**
     * Sets the value of the shipmentResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentResultsType }
     *     
     */
    public void setShipmentResults(ShipmentResultsType value) {
        this.shipmentResults = value;
    }

}
