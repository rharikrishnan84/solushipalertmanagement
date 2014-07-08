//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.01 at 06:34:59 PM EST 
//


package com.meritconinc.shiplinx.carrier.dhl.xml;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipValResponsePiece complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipValResponsePiece">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PieceNumber" type="{http://www.dhl.com/datatypes}PieceNumber"/>
 *         &lt;element name="Depth" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Weight" type="{http://www.dhl.com/datatypes}Weight" minOccurs="0"/>
 *         &lt;element name="PackageType" type="{http://www.dhl.com/datatypes}PackageType" minOccurs="0"/>
 *         &lt;element name="DimWeight" type="{http://www.dhl.com/datatypes}Weight" minOccurs="0"/>
 *         &lt;element name="PieceContents" type="{http://www.dhl.com/datatypes}PieceContents" minOccurs="0"/>
 *         &lt;element name="DataIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LicensePlate" type="{http://www.dhl.com/datatypes}PieceID"/>
 *         &lt;element name="LicensePlateBarCode" type="{http://www.dhl.com/datatypes}BarCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipValResponsePiece", propOrder = {
    "pieceNumber",
    "depth",
    "width",
    "height",
    "weight",
    "packageType",
    "dimWeight",
    "pieceContents",
    "dataIdentifier",
    "licensePlate",
    "licensePlateBarCode"
})
public class ShipValResponsePiece {

    @XmlElement(name = "PieceNumber", required = true)
    protected BigInteger pieceNumber;
    @XmlElement(name = "Depth")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger depth;
    @XmlElement(name = "Width")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger width;
    @XmlElement(name = "Height")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger height;
    @XmlElement(name = "Weight")
    protected BigDecimal weight;
    @XmlElement(name = "PackageType")
    protected PackageType packageType;
    @XmlElement(name = "DimWeight")
    protected BigDecimal dimWeight;
    @XmlElement(name = "PieceContents")
    protected String pieceContents;
    @XmlElement(name = "DataIdentifier", required = true)
    protected String dataIdentifier;
    @XmlElement(name = "LicensePlate", required = true)
    protected String licensePlate;
    @XmlElement(name = "LicensePlateBarCode", required = true)
    protected byte[] licensePlateBarCode;

    /**
     * Gets the value of the pieceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPieceNumber() {
        return pieceNumber;
    }

    /**
     * Sets the value of the pieceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPieceNumber(BigInteger value) {
        this.pieceNumber = value;
    }

    /**
     * Gets the value of the depth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDepth(BigInteger value) {
        this.depth = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWidth(BigInteger value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHeight(BigInteger value) {
        this.height = value;
    }

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
     * Gets the value of the packageType property.
     * 
     * @return
     *     possible object is
     *     {@link PackageType }
     *     
     */
    public PackageType getPackageType() {
        return packageType;
    }

    /**
     * Sets the value of the packageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageType }
     *     
     */
    public void setPackageType(PackageType value) {
        this.packageType = value;
    }

    /**
     * Gets the value of the dimWeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDimWeight() {
        return dimWeight;
    }

    /**
     * Sets the value of the dimWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDimWeight(BigDecimal value) {
        this.dimWeight = value;
    }

    /**
     * Gets the value of the pieceContents property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPieceContents() {
        return pieceContents;
    }

    /**
     * Sets the value of the pieceContents property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPieceContents(String value) {
        this.pieceContents = value;
    }

    /**
     * Gets the value of the dataIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataIdentifier() {
        return dataIdentifier;
    }

    /**
     * Sets the value of the dataIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataIdentifier(String value) {
        this.dataIdentifier = value;
    }

    /**
     * Gets the value of the licensePlate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Sets the value of the licensePlate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicensePlate(String value) {
        this.licensePlate = value;
    }

    /**
     * Gets the value of the licensePlateBarCode property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLicensePlateBarCode() {
        return licensePlateBarCode;
    }

    /**
     * Sets the value of the licensePlateBarCode property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLicensePlateBarCode(byte[] value) {
        this.licensePlateBarCode = value;
    }

}
