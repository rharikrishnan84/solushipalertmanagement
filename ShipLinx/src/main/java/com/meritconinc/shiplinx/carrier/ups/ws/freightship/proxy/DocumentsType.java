
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Image" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ImageType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PackingList" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PackingListType" minOccurs="0"/>
 *         &lt;element name="InternationalForms" type="{http://www.ups.com/XMLSchema/XOLTWS/IF/v1.0}InternationalFormType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentsType", propOrder = {
    "image",
    "packingList",
    "internationalForms"
})
public class DocumentsType {

    @XmlElement(name = "Image")
    protected List<ImageType> image;
    @XmlElement(name = "PackingList")
    protected PackingListType packingList;
    @XmlElement(name = "InternationalForms")
    protected InternationalFormType internationalForms;

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageType }
     * 
     * 
     */
    public List<ImageType> getImage() {
        if (image == null) {
            image = new ArrayList<ImageType>();
        }
        return this.image;
    }

    /**
     * Gets the value of the packingList property.
     * 
     * @return
     *     possible object is
     *     {@link PackingListType }
     *     
     */
    public PackingListType getPackingList() {
        return packingList;
    }

    /**
     * Sets the value of the packingList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackingListType }
     *     
     */
    public void setPackingList(PackingListType value) {
        this.packingList = value;
    }

    /**
     * Gets the value of the internationalForms property.
     * 
     * @return
     *     possible object is
     *     {@link InternationalFormType }
     *     
     */
    public InternationalFormType getInternationalForms() {
        return internationalForms;
    }

    /**
     * Sets the value of the internationalForms property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternationalFormType }
     *     
     */
    public void setInternationalForms(InternationalFormType value) {
        this.internationalForms = value;
    }

}
