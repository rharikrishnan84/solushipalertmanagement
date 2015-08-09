	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Dimension complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Dimension">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 * &lt;element name="DimensionUnit" type="{http://purolator.com/pws/datatypes/v1}DimensionUnit"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dimension", propOrder = {
 "value",
 "dimensionUnit"
})
public class Dimension {

 @XmlElement(name = "Value", required = true, nillable = true)
 protected BigDecimal value;
 @XmlElement(name = "DimensionUnit", required = true)
 protected DimensionUnit dimensionUnit;

 /**
 * Gets the value of the value property.
 *
 * @return
 * possible object is
 * {@link BigDecimal }
 *
 */
 public BigDecimal getValue() {
 return value;
 }

 /**
 * Sets the value of the value property.
 *
 * @param value
 * allowed object is
 * {@link BigDecimal }
 *
 */
 public void setValue(BigDecimal value) {
 this.value = value;
 }

 /**
 * Gets the value of the dimensionUnit property.
 *
 * @return
 * possible object is
 * {@link DimensionUnit }
 *
 */
 public DimensionUnit getDimensionUnit() {
 return dimensionUnit;
 }

 /**
 * Sets the value of the dimensionUnit property.
 *
 * @param value
 * allowed object is
 * {@link DimensionUnit }
 *
 */
 public void setDimensionUnit(DimensionUnit value) {
 this.dimensionUnit = value;
 }

}