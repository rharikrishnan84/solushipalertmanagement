
package com.meritconinc.shiplinx.carrier.purolator.ws.returnsmanagement.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Piece
 * 
 * <p>Java class for Piece complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Piece">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Weight" type="{http://purolator.com/pws/datatypes/v1}Weight"/>
 *         &lt;element name="Length" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="Width" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="Height" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="Options" type="{http://purolator.com/pws/datatypes/v1}ArrayOfOptionIDValuePair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Piece", propOrder = {
    "weight",
    "length",
    "width",
    "height",
    "options"
})
public class Piece {

    @XmlElement(name = "Weight", required = true, nillable = true)
    protected Weight weight;
    @XmlElementRef(name = "Length", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> length;
    @XmlElementRef(name = "Width", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> width;
    @XmlElementRef(name = "Height", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> height;
    @XmlElementRef(name = "Options", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOptionIDValuePair> options;

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link Weight }
     *     
     */
    public Weight getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Weight }
     *     
     */
    public void setWeight(Weight value) {
        this.weight = value;
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setLength(JAXBElement<Dimension> value) {
        this.length = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setWidth(JAXBElement<Dimension> value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setHeight(JAXBElement<Dimension> value) {
        this.height = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionIDValuePair }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOptionIDValuePair> getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOptionIDValuePair }{@code >}
     *     
     */
    public void setOptions(JAXBElement<ArrayOfOptionIDValuePair> value) {
        this.options = value;
    }

}
