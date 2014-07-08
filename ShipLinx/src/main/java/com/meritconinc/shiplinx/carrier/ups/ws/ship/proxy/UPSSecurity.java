
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UsernameToken">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ServiceAccessToken">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AccessLicenseNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usernameToken",
    "serviceAccessToken"
})
@XmlRootElement(name = "UPSSecurity", namespace = "http://www.ups.com/XMLSchema/XOLTWS/UPSS/v1.0")
public class UPSSecurity {

    @XmlElement(name = "UsernameToken", namespace = "http://www.ups.com/XMLSchema/XOLTWS/UPSS/v1.0", required = true)
    protected UPSSecurity.UsernameToken usernameToken;
    @XmlElement(name = "ServiceAccessToken", namespace = "http://www.ups.com/XMLSchema/XOLTWS/UPSS/v1.0", required = true)
    protected UPSSecurity.ServiceAccessToken serviceAccessToken;

    /**
     * Gets the value of the usernameToken property.
     * 
     * @return
     *     possible object is
     *     {@link UPSSecurity.UsernameToken }
     *     
     */
    public UPSSecurity.UsernameToken getUsernameToken() {
        return usernameToken;
    }

    /**
     * Sets the value of the usernameToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSSecurity.UsernameToken }
     *     
     */
    public void setUsernameToken(UPSSecurity.UsernameToken value) {
        this.usernameToken = value;
    }

    /**
     * Gets the value of the serviceAccessToken property.
     * 
     * @return
     *     possible object is
     *     {@link UPSSecurity.ServiceAccessToken }
     *     
     */
    public UPSSecurity.ServiceAccessToken getServiceAccessToken() {
        return serviceAccessToken;
    }

    /**
     * Sets the value of the serviceAccessToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSSecurity.ServiceAccessToken }
     *     
     */
    public void setServiceAccessToken(UPSSecurity.ServiceAccessToken value) {
        this.serviceAccessToken = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="AccessLicenseNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "accessLicenseNumber"
    })
    public static class ServiceAccessToken {

        @XmlElement(name = "AccessLicenseNumber", namespace = "http://www.ups.com/XMLSchema/XOLTWS/UPSS/v1.0", required = true)
        protected String accessLicenseNumber;

        /**
         * Gets the value of the accessLicenseNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccessLicenseNumber() {
            return accessLicenseNumber;
        }

        /**
         * Sets the value of the accessLicenseNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccessLicenseNumber(String value) {
            this.accessLicenseNumber = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "username",
        "password"
    })
    public static class UsernameToken {

        @XmlElement(name = "Username", namespace = "http://www.ups.com/XMLSchema/XOLTWS/UPSS/v1.0", required = true)
        protected String username;
        @XmlElement(name = "Password", namespace = "http://www.ups.com/XMLSchema/XOLTWS/UPSS/v1.0", required = true)
        protected String password;

        /**
         * Gets the value of the username property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUsername() {
            return username;
        }

        /**
         * Sets the value of the username property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUsername(String value) {
            this.username = value;
        }

        /**
         * Gets the value of the password property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPassword() {
            return password;
        }

        /**
         * Sets the value of the password property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPassword(String value) {
            this.password = value;
        }

    }

}
