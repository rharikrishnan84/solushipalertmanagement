
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSItemPackage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSItemPackage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eshipplus.com/}WSReturn">
 *       &lt;sequence>
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PackageName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DefaultLength" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="DefaultHeight" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="DefaultWidth" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSItemPackage", propOrder = {
    "key",
    "packageName",
    "defaultLength",
    "defaultHeight",
    "defaultWidth"
})
public class WSItemPackage
    extends WSReturn
{

    @XmlElement(name = "Key")
    protected long key;
    @XmlElement(name = "PackageName")
    protected String packageName;
    @XmlElement(name = "DefaultLength", required = true)
    protected BigDecimal defaultLength;
    @XmlElement(name = "DefaultHeight", required = true)
    protected BigDecimal defaultHeight;
    @XmlElement(name = "DefaultWidth", required = true)
    protected BigDecimal defaultWidth;

    /**
     * Gets the value of the key property.
     * 
     */
    public long getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     */
    public void setKey(long value) {
        this.key = value;
    }

    /**
     * Gets the value of the packageName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the value of the packageName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageName(String value) {
        this.packageName = value;
    }

    /**
     * Gets the value of the defaultLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDefaultLength() {
        return defaultLength;
    }

    /**
     * Sets the value of the defaultLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDefaultLength(BigDecimal value) {
        this.defaultLength = value;
    }

    /**
     * Gets the value of the defaultHeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDefaultHeight() {
        return defaultHeight;
    }

    /**
     * Sets the value of the defaultHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDefaultHeight(BigDecimal value) {
        this.defaultHeight = value;
    }

    /**
     * Gets the value of the defaultWidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDefaultWidth() {
        return defaultWidth;
    }

    /**
     * Sets the value of the defaultWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDefaultWidth(BigDecimal value) {
        this.defaultWidth = value;
    }

}
