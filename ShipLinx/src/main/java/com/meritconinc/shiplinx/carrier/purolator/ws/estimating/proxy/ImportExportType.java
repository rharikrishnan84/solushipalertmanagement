
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImportExportType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ImportExportType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Permanent"/>
 *     &lt;enumeration value="Temporary"/>
 *     &lt;enumeration value="Repair"/>
 *     &lt;enumeration value="Return"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ImportExportType")
@XmlEnum
public enum ImportExportType {


    /**
     * Permanent
     * 
     */
    @XmlEnumValue("Permanent")
    PERMANENT("Permanent"),

    /**
     * Temporary
     * 
     */
    @XmlEnumValue("Temporary")
    TEMPORARY("Temporary"),

    /**
     * Repair
     * 
     */
    @XmlEnumValue("Repair")
    REPAIR("Repair"),

    /**
     * Return
     * 
     */
    @XmlEnumValue("Return")
    RETURN("Return");
    private final String value;

    ImportExportType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ImportExportType fromValue(String v) {
        for (ImportExportType c: ImportExportType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
