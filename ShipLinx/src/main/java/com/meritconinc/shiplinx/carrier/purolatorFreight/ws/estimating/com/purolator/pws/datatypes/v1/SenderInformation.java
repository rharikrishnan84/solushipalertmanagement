package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * SenderInformation
 *
 * <p>Java class for SenderInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SenderInformation">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Address" type="{http://purolator.com/pws/datatypes/v1}Address"/>
 * &lt;element name="EmailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SenderInformation", propOrder = {
 "address",
 "emailAddress"
})
public class SenderInformation {

 @XmlElement(name = "Address", required = true, nillable = true)
 protected Address address;
 @XmlElementRef(name = "EmailAddress", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> emailAddress;

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

 /**
 * Gets the value of the emailAddress property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getEmailAddress() {
 return emailAddress;
 }

 /**
 * Sets the value of the emailAddress property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setEmailAddress(JAXBElement<String> value) {
 this.emailAddress = ((JAXBElement<String> ) value);
 }

}