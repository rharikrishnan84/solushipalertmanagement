
package com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Piece complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Piece">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
    "height"
})
public class Piece {

    @XmlElement(name = "Weight")
    protected double weight;
    @XmlElement(name = "Length")
    protected double length;
    @XmlElement(name = "Width")
    protected double width;
    @XmlElement(name = "Height")
    protected double height;

    /**
     * Gets the value of the weight property.
     * 
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     */
    public void setWeight(double value) {
        this.weight = value;
    }

    /**
     * Gets the value of the length property.
     * 
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     */
    public void setLength(double value) {
        this.length = value;
    }

    /**
     * Gets the value of the width property.
     * 
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     */
    public void setWidth(double value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     */
    public void setHeight(double value) {
        this.height = value;
    }

}
