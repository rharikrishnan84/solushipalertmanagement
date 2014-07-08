
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * ServiceOptionRules
 * 
 * <p>Java class for ServiceOptionRules complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceOptionRules">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Exclusions" type="{http://purolator.com/pws/datatypes/v1}ArrayOfOptionIDValuePair" minOccurs="0"/>
 *         &lt;element name="Inclusions" type="{http://purolator.com/pws/datatypes/v1}ArrayOfOptionIDValuePair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceOptionRules", propOrder = {
    "serviceID",
    "exclusions",
    "inclusions"
})
public class ServiceOptionRules {

    @XmlElement(name = "ServiceID", required = true, nillable = true)
    protected String serviceID;
    @XmlElementRef(name = "Exclusions", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOptionIDValuePair> exclusions;
    @XmlElementRef(name = "Inclusions", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOptionIDValuePair> inclusions;

    /**
     * Gets the value of the serviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the exclusions property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionIDValuePair }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOptionIDValuePair> getExclusions() {
        return exclusions;
    }

    /**
     * Sets the value of the exclusions property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionIDValuePair }{@code >}
     *     
     */
    public void setExclusions(JAXBElement<ArrayOfOptionIDValuePair> value) {
        this.exclusions = value;
    }

    /**
     * Gets the value of the inclusions property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionIDValuePair }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOptionIDValuePair> getInclusions() {
        return inclusions;
    }

    /**
     * Sets the value of the inclusions property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionIDValuePair }{@code >}
     *     
     */
    public void setInclusions(JAXBElement<ArrayOfOptionIDValuePair> value) {
        this.inclusions = value;
    }

}
