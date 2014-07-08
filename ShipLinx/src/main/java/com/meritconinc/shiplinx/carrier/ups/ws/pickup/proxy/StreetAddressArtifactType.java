
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StreetAddressArtifactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StreetAddressArtifactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StreetNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StreetPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StreetName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StreetType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StreetSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnparsedStreetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StreetAddressArtifactSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StreetAddressMatchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StreetAddressArtifactType", propOrder = {
    "streetNumber",
    "streetPrefix",
    "streetName",
    "streetType",
    "streetSuffix",
    "unparsedStreetAddress",
    "streetAddressArtifactSource",
    "streetAddressMatchCode"
})
public class StreetAddressArtifactType {

    @XmlElement(name = "StreetNumber")
    protected String streetNumber;
    @XmlElement(name = "StreetPrefix")
    protected String streetPrefix;
    @XmlElement(name = "StreetName")
    protected String streetName;
    @XmlElement(name = "StreetType")
    protected String streetType;
    @XmlElement(name = "StreetSuffix")
    protected String streetSuffix;
    @XmlElement(name = "UnparsedStreetAddress")
    protected String unparsedStreetAddress;
    @XmlElement(name = "StreetAddressArtifactSource")
    protected String streetAddressArtifactSource;
    @XmlElement(name = "StreetAddressMatchCode")
    protected String streetAddressMatchCode;

    /**
     * Gets the value of the streetNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the value of the streetNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetNumber(String value) {
        this.streetNumber = value;
    }

    /**
     * Gets the value of the streetPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetPrefix() {
        return streetPrefix;
    }

    /**
     * Sets the value of the streetPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetPrefix(String value) {
        this.streetPrefix = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the streetType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetType() {
        return streetType;
    }

    /**
     * Sets the value of the streetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetType(String value) {
        this.streetType = value;
    }

    /**
     * Gets the value of the streetSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetSuffix() {
        return streetSuffix;
    }

    /**
     * Sets the value of the streetSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetSuffix(String value) {
        this.streetSuffix = value;
    }

    /**
     * Gets the value of the unparsedStreetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnparsedStreetAddress() {
        return unparsedStreetAddress;
    }

    /**
     * Sets the value of the unparsedStreetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnparsedStreetAddress(String value) {
        this.unparsedStreetAddress = value;
    }

    /**
     * Gets the value of the streetAddressArtifactSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetAddressArtifactSource() {
        return streetAddressArtifactSource;
    }

    /**
     * Sets the value of the streetAddressArtifactSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetAddressArtifactSource(String value) {
        this.streetAddressArtifactSource = value;
    }

    /**
     * Gets the value of the streetAddressMatchCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetAddressMatchCode() {
        return streetAddressMatchCode;
    }

    /**
     * Sets the value of the streetAddressMatchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetAddressMatchCode(String value) {
        this.streetAddressMatchCode = value;
    }

}
