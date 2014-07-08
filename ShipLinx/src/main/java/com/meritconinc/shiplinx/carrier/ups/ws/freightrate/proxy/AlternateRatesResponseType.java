
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AlternateRatesResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AlternateRatesResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AlternateRateType" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateCodeDescriptionType"/>
 *         &lt;element name="Rate" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateType" maxOccurs="unbounded"/>
 *         &lt;element name="BillableShipmentWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}WeightType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlternateRatesResponseType", propOrder = {
    "alternateRateType",
    "rate",
    "billableShipmentWeight"
})
public class AlternateRatesResponseType {

    @XmlElement(name = "AlternateRateType", required = true)
    protected RateCodeDescriptionType alternateRateType;
    @XmlElement(name = "Rate", required = true)
    protected List<RateType> rate;
    @XmlElement(name = "BillableShipmentWeight")
    protected WeightType billableShipmentWeight;

    /**
     * Gets the value of the alternateRateType property.
     * 
     * @return
     *     possible object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public RateCodeDescriptionType getAlternateRateType() {
        return alternateRateType;
    }

    /**
     * Sets the value of the alternateRateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public void setAlternateRateType(RateCodeDescriptionType value) {
        this.alternateRateType = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RateType }
     * 
     * 
     */
    public List<RateType> getRate() {
        if (rate == null) {
            rate = new ArrayList<RateType>();
        }
        return this.rate;
    }

    /**
     * Gets the value of the billableShipmentWeight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightType }
     *     
     */
    public WeightType getBillableShipmentWeight() {
        return billableShipmentWeight;
    }

    /**
     * Sets the value of the billableShipmentWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightType }
     *     
     */
    public void setBillableShipmentWeight(WeightType value) {
        this.billableShipmentWeight = value;
    }

}
