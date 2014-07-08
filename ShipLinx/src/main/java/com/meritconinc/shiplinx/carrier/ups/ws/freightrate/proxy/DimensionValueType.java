
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DimensionValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DimensionValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cube" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}CubeType" minOccurs="0"/>
 *         &lt;element name="CWT" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}CWTType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionValueType", propOrder = {
    "cube",
    "cwt"
})
public class DimensionValueType {

    @XmlElement(name = "Cube")
    protected CubeType cube;
    @XmlElement(name = "CWT")
    protected CWTType cwt;

    /**
     * Gets the value of the cube property.
     * 
     * @return
     *     possible object is
     *     {@link CubeType }
     *     
     */
    public CubeType getCube() {
        return cube;
    }

    /**
     * Sets the value of the cube property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeType }
     *     
     */
    public void setCube(CubeType value) {
        this.cube = value;
    }

    /**
     * Gets the value of the cwt property.
     * 
     * @return
     *     possible object is
     *     {@link CWTType }
     *     
     */
    public CWTType getCWT() {
        return cwt;
    }

    /**
     * Sets the value of the cwt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CWTType }
     *     
     */
    public void setCWT(CWTType value) {
        this.cwt = value;
    }

}
