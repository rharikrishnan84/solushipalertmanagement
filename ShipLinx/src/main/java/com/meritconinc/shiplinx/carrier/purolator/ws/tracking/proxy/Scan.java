
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Scan
 * 
 * <p>Java class for Scan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Scan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ScanType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PIN" type="{http://purolator.com/pws/datatypes/v1}PIN"/>
 *         &lt;element name="Depot" type="{http://purolator.com/pws/datatypes/v1}Depot"/>
 *         &lt;element name="ScanDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ScanTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SummaryScanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Scan", propOrder = {
    "scanType",
    "pin",
    "depot",
    "scanDate",
    "scanTime",
    "description",
    "comment",
    "summaryScanIndicator"
})
@XmlSeeAlso({
    InternationalOutboundScan.class,
    OnDeliveryScan.class,
    ProofOfPickUpScan.class,
    UndeliverableScan.class,
    DeliveryScan.class
})
public class Scan {

    @XmlElement(name = "ScanType", required = true, nillable = true)
    protected String scanType;
    @XmlElement(name = "PIN", required = true, nillable = true)
    protected PIN pin;
    @XmlElement(name = "Depot", required = true, nillable = true)
    protected Depot depot;
    @XmlElement(name = "ScanDate", required = true, nillable = true)
    protected String scanDate;
    @XmlElement(name = "ScanTime", required = true, nillable = true)
    protected String scanTime;
    @XmlElement(name = "Description", required = true, nillable = true)
    protected String description;
    @XmlElement(name = "Comment", required = true, nillable = true)
    protected String comment;
    @XmlElement(name = "SummaryScanIndicator")
    protected boolean summaryScanIndicator;

    /**
     * Gets the value of the scanType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanType() {
        return scanType;
    }

    /**
     * Sets the value of the scanType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanType(String value) {
        this.scanType = value;
    }

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link PIN }
     *     
     */
    public PIN getPIN() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PIN }
     *     
     */
    public void setPIN(PIN value) {
        this.pin = value;
    }

    /**
     * Gets the value of the depot property.
     * 
     * @return
     *     possible object is
     *     {@link Depot }
     *     
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * Sets the value of the depot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Depot }
     *     
     */
    public void setDepot(Depot value) {
        this.depot = value;
    }

    /**
     * Gets the value of the scanDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanDate() {
        return scanDate;
    }

    /**
     * Sets the value of the scanDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanDate(String value) {
        this.scanDate = value;
    }

    /**
     * Gets the value of the scanTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanTime() {
        return scanTime;
    }

    /**
     * Sets the value of the scanTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanTime(String value) {
        this.scanTime = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the summaryScanIndicator property.
     * 
     */
    public boolean isSummaryScanIndicator() {
        return summaryScanIndicator;
    }

    /**
     * Sets the value of the summaryScanIndicator property.
     * 
     */
    public void setSummaryScanIndicator(boolean value) {
        this.summaryScanIndicator = value;
    }

}
