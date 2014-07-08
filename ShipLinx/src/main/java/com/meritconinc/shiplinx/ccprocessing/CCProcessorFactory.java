package com.meritconinc.shiplinx.ccprocessing;

import com.meritconinc.shiplinx.ccprocessing.processor.AuthNetCIMProcessor;
import com.meritconinc.shiplinx.ccprocessing.processor.AuthNetProcessor;
import com.meritconinc.shiplinx.ccprocessing.processor.MonerisProcessor;
import com.meritconinc.shiplinx.model.MerchantAccount;



/**
 * @author rizwanm
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CCProcessorFactory
{

  private final static String PROCESSOR_MONERIS = "MONERIS";
  private final static String PROCESSOR_AUTHNET = "AUTHNET";
  private final static String PROCESSOR_AUTHNETCIM = "AUTHNETCIM";
 
 
  public static ICreditCardProcessor getProcessor(MerchantAccount ma)
  {
 
    if (ma.getProcessor().equalsIgnoreCase(PROCESSOR_MONERIS))
      return new MonerisProcessor(ma);
    if (ma.getProcessor().equalsIgnoreCase(PROCESSOR_AUTHNET))
        return new AuthNetProcessor(ma);
    if (ma.getProcessor().equalsIgnoreCase(PROCESSOR_AUTHNETCIM))
        return new AuthNetCIMProcessor(ma);

    return null;
  }

 
}