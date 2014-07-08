
package com.meritconinc.shiplinx.carrier.purolator.ws.returnsmanagement.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPIN complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPIN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PIN" type="{http://purolator.com/pws/datatypes/v1}PIN" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPIN", propOrder = {
    "pin"
})
public class ArrayOfPIN {

    @XmlElement(name = "PIN", nillable = true)
    protected List<PIN> pin;

    /**
     * Gets the value of the pin property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pin property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPIN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PIN }
     * 
     * 
     */
    public List<PIN> getPIN() {
        if (pin == null) {
            pin = new ArrayList<PIN>();
        }
        return this.pin;
    }

}
