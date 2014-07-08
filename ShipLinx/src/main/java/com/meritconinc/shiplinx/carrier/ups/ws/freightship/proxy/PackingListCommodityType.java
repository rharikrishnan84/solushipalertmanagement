
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackingListCommodityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackingListCommodityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumberOfPieces" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackagingType" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="DangerousGoodsIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NMFCCommodityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FreightClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dimensions" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PackingListDimensionsType" minOccurs="0"/>
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CommodityValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackingListCommodityType", propOrder = {
    "numberOfPieces",
    "packagingType",
    "dangerousGoodsIndicator",
    "description",
    "nmfcCommodityCode",
    "freightClass",
    "dimensions",
    "weight",
    "commodityValue"
})
public class PackingListCommodityType {

    @XmlElement(name = "NumberOfPieces")
    protected String numberOfPieces;
    @XmlElement(name = "PackagingType")
    protected ShipCodeDescriptionType packagingType;
    @XmlElement(name = "DangerousGoodsIndicator")
    protected String dangerousGoodsIndicator;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "NMFCCommodityCode")
    protected String nmfcCommodityCode;
    @XmlElement(name = "FreightClass")
    protected String freightClass;
    @XmlElement(name = "Dimensions")
    protected PackingListDimensionsType dimensions;
    @XmlElement(name = "Weight")
    protected String weight;
    @XmlElement(name = "CommodityValue")
    protected String commodityValue;

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
     * Gets the value of the packagingType property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getPackagingType() {
        return packagingType;
    }

    /**
     * Sets the value of the packagingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setPackagingType(ShipCodeDescriptionType value) {
        this.packagingType = value;
    }

    /**
     * Gets the value of the dangerousGoodsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDangerousGoodsIndicator() {
        return dangerousGoodsIndicator;
    }

    /**
     * Sets the value of the dangerousGoodsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDangerousGoodsIndicator(String value) {
        this.dangerousGoodsIndicator = value;
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
     * Gets the value of the nmfcCommodityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNMFCCommodityCode() {
        return nmfcCommodityCode;
    }

    /**
     * Sets the value of the nmfcCommodityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNMFCCommodityCode(String value) {
        this.nmfcCommodityCode = value;
    }

    /**
     * Gets the value of the freightClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreightClass() {
        return freightClass;
    }

    /**
     * Sets the value of the freightClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreightClass(String value) {
        this.freightClass = value;
    }

    /**
     * Gets the value of the dimensions property.
     * 
     * @return
     *     possible object is
     *     {@link PackingListDimensionsType }
     *     
     */
    public PackingListDimensionsType getDimensions() {
        return dimensions;
    }

    /**
     * Sets the value of the dimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackingListDimensionsType }
     *     
     */
    public void setDimensions(PackingListDimensionsType value) {
        this.dimensions = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeight(String value) {
        this.weight = value;
    }

    /**
     * Gets the value of the commodityValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityValue() {
        return commodityValue;
    }

    /**
     * Sets the value of the commodityValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityValue(String value) {
        this.commodityValue = value;
    }

}
