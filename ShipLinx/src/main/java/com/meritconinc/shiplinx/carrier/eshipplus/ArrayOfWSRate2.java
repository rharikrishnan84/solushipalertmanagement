
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSRate2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSRate2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSRate2" type="{http://www.eshipplus.com/}WSRate2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSRate2", propOrder = {
    "wsRate2"
})
public class ArrayOfWSRate2 {

    @XmlElement(name = "WSRate2", nillable = true)
    protected List<WSRate2> wsRate2;

    /**
     * Gets the value of the wsRate2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsRate2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSRate2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSRate2 }
     * 
     * 
     */
    public List<WSRate2> getWSRate2() {
        if (wsRate2 == null) {
            wsRate2 = new ArrayList<WSRate2>();
        }
        return this.wsRate2;
    }

}
