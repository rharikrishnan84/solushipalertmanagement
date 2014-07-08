
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * InternationalInformation
 * 
 * <p>Java class for InternationalInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InternationalInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocumentsOnlyIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ContentDetails" type="{http://purolator.com/pws/datatypes/v1}ContentDetails" minOccurs="0"/>
 *         &lt;element name="BuyerInformation" type="{http://purolator.com/pws/datatypes/v1}BuyerInformation" minOccurs="0"/>
 *         &lt;element name="PreferredCustomsBroker" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DutyInformation" type="{http://purolator.com/pws/datatypes/v1}DutyInformation" minOccurs="0"/>
 *         &lt;element name="ImportExportType" type="{http://purolator.com/pws/datatypes/v1}ImportExportType" minOccurs="0"/>
 *         &lt;element name="CustomsInvoiceDocumentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternationalInformation", propOrder = {
    "documentsOnlyIndicator",
    "contentDetails",
    "buyerInformation",
    "preferredCustomsBroker",
    "dutyInformation",
    "importExportType",
    "customsInvoiceDocumentIndicator"
})
public class InternationalInformation {

    @XmlElement(name = "DocumentsOnlyIndicator")
    protected boolean documentsOnlyIndicator;
    @XmlElementRef(name = "ContentDetails", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ContentDetails> contentDetails;
    @XmlElementRef(name = "BuyerInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<BuyerInformation> buyerInformation;
    @XmlElementRef(name = "PreferredCustomsBroker", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> preferredCustomsBroker;
    @XmlElementRef(name = "DutyInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<DutyInformation> dutyInformation;
    @XmlElementRef(name = "ImportExportType", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ImportExportType> importExportType;
    @XmlElement(name = "CustomsInvoiceDocumentIndicator")
    protected Boolean customsInvoiceDocumentIndicator;

    /**
     * Gets the value of the documentsOnlyIndicator property.
     * 
     */
    public boolean isDocumentsOnlyIndicator() {
        return documentsOnlyIndicator;
    }

    /**
     * Sets the value of the documentsOnlyIndicator property.
     * 
     */
    public void setDocumentsOnlyIndicator(boolean value) {
        this.documentsOnlyIndicator = value;
    }

    /**
     * Gets the value of the contentDetails property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ContentDetails }{@code >}
     *     
     */
    public JAXBElement<ContentDetails> getContentDetails() {
        return contentDetails;
    }

    /**
     * Sets the value of the contentDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ContentDetails }{@code >}
     *     
     */
    public void setContentDetails(JAXBElement<ContentDetails> value) {
        this.contentDetails = value;
    }

    /**
     * Gets the value of the buyerInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BuyerInformation }{@code >}
     *     
     */
    public JAXBElement<BuyerInformation> getBuyerInformation() {
        return buyerInformation;
    }

    /**
     * Sets the value of the buyerInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BuyerInformation }{@code >}
     *     
     */
    public void setBuyerInformation(JAXBElement<BuyerInformation> value) {
        this.buyerInformation = value;
    }

    /**
     * Gets the value of the preferredCustomsBroker property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPreferredCustomsBroker() {
        return preferredCustomsBroker;
    }

    /**
     * Sets the value of the preferredCustomsBroker property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreferredCustomsBroker(JAXBElement<String> value) {
        this.preferredCustomsBroker = value;
    }

    /**
     * Gets the value of the dutyInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DutyInformation }{@code >}
     *     
     */
    public JAXBElement<DutyInformation> getDutyInformation() {
        return dutyInformation;
    }

    /**
     * Sets the value of the dutyInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DutyInformation }{@code >}
     *     
     */
    public void setDutyInformation(JAXBElement<DutyInformation> value) {
        this.dutyInformation = value;
    }

    /**
     * Gets the value of the importExportType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ImportExportType }{@code >}
     *     
     */
    public JAXBElement<ImportExportType> getImportExportType() {
        return importExportType;
    }

    /**
     * Sets the value of the importExportType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ImportExportType }{@code >}
     *     
     */
    public void setImportExportType(JAXBElement<ImportExportType> value) {
        this.importExportType = value;
    }

    /**
     * Gets the value of the customsInvoiceDocumentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomsInvoiceDocumentIndicator() {
        return customsInvoiceDocumentIndicator;
    }

    /**
     * Sets the value of the customsInvoiceDocumentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomsInvoiceDocumentIndicator(Boolean value) {
        this.customsInvoiceDocumentIndicator = value;
    }

}
