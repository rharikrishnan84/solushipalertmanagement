
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for WSInvoice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSInvoice">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eshipplus.com/}WSReturn">
 *       &lt;sequence>
 *         &lt;element name="InvoiceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Terms" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Billing" type="{http://www.eshipplus.com/}WSLocation2" minOccurs="0"/>
 *         &lt;element name="CustomerControlAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.eshipplus.com/}InvoiceType"/>
 *         &lt;element name="TotalFreightCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TotalFuelCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TotalAccessorialCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TotalServiceCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TotalAmountDue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="BillingDetails" type="{http://www.eshipplus.com/}ArrayOfWSBillingDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSInvoice", propOrder = {
    "invoiceNumber",
    "date",
    "dueDate",
    "terms",
    "billing",
    "customerControlAccount",
    "type",
    "totalFreightCharges",
    "totalFuelCharges",
    "totalAccessorialCharges",
    "totalServiceCharges",
    "totalAmountDue",
    "billingDetails"
})
public class WSInvoice
    extends WSReturn
{

    @XmlElement(name = "InvoiceNumber")
    protected String invoiceNumber;
    @XmlElement(name = "Date", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "DueDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(name = "Terms")
    protected int terms;
    @XmlElement(name = "Billing")
    protected WSLocation2 billing;
    @XmlElement(name = "CustomerControlAccount")
    protected String customerControlAccount;
    @XmlElement(name = "Type", required = true)
    protected InvoiceType type;
    @XmlElement(name = "TotalFreightCharges", required = true)
    protected BigDecimal totalFreightCharges;
    @XmlElement(name = "TotalFuelCharges", required = true)
    protected BigDecimal totalFuelCharges;
    @XmlElement(name = "TotalAccessorialCharges", required = true)
    protected BigDecimal totalAccessorialCharges;
    @XmlElement(name = "TotalServiceCharges", required = true)
    protected BigDecimal totalServiceCharges;
    @XmlElement(name = "TotalAmountDue", required = true)
    protected BigDecimal totalAmountDue;
    @XmlElement(name = "BillingDetails")
    protected ArrayOfWSBillingDetail billingDetails;

    /**
     * Gets the value of the invoiceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the value of the invoiceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNumber(String value) {
        this.invoiceNumber = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the terms property.
     * 
     */
    public int getTerms() {
        return terms;
    }

    /**
     * Sets the value of the terms property.
     * 
     */
    public void setTerms(int value) {
        this.terms = value;
    }

    /**
     * Gets the value of the billing property.
     * 
     * @return
     *     possible object is
     *     {@link WSLocation2 }
     *     
     */
    public WSLocation2 getBilling() {
        return billing;
    }

    /**
     * Sets the value of the billing property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSLocation2 }
     *     
     */
    public void setBilling(WSLocation2 value) {
        this.billing = value;
    }

    /**
     * Gets the value of the customerControlAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerControlAccount() {
        return customerControlAccount;
    }

    /**
     * Sets the value of the customerControlAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerControlAccount(String value) {
        this.customerControlAccount = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceType }
     *     
     */
    public InvoiceType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceType }
     *     
     */
    public void setType(InvoiceType value) {
        this.type = value;
    }

    /**
     * Gets the value of the totalFreightCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFreightCharges() {
        return totalFreightCharges;
    }

    /**
     * Sets the value of the totalFreightCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFreightCharges(BigDecimal value) {
        this.totalFreightCharges = value;
    }

    /**
     * Gets the value of the totalFuelCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFuelCharges() {
        return totalFuelCharges;
    }

    /**
     * Sets the value of the totalFuelCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFuelCharges(BigDecimal value) {
        this.totalFuelCharges = value;
    }

    /**
     * Gets the value of the totalAccessorialCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAccessorialCharges() {
        return totalAccessorialCharges;
    }

    /**
     * Sets the value of the totalAccessorialCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAccessorialCharges(BigDecimal value) {
        this.totalAccessorialCharges = value;
    }

    /**
     * Gets the value of the totalServiceCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalServiceCharges() {
        return totalServiceCharges;
    }

    /**
     * Sets the value of the totalServiceCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalServiceCharges(BigDecimal value) {
        this.totalServiceCharges = value;
    }

    /**
     * Gets the value of the totalAmountDue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmountDue() {
        return totalAmountDue;
    }

    /**
     * Sets the value of the totalAmountDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmountDue(BigDecimal value) {
        this.totalAmountDue = value;
    }

    /**
     * Gets the value of the billingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSBillingDetail }
     *     
     */
    public ArrayOfWSBillingDetail getBillingDetails() {
        return billingDetails;
    }

    /**
     * Sets the value of the billingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSBillingDetail }
     *     
     */
    public void setBillingDetails(ArrayOfWSBillingDetail value) {
        this.billingDetails = value;
    }

}
