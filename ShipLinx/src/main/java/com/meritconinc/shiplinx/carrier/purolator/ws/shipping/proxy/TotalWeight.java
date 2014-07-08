
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * TotalWeight
 * 
 * <p>Java class for TotalWeight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TotalWeight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "TotalWeight", propOrder = {
    "value",
    "weightUnit"
})
public class TotalWeight {

    @XmlElement(name = "Value", required = true, type = Integer.class, nillable = true)
    protected Integer value;
    @XmlElement(name = "WeightUnit", required = true)
    protected WeightUnit weightUnit;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setValue(Integer value) {
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
