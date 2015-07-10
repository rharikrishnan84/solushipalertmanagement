
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSTime2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSTime2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSTime2" type="{http://www.eshipplus.com/}WSTime2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSTime2", propOrder = {
    "wsTime2"
})
public class ArrayOfWSTime2 {

    @XmlElement(name = "WSTime2", nillable = true)
    protected List<WSTime2> wsTime2;

    /**
     * Gets the value of the wsTime2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsTime2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSTime2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSTime2 }
     * 
     * 
     */
    public List<WSTime2> getWSTime2() {
        if (wsTime2 == null) {
            wsTime2 = new ArrayList<WSTime2>();
        }
        return this.wsTime2;
    }

}
