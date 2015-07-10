
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSInvoiceSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSInvoiceSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSInvoiceSummary" type="{http://www.eshipplus.com/}WSInvoiceSummary" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSInvoiceSummary", propOrder = {
    "wsInvoiceSummary"
})
public class ArrayOfWSInvoiceSummary {

    @XmlElement(name = "WSInvoiceSummary", nillable = true)
    protected List<WSInvoiceSummary> wsInvoiceSummary;

    /**
     * Gets the value of the wsInvoiceSummary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsInvoiceSummary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSInvoiceSummary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSInvoiceSummary }
     * 
     * 
     */
    public List<WSInvoiceSummary> getWSInvoiceSummary() {
        if (wsInvoiceSummary == null) {
            wsInvoiceSummary = new ArrayList<WSInvoiceSummary>();
        }
        return this.wsInvoiceSummary;
    }

}
