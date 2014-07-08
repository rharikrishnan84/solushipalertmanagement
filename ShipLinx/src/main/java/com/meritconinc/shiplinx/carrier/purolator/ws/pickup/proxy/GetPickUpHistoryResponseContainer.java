
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Service Contract Class - GetPickUpHistoryResponseContainer
 * 
 * <p>Java class for GetPickUpHistoryResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetPickUpHistoryResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="PickUpDetailList" type="{http://purolator.com/pws/datatypes/v1}ArrayOfPickUpDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPickUpHistoryResponseContainer", propOrder = {
    "pickUpDetailList"
})
public class GetPickUpHistoryResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "PickUpDetailList", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPickUpDetail> pickUpDetailList;

    /**
     * Gets the value of the pickUpDetailList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPickUpDetail }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPickUpDetail> getPickUpDetailList() {
        return pickUpDetailList;
    }

    /**
     * Sets the value of the pickUpDetailList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPickUpDetail }{@code >}
     *     
     */
    public void setPickUpDetailList(JAXBElement<ArrayOfPickUpDetail> value) {
        this.pickUpDetailList = value;
    }

}
