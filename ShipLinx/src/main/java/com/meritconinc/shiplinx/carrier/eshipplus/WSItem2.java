
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSItem2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSItem2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PackagingQuantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SaidToContain" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Stackable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DeclaredValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NationalMotorFreightClassification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HarmonizedTariffSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Packaging" type="{http://www.eshipplus.com/}WSItemPackage" minOccurs="0"/>
 *         &lt;element name="FreightClass" type="{http://www.eshipplus.com/}WSFreightClass" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSItem2", propOrder = {
    "weight",
    "packagingQuantity",
    "saidToContain",
    "height",
    "width",
    "length",
    "stackable",
    "declaredValue",
    "description",
    "comment",
    "nationalMotorFreightClassification",
    "harmonizedTariffSchedule",
    "packaging",
    "freightClass"
})
public class WSItem2 {

    @XmlElement(name = "Weight", required = true)
    protected BigDecimal weight;
    @XmlElement(name = "PackagingQuantity")
    protected int packagingQuantity;
    @XmlElement(name = "SaidToContain")
    protected int saidToContain;
    @XmlElement(name = "Height", required = true)
    protected BigDecimal height;
    @XmlElement(name = "Width", required = true)
    protected BigDecimal width;
    @XmlElement(name = "Length", required = true)
    protected BigDecimal length;
    @XmlElement(name = "Stackable")
    protected boolean stackable;
    @XmlElement(name = "DeclaredValue", required = true)
    protected BigDecimal declaredValue;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Comment")
    protected String comment;
    @XmlElement(name = "NationalMotorFreightClassification")
    protected String nationalMotorFreightClassification;
    @XmlElement(name = "HarmonizedTariffSchedule")
    protected String harmonizedTariffSchedule;
    @XmlElement(name = "Packaging")
    protected WSItemPackage packaging;
    @XmlElement(name = "FreightClass")
    protected WSFreightClass freightClass;

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

    /**
     * Gets the value of the packagingQuantity property.
     * 
     */
    public int getPackagingQuantity() {
        return packagingQuantity;
    }

    /**
     * Sets the value of the packagingQuantity property.
     * 
     */
    public void setPackagingQuantity(int value) {
        this.packagingQuantity = value;
    }

    /**
     * Gets the value of the saidToContain property.
     * 
     */
    public int getSaidToContain() {
        return saidToContain;
    }

    /**
     * Sets the value of the saidToContain property.
     * 
     */
    public void setSaidToContain(int value) {
        this.saidToContain = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHeight(BigDecimal value) {
        this.height = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWidth(BigDecimal value) {
        this.width = value;
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLength(BigDecimal value) {
        this.length = value;
    }

    /**
     * Gets the value of the stackable property.
     * 
     */
    public boolean isStackable() {
        return stackable;
    }

    /**
     * Sets the value of the stackable property.
     * 
     */
    public void setStackable(boolean value) {
        this.stackable = value;
    }

    /**
     * Gets the value of the declaredValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeclaredValue() {
        return declaredValue;
    }

    /**
     * Sets the value of the declaredValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeclaredValue(BigDecimal value) {
        this.declaredValue = value;
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
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the nationalMotorFreightClassification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalMotorFreightClassification() {
        return nationalMotorFreightClassification;
    }

    /**
     * Sets the value of the nationalMotorFreightClassification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalMotorFreightClassification(String value) {
        this.nationalMotorFreightClassification = value;
    }

    /**
     * Gets the value of the harmonizedTariffSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHarmonizedTariffSchedule() {
        return harmonizedTariffSchedule;
    }

    /**
     * Sets the value of the harmonizedTariffSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHarmonizedTariffSchedule(String value) {
        this.harmonizedTariffSchedule = value;
    }

    /**
     * Gets the value of the packaging property.
     * 
     * @return
     *     possible object is
     *     {@link WSItemPackage }
     *     
     */
    public WSItemPackage getPackaging() {
        return packaging;
    }

    /**
     * Sets the value of the packaging property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSItemPackage }
     *     
     */
    public void setPackaging(WSItemPackage value) {
        this.packaging = value;
    }

    /**
     * Gets the value of the freightClass property.
     * 
     * @return
     *     possible object is
     *     {@link WSFreightClass }
     *     
     */
    public WSFreightClass getFreightClass() {
        return freightClass;
    }

    /**
     * Sets the value of the freightClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSFreightClass }
     *     
     */
    public void setFreightClass(WSFreightClass value) {
        this.freightClass = value;
    }

}
