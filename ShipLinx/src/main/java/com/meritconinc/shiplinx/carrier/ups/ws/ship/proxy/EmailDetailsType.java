
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmailDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmailDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="UndeliverableEMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FromEMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubjectCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmailDetailsType", propOrder = {
    "eMailAddress",
    "undeliverableEMailAddress",
    "fromEMailAddress",
    "fromName",
    "memo",
    "subject",
    "subjectCode"
})
public class EmailDetailsType {

    @XmlElement(name = "EMailAddress", required = true)
    protected List<String> eMailAddress;
    @XmlElement(name = "UndeliverableEMailAddress")
    protected String undeliverableEMailAddress;
    @XmlElement(name = "FromEMailAddress")
    protected String fromEMailAddress;
    @XmlElement(name = "FromName")
    protected String fromName;
    @XmlElement(name = "Memo")
    protected String memo;
    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "SubjectCode")
    protected String subjectCode;

    /**
     * Gets the value of the eMailAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eMailAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEMailAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEMailAddress() {
        if (eMailAddress == null) {
            eMailAddress = new ArrayList<String>();
        }
        return this.eMailAddress;
    }

    /**
     * Gets the value of the undeliverableEMailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUndeliverableEMailAddress() {
        return undeliverableEMailAddress;
    }

    /**
     * Sets the value of the undeliverableEMailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUndeliverableEMailAddress(String value) {
        this.undeliverableEMailAddress = value;
    }

    /**
     * Gets the value of the fromEMailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromEMailAddress() {
        return fromEMailAddress;
    }

    /**
     * Sets the value of the fromEMailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromEMailAddress(String value) {
        this.fromEMailAddress = value;
    }

    /**
     * Gets the value of the fromName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * Sets the value of the fromName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromName(String value) {
        this.fromName = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the subjectCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubjectCode() {
        return subjectCode;
    }

    /**
     * Sets the value of the subjectCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjectCode(String value) {
        this.subjectCode = value;
    }

}
