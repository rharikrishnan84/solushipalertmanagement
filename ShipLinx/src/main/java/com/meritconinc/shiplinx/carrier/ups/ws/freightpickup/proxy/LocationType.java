
package com.meritconinc.shiplinx.carrier.ups.ws.freightpickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LocationElementName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XPathOfElement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginalValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationType", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Error/v1.1", propOrder = {
    "locationElementName",
    "xPathOfElement",
    "originalValue"
})
public class LocationType {

    @XmlElement(name = "LocationElementName")
    protected String locationElementName;
    @XmlElement(name = "XPathOfElement")
    protected String xPathOfElement;
    @XmlElement(name = "OriginalValue")
    protected String originalValue;

    /**
     * Gets the value of the locationElementName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationElementName() {
        return locationElementName;
    }

    /**
     * Sets the value of the locationElementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationElementName(String value) {
        this.locationElementName = value;
    }

    /**
     * Gets the value of the xPathOfElement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPathOfElement() {
        return xPathOfElement;
    }

    /**
     * Sets the value of the xPathOfElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPathOfElement(String value) {
        this.xPathOfElement = value;
    }

    /**
     * Gets the value of the originalValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalValue() {
        return originalValue;
    }

    /**
     * Sets the value of the originalValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalValue(String value) {
        this.originalValue = value;
    }

}
