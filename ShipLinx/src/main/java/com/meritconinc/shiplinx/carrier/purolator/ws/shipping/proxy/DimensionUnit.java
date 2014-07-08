
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DimensionUnit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DimensionUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="in"/>
 *     &lt;enumeration value="cm"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DimensionUnit")
@XmlEnum
public enum DimensionUnit {


    /**
     * in
     * 
     */
    @XmlEnumValue("in")
    IN("in"),

    /**
     * cm
     * 
     */
    @XmlEnumValue("cm")
    CM("cm");
    private final String value;

    DimensionUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DimensionUnit fromValue(String v) {
        for (DimensionUnit c: DimensionUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
