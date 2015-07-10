
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSItem2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSItem2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSItem2" type="{http://www.eshipplus.com/}WSItem2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSItem2", propOrder = {
    "wsItem2"
})
public class ArrayOfWSItem2 {

    @XmlElement(name = "WSItem2", nillable = true)
    public List<WSItem2> wsItem2;

    /**
     * Gets the value of the wsItem2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsItem2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSItem2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSItem2 }
     * 
     * 
     */
    public List<WSItem2> getWSItem2() {
        if (wsItem2 == null) {
            wsItem2 = new ArrayList<WSItem2>();
        }
        return this.wsItem2;
    }

}
