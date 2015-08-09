	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ThirdPartyInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ThirdPartyInformation">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Address" type="{http://purolator.com/pws/datatypes/v1}Address"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ThirdPartyInformation", propOrder = {
 "address"
})
public class ThirdPartyInformation {

 @XmlElement(name = "Address", required = true, nillable = true)
 protected Address address;

 /**
 * Gets the value of the address property.
 *
 * @return
 * possible object is
 * {@link Address }
 *
 */
 public Address getAddress() {
 return address;
 }

 /**
 * Sets the value of the address property.
 *
 * @param value
 * allowed object is
 * {@link Address }
 *
 */
 public void setAddress(Address value) {
 this.address = value;
 }

}	