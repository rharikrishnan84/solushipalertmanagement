
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommodityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommodityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FreightClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NMFC" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}NMFCCommodityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommodityType", propOrder = {
    "freightClass",
    "nmfc"
})
public class CommodityType {

    @XmlElement(name = "FreightClass", required = true)
    protected String freightClass;
    @XmlElement(name = "NMFC")
    protected NMFCCommodityType nmfc;

    /**
     * Gets the value of the freightClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreightClass() {
        return freightClass;
    }

    /**
     * Sets the value of the freightClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreightClass(String value) {
        this.freightClass = value;
    }

    /**
     * Gets the value of the nmfc property.
     * 
     * @return
     *     possible object is
     *     {@link NMFCCommodityType }
     *     
     */
    public NMFCCommodityType getNMFC() {
        return nmfc;
    }

    /**
     * Sets the value of the nmfc property.
     * 
     * @param value
     *     allowed object is
     *     {@link NMFCCommodityType }
     *     
     */
    public void setNMFC(NMFCCommodityType value) {
        this.nmfc = value;
    }

}
