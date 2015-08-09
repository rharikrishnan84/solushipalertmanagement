	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreateShipmentResponseContainer complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CreateShipmentResponseContainer">
 * &lt;complexContent>
 * &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 * &lt;sequence>
 * &lt;element name="ProNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="PickupNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="TariffCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="DiscountPoint" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 * &lt;element name="TransitDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 * &lt;element name="EstimatedDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="OriginalTerminalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="DestinationTerminalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="DestinationUniCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="TotalPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 * &lt;element name="ShipmentPINs" type="{http://purolator.com/pws/datatypes/v1}ArrayOfPIN" minOccurs="0"/>
 * &lt;element name="LineItemDetails" type="{http://purolator.com/pws/datatypes/v1}ArrayOfLineItem" minOccurs="0"/>
 * &lt;element name="AccessorialDetails" type="{http://purolator.com/pws/datatypes/v1}ArrayOfAccessorialItem" minOccurs="0"/>
 * &lt;element name="ShipmentTaxes" type="{http://purolator.com/pws/datatypes/v1}ArrayOfTax" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateShipmentResponseContainer", propOrder = {
 "proNumber",
 "pickupNumber",
 "tariffCode",
 "discountPoint",
 "transitDays",
 "estimatedDeliveryDate",
 "originalTerminalCode",
 "destinationTerminalCode",
 "destinationUniCode",
 "totalPrice",
 "shipmentPINs",
 "lineItemDetails",
 "accessorialDetails",
 "shipmentTaxes"
})
public class CreateShipmentResponseContainer
 extends ResponseContainer
{

 @XmlElementRef(name = "ProNumber", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> proNumber;
 @XmlElementRef(name = "PickupNumber", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> pickupNumber;
 @XmlElementRef(name = "TariffCode", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> tariffCode;
 @XmlElement(name = "DiscountPoint")
 protected BigDecimal discountPoint;
 @XmlElement(name = "TransitDays")
 protected Integer transitDays;
 @XmlElementRef(name = "EstimatedDeliveryDate", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> estimatedDeliveryDate;
 @XmlElementRef(name = "OriginalTerminalCode", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> originalTerminalCode;
 @XmlElementRef(name = "DestinationTerminalCode", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> destinationTerminalCode;
 @XmlElementRef(name = "DestinationUniCode", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> destinationUniCode;
 @XmlElement(name = "TotalPrice")
 protected BigDecimal totalPrice;
 @XmlElementRef(name = "ShipmentPINs", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ArrayOfPIN> shipmentPINs;
 @XmlElementRef(name = "LineItemDetails", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ArrayOfLineItem> lineItemDetails;
 @XmlElementRef(name = "AccessorialDetails", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ArrayOfAccessorialItem> accessorialDetails;
 @XmlElementRef(name = "ShipmentTaxes", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ArrayOfTax> shipmentTaxes;

 /**
 * Gets the value of the proNumber property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getProNumber() {
 return proNumber;
 }

 /**
 * Sets the value of the proNumber property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setProNumber(JAXBElement<String> value) {
 this.proNumber = ((JAXBElement<String> ) value);
 }

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

 /**
 * Gets the value of the tariffCode property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getTariffCode() {
 return tariffCode;
 }

 /**
 * Sets the value of the tariffCode property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setTariffCode(JAXBElement<String> value) {
 this.tariffCode = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the discountPoint property.
 *
 * @return
 * possible object is
 * {@link BigDecimal }
 *
 */
 public BigDecimal getDiscountPoint() {
 return discountPoint;
 }

 /**
 * Sets the value of the discountPoint property.
 *
 * @param value
 * allowed object is
 * {@link BigDecimal }
 *
 */
 public void setDiscountPoint(BigDecimal value) {
 this.discountPoint = value;
 }

 /**
 * Gets the value of the transitDays property.
 *
 * @return
 * possible object is
 * {@link Integer }
 *
 */
 public Integer getTransitDays() {
 return transitDays;
 }

 /**
 * Sets the value of the transitDays property.
 *
 * @param value
 * allowed object is
 * {@link Integer }
 *
 */
 public void setTransitDays(Integer value) {
 this.transitDays = value;
 }

 /**
 * Gets the value of the estimatedDeliveryDate property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getEstimatedDeliveryDate() {
 return estimatedDeliveryDate;
 }

 /**
 * Sets the value of the estimatedDeliveryDate property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setEstimatedDeliveryDate(JAXBElement<String> value) {
 this.estimatedDeliveryDate = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the originalTerminalCode property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getOriginalTerminalCode() {
 return originalTerminalCode;
 }

 /**
 * Sets the value of the originalTerminalCode property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setOriginalTerminalCode(JAXBElement<String> value) {
 this.originalTerminalCode = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the destinationTerminalCode property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getDestinationTerminalCode() {
 return destinationTerminalCode;
 }

 /**
 * Sets the value of the destinationTerminalCode property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setDestinationTerminalCode(JAXBElement<String> value) {
 this.destinationTerminalCode = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the destinationUniCode property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getDestinationUniCode() {
 return destinationUniCode;
 }

 /**
 * Sets the value of the destinationUniCode property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setDestinationUniCode(JAXBElement<String> value) {
 this.destinationUniCode = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the totalPrice property.
 *
 * @return
 * possible object is
 * {@link BigDecimal }
 *
 */
 public BigDecimal getTotalPrice() {
 return totalPrice;
 }

 /**
 * Sets the value of the totalPrice property.
 *
 * @param value
 * allowed object is
 * {@link BigDecimal }
 *
 */
 public void setTotalPrice(BigDecimal value) {
 this.totalPrice = value;
 }

 /**
 * Gets the value of the shipmentPINs property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ArrayOfPIN }{@code >}
 *
 */
 public JAXBElement<ArrayOfPIN> getShipmentPINs() {
 return shipmentPINs;
 }

 /**
 * Sets the value of the shipmentPINs property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ArrayOfPIN }{@code >}
 *
 */
 public void setShipmentPINs(JAXBElement<ArrayOfPIN> value) {
 this.shipmentPINs = ((JAXBElement<ArrayOfPIN> ) value);
 }

 /**
 * Gets the value of the lineItemDetails property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ArrayOfLineItem }{@code >}
 *
 */
 public JAXBElement<ArrayOfLineItem> getLineItemDetails() {
 return lineItemDetails;
 }

 /**
 * Sets the value of the lineItemDetails property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ArrayOfLineItem }{@code >}
 *
 */
 public void setLineItemDetails(JAXBElement<ArrayOfLineItem> value) {
 this.lineItemDetails = ((JAXBElement<ArrayOfLineItem> ) value);
 }

 /**
 * Gets the value of the accessorialDetails property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ArrayOfAccessorialItem }{@code >}
 *
 */
 public JAXBElement<ArrayOfAccessorialItem> getAccessorialDetails() {
 return accessorialDetails;
 }

 /**
 * Sets the value of the accessorialDetails property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ArrayOfAccessorialItem }{@code >}
 *
 */
 public void setAccessorialDetails(JAXBElement<ArrayOfAccessorialItem> value) {
 this.accessorialDetails = ((JAXBElement<ArrayOfAccessorialItem> ) value);
 }

 /**
 * Gets the value of the shipmentTaxes property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ArrayOfTax }{@code >}
 *
 */
 public JAXBElement<ArrayOfTax> getShipmentTaxes() {
 return shipmentTaxes;
 }

 /**
 * Sets the value of the shipmentTaxes property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ArrayOfTax }{@code >}
 *
 */
 public void setShipmentTaxes(JAXBElement<ArrayOfTax> value) {
 this.shipmentTaxes = ((JAXBElement<ArrayOfTax> ) value);
 }

}