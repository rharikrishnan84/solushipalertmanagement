
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreditCardType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CreditCardType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Visa"/>
 *     &lt;enumeration value="MasterCard"/>
 *     &lt;enumeration value="AmericanExpress"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CreditCardType")
@XmlEnum
public enum CreditCardType {


    /**
     * Visa
     * 
     */
    @XmlEnumValue("Visa")
    VISA("Visa"),

    /**
     * MasterCard
     * 
     */
    @XmlEnumValue("MasterCard")
    MASTER_CARD("MasterCard"),

    /**
     * AmericanExpress
     * 
     */
    @XmlEnumValue("AmericanExpress")
    AMERICAN_EXPRESS("AmericanExpress");
    private final String value;

    CreditCardType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CreditCardType fromValue(String v) {
        for (CreditCardType c: CreditCardType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
