
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CODType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CODType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CODValue" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}CODValueType"/>
 *         &lt;element name="CODPaymentMethod" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateCodeDescriptionType"/>
 *         &lt;element name="CODBillingOption" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateCodeDescriptionType"/>
 *         &lt;element name="RemitTo" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RemitToType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CODType", propOrder = {
    "codValue",
    "codPaymentMethod",
    "codBillingOption",
    "remitTo"
})
public class CODType {

    @XmlElement(name = "CODValue", required = true)
    protected CODValueType codValue;
    @XmlElement(name = "CODPaymentMethod", required = true)
    protected RateCodeDescriptionType codPaymentMethod;
    @XmlElement(name = "CODBillingOption", required = true)
    protected RateCodeDescriptionType codBillingOption;
    @XmlElement(name = "RemitTo", required = true)
    protected RemitToType remitTo;

    /**
     * Gets the value of the codValue property.
     * 
     * @return
     *     possible object is
     *     {@link CODValueType }
     *     
     */
    public CODValueType getCODValue() {
        return codValue;
    }

    /**
     * Sets the value of the codValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CODValueType }
     *     
     */
    public void setCODValue(CODValueType value) {
        this.codValue = value;
    }

    /**
     * Gets the value of the codPaymentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public RateCodeDescriptionType getCODPaymentMethod() {
        return codPaymentMethod;
    }

    /**
     * Sets the value of the codPaymentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public void setCODPaymentMethod(RateCodeDescriptionType value) {
        this.codPaymentMethod = value;
    }

    /**
     * Gets the value of the codBillingOption property.
     * 
     * @return
     *     possible object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public RateCodeDescriptionType getCODBillingOption() {
        return codBillingOption;
    }

    /**
     * Sets the value of the codBillingOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public void setCODBillingOption(RateCodeDescriptionType value) {
        this.codBillingOption = value;
    }

    /**
     * Gets the value of the remitTo property.
     * 
     * @return
     *     possible object is
     *     {@link RemitToType }
     *     
     */
    public RemitToType getRemitTo() {
        return remitTo;
    }

    /**
     * Sets the value of the remitTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemitToType }
     *     
     */
    public void setRemitTo(RemitToType value) {
        this.remitTo = value;
    }

}
