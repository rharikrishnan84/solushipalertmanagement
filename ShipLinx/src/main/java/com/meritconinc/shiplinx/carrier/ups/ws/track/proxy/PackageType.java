
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeliveryDetail" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DeliveryDetailType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PackageAddress" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}PackageAddressType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PackageServiceOption" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ServiceOptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="COD" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}CODType" minOccurs="0"/>
 *         &lt;element name="Activity" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ActivityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}MessageType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PackageWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}WeightType" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ReferenceNumberType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AlternateTrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AlternateTrackingInfo" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AlternateTrackingInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Document" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AdditionalAttribute" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AdditionalCodeDescriptionValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageType", propOrder = {
    "trackingNumber",
    "deliveryDetail",
    "packageAddress",
    "packageServiceOption",
    "cod",
    "activity",
    "message",
    "packageWeight",
    "referenceNumber",
    "alternateTrackingNumber",
    "alternateTrackingInfo",
    "document",
    "additionalAttribute"
})
public class PackageType {

    @XmlElement(name = "TrackingNumber")
    protected String trackingNumber;
    @XmlElement(name = "DeliveryDetail")
    protected List<DeliveryDetailType> deliveryDetail;
    @XmlElement(name = "PackageAddress")
    protected List<PackageAddressType> packageAddress;
    @XmlElement(name = "PackageServiceOption")
    protected List<ServiceOptionType> packageServiceOption;
    @XmlElement(name = "COD")
    protected CODType cod;
    @XmlElement(name = "Activity")
    protected List<ActivityType> activity;
    @XmlElement(name = "Message")
    protected List<MessageType> message;
    @XmlElement(name = "PackageWeight")
    protected WeightType packageWeight;
    @XmlElement(name = "ReferenceNumber")
    protected List<ReferenceNumberType> referenceNumber;
    @XmlElement(name = "AlternateTrackingNumber")
    protected List<String> alternateTrackingNumber;
    @XmlElement(name = "AlternateTrackingInfo")
    protected List<AlternateTrackingInfoType> alternateTrackingInfo;
    @XmlElement(name = "Document")
    protected List<DocumentType> document;
    @XmlElement(name = "AdditionalAttribute")
    protected List<AdditionalCodeDescriptionValueType> additionalAttribute;

    /**
     * Gets the value of the trackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Sets the value of the trackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNumber(String value) {
        this.trackingNumber = value;
    }

    /**
     * Gets the value of the deliveryDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryDetailType }
     * 
     * 
     */
    public List<DeliveryDetailType> getDeliveryDetail() {
        if (deliveryDetail == null) {
            deliveryDetail = new ArrayList<DeliveryDetailType>();
        }
        return this.deliveryDetail;
    }

    /**
     * Gets the value of the packageAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageAddressType }
     * 
     * 
     */
    public List<PackageAddressType> getPackageAddress() {
        if (packageAddress == null) {
            packageAddress = new ArrayList<PackageAddressType>();
        }
        return this.packageAddress;
    }

    /**
     * Gets the value of the packageServiceOption property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageServiceOption property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageServiceOption().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceOptionType }
     * 
     * 
     */
    public List<ServiceOptionType> getPackageServiceOption() {
        if (packageServiceOption == null) {
            packageServiceOption = new ArrayList<ServiceOptionType>();
        }
        return this.packageServiceOption;
    }

    /**
     * Gets the value of the cod property.
     * 
     * @return
     *     possible object is
     *     {@link CODType }
     *     
     */
    public CODType getCOD() {
        return cod;
    }

    /**
     * Sets the value of the cod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CODType }
     *     
     */
    public void setCOD(CODType value) {
        this.cod = value;
    }

    /**
     * Gets the value of the activity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActivityType }
     * 
     * 
     */
    public List<ActivityType> getActivity() {
        if (activity == null) {
            activity = new ArrayList<ActivityType>();
        }
        return this.activity;
    }

    /**
     * Gets the value of the message property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the message property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageType }
     * 
     * 
     */
    public List<MessageType> getMessage() {
        if (message == null) {
            message = new ArrayList<MessageType>();
        }
        return this.message;
    }

    /**
     * Gets the value of the packageWeight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightType }
     *     
     */
    public WeightType getPackageWeight() {
        return packageWeight;
    }

    /**
     * Sets the value of the packageWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightType }
     *     
     */
    public void setPackageWeight(WeightType value) {
        this.packageWeight = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceNumberType }
     * 
     * 
     */
    public List<ReferenceNumberType> getReferenceNumber() {
        if (referenceNumber == null) {
            referenceNumber = new ArrayList<ReferenceNumberType>();
        }
        return this.referenceNumber;
    }

    /**
     * Gets the value of the alternateTrackingNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternateTrackingNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternateTrackingNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAlternateTrackingNumber() {
        if (alternateTrackingNumber == null) {
            alternateTrackingNumber = new ArrayList<String>();
        }
        return this.alternateTrackingNumber;
    }

    /**
     * Gets the value of the alternateTrackingInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternateTrackingInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternateTrackingInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlternateTrackingInfoType }
     * 
     * 
     */
    public List<AlternateTrackingInfoType> getAlternateTrackingInfo() {
        if (alternateTrackingInfo == null) {
            alternateTrackingInfo = new ArrayList<AlternateTrackingInfoType>();
        }
        return this.alternateTrackingInfo;
    }

    /**
     * Gets the value of the document property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the document property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentType }
     * 
     * 
     */
    public List<DocumentType> getDocument() {
        if (document == null) {
            document = new ArrayList<DocumentType>();
        }
        return this.document;
    }

    /**
     * Gets the value of the additionalAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalCodeDescriptionValueType }
     * 
     * 
     */
    public List<AdditionalCodeDescriptionValueType> getAdditionalAttribute() {
        if (additionalAttribute == null) {
            additionalAttribute = new ArrayList<AdditionalCodeDescriptionValueType>();
        }
        return this.additionalAttribute;
    }

}
