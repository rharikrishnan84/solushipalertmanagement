package com.meritconinc.shiplinx.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.exception.CardProcessingException;
import com.meritconinc.mmr.exception.CustomerNameAlreadyTakenException;
import com.meritconinc.mmr.exception.UsernameAlreadyTakenException;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.mail.MailHelper;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.InvoiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Billduty;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.CreditUsageReport;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.CustomerSalesUser;
import com.meritconinc.shiplinx.model.CustomerTier;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import com.meritconinc.shiplinx.model.UserFilter;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.model.CustomerBusiness;
import com.meritconinc.shiplinx.model.Partner;
import com.soluship.businessfilter.util.BusinessFilterUtil;
public class CustomerManagerImpl implements CustomerManager {

	private static final Logger log = LogManager
			.getLogger(CustomerManagerImpl.class);
	CustomerDAO customerDAO;
	AddressDAO addressDAO;
	UserDAO siteUserDAO;
	BusinessDAO businessDAO;
	private InvoiceDAO invoiceDAO;
	private ShippingDAO shippingDAO;
	private CarrierServiceDAO carrierServiceDAO;
	protected MarkupManager markupManagerService;
	private PinBlockManager pinBlockManager;
	private CreditCardTransactionManager creditCardService;
 
	public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
		this.carrierServiceDAO = carrierServiceDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setAddressDAO(AddressDAO addressBookDAO) {
		this.addressDAO = addressBookDAO;
	}

	public void setSiteUserDAO(UserDAO siteUserDAO) {
		this.siteUserDAO = siteUserDAO;
	}

	/**
	 * @param businessDAO
	 *            the businessDAO to set
	 */
	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public void setMarkupManagerService(MarkupManager markupManagerService) {
		this.markupManagerService = markupManagerService;
	}

	public void setPinBlockManager(PinBlockManager pinBlockManager) {
		this.pinBlockManager = pinBlockManager;
	}

	public void setCreditCardService(
			CreditCardTransactionManager creditCardService) {
		this.creditCardService = creditCardService;
	}

	public void setInvoiceDAO(InvoiceDAO invoiceDAO) {
		this.invoiceDAO = invoiceDAO;
	}

	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}

	public void add(Customer customer, CreditCard creditCard) throws Exception {

		CCTransaction ccTransaction = null;
		try {
			if (customerDAO.isCustomerRegistered(customer.getName(),
					customer.getBusinessId(), customer.getId())) {
				throw new CustomerNameAlreadyTakenException(customer.getName());
			}
			if (siteUserDAO.isUsernameRegistered(customer.getUsername())) {
				throw new UsernameAlreadyTakenException(customer.getUsername());
			}

			Business business = businessDAO.getBusiessById(customer
					.getBusinessId());

			if (creditCard != null && creditCard.getCcExpiryMonth() != null
					&& creditCard.getCcExpiryYear() != null) {
				// ccTransaction = creditCardService.authorizeCard(1,
				// creditCard, customer.getBusinessId(),
				// business.getDefaultCurreny());
				// if(ccTransaction.getStatus() ==
				// CCTransaction.DECLINED_STATUS)
				// throw new
				// CardProcessingException(ccTransaction.getProcMessage());
				// card was authorized
				customer.setPaymentType(ShiplinxConstants.PAYMENT_TYPE_CREDIT_CARD);

				if (customer.getPaymentTypeLevel() == 0)
					customer.setPaymentTypeLevel(business
							.getDefaultPaymentTypeLevel());
			}

			if (customer.getAddress() != null) {
				customer.getAddress().setDefaultFromAddress(true);
				customer.getAddress().setAbbreviationName(customer.getName());
				log.debug("XXXXXXXXXXXXXXX");
				log.debug(customer.getAddress().getProvinceCode());
				Long id = this.addressDAO.add(customer.getAddress());
				customer.setAddressId(id);
			}

			// try to auto generate the customer's account number. If account #
			// has not been manually entered and there is a pin roll in the pins
			// table for this business.
			if (customer.getAccountNumber() == null
					|| customer.getAccountNumber().length() == 0) {
				String[] pins = this.pinBlockManager.getNewPrefixedPinNumbers(
						ShiplinxConstants.PIN_TYPE_CUSTOMER_ACCOUNT_NUMBERS, 1,
						customer.getBusinessId());
				if (pins != null && pins.length > 0) {// if pins is null then it
														// means that pin has
														// not been defined for
														// customer account #
														// generation for this
														// business
					customer.setAccountNumber(pins[0]);
				}
			}

			// set the payment terms if not manually set
			if (customer.getPayableDays() == 0)
				customer.setPayableDays(business.getDefaultNetTerms());

			customer.setActive(true);
			long customerId = customerDAO.addCustomer(customer);
			customerDAO.addUser(customer, customerId);
			
						//Partner partner = (Partner) ActionContext.getContext().getSession().get(Constants.CURRENT_PAGE);
												Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
												Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
												Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
												Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
												BusinessFilterDAO businessFilterDAO = (BusinessFilterDAO) MmrBeanLocator.getInstance().findBean("businessFilterDAO");
												CustomerBusiness customerbus=new CustomerBusiness();
												UserFilter uf=businessFilterDAO.getUserFilterByUsername(UserUtil.getMmrUser().getUsername());
												//setting the user details
												if(businessId==null || businessId==0 ){
												businessId=UserUtil.getMmrUser().getBusinessId();
												}
												if(uf!=null){
													if((partnerId==null || partnerId==0) ){
														
														partnerId=uf.getPartnerId();
													}
													
													if(countryPartnerId==null || countryPartnerId==0){
														
														countryPartnerId=uf.getCountryPartnerId();
													}
													
													if(branchId==null || branchId==0){
														branchId=uf.getBranchId();
													}
												}
												
												customerbus.setUserName(UserUtil.getMmrUser().getUsername());
												customerbus.setCustomerId(customerId);
												if((businessId !=null && businessId !=0) &&(partnerId!=null && partnerId !=0)
														&& (countryPartnerId!=null && countryPartnerId!=0 )
														&& (branchId!=null && branchId !=0) ){
													//setting the customer from the branch level
													customerbus.setBusinessId(branchId);
													/* customerbus.setPartnerId(partnerId);
													customerbus.setCountryPartnerId(countryPartnerId);
													customerbus.setBranchId(branchId);*/
													customerbus.setBranchLevel(true);
													
												
												}else if((businessId !=null && businessId !=0) &&(partnerId!=null && partnerId !=0)
														&& (countryPartnerId!=null && countryPartnerId!=0 )){
													//setting the customer from the country level
													customerbus.setBusinessId(countryPartnerId);
													/*customerbus.setPartnerId(partnerId);
													customerbus.setCountryPartnerId(countryPartnerId);*/
													customerbus.setNationLevel(true);
												}else if((businessId !=null && businessId !=0) &&(partnerId!=null && partnerId !=0)){
													//setting the customer from the country level
													customerbus.setBusinessId(partnerId);
													/*customerbus.setPartnerId(partnerId);*/
													customerbus.setPartnerLevel(true);
												}else if((businessId !=null && businessId !=0)){
													//setting the customer from the busadmin level
													customerbus.setBusinessId(businessId);
													customerbus.setBusinessLevel(true);
												}else if(businessId==null && UserUtil.getMmrUser().isBusinessLevel()){
												customerbus.setBusinessId(UserUtil.getMmrUser().getBusinessId());
													customerbus.setBusinessLevel(true);
												}
												businessFilterDAO.addCustomerBusiness(customerbus);
			List<CustomerTier> lstCustomerTier = new ArrayList<CustomerTier>();
			lstCustomerTier = customerDAO.getCustomerByTier(customer);
			/*if (lstCustomerTier.size() > 0) { // If it returns a row matching
												// the Spend criteria.
				// Copy markups from source customer to target customer
				markupManagerService.copyCustomerMarkup(lstCustomerTier.get(0)
						.getCustomerId(), customerId, business.getId());				
				log.debug("Copied the Markup from the Customer:"
						+ lstCustomerTier.get(0).getCustomerId()
						+ " to the new Customer:" + customerId);
			} else
				// copy markups from default to this customer
				markupManagerService.copyCustomerMarkup(new Long(0),
						customerId, business.getId());
*/
			// put the customer id in the address
			customer.getAddress().setCustomerId(customerId);
			addressDAO.edit(customer.getAddress());

			// set up the carriers based on selection
			setUpCarriersForCustomer(customer, customerId);

			// if the user is a sales user, then associate the customer to the
			// sales agent
			User user = UserUtil.getMmrUser();
			User salesUser = null;
			if (user != null
					&& user.getUserRole().equals(ShiplinxConstants.ROLE_SALES))
				salesUser = user;
			if (salesUser == null && user == null) { // try to check in the
														// session for code or
														// in Cookie, could be
														// coming from
														// registration page
														// (user has to be null
														// if coming from reg
														// page)
				String code = (String) ActionContext.getContext().getSession()
						.get(ShiplinxConstants.USER_CODE);
				// if(StringUtil.isEmpty(code))
				// code = Common.getCookie(ShiplinxConstants.USER_CODE);

				if (!StringUtil.isEmpty(code)) { // found the code
					UserSearchCriteria criteria = new UserSearchCriteria();
					criteria.setBusinessId(business.getId());
					criteria.setRoleCode(ShiplinxConstants.ROLE_SALES);
					// criteria.setUserCode(code); RIZ HAD THIS - IT IS ERROR
					// the compile - JAY COOK
					criteria.setUserCode(code);
					criteria.setUsername(code);
					List<User> salesUsers = siteUserDAO.findUsers(criteria, 0,
							0);
					if (salesUsers != null && salesUsers.size() > 0)
						salesUser = salesUsers.get(0);
				}
			}

			if (salesUser != null) {
				CustomerSalesUser customerSalesUser = new CustomerSalesUser();
				/*customerSalesUser.setCommissionPercentage(salesUser
						.getCommissionPercentage());*/
				customerSalesUser.setCommisionPercentagePerCHB((salesUser
					      .getCommissionPercentageCHB()));
				customerSalesUser
						.setCommissionPercentagePerPalletService(salesUser
								.getCommissionPercentagePP());
				customerSalesUser
						.setCommissionPercentagePerSkidService(salesUser
								.getCommissionPercentagePS());
				customerSalesUser.setSalesAgent(salesUser.getUsername());
				customerSalesUser.setCustomerId(customer.getId());
				this.addCustomerSales(customerSalesUser);
			}else {
				String code = (String) ActionContext.getContext().getSession()
						.get(ShiplinxConstants.USER_CODE);
				if (code != null) {
					User salesuser = siteUserDAO.findUserByUserCode(code);
					if(salesuser!=null){
					CustomerSalesUser customerSalesUser = new CustomerSalesUser();
					/*customerSalesUser.setCommissionPercentage(salesuser
							.getCommissionPercentage());*/
					customerSalesUser.setCommisionPercentagePerCHB((salesuser
						      .getCommissionPercentageCHB()));
					customerSalesUser
							.setCommissionPercentagePerPalletService(salesuser
									.getCommissionPercentagePP());
					customerSalesUser
							.setCommissionPercentagePerSkidService(salesuser
									.getCommissionPercentagePS());
					customerSalesUser.setSalesAgent(salesuser.getUsername());
					customerSalesUser.setCustomerId(customer.getId());
					this.addCustomerSales(customerSalesUser);
					}
				}
			}

			// save credit card information
			if (creditCard != null && creditCard.getCcExpiryMonth() != null
					&& creditCard.getCcExpiryYear() != null) {
				long billingAddressId = addressDAO.add(creditCard
						.getBillingAddress());
				creditCard.setBillingAddressId(billingAddressId);
				creditCard.setCustomerId(customerId);
				creditCard.setActive(true);
				boolean added = creditCardService.addCreditCard(creditCard,
						customer);
				if (!added)
					throw new CardProcessingException("Card not added!");
			}
			log.debug("Online");
			log.debug(customer.isOnlineSignup());
			// send email to customer if this is an online signup
			if (customer.isOnlineSignup()) {
				log.debug("online");
				sendSignupEmailToCustomer(customer, business);
				
			}

		} catch (CustomerNameAlreadyTakenException e) {
			throw new CustomerNameAlreadyTakenException(customer.getUsername());
		} catch (UsernameAlreadyTakenException e) {
			throw new UsernameAlreadyTakenException(customer.getUsername());
		} catch (Exception e) {
			log.error("Unable to add customer!", e);
			throw e;
		}		
	}

	public List<Customer> search(Customer customer) {
		return customerDAO.search(customer);
	}

	public void edit(Customer customer, String customerOldName)
			throws Exception {

		try {
			if ((!customerOldName.equals(customer.getName()))
					&& customerDAO.isCustomerRegistered(customer.getName(),
							customer.getBusinessId(), customer.getId())) {
				throw new CustomerNameAlreadyTakenException(customer.getName());
			}

			customerDAO.edit(customer);
			addressDAO.edit(customer.getAddress());

			// add or edit credit card information
			// save credit card information
			CreditCard activeCard = customer.getCreditCardActive();
			CreditCard newCard = customer.getNewCreditCard();
			if (activeCard == null && newCard != null
					&& !StringUtil.isEmpty(newCard.getCcNumber())) { // there is
																		// no
																		// existing
																		// credit
																		// card
				long billingAddressId = addressDAO.add(newCard
						.getBillingAddress());
				newCard.setBillingAddressId(billingAddressId);
				newCard.setCustomerId(customer.getId());
				newCard.setActive(true);
				boolean added = creditCardService.addCreditCard(newCard,
						customer);
				if (!added)
					throw new CardProcessingException("Card not added!");
			}
			if (activeCard != null && newCard != null
					&& !StringUtil.isEmpty(newCard.getCcNumber())) { // there is
																		// an
																		// existing
																		// credit
																		// card,
																		// this
																		// is an
																		// update
				long billingAddressId = addressDAO.add(newCard
						.getBillingAddress());
				newCard.setBillingAddressId(billingAddressId);
				newCard.setCustomerId(customer.getId());
				newCard.setActive(true);
				boolean added = creditCardService.updateCreditCard(newCard,
						customer);
				if (!added)
					throw new CardProcessingException("Card not added!");
			}
		} catch (CustomerNameAlreadyTakenException e) {
			throw new CustomerNameAlreadyTakenException(customer.getUsername());
		} catch (CardProcessingException e) {
			throw new CardProcessingException();
		}
	}

	public Customer getCustomerInfoByCustomerId(long customerId)
			throws Exception {
		try {
			Customer customer = customerDAO.getCustomerInfoByCustomerId(
					customerId, UserUtil.getMmrUser().getBusinessId());
			return customer;
		} catch (Exception exception) {
			throw exception;
		}

	}

	public long getCustomerIdByUserName(String username) {
		long customerId = customerDAO.getCustomerIdByUserName(username);
		return customerId;
	}

	public void getCustomerInfoByCustomerIdAndCarrierId(Long customerId, long l) {
		// try{
		// Customer
		// customer=customerDAO.getCustomerInfoByCustomerIdAndCarrierId(customerId,l);
		// return customer;
		// }catch(Exception exception){
		// throw exception;
		// }

	}

	public CustomerCarrier getCustomerCarrier(long customerId) {
		log.debug("-----getCustomerCarrier-----00--");
		return customerDAO.getCustomerCarrier(customerId);
	}

	public void saveCustomerCarrier(CustomerCarrier customerCarrier) {

		String account1 = customerCarrier.getAccountNumber1();
		boolean account1Exists = false;
		String account2 = customerCarrier.getAccountNumber2();
		boolean account2Exists = false;

		if (account1 != null && account1.length() > 0) {
			// ensure that the carrier account has not already been registered
			account1Exists = customerDAO.isCarrierAccountRegistered(account1,
					customerCarrier.getCarrierId());
		}
		if (account1Exists)
			throw new ShiplinxException(account1);

		if (account2 != null && account2.length() > 0) {
			// ensure that the carrier account has not already been registered
			account2Exists = customerDAO.isCarrierAccountRegistered(account2,
					customerCarrier.getCarrierId());
		}
		if (account2Exists)
			throw new ShiplinxException(account2);

		customerDAO.saveCustomerCarrier(customerCarrier);
	}

	public CreditUsageReport getCreditUsageReport(long customerId, long busId) {

		CreditUsageReport cur = new CreditUsageReport();
		// first get all unpaid and partially paid invoices
		Invoice invoice = new Invoice();
		invoice.setCustomerId(customerId);
		// invoice.setBusinessId(UserUtil.getMmrUser().getBusinessId());
		invoice.setBusinessId(busId);

		List<Long> BusIds=new ArrayList<Long>();
		        BusIds.add(busId);
		        invoice.setBusinessIds(BusIds);
		invoice.setPaymentStatusList(new int[] { Invoice.INVOICE_STATUS_UNPAID,
				Invoice.INVOICE_STATUS_PARTIAL_PAID });

		List<Invoice> invoices = invoiceDAO.searchInvoices(invoice);
		cur.setUnpaidInvoices(invoices);
		double total = 0.0;
		if (invoices != null) {
			for (Invoice i : invoices)
				total = (FormattingUtil.add(total, i.getBalanceDue()
						.doubleValue()).doubleValue());
		}
		cur.setUnpaidInvoiceAmount(total);

		List<ShippingOrder> orders = shippingDAO
				.getLiveUnpaidShipments(customerId);
		cur.setLiveUnpaidShipments(orders);
		total = 0.0;
		if (orders != null) {
			for (ShippingOrder order : orders)
				total = (FormattingUtil
						.add(total, order.getTotalChargeQuoted()).doubleValue());
		}
		cur.setLiveUnpaidShipmentsAmount(total);

		return cur;
	}

	public CustomerCarrier getCustomerCarrierInfoById(long customerId,
			Long carrierAccountId) {
		return customerDAO.getCustomerCarrierInfoById(customerId,
				carrierAccountId);

	}

	public void deleteCustomerCarrier(String carrierAccountId) {
		customerDAO.deleteCustomerCarrier(carrierAccountId);
	}

	public void editCustomerCarrier(CustomerCarrier customerCarrier,
			Long customerCarrierId) {
		customerDAO.editCustomerCarrier(customerCarrier, customerCarrierId);
	}

	public void setOtherCarrierAccountAsFalse(long customerId, String country,
			long carrierId) {
		customerDAO.setDefaultCarrierAccount(customerId, country, carrierId);
	}

	public boolean sendCustomerMailNotification(long customerId, User user) {
		boolean boolret = true;
		List<String> bccAddresses = new ArrayList<String>();
		try {
			Customer customer = getCustomerInfoByCustomerId(customerId);
			UserSearchCriteria criteria = new UserSearchCriteria();
			if(customer!=null && UserUtil.getMmrUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SYSADMIN)){
								
									criteria.setBusinessId(customer.getBusinessId());
								
							}else{
			criteria.setBusinessId(UserUtil.getMmrUser().getBusinessId());
							}
			criteria.setCustomerId(Long.valueOf(customerId));
			criteria.setRoleCode(ShiplinxConstants.ROLE_CUSTOMER_ADMIN);

			List<User> users = siteUserDAO.findUserByCustomer(criteria);
			if (users == null || users.size() == 0) {
				// addActionError(getText("login.request.notes1"));
				// return INPUT;
			}

			User firstUserFound = users.get(0);

			String locale = user.getLocale();
			String subject = MessageUtil.getMessage(user.getBusiness()
					.getCustomerNotificationSubject());
			subject = new String(subject.replaceAll("%BUSINESSNAME", user
					.getBusiness().getName()));

		/*	String body = MessageUtil.getMessage(user.getBusiness()
					.getCustomerNotificationBody(), locale);*/
			String body=BusinessFilterUtil.getEmailBody(customer.getBusinessId(), ShiplinxConstants.MSGID_EMAIL_CUS_NOTIFY);
						if (body == null || body.length() == 0) {
							body = MessageUtil.getMessage(user.getBusiness()
									.getCustomerNotificationBody(), locale);
						}
			if (body == null || body.length() == 0) {
				log.error("Cannot find template to send customer notification for business "
						+ user.getBusiness().getName());
				return false;
			}

			body = new String(body.replaceAll("%USERNAME",
					firstUserFound.getUsername()));
			body = new String(body.replaceAll("%PASSWORD",
					resetPassword(firstUserFound)));
			body = new String(body.replaceAll("%CUSTOMERNAME", customer
					.getAddress().getContactName()));

			//bccAddresses.add(user.getBusiness().getAddress().getEmailAddress());
			String emailAddress = user.getBusiness().getAddress()
					.getEmailAddress();
			log.debug("Business Email Address " + emailAddress);
			if (emailAddress != null) {
				String emails[] = emailAddress.split(";");
				for (String email : emails) {
					bccAddresses.add(email);
				}
			}

			String fromAddress = bccAddresses.get(0);
			
			List<CustomerSalesUser> customerSalesUser=getCustomerSalesInformation(customerId);
			for (CustomerSalesUser salesUser : customerSalesUser) {
				String customerSalesUserMail = salesUser.getEmail();
				log.debug("Sales user Email");
				log.debug(customerSalesUserMail);
				if(customerSalesUserMail!=null){
				bccAddresses.add(customerSalesUserMail);
				log.debug("Addedd Sales Email");
				}
			}
			
			MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user
							.getBusiness().getSmtpUsername(), user
							.getBusiness().getSmtpPassword(), user
							.getBusiness().getName(), user.getBusiness()
							.getSmtpPort(), fromAddress, customer.getAddress()
							.getEmailAddress(), bccAddresses, subject, body,
							null, true);
		} catch (MessagingException e) {
			log.error("Error sending email: ", e);
			boolret = false;
		} catch (Exception e) {
			log.error("Error sending email: ", e);
			boolret = false;
		}

		return boolret;
	}

	public boolean sendAddCustomerMailNotification(Customer customer, User user) {
		boolean boolret = true;
		try {
			String locale = user.getLocale();
			String subject = MessageUtil.getMessage(user.getBusiness()
					.getAddCustomerNotificationSubject());
			String body = MessageUtil.getMessage(user.getBusiness()
					.getAddCustomerNotificationBody(), locale);
			if (body == null || body.length() == 0) {
				log.error("Cannot find template to send customer notification for business "
						+ user.getBusiness().getName());
				return false;
			}
            log.debug("Test");
            log.debug(customer.getName());
            log.debug(customer.getAccountNumber());
            log.debug(customer.getId());
			body = new String(body.replaceAll("%CUSTOMERNAME",
					customer.getName()));
			body = new String(body.replaceAll("%ACCOUNT",
					customer.getAccountNumber()));
			body = new String(body.replaceAll("%CONTACTNAME", customer
					.getAddress().getContactName()));
			body = new String(body.replaceAll("%ADDRESS1", customer
					.getAddress().getAddress1()));
			body = new String(body.replaceAll("%CITY", customer.getAddress()
					.getCity()));
			body = new String(body.replaceAll("%ZIP", customer.getAddress()
					.getPostalCode()));
			body = new String(body.replaceAll("%PROVINCE", customer
					.getAddress().getProvinceCode()));
			body = new String(body.replaceAll("%COUNTRY", customer.getAddress()
					.getCountryCode()));

			String emailAddress = user.getBusiness().getAddress()
					.getEmailAddress();
			String emails[] = emailAddress.split(";");
			
			MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user
					.getBusiness().getSmtpUsername(), user.getBusiness()
					.getSmtpPassword(), user.getBusiness().getName(), user
					.getBusiness().getSmtpPort(), emails[0], user.getBusiness().getAddress()
					.getEmailAddress(), null, subject, body, null, true);
		} catch (MessagingException e) {
			log.error("Error sending email: ", e);
			boolret = false;
		} catch (Exception e) {
			log.error("Error sending email: ", e);
			boolret = false;
		}

		return boolret;
	}

	public boolean sendSignupEmailToCustomer(Customer customer,
			Business business) {
		boolean boolret = true;
		List<String> bccAddresses = new ArrayList<String>();
		try {

			String subject = MessageUtil.getMessage(business
					.getAddCustomerNotificationSubject());

			/*String body = MessageUtil.getMessage(business
					.getAddCustomerNotificationBody());*/
			
			String body = null;
						
						/*if(customer.getUser() != null){
							 body = MessageUtil.getMessage("message.send.addcustomersalesrep.notification.body");
						}else{
						body = MessageUtil.getMessage(business
								.getAddCustomerNotificationBody());
						}*/

			body=BusinessFilterUtil.getEmailBody(business.getId(), ShiplinxConstants.MSGID_EMAIL_NEW_CUSTOMER);
			  
					   if (body == null || body.length() == 0) {
						   if(customer.getUser() != null){
									 body = MessageUtil.getMessage("message.send.addcustomersalesrep.notification.body");
								}else{
								body = MessageUtil.getMessage(business
										.getAddCustomerNotificationBody());
								}
							}
						
							if (body == null || body.length() == 0) {
								log.error("Cannot find template to send customer notification for business "
										+ business.getName());
								return false;
							}
						
			if (body == null || body.length() == 0) {
				log.error("Cannot find template to send customer notification for business "
						+ business.getName());
				return false;
			}

			body = new String(body.replaceAll("%CUSTOMERNAME",
					customer.getName()));
			body = new String(body.replaceAll("%ACCOUNT",
					customer.getAccountNumber()));
			body = new String(body.replaceAll("%CONTACTNAME",
					customer.getUsername()));
			if(customer.getUser() != null){
							body = new String(body.replaceAll("%SALESREP",
									(customer.getUser().getFirstName())+" "+customer.getUser().getLastName()));
							}
			body = new String(body.replaceAll("%ADDRESS1", customer
					.getAddress().getAddress1()));
			body = new String(body.replaceAll(" %CITY", customer.getAddress()
					.getCity()));
			body = new String(body.replaceAll("%ZIP", customer.getAddress()
					.getPostalCode()));
			body = new String(body.replaceAll("%PROVINCE", customer
					.getAddress().getProvinceCode()));
			body = new String(body.replaceAll("%COUNTRY", customer
					.getAddress().getCountryName()));

			bccAddresses.add(business.getAddress().getEmailAddress());

			MailHelper.sendEmailNow2(business.getSmtpHost(), business
					.getSmtpUsername(), business.getSmtpPassword(), business
					.getName(), business.getSmtpPort(), business.getAddress()
					.getEmailAddress(),business.getAddress().getEmailAddress(),
					bccAddresses, subject, body, null,
					true);
		} catch (MessagingException e) {
			log.error("Error sending email: ", e);
			boolret = false;
		} catch (Exception e) {
			log.error("Error sending email: ", e);
			boolret = false;
		}

		return boolret;
	}

	public String resetPassword(User user) {

		String newPassword = StringUtil.generateRandomString(8);
		siteUserDAO.changePassword(user.getUsername(), newPassword,
				Constants.SYSTEM_SCOPE, true);

		return newPassword;
	}

	public List<CustomerSalesUser> getCustomerSalesInfoByCustId(long customerId) {
		List<CustomerSalesUser> listCSU = new ArrayList<CustomerSalesUser>();
		CustomerSalesUser csu = new CustomerSalesUser();
		csu.setCustomerId(customerId);
		try {
			listCSU = customerDAO.getCustomerSalesUser(csu);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return listCSU;
	}

	public List<CustomerSalesUser> getCustomerSalesInformation(long customerId) {
		List<CustomerSalesUser> listCSU = new ArrayList<CustomerSalesUser>();
		CustomerSalesUser csu = new CustomerSalesUser();
		csu.setCustomerId(customerId);
		try {
			listCSU = customerDAO.getCustomerSalesInformation(customerId);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return listCSU;
	}
	
	public boolean addCustomerSales(CustomerSalesUser customerSalesUser) {
		long customerId = customerDAO.addCustomerSales(customerSalesUser);

		if (customerId > 0)
			return true;
		else
			return false;
	}

	public boolean deleteCustomerSales(CustomerSalesUser customerSalesUser) {
		boolean retval = true;
		try {
			customerDAO.deleteCustomerSalesUser(customerSalesUser);
		} catch (Exception e) {
			e.printStackTrace();
			retval = false;
		}

		return retval;
	}

	public boolean updateCustomerSales(CustomerSalesUser customerSalesUser) {
		boolean retval = true;
		try {
			customerDAO.updateCustomerSalesUser(customerSalesUser);
		} catch (Exception e) {
			e.printStackTrace();
			retval = false;
		}
		return retval;
	}

	private void setUpCarriersForCustomer(Customer customer, long customerId) {

		List<Carrier> carriersForBusiness = carrierServiceDAO
				.getCarriersForBusiness(customer.getBusinessId());
		if((carriersForBusiness==null || carriersForBusiness.size()==0) && customer!=null){
												   carriersForBusiness=BusinessFilterUtil.getCarriersForBusinessByBusinessLevel(customer.getBusinessId());
											}
		List<Carrier> carriersToEnable = customer.getCustomerSelectedCarriers();
		List<Carrier> carriersToDisable = new ArrayList<Carrier>();
		Business business = businessDAO
				.getBusiessById(customer.getBusinessId());

		for (Carrier carrier : carriersForBusiness) {
			boolean found = false;
			for (Carrier carrierToEnable : carriersToEnable) {
				if (carrier.getId().longValue() == carrierToEnable.getId()
						.longValue()) {
					found = true;

					// if a default record does not exist for this carrier, then
					// we need to add a customer specific record (for example
					// LTL services that are not available by default to
					// customers)
					CustomerCarrier customerCarrierAccount = null;
					try {
						customerCarrierAccount = carrierServiceDAO
								.getCarrierAccount(customerId,
										customer.getBusinessId(),
										carrierToEnable.getId(), null, null);
					} catch (Exception e) {

					}
					CustomerCarrier carrierAccount = null;
					try {
						carrierAccount = carrierServiceDAO.getCarrierAccount(
								new Long(0), customer.getBusinessId(),
								carrierToEnable.getId(), null, null);
					} catch (Exception e) {

					}
//					String code = (String) ActionContext.getContext().getSession().get(ShiplinxConstants.USER_CODE);
//					if (code != null && carrierAccount == null
//							&& customerCarrierAccount == null) {
//						carrierAccount = new CustomerCarrier();
//						carrierAccount.setBusinessId(customer.getBusinessId());
//						carrierAccount.setCustomerId(customer.getId());
//						carrierAccount.setLive(true);
//						carrierAccount.setDefaultAccount(true);
//						carrierAccount.setCountry(business.getAddress()
//								.getCountryCode());
//						carrierAccount.setCarrierId(carrierToEnable.getId());
//						customerDAO.saveCustomerCarrier(carrierAccount);
//					}
					
					boolean flag = false;
					String code = (String) ActionContext.getContext().getSession().get(ShiplinxConstants.USER_CODE);
					if(code==null){                     
						flag = true;                         //disabling the services for the direct new customer
					}
					// Enable all services for this carrier and customer
					markupManagerService
							.disableOrEnableAllServicesForCustomerAndCarrier(
									customer.getId(), carrier.getId(), flag);
					
					break;
				}
			}
			if (!found) {
				// Disable all services for this carrier and customer
				carriersToDisable.add(carrier);
				/*markupManagerService
						.disableOrEnableAllServicesForCustomerAndCarrier(
								customer.getId(), carrier.getId(), true);*/
			}
		}

	}

	public boolean isWarehouseCustomer(long customerId) {
		return customerDAO.isWarehouseCustomer(customerId);
	}
	public boolean sendSalesRepAddCustomerMailNotification(Customer customer, User user) {
						boolean boolret = true;
						try {
							String locale = user.getLocale();
							String subject = MessageUtil.getMessage(user.getBusiness()
									.getAddCustomerNotificationSubject());
							 /*String body = MessageUtil.getMessage("message.send.salesrep.addcustomer.notification.body", locale);*/
 														
														String  body=BusinessFilterUtil.getEmailBody(customer.getBusinessId(),ShiplinxConstants.MSGID_EMAIL_SALESREP_NEWCUS);
							
														if (body == null || body.length() == 0) {
														   body = MessageUtil.getMessage("message.send.salesrep.addcustomer.notification.body", locale);
														}
							
							if (body == null || body.length() == 0) {
								log.error("Cannot find template to send customer notification for business "
										+ user.getBusiness().getName());
								return false;
							}
				            
						body = new String(body.replaceAll("%CUSTOMERNAME",
									customer.getName()));
							body = new String(body.replaceAll("%ACCOUNT",
									customer.getAccountNumber()));
							body = new String(body.replaceAll("%CONTACTNAME", customer
									.getAddress().getContactName()));
							body = new String(body.replaceAll("%SALESREP",
									user.getFirstName()+" "+user.getLastName()));
							body = new String(body.replaceAll("%ADDRESS1", customer
									.getAddress().getAddress1()));
							body = new String(body.replaceAll("%CITY", customer.getAddress()
									.getCity()));
							body = new String(body.replaceAll("%ZIP", customer.getAddress()
									.getPostalCode()));
							body = new String(body.replaceAll("%PROVINCE", customer
									.getAddress().getProvinceCode()));
							body = new String(body.replaceAll("%COUNTRY", customer.getAddress()
									.getCountryCode()));
				            
							String emailAddress = user.getBusiness().getAddress()
									.getEmailAddress();
							String emails[] = emailAddress.split(";");
							
							MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user
									.getBusiness().getSmtpUsername(), user.getBusiness()
									.getSmtpPassword(), user.getBusiness().getName(), user
									.getBusiness().getSmtpPort(), emails[0], user.getBusiness().getAddress()
									.getEmailAddress(), null, subject, body, null, true);
						} catch (MessagingException e) {
							log.error("Error sending email: ", e);
							boolret = false;
						} catch (Exception e) {
							log.error("Error sending email: ", e);
							boolret = false;
						}
						return boolret;
					}

	@Override
	public List<Billduty> getBilldutyList(String locale) {
		return customerDAO.getBilldutyList(locale);
	}
	
	public void updateAvailableCredit(double availableCredit, long customerId){
				 customerDAO.updateAvailableCredit(availableCredit, customerId);
			}
			public double getAvailableCredit(long customerId){
				return customerDAO.getAvailableCredit(customerId);
			}
			
			public int findUnpaidInvoiceDuration(long customerId, int holdTerms) {
				return customerDAO.findUnpaidInvoiceDuration(customerId, holdTerms);
			}
			
			@Override
								public List<Customer> getAllCustomers() {
									// TODO Auto-generated method stub
									return customerDAO.getAllCustomers();
								}
							
								@Override
								public List<Customer> getAllCustomersByBusinessLevel(Long businessId) {
									// TODO Auto-generated method stub
									return customerDAO.getAllCustomersByBusinessLevel(businessId);
								}
							
								@Override
								public List<Customer> getAllCustomersByPartnerLevel(Long businessId,
										Long partnerId) {
									// TODO Auto-generated method stub
									
									return customerDAO.getAllCustomersByPartnerLevel(businessId,partnerId);
								}
							
								@Override
								public List<Customer> getAllCustomersByNationLevel(Long businessId,
										Long partnerId, Long countryPartnerId) {
									// TODO Auto-generated method stub
									return customerDAO.getAllCustomersByNationLevel(businessId,partnerId,countryPartnerId);
								}
							
								@Override
								public List<Customer> getAllCustomersByBranchLevel(Long businessId,
										Long partnerId, Long countryPartnerId, long branchId) {
								// TODO Auto-generated method stub
									return customerDAO.getAllCustomersByBranchLevel(businessId,partnerId,countryPartnerId,branchId);
								}
			
								 
								public List<Customer> getCustomersBySalesUser(String usename) {
									// TODO Auto-generated method stub
									return customerDAO.getCustomersBySalesUser(usename);
								}
	@Override
	public List<Customer> getAllCustomerForBusiness(long businessId) {
		return customerDAO.getAllCustomerForBusiness(businessId);
	}
}