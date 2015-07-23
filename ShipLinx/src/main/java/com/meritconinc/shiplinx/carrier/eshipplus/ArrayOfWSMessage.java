
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSMessage" type="{http://www.eshipplus.com/}WSMessage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSMessage", propOrder = {
    "wsMessage"
})
public class ArrayOfWSMessage {

    @XmlElement(name = "WSMessage", nillable = true)
    protected List<WSMessage> wsMessage;

    /**
     * Gets the value of the wsMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSMessage }
     * 
     * 
     */
    public List<WSMessage> getWSMessage() {
        if (wsMessage == null) {
            wsMessage = new ArrayList<WSMessage>();
        }
        return this.wsMessage;
    }

}