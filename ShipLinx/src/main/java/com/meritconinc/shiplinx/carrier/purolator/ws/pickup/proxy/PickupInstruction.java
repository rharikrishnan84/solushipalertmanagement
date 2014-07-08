
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * PickupInstruction
 * 
 * <p>Java class for PickupInstruction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PickupInstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnyTimeAfter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UntilTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TotalWeight" type="{http://purolator.com/pws/datatypes/v1}Weight" minOccurs="0"/>
 *         &lt;element name="TotalPieces" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BoxesIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PickUpLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdditionalInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SupplyRequestCodes" type="{http://purolator.com/pws/datatypes/v1}SupplyRequestCodes" minOccurs="0"/>
 *         &lt;element name="TrailerAccessible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LoadingDockAvailable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ShipmentOnSkids" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="NumberOfSkids" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickupInstruction", propOrder = {
    "date",
    "anyTimeAfter",
    "untilTime",
    "totalWeight",
    "totalPieces",
    "boxesIndicator",
    "pickUpLocation",
    "additionalInstructions",
    "supplyRequestCodes",
    "trailerAccessible",
    "loadingDockAvailable",
    "shipmentOnSkids",
    "numberOfSkids"
})
public class PickupInstruction {

    @XmlElement(name = "Date", required = true, nillable = true)
    protected String date;
    @XmlElement(name = "AnyTimeAfter", required = true, nillable = true)
    protected String anyTimeAfter;
    @XmlElement(name = "UntilTime", required = true, nillable = true)
    protected String untilTime;
    @XmlElementRef(name = "TotalWeight", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Weight> totalWeight;
    @XmlElement(name = "TotalPieces")
    protected Integer totalPieces;
    @XmlElementRef(name = "BoxesIndicator", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> boxesIndicator;
    @XmlElementRef(name = "PickUpLocation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> pickUpLocation;
    @XmlElementRef(name = "AdditionalInstructions", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> additionalInstructions;
    @XmlElementRef(name = "SupplyRequestCodes", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<SupplyRequestCodes> supplyRequestCodes;
    @XmlElement(name = "TrailerAccessible")
    protected Boolean trailerAccessible;
    @XmlElement(name = "LoadingDockAvailable")
    protected Boolean loadingDockAvailable;
    @XmlElement(name = "ShipmentOnSkids")
    protected Boolean shipmentOnSkids;
    @XmlElementRef(name = "NumberOfSkids", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Integer> numberOfSkids;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the anyTimeAfter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnyTimeAfter() {
        return anyTimeAfter;
    }

    /**
     * Sets the value of the anyTimeAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnyTimeAfter(String value) {
        this.anyTimeAfter = value;
    }

    /**
     * Gets the value of the untilTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUntilTime() {
        return untilTime;
    }

    /**
     * Sets the value of the untilTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUntilTime(String value) {
        this.untilTime = value;
    }

    /**
     * Gets the value of the totalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Weight }{@code >}
     *     
     */
    public JAXBElement<Weight> getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the value of the totalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Weight }{@code >}
     *     
     */
    public void setTotalWeight(JAXBElement<Weight> value) {
        this.totalWeight = value;
    }

    /**
     * Gets the value of the totalPieces property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalPieces() {
        return totalPieces;
    }

    /**
     * Sets the value of the totalPieces property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalPieces(Integer value) {
        this.totalPieces = value;
    }

    /**
     * Gets the value of the boxesIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBoxesIndicator() {
        return boxesIndicator;
    }

    /**
     * Sets the value of the boxesIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBoxesIndicator(JAXBElement<String> value) {
        this.boxesIndicator = value;
    }

    /**
     * Gets the value of the pickUpLocation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPickUpLocation() {
        return pickUpLocation;
    }

    /**
     * Sets the value of the pickUpLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPickUpLocation(JAXBElement<String> value) {
        this.pickUpLocation = value;
    }

    /**
     * Gets the value of the additionalInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAdditionalInstructions() {
        return additionalInstructions;
    }

    /**
     * Sets the value of the additionalInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAdditionalInstructions(JAXBElement<String> value) {
        this.additionalInstructions = value;
    }

    /**
     * Gets the value of the supplyRequestCodes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SupplyRequestCodes }{@code >}
     *     
     */
    public JAXBElement<SupplyRequestCodes> getSupplyRequestCodes() {
        return supplyRequestCodes;
    }

    /**
     * Sets the value of the supplyRequestCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SupplyRequestCodes }{@code >}
     *     
     */
    public void setSupplyRequestCodes(JAXBElement<SupplyRequestCodes> value) {
        this.supplyRequestCodes = value;
    }

    /**
     * Gets the value of the trailerAccessible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTrailerAccessible() {
        return trailerAccessible;
    }

    /**
     * Sets the value of the trailerAccessible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTrailerAccessible(Boolean value) {
        this.trailerAccessible = value;
    }

    /**
     * Gets the value of the loadingDockAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLoadingDockAvailable() {
        return loadingDockAvailable;
    }

    /**
     * Sets the value of the loadingDockAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLoadingDockAvailable(Boolean value) {
        this.loadingDockAvailable = value;
    }

    /**
     * Gets the value of the shipmentOnSkids property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShipmentOnSkids() {
        return shipmentOnSkids;
    }

    /**
     * Sets the value of the shipmentOnSkids property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShipmentOnSkids(Boolean value) {
        this.shipmentOnSkids = value;
    }

    /**
     * Gets the value of the numberOfSkids property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNumberOfSkids() {
        return numberOfSkids;
    }

    /**
     * Sets the value of the numberOfSkids property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNumberOfSkids(JAXBElement<Integer> value) {
        this.numberOfSkids = value;
    }

}
