
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * GetQuickEstimateRequest
 * 
 * <p>Java class for GetQuickEstimateRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetQuickEstimateRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="BillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SenderPostalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReceiverAddress" type="{http://purolator.com/pws/datatypes/v1}ShortAddress"/>
 *         &lt;element name="PackageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TotalWeight" type="{http://purolator.com/pws/datatypes/v1}TotalWeight" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetQuickEstimateRequestContainer", propOrder = {
    "billingAccountNumber",
    "senderPostalCode",
    "receiverAddress",
    "packageType",
    "totalWeight"
})
public class GetQuickEstimateRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "BillingAccountNumber", required = true, nillable = true)
    protected String billingAccountNumber;
    @XmlElement(name = "SenderPostalCode", required = true, nillable = true)
    protected String senderPostalCode;
    @XmlElement(name = "ReceiverAddress", required = true, nillable = true)
    protected ShortAddress receiverAddress;
    @XmlElement(name = "PackageType", required = true, nillable = true)
    protected String packageType;
    @XmlElementRef(name = "TotalWeight", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<TotalWeight> totalWeight;

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNumber(String value) {
        this.billingAccountNumber = value;
    }

    /**
     * Gets the value of the senderPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderPostalCode() {
        return senderPostalCode;
    }

    /**
     * Sets the value of the senderPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderPostalCode(String value) {
        this.senderPostalCode = value;
    }

    /**
     * Gets the value of the receiverAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ShortAddress }
     *     
     */
    public ShortAddress getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * Sets the value of the receiverAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShortAddress }
     *     
     */
    public void setReceiverAddress(ShortAddress value) {
        this.receiverAddress = value;
    }

    /**
     * Gets the value of the packageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageType() {
        return packageType;
    }

    /**
     * Sets the value of the packageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageType(String value) {
        this.packageType = value;
    }

    /**
     * Gets the value of the totalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TotalWeight }{@code >}
     *     
     */
    public JAXBElement<TotalWeight> getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the value of the totalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TotalWeight }{@code >}
     *     
     */
    public void setTotalWeight(JAXBElement<TotalWeight> value) {
        this.totalWeight = value;
    }

}
