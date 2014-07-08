
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DocumentStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Queued"/>
 *     &lt;enumeration value="Processing"/>
 *     &lt;enumeration value="Completed"/>
 *     &lt;enumeration value="Error"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DocumentStatus")
@XmlEnum
public enum DocumentStatus {


    /**
     * Pending
     * 
     */
    @XmlEnumValue("Pending")
    PENDING("Pending"),

    /**
     * Queued
     * 
     */
    @XmlEnumValue("Queued")
    QUEUED("Queued"),

    /**
     * Processing
     * 
     */
    @XmlEnumValue("Processing")
    PROCESSING("Processing"),

    /**
     * Completed
     * 
     */
    @XmlEnumValue("Completed")
    COMPLETED("Completed"),

    /**
     * Error
     * 
     */
    @XmlEnumValue("Error")
    ERROR("Error");
    private final String value;

    DocumentStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DocumentStatus fromValue(String v) {
        for (DocumentStatus c: DocumentStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
