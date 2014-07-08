
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PreAlertNotificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PreAlertNotificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EMailMessage" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PreAlertEMailMessageType" minOccurs="0"/>
 *         &lt;element name="VoiceMessage" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PreAlertVoiceMessageType" minOccurs="0"/>
 *         &lt;element name="TextMessage" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}PreAlertTextMessageType" minOccurs="0"/>
 *         &lt;element name="Locale" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}LocaleType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreAlertNotificationType", propOrder = {
    "eMailMessage",
    "voiceMessage",
    "textMessage",
    "locale"
})
public class PreAlertNotificationType {

    @XmlElement(name = "EMailMessage")
    protected PreAlertEMailMessageType eMailMessage;
    @XmlElement(name = "VoiceMessage")
    protected PreAlertVoiceMessageType voiceMessage;
    @XmlElement(name = "TextMessage")
    protected PreAlertTextMessageType textMessage;
    @XmlElement(name = "Locale", required = true)
    protected LocaleType locale;

    /**
     * Gets the value of the eMailMessage property.
     * 
     * @return
     *     possible object is
     *     {@link PreAlertEMailMessageType }
     *     
     */
    public PreAlertEMailMessageType getEMailMessage() {
        return eMailMessage;
    }

    /**
     * Sets the value of the eMailMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PreAlertEMailMessageType }
     *     
     */
    public void setEMailMessage(PreAlertEMailMessageType value) {
        this.eMailMessage = value;
    }

    /**
     * Gets the value of the voiceMessage property.
     * 
     * @return
     *     possible object is
     *     {@link PreAlertVoiceMessageType }
     *     
     */
    public PreAlertVoiceMessageType getVoiceMessage() {
        return voiceMessage;
    }

    /**
     * Sets the value of the voiceMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PreAlertVoiceMessageType }
     *     
     */
    public void setVoiceMessage(PreAlertVoiceMessageType value) {
        this.voiceMessage = value;
    }

    /**
     * Gets the value of the textMessage property.
     * 
     * @return
     *     possible object is
     *     {@link PreAlertTextMessageType }
     *     
     */
    public PreAlertTextMessageType getTextMessage() {
        return textMessage;
    }

    /**
     * Sets the value of the textMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PreAlertTextMessageType }
     *     
     */
    public void setTextMessage(PreAlertTextMessageType value) {
        this.textMessage = value;
    }

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link LocaleType }
     *     
     */
    public LocaleType getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocaleType }
     *     
     */
    public void setLocale(LocaleType value) {
        this.locale = value;
    }

}
