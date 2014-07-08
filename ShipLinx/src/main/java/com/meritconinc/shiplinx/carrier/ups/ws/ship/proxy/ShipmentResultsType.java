
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentResultsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentResultsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipmentChargesType" minOccurs="0"/>
 *         &lt;element name="NegotiatedRateCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}NegotiatedRateChargesType" minOccurs="0"/>
 *         &lt;element name="FRSShipmentData" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}FRSShipmentDataType" minOccurs="0"/>
 *         &lt;element name="BillingWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}BillingWeightType"/>
 *         &lt;element name="ShipmentIdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentDigest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageResults" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PackageResultsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ControlLogReceipt" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ImageType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Form" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}FormType" minOccurs="0"/>
 *         &lt;element name="CODTurnInPage" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}SCReportType" minOccurs="0"/>
 *         &lt;element name="HighValueReport" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}HighValueReportType" minOccurs="0"/>
 *         &lt;element name="LabelURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocalLanguageLabelURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReceiptURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocalLanguageReceiptURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentResultsType", propOrder = {
    "shipmentCharges",
    "negotiatedRateCharges",
    "frsShipmentData",
    "billingWeight",
    "shipmentIdentificationNumber",
    "shipmentDigest",
    "packageResults",
    "controlLogReceipt",
    "form",
    "codTurnInPage",
    "highValueReport",
    "labelURL",
    "localLanguageLabelURL",
    "receiptURL",
    "localLanguageReceiptURL"
})
public class ShipmentResultsType {

    @XmlElement(name = "ShipmentCharges")
    protected ShipmentChargesType shipmentCharges;
    @XmlElement(name = "NegotiatedRateCharges")
    protected NegotiatedRateChargesType negotiatedRateCharges;
    @XmlElement(name = "FRSShipmentData")
    protected FRSShipmentDataType frsShipmentData;
    @XmlElement(name = "BillingWeight", required = true)
    protected BillingWeightType billingWeight;
    @XmlElement(name = "ShipmentIdentificationNumber")
    protected String shipmentIdentificationNumber;
    @XmlElement(name = "ShipmentDigest")
    protected String shipmentDigest;
    @XmlElement(name = "PackageResults")
    protected List<PackageResultsType> packageResults;
    @XmlElement(name = "ControlLogReceipt")
    protected List<ImageType> controlLogReceipt;
    @XmlElement(name = "Form")
    protected FormType form;
    @XmlElement(name = "CODTurnInPage")
    protected SCReportType codTurnInPage;
    @XmlElement(name = "HighValueReport")
    protected HighValueReportType highValueReport;
    @XmlElement(name = "LabelURL")
    protected String labelURL;
    @XmlElement(name = "LocalLanguageLabelURL")
    protected String localLanguageLabelURL;
    @XmlElement(name = "ReceiptURL")
    protected String receiptURL;
    @XmlElement(name = "LocalLanguageReceiptURL")
    protected String localLanguageReceiptURL;

    /**
     * Gets the value of the shipmentCharges property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentChargesType }
     *     
     */
    public ShipmentChargesType getShipmentCharges() {
        return shipmentCharges;
    }

    /**
     * Sets the value of the shipmentCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentChargesType }
     *     
     */
    public void setShipmentCharges(ShipmentChargesType value) {
        this.shipmentCharges = value;
    }

    /**
     * Gets the value of the negotiatedRateCharges property.
     * 
     * @return
     *     possible object is
     *     {@link NegotiatedRateChargesType }
     *     
     */
    public NegotiatedRateChargesType getNegotiatedRateCharges() {
        return negotiatedRateCharges;
    }

    /**
     * Sets the value of the negotiatedRateCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link NegotiatedRateChargesType }
     *     
     */
    public void setNegotiatedRateCharges(NegotiatedRateChargesType value) {
        this.negotiatedRateCharges = value;
    }

    /**
     * Gets the value of the frsShipmentData property.
     * 
     * @return
     *     possible object is
     *     {@link FRSShipmentDataType }
     *     
     */
    public FRSShipmentDataType getFRSShipmentData() {
        return frsShipmentData;
    }

    /**
     * Sets the value of the frsShipmentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link FRSShipmentDataType }
     *     
     */
    public void setFRSShipmentData(FRSShipmentDataType value) {
        this.frsShipmentData = value;
    }

    /**
     * Gets the value of the billingWeight property.
     * 
     * @return
     *     possible object is
     *     {@link BillingWeightType }
     *     
     */
    public BillingWeightType getBillingWeight() {
        return billingWeight;
    }

    /**
     * Sets the value of the billingWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillingWeightType }
     *     
     */
    public void setBillingWeight(BillingWeightType value) {
        this.billingWeight = value;
    }

    /**
     * Gets the value of the shipmentIdentificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentIdentificationNumber() {
        return shipmentIdentificationNumber;
    }

    /**
     * Sets the value of the shipmentIdentificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentIdentificationNumber(String value) {
        this.shipmentIdentificationNumber = value;
    }

    /**
     * Gets the value of the shipmentDigest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentDigest() {
        return shipmentDigest;
    }

    /**
     * Sets the value of the shipmentDigest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentDigest(String value) {
        this.shipmentDigest = value;
    }

    /**
     * Gets the value of the packageResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageResultsType }
     * 
     * 
     */
    public List<PackageResultsType> getPackageResults() {
        if (packageResults == null) {
            packageResults = new ArrayList<PackageResultsType>();
        }
        return this.packageResults;
    }

    /**
     * Gets the value of the controlLogReceipt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the controlLogReceipt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getControlLogReceipt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageType }
     * 
     * 
     */
    public List<ImageType> getControlLogReceipt() {
        if (controlLogReceipt == null) {
            controlLogReceipt = new ArrayList<ImageType>();
        }
        return this.controlLogReceipt;
    }

    /**
     * Gets the value of the form property.
     * 
     * @return
     *     possible object is
     *     {@link FormType }
     *     
     */
    public FormType getForm() {
        return form;
    }

    /**
     * Sets the value of the form property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormType }
     *     
     */
    public void setForm(FormType value) {
        this.form = value;
    }

    /**
     * Gets the value of the codTurnInPage property.
     * 
     * @return
     *     possible object is
     *     {@link SCReportType }
     *     
     */
    public SCReportType getCODTurnInPage() {
        return codTurnInPage;
    }

    /**
     * Sets the value of the codTurnInPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link SCReportType }
     *     
     */
    public void setCODTurnInPage(SCReportType value) {
        this.codTurnInPage = value;
    }

    /**
     * Gets the value of the highValueReport property.
     * 
     * @return
     *     possible object is
     *     {@link HighValueReportType }
     *     
     */
    public HighValueReportType getHighValueReport() {
        return highValueReport;
    }

    /**
     * Sets the value of the highValueReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link HighValueReportType }
     *     
     */
    public void setHighValueReport(HighValueReportType value) {
        this.highValueReport = value;
    }

    /**
     * Gets the value of the labelURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelURL() {
        return labelURL;
    }

    /**
     * Sets the value of the labelURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelURL(String value) {
        this.labelURL = value;
    }

    /**
     * Gets the value of the localLanguageLabelURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalLanguageLabelURL() {
        return localLanguageLabelURL;
    }

    /**
     * Sets the value of the localLanguageLabelURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalLanguageLabelURL(String value) {
        this.localLanguageLabelURL = value;
    }

    /**
     * Gets the value of the receiptURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiptURL() {
        return receiptURL;
    }

    /**
     * Sets the value of the receiptURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiptURL(String value) {
        this.receiptURL = value;
    }

    /**
     * Gets the value of the localLanguageReceiptURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalLanguageReceiptURL() {
        return localLanguageReceiptURL;
    }

    /**
     * Sets the value of the localLanguageReceiptURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalLanguageReceiptURL(String value) {
        this.localLanguageReceiptURL = value;
    }

}
