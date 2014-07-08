
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OverSeasLegType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OverSeasLegType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Dimensions" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}DimensionType"/>
 *         &lt;element name="Value" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}DimensionValueType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OverSeasLegType", propOrder = {
    "dimensions",
    "value"
})
public class OverSeasLegType {

    @XmlElement(name = "Dimensions", required = true)
    protected DimensionType dimensions;
    @XmlElement(name = "Value", required = true)
    protected DimensionValueType value;

    /**
     * Gets the value of the dimensions property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionType }
     *     
     */
    public DimensionType getDimensions() {
        return dimensions;
    }

    /**
     * Sets the value of the dimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionType }
     *     
     */
    public void setDimensions(DimensionType value) {
        this.dimensions = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionValueType }
     *     
     */
    public DimensionValueType getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionValueType }
     *     
     */
    public void setValue(DimensionValueType value) {
        this.value = value;
    }

}
