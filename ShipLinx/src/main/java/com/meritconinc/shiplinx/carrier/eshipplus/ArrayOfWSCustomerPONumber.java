
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSCustomerPONumber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSCustomerPONumber">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSCustomerPONumber" type="{http://www.eshipplus.com/}WSCustomerPONumber" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSCustomerPONumber", propOrder = {
    "wsCustomerPONumber"
})
public class ArrayOfWSCustomerPONumber {

    @XmlElement(name = "WSCustomerPONumber", nillable = true)
    protected List<WSCustomerPONumber> wsCustomerPONumber;

    /**
     * Gets the value of the wsCustomerPONumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsCustomerPONumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSCustomerPONumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSCustomerPONumber }
     * 
     * 
     */
    public List<WSCustomerPONumber> getWSCustomerPONumber() {
        if (wsCustomerPONumber == null) {
            wsCustomerPONumber = new ArrayList<WSCustomerPONumber>();
        }
        return this.wsCustomerPONumber;
    }

}
