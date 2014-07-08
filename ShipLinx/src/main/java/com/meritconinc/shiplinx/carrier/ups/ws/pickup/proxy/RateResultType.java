
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RateResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RateResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChargeDetail" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}ChargeDetailType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TotalTax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrandTotalOfAllCharge" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateResultType", propOrder = {
    "rateType",
    "currencyCode",
    "chargeDetail",
    "totalTax",
    "grandTotalOfAllCharge"
})
public class RateResultType {

    @XmlElement(name = "RateType")
    protected String rateType;
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "ChargeDetail")
    protected List<ChargeDetailType> chargeDetail;
    @XmlElement(name = "TotalTax")
    protected String totalTax;
    @XmlElement(name = "GrandTotalOfAllCharge", required = true)
    protected String grandTotalOfAllCharge;

    /**
     * Gets the value of the rateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * Sets the value of the rateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateType(String value) {
        this.rateType = value;
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
     * Gets the value of the chargeDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargeDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargeDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargeDetailType }
     * 
     * 
     */
    public List<ChargeDetailType> getChargeDetail() {
        if (chargeDetail == null) {
            chargeDetail = new ArrayList<ChargeDetailType>();
        }
        return this.chargeDetail;
    }

    /**
     * Gets the value of the totalTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalTax() {
        return totalTax;
    }

    /**
     * Sets the value of the totalTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalTax(String value) {
        this.totalTax = value;
    }

    /**
     * Gets the value of the grandTotalOfAllCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrandTotalOfAllCharge() {
        return grandTotalOfAllCharge;
    }

    /**
     * Sets the value of the grandTotalOfAllCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrandTotalOfAllCharge(String value) {
        this.grandTotalOfAllCharge = value;
    }

}
