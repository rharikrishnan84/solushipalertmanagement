
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingReferenceNumberType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BookingReferenceNumberType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Shipment"/>
 *     &lt;enumeration value="Quote"/>
 *     &lt;enumeration value="NotApplicable"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BookingReferenceNumberType")
@XmlEnum
public enum BookingReferenceNumberType {

    @XmlEnumValue("Shipment")
    SHIPMENT("Shipment"),
    @XmlEnumValue("Quote")
    QUOTE("Quote"),
    @XmlEnumValue("NotApplicable")
    NOT_APPLICABLE("NotApplicable");
    private final String value;

    BookingReferenceNumberType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BookingReferenceNumberType fromValue(String v) {
        for (BookingReferenceNumberType c: BookingReferenceNumberType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
