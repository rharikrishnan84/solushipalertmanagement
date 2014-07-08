
package com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnRates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnRates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReturnCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BaseCharge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="FreightCharge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SurCharge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="AdditionalServiceCharge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ValuationCharge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="GST_HST" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="PST_QST" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalCharge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="StandardDeliveryDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PricingWeight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnRates", propOrder = {
    "returnCode",
    "baseCharge",
    "freightCharge",
    "surCharge",
    "additionalServiceCharge",
    "valuationCharge",
    "gsthst",
    "pstqst",
    "totalCharge",
    "standardDeliveryDays",
    "pricingWeight",
    "uom",
    "currencyCode",
    "returnMessage"
})
public class ReturnRates {

    @XmlElement(name = "ReturnCode")
    protected int returnCode;
    @XmlElement(name = "BaseCharge")
    protected double baseCharge;
    @XmlElement(name = "FreightCharge")
    protected double freightCharge;
    @XmlElement(name = "SurCharge")
    protected double surCharge;
    @XmlElement(name = "AdditionalServiceCharge")
    protected double additionalServiceCharge;
    @XmlElement(name = "ValuationCharge")
    protected double valuationCharge;
    @XmlElement(name = "GST_HST")
    protected double gsthst;
    @XmlElement(name = "PST_QST")
    protected double pstqst;
    @XmlElement(name = "TotalCharge")
    protected double totalCharge;
    @XmlElement(name = "StandardDeliveryDays")
    protected int standardDeliveryDays;
    @XmlElement(name = "PricingWeight")
    protected double pricingWeight;
    @XmlElement(name = "UOM")
    protected String uom;
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "ReturnMessage")
    protected String returnMessage;

    /**
     * Gets the value of the returnCode property.
     * 
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * Sets the value of the returnCode property.
     * 
     */
    public void setReturnCode(int value) {
        this.returnCode = value;
    }

    /**
     * Gets the value of the baseCharge property.
     * 
     */
    public double getBaseCharge() {
        return baseCharge;
    }

    /**
     * Sets the value of the baseCharge property.
     * 
     */
    public void setBaseCharge(double value) {
        this.baseCharge = value;
    }

    /**
     * Gets the value of the freightCharge property.
     * 
     */
    public double getFreightCharge() {
        return freightCharge;
    }

    /**
     * Sets the value of the freightCharge property.
     * 
     */
    public void setFreightCharge(double value) {
        this.freightCharge = value;
    }

    /**
     * Gets the value of the surCharge property.
     * 
     */
    public double getSurCharge() {
        return surCharge;
    }

    /**
     * Sets the value of the surCharge property.
     * 
     */
    public void setSurCharge(double value) {
        this.surCharge = value;
    }

    /**
     * Gets the value of the additionalServiceCharge property.
     * 
     */
    public double getAdditionalServiceCharge() {
        return additionalServiceCharge;
    }

    /**
     * Sets the value of the additionalServiceCharge property.
     * 
     */
    public void setAdditionalServiceCharge(double value) {
        this.additionalServiceCharge = value;
    }

    /**
     * Gets the value of the valuationCharge property.
     * 
     */
    public double getValuationCharge() {
        return valuationCharge;
    }

    /**
     * Sets the value of the valuationCharge property.
     * 
     */
    public void setValuationCharge(double value) {
        this.valuationCharge = value;
    }

    /**
     * Gets the value of the gsthst property.
     * 
     */
    public double getGSTHST() {
        return gsthst;
    }

    /**
     * Sets the value of the gsthst property.
     * 
     */
    public void setGSTHST(double value) {
        this.gsthst = value;
    }

    /**
     * Gets the value of the pstqst property.
     * 
     */
    public double getPSTQST() {
        return pstqst;
    }

    /**
     * Sets the value of the pstqst property.
     * 
     */
    public void setPSTQST(double value) {
        this.pstqst = value;
    }

    /**
     * Gets the value of the totalCharge property.
     * 
     */
    public double getTotalCharge() {
        return totalCharge;
    }

    /**
     * Sets the value of the totalCharge property.
     * 
     */
    public void setTotalCharge(double value) {
        this.totalCharge = value;
    }

    /**
     * Gets the value of the standardDeliveryDays property.
     * 
     */
    public int getStandardDeliveryDays() {
        return standardDeliveryDays;
    }

    /**
     * Sets the value of the standardDeliveryDays property.
     * 
     */
    public void setStandardDeliveryDays(int value) {
        this.standardDeliveryDays = value;
    }

    /**
     * Gets the value of the pricingWeight property.
     * 
     */
    public double getPricingWeight() {
        return pricingWeight;
    }

    /**
     * Sets the value of the pricingWeight property.
     * 
     */
    public void setPricingWeight(double value) {
        this.pricingWeight = value;
    }

    /**
     * Gets the value of the uom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOM() {
        return uom;
    }

    /**
     * Sets the value of the uom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOM(String value) {
        this.uom = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the returnMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnMessage() {
        return returnMessage;
    }

    /**
     * Sets the value of the returnMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnMessage(String value) {
        this.returnMessage = value;
    }

}
