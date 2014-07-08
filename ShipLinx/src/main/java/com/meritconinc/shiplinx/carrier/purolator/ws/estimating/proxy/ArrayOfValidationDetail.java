
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfValidationDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfValidationDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ValidationDetail" type="{http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF}ValidationDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfValidationDetail", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", propOrder = {
    "validationDetail"
})
public class ArrayOfValidationDetail {

    @XmlElement(name = "ValidationDetail", nillable = true)
    protected List<ValidationDetail> validationDetail;

    /**
     * Gets the value of the validationDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validationDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidationDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidationDetail }
     * 
     * 
     */
    public List<ValidationDetail> getValidationDetail() {
        if (validationDetail == null) {
            validationDetail = new ArrayList<ValidationDetail>();
        }
        return this.validationDetail;
    }

}
