package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseContainer complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ResponseContainer">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="ResponseInformation" type="{http://purolator.com/pws/datatypes/v1}ResponseInformation"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseContainer", propOrder = {
 "responseInformation"
})
@XmlSeeAlso({
 GetEstimateResponseContainer.class
})
public class ResponseContainer {

 @XmlElement(name = "ResponseInformation", required = true, nillable = true)
 protected ResponseInformation responseInformation;

 /**
 * Gets the value of the responseInformation property.
 *
 * @return
 * possible object is
 * {@link ResponseInformation }
 *
 */
 public ResponseInformation getResponseInformation() {
 return responseInformation;
 }

 /**
 * Sets the value of the responseInformation property.
 *
 * @param value
 * allowed object is
 * {@link ResponseInformation }
 *
 */
 public void setResponseInformation(ResponseInformation value) {
 this.responseInformation = value;
 }

}