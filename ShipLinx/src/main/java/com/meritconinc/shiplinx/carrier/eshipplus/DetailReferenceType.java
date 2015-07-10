
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetailReferenceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DetailReferenceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Shipment"/>
 *     &lt;enumeration value="ServiceTicket"/>
 *     &lt;enumeration value="Miscellaneous"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DetailReferenceType")
@XmlEnum
public enum DetailReferenceType {

    @XmlEnumValue("Shipment")
    SHIPMENT("Shipment"),
    @XmlEnumValue("ServiceTicket")
    SERVICE_TICKET("ServiceTicket"),
    @XmlEnumValue("Miscellaneous")
    MISCELLANEOUS("Miscellaneous");
    private final String value;

    DetailReferenceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DetailReferenceType fromValue(String v) {
        for (DetailReferenceType c: DetailReferenceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
