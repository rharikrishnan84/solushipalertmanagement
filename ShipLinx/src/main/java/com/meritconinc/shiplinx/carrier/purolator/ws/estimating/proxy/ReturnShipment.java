
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Shipment
 * 
 * <p>Java class for ReturnShipment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnShipment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SenderInformation" type="{http://purolator.com/pws/datatypes/v1}SenderInformation"/>
 *         &lt;element name="ReceiverInformation" type="{http://purolator.com/pws/datatypes/v1}ReceiverInformation"/>
 *         &lt;element name="PackageInformation" type="{http://purolator.com/pws/datatypes/v1}PackageInformation"/>
 *         &lt;element name="PaymentInformation" type="{http://purolator.com/pws/datatypes/v1}PaymentInformation"/>
 *         &lt;element name="PickupInformation" type="{http://purolator.com/pws/datatypes/v1}PickupInformation"/>
 *         &lt;element name="NotificationInformation" type="{http://purolator.com/pws/datatypes/v1}NotificationInformation" minOccurs="0"/>
 *         &lt;element name="TrackingReferenceInformation" type="{http://purolator.com/pws/datatypes/v1}TrackingReferenceInformation" minOccurs="0"/>
 *         &lt;element name="OtherInformation" type="{http://purolator.com/pws/datatypes/v1}OtherInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnShipment", propOrder = {
    "senderInformation",
    "receiverInformation",
    "packageInformation",
    "paymentInformation",
    "pickupInformation",
    "notificationInformation",
    "trackingReferenceInformation",
    "otherInformation"
})
public class ReturnShipment {

    @XmlElement(name = "SenderInformation", required = true, nillable = true)
    protected SenderInformation senderInformation;
    @XmlElement(name = "ReceiverInformation", required = true, nillable = true)
    protected ReceiverInformation receiverInformation;
    @XmlElement(name = "PackageInformation", required = true, nillable = true)
    protected PackageInformation packageInformation;
    @XmlElement(name = "PaymentInformation", required = true, nillable = true)
    protected PaymentInformation paymentInformation;
    @XmlElement(name = "PickupInformation", required = true, nillable = true)
    protected PickupInformation pickupInformation;
    @XmlElementRef(name = "NotificationInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<NotificationInformation> notificationInformation;
    @XmlElementRef(name = "TrackingReferenceInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<TrackingReferenceInformation> trackingReferenceInformation;
    @XmlElementRef(name = "OtherInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<OtherInformation> otherInformation;

    /**
     * Gets the value of the senderInformation property.
     * 
     * @return
     *     possible object is
     *     {@link SenderInformation }
     *     
     */
    public SenderInformation getSenderInformation() {
        return senderInformation;
    }

    /**
     * Sets the value of the senderInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SenderInformation }
     *     
     */
    public void setSenderInformation(SenderInformation value) {
        this.senderInformation = value;
    }

    /**
     * Gets the value of the receiverInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ReceiverInformation }
     *     
     */
    public ReceiverInformation getReceiverInformation() {
        return receiverInformation;
    }

    /**
     * Sets the value of the receiverInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceiverInformation }
     *     
     */
    public void setReceiverInformation(ReceiverInformation value) {
        this.receiverInformation = value;
    }

    /**
     * Gets the value of the packageInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PackageInformation }
     *     
     */
    public PackageInformation getPackageInformation() {
        return packageInformation;
    }

    /**
     * Sets the value of the packageInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageInformation }
     *     
     */
    public void setPackageInformation(PackageInformation value) {
        this.packageInformation = value;
    }

    /**
     * Gets the value of the paymentInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentInformation }
     *     
     */
    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * Sets the value of the paymentInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInformation }
     *     
     */
    public void setPaymentInformation(PaymentInformation value) {
        this.paymentInformation = value;
    }

    /**
     * Gets the value of the pickupInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PickupInformation }
     *     
     */
    public PickupInformation getPickupInformation() {
        return pickupInformation;
    }

    /**
     * Sets the value of the pickupInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupInformation }
     *     
     */
    public void setPickupInformation(PickupInformation value) {
        this.pickupInformation = value;
    }

    /**
     * Gets the value of the notificationInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link NotificationInformation }{@code >}
     *     
     */
    public JAXBElement<NotificationInformation> getNotificationInformation() {
        return notificationInformation;
    }

    /**
     * Sets the value of the notificationInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link NotificationInformation }{@code >}
     *     
     */
    public void setNotificationInformation(JAXBElement<NotificationInformation> value) {
        this.notificationInformation = value;
    }

    /**
     * Gets the value of the trackingReferenceInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TrackingReferenceInformation }{@code >}
     *     
     */
    public JAXBElement<TrackingReferenceInformation> getTrackingReferenceInformation() {
        return trackingReferenceInformation;
    }

    /**
     * Sets the value of the trackingReferenceInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TrackingReferenceInformation }{@code >}
     *     
     */
    public void setTrackingReferenceInformation(JAXBElement<TrackingReferenceInformation> value) {
        this.trackingReferenceInformation = value;
    }

    /**
     * Gets the value of the otherInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OtherInformation }{@code >}
     *     
     */
    public JAXBElement<OtherInformation> getOtherInformation() {
        return otherInformation;
    }

    /**
     * Sets the value of the otherInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OtherInformation }{@code >}
     *     
     */
    public void setOtherInformation(JAXBElement<OtherInformation> value) {
        this.otherInformation = value;
    }

}
