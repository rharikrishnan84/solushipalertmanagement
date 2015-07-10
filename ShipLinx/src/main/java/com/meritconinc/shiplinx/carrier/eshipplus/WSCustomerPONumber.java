
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for WSCustomerPONumber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSCustomerPONumber">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eshipplus.com/}WSReturn">
 *       &lt;sequence>
 *         &lt;element name="PONumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValidPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Validity" type="{http://www.eshipplus.com/}PONumberValidity"/>
 *         &lt;element name="MaximumUses" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CurrentUses" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="AdditionalPurchaseOrderNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://www.eshipplus.com/}WSCountry" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSCustomerPONumber", propOrder = {
    "poNumber",
    "validPostalCode",
    "validity",
    "maximumUses",
    "currentUses",
    "expirationDate",
    "additionalPurchaseOrderNotes",
    "country"
})
public class WSCustomerPONumber
    extends WSReturn
{

    @XmlElement(name = "PONumber")
    protected String poNumber;
    @XmlElement(name = "ValidPostalCode")
    protected String validPostalCode;
    @XmlElement(name = "Validity", required = true)
    protected PONumberValidity validity;
    @XmlElement(name = "MaximumUses")
    protected int maximumUses;
    @XmlElement(name = "CurrentUses")
    protected int currentUses;
    @XmlElement(name = "ExpirationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expirationDate;
    @XmlElement(name = "AdditionalPurchaseOrderNotes")
    protected String additionalPurchaseOrderNotes;
    @XmlElement(name = "Country")
    protected WSCountry country;

    /**
     * Gets the value of the poNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPONumber() {
        return poNumber;
    }

    /**
     * Sets the value of the poNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPONumber(String value) {
        this.poNumber = value;
    }

    /**
     * Gets the value of the validPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidPostalCode() {
        return validPostalCode;
    }

    /**
     * Sets the value of the validPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidPostalCode(String value) {
        this.validPostalCode = value;
    }

    /**
     * Gets the value of the validity property.
     * 
     * @return
     *     possible object is
     *     {@link PONumberValidity }
     *     
     */
    public PONumberValidity getValidity() {
        return validity;
    }

    /**
     * Sets the value of the validity property.
     * 
     * @param value
     *     allowed object is
     *     {@link PONumberValidity }
     *     
     */
    public void setValidity(PONumberValidity value) {
        this.validity = value;
    }

    /**
     * Gets the value of the maximumUses property.
     * 
     */
    public int getMaximumUses() {
        return maximumUses;
    }

    /**
     * Sets the value of the maximumUses property.
     * 
     */
    public void setMaximumUses(int value) {
        this.maximumUses = value;
    }

    /**
     * Gets the value of the currentUses property.
     * 
     */
    public int getCurrentUses() {
        return currentUses;
    }

    /**
     * Sets the value of the currentUses property.
     * 
     */
    public void setCurrentUses(int value) {
        this.currentUses = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the additionalPurchaseOrderNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalPurchaseOrderNotes() {
        return additionalPurchaseOrderNotes;
    }

    /**
     * Sets the value of the additionalPurchaseOrderNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalPurchaseOrderNotes(String value) {
        this.additionalPurchaseOrderNotes = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link WSCountry }
     *     
     */
    public WSCountry getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSCountry }
     *     
     */
    public void setCountry(WSCountry value) {
        this.country = value;
    }

}
