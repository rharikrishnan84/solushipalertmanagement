	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetPickUpRequestContainer complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetPickUpRequestContainer">
 * &lt;complexContent>
 * &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 * &lt;sequence>
 * &lt;element name="PickUp" type="{http://purolator.com/pws/datatypes/v1}PickUp"/>
 * &lt;/sequence>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPickUpRequestContainer", propOrder = {
 "pickUp"
})
public class GetPickUpRequestContainer
 extends RequestContainer
{

 @XmlElement(name = "PickUp", required = true, nillable = true)
 protected PickUp pickUp;

 /**
 * Gets the value of the pickUp property.
 *
 * @return
 * possible object is
 * {@link PickUp }
 *
 */
 public PickUp getPickUp() {
 return pickUp;
 }

 /**
 * Sets the value of the pickUp property.
 *
 * @param value
 * allowed object is
 * {@link PickUp }
 *
 */
 public void setPickUp(PickUp value) {
 this.pickUp = value;
 }

}