
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfOptionRule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOptionRule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OptionRule" type="{http://purolator.com/pws/datatypes/v1}OptionRule" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOptionRule", propOrder = {
    "optionRule"
})
public class ArrayOfOptionRule {

    @XmlElement(name = "OptionRule", nillable = true)
    protected List<OptionRule> optionRule;

    /**
     * Gets the value of the optionRule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionRule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionRule }
     * 
     * 
     */
    public List<OptionRule> getOptionRule() {
        if (optionRule == null) {
            optionRule = new ArrayList<OptionRule>();
        }
        return this.optionRule;
    }

}
