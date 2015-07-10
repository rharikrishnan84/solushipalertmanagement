
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSBillingDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSBillingDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eshipplus.com/}WSReturn">
 *       &lt;sequence>
 *         &lt;element name="ReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceType" type="{http://www.eshipplus.com/}DetailReferenceType"/>
 *         &lt;element name="BillingCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Category" type="{http://www.eshipplus.com/}ChargeCodeCategory"/>
 *         &lt;element name="AmountDue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSBillingDetail", propOrder = {
    "referenceNumber",
    "referenceType",
    "billingCode",
    "description",
    "category",
    "amountDue"
})
public class WSBillingDetail
    extends WSReturn
{

    @XmlElement(name = "ReferenceNumber")
    protected String referenceNumber;
    @XmlElement(name = "ReferenceType", required = true)
    protected DetailReferenceType referenceType;
    @XmlElement(name = "BillingCode")
    protected String billingCode;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Category", required = true)
    protected ChargeCodeCategory category;
    @XmlElement(name = "AmountDue", required = true)
    protected BigDecimal amountDue;

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the referenceType property.
     * 
     * @return
     *     possible object is
     *     {@link DetailReferenceType }
     *     
     */
    public DetailReferenceType getReferenceType() {
        return referenceType;
    }

    /**
     * Sets the value of the referenceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetailReferenceType }
     *     
     */
    public void setReferenceType(DetailReferenceType value) {
        this.referenceType = value;
    }

    /**
     * Gets the value of the billingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingCode() {
        return billingCode;
    }

    /**
     * Sets the value of the billingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingCode(String value) {
        this.billingCode = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeCodeCategory }
     *     
     */
    public ChargeCodeCategory getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeCodeCategory }
     *     
     */
    public void setCategory(ChargeCodeCategory value) {
        this.category = value;
    }

    /**
     * Gets the value of the amountDue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountDue() {
        return amountDue;
    }

    /**
     * Sets the value of the amountDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountDue(BigDecimal value) {
        this.amountDue = value;
    }

}
