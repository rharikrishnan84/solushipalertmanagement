package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * CreditCardInformation
 *
 * <p>Java class for CreditCardInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CreditCardInformation">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Type" type="{http://purolator.com/pws/datatypes/v1}CreditCardType"/>
 * &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="ExpiryMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 * &lt;element name="ExpiryYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 * &lt;element name="CVV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCardInformation", propOrder = {
 "type",
 "number",
 "name",
 "expiryMonth",
 "expiryYear",
 "cvv"
})
public class CreditCardInformation {

 @XmlElement(name = "Type", required = true)
 protected CreditCardType type;
 @XmlElement(name = "Number", required = true, nillable = true)
 protected String number;
 @XmlElement(name = "Name", required = true, nillable = true)
 protected String name;
 @XmlElement(name = "ExpiryMonth")
 protected int expiryMonth;
 @XmlElement(name = "ExpiryYear")
 protected int expiryYear;
 @XmlElement(name = "CVV", required = true, nillable = true)
 protected String cvv;

 /**
 * Gets the value of the type property.
 *
 * @return
 * possible object is
 * {@link CreditCardType }
 *
 */
 public CreditCardType getType() {
 return type;
 }

 /**
 * Sets the value of the type property.
 *
 * @param value
 * allowed object is
 * {@link CreditCardType }
 *
 */
 public void setType(CreditCardType value) {
 this.type = value;
 }

 /**
 * Gets the value of the number property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getNumber() {
 return number;
 }

 /**
 * Sets the value of the number property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setNumber(String value) {
 this.number = value;
 }

 /**
 * Gets the value of the name property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getName() {
 return name;
 }

 /**
 * Sets the value of the name property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setName(String value) {
 this.name = value;
 }

 /**
 * Gets the value of the expiryMonth property.
 *
 */
 public int getExpiryMonth() {
 return expiryMonth;
 }

 /**
 * Sets the value of the expiryMonth property.
 *
 */
 public void setExpiryMonth(int value) {
 this.expiryMonth = value;
 }

 /**
 * Gets the value of the expiryYear property.
 *
 */
 public int getExpiryYear() {
 return expiryYear;
 }

 /**
 * Sets the value of the expiryYear property.
 *
 */
 public void setExpiryYear(int value) {
 this.expiryYear = value;
 }

 /**
 * Gets the value of the cvv property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getCVV() {
 return cvv;
 }

 /**
 * Sets the value of the cvv property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setCVV(String value) {
 this.cvv = value;
 }

}