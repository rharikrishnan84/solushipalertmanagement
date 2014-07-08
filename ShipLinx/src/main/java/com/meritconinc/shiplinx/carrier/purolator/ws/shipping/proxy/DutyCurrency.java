
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DutyCurrency.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DutyCurrency">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CAD"/>
 *     &lt;enumeration value="USD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DutyCurrency")
@XmlEnum
public enum DutyCurrency {


    /**
     * CAD
     * 
     */
    CAD,

    /**
     * USD
     * 
     */
    USD;

    public String value() {
        return name();
    }

    public static DutyCurrency fromValue(String v) {
        return valueOf(v);
    }

}
