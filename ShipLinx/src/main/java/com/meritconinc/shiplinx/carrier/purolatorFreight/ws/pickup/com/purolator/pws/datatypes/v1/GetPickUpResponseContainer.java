	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetPickUpResponseContainer complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetPickUpResponseContainer">
 * &lt;complexContent>
 * &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 * &lt;sequence>
 * &lt;element name="PickupNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPickUpResponseContainer", propOrder = {
 "pickupNumber"
})
public class GetPickUpResponseContainer
 extends ResponseContainer
{

 @XmlElementRef(name = "PickupNumber", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> pickupNumber;

 /**
 * Gets the value of the pickupNumber property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getPickupNumber() {
 return pickupNumber;
 }

 /**
 * Sets the value of the pickupNumber property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setPickupNumber(JAXBElement<String> value) {
 this.pickupNumber = ((JAXBElement<String> ) value);
 }

}