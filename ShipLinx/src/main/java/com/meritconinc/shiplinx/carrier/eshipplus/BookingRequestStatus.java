
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingRequestStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BookingRequestStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PendingCover"/>
 *     &lt;enumeration value="Covered"/>
 *     &lt;enumeration value="Cancelled"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BookingRequestStatus")
@XmlEnum
public enum BookingRequestStatus {

    @XmlEnumValue("PendingCover")
    PENDING_COVER("PendingCover"),
    @XmlEnumValue("Covered")
    COVERED("Covered"),
    @XmlEnumValue("Cancelled")
    CANCELLED("Cancelled");
    private final String value;

    BookingRequestStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BookingRequestStatus fromValue(String v) {
        for (BookingRequestStatus c: BookingRequestStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
