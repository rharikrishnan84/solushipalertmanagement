
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * DutyInformation
 * 
 * <p>Java class for DutyInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DutyInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BillDutiesToParty" type="{http://purolator.com/pws/datatypes/v1}BillDutiesToParty"/>
 *         &lt;element name="BusinessRelationship" type="{http://purolator.com/pws/datatypes/v1}BusinessRelationship"/>
 *         &lt;element name="Currency" type="{http://purolator.com/pws/datatypes/v1}DutyCurrency"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DutyInformation", propOrder = {
    "billDutiesToParty",
    "businessRelationship",
    "currency"
})
public class DutyInformation {

    @XmlElement(name = "BillDutiesToParty", required = true, nillable = true)
    protected BillDutiesToParty billDutiesToParty;
    @XmlElement(name = "BusinessRelationship", required = true, nillable = true)
    protected BusinessRelationship businessRelationship;
    @XmlElement(name = "Currency", required = true, nillable = true)
    protected DutyCurrency currency;

    /**
     * Gets the value of the billDutiesToParty property.
     * 
     * @return
     *     possible object is
     *     {@link BillDutiesToParty }
     *     
     */
    public BillDutiesToParty getBillDutiesToParty() {
        return billDutiesToParty;
    }

    /**
     * Sets the value of the billDutiesToParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillDutiesToParty }
     *     
     */
    public void setBillDutiesToParty(BillDutiesToParty value) {
        this.billDutiesToParty = value;
    }

    /**
     * Gets the value of the businessRelationship property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessRelationship }
     *     
     */
    public BusinessRelationship getBusinessRelationship() {
        return businessRelationship;
    }

    /**
     * Sets the value of the businessRelationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessRelationship }
     *     
     */
    public void setBusinessRelationship(BusinessRelationship value) {
        this.businessRelationship = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link DutyCurrency }
     *     
     */
    public DutyCurrency getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link DutyCurrency }
     *     
     */
    public void setCurrency(DutyCurrency value) {
        this.currency = value;
    }

}
