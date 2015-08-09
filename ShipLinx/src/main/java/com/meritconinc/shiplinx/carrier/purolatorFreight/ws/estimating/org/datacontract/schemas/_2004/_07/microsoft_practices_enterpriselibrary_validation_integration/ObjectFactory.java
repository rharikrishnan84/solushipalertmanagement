package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.org.datacontract.schemas._2004._07.microsoft_practices_enterpriselibrary_validation_integration;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.datacontract.schemas._2004._07.microsoft_practices_enterpriselibrary_validation_integration package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups. Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

 private final static QName _ArrayOfValidationDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "ArrayOfValidationDetail");
 private final static QName _ValidationDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "ValidationDetail");
 private final static QName _ValidationDetailKey_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "Key");
 private final static QName _ValidationDetailMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "Message");
 private final static QName _ValidationDetailTag_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "Tag");

 /**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.microsoft_practices_enterpriselibrary_validation_integration
 *
 */
 public ObjectFactory() {
 }

 /**
 * Create an instance of {@link ArrayOfValidationDetail }
 *
 */
 public ArrayOfValidationDetail createArrayOfValidationDetail() {
 return new ArrayOfValidationDetail();
 }

 /**
 * Create an instance of {@link ValidationDetail }
 *
 */
 public ValidationDetail createValidationDetail() {
 return new ValidationDetail();
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfValidationDetail }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "ArrayOfValidationDetail")
 public JAXBElement<ArrayOfValidationDetail> createArrayOfValidationDetail(ArrayOfValidationDetail value) {
 return new JAXBElement<ArrayOfValidationDetail>(_ArrayOfValidationDetail_QNAME, ArrayOfValidationDetail.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ValidationDetail }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "ValidationDetail")
 public JAXBElement<ValidationDetail> createValidationDetail(ValidationDetail value) {
 return new JAXBElement<ValidationDetail>(_ValidationDetail_QNAME, ValidationDetail.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "Key", scope = ValidationDetail.class)
 public JAXBElement<String> createValidationDetailKey(String value) {
 return new JAXBElement<String>(_ValidationDetailKey_QNAME, String.class, ValidationDetail.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "Message", scope = ValidationDetail.class)
 public JAXBElement<String> createValidationDetailMessage(String value) {
 return new JAXBElement<String>(_ValidationDetailMessage_QNAME, String.class, ValidationDetail.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "Tag", scope = ValidationDetail.class)
 public JAXBElement<String> createValidationDetailTag(String value) {
 return new JAXBElement<String>(_ValidationDetailTag_QNAME, String.class, ValidationDetail.class, value);
 }

}