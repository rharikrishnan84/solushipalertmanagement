
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSCheckCall complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSCheckCall">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSCheckCall" type="{http://www.eshipplus.com/}WSCheckCall" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSCheckCall", propOrder = {
    "wsCheckCall"
})
public class ArrayOfWSCheckCall {

    @XmlElement(name = "WSCheckCall", nillable = true)
    protected List<WSCheckCall> wsCheckCall;

    /**
     * Gets the value of the wsCheckCall property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsCheckCall property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSCheckCall().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSCheckCall }
     * 
     * 
     */
    public List<WSCheckCall> getWSCheckCall() {
        if (wsCheckCall == null) {
            wsCheckCall = new ArrayList<WSCheckCall>();
        }
        return this.wsCheckCall;
    }

}
