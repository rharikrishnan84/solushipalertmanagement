
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackageServiceOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageServiceOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeliveryConfirmation" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}DeliveryConfirmationType" minOccurs="0"/>
 *         &lt;element name="COD" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}CODType" minOccurs="0"/>
 *         &lt;element name="DeclaredValue" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}InsuredValueType" minOccurs="0"/>
 *         &lt;element name="VerbalConfirmationIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageServiceOptionsType", propOrder = {
    "deliveryConfirmation",
    "cod",
    "declaredValue",
    "verbalConfirmationIndicator"
})
public class PackageServiceOptionsType {

    @XmlElement(name = "DeliveryConfirmation")
    protected DeliveryConfirmationType deliveryConfirmation;
    @XmlElement(name = "COD")
    protected CODType cod;
    @XmlElement(name = "DeclaredValue")
    protected InsuredValueType declaredValue;
    @XmlElement(name = "VerbalConfirmationIndicator")
    protected String verbalConfirmationIndicator;

    /**
     * Gets the value of the deliveryConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryConfirmationType }
     *     
     */
    public DeliveryConfirmationType getDeliveryConfirmation() {
        return deliveryConfirmation;
    }

    /**
     * Sets the value of the deliveryConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryConfirmationType }
     *     
     */
    public void setDeliveryConfirmation(DeliveryConfirmationType value) {
        this.deliveryConfirmation = value;
    }

    /**
     * Gets the value of the cod property.
     * 
     * @return
     *     possible object is
     *     {@link CODType }
     *     
     */
    public CODType getCOD() {
        return cod;
    }

    /**
     * Sets the value of the cod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CODType }
     *     
     */
    public void setCOD(CODType value) {
        this.cod = value;
    }

    /**
     * Gets the value of the declaredValue property.
     * 
     * @return
     *     possible object is
     *     {@link InsuredValueType }
     *     
     */
    public InsuredValueType getDeclaredValue() {
        return declaredValue;
    }

    /**
     * Sets the value of the declaredValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link InsuredValueType }
     *     
     */
    public void setDeclaredValue(InsuredValueType value) {
        this.declaredValue = value;
    }

    /**
     * Gets the value of the verbalConfirmationIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerbalConfirmationIndicator() {
        return verbalConfirmationIndicator;
    }

    /**
     * Sets the value of the verbalConfirmationIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerbalConfirmationIndicator(String value) {
        this.verbalConfirmationIndicator = value;
    }

}
