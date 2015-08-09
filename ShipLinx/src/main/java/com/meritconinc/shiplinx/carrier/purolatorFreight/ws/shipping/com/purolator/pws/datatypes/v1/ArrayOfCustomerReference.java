	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCustomerReference complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfCustomerReference">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="CustomerReference" type="{http://purolator.com/pws/datatypes/v1}CustomerReference" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCustomerReference", propOrder = {
 "customerReference"
})
public class ArrayOfCustomerReference {

 @XmlElement(name = "CustomerReference", nillable = true)
 protected List<CustomerReference> customerReference;

 /**
 * Gets the value of the customerReference property.
 *
 * <p>
 * This accessor method returns a reference to the live list,
 * not a snapshot. Therefore any modification you make to the
 * returned list will be present inside the JAXB object.
 * This is why there is not a <CODE>set</CODE> method for the customerReference property.
 *
 * <p>
 * For example, to add a new item, do as follows:
 * <pre>
 * getCustomerReference().add(newItem);
 * </pre>
 *
 *
 * <p>
 * Objects of the following type(s) are allowed in the list
 * {@link CustomerReference }
 *
 *
 */
 public List<CustomerReference> getCustomerReference() {
 if (customerReference == null) {
 customerReference = new ArrayList<CustomerReference>();
 }
 return this.customerReference;
 }

}