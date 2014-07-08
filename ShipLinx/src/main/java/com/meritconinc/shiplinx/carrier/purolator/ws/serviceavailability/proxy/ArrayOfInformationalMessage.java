
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfInformationalMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfInformationalMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InformationalMessage" type="{http://purolator.com/pws/datatypes/v1}InformationalMessage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfInformationalMessage", propOrder = {
    "informationalMessage"
})
public class ArrayOfInformationalMessage {

    @XmlElement(name = "InformationalMessage", nillable = true)
    protected List<InformationalMessage> informationalMessage;

    /**
     * Gets the value of the informationalMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informationalMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformationalMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InformationalMessage }
     * 
     * 
     */
    public List<InformationalMessage> getInformationalMessage() {
        if (informationalMessage == null) {
            informationalMessage = new ArrayList<InformationalMessage>();
        }
        return this.informationalMessage;
    }

}
