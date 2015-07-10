
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSReference" type="{http://www.eshipplus.com/}WSReference" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSReference", propOrder = {
    "wsReference"
})
public class ArrayOfWSReference {

    @XmlElement(name = "WSReference", nillable = true)
    protected List<WSReference> wsReference;

    /**
     * Gets the value of the wsReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSReference }
     * 
     * 
     */
    public List<WSReference> getWSReference() {
        if (wsReference == null) {
            wsReference = new ArrayList<WSReference>();
        }
        return this.wsReference;
    }
    public void setWSReference(List<WSReference> wsReference){
    	this.wsReference=wsReference;
    }

}
