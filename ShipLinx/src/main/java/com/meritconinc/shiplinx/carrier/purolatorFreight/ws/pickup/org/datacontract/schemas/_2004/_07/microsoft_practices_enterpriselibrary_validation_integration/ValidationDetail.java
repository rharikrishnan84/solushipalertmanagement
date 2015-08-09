	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.org.datacontract.schemas._2004._07.microsoft_practices_enterpriselibrary_validation_integration;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * This class holds the results of a single validation. Effectively, it's the same as a ValidationResult, but creating a separate class allows us to mark up a DataContract with impunity.
 *
 * <p>Java class for ValidationDetail complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ValidationDetail">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationDetail", propOrder = {
 "key",
 "message",
 "tag"
})
public class ValidationDetail {

 @XmlElementRef(name = "Key", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", type = JAXBElement.class)
 protected JAXBElement<String> key;
 @XmlElementRef(name = "Message", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", type = JAXBElement.class)
 protected JAXBElement<String> message;
 @XmlElementRef(name = "Tag", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", type = JAXBElement.class)
 protected JAXBElement<String> tag;

 /**
 * Gets the value of the key property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getKey() {
 return key;
 }

 /**
 * Sets the value of the key property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setKey(JAXBElement<String> value) {
 this.key = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the message property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getMessage() {
 return message;
 }

 /**
 * Sets the value of the message property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setMessage(JAXBElement<String> value) {
 this.message = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the tag property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getTag() {
 return tag;
 }

 /**
 * Sets the value of the tag property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setTag(JAXBElement<String> value) {
 this.tag = ((JAXBElement<String> ) value);
 }

}