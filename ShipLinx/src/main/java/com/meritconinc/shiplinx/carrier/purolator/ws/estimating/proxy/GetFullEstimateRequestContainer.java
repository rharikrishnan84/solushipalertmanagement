
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * GetFullEstimateRequest
 * 
 * <p>Java class for GetFullEstimateRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetFullEstimateRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="Shipment" type="{http://purolator.com/pws/datatypes/v1}Shipment"/>
 *         &lt;element name="ShowAlternativeServicesIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetFullEstimateRequestContainer", propOrder = {
    "shipment",
    "showAlternativeServicesIndicator"
})
public class GetFullEstimateRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "Shipment", required = true, nillable = true)
    protected Shipment shipment;
    @XmlElement(name = "ShowAlternativeServicesIndicator")
    protected boolean showAlternativeServicesIndicator;

    /**
     * Gets the value of the shipment property.
     * 
     * @return
     *     possible object is
     *     {@link Shipment }
     *     
     */
    public Shipment getShipment() {
        return shipment;
    }

    /**
     * Sets the value of the shipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shipment }
     *     
     */
    public void setShipment(Shipment value) {
        this.shipment = value;
    }

    /**
     * Gets the value of the showAlternativeServicesIndicator property.
     * 
     */
    public boolean isShowAlternativeServicesIndicator() {
        return showAlternativeServicesIndicator;
    }

    /**
     * Sets the value of the showAlternativeServicesIndicator property.
     * 
     */
    public void setShowAlternativeServicesIndicator(boolean value) {
        this.showAlternativeServicesIndicator = value;
    }

}
