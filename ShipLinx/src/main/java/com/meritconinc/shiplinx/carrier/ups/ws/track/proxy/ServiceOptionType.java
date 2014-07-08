
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceOptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceOptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}CommonCodeDescriptionType"/>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdditionalAttribute" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AdditionalCodeDescriptionValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceOptionType", propOrder = {
    "type",
    "value",
    "additionalAttribute"
})
public class ServiceOptionType {

    @XmlElement(name = "Type", required = true)
    protected CommonCodeDescriptionType type;
    @XmlElement(name = "Value")
    protected String value;
    @XmlElement(name = "AdditionalAttribute")
    protected List<AdditionalCodeDescriptionValueType> additionalAttribute;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CommonCodeDescriptionType }
     *     
     */
    public CommonCodeDescriptionType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonCodeDescriptionType }
     *     
     */
    public void setType(CommonCodeDescriptionType value) {
        this.type = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the additionalAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalCodeDescriptionValueType }
     * 
     * 
     */
    public List<AdditionalCodeDescriptionValueType> getAdditionalAttribute() {
        if (additionalAttribute == null) {
            additionalAttribute = new ArrayList<AdditionalCodeDescriptionValueType>();
        }
        return this.additionalAttribute;
    }

}
