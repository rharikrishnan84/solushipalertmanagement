
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActivityLocationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivityLocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Address" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AddressType" minOccurs="0"/>
 *         &lt;element name="TransportFacility" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}TransportFacilityType" minOccurs="0"/>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignedForByName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityLocationType", propOrder = {
    "address",
    "transportFacility",
    "code",
    "description",
    "signedForByName"
})
public class ActivityLocationType {

    @XmlElement(name = "Address")
    protected AddressType address;
    @XmlElement(name = "TransportFacility")
    protected TransportFacilityType transportFacility;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "SignedForByName")
    protected String signedForByName;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setAddress(AddressType value) {
        this.address = value;
    }

    /**
     * Gets the value of the transportFacility property.
     * 
     * @return
     *     possible object is
     *     {@link TransportFacilityType }
     *     
     */
    public TransportFacilityType getTransportFacility() {
        return transportFacility;
    }

    /**
     * Sets the value of the transportFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportFacilityType }
     *     
     */
    public void setTransportFacility(TransportFacilityType value) {
        this.transportFacility = value;
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
     * Gets the value of the signedForByName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignedForByName() {
        return signedForByName;
    }

    /**
     * Sets the value of the signedForByName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedForByName(String value) {
        this.signedForByName = value;
    }

}
