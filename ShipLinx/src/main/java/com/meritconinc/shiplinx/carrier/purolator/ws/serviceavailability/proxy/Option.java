
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Option
 * 
 * <p>Java class for Option complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Option">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ValueType" type="{http://purolator.com/pws/datatypes/v1}ValueType"/>
 *         &lt;element name="AvailableForPieces" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PossibleValues" type="{http://purolator.com/pws/datatypes/v1}ArrayOfOptionValue"/>
 *         &lt;element name="ChildServiceOptions" type="{http://purolator.com/pws/datatypes/v1}ArrayOfOption" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Option", propOrder = {
    "id",
    "description",
    "valueType",
    "availableForPieces",
    "possibleValues",
    "childServiceOptions"
})
public class Option {

    @XmlElement(name = "ID", required = true, nillable = true)
    protected String id;
    @XmlElement(name = "Description", required = true, nillable = true)
    protected String description;
    @XmlElement(name = "ValueType", required = true)
    protected ValueType valueType;
    @XmlElement(name = "AvailableForPieces")
    protected boolean availableForPieces;
    @XmlElement(name = "PossibleValues", required = true, nillable = true)
    protected ArrayOfOptionValue possibleValues;
    @XmlElementRef(name = "ChildServiceOptions", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOption> childServiceOptions;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the valueType property.
     * 
     * @return
     *     possible object is
     *     {@link ValueType }
     *     
     */
    public ValueType getValueType() {
        return valueType;
    }

    /**
     * Sets the value of the valueType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueType }
     *     
     */
    public void setValueType(ValueType value) {
        this.valueType = value;
    }

    /**
     * Gets the value of the availableForPieces property.
     * 
     */
    public boolean isAvailableForPieces() {
        return availableForPieces;
    }

    /**
     * Sets the value of the availableForPieces property.
     * 
     */
    public void setAvailableForPieces(boolean value) {
        this.availableForPieces = value;
    }

    /**
     * Gets the value of the possibleValues property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOptionValue }
     *     
     */
    public ArrayOfOptionValue getPossibleValues() {
        return possibleValues;
    }

    /**
     * Sets the value of the possibleValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOptionValue }
     *     
     */
    public void setPossibleValues(ArrayOfOptionValue value) {
        this.possibleValues = value;
    }

    /**
     * Gets the value of the childServiceOptions property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOption }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOption> getChildServiceOptions() {
        return childServiceOptions;
    }

    /**
     * Sets the value of the childServiceOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOption }{@code >}
     *     
     */
    public void setChildServiceOptions(JAXBElement<ArrayOfOption> value) {
        this.childServiceOptions = value;
    }

}
