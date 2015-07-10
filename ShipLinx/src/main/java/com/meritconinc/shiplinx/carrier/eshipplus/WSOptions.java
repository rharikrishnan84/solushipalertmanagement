
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSOptions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSOptions">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eshipplus.com/}WSReturn">
 *       &lt;sequence>
 *         &lt;element name="AccessorialServices" type="{http://www.eshipplus.com/}ArrayOfWSAccessorialService" minOccurs="0"/>
 *         &lt;element name="LTLFreightClasses" type="{http://www.eshipplus.com/}ArrayOfWSFreightClass" minOccurs="0"/>
 *         &lt;element name="ItemPackaging" type="{http://www.eshipplus.com/}ArrayOfWSItemPackage" minOccurs="0"/>
 *         &lt;element name="Countries" type="{http://www.eshipplus.com/}ArrayOfWSCountry" minOccurs="0"/>
 *         &lt;element name="Times" type="{http://www.eshipplus.com/}ArrayOfWSTime2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSOptions", propOrder = {
    "accessorialServices",
    "ltlFreightClasses",
    "itemPackaging",
    "countries",
    "times"
})
public class WSOptions
    extends WSReturn
{

    @XmlElement(name = "AccessorialServices")
    protected ArrayOfWSAccessorialService accessorialServices;
    @XmlElement(name = "LTLFreightClasses")
    protected ArrayOfWSFreightClass ltlFreightClasses;
    @XmlElement(name = "ItemPackaging")
    protected ArrayOfWSItemPackage itemPackaging;
    @XmlElement(name = "Countries")
    protected ArrayOfWSCountry countries;
    @XmlElement(name = "Times")
    protected ArrayOfWSTime2 times;

    /**
     * Gets the value of the accessorialServices property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSAccessorialService }
     *     
     */
    public ArrayOfWSAccessorialService getAccessorialServices() {
        return accessorialServices;
    }

    /**
     * Sets the value of the accessorialServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSAccessorialService }
     *     
     */
    public void setAccessorialServices(ArrayOfWSAccessorialService value) {
        this.accessorialServices = value;
    }

    /**
     * Gets the value of the ltlFreightClasses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSFreightClass }
     *     
     */
    public ArrayOfWSFreightClass getLTLFreightClasses() {
        return ltlFreightClasses;
    }

    /**
     * Sets the value of the ltlFreightClasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSFreightClass }
     *     
     */
    public void setLTLFreightClasses(ArrayOfWSFreightClass value) {
        this.ltlFreightClasses = value;
    }

    /**
     * Gets the value of the itemPackaging property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSItemPackage }
     *     
     */
    public ArrayOfWSItemPackage getItemPackaging() {
        return itemPackaging;
    }

    /**
     * Sets the value of the itemPackaging property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSItemPackage }
     *     
     */
    public void setItemPackaging(ArrayOfWSItemPackage value) {
        this.itemPackaging = value;
    }

    /**
     * Gets the value of the countries property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSCountry }
     *     
     */
    public ArrayOfWSCountry getCountries() {
        return countries;
    }

    /**
     * Sets the value of the countries property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSCountry }
     *     
     */
    public void setCountries(ArrayOfWSCountry value) {
        this.countries = value;
    }

    /**
     * Gets the value of the times property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSTime2 }
     *     
     */
    public ArrayOfWSTime2 getTimes() {
        return times;
    }

    /**
     * Sets the value of the times property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSTime2 }
     *     
     */
    public void setTimes(ArrayOfWSTime2 value) {
        this.times = value;
    }

}
