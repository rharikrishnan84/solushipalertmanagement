
package com.meritconinc.shiplinx.carrier.purolator.ws.returnsmanagement.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfOptionIDValuePair complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOptionIDValuePair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OptionIDValuePair" type="{http://purolator.com/pws/datatypes/v1}OptionIDValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOptionIDValuePair", propOrder = {
    "optionIDValuePair"
})
public class ArrayOfOptionIDValuePair {

    @XmlElement(name = "OptionIDValuePair", nillable = true)
    protected List<OptionIDValuePair> optionIDValuePair;

    /**
     * Gets the value of the optionIDValuePair property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionIDValuePair property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionIDValuePair().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionIDValuePair }
     * 
     * 
     */
    public List<OptionIDValuePair> getOptionIDValuePair() {
        if (optionIDValuePair == null) {
            optionIDValuePair = new ArrayList<OptionIDValuePair>();
        }
        return this.optionIDValuePair;
    }

}
