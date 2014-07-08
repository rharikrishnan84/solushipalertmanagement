
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * GetPickUpHistoryRequestContainer
 * 
 * <p>Java class for GetPickUpHistoryRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetPickUpHistoryRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="PickUpHistorySearchCriteria" type="{http://purolator.com/pws/datatypes/v1}PickUpHistorySearchCriteria"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPickUpHistoryRequestContainer", propOrder = {
    "pickUpHistorySearchCriteria"
})
public class GetPickUpHistoryRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "PickUpHistorySearchCriteria", required = true, nillable = true)
    protected PickUpHistorySearchCriteria pickUpHistorySearchCriteria;

    /**
     * Gets the value of the pickUpHistorySearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link PickUpHistorySearchCriteria }
     *     
     */
    public PickUpHistorySearchCriteria getPickUpHistorySearchCriteria() {
        return pickUpHistorySearchCriteria;
    }

    /**
     * Sets the value of the pickUpHistorySearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickUpHistorySearchCriteria }
     *     
     */
    public void setPickUpHistorySearchCriteria(PickUpHistorySearchCriteria value) {
        this.pickUpHistorySearchCriteria = value;
    }

}
