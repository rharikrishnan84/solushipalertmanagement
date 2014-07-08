
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * ModifyPickupInstruction
 * 
 * <p>Java class for ModifyPickupInstruction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModifyPickupInstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UntilTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickUpLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ModifyPickupInstruction", propOrder = {
    "untilTime",
    "pickUpLocation",
    "supplyRequestCodes",
    "trailerAccessible",
    "loadingDockAvailable",
    "shipmentOnSkids",
    "numberOfSkids"
})
public class ModifyPickupInstruction {

    @XmlElement(name = "UntilTime", required = true, nillable = true)
    protected String untilTime;
    @XmlElementRef(name = "PickUpLocation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> pickUpLocation;
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
