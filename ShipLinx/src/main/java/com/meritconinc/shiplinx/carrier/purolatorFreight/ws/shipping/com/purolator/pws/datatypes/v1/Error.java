	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Error complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Error">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="AdditionalInformation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Error", propOrder = {
 "code",
 "description",
 "additionalInformation"
})
public class Error {

 @XmlElement(name = "Code", required = true, nillable = true)
 protected String code;
 @XmlElement(name = "Description", required = true, nillable = true)
 protected String description;
 @XmlElement(name = "AdditionalInformation", required = true, nillable = true)
 protected String additionalInformation;

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
 * Gets the value of the description property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getDescription() {
 return description;
 }

 /**
 * Sets the value of the description property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setDescription(String value) {
 this.description = value;
 }

 /**
 * Gets the value of the additionalInformation property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getAdditionalInformation() {
 return additionalInformation;
 }

 /**
 * Sets the value of the additionalInformation property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setAdditionalInformation(String value) {
 this.additionalInformation = value;
 }

}