
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * ShipmentEstimate
 * 
 * <p>Java class for ShipmentEstimate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentEstimate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipmentDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpectedDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstimatedTransitDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BasePrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Surcharges" type="{http://purolator.com/pws/datatypes/v1}ArrayOfSurcharge" minOccurs="0"/>
 *         &lt;element name="Taxes" type="{http://purolator.com/pws/datatypes/v1}ArrayOfTax"/>
 *         &lt;element name="OptionPrices" type="{http://purolator.com/pws/datatypes/v1}ArrayOfOptionPrice" minOccurs="0"/>
 *         &lt;element name="TotalPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentEstimate", propOrder = {
    "serviceID",
    "shipmentDate",
    "expectedDeliveryDate",
    "estimatedTransitDays",
    "basePrice",
    "surcharges",
    "taxes",
    "optionPrices",
    "totalPrice"
})
public class ShipmentEstimate {

    @XmlElement(name = "ServiceID", required = true, nillable = true)
    protected String serviceID;
    @XmlElement(name = "ShipmentDate", required = true, nillable = true)
    protected String shipmentDate;
    @XmlElementRef(name = "ExpectedDeliveryDate", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> expectedDeliveryDate;
    @XmlElement(name = "EstimatedTransitDays")
    protected Integer estimatedTransitDays;
    @XmlElement(name = "BasePrice", required = true)
    protected BigDecimal basePrice;
    @XmlElementRef(name = "Surcharges", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSurcharge> surcharges;
    @XmlElement(name = "Taxes", required = true, nillable = true)
    protected ArrayOfTax taxes;
    @XmlElementRef(name = "OptionPrices", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOptionPrice> optionPrices;
    @XmlElement(name = "TotalPrice", required = true)
    protected BigDecimal totalPrice;

    /**
     * Gets the value of the serviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the shipmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentDate() {
        return shipmentDate;
    }

    /**
     * Sets the value of the shipmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentDate(String value) {
        this.shipmentDate = value;
    }

    /**
     * Gets the value of the expectedDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * Sets the value of the expectedDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExpectedDeliveryDate(JAXBElement<String> value) {
        this.expectedDeliveryDate = value;
    }

    /**
     * Gets the value of the estimatedTransitDays property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstimatedTransitDays() {
        return estimatedTransitDays;
    }

    /**
     * Sets the value of the estimatedTransitDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstimatedTransitDays(Integer value) {
        this.estimatedTransitDays = value;
    }

    /**
     * Gets the value of the basePrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the value of the basePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBasePrice(BigDecimal value) {
        this.basePrice = value;
    }

    /**
     * Gets the value of the surcharges property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSurcharge }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSurcharge> getSurcharges() {
        return surcharges;
    }

    /**
     * Sets the value of the surcharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSurcharge }{@code >}
     *     
     */
    public void setSurcharges(JAXBElement<ArrayOfSurcharge> value) {
        this.surcharges = value;
    }

    /**
     * Gets the value of the taxes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTax }
     *     
     */
    public ArrayOfTax getTaxes() {
        return taxes;
    }

    /**
     * Sets the value of the taxes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTax }
     *     
     */
    public void setTaxes(ArrayOfTax value) {
        this.taxes = value;
    }

    /**
     * Gets the value of the optionPrices property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionPrice }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOptionPrice> getOptionPrices() {
        return optionPrices;
    }

    /**
     * Sets the value of the optionPrices property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionPrice }{@code >}
     *     
     */
    public void setOptionPrices(JAXBElement<ArrayOfOptionPrice> value) {
        this.optionPrices = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPrice(BigDecimal value) {
        this.totalPrice = value;
    }

}
