
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillDutiesToParty.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BillDutiesToParty">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Sender"/>
 *     &lt;enumeration value="Receiver"/>
 *     &lt;enumeration value="Buyer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BillDutiesToParty")
@XmlEnum
public enum BillDutiesToParty {


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
     * Buyer
     * 
     */
    @XmlEnumValue("Buyer")
    BUYER("Buyer");
    private final String value;

    BillDutiesToParty(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BillDutiesToParty fromValue(String v) {
        for (BillDutiesToParty c: BillDutiesToParty.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
