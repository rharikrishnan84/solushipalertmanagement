
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentActivityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentActivityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActivityLocation" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AddressType" minOccurs="0"/>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Trailer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ShipmentActivityType", propOrder = {
    "activityLocation",
    "code",
    "description",
    "date",
    "time",
    "trailer",
    "document",
    "additionalAttribute"
})
public class ShipmentActivityType {

    @XmlElement(name = "ActivityLocation")
    protected AddressType activityLocation;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Date")
    protected String date;
    @XmlElement(name = "Time")
    protected String time;
    @XmlElement(name = "Trailer")
    protected String trailer;
    @XmlElement(name = "Document")
    protected List<DocumentType> document;
    @XmlElement(name = "AdditionalAttribute")
    protected List<AdditionalCodeDescriptionValueType> additionalAttribute;

    /**
     * Gets the value of the activityLocation property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getActivityLocation() {
        return activityLocation;
    }

    /**
     * Sets the value of the activityLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setActivityLocation(AddressType value) {
        this.activityLocation = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
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
     * Gets the value of the trailer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrailer() {
        return trailer;
    }

    /**
     * Sets the value of the trailer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrailer(String value) {
        this.trailer = value;
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
