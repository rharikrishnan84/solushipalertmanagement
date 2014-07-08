
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * PackageInformation
 * 
 * <p>Java class for PackageInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalWeight" type="{http://purolator.com/pws/datatypes/v1}TotalWeight"/>
 *         &lt;element name="TotalPieces" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PiecesInformation" type="{http://purolator.com/pws/datatypes/v1}ArrayOfPiece" minOccurs="0"/>
 *         &lt;element name="DangerousGoodsDeclarationDocumentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="OptionsInformation" type="{http://purolator.com/pws/datatypes/v1}OptionsInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageInformation", propOrder = {
    "serviceID",
    "description",
    "totalWeight",
    "totalPieces",
    "piecesInformation",
    "dangerousGoodsDeclarationDocumentIndicator",
    "optionsInformation"
})
public class PackageInformation {

    @XmlElement(name = "ServiceID", required = true, nillable = true)
    protected String serviceID;
    @XmlElementRef(name = "Description", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> description;
    @XmlElement(name = "TotalWeight", required = true, nillable = true)
    protected TotalWeight totalWeight;
    @XmlElement(name = "TotalPieces")
    protected int totalPieces;
    @XmlElementRef(name = "PiecesInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPiece> piecesInformation;
    @XmlElement(name = "DangerousGoodsDeclarationDocumentIndicator")
    protected Boolean dangerousGoodsDeclarationDocumentIndicator;
    @XmlElementRef(name = "OptionsInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<OptionsInformation> optionsInformation;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = value;
    }

    /**
     * Gets the value of the totalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link TotalWeight }
     *     
     */
    public TotalWeight getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the value of the totalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalWeight }
     *     
     */
    public void setTotalWeight(TotalWeight value) {
        this.totalWeight = value;
    }

    /**
     * Gets the value of the totalPieces property.
     * 
     */
    public int getTotalPieces() {
        return totalPieces;
    }

    /**
     * Sets the value of the totalPieces property.
     * 
     */
    public void setTotalPieces(int value) {
        this.totalPieces = value;
    }

    /**
     * Gets the value of the piecesInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPiece }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPiece> getPiecesInformation() {
        return piecesInformation;
    }

    /**
     * Sets the value of the piecesInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPiece }{@code >}
     *     
     */
    public void setPiecesInformation(JAXBElement<ArrayOfPiece> value) {
        this.piecesInformation = value;
    }

    /**
     * Gets the value of the dangerousGoodsDeclarationDocumentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDangerousGoodsDeclarationDocumentIndicator() {
        return dangerousGoodsDeclarationDocumentIndicator;
    }

    /**
     * Sets the value of the dangerousGoodsDeclarationDocumentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDangerousGoodsDeclarationDocumentIndicator(Boolean value) {
        this.dangerousGoodsDeclarationDocumentIndicator = value;
    }

    /**
     * Gets the value of the optionsInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OptionsInformation }{@code >}
     *     
     */
    public JAXBElement<OptionsInformation> getOptionsInformation() {
        return optionsInformation;
    }

    /**
     * Sets the value of the optionsInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OptionsInformation }{@code >}
     *     
     */
    public void setOptionsInformation(JAXBElement<OptionsInformation> value) {
        this.optionsInformation = value;
    }

}
