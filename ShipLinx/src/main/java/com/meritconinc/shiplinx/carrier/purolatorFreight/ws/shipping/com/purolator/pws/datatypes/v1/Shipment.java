	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Shipment complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Shipment">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="SenderInformation" type="{http://purolator.com/pws/datatypes/v1}SenderInformation"/>
 * &lt;element name="ReceiverInformation" type="{http://purolator.com/pws/datatypes/v1}ReceiverInformation"/>
 * &lt;element name="ThirdPartyInformation" type="{http://purolator.com/pws/datatypes/v1}ThirdPartyInformation" minOccurs="0"/>
 * &lt;element name="PaymentInformation" type="{http://purolator.com/pws/datatypes/v1}PaymentInformation"/>
 * &lt;element name="ShipmentDetails" type="{http://purolator.com/pws/datatypes/v1}ShipmentInformation"/>
 * &lt;element name="AppointmentFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 * &lt;element name="AppointmentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="AppointmentStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="AppointmentEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="PickupFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 * &lt;element name="PickupInformation" type="{http://purolator.com/pws/datatypes/v1}PickUpInformation" minOccurs="0"/>
 * &lt;element name="AlertInformation" type="{http://purolator.com/pws/datatypes/v1}AlertInformation" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Shipment", propOrder = {
 "senderInformation",
 "receiverInformation",
 "thirdPartyInformation",
 "paymentInformation",
 "shipmentDetails",
 "appointmentFlag",
 "appointmentDate",
 "appointmentStartTime",
 "appointmentEndTime",
 "pickupFlag",
 "pickupInformation",
 "alertInformation"
})
public class Shipment {

 @XmlElement(name = "SenderInformation", required = true, nillable = true)
 protected SenderInformation senderInformation;
 @XmlElement(name = "ReceiverInformation", required = true, nillable = true)
 protected ReceiverInformation receiverInformation;
 @XmlElementRef(name = "ThirdPartyInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ThirdPartyInformation> thirdPartyInformation;
 @XmlElement(name = "PaymentInformation", required = true, nillable = true)
 protected PaymentInformation paymentInformation;
 @XmlElement(name = "ShipmentDetails", required = true, nillable = true)
 protected ShipmentInformation shipmentDetails;
 @XmlElement(name = "AppointmentFlag")
 protected boolean appointmentFlag;
 @XmlElementRef(name = "AppointmentDate", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> appointmentDate;
 @XmlElementRef(name = "AppointmentStartTime", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> appointmentStartTime;
 @XmlElementRef(name = "AppointmentEndTime", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> appointmentEndTime;
 @XmlElement(name = "PickupFlag")
 protected boolean pickupFlag;
 @XmlElementRef(name = "PickupInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<PickUpInformation> pickupInformation;
 @XmlElementRef(name = "AlertInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<AlertInformation> alertInformation;

 /**
 * Gets the value of the senderInformation property.
 *
 * @return
 * possible object is
 * {@link SenderInformation }
 *
 */
 public SenderInformation getSenderInformation() {
 return senderInformation;
 }

 /**
 * Sets the value of the senderInformation property.
 *
 * @param value
 * allowed object is
 * {@link SenderInformation }
 *
 */
 public void setSenderInformation(SenderInformation value) {
 this.senderInformation = value;
 }

 /**
 * Gets the value of the receiverInformation property.
 *
 * @return
 * possible object is
 * {@link ReceiverInformation }
 *
 */
 public ReceiverInformation getReceiverInformation() {
 return receiverInformation;
 }

 /**
 * Sets the value of the receiverInformation property.
 *
 * @param value
 * allowed object is
 * {@link ReceiverInformation }
 *
 */
 public void setReceiverInformation(ReceiverInformation value) {
 this.receiverInformation = value;
 }

 /**
 * Gets the value of the thirdPartyInformation property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ThirdPartyInformation }{@code >}
 *
 */
 public JAXBElement<ThirdPartyInformation> getThirdPartyInformation() {
 return thirdPartyInformation;
 }

 /**
 * Sets the value of the thirdPartyInformation property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ThirdPartyInformation }{@code >}
 *
 */
 public void setThirdPartyInformation(JAXBElement<ThirdPartyInformation> value) {
 this.thirdPartyInformation = ((JAXBElement<ThirdPartyInformation> ) value);
 }

 /**
 * Gets the value of the paymentInformation property.
 *
 * @return
 * possible object is
 * {@link PaymentInformation }
 *
 */
 public PaymentInformation getPaymentInformation() {
 return paymentInformation;
 }

 /**
 * Sets the value of the paymentInformation property.
 *
 * @param value
 * allowed object is
 * {@link PaymentInformation }
 *
 */
 public void setPaymentInformation(PaymentInformation value) {
 this.paymentInformation = value;
 }

 /**
 * Gets the value of the shipmentDetails property.
 *
 * @return
 * possible object is
 * {@link ShipmentInformation }
 *
 */
 public ShipmentInformation getShipmentDetails() {
 return shipmentDetails;
 }

 /**
 * Sets the value of the shipmentDetails property.
 *
 * @param value
 * allowed object is
 * {@link ShipmentInformation }
 *
 */
 public void setShipmentDetails(ShipmentInformation value) {
 this.shipmentDetails = value;
 }

 /**
 * Gets the value of the appointmentFlag property.
 *
 */
 public boolean isAppointmentFlag() {
 return appointmentFlag;
 }

 /**
 * Sets the value of the appointmentFlag property.
 *
 */
 public void setAppointmentFlag(boolean value) {
 this.appointmentFlag = value;
 }

 /**
 * Gets the value of the appointmentDate property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getAppointmentDate() {
 return appointmentDate;
 }

 /**
 * Sets the value of the appointmentDate property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setAppointmentDate(JAXBElement<String> value) {
 this.appointmentDate = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the appointmentStartTime property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getAppointmentStartTime() {
 return appointmentStartTime;
 }

 /**
 * Sets the value of the appointmentStartTime property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setAppointmentStartTime(JAXBElement<String> value) {
 this.appointmentStartTime = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the appointmentEndTime property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getAppointmentEndTime() {
 return appointmentEndTime;
 }

 /**
 * Sets the value of the appointmentEndTime property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setAppointmentEndTime(JAXBElement<String> value) {
 this.appointmentEndTime = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the pickupFlag property.
 *
 */
 public boolean isPickupFlag() {
 return pickupFlag;
 }

 /**
 * Sets the value of the pickupFlag property.
 *
 */
 public void setPickupFlag(boolean value) {
 this.pickupFlag = value;
 }

 /**
 * Gets the value of the pickupInformation property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link PickUpInformation }{@code >}
 *
 */
 public JAXBElement<PickUpInformation> getPickupInformation() {
 return pickupInformation;
 }

 /**
 * Sets the value of the pickupInformation property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link PickUpInformation }{@code >}
 *
 */
 public void setPickupInformation(JAXBElement<PickUpInformation> value) {
 this.pickupInformation = ((JAXBElement<PickUpInformation> ) value);
 }

 /**
 * Gets the value of the alertInformation property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link AlertInformation }{@code >}
 *
 */
 public JAXBElement<AlertInformation> getAlertInformation() {
 return alertInformation;
 }

 /**
 * Sets the value of the alertInformation property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link AlertInformation }{@code >}
 *
 */
 public void setAlertInformation(JAXBElement<AlertInformation> value) {
 this.alertInformation = ((JAXBElement<AlertInformation> ) value);
 }

}