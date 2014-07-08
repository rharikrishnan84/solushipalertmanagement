
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * GetServiceRulesResponse
 * 
 * <p>Java class for GetServiceRulesResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetServiceRulesResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="ServiceRules" type="{http://purolator.com/pws/datatypes/v1}ArrayOfServiceRule" minOccurs="0"/>
 *         &lt;element name="ServiceOptionRules" type="{http://purolator.com/pws/datatypes/v1}ArrayOfServiceOptionRules" minOccurs="0"/>
 *         &lt;element name="OptionRules" type="{http://purolator.com/pws/datatypes/v1}ArrayOfOptionRule" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetServiceRulesResponseContainer", propOrder = {
    "serviceRules",
    "serviceOptionRules",
    "optionRules"
})
public class GetServiceRulesResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "ServiceRules", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfServiceRule> serviceRules;
    @XmlElementRef(name = "ServiceOptionRules", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfServiceOptionRules> serviceOptionRules;
    @XmlElementRef(name = "OptionRules", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOptionRule> optionRules;

    /**
     * Gets the value of the serviceRules property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfServiceRule }{@code >}
     *     
     */
    public JAXBElement<ArrayOfServiceRule> getServiceRules() {
        return serviceRules;
    }

    /**
     * Sets the value of the serviceRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfServiceRule }{@code >}
     *     
     */
    public void setServiceRules(JAXBElement<ArrayOfServiceRule> value) {
        this.serviceRules = value;
    }

    /**
     * Gets the value of the serviceOptionRules property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfServiceOptionRules }{@code >}
     *     
     */
    public JAXBElement<ArrayOfServiceOptionRules> getServiceOptionRules() {
        return serviceOptionRules;
    }

    /**
     * Sets the value of the serviceOptionRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfServiceOptionRules }{@code >}
     *     
     */
    public void setServiceOptionRules(JAXBElement<ArrayOfServiceOptionRules> value) {
        this.serviceOptionRules = value;
    }

    /**
     * Gets the value of the optionRules property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionRule }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOptionRule> getOptionRules() {
        return optionRules;
    }

    /**
     * Sets the value of the optionRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionRule }{@code >}
     *     
     */
    public void setOptionRules(JAXBElement<ArrayOfOptionRule> value) {
        this.optionRules = value;
    }

}
