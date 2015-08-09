	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ShipmentInformation">
 * &lt;complexContent>
 * &lt;extension base="{http://purolator.com/pws/datatypes/v1}EstimateInformation">
 * &lt;sequence>
 * &lt;element name="CustomerReferences" type="{http://purolator.com/pws/datatypes/v1}ArrayOfCustomerReference" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentInformation", propOrder = {
 "customerReferences"
})
public class ShipmentInformation
 extends EstimateInformation
{

 @XmlElementRef(name = "CustomerReferences", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
 protected JAXBElement<ArrayOfCustomerReference> customerReferences;

 /**
 * Gets the value of the customerReferences property.
 *
 * @return
 * possible object is
 * {@link JAXBElement }{@code <}{@link ArrayOfCustomerReference }{@code >}
 *
 */
 public JAXBElement<ArrayOfCustomerReference> getCustomerReferences() {
 return customerReferences;
 }

 /**
 * Sets the value of the customerReferences property.
 *
 * @param value
 * allowed object is
 * {@link JAXBElement }{@code <}{@link ArrayOfCustomerReference }{@code >}
 *
 */
 public void setCustomerReferences(JAXBElement<ArrayOfCustomerReference> value) {
 this.customerReferences = ((JAXBElement<ArrayOfCustomerReference> ) value);
 }

}