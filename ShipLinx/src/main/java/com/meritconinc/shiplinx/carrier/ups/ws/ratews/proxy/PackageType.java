
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PackagingType" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}CodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="Dimensions" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}DimensionsType" minOccurs="0"/>
 *         &lt;element name="PackageWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}PackageWeightType" minOccurs="0"/>
 *         &lt;element name="Commodity" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}CommodityType" minOccurs="0"/>
 *         &lt;element name="LargePackageIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageServiceOptions" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}PackageServiceOptionsType" minOccurs="0"/>
 *         &lt;element name="AdditionalHandlingIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageType", propOrder = {
    "packagingType",
    "dimensions",
    "packageWeight",
    "commodity",
    "largePackageIndicator",
    "packageServiceOptions",
    "additionalHandlingIndicator"
})
public class PackageType {

    @XmlElement(name = "PackagingType")
    protected RateCodeDescriptionType packagingType;
    @XmlElement(name = "Dimensions")
    protected DimensionsType dimensions;
    @XmlElement(name = "PackageWeight")
    protected PackageWeightType packageWeight;
    @XmlElement(name = "Commodity")
    protected CommodityType commodity;
    @XmlElement(name = "LargePackageIndicator")
    protected String largePackageIndicator;
    @XmlElement(name = "PackageServiceOptions")
    protected PackageServiceOptionsType packageServiceOptions;
    @XmlElement(name = "AdditionalHandlingIndicator")
    protected String additionalHandlingIndicator;

    /**
     * Gets the value of the packagingType property.
     * 
     * @return
     *     possible object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public RateCodeDescriptionType getPackagingType() {
        return packagingType;
    }

    /**
     * Sets the value of the packagingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public void setPackagingType(RateCodeDescriptionType value) {
        this.packagingType = value;
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
     * Gets the value of the packageWeight property.
     * 
     * @return
     *     possible object is
     *     {@link PackageWeightType }
     *     
     */
    public PackageWeightType getPackageWeight() {
        return packageWeight;
    }

    /**
     * Sets the value of the packageWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageWeightType }
     *     
     */
    public void setPackageWeight(PackageWeightType value) {
        this.packageWeight = value;
    }

    /**
     * Gets the value of the commodity property.
     * 
     * @return
     *     possible object is
     *     {@link CommodityType }
     *     
     */
    public CommodityType getCommodity() {
        return commodity;
    }

    /**
     * Sets the value of the commodity property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityType }
     *     
     */
    public void setCommodity(CommodityType value) {
        this.commodity = value;
    }

    /**
     * Gets the value of the largePackageIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLargePackageIndicator() {
        return largePackageIndicator;
    }

    /**
     * Sets the value of the largePackageIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLargePackageIndicator(String value) {
        this.largePackageIndicator = value;
    }

    /**
     * Gets the value of the packageServiceOptions property.
     * 
     * @return
     *     possible object is
     *     {@link PackageServiceOptionsType }
     *     
     */
    public PackageServiceOptionsType getPackageServiceOptions() {
        return packageServiceOptions;
    }

    /**
     * Sets the value of the packageServiceOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageServiceOptionsType }
     *     
     */
    public void setPackageServiceOptions(PackageServiceOptionsType value) {
        this.packageServiceOptions = value;
    }

    /**
     * Gets the value of the additionalHandlingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalHandlingIndicator() {
        return additionalHandlingIndicator;
    }

    /**
     * Sets the value of the additionalHandlingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalHandlingIndicator(String value) {
        this.additionalHandlingIndicator = value;
    }

}
