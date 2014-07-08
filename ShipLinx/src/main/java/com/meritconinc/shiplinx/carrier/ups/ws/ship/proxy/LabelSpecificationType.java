
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabelSpecificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabelSpecificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LabelImageFormat" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}LabelImageFormatType"/>
 *         &lt;element name="HTTPUserAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LabelStockSize" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}LabelStockSizeType" minOccurs="0"/>
 *         &lt;element name="Instruction" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}InstructionCodeDescriptionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelSpecificationType", propOrder = {
    "labelImageFormat",
    "httpUserAgent",
    "labelStockSize",
    "instruction"
})
public class LabelSpecificationType {

    @XmlElement(name = "LabelImageFormat", required = true)
    protected LabelImageFormatType labelImageFormat;
    @XmlElement(name = "HTTPUserAgent")
    protected String httpUserAgent;
    @XmlElement(name = "LabelStockSize")
    protected LabelStockSizeType labelStockSize;
    @XmlElement(name = "Instruction")
    protected List<InstructionCodeDescriptionType> instruction;

    /**
     * Gets the value of the labelImageFormat property.
     * 
     * @return
     *     possible object is
     *     {@link LabelImageFormatType }
     *     
     */
    public LabelImageFormatType getLabelImageFormat() {
        return labelImageFormat;
    }

    /**
     * Sets the value of the labelImageFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelImageFormatType }
     *     
     */
    public void setLabelImageFormat(LabelImageFormatType value) {
        this.labelImageFormat = value;
    }

    /**
     * Gets the value of the httpUserAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHTTPUserAgent() {
        return httpUserAgent;
    }

    /**
     * Sets the value of the httpUserAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHTTPUserAgent(String value) {
        this.httpUserAgent = value;
    }

    /**
     * Gets the value of the labelStockSize property.
     * 
     * @return
     *     possible object is
     *     {@link LabelStockSizeType }
     *     
     */
    public LabelStockSizeType getLabelStockSize() {
        return labelStockSize;
    }

    /**
     * Sets the value of the labelStockSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelStockSizeType }
     *     
     */
    public void setLabelStockSize(LabelStockSizeType value) {
        this.labelStockSize = value;
    }

    /**
     * Gets the value of the instruction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instruction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstruction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstructionCodeDescriptionType }
     * 
     * 
     */
    public List<InstructionCodeDescriptionType> getInstruction() {
        if (instruction == null) {
            instruction = new ArrayList<InstructionCodeDescriptionType>();
        }
        return this.instruction;
    }

}
