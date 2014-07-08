
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ProofOfPickUpScanDetails
 * 
 * <p>Java class for ProofOfPickUpScanDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProofOfPickUpScanDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PickUpConfirmationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickUpAddress" type="{http://purolator.com/pws/datatypes/v1}Address"/>
 *         &lt;element name="PickUpContactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickUpCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickUpLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CommitedDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PremiumServiceText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductTypeText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SpecialHandlingText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PaymentTypeText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProofOfPickUpScanDetails", propOrder = {
    "pickUpConfirmationNumber",
    "pickUpAddress",
    "pickUpContactName",
    "pickUpCompanyName",
    "pickUpLocation",
    "commitedDeliveryDate",
    "premiumServiceText",
    "productTypeText",
    "specialHandlingText",
    "paymentTypeText"
})
public class ProofOfPickUpScanDetails {

    @XmlElement(name = "PickUpConfirmationNumber", required = true, nillable = true)
    protected String pickUpConfirmationNumber;
    @XmlElement(name = "PickUpAddress", required = true, nillable = true)
    protected Address pickUpAddress;
    @XmlElement(name = "PickUpContactName", required = true, nillable = true)
    protected String pickUpContactName;
    @XmlElement(name = "PickUpCompanyName", required = true, nillable = true)
    protected String pickUpCompanyName;
    @XmlElement(name = "PickUpLocation", required = true, nillable = true)
    protected String pickUpLocation;
    @XmlElement(name = "CommitedDeliveryDate", required = true, nillable = true)
    protected String commitedDeliveryDate;
    @XmlElement(name = "PremiumServiceText", required = true, nillable = true)
    protected String premiumServiceText;
    @XmlElement(name = "ProductTypeText", required = true, nillable = true)
    protected String productTypeText;
    @XmlElement(name = "SpecialHandlingText", required = true, nillable = true)
    protected String specialHandlingText;
    @XmlElement(name = "PaymentTypeText", required = true, nillable = true)
    protected String paymentTypeText;

    /**
     * Gets the value of the pickUpConfirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickUpConfirmationNumber() {
        return pickUpConfirmationNumber;
    }

    /**
     * Sets the value of the pickUpConfirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickUpConfirmationNumber(String value) {
        this.pickUpConfirmationNumber = value;
    }

    /**
     * Gets the value of the pickUpAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getPickUpAddress() {
        return pickUpAddress;
    }

    /**
     * Sets the value of the pickUpAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setPickUpAddress(Address value) {
        this.pickUpAddress = value;
    }

    /**
     * Gets the value of the pickUpContactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickUpContactName() {
        return pickUpContactName;
    }

    /**
     * Sets the value of the pickUpContactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickUpContactName(String value) {
        this.pickUpContactName = value;
    }

    /**
     * Gets the value of the pickUpCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickUpCompanyName() {
        return pickUpCompanyName;
    }

    /**
     * Sets the value of the pickUpCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickUpCompanyName(String value) {
        this.pickUpCompanyName = value;
    }

    /**
     * Gets the value of the pickUpLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickUpLocation() {
        return pickUpLocation;
    }

    /**
     * Sets the value of the pickUpLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickUpLocation(String value) {
        this.pickUpLocation = value;
    }

    /**
     * Gets the value of the commitedDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitedDeliveryDate() {
        return commitedDeliveryDate;
    }

    /**
     * Sets the value of the commitedDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitedDeliveryDate(String value) {
        this.commitedDeliveryDate = value;
    }

    /**
     * Gets the value of the premiumServiceText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPremiumServiceText() {
        return premiumServiceText;
    }

    /**
     * Sets the value of the premiumServiceText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPremiumServiceText(String value) {
        this.premiumServiceText = value;
    }

    /**
     * Gets the value of the productTypeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeText() {
        return productTypeText;
    }

    /**
     * Sets the value of the productTypeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeText(String value) {
        this.productTypeText = value;
    }

    /**
     * Gets the value of the specialHandlingText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialHandlingText() {
        return specialHandlingText;
    }

    /**
     * Sets the value of the specialHandlingText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialHandlingText(String value) {
        this.specialHandlingText = value;
    }

    /**
     * Gets the value of the paymentTypeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTypeText() {
        return paymentTypeText;
    }

    /**
     * Sets the value of the paymentTypeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTypeText(String value) {
        this.paymentTypeText = value;
    }

}
