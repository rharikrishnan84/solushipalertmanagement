package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.microsoft.practices.enterpriselibrary._2007._01.wcf.validation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.org.datacontract.schemas._2004._07.microsoft_practices_enterpriselibrary_validation_integration.ArrayOfValidationDetail;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.microsoft.practices.enterpriselibrary._2007._01.wcf.validation package.
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

 private final static QName _ValidationFault_QNAME = new QName("http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", "ValidationFault");
 private final static QName _ValidationFaultDetails_QNAME = new QName("http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", "Details");

 /**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.practices.enterpriselibrary._2007._01.wcf.validation
 *
 */
 public ObjectFactory() {
 }

 /**
 * Create an instance of {@link ValidationFault }
 *
 */
 public ValidationFault createValidationFault() {
 return new ValidationFault();
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ValidationFault }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", name = "ValidationFault")
 public JAXBElement<ValidationFault> createValidationFault(ValidationFault value) {
 return new JAXBElement<ValidationFault>(_ValidationFault_QNAME, ValidationFault.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfValidationDetail }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", name = "Details", scope = ValidationFault.class)
 public JAXBElement<ArrayOfValidationDetail> createValidationFaultDetails(ArrayOfValidationDetail value) {
 return new JAXBElement<ArrayOfValidationDetail>(_ValidationFaultDetails_QNAME, ArrayOfValidationDetail.class, ValidationFault.class, value);
 }

}