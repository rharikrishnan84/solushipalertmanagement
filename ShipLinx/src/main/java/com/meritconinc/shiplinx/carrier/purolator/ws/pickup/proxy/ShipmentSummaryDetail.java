
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Shipment Summary
 * 
 * <p>Java class for ShipmentSummaryDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentSummaryDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DestinationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModeOfTransport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalPieces" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TotalWeight" type="{http://purolator.com/pws/datatypes/v1}Weight"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentSummaryDetail", propOrder = {
    "destinationCode",
    "modeOfTransport",
    "totalPieces",
    "totalWeight"
})
public class ShipmentSummaryDetail {

    @XmlElement(name = "DestinationCode", required = true, nillable = true)
    protected String destinationCode;
    @XmlElementRef(name = "ModeOfTransport", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> modeOfTransport;
    @XmlElement(name = "TotalPieces")
    protected int totalPieces;
    @XmlElement(name = "TotalWeight", required = true, nillable = true)
    protected Weight totalWeight;

    /**
     * Gets the value of the destinationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationCode() {
        return destinationCode;
    }

    /**
     * Sets the value of the destinationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationCode(String value) {
        this.destinationCode = value;
    }

    /**
     * Gets the value of the modeOfTransport property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModeOfTransport() {
        return modeOfTransport;
    }

    /**
     * Sets the value of the modeOfTransport property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModeOfTransport(JAXBElement<String> value) {
        this.modeOfTransport = value;
    }

    /**
     * Gets the value of the totalPieces property.
     * 
     */
    public int getTotalPieces() {
        return totalPieces;
    }

    /**
     * Sets the value of the totalPieces property.
     * 
     */
    public void setTotalPieces(int value) {
        this.totalPieces = value;
    }

    /**
     * Gets the value of the totalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Weight }
     *     
     */
    public Weight getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the value of the totalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Weight }
     *     
     */
    public void setTotalWeight(Weight value) {
        this.totalWeight = value;
    }

}
