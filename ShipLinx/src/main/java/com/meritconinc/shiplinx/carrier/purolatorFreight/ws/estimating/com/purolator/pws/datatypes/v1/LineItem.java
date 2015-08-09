package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Piece
 *
 * <p>Java class for LineItem complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="LineItem">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="LineNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 * &lt;element name="Pieces" type="{http://www.w3.org/2001/XMLSchema}int"/>
 * &lt;element name="HandlingUnit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 * &lt;element name="HandlingUnitType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="Weight" type="{http://purolator.com/pws/datatypes/v1}Weight"/>
 * &lt;element name="FreightClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="Length" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 * &lt;element name="Width" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 * &lt;element name="Height" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 * &lt;element name="BasePrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 * &lt;element name="Charge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineItem", propOrder = {
 "lineNumber",
 "pieces",
 "handlingUnit",
 "handlingUnitType",
 "description",
 "weight",
 "freightClass",
 "length",
 "width",
 "height",
 "basePrice",
 "charge"
})
public class LineItem {

 @XmlElement(name = "LineNumber")
 protected int lineNumber;
 @XmlElement(name = "Pieces")
 protected int pieces;
 @XmlElement(name = "HandlingUnit")
 protected int handlingUnit;
 @XmlElement(name = "HandlingUnitType", required = true, nillable = true)
 protected String handlingUnitType;
 @XmlElementRef(name = "Description", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> description;
 @XmlElement(name = "Weight", required = true, nillable = true)
 protected Weight weight;
 @XmlElementRef(name = "FreightClass", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> freightClass;
 @XmlElementRef(name = "Length", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<Dimension> length;
 @XmlElementRef(name = "Width", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<Dimension> width;
 @XmlElementRef(name = "Height", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<Dimension> height;
 @XmlElement(name = "BasePrice")
 protected BigDecimal basePrice;
 @XmlElement(name = "Charge")
 protected BigDecimal charge;

 /**
 * Gets the value of the lineNumber property.
 *
 */
 public int getLineNumber() {
 return lineNumber;
 }

 /**
 * Sets the value of the lineNumber property.
 *
 */
 public void setLineNumber(int value) {
 this.lineNumber = value;
 }

 /**
 * Gets the value of the pieces property.
 *
 */
 public int getPieces() {
 return pieces;
 }

 /**
 * Sets the value of the pieces property.
 *
 */
 public void setPieces(int value) {
 this.pieces = value;
 }

 /**
 * Gets the value of the handlingUnit property.
 *
 */
 public int getHandlingUnit() {
 return handlingUnit;
 }

 /**
 * Sets the value of the handlingUnit property.
 *
 */
 public void setHandlingUnit(int value) {
 this.handlingUnit = value;
 }

 /**
 * Gets the value of the handlingUnitType property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getHandlingUnitType() {
 return handlingUnitType;
 }

 /**
 * Sets the value of the handlingUnitType property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setHandlingUnitType(String value) {
 this.handlingUnitType = value;
 }

 /**
 * Gets the value of the description property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getDescription() {
 return description;
 }

 /**
 * Sets the value of the description property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setDescription(JAXBElement<String> value) {
 this.description = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the weight property.
 *
 * @return
 * possible object is
 * {@link Weight }
 *
 */
 public Weight getWeight() {
 return weight;
 }

 /**
 * Sets the value of the weight property.
 *
 * @param value
 * allowed object is
 * {@link Weight }
 *
 */
 public void setWeight(Weight value) {
 this.weight = value;
 }

 /**
 * Gets the value of the freightClass property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getFreightClass() {
 return freightClass;
 }

 /**
 * Sets the value of the freightClass property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setFreightClass(JAXBElement<String> value) {
 this.freightClass = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the length property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link Dimension }{@code >}
 *
 */
 public JAXBElement<Dimension> getLength() {
 return length;
 }

 /**
 * Sets the value of the length property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link Dimension }{@code >}
 *
 */
 public void setLength(JAXBElement<Dimension> value) {
 this.length = ((JAXBElement<Dimension> ) value);
 }

 /**
 * Gets the value of the width property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link Dimension }{@code >}
 *
 */
 public JAXBElement<Dimension> getWidth() {
 return width;
 }

 /**
 * Sets the value of the width property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link Dimension }{@code >}
 *
 */
 public void setWidth(JAXBElement<Dimension> value) {
 this.width = ((JAXBElement<Dimension> ) value);
 }

 /**
 * Gets the value of the height property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link Dimension }{@code >}
 *
 */
 public JAXBElement<Dimension> getHeight() {
 return height;
 }

 /**
 * Sets the value of the height property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link Dimension }{@code >}
 *
 */
 public void setHeight(JAXBElement<Dimension> value) {
 this.height = ((JAXBElement<Dimension> ) value);
 }

 /**
 * Gets the value of the basePrice property.
 *
 * @return
 * possible object is
 * {@link BigDecimal }
 *
 */
 public BigDecimal getBasePrice() {
 return basePrice;
 }

 /**
 * Sets the value of the basePrice property.
 *
 * @param value
 * allowed object is
 * {@link BigDecimal }
 *
 */
 public void setBasePrice(BigDecimal value) {
 this.basePrice = value;
 }

 /**
 * Gets the value of the charge property.
 *
 * @return
 * possible object is
 * {@link BigDecimal }
 *
 */
 public BigDecimal getCharge() {
 return charge;
 }

 /**
 * Sets the value of the charge property.
 *
 * @param value
 * allowed object is
 * {@link BigDecimal }
 *
 */
 public void setCharge(BigDecimal value) {
 this.charge = value;
 }

}