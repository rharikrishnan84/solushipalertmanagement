
package com.meritconinc.shiplinx.carrier.ups.ws.freightpickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HazmatIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackagingType" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}PickupCodeDescriptionType"/>
 *         &lt;element name="NumberOfPieces" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DescriptionOfCommodity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Weight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}WeightType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentDetailType", propOrder = {
    "hazmatIndicator",
    "packagingType",
    "numberOfPieces",
    "descriptionOfCommodity",
    "weight"
})
public class ShipmentDetailType {

    @XmlElement(name = "HazmatIndicator")
    protected String hazmatIndicator;
    @XmlElement(name = "PackagingType", required = true)
    protected PickupCodeDescriptionType packagingType;
    @XmlElement(name = "NumberOfPieces", required = true)
    protected String numberOfPieces;
    @XmlElement(name = "DescriptionOfCommodity", required = true)
    protected String descriptionOfCommodity;
    @XmlElement(name = "Weight", required = true)
    protected WeightType weight;

    /**
     * Gets the value of the hazmatIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHazmatIndicator() {
        return hazmatIndicator;
    }

    /**
     * Sets the value of the hazmatIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHazmatIndicator(String value) {
        this.hazmatIndicator = value;
    }

    /**
     * Gets the value of the packagingType property.
     * 
     * @return
     *     possible object is
     *     {@link PickupCodeDescriptionType }
     *     
     */
    public PickupCodeDescriptionType getPackagingType() {
        return packagingType;
    }

    /**
     * Sets the value of the packagingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupCodeDescriptionType }
     *     
     */
    public void setPackagingType(PickupCodeDescriptionType value) {
        this.packagingType = value;
    }

    /**
     * Gets the value of the numberOfPieces property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfPieces() {
        return numberOfPieces;
    }

    /**
     * Sets the value of the numberOfPieces property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfPieces(String value) {
        this.numberOfPieces = value;
    }

    /**
     * Gets the value of the descriptionOfCommodity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionOfCommodity() {
        return descriptionOfCommodity;
    }

    /**
     * Sets the value of the descriptionOfCommodity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionOfCommodity(String value) {
        this.descriptionOfCommodity = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightType }
     *     
     */
    public WeightType getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightType }
     *     
     */
    public void setWeight(WeightType value) {
        this.weight = value;
    }

}
