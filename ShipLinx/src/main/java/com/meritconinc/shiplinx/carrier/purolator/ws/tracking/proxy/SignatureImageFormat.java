
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SignatureImageFormat.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SignatureImageFormat">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GIF"/>
 *     &lt;enumeration value="Bitmap"/>
 *     &lt;enumeration value="OriginalUncompressed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SignatureImageFormat")
@XmlEnum
public enum SignatureImageFormat {


    /**
     * GIF
     * 
     */
    GIF("GIF"),

    /**
     * Bitmap
     * 
     */
    @XmlEnumValue("Bitmap")
    BITMAP("Bitmap"),

    /**
     * OriginalUncompressed
     * 
     */
    @XmlEnumValue("OriginalUncompressed")
    ORIGINAL_UNCOMPRESSED("OriginalUncompressed");
    private final String value;

    SignatureImageFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SignatureImageFormat fromValue(String v) {
        for (SignatureImageFormat c: SignatureImageFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
