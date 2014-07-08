
package com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RateInq }
     * 
     */
    public RateInq createRateInq() {
        return new RateInq();
    }

    /**
     * Create an instance of {@link ArrayOfPiece }
     * 
     */
    public ArrayOfPiece createArrayOfPiece() {
        return new ArrayOfPiece();
    }

    /**
     * Create an instance of {@link RateInqResponse }
     * 
     */
    public RateInqResponse createRateInqResponse() {
        return new RateInqResponse();
    }

    /**
     * Create an instance of {@link ReturnRates }
     * 
     */
    public ReturnRates createReturnRates() {
        return new ReturnRates();
    }

    /**
     * Create an instance of {@link Piece }
     * 
     */
    public Piece createPiece() {
        return new Piece();
    }

}
