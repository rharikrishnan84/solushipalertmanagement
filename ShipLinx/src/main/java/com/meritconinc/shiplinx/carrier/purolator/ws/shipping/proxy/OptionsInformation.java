
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * OptionsInformation
 * 
 * <p>Java class for OptionsInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OptionsInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Options" type="{http://purolator.com/pws/datatypes/v1}ArrayOfOptionIDValuePair"/>
 *         &lt;element name="ExpressChequeAddress" type="{http://purolator.com/pws/datatypes/v1}Address" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OptionsInformation", propOrder = {
    "options",
    "expressChequeAddress"
})
public class OptionsInformation {

    @XmlElement(name = "Options", required = true, nillable = true)
    protected ArrayOfOptionIDValuePair options;
    @XmlElementRef(name = "ExpressChequeAddress", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Address> expressChequeAddress;

    /**
     * Gets the value of the options property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOptionIDValuePair }
     *     
     */
    public ArrayOfOptionIDValuePair getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOptionIDValuePair }
     *     
     */
    public void setOptions(ArrayOfOptionIDValuePair value) {
        this.options = value;
    }

    /**
     * Gets the value of the expressChequeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Address }{@code >}
     *     
     */
    public JAXBElement<Address> getExpressChequeAddress() {
        return expressChequeAddress;
    }

    /**
     * Sets the value of the expressChequeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Address }{@code >}
     *     
     */
    public void setExpressChequeAddress(JAXBElement<Address> value) {
        this.expressChequeAddress = value;
    }

}
