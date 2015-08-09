	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AlertInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AlertInformation">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="AlertDetails" type="{http://purolator.com/pws/datatypes/v1}ArrayOfAlertDetail" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlertInformation", propOrder = {
 "alertDetails"
})
public class AlertInformation {

 @XmlElementRef(name = "AlertDetails", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ArrayOfAlertDetail> alertDetails;

 /**
 * Gets the value of the alertDetails property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ArrayOfAlertDetail }{@code >}
 *
 */
 public JAXBElement<ArrayOfAlertDetail> getAlertDetails() {
 return alertDetails;
 }

 /**
 * Sets the value of the alertDetails property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ArrayOfAlertDetail }{@code >}
 *
 */
 public void setAlertDetails(JAXBElement<ArrayOfAlertDetail> value) {
 this.alertDetails = ((JAXBElement<ArrayOfAlertDetail> ) value);
 }

}