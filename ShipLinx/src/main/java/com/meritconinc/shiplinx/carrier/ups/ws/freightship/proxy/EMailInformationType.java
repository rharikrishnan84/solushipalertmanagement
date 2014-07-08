
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EMailInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EMailInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EMailType" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType"/>
 *         &lt;element name="EMail" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}EMailType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EMailInformationType", propOrder = {
    "eMailType",
    "eMail"
})
public class EMailInformationType {

    @XmlElement(name = "EMailType", required = true)
    protected ShipCodeDescriptionType eMailType;
    @XmlElement(name = "EMail", required = true)
    protected EMailType eMail;

    /**
     * Gets the value of the eMailType property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getEMailType() {
        return eMailType;
    }

    /**
     * Sets the value of the eMailType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setEMailType(ShipCodeDescriptionType value) {
        this.eMailType = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link EMailType }
     *     
     */
    public EMailType getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link EMailType }
     *     
     */
    public void setEMail(EMailType value) {
        this.eMail = value;
    }

}
