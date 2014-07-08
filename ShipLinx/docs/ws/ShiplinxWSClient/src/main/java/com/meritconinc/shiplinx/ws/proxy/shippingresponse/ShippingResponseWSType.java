
package com.meritconinc.shiplinx.ws.proxy.shippingresponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.ErrorsWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.OrderWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType;


/**
 * <p>Java class for ShippingResponseWSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShippingResponseWSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Security" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}SecurityWSType"/>
 *         &lt;element name="IsSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Errors" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}ErrorsWSType" minOccurs="0"/>
 *         &lt;element name="Order" type="{http://www.proxy.ws.shiplinx.meritconinc.com/datatypes}OrderWSType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShippingResponseWSType", propOrder = {
    "security",
    "isSuccess",
    "errors",
    "order"
})
@XmlRootElement(name = "ShippingResponseWSType", namespace = "http://www.proxy.ws.shiplinx.meritconinc.com")
public class ShippingResponseWSType {

    @XmlElement(name = "Security", required = true)
    protected SecurityWSType security;
    @XmlElement(name = "IsSuccess")
    protected boolean isSuccess;
    @XmlElement(name = "Errors")
    protected ErrorsWSType errors;
    @XmlElement(name = "Order")
    protected OrderWSType order;

    /**
     * Gets the value of the security property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityWSType }
     *     
     */
    public SecurityWSType getSecurity() {
        return security;
    }

    /**
     * Sets the value of the security property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityWSType }
     *     
     */
    public void setSecurity(SecurityWSType value) {
        this.security = value;
    }

    /**
     * Gets the value of the isSuccess property.
     * 
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }

    /**
     * Sets the value of the isSuccess property.
     * 
     */
    public void setIsSuccess(boolean value) {
        this.isSuccess = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorsWSType }
     *     
     */
    public ErrorsWSType getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorsWSType }
     *     
     */
    public void setErrors(ErrorsWSType value) {
        this.errors = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link OrderWSType }
     *     
     */
    public OrderWSType getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderWSType }
     *     
     */
    public void setOrder(OrderWSType value) {
        this.order = value;
    }

}
