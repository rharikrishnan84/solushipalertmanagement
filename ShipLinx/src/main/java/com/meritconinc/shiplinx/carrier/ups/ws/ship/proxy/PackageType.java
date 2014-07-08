
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Packaging" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PackagingType" minOccurs="0"/>
 *         &lt;element name="Dimensions" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}DimensionsType" minOccurs="0"/>
 *         &lt;element name="PackageWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PackageWeightType" minOccurs="0"/>
 *         &lt;element name="LargePackageIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ReferenceNumberType" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="AdditionalHandlingIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageServiceOptions" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PackageServiceOptionsType" minOccurs="0"/>
 *         &lt;element name="Commodity" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}CommodityType" minOccurs="0"/>
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
    "description",
    "packaging",
    "dimensions",
    "packageWeight",
    "largePackageIndicator",
    "referenceNumber",
    "additionalHandlingIndicator",
    "packageServiceOptions",
    "commodity"
})
public class PackageType {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Packaging")
    protected PackagingType packaging;
    @XmlElement(name = "Dimensions")
    protected DimensionsType dimensions;
    @XmlElement(name = "PackageWeight")
    protected PackageWeightType packageWeight;
    @XmlElement(name = "LargePackageIndicator")
    protected String largePackageIndicator;
    @XmlElement(name = "ReferenceNumber")
    protected List<ReferenceNumberType> referenceNumber;
    @XmlElement(name = "AdditionalHandlingIndicator")
    protected String additionalHandlingIndicator;
    @XmlElement(name = "PackageServiceOptions")
    protected PackageServiceOptionsType packageServiceOptions;
    @XmlElement(name = "Commodity")
    protected CommodityType commodity;

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
     * Gets the value of the packaging property.
     * 
     * @return
     *     possible object is
     *     {@link PackagingType }
     *     
     */
    public PackagingType getPackaging() {
        return packaging;
    }

    /**
     * Sets the value of the packaging property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackagingType }
     *     
     */
    public void setPackaging(PackagingType value) {
        this.packaging = value;
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
     * Gets the value of the referenceNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceNumberType }
     * 
     * 
     */
    public List<ReferenceNumberType> getReferenceNumber() {
        if (referenceNumber == null) {
            referenceNumber = new ArrayList<ReferenceNumberType>();
        }
        return this.referenceNumber;
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

}
