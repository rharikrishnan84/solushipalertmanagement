
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
 *         &lt;element ref="{http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0}Response"/>
 *         &lt;element name="SummaryResult">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Status" type="{http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1}CodeDescriptionType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PackageLevelResult" type="{http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1}PackageLevelResult" maxOccurs="unbounded" minOccurs="0"/>
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
    "response",
    "summaryResult",
    "packageLevelResult"
})
@XmlRootElement(name = "VoidShipmentResponse", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1")
public class VoidShipmentResponse {

    @XmlElement(name = "Response", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected ResponseType response;
    @XmlElement(name = "SummaryResult", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1", required = true)
    protected VoidShipmentResponse.SummaryResult summaryResult;
    @XmlElement(name = "PackageLevelResult", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1")
    protected List<PackageLevelResult> packageLevelResult;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseType }
     *     
     */
    public ResponseType getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseType }
     *     
     */
    public void setResponse(ResponseType value) {
        this.response = value;
    }

    /**
     * Gets the value of the summaryResult property.
     * 
     * @return
     *     possible object is
     *     {@link VoidShipmentResponse.SummaryResult }
     *     
     */
    public VoidShipmentResponse.SummaryResult getSummaryResult() {
        return summaryResult;
    }

    /**
     * Sets the value of the summaryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoidShipmentResponse.SummaryResult }
     *     
     */
    public void setSummaryResult(VoidShipmentResponse.SummaryResult value) {
        this.summaryResult = value;
    }

    /**
     * Gets the value of the packageLevelResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageLevelResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageLevelResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageLevelResult }
     * 
     * 
     */
    public List<PackageLevelResult> getPackageLevelResult() {
        if (packageLevelResult == null) {
            packageLevelResult = new ArrayList<PackageLevelResult>();
        }
        return this.packageLevelResult;
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
     *         &lt;element name="Status" type="{http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1}CodeDescriptionType"/>
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
        "status"
    })
    public static class SummaryResult {

        @XmlElement(name = "Status", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Void/v1.1", required = true)
        protected VoidCodeDescriptionType status;

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link VoidCodeDescriptionType }
         *     
         */
        public VoidCodeDescriptionType getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link VoidCodeDescriptionType }
         *     
         */
        public void setStatus(VoidCodeDescriptionType value) {
            this.status = value;
        }

    }

}
