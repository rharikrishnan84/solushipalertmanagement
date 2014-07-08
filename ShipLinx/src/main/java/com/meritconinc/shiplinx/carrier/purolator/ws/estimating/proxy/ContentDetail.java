
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * ContentDetail
 * 
 * <p>Java class for ContentDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContentDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HarmonizedCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountryOfManufacture" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UnitValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NAFTADocumentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FDADocumentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FCCDocumentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SenderIsProducerIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TextileIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TextileManufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentDetail", propOrder = {
    "description",
    "harmonizedCode",
    "countryOfManufacture",
    "productCode",
    "unitValue",
    "quantity",
    "naftaDocumentIndicator",
    "fdaDocumentIndicator",
    "fccDocumentIndicator",
    "senderIsProducerIndicator",
    "textileIndicator",
    "textileManufacturer"
})
public class ContentDetail {

    @XmlElement(name = "Description", required = true, nillable = true)
    protected String description;
    @XmlElement(name = "HarmonizedCode", required = true, nillable = true)
    protected String harmonizedCode;
    @XmlElement(name = "CountryOfManufacture", required = true, nillable = true)
    protected String countryOfManufacture;
    @XmlElement(name = "ProductCode", required = true, nillable = true)
    protected String productCode;
    @XmlElement(name = "UnitValue", required = true)
    protected BigDecimal unitValue;
    @XmlElement(name = "Quantity")
    protected int quantity;
    @XmlElement(name = "NAFTADocumentIndicator")
    protected Boolean naftaDocumentIndicator;
    @XmlElement(name = "FDADocumentIndicator")
    protected Boolean fdaDocumentIndicator;
    @XmlElement(name = "FCCDocumentIndicator")
    protected Boolean fccDocumentIndicator;
    @XmlElement(name = "SenderIsProducerIndicator")
    protected Boolean senderIsProducerIndicator;
    @XmlElement(name = "TextileIndicator")
    protected Boolean textileIndicator;
    @XmlElementRef(name = "TextileManufacturer", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> textileManufacturer;

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
     * Gets the value of the harmonizedCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHarmonizedCode() {
        return harmonizedCode;
    }

    /**
     * Sets the value of the harmonizedCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHarmonizedCode(String value) {
        this.harmonizedCode = value;
    }

    /**
     * Gets the value of the countryOfManufacture property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryOfManufacture() {
        return countryOfManufacture;
    }

    /**
     * Sets the value of the countryOfManufacture property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryOfManufacture(String value) {
        this.countryOfManufacture = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the unitValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnitValue() {
        return unitValue;
    }

    /**
     * Sets the value of the unitValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnitValue(BigDecimal value) {
        this.unitValue = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the naftaDocumentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNAFTADocumentIndicator() {
        return naftaDocumentIndicator;
    }

    /**
     * Sets the value of the naftaDocumentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNAFTADocumentIndicator(Boolean value) {
        this.naftaDocumentIndicator = value;
    }

    /**
     * Gets the value of the fdaDocumentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFDADocumentIndicator() {
        return fdaDocumentIndicator;
    }

    /**
     * Sets the value of the fdaDocumentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFDADocumentIndicator(Boolean value) {
        this.fdaDocumentIndicator = value;
    }

    /**
     * Gets the value of the fccDocumentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFCCDocumentIndicator() {
        return fccDocumentIndicator;
    }

    /**
     * Sets the value of the fccDocumentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFCCDocumentIndicator(Boolean value) {
        this.fccDocumentIndicator = value;
    }

    /**
     * Gets the value of the senderIsProducerIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSenderIsProducerIndicator() {
        return senderIsProducerIndicator;
    }

    /**
     * Sets the value of the senderIsProducerIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSenderIsProducerIndicator(Boolean value) {
        this.senderIsProducerIndicator = value;
    }

    /**
     * Gets the value of the textileIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTextileIndicator() {
        return textileIndicator;
    }

    /**
     * Sets the value of the textileIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTextileIndicator(Boolean value) {
        this.textileIndicator = value;
    }

    /**
     * Gets the value of the textileManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTextileManufacturer() {
        return textileManufacturer;
    }

    /**
     * Sets the value of the textileManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTextileManufacturer(JAXBElement<String> value) {
        this.textileManufacturer = value;
    }

}
