package com.meritconinc.shiplinx.ccprocessing;

import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Customer;


public interface ICreditCardProcessor {

	public CCResponseObject authorizeCard(CCRequestObject ccRequest, long pin);
	public CCResponseObject processPostAuth(CCRequestObject ccRequest);
	public CCResponseObject voidCharge(CCRequestObject ccRequest);
	public CCResponseObject refundCharge(CCRequestObject ccRequest);
	public CCResponseObject processCharge(CCRequestObject request, long pin);
	public boolean addCard(CreditCard creditCard, Customer customer);
	public boolean updateCard(CreditCard creditCard, Customer customer);
	public boolean verifyCard(CCRequestObject request, long pin);
} 
