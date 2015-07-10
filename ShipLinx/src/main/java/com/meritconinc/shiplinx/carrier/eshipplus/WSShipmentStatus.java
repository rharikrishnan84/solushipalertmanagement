
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for WSShipmentStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSShipmentStatus">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eshipplus.com/}WSReturn">
 *       &lt;sequence>
 *         &lt;element name="ShipmentNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.eshipplus.com/}ShipmentStatus"/>
 *         &lt;element name="DateCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EstimatePickupDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EstimateDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ActualPickupDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ActualDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IsPickedUp" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsDelivered" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Mode" type="{http://www.eshipplus.com/}ServiceMode"/>
 *         &lt;element name="Pro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VendorKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VendorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VendorScac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CheckCalls" type="{http://www.eshipplus.com/}ArrayOfWSCheckCall" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSShipmentStatus", propOrder = {
    "shipmentNumber",
    "status",
    "dateCreated",
    "estimatePickupDate",
    "estimateDeliveryDate",
    "actualPickupDate",
    "actualDeliveryDate",
    "isPickedUp",
    "isDelivered",
    "mode",
    "pro",
    "vendorKey",
    "vendorName",
    "vendorScac",
    "checkCalls"
})
public class WSShipmentStatus
    extends WSReturn
{

    @XmlElement(name = "ShipmentNumber")
    protected String shipmentNumber;
    @XmlElement(name = "Status", required = true)
    protected ShipmentStatus status;
    @XmlElement(name = "DateCreated", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreated;
    @XmlElement(name = "EstimatePickupDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar estimatePickupDate;
    @XmlElement(name = "EstimateDeliveryDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar estimateDeliveryDate;
    @XmlElement(name = "ActualPickupDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar actualPickupDate;
    @XmlElement(name = "ActualDeliveryDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar actualDeliveryDate;
    @XmlElement(name = "IsPickedUp")
    protected boolean isPickedUp;
    @XmlElement(name = "IsDelivered")
    protected boolean isDelivered;
    @XmlElement(name = "Mode", required = true)
    protected ServiceMode mode;
    @XmlElement(name = "Pro")
    protected String pro;
    @XmlElement(name = "VendorKey")
    protected String vendorKey;
    @XmlElement(name = "VendorName")
    protected String vendorName;
    @XmlElement(name = "VendorScac")
    protected String vendorScac;
    @XmlElement(name = "CheckCalls")
    protected ArrayOfWSCheckCall checkCalls;

    /**
     * Gets the value of the shipmentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentNumber() {
        return shipmentNumber;
    }

    /**
     * Sets the value of the shipmentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentNumber(String value) {
        this.shipmentNumber = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentStatus }
     *     
     */
    public ShipmentStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentStatus }
     *     
     */
    public void setStatus(ShipmentStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the estimatePickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEstimatePickupDate() {
        return estimatePickupDate;
    }

    /**
     * Sets the value of the estimatePickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEstimatePickupDate(XMLGregorianCalendar value) {
        this.estimatePickupDate = value;
    }

    /**
     * Gets the value of the estimateDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEstimateDeliveryDate() {
        return estimateDeliveryDate;
    }

    /**
     * Sets the value of the estimateDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEstimateDeliveryDate(XMLGregorianCalendar value) {
        this.estimateDeliveryDate = value;
    }

    /**
     * Gets the value of the actualPickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActualPickupDate() {
        return actualPickupDate;
    }

    /**
     * Sets the value of the actualPickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActualPickupDate(XMLGregorianCalendar value) {
        this.actualPickupDate = value;
    }

    /**
     * Gets the value of the actualDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    /**
     * Sets the value of the actualDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActualDeliveryDate(XMLGregorianCalendar value) {
        this.actualDeliveryDate = value;
    }

    /**
     * Gets the value of the isPickedUp property.
     * 
     */
    public boolean isIsPickedUp() {
        return isPickedUp;
    }

    /**
     * Sets the value of the isPickedUp property.
     * 
     */
    public void setIsPickedUp(boolean value) {
        this.isPickedUp = value;
    }

    /**
     * Gets the value of the isDelivered property.
     * 
     */
    public boolean isIsDelivered() {
        return isDelivered;
    }

    /**
     * Sets the value of the isDelivered property.
     * 
     */
    public void setIsDelivered(boolean value) {
        this.isDelivered = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceMode }
     *     
     */
    public ServiceMode getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceMode }
     *     
     */
    public void setMode(ServiceMode value) {
        this.mode = value;
    }

    /**
     * Gets the value of the pro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPro() {
        return pro;
    }

    /**
     * Sets the value of the pro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPro(String value) {
        this.pro = value;
    }

    /**
     * Gets the value of the vendorKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorKey() {
        return vendorKey;
    }

    /**
     * Sets the value of the vendorKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorKey(String value) {
        this.vendorKey = value;
    }

    /**
     * Gets the value of the vendorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * Sets the value of the vendorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorName(String value) {
        this.vendorName = value;
    }

    /**
     * Gets the value of the vendorScac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorScac() {
        return vendorScac;
    }

    /**
     * Sets the value of the vendorScac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorScac(String value) {
        this.vendorScac = value;
    }

    /**
     * Gets the value of the checkCalls property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSCheckCall }
     *     
     */
    public ArrayOfWSCheckCall getCheckCalls() {
        return checkCalls;
    }

    /**
     * Sets the value of the checkCalls property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSCheckCall }
     *     
     */
    public void setCheckCalls(ArrayOfWSCheckCall value) {
        this.checkCalls = value;
    }

}
