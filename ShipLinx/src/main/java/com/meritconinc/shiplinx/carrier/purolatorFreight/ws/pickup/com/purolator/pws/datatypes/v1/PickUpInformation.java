	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PickUpInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PickUpInformation">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="ReadyTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="CloseTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="PickUpOptions" type="{http://purolator.com/pws/datatypes/v1}ArrayOfBoolValuePair" minOccurs="0"/>
 * &lt;element name="OtherOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="StopNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="DriverNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="PickUpConfirmationEmailFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickUpInformation", propOrder = {
 "pickupDate",
 "readyTime",
 "closeTime",
 "pickUpOptions",
 "otherOption",
 "stopNotes",
 "driverNotes",
 "pickUpConfirmationEmailFlag"
})
public class PickUpInformation {

 @XmlElement(name = "PickupDate", required = true, nillable = true)
 protected String pickupDate;
 @XmlElement(name = "ReadyTime", required = true, nillable = true)
 protected String readyTime;
 @XmlElement(name = "CloseTime", required = true, nillable = true)
 protected String closeTime;
 @XmlElementRef(name = "PickUpOptions", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ArrayOfBoolValuePair> pickUpOptions;
 @XmlElementRef(name = "OtherOption", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> otherOption;
 @XmlElementRef(name = "StopNotes", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> stopNotes;
 @XmlElementRef(name = "DriverNotes", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<String> driverNotes;
 @XmlElement(name = "PickUpConfirmationEmailFlag")
 protected Boolean pickUpConfirmationEmailFlag;

 /**
 * Gets the value of the pickupDate property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getPickupDate() {
 return pickupDate;
 }

 /**
 * Sets the value of the pickupDate property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setPickupDate(String value) {
 this.pickupDate = value;
 }

 /**
 * Gets the value of the readyTime property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getReadyTime() {
 return readyTime;
 }

 /**
 * Sets the value of the readyTime property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setReadyTime(String value) {
 this.readyTime = value;
 }

 /**
 * Gets the value of the closeTime property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getCloseTime() {
 return closeTime;
 }

 /**
 * Sets the value of the closeTime property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setCloseTime(String value) {
 this.closeTime = value;
 }

 /**
 * Gets the value of the pickUpOptions property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ArrayOfBoolValuePair }{@code >}
 *
 */
 public JAXBElement<ArrayOfBoolValuePair> getPickUpOptions() {
 return pickUpOptions;
 }

 /**
 * Sets the value of the pickUpOptions property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ArrayOfBoolValuePair }{@code >}
 *
 */
 public void setPickUpOptions(JAXBElement<ArrayOfBoolValuePair> value) {
 this.pickUpOptions = ((JAXBElement<ArrayOfBoolValuePair> ) value);
 }

 /**
 * Gets the value of the otherOption property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getOtherOption() {
 return otherOption;
 }

 /**
 * Sets the value of the otherOption property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setOtherOption(JAXBElement<String> value) {
 this.otherOption = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the stopNotes property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getStopNotes() {
 return stopNotes;
 }

 /**
 * Sets the value of the stopNotes property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setStopNotes(JAXBElement<String> value) {
 this.stopNotes = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the driverNotes property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public JAXBElement<String> getDriverNotes() {
 return driverNotes;
 }

 /**
 * Sets the value of the driverNotes property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link String }{@code >}
 *
 */
 public void setDriverNotes(JAXBElement<String> value) {
 this.driverNotes = ((JAXBElement<String> ) value);
 }

 /**
 * Gets the value of the pickUpConfirmationEmailFlag property.
 *
 * @return
 * possible object is
 * {@link Boolean }
 *
 */
 public Boolean isPickUpConfirmationEmailFlag() {
 return pickUpConfirmationEmailFlag;
 }

 /**
 * Sets the value of the pickUpConfirmationEmailFlag property.
 *
 * @param value
 * allowed object is
 * {@link Boolean }
 *
 */
 public void setPickUpConfirmationEmailFlag(Boolean value) {
 this.pickUpConfirmationEmailFlag = value;
 }

}