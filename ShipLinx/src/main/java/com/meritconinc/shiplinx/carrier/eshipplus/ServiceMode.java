
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceMode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ServiceMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LessThanTruckload"/>
 *     &lt;enumeration value="Truckload"/>
 *     &lt;enumeration value="Air"/>
 *     &lt;enumeration value="Rail"/>
 *     &lt;enumeration value="SmallPackage"/>
 *     &lt;enumeration value="NotApplicable"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ServiceMode")
@XmlEnum
public enum ServiceMode {

    @XmlEnumValue("LessThanTruckload")
    LESS_THAN_TRUCKLOAD("LessThanTruckload"),
    @XmlEnumValue("Truckload")
    TRUCKLOAD("Truckload"),
    @XmlEnumValue("Air")
    AIR("Air"),
    @XmlEnumValue("Rail")
    RAIL("Rail"),
    @XmlEnumValue("SmallPackage")
    SMALL_PACKAGE("SmallPackage"),
    @XmlEnumValue("NotApplicable")
    NOT_APPLICABLE("NotApplicable");
    private final String value;

    ServiceMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceMode fromValue(String v) {
        for (ServiceMode c: ServiceMode.values()) {
            if (c.name().equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
