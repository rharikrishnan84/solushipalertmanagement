package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfAlertDetail complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfAlertDetail">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="AlertDetail" type="{http://purolator.com/pws/datatypes/v1}AlertDetail" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAlertDetail", propOrder = {
 "alertDetail"
})
public class ArrayOfAlertDetail {

 @XmlElement(name = "AlertDetail", nillable = true)
 protected List<AlertDetail> alertDetail;

 /**
 * Gets the value of the alertDetail property.
 *
 * <p>
 * This accessor method returns a reference to the live list,
 * not a snapshot. Therefore any modification you make to the
 * returned list will be present inside the JAXB object.
 * This is why there is not a <CODE>set</CODE> method for the alertDetail property.
 *
 * <p>
 * For example, to add a new item, do as follows:
 * <pre>
 * getAlertDetail().add(newItem);
 * </pre>
 *
 *
 * <p>
 * Objects of the following type(s) are allowed in the list
 * {@link AlertDetail }
 *
 *
 */
 public List<AlertDetail> getAlertDetail() {
 if (alertDetail == null) {
 alertDetail = new ArrayList<AlertDetail>();
 }
 return this.alertDetail;
 }

}