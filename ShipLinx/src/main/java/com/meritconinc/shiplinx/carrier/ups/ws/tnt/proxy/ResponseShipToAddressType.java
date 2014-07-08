
package com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseShipToAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseShipToAddressType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}ResponseShipFromAddressType">
 *       &lt;sequence>
 *         &lt;element name="ResidentialAddressIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseShipToAddressType", propOrder = {
    "residentialAddressIndicator"
})
public class ResponseShipToAddressType
    extends ResponseShipFromAddressType
{

    @XmlElement(name = "ResidentialAddressIndicator")
    protected String residentialAddressIndicator;

    /**
     * Gets the value of the residentialAddressIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidentialAddressIndicator() {
        return residentialAddressIndicator;
    }

    /**
     * Sets the value of the residentialAddressIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidentialAddressIndicator(String value) {
        this.residentialAddressIndicator = value;
    }

}
