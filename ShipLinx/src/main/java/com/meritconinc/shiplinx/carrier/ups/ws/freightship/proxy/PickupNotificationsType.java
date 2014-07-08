
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PickupNotificationsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PickupNotificationsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EMailNotification" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}EMailNotificationType" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="FailedEMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickupNotificationsType", propOrder = {
    "companyName",
    "eMailNotification",
    "failedEMail"
})
public class PickupNotificationsType {

    @XmlElement(name = "CompanyName")
    protected String companyName;
    @XmlElement(name = "EMailNotification")
    protected List<EMailNotificationType> eMailNotification;
    @XmlElement(name = "FailedEMail")
    protected String failedEMail;

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the eMailNotification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eMailNotification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEMailNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EMailNotificationType }
     * 
     * 
     */
    public List<EMailNotificationType> getEMailNotification() {
        if (eMailNotification == null) {
            eMailNotification = new ArrayList<EMailNotificationType>();
        }
        return this.eMailNotification;
    }

    /**
     * Gets the value of the failedEMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailedEMail() {
        return failedEMail;
    }

    /**
     * Sets the value of the failedEMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailedEMail(String value) {
        this.failedEMail = value;
    }

}
