
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-07-13T18:43:59.892-04:00
 * Generated source version: 2.5.2
 * 
 */

@javax.jws.WebService(
                      serviceName = "PickUpService",
                      portName = "PickUpServiceEndpoint",
                      targetNamespace = "http://purolator.com/pws/service/v1",
                      wsdlLocation = "PickUpService.wsdl",
                      endpointInterface = "com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContract")
                      
public class PickUpServiceContractImpl implements PickUpServiceContract {

    private static final Logger LOG = Logger.getLogger(PickUpServiceContractImpl.class.getName());

    /* (non-Javadoc)
     * @see com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContract#getPickUpHistory(com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.GetPickUpHistoryRequestContainer  getPickUpHistoryRequest )*
     */
    public com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.GetPickUpHistoryResponseContainer getPickUpHistory(GetPickUpHistoryRequestContainer getPickUpHistoryRequest) throws PickUpServiceContractGetPickUpHistoryValidationFaultFaultFaultMessage    { 
        LOG.info("Executing operation getPickUpHistory");
        System.out.println(getPickUpHistoryRequest);
        try {
            com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.GetPickUpHistoryResponseContainer _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PickUpServiceContractGetPickUpHistoryValidationFaultFaultFaultMessage("PickUpServiceContract_GetPickUpHistory_ValidationFaultFault_FaultMessage...");
    }

    /* (non-Javadoc)
     * @see com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContract#modifyPickUp(com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ModifyPickUpRequestContainer  modifyPickUpRequest )*
     */
    public com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ModifyPickUpResponseContainer modifyPickUp(ModifyPickUpRequestContainer modifyPickUpRequest) throws PickUpServiceContractModifyPickUpValidationFaultFaultFaultMessage    { 
        LOG.info("Executing operation modifyPickUp");
        System.out.println(modifyPickUpRequest);
        try {
            com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ModifyPickUpResponseContainer _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PickUpServiceContractModifyPickUpValidationFaultFaultFaultMessage("PickUpServiceContract_ModifyPickUp_ValidationFaultFault_FaultMessage...");
    }

    /* (non-Javadoc)
     * @see com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContract#voidPickUp(com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.VoidPickUpRequestContainer  voidPickUpRequest )*
     */
    public com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.VoidPickUpResponseContainer voidPickUp(VoidPickUpRequestContainer voidPickUpRequest) throws PickUpServiceContractVoidPickUpValidationFaultFaultFaultMessage    { 
        LOG.info("Executing operation voidPickUp");
        System.out.println(voidPickUpRequest);
        try {
            com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.VoidPickUpResponseContainer _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PickUpServiceContractVoidPickUpValidationFaultFaultFaultMessage("PickUpServiceContract_VoidPickUp_ValidationFaultFault_FaultMessage...");
    }

    /* (non-Javadoc)
     * @see com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContract#validatePickUp(com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ValidatePickUpRequestContainer  validatePickUpRequest )*
     */
    public com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ValidatePickUpResponseContainer validatePickUp(ValidatePickUpRequestContainer validatePickUpRequest) throws PickUpServiceContractValidatePickUpValidationFaultFaultFaultMessage    { 
        LOG.info("Executing operation validatePickUp");
        System.out.println(validatePickUpRequest);
        try {
            com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ValidatePickUpResponseContainer _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PickUpServiceContractValidatePickUpValidationFaultFaultFaultMessage("PickUpServiceContract_ValidatePickUp_ValidationFaultFault_FaultMessage...");
    }

    /* (non-Javadoc)
     * @see com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContract#schedulePickUp(com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.SchedulePickUpRequestContainer  schedulePickUpRequest )*
     */
    public com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.SchedulePickUpResponseContainer schedulePickUp(SchedulePickUpRequestContainer schedulePickUpRequest) throws PickUpServiceContractSchedulePickUpValidationFaultFaultFaultMessage    { 
        LOG.info("Executing operation schedulePickUp");
        System.out.println(schedulePickUpRequest);
        try {
            com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.SchedulePickUpResponseContainer _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PickUpServiceContractSchedulePickUpValidationFaultFaultFaultMessage("PickUpServiceContract_SchedulePickUp_ValidationFaultFault_FaultMessage...");
    }

}
