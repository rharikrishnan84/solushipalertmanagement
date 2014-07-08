
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestOption" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TransactionReference" type="{http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0}TransactionReferenceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestType", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", propOrder = {
    "requestOption",
    "transactionReference"
})
public class RequestType {

    @XmlElement(name = "RequestOption")
    protected List<String> requestOption;
    @XmlElement(name = "TransactionReference")
    protected TransactionReferenceType transactionReference;

    /**
     * Gets the value of the requestOption property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestOption property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestOption().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRequestOption() {
        if (requestOption == null) {
            requestOption = new ArrayList<String>();
        }
        return this.requestOption;
    }

    /**
     * Gets the value of the transactionReference property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionReferenceType }
     *     
     */
    public TransactionReferenceType getTransactionReference() {
        return transactionReference;
    }

    /**
     * Sets the value of the transactionReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionReferenceType }
     *     
     */
    public void setTransactionReference(TransactionReferenceType value) {
        this.transactionReference = value;
    }

}
