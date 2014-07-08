package com.meritconinc.shiplinx.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.dao.PinDAO;
import com.meritconinc.shiplinx.exception.PinBlockException;
import com.meritconinc.shiplinx.exception.PinRollOverException;
import com.meritconinc.shiplinx.model.PinNumberBlock;
import com.meritconinc.shiplinx.service.PinBlockManager;

public class PinBlockManagerImpl implements PinBlockManager {

	private static Logger logger = Logger.getLogger(PinBlockManagerImpl.class);
	
	private PinDAO pinDAO;
	private String basePath = null;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
 
	public void setPinDAO(PinDAO dao) {
		this.pinDAO = dao;
	}


	public synchronized long[] getNewPinNumbers(String type, int count, long businessId){
			long[] pins = new long[count];
			
			logger.debug("Thread Id is : " + Thread.currentThread().getId());
			PinNumberBlock block = pinDAO.getBlock(type, businessId);	
			long nextPin;
			try{
				nextPin = block.getNextPin();
			}
			catch(NullPointerException n){
				logger.error("Could not find block for pin type " + type + " in pins table of db!!", n);
//				n.printStackTrace();
				throw new PinBlockException(n.getMessage());
			}
			
			
			for(int i=0; i<count; i++){
				pins[i] = nextPin;
				nextPin++;
				if(nextPin > block.getToPin()){
					logger.error("PIN BLOCK OVER USE ERROR!! " + type);
					//send error message to someone, email?
				}
			}
			block.setNextPin(nextPin);
			pinDAO.saveBlock(block);
			block = pinDAO.getBlock(type, businessId);		
			logger.debug("NEXT PIN " + block.getNextPin());
			
			return pins;
	}

	public synchronized String[] getNewPrefixedPinNumbers(String type, int count, long businessId){
		String[] pins = new String[count];
		
		logger.debug("Thread Id is : " + Thread.currentThread().getId());
		PinNumberBlock block = pinDAO.getBlock(type, businessId);		
		if(block==null)
			return null;
		
		long nextPin;
		String prefix = block.getPrefix();
		try{
			nextPin = block.getNextPin();
		}
		catch(NullPointerException n){
			logger.error("Could not find block for pin type " + type + " in pins table of db!!", n);
//			n.printStackTrace();
			throw new PinBlockException(n.getMessage());
		}
		
		
		for(int i=0; i<count; i++){
			pins[i] = prefix + String.valueOf(nextPin);
			nextPin++;
			if(nextPin > block.getToPin()){
				logger.error("PIN BLOCK OVER USE ERROR!! " + type);
				//send error message to someone, email?
			}
		}
		block.setNextPin(nextPin);
		pinDAO.saveBlock(block);
		block = pinDAO.getBlock(type, businessId);		
		logger.debug("NEXT PIN " + block.getNextPin());
		
		return pins;
}
	
	private void rollToNewBlock(String type, long business_id) throws PinRollOverException{
		try{
			BufferedReader in = new BufferedReader(new FileReader(new File(basePath,"pinnumbers.txt")));
			String s;
			while((s=in.readLine())!=null){
				System.out.println(s);
				if(s.startsWith(type)){
					
					StringTokenizer stk = new StringTokenizer(s, " ");
					stk.nextToken(); //this is the type
					String fromPin = stk.nextToken();
					String toPin = stk.nextToken();
					PinNumberBlock pinBlock = pinDAO.getBlock(type, business_id);
					pinBlock.setFromPin(new Long(fromPin).longValue());
					pinBlock.setToPin(new Long(toPin).longValue());
					pinBlock.setNextPin(pinBlock.getFromPin());
					System.out.println(pinBlock.getFromPin() + " " + pinBlock.getToPin() + " " + pinBlock.getNextPin());
					pinDAO.saveBlock(pinBlock);
				}
				else
					continue;
			}
		}
		catch(Exception e){
			throw new PinRollOverException(e.getMessage());
		}		
	}

	public String getPropertyValue(String propertyName){
		return pinDAO.getPropertyValue(propertyName);
	}

	
	

}
