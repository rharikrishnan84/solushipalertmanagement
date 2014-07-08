
package com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy;

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
 *         &lt;element name="RateInqResult" type="{http://tempuri.org/RATEINQ/Service1}ReturnRates" minOccurs="0"/>
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
    "rateInqResult"
})
@XmlRootElement(name = "RateInqResponse")
public class RateInqResponse {

    @XmlElement(name = "RateInqResult")
    protected ReturnRates rateInqResult;

    /**
     * Gets the value of the rateInqResult property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnRates }
     *     
     */
    public ReturnRates getRateInqResult() {
        return rateInqResult;
    }

    /**
     * Sets the value of the rateInqResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnRates }
     *     
     */
    public void setRateInqResult(ReturnRates value) {
        this.rateInqResult = value;
    }

}
