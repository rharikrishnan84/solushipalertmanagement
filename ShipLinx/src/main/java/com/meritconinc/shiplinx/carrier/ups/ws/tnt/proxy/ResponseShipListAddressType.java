
package com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseShipListAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseShipListAddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Town" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StateProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PostcodePrimaryLow" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PostcodePrimaryHigh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseShipListAddressType", propOrder = {
    "town",
    "city",
    "stateProvinceCode",
    "countryCode",
    "postcodePrimaryLow",
    "postcodePrimaryHigh"
})
public class ResponseShipListAddressType {

    @XmlElement(name = "Town")
    protected String town;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "StateProvinceCode")
    protected String stateProvinceCode;
    @XmlElement(name = "CountryCode", required = true)
    protected String countryCode;
    @XmlElement(name = "PostcodePrimaryLow")
    protected String postcodePrimaryLow;
    @XmlElement(name = "PostcodePrimaryHigh")
    protected String postcodePrimaryHigh;

    /**
     * Gets the value of the town property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets the value of the town property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTown(String value) {
        this.town = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the stateProvinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateProvinceCode() {
        return stateProvinceCode;
    }

    /**
     * Sets the value of the stateProvinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateProvinceCode(String value) {
        this.stateProvinceCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the postcodePrimaryLow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostcodePrimaryLow() {
        return postcodePrimaryLow;
    }

    /**
     * Sets the value of the postcodePrimaryLow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostcodePrimaryLow(String value) {
        this.postcodePrimaryLow = value;
    }

    /**
     * Gets the value of the postcodePrimaryHigh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostcodePrimaryHigh() {
        return postcodePrimaryHigh;
    }

    /**
     * Sets the value of the postcodePrimaryHigh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostcodePrimaryHigh(String value) {
        this.postcodePrimaryHigh = value;
    }

}
