
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeCodeCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChargeCodeCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Freight"/>
 *     &lt;enumeration value="Fuel"/>
 *     &lt;enumeration value="Accessorial"/>
 *     &lt;enumeration value="Service"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChargeCodeCategory")
@XmlEnum
public enum ChargeCodeCategory {

    @XmlEnumValue("Freight")
    FREIGHT("Freight"),
    @XmlEnumValue("Fuel")
    FUEL("Fuel"),
    @XmlEnumValue("Accessorial")
    ACCESSORIAL("Accessorial"),
    @XmlEnumValue("Service")
    SERVICE("Service");
    private final String value;

    ChargeCodeCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChargeCodeCategory fromValue(String v) {
        for (ChargeCodeCategory c: ChargeCodeCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
