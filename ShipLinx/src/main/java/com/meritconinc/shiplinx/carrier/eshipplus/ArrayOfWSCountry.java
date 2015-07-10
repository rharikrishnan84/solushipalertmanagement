
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSCountry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSCountry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSCountry" type="{http://www.eshipplus.com/}WSCountry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSCountry", propOrder = {
    "wsCountry"
})
public class ArrayOfWSCountry {

    @XmlElement(name = "WSCountry", nillable = true)
    protected List<WSCountry> wsCountry;

    /**
     * Gets the value of the wsCountry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsCountry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSCountry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSCountry }
     * 
     * 
     */
    public List<WSCountry> getWSCountry() {
        if (wsCountry == null) {
            wsCountry = new ArrayList<WSCountry>();
        }
        return this.wsCountry;
    }

}
