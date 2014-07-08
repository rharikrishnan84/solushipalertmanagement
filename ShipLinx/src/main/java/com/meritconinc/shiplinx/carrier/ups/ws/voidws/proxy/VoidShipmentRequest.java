
package com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0}Request"/>
 *         &lt;element name="VoidShipment">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ShipmentIdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request",
    "voidShipment"
})
@XmlRootElement(name = "VoidShipmentRequest", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1")
public class VoidShipmentRequest {

    @XmlElement(name = "Request", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected RequestType request;
    @XmlElement(name = "VoidShipment", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1", required = true)
    protected VoidShipmentRequest.VoidShipment voidShipment;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link RequestType }
     *     
     */
    public RequestType getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestType }
     *     
     */
    public void setRequest(RequestType value) {
        this.request = value;
    }

    /**
     * Gets the value of the voidShipment property.
     * 
     * @return
     *     possible object is
     *     {@link VoidShipmentRequest.VoidShipment }
     *     
     */
    public VoidShipmentRequest.VoidShipment getVoidShipment() {
        return voidShipment;
    }

    /**
     * Sets the value of the voidShipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoidShipmentRequest.VoidShipment }
     *     
     */
    public void setVoidShipment(VoidShipmentRequest.VoidShipment value) {
        this.voidShipment = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ShipmentIdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "shipmentIdentificationNumber",
        "trackingNumber"
    })
    public static class VoidShipment {

        @XmlElement(name = "ShipmentIdentificationNumber", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1", required = true)
        protected String shipmentIdentificationNumber;
        @XmlElement(name = "TrackingNumber", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1")
        protected List<String> trackingNumber;

        /**
         * Gets the value of the shipmentIdentificationNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipmentIdentificationNumber() {
            return shipmentIdentificationNumber;
        }

        /**
         * Sets the value of the shipmentIdentificationNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipmentIdentificationNumber(String value) {
            this.shipmentIdentificationNumber = value;
        }

        /**
         * Gets the value of the trackingNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the trackingNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTrackingNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getTrackingNumber() {
            if (trackingNumber == null) {
                trackingNumber = new ArrayList<String>();
            }
            return this.trackingNumber;
        }

    }

}
