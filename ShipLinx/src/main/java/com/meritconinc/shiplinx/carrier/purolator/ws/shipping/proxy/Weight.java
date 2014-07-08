
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Weight
 * 
 * <p>Java class for Weight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Weight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="WeightUnit" type="{http://purolator.com/pws/datatypes/v1}WeightUnit"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Weight", propOrder = {
    "value",
    "weightUnit"
})
public class Weight {

    @XmlElement(name = "Value", required = true, nillable = true)
    protected BigDecimal value;
    @XmlElement(name = "WeightUnit", required = true)
    protected WeightUnit weightUnit;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets the value of the weightUnit property.
     * 
     * @return
     *     possible object is
     *     {@link WeightUnit }
     *     
     */
    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    /**
     * Sets the value of the weightUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightUnit }
     *     
     */
    public void setWeightUnit(WeightUnit value) {
        this.weightUnit = value;
    }

}
