
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PickupAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PickupAddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressLine" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="Room" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Floor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StateProvince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Urbanization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResidentialIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickupPoint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Phone" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}PhoneType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickupAddressType", propOrder = {
    "companyName",
    "contactName",
    "addressLine",
    "room",
    "floor",
    "city",
    "stateProvince",
    "urbanization",
    "postalCode",
    "countryCode",
    "residentialIndicator",
    "pickupPoint",
    "phone"
})
public class PickupAddressType {

    @XmlElement(name = "CompanyName", required = true)
    protected String companyName;
    @XmlElement(name = "ContactName", required = true)
    protected String contactName;
    @XmlElement(name = "AddressLine", required = true)
    protected List<String> addressLine;
    @XmlElement(name = "Room")
    protected String room;
    @XmlElement(name = "Floor")
    protected String floor;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "StateProvince", required = true)
    protected String stateProvince;
    @XmlElement(name = "Urbanization")
    protected String urbanization;
    @XmlElement(name = "PostalCode")
    protected String postalCode;
    @XmlElement(name = "CountryCode", required = true)
    protected String countryCode;
    @XmlElement(name = "ResidentialIndicator", required = true, defaultValue = "N")
    protected String residentialIndicator;
    @XmlElement(name = "PickupPoint")
    protected String pickupPoint;
    @XmlElement(name = "Phone", required = true)
    protected PhoneType phone;

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
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the addressLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAddressLine() {
        if (addressLine == null) {
            addressLine = new ArrayList<String>();
        }
        return this.addressLine;
    }

    /**
     * Gets the value of the room property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoom() {
        return room;
    }

    /**
     * Sets the value of the room property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoom(String value) {
        this.room = value;
    }

    /**
     * Gets the value of the floor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Sets the value of the floor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFloor(String value) {
        this.floor = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the stateProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * Sets the value of the stateProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateProvince(String value) {
        this.stateProvince = value;
    }

    /**
     * Gets the value of the urbanization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrbanization() {
        return urbanization;
    }

    /**
     * Sets the value of the urbanization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrbanization(String value) {
        this.urbanization = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the residentialIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidentialIndicator() {
        return residentialIndicator;
    }

    /**
     * Sets the value of the residentialIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidentialIndicator(String value) {
        this.residentialIndicator = value;
    }

    /**
     * Gets the value of the pickupPoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupPoint() {
        return pickupPoint;
    }

    /**
     * Sets the value of the pickupPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupPoint(String value) {
        this.pickupPoint = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneType }
     *     
     */
    public PhoneType getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneType }
     *     
     */
    public void setPhone(PhoneType value) {
        this.phone = value;
    }

}
