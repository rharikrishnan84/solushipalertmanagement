
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * PickUpHistorySearchCriteria
 * 
 * <p>Java class for PickUpHistorySearchCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PickUpHistorySearchCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FromDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ToDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConfirmationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxNumOfRecords" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SortColumn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SortDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickUpHistorySearchCriteria", propOrder = {
    "fromDate",
    "toDate",
    "confirmationNumber",
    "accountNumber",
    "status",
    "maxNumOfRecords",
    "sortColumn",
    "sortDirection"
})
public class PickUpHistorySearchCriteria {

    @XmlElement(name = "FromDate", required = true, nillable = true)
    protected String fromDate;
    @XmlElement(name = "ToDate", required = true, nillable = true)
    protected String toDate;
    @XmlElementRef(name = "ConfirmationNumber", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> confirmationNumber;
    @XmlElement(name = "AccountNumber", required = true, nillable = true)
    protected String accountNumber;
    @XmlElementRef(name = "Status", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> status;
    @XmlElement(name = "MaxNumOfRecords")
    protected Integer maxNumOfRecords;
    @XmlElementRef(name = "SortColumn", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> sortColumn;
    @XmlElementRef(name = "SortDirection", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> sortDirection;

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromDate(String value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the toDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToDate(String value) {
        this.toDate = value;
    }

    /**
     * Gets the value of the confirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Sets the value of the confirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConfirmationNumber(JAXBElement<String> value) {
        this.confirmationNumber = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatus(JAXBElement<String> value) {
        this.status = value;
    }

    /**
     * Gets the value of the maxNumOfRecords property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxNumOfRecords() {
        return maxNumOfRecords;
    }

    /**
     * Sets the value of the maxNumOfRecords property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxNumOfRecords(Integer value) {
        this.maxNumOfRecords = value;
    }

    /**
     * Gets the value of the sortColumn property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSortColumn() {
        return sortColumn;
    }

    /**
     * Sets the value of the sortColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSortColumn(JAXBElement<String> value) {
        this.sortColumn = value;
    }

    /**
     * Gets the value of the sortDirection property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSortDirection() {
        return sortDirection;
    }

    /**
     * Sets the value of the sortDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSortDirection(JAXBElement<String> value) {
        this.sortDirection = value;
    }

}
