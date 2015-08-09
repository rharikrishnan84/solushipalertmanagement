	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EstimateInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EstimateInformation">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="ServiceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="ShipmentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="DeclaredValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 * &lt;element name="CODAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 * &lt;element name="SpecialInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="LineItemDetails" type="{http://purolator.com/pws/datatypes/v1}ArrayOfLineItem"/>
 * &lt;element name="AccesorialParameters" type="{http://purolator.com/pws/datatypes/v1}ArrayOfBoolValuePair" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstimateInformation", propOrder = {
 "serviceTypeCode",
 "shipmentDate",
 "declaredValue",
 "codAmount",
 "specialInstructions",
 "lineItemDetails",
 "accesorialParameters"
})
@XmlSeeAlso({
 ShipmentInformation.class
})
public class EstimateInformation {

 @XmlElementRef(name = "ServiceTypeCode", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> serviceTypeCode;
 @XmlElementRef(name = "ShipmentDate", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> shipmentDate;
 @XmlElementRef(name = "DeclaredValue", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<BigDecimal> declaredValue;
 @XmlElementRef(name = "CODAmount", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<BigDecimal> codAmount;
 @XmlElementRef(name = "SpecialInstructions", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> specialInstructions;
 @XmlElement(name = "LineItemDetails", required = true, nillable = true)
 protected ArrayOfLineItem lineItemDetails;
 @XmlElementRef(name = "AccesorialParameters", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ArrayOfBoolValuePair> accesorialParameters;

 /**
 * Gets the value of the serviceTypeCode property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getServiceTypeCode() {
 return serviceTypeCode;
 }

 /**
 * Sets the value of the serviceTypeCode property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setServiceTypeCode(JAXBElement<String> value) {
 this.serviceTypeCode = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the shipmentDate property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getShipmentDate() {
 return shipmentDate;
 }

 /**
 * Sets the value of the shipmentDate property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setShipmentDate(JAXBElement<String> value) {
 this.shipmentDate = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the declaredValue property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
 *
 */
 public JAXBElement<BigDecimal> getDeclaredValue() {
 return declaredValue;
 }

 /**
 * Sets the value of the declaredValue property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
 *
 */
 public void setDeclaredValue(JAXBElement<BigDecimal> value) {
 this.declaredValue = ((JAXBElement<BigDecimal> ) value);
 }

 /**
 * Gets the value of the codAmount property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
 *
 */
 public JAXBElement<BigDecimal> getCODAmount() {
 return codAmount;
 }

 /**
 * Sets the value of the codAmount property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
 *
 */
 public void setCODAmount(JAXBElement<BigDecimal> value) {
 this.codAmount = ((JAXBElement<BigDecimal> ) value);
 }

 /**
 * Gets the value of the specialInstructions property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getSpecialInstructions() {
 return specialInstructions;
 }

 /**
 * Sets the value of the specialInstructions property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setSpecialInstructions(JAXBElement<String> value) {
 this.specialInstructions = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the lineItemDetails property.
 *
 * @return
 * possible object is
 * {@link ArrayOfLineItem }
 *
 */
 public ArrayOfLineItem getLineItemDetails() {
 return lineItemDetails;
 }

 /**
 * Sets the value of the lineItemDetails property.
 *
 * @param value
 * allowed object is
 * {@link ArrayOfLineItem }
 *
 */
 public void setLineItemDetails(ArrayOfLineItem value) {
 this.lineItemDetails = value;
 }

 /**
 * Gets the value of the accesorialParameters property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ArrayOfBoolValuePair }{@code >}
 *
 */
 public JAXBElement<ArrayOfBoolValuePair> getAccesorialParameters() {
 return accesorialParameters;
 }

 /**
 * Sets the value of the accesorialParameters property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ArrayOfBoolValuePair }{@code >}
 *
 */
 public void setAccesorialParameters(JAXBElement<ArrayOfBoolValuePair> value) {
 this.accesorialParameters = ((JAXBElement<ArrayOfBoolValuePair> ) value);
 }

}