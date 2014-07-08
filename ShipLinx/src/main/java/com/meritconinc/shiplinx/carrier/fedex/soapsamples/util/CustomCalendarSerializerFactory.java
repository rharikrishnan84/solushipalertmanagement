package com.meritconinc.shiplinx.carrier.fedex.soapsamples.util;

import javax.xml.namespace.QName;
import org.apache.axis.encoding.ser.*;

public class CustomCalendarSerializerFactory extends BaseSerializerFactory {
    public CustomCalendarSerializerFactory(Class javaType, QName xmlType) {
        super(com.meritconinc.shiplinx.carrier.fedex.soapsamples.util.CustomCalendarSerializer.class, xmlType, javaType);
    }
}
