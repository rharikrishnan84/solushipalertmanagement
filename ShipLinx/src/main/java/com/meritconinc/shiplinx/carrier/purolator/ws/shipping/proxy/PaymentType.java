
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PaymentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Sender"/>
 *     &lt;enumeration value="Receiver"/>
 *     &lt;enumeration value="ThirdParty"/>
 *     &lt;enumeration value="CreditCard"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PaymentType")
@XmlEnum
public enum PaymentType {


    /**
     * Sender
     * 
     */
    @XmlEnumValue("Sender")
    SENDER("Sender"),

    /**
     * Receiver
     * 
     */
    @XmlEnumValue("Receiver")
    RECEIVER("Receiver"),

    /**
     * ThirdParty
     * 
     */
    @XmlEnumValue("ThirdParty")
    THIRD_PARTY("ThirdParty"),

    /**
     * CreditCard
     * 
     */
    @XmlEnumValue("CreditCard")
    CREDIT_CARD("CreditCard");
    private final String value;

    PaymentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PaymentType fromValue(String v) {
        for (PaymentType c: PaymentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
