
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PONumberValidity.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PONumberValidity">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Origin"/>
 *     &lt;enumeration value="Destination"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PONumberValidity")
@XmlEnum
public enum PONumberValidity {

    @XmlEnumValue("Origin")
    ORIGIN("Origin"),
    @XmlEnumValue("Destination")
    DESTINATION("Destination");
    private final String value;

    PONumberValidity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PONumberValidity fromValue(String v) {
        for (PONumberValidity c: PONumberValidity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
