
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ValueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="String"/>
 *     &lt;enumeration value="Decimal"/>
 *     &lt;enumeration value="DateTime"/>
 *     &lt;enumeration value="Enumeration"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ValueType")
@XmlEnum
public enum ValueType {


    /**
     * String
     * 
     */
    @XmlEnumValue("String")
    STRING("String"),

    /**
     * Decimal
     * 
     */
    @XmlEnumValue("Decimal")
    DECIMAL("Decimal"),

    /**
     * DateTime
     * 
     */
    @XmlEnumValue("DateTime")
    DATE_TIME("DateTime"),

    /**
     * Enumeration
     * 
     */
    @XmlEnumValue("Enumeration")
    ENUMERATION("Enumeration");
    private final String value;

    ValueType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValueType fromValue(String v) {
        for (ValueType c: ValueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
