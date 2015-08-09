package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * FreightBoolValuePair
 *
 * <p>Java class for BoolValuePair complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BoolValuePair">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Keyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoolValuePair", propOrder = {
 "keyword",
 "value"
})
public class BoolValuePair {

 @XmlElement(name = "Keyword", required = true, nillable = true)
 protected String keyword;
 @XmlElement(name = "Value")
 protected boolean value;

 /**
 * Gets the value of the keyword property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getKeyword() {
 return keyword;
 }

 /**
 * Sets the value of the keyword property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setKeyword(String value) {
 this.keyword = value;
 }

 /**
 * Gets the value of the value property.
 *
 */
 public boolean isValue() {
 return value;
 }

 /**
 * Sets the value of the value property.
 *
 */
 public void setValue(boolean value) {
 this.value = value;
 }

}