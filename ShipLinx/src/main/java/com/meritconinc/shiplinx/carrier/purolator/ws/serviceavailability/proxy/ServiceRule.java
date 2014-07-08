
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * ServiceRules
 * 
 * <p>Java class for ServiceRule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MinimumTotalPieces" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaximumTotalPieces" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MinimumTotalWeight" type="{http://purolator.com/pws/datatypes/v1}Weight"/>
 *         &lt;element name="MaximumTotalWeight" type="{http://purolator.com/pws/datatypes/v1}Weight"/>
 *         &lt;element name="MinimumPieceWeight" type="{http://purolator.com/pws/datatypes/v1}Weight"/>
 *         &lt;element name="MaximumPieceWeight" type="{http://purolator.com/pws/datatypes/v1}Weight"/>
 *         &lt;element name="MinimumPieceLength" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="MaximumPieceLength" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="MinimumPieceWidth" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="MaximumPieceWidth" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="MinimumPieceHeight" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="MaximumPieceHeight" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="MaximumSize" type="{http://purolator.com/pws/datatypes/v1}Dimension" minOccurs="0"/>
 *         &lt;element name="MaximumDeclaredValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRule", propOrder = {
    "serviceID",
    "minimumTotalPieces",
    "maximumTotalPieces",
    "minimumTotalWeight",
    "maximumTotalWeight",
    "minimumPieceWeight",
    "maximumPieceWeight",
    "minimumPieceLength",
    "maximumPieceLength",
    "minimumPieceWidth",
    "maximumPieceWidth",
    "minimumPieceHeight",
    "maximumPieceHeight",
    "maximumSize",
    "maximumDeclaredValue"
})
public class ServiceRule {

    @XmlElement(name = "ServiceID", required = true, nillable = true)
    protected String serviceID;
    @XmlElement(name = "MinimumTotalPieces")
    protected int minimumTotalPieces;
    @XmlElement(name = "MaximumTotalPieces")
    protected int maximumTotalPieces;
    @XmlElement(name = "MinimumTotalWeight", required = true, nillable = true)
    protected Weight minimumTotalWeight;
    @XmlElement(name = "MaximumTotalWeight", required = true, nillable = true)
    protected Weight maximumTotalWeight;
    @XmlElement(name = "MinimumPieceWeight", required = true, nillable = true)
    protected Weight minimumPieceWeight;
    @XmlElement(name = "MaximumPieceWeight", required = true, nillable = true)
    protected Weight maximumPieceWeight;
    @XmlElementRef(name = "MinimumPieceLength", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> minimumPieceLength;
    @XmlElementRef(name = "MaximumPieceLength", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> maximumPieceLength;
    @XmlElementRef(name = "MinimumPieceWidth", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> minimumPieceWidth;
    @XmlElementRef(name = "MaximumPieceWidth", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> maximumPieceWidth;
    @XmlElementRef(name = "MinimumPieceHeight", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> minimumPieceHeight;
    @XmlElementRef(name = "MaximumPieceHeight", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> maximumPieceHeight;
    @XmlElementRef(name = "MaximumSize", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<Dimension> maximumSize;
    @XmlElement(name = "MaximumDeclaredValue")
    protected BigDecimal maximumDeclaredValue;

    /**
     * Gets the value of the serviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the minimumTotalPieces property.
     * 
     */
    public int getMinimumTotalPieces() {
        return minimumTotalPieces;
    }

    /**
     * Sets the value of the minimumTotalPieces property.
     * 
     */
    public void setMinimumTotalPieces(int value) {
        this.minimumTotalPieces = value;
    }

    /**
     * Gets the value of the maximumTotalPieces property.
     * 
     */
    public int getMaximumTotalPieces() {
        return maximumTotalPieces;
    }

    /**
     * Sets the value of the maximumTotalPieces property.
     * 
     */
    public void setMaximumTotalPieces(int value) {
        this.maximumTotalPieces = value;
    }

    /**
     * Gets the value of the minimumTotalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Weight }
     *     
     */
    public Weight getMinimumTotalWeight() {
        return minimumTotalWeight;
    }

    /**
     * Sets the value of the minimumTotalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Weight }
     *     
     */
    public void setMinimumTotalWeight(Weight value) {
        this.minimumTotalWeight = value;
    }

    /**
     * Gets the value of the maximumTotalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Weight }
     *     
     */
    public Weight getMaximumTotalWeight() {
        return maximumTotalWeight;
    }

    /**
     * Sets the value of the maximumTotalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Weight }
     *     
     */
    public void setMaximumTotalWeight(Weight value) {
        this.maximumTotalWeight = value;
    }

    /**
     * Gets the value of the minimumPieceWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Weight }
     *     
     */
    public Weight getMinimumPieceWeight() {
        return minimumPieceWeight;
    }

    /**
     * Sets the value of the minimumPieceWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Weight }
     *     
     */
    public void setMinimumPieceWeight(Weight value) {
        this.minimumPieceWeight = value;
    }

    /**
     * Gets the value of the maximumPieceWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Weight }
     *     
     */
    public Weight getMaximumPieceWeight() {
        return maximumPieceWeight;
    }

    /**
     * Sets the value of the maximumPieceWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Weight }
     *     
     */
    public void setMaximumPieceWeight(Weight value) {
        this.maximumPieceWeight = value;
    }

    /**
     * Gets the value of the minimumPieceLength property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getMinimumPieceLength() {
        return minimumPieceLength;
    }

    /**
     * Sets the value of the minimumPieceLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setMinimumPieceLength(JAXBElement<Dimension> value) {
        this.minimumPieceLength = value;
    }

    /**
     * Gets the value of the maximumPieceLength property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getMaximumPieceLength() {
        return maximumPieceLength;
    }

    /**
     * Sets the value of the maximumPieceLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setMaximumPieceLength(JAXBElement<Dimension> value) {
        this.maximumPieceLength = value;
    }

    /**
     * Gets the value of the minimumPieceWidth property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getMinimumPieceWidth() {
        return minimumPieceWidth;
    }

    /**
     * Sets the value of the minimumPieceWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setMinimumPieceWidth(JAXBElement<Dimension> value) {
        this.minimumPieceWidth = value;
    }

    /**
     * Gets the value of the maximumPieceWidth property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getMaximumPieceWidth() {
        return maximumPieceWidth;
    }

    /**
     * Sets the value of the maximumPieceWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setMaximumPieceWidth(JAXBElement<Dimension> value) {
        this.maximumPieceWidth = value;
    }

    /**
     * Gets the value of the minimumPieceHeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getMinimumPieceHeight() {
        return minimumPieceHeight;
    }

    /**
     * Sets the value of the minimumPieceHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setMinimumPieceHeight(JAXBElement<Dimension> value) {
        this.minimumPieceHeight = value;
    }

    /**
     * Gets the value of the maximumPieceHeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getMaximumPieceHeight() {
        return maximumPieceHeight;
    }

    /**
     * Sets the value of the maximumPieceHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setMaximumPieceHeight(JAXBElement<Dimension> value) {
        this.maximumPieceHeight = value;
    }

    /**
     * Gets the value of the maximumSize property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public JAXBElement<Dimension> getMaximumSize() {
        return maximumSize;
    }

    /**
     * Sets the value of the maximumSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Dimension }{@code >}
     *     
     */
    public void setMaximumSize(JAXBElement<Dimension> value) {
        this.maximumSize = value;
    }

    /**
     * Gets the value of the maximumDeclaredValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximumDeclaredValue() {
        return maximumDeclaredValue;
    }

    /**
     * Sets the value of the maximumDeclaredValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximumDeclaredValue(BigDecimal value) {
        this.maximumDeclaredValue = value;
    }

}
