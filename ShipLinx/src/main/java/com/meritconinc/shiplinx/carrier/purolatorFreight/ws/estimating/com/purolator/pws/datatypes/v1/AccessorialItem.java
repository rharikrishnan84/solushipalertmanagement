	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;
	
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccessorialItem complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AccessorialItem">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * &lt;element name="Charge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessorialItem", propOrder = {
 "code",
 "description",
 "charge"
})
public class AccessorialItem {

 @XmlElement(name = "Code", required = true, nillable = true)
 protected String code;
 @XmlElement(name = "Description", required = true, nillable = true)
 protected String description;
 @XmlElement(name = "Charge", required = true)
 protected BigDecimal charge;

 /**
 * Gets the value of the code property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getCode() {
 return code;
 }

 /**
 * Sets the value of the code property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setCode(String value) {
 this.code = value;
 }

 /**
 * Gets the value of the description property.
 *
 * @return
 * possible object is
 * {@link String }
 *
 */
 public String getDescription() {
 return description;
 }

 /**
 * Sets the value of the description property.
 *
 * @param value
 * allowed object is
 * {@link String }
 *
 */
 public void setDescription(String value) {
 this.description = value;
 }

 /**
 * Gets the value of the charge property.
 *
 * @return
 * possible object is
 * {@link BigDecimal }
 *
 */
 public BigDecimal getCharge() {
 return charge;
 }

 /**
 * Sets the value of the charge property.
 *
 * @param value
 * allowed object is
 * {@link BigDecimal }
 *
 */
 public void setCharge(BigDecimal value) {
 this.charge = value;
 }

}
