
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DocumentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Pdf"/>
 *     &lt;enumeration value="Html"/>
 *     &lt;enumeration value="Png"/>
 *     &lt;enumeration value="Gif"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DocumentType")
@XmlEnum
public enum DocumentType {

    @XmlEnumValue("Pdf")
    PDF("Pdf"),
    @XmlEnumValue("Html")
    HTML("Html"),
    @XmlEnumValue("Png")
    PNG("Png"),
    @XmlEnumValue("Gif")
    GIF("Gif");
    private final String value;

    DocumentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DocumentType fromValue(String v) {
        for (DocumentType c: DocumentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
