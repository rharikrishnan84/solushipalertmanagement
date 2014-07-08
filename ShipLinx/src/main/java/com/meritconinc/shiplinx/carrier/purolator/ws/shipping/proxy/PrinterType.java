
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrinterType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrinterType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Regular"/>
 *     &lt;enumeration value="Thermal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrinterType")
@XmlEnum
public enum PrinterType {


    /**
     * Regular
     * 
     */
    @XmlEnumValue("Regular")
    REGULAR("Regular"),

    /**
     * Thermal
     * 
     */
    @XmlEnumValue("Thermal")
    THERMAL("Thermal");
    private final String value;

    PrinterType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrinterType fromValue(String v) {
        for (PrinterType c: PrinterType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
