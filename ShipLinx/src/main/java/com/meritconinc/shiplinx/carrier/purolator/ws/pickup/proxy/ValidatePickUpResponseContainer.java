
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ValidatePickUpRespone
 * 
 * <p>Java class for ValidatePickUpResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidatePickUpResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="IsBulkdRequired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CutOffTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CutOffWindow" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BulkMaxWeight" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="BulkMaxPackages" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidatePickUpResponseContainer", propOrder = {
    "isBulkdRequired",
    "cutOffTime",
    "cutOffWindow",
    "bulkMaxWeight",
    "bulkMaxPackages"
})
public class ValidatePickUpResponseContainer
    extends ResponseContainer
{

    @XmlElement(name = "IsBulkdRequired")
    protected boolean isBulkdRequired;
    @XmlElement(name = "CutOffTime", required = true, nillable = true)
    protected String cutOffTime;
    @XmlElement(name = "CutOffWindow")
    protected int cutOffWindow;
    @XmlElement(name = "BulkMaxWeight", required = true)
    protected BigDecimal bulkMaxWeight;
    @XmlElement(name = "BulkMaxPackages")
    protected int bulkMaxPackages;

    /**
     * Gets the value of the isBulkdRequired property.
     * 
     */
    public boolean isIsBulkdRequired() {
        return isBulkdRequired;
    }

    /**
     * Sets the value of the isBulkdRequired property.
     * 
     */
    public void setIsBulkdRequired(boolean value) {
        this.isBulkdRequired = value;
    }

    /**
     * Gets the value of the cutOffTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCutOffTime() {
        return cutOffTime;
    }

    /**
     * Sets the value of the cutOffTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCutOffTime(String value) {
        this.cutOffTime = value;
    }

    /**
     * Gets the value of the cutOffWindow property.
     * 
     */
    public int getCutOffWindow() {
        return cutOffWindow;
    }

    /**
     * Sets the value of the cutOffWindow property.
     * 
     */
    public void setCutOffWindow(int value) {
        this.cutOffWindow = value;
    }

    /**
     * Gets the value of the bulkMaxWeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBulkMaxWeight() {
        return bulkMaxWeight;
    }

    /**
     * Sets the value of the bulkMaxWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBulkMaxWeight(BigDecimal value) {
        this.bulkMaxWeight = value;
    }

    /**
     * Gets the value of the bulkMaxPackages property.
     * 
     */
    public int getBulkMaxPackages() {
        return bulkMaxPackages;
    }

    /**
     * Sets the value of the bulkMaxPackages property.
     * 
     */
    public void setBulkMaxPackages(int value) {
        this.bulkMaxPackages = value;
    }

}
