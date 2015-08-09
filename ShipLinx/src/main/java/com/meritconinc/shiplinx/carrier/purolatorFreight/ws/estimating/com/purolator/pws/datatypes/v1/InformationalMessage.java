package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * InformationalMessage
 *
 * <p>Java class for InformationalMessage complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="InformationalMessage">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformationalMessage", propOrder = {
 "code",
 "message"
})
public class InformationalMessage {

 @XmlElement(name = "Code", required = true, nillable = true)
 protected String code;
 @XmlElement(name = "Message", required = true, nillable = true)
 protected String message;

 /**
 * Gets the value of the code property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getCode() {
 return code;
 }

 /**
 * Sets the value of the code property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setCode(String value) {
 this.code = value;
 }

 /**
 * Gets the value of the message property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getMessage() {
 return message;
 }

 /**
 * Sets the value of the message property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setMessage(String value) {
 this.message = value;
 }

}