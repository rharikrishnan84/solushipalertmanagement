
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProductType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="3"/>
 *         &lt;element name="Unit" type="{http://www.ups.com/XMLSchema/XOLTWS/IF/v1.0}UnitType" minOccurs="0"/>
 *         &lt;element name="CommodityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PartNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="JointProductionIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetCostCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetCostDateRange" type="{http://www.ups.com/XMLSchema/XOLTWS/IF/v1.0}NetCostDateType" minOccurs="0"/>
 *         &lt;element name="PreferenceCriteria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProducerInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarksAndNumbers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumberOfPackagesPerCommodity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/IF/v1.0}ProductWeightType" minOccurs="0"/>
 *         &lt;element name="VehicleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ScheduleB" type="{http://www.ups.com/XMLSchema/XOLTWS/IF/v1.0}ScheduleBType" minOccurs="0"/>
 *         &lt;element name="ExportType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEDTotalValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductType", namespace = "http://www.ups.com/XMLSchema/XOLTWS/IF/v1.0", propOrder = {
    "description",
    "unit",
    "commodityCode",
    "partNumber",
    "originCountryCode",
    "jointProductionIndicator",
    "netCostCode",
    "netCostDateRange",
    "preferenceCriteria",
    "producerInfo",
    "marksAndNumbers",
    "numberOfPackagesPerCommodity",
    "productWeight",
    "vehicleID",
    "scheduleB",
    "exportType",
    "sedTotalValue"
})
public class ProductType {

    @XmlElement(name = "Description", required = true)
    protected List<String> description;
    @XmlElement(name = "Unit")
    protected UnitType unit;
    @XmlElement(name = "CommodityCode")
    protected String commodityCode;
    @XmlElement(name = "PartNumber")
    protected String partNumber;
    @XmlElement(name = "OriginCountryCode")
    protected String originCountryCode;
    @XmlElement(name = "JointProductionIndicator")
    protected String jointProductionIndicator;
    @XmlElement(name = "NetCostCode")
    protected String netCostCode;
    @XmlElement(name = "NetCostDateRange")
    protected NetCostDateType netCostDateRange;
    @XmlElement(name = "PreferenceCriteria")
    protected String preferenceCriteria;
    @XmlElement(name = "ProducerInfo")
    protected String producerInfo;
    @XmlElement(name = "MarksAndNumbers")
    protected String marksAndNumbers;
    @XmlElement(name = "NumberOfPackagesPerCommodity")
    protected String numberOfPackagesPerCommodity;
    @XmlElement(name = "ProductWeight")
    protected ProductWeightType productWeight;
    @XmlElement(name = "VehicleID")
    protected String vehicleID;
    @XmlElement(name = "ScheduleB")
    protected ScheduleBType scheduleB;
    @XmlElement(name = "ExportType")
    protected String exportType;
    @XmlElement(name = "SEDTotalValue")
    protected String sedTotalValue;

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDescription() {
        if (description == null) {
            description = new ArrayList<String>();
        }
        return this.description;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setUnit(UnitType value) {
        this.unit = value;
    }

    /**
     * Gets the value of the commodityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityCode() {
        return commodityCode;
    }

    /**
     * Sets the value of the commodityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityCode(String value) {
        this.commodityCode = value;
    }

    /**
     * Gets the value of the partNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * Sets the value of the partNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNumber(String value) {
        this.partNumber = value;
    }

    /**
     * Gets the value of the originCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginCountryCode() {
        return originCountryCode;
    }

    /**
     * Sets the value of the originCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginCountryCode(String value) {
        this.originCountryCode = value;
    }

    /**
     * Gets the value of the jointProductionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJointProductionIndicator() {
        return jointProductionIndicator;
    }

    /**
     * Sets the value of the jointProductionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJointProductionIndicator(String value) {
        this.jointProductionIndicator = value;
    }

    /**
     * Gets the value of the netCostCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetCostCode() {
        return netCostCode;
    }

    /**
     * Sets the value of the netCostCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetCostCode(String value) {
        this.netCostCode = value;
    }

    /**
     * Gets the value of the netCostDateRange property.
     * 
     * @return
     *     possible object is
     *     {@link NetCostDateType }
     *     
     */
    public NetCostDateType getNetCostDateRange() {
        return netCostDateRange;
    }

    /**
     * Sets the value of the netCostDateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetCostDateType }
     *     
     */
    public void setNetCostDateRange(NetCostDateType value) {
        this.netCostDateRange = value;
    }

    /**
     * Gets the value of the preferenceCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceCriteria() {
        return preferenceCriteria;
    }

    /**
     * Sets the value of the preferenceCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceCriteria(String value) {
        this.preferenceCriteria = value;
    }

    /**
     * Gets the value of the producerInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerInfo() {
        return producerInfo;
    }

    /**
     * Sets the value of the producerInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerInfo(String value) {
        this.producerInfo = value;
    }

    /**
     * Gets the value of the marksAndNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarksAndNumbers() {
        return marksAndNumbers;
    }

    /**
     * Sets the value of the marksAndNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarksAndNumbers(String value) {
        this.marksAndNumbers = value;
    }

    /**
     * Gets the value of the numberOfPackagesPerCommodity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfPackagesPerCommodity() {
        return numberOfPackagesPerCommodity;
    }

    /**
     * Sets the value of the numberOfPackagesPerCommodity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfPackagesPerCommodity(String value) {
        this.numberOfPackagesPerCommodity = value;
    }

    /**
     * Gets the value of the productWeight property.
     * 
     * @return
     *     possible object is
     *     {@link ProductWeightType }
     *     
     */
    public ProductWeightType getProductWeight() {
        return productWeight;
    }

    /**
     * Sets the value of the productWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductWeightType }
     *     
     */
    public void setProductWeight(ProductWeightType value) {
        this.productWeight = value;
    }

    /**
     * Gets the value of the vehicleID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * Sets the value of the vehicleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleID(String value) {
        this.vehicleID = value;
    }

    /**
     * Gets the value of the scheduleB property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleBType }
     *     
     */
    public ScheduleBType getScheduleB() {
        return scheduleB;
    }

    /**
     * Sets the value of the scheduleB property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleBType }
     *     
     */
    public void setScheduleB(ScheduleBType value) {
        this.scheduleB = value;
    }

    /**
     * Gets the value of the exportType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportType() {
        return exportType;
    }

    /**
     * Sets the value of the exportType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportType(String value) {
        this.exportType = value;
    }

    /**
     * Gets the value of the sedTotalValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEDTotalValue() {
        return sedTotalValue;
    }

    /**
     * Sets the value of the sedTotalValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEDTotalValue(String value) {
        this.sedTotalValue = value;
    }

}
