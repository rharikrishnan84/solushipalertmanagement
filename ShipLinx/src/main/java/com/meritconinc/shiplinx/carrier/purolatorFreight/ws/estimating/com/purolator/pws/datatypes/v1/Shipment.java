package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * FreightShipment
 *
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
 * &lt;element name="PaymentInformation" type="{http://purolator.com/pws/datatypes/v1}PaymentInformation"/>
 * &lt;element name="ShipmentDetails" type="{http://purolator.com/pws/datatypes/v1}EstimateInformation"/>
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
 "paymentInformation",
 "shipmentDetails"
})
public class Shipment {

 @XmlElement(name = "SenderInformation", required = true, nillable = true)
 protected SenderInformation senderInformation;
 @XmlElement(name = "ReceiverInformation", required = true, nillable = true)
 protected ReceiverInformation receiverInformation;
 @XmlElement(name = "PaymentInformation", required = true, nillable = true)
 protected PaymentInformation paymentInformation;
 @XmlElement(name = "ShipmentDetails", required = true, nillable = true)
 protected EstimateInformation shipmentDetails;

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
 * {@link EstimateInformation }
 *
 */
 public EstimateInformation getShipmentDetails() {
 return shipmentDetails;
 }

 /**
 * Sets the value of the shipmentDetails property.
 *
 * @param value
 * allowed object is
 * {@link EstimateInformation }
 *
 */
 public void setShipmentDetails(EstimateInformation value) {
 this.shipmentDetails = value;
 }

}