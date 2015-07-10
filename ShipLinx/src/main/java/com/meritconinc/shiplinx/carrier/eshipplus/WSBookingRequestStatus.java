
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSBookingRequestStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSBookingRequestStatus">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eshipplus.com/}WSReturn">
 *       &lt;sequence>
 *         &lt;element name="BookingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.eshipplus.com/}BookingRequestStatus"/>
 *         &lt;element name="Shipments" type="{http://www.eshipplus.com/}ArrayOfWSShipmentStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSBookingRequestStatus", propOrder = {
    "bookingNumber",
    "status",
    "shipments"
})
public class WSBookingRequestStatus
    extends WSReturn
{

    @XmlElement(name = "BookingNumber")
    protected String bookingNumber;
    @XmlElement(name = "Status", required = true)
    protected BookingRequestStatus status;
    @XmlElement(name = "Shipments")
    protected ArrayOfWSShipmentStatus shipments;

    /**
     * Gets the value of the bookingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingNumber() {
        return bookingNumber;
    }

    /**
     * Sets the value of the bookingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingNumber(String value) {
        this.bookingNumber = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link BookingRequestStatus }
     *     
     */
    public BookingRequestStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingRequestStatus }
     *     
     */
    public void setStatus(BookingRequestStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the shipments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSShipmentStatus }
     *     
     */
    public ArrayOfWSShipmentStatus getShipments() {
        return shipments;
    }

    /**
     * Sets the value of the shipments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSShipmentStatus }
     *     
     */
    public void setShipments(ArrayOfWSShipmentStatus value) {
        this.shipments = value;
    }

}
