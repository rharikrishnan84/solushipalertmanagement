
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfServiceOptionRules complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfServiceOptionRules">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceOptionRules" type="{http://purolator.com/pws/datatypes/v1}ServiceOptionRules" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfServiceOptionRules", propOrder = {
    "serviceOptionRules"
})
public class ArrayOfServiceOptionRules {

    @XmlElement(name = "ServiceOptionRules", nillable = true)
    protected List<ServiceOptionRules> serviceOptionRules;

    /**
     * Gets the value of the serviceOptionRules property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceOptionRules property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceOptionRules().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceOptionRules }
     * 
     * 
     */
    public List<ServiceOptionRules> getServiceOptionRules() {
        if (serviceOptionRules == null) {
            serviceOptionRules = new ArrayList<ServiceOptionRules>();
        }
        return this.serviceOptionRules;
    }

}
