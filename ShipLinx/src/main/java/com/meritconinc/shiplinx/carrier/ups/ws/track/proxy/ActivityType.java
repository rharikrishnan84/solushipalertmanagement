
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActivityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AlternateTrackingInfo" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AlternateTrackingInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ActivityLocation" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ActivityLocationType" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}StatusType" minOccurs="0"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Document" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AdditionalAttribute" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AdditionalCodeDescriptionValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityType", propOrder = {
    "alternateTrackingInfo",
    "activityLocation",
    "status",
    "date",
    "time",
    "document",
    "additionalAttribute"
})
public class ActivityType {

    @XmlElement(name = "AlternateTrackingInfo")
    protected List<AlternateTrackingInfoType> alternateTrackingInfo;
    @XmlElement(name = "ActivityLocation")
    protected ActivityLocationType activityLocation;
    @XmlElement(name = "Status")
    protected StatusType status;
    @XmlElement(name = "Date")
    protected String date;
    @XmlElement(name = "Time")
    protected String time;
    @XmlElement(name = "Document")
    protected List<DocumentType> document;
    @XmlElement(name = "AdditionalAttribute")
    protected List<AdditionalCodeDescriptionValueType> additionalAttribute;

    /**
     * Gets the value of the alternateTrackingInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternateTrackingInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternateTrackingInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlternateTrackingInfoType }
     * 
     * 
     */
    public List<AlternateTrackingInfoType> getAlternateTrackingInfo() {
        if (alternateTrackingInfo == null) {
            alternateTrackingInfo = new ArrayList<AlternateTrackingInfoType>();
        }
        return this.alternateTrackingInfo;
    }

    /**
     * Gets the value of the activityLocation property.
     * 
     * @return
     *     possible object is
     *     {@link ActivityLocationType }
     *     
     */
    public ActivityLocationType getActivityLocation() {
        return activityLocation;
    }

    /**
     * Sets the value of the activityLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityLocationType }
     *     
     */
    public void setActivityLocation(ActivityLocationType value) {
        this.activityLocation = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the document property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the document property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentType }
     * 
     * 
     */
    public List<DocumentType> getDocument() {
        if (document == null) {
            document = new ArrayList<DocumentType>();
        }
        return this.document;
    }

    /**
     * Gets the value of the additionalAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalCodeDescriptionValueType }
     * 
     * 
     */
    public List<AdditionalCodeDescriptionValueType> getAdditionalAttribute() {
        if (additionalAttribute == null) {
            additionalAttribute = new ArrayList<AdditionalCodeDescriptionValueType>();
        }
        return this.additionalAttribute;
    }

}
