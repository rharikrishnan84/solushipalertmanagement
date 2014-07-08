
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommodityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommodityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommodityID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Weight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}WeightType"/>
 *         &lt;element name="Dimensions" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}DimensionsType" minOccurs="0"/>
 *         &lt;element name="NumberOfPieces" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PackagingType" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType"/>
 *         &lt;element name="DangerousGoodsIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CommodityValue" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}CommodityValueType" minOccurs="0"/>
 *         &lt;element name="FreightClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NMFCCommodityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NMFCCommodity" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}NMFCCommodityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityType", propOrder = {
    "commodityID",
    "description",
    "weight",
    "dimensions",
    "numberOfPieces",
    "packagingType",
    "dangerousGoodsIndicator",
    "commodityValue",
    "freightClass",
    "nmfcCommodityCode",
    "nmfcCommodity"
})
public class CommodityType {

    @XmlElement(name = "CommodityID")
    protected String commodityID;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Weight", required = true)
    protected WeightType weight;
    @XmlElement(name = "Dimensions")
    protected DimensionsType dimensions;
    @XmlElement(name = "NumberOfPieces", required = true)
    protected String numberOfPieces;
    @XmlElement(name = "PackagingType", required = true)
    protected ShipCodeDescriptionType packagingType;
    @XmlElement(name = "DangerousGoodsIndicator")
    protected String dangerousGoodsIndicator;
    @XmlElement(name = "CommodityValue")
    protected CommodityValueType commodityValue;
    @XmlElement(name = "FreightClass", required = true)
    protected String freightClass;
    @XmlElement(name = "NMFCCommodityCode")
    protected String nmfcCommodityCode;
    @XmlElement(name = "NMFCCommodity")
    protected NMFCCommodityType nmfcCommodity;

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
     * Gets the value of the dimensions property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionsType }
     *     
     */
    public DimensionsType getDimensions() {
        return dimensions;
    }

    /**
     * Sets the value of the dimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionsType }
     *     
     */
    public void setDimensions(DimensionsType value) {
        this.dimensions = value;
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
     * Gets the value of the commodityValue property.
     * 
     * @return
     *     possible object is
     *     {@link CommodityValueType }
     *     
     */
    public CommodityValueType getCommodityValue() {
        return commodityValue;
    }

    /**
     * Sets the value of the commodityValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityValueType }
     *     
     */
    public void setCommodityValue(CommodityValueType value) {
        this.commodityValue = value;
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
     * Gets the value of the nmfcCommodity property.
     * 
     * @return
     *     possible object is
     *     {@link NMFCCommodityType }
     *     
     */
    public NMFCCommodityType getNMFCCommodity() {
        return nmfcCommodity;
    }

    /**
     * Sets the value of the nmfcCommodity property.
     * 
     * @param value
     *     allowed object is
     *     {@link NMFCCommodityType }
     *     
     */
    public void setNMFCCommodity(NMFCCommodityType value) {
        this.nmfcCommodity = value;
    }

}
