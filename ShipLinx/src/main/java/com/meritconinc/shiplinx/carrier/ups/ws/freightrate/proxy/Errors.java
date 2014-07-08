
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorDetail" type="{http://www.ups.com/XMLSchema/XOLTWS/Error/v1.1}ErrorDetailType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "errorDetail"
})
@XmlRootElement(name = "Errors", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Error/v1.1")
public class Errors {

    @XmlElement(name = "ErrorDetail", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Error/v1.1", required = true)
    protected List<ErrorDetailType> errorDetail;

    /**
     * Gets the value of the errorDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorDetailType }
     * 
     * 
     */
    public List<ErrorDetailType> getErrorDetail() {
        if (errorDetail == null) {
            errorDetail = new ArrayList<ErrorDetailType>();
        }
        return this.errorDetail;
    }

}
