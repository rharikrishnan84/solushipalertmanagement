
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * DeliveryScanDetails
 * 
 * <p>Java class for DeliveryScanDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeliveryScanDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeliverySignature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignatureImage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignatureImageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SignatureImageFormat" type="{http://purolator.com/pws/datatypes/v1}SignatureImageFormat"/>
 *         &lt;element name="DeliveryAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeliveryCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DeliveryScanDetails", propOrder = {
    "deliverySignature",
    "signatureImage",
    "signatureImageSize",
    "signatureImageFormat",
    "deliveryAddress",
    "deliveryCompanyName",
    "premiumServiceText",
    "productTypeText",
    "specialHandlingText",
    "paymentTypeText"
})
public class DeliveryScanDetails {

    @XmlElement(name = "DeliverySignature", required = true, nillable = true)
    protected String deliverySignature;
    @XmlElement(name = "SignatureImage", required = true, nillable = true)
    protected String signatureImage;
    @XmlElement(name = "SignatureImageSize")
    protected int signatureImageSize;
    @XmlElement(name = "SignatureImageFormat", required = true)
    protected SignatureImageFormat signatureImageFormat;
    @XmlElement(name = "DeliveryAddress", required = true, nillable = true)
    protected String deliveryAddress;
    @XmlElement(name = "DeliveryCompanyName", required = true, nillable = true)
    protected String deliveryCompanyName;
    @XmlElement(name = "PremiumServiceText", required = true, nillable = true)
    protected String premiumServiceText;
    @XmlElement(name = "ProductTypeText", required = true, nillable = true)
    protected String productTypeText;
    @XmlElement(name = "SpecialHandlingText", required = true, nillable = true)
    protected String specialHandlingText;
    @XmlElement(name = "PaymentTypeText", required = true, nillable = true)
    protected String paymentTypeText;

    /**
     * Gets the value of the deliverySignature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliverySignature() {
        return deliverySignature;
    }

    /**
     * Sets the value of the deliverySignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliverySignature(String value) {
        this.deliverySignature = value;
    }

    /**
     * Gets the value of the signatureImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureImage() {
        return signatureImage;
    }

    /**
     * Sets the value of the signatureImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureImage(String value) {
        this.signatureImage = value;
    }

    /**
     * Gets the value of the signatureImageSize property.
     * 
     */
    public int getSignatureImageSize() {
        return signatureImageSize;
    }

    /**
     * Sets the value of the signatureImageSize property.
     * 
     */
    public void setSignatureImageSize(int value) {
        this.signatureImageSize = value;
    }

    /**
     * Gets the value of the signatureImageFormat property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureImageFormat }
     *     
     */
    public SignatureImageFormat getSignatureImageFormat() {
        return signatureImageFormat;
    }

    /**
     * Sets the value of the signatureImageFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureImageFormat }
     *     
     */
    public void setSignatureImageFormat(SignatureImageFormat value) {
        this.signatureImageFormat = value;
    }

    /**
     * Gets the value of the deliveryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Sets the value of the deliveryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryAddress(String value) {
        this.deliveryAddress = value;
    }

    /**
     * Gets the value of the deliveryCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryCompanyName() {
        return deliveryCompanyName;
    }

    /**
     * Sets the value of the deliveryCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryCompanyName(String value) {
        this.deliveryCompanyName = value;
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
