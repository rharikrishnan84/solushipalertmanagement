
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CODType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CODType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CODFundsCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CODAmount" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}CODAmountType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CODType", propOrder = {
    "codFundsCode",
    "codAmount"
})
public class CODType {

    @XmlElement(name = "CODFundsCode", required = true)
    protected String codFundsCode;
    @XmlElement(name = "CODAmount", required = true)
    protected CODAmountType codAmount;

    /**
     * Gets the value of the codFundsCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODFundsCode() {
        return codFundsCode;
    }

    /**
     * Sets the value of the codFundsCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODFundsCode(String value) {
        this.codFundsCode = value;
    }

    /**
     * Gets the value of the codAmount property.
     * 
     * @return
     *     possible object is
     *     {@link CODAmountType }
     *     
     */
    public CODAmountType getCODAmount() {
        return codAmount;
    }

    /**
     * Sets the value of the codAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link CODAmountType }
     *     
     */
    public void setCODAmount(CODAmountType value) {
        this.codAmount = value;
    }

}
