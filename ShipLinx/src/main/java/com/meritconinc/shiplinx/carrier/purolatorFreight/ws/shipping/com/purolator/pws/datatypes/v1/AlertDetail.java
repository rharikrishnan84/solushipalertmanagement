package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AlertDetail complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AlertDetail">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="EmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlertDetail", propOrder = {
 "type",
 "emailAddress"
})
public class AlertDetail {

 @XmlElement(name = "Type", required = true, nillable = true)
 protected String type;
 @XmlElement(name = "EmailAddress", required = true, nillable = true)
 protected String emailAddress;

 /**
 * Gets the value of the type property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getType() {
 return type;
 }

 /**
 * Sets the value of the type property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setType(String value) {
 this.type = value;
 }

 /**
 * Gets the value of the emailAddress property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getEmailAddress() {
 return emailAddress;
 }

 /**
 * Sets the value of the emailAddress property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setEmailAddress(String value) {
 this.emailAddress = value;
 }

}