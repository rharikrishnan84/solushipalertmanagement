
package com.meritconinc.shiplinx.carrier.eshipplus;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.axis2.databinding.types.soapencoding.DateTime;


/**
 * <p>Java class for WSRate2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSRate2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CarrierKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CarrierName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CarrierScac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BilledWeight" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TransitTime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ServiceMode" type="{http://www.eshipplus.com/}ServiceMode"/>
 *         &lt;element name="FreightCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="FuelCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="AccessorialCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ServiceCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TotalCharges" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Mileage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="MileageSourceKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="MileageSourceDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BillingDetails" type="{http://www.eshipplus.com/}ArrayOfWSBillingDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSRate2", propOrder = {
    "carrierKey",
    "carrierName",
    "carrierScac",
    "billedWeight",
    "ratedWeight",
    "ratedCubicWeight",
    "transitTime",
    "estimatedDeliveryDate",
    "serviceMode",
    "freightCharges",	
    "fuelCharges",
    "accessorialCharges",
    "serviceCharges",
    "totalCharges",
    "mileage",
    "mileageSourceKey",
    "mileageSourceDescription",
    "billingDetails"
})
public class WSRate2 {

    @XmlElement(name = "CarrierKey")
    protected String carrierKey;
    @XmlElement(name = "CarrierName")
    protected String carrierName;
    @XmlElement(name = "CarrierScac")
    protected String carrierScac;
    @XmlElement(name = "BilledWeight", required = true)
    protected BigDecimal billedWeight;
    @XmlElement(name = "RatedWeight", required = true)
	private BigDecimal ratedWeight;
    @XmlElement(name = "RatedCubicFeet", required = true)
	private BigDecimal ratedCubicWeight;
    @XmlElement(name = "TransitTime")
    protected int transitTime;
    @XmlElement(name = "EstimatedDeliveryDate", required = true)
	private DateTime estimatedDeliveryDate;
    @XmlElement(name = "ServiceMode", required = true)
    protected ServiceMode serviceMode;
    @XmlElement(name = "FreightCharges", required = true)
    protected BigDecimal freightCharges;
    @XmlElement(name = "FuelCharges", required = true)
    protected BigDecimal fuelCharges;
    @XmlElement(name = "AccessorialCharges", required = true)
    protected BigDecimal accessorialCharges;
    @XmlElement(name = "ServiceCharges", required = true)
    protected BigDecimal serviceCharges;
    @XmlElement(name = "TotalCharges", required = true)
    protected BigDecimal totalCharges;
    @XmlElement(name = "Mileage", required = true)
    protected BigDecimal mileage;
    @XmlElement(name = "MileageSourceKey")
    protected long mileageSourceKey;
    @XmlElement(name = "MileageSourceDescription")
    protected String mileageSourceDescription;
    @XmlElement(name = "BillingDetails")
    protected ArrayOfWSBillingDetail billingDetails;

    /**
     * Gets the value of the carrierKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierKey() {
        return carrierKey;
    }

    /**
     * Sets the value of the carrierKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierKey(String value) {
        this.carrierKey = value;
    }

    /**
     * Gets the value of the carrierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierName() {
        return carrierName;
    }

    /**
     * Sets the value of the carrierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierName(String value) {
        this.carrierName = value;
    }

    /**
     * Gets the value of the carrierScac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierScac() {
        return carrierScac;
    }

    /**
     * Sets the value of the carrierScac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierScac(String value) {
        this.carrierScac = value;
    }

    /**
     * Gets the value of the billedWeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBilledWeight() {
        return billedWeight;
    }

    /**
     * Sets the value of the billedWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBilledWeight(BigDecimal value) {
        this.billedWeight = value;
    }

    /**
     * Gets the value of the transitTime property.
     * 
     */
    public int getTransitTime() {
        return transitTime;
    }

    /**
     * Sets the value of the transitTime property.
     * 
     */
    public void setTransitTime(int value) {
        this.transitTime = value;
    }

    /**
     * Gets the value of the serviceMode property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceMode }
     *     
     */
    public ServiceMode getServiceMode() {
        return serviceMode;
    }

    /**
     * Sets the value of the serviceMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceMode }
     *     
     */
    public void setServiceMode(ServiceMode value) {
        this.serviceMode = value;
    }

    /**
     * Gets the value of the freightCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFreightCharges() {
        return freightCharges;
    }

    /**
     * Sets the value of the freightCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFreightCharges(BigDecimal value) {
        this.freightCharges = value;
    }

    /**
     * Gets the value of the fuelCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFuelCharges() {
        return fuelCharges;
    }

    /**
     * Sets the value of the fuelCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFuelCharges(BigDecimal value) {
        this.fuelCharges = value;
    }

    /**
     * Gets the value of the accessorialCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAccessorialCharges() {
        return accessorialCharges;
    }

    /**
     * Sets the value of the accessorialCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAccessorialCharges(BigDecimal value) {
        this.accessorialCharges = value;
    }

    /**
     * Gets the value of the serviceCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getServiceCharges() {
        return serviceCharges;
    }

    /**
     * Sets the value of the serviceCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setServiceCharges(BigDecimal value) {
        this.serviceCharges = value;
    }

    /**
     * Gets the value of the totalCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalCharges() {
        return totalCharges;
    }

    /**
     * Sets the value of the totalCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalCharges(BigDecimal value) {
        this.totalCharges = value;
    }

    /**
     * Gets the value of the mileage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMileage() {
        return mileage;
    }

    /**
     * Sets the value of the mileage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMileage(BigDecimal value) {
        this.mileage = value;
    }

    /**
     * Gets the value of the mileageSourceKey property.
     * 
     */
    public long getMileageSourceKey() {
        return mileageSourceKey;
    }

    /**
     * Sets the value of the mileageSourceKey property.
     * 
     */
    public void setMileageSourceKey(long value) {
        this.mileageSourceKey = value;
    }

    /**
     * Gets the value of the mileageSourceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMileageSourceDescription() {
        return mileageSourceDescription;
    }

    /**
     * Sets the value of the mileageSourceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMileageSourceDescription(String value) {
        this.mileageSourceDescription = value;
    }

    /**
     * Gets the value of the billingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSBillingDetail }
     *     
     */
    public ArrayOfWSBillingDetail getBillingDetails() {
        return billingDetails;
    }

    /**
     * Sets the value of the billingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSBillingDetail }
     *     
     */
    public void setBillingDetails(ArrayOfWSBillingDetail value) {
        this.billingDetails = value;
    }

	public BigDecimal getRatedWeight() {
		return ratedWeight;
	}

	public void setRatedWeight(BigDecimal ratedWeight) {
		this.ratedWeight = ratedWeight;
	}

	public BigDecimal getRatedCubicWeight() {
		return ratedCubicWeight;
	}

	public void setRatedCubicWeight(BigDecimal ratedCubicWeight) {
		this.ratedCubicWeight = ratedCubicWeight;
	}

	public DateTime getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(DateTime estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

}
