
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackingListHandlingUnitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackingListHandlingUnitType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Commodity" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PackingListCommodityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SpecialInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNumberOfPieces" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalWeight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnitOfMeasurement" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}FreightShipUnitOfMeasurementType" minOccurs="0"/>
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackingListHandlingUnitType", propOrder = {
    "commodity",
    "specialInstructions",
    "totalNumberOfPieces",
    "totalWeight",
    "unitOfMeasurement",
    "currencyCode"
})
public class PackingListHandlingUnitType {

    @XmlElement(name = "Commodity")
    protected List<PackingListCommodityType> commodity;
    @XmlElement(name = "SpecialInstructions")
    protected String specialInstructions;
    @XmlElement(name = "TotalNumberOfPieces")
    protected String totalNumberOfPieces;
    @XmlElement(name = "TotalWeight")
    protected String totalWeight;
    @XmlElement(name = "UnitOfMeasurement")
    protected FreightShipUnitOfMeasurementType unitOfMeasurement;
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;

    /**
     * Gets the value of the commodity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commodity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommodity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackingListCommodityType }
     * 
     * 
     */
    public List<PackingListCommodityType> getCommodity() {
        if (commodity == null) {
            commodity = new ArrayList<PackingListCommodityType>();
        }
        return this.commodity;
    }

    /**
     * Gets the value of the specialInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    /**
     * Sets the value of the specialInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialInstructions(String value) {
        this.specialInstructions = value;
    }

    /**
     * Gets the value of the totalNumberOfPieces property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalNumberOfPieces() {
        return totalNumberOfPieces;
    }

    /**
     * Sets the value of the totalNumberOfPieces property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalNumberOfPieces(String value) {
        this.totalNumberOfPieces = value;
    }

    /**
     * Gets the value of the totalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the value of the totalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalWeight(String value) {
        this.totalWeight = value;
    }

    /**
     * Gets the value of the unitOfMeasurement property.
     * 
     * @return
     *     possible object is
     *     {@link FreightShipUnitOfMeasurementType }
     *     
     */
    public FreightShipUnitOfMeasurementType getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    /**
     * Sets the value of the unitOfMeasurement property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreightShipUnitOfMeasurementType }
     *     
     */
    public void setUnitOfMeasurement(FreightShipUnitOfMeasurementType value) {
        this.unitOfMeasurement = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

}
