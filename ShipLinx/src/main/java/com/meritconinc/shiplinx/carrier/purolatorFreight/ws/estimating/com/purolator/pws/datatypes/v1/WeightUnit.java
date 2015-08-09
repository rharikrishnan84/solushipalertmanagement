package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeightUnit.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WeightUnit">
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 * &lt;enumeration value="lb"/>
 * &lt;enumeration value="kg"/>
 * &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "WeightUnit")
@XmlEnum
public enum WeightUnit {


 /**
 * lb
 *
 */
 @XmlEnumValue("lb")
 LB("lb"),

 /**
 * kg
 *
 */
 @XmlEnumValue("kg")
 KG("kg");
 private final String value;

 WeightUnit(String v) {
 value = v;
 }

 public String value() {
 return value;
 }

 public static WeightUnit fromValue(String v) {
 for (WeightUnit c: WeightUnit.values()) {
 if (c.value.equals(v)) {
 return c;
 }
 }
 throw new IllegalArgumentException(v);
 }

}