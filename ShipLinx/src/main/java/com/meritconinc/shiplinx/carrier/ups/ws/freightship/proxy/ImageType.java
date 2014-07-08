
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType"/>
 *         &lt;element name="LabelsPerPage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Format" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType"/>
 *         &lt;element name="PrintFormat" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="PrintSize" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PrintSizeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageType", propOrder = {
    "type",
    "labelsPerPage",
    "format",
    "printFormat",
    "printSize"
})
public class ImageType {

    @XmlElement(name = "Type", required = true)
    protected ShipCodeDescriptionType type;
    @XmlElement(name = "LabelsPerPage")
    protected String labelsPerPage;
    @XmlElement(name = "Format", required = true)
    protected ShipCodeDescriptionType format;
    @XmlElement(name = "PrintFormat")
    protected ShipCodeDescriptionType printFormat;
    @XmlElement(name = "PrintSize")
    protected PrintSizeType printSize;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setType(ShipCodeDescriptionType value) {
        this.type = value;
    }

    /**
     * Gets the value of the labelsPerPage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelsPerPage() {
        return labelsPerPage;
    }

    /**
     * Sets the value of the labelsPerPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelsPerPage(String value) {
        this.labelsPerPage = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setFormat(ShipCodeDescriptionType value) {
        this.format = value;
    }

    /**
     * Gets the value of the printFormat property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getPrintFormat() {
        return printFormat;
    }

    /**
     * Sets the value of the printFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setPrintFormat(ShipCodeDescriptionType value) {
        this.printFormat = value;
    }

    /**
     * Gets the value of the printSize property.
     * 
     * @return
     *     possible object is
     *     {@link PrintSizeType }
     *     
     */
    public PrintSizeType getPrintSize() {
        return printSize;
    }

    /**
     * Sets the value of the printSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintSizeType }
     *     
     */
    public void setPrintSize(PrintSizeType value) {
        this.printSize = value;
    }

}
