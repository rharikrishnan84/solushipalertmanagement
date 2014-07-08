
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommodityWeightType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommodityWeightType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommodityID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Weight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}WeightType"/>
 *         &lt;element name="AdjustedWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}AdjustedWeightType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityWeightType", propOrder = {
    "commodityID",
    "description",
    "weight",
    "adjustedWeight"
})
public class CommodityWeightType {

    @XmlElement(name = "CommodityID")
    protected String commodityID;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Weight", required = true)
    protected WeightType weight;
    @XmlElement(name = "AdjustedWeight")
    protected AdjustedWeightType adjustedWeight;

    /**
     * Gets the value of the commodityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityID() {
        return commodityID;
    }

    /**
     * Sets the value of the commodityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityID(String value) {
        this.commodityID = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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

    /**
     * Gets the value of the adjustedWeight property.
     * 
     * @return
     *     possible object is
     *     {@link AdjustedWeightType }
     *     
     */
    public AdjustedWeightType getAdjustedWeight() {
        return adjustedWeight;
    }

    /**
     * Sets the value of the adjustedWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdjustedWeightType }
     *     
     */
    public void setAdjustedWeight(AdjustedWeightType value) {
        this.adjustedWeight = value;
    }

}
