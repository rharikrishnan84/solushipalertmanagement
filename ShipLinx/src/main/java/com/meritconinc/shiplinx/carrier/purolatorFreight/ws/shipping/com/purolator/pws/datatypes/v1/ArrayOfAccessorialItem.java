	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfAccessorialItem complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfAccessorialItem">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="AccessorialItem" type="{http://purolator.com/pws/datatypes/v1}AccessorialItem" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAccessorialItem", propOrder = {
 "accessorialItem"
})
public class ArrayOfAccessorialItem {

 @XmlElement(name = "AccessorialItem", nillable = true)
 protected List<AccessorialItem> accessorialItem;

 /**
 * Gets the value of the accessorialItem property.
 *
 * <p>
 * This accessor method returns a reference to the live list,
 * not a snapshot. Therefore any modification you make to the
 * returned list will be present inside the JAXB object.
 * This is why there is not a <CODE>set</CODE> method for the accessorialItem property.
 *
 * <p>
 * For example, to add a new item, do as follows:
 * <pre>
 * getAccessorialItem().add(newItem);
 * </pre>
 *
 *
 * <p>
 * Objects of the following type(s) are allowed in the list
 * {@link AccessorialItem }
 *
 *
 */
 public List<AccessorialItem> getAccessorialItem() {
 if (accessorialItem == null) {
 accessorialItem = new ArrayList<AccessorialItem>();
 }
 return this.accessorialItem;
 }

}