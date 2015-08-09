package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * GetFullEstimateRequest
 *
 * <p>Java class for GetEstimateRequestContainer complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetEstimateRequestContainer">
 * &lt;complexContent>
 * &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 * &lt;sequence>
 * &lt;element name="Estimate" type="{http://purolator.com/pws/datatypes/v1}Shipment"/>
 * &lt;/sequence>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetEstimateRequestContainer", propOrder = {
 "estimate"
})
public class GetEstimateRequestContainer
 extends RequestContainer
{

 @XmlElement(name = "Estimate", required = true, nillable = true)
 protected Shipment estimate;

 /**
 * Gets the value of the estimate property.
 *
 * @return
 * possible object is
 * {@link Shipment }
 *
 */
 public Shipment getEstimate() {
 return estimate;
 }

 /**
 * Sets the value of the estimate property.
 *
 * @param value
 * allowed object is
 * {@link Shipment }
 *
 */
 public void setEstimate(Shipment value) {
 this.estimate = value;
 }

}