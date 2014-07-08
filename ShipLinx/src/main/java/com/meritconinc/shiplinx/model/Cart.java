package com.meritconinc.shiplinx.model;

import java.io.Serializable;

public class Cart implements Serializable, Cloneable {

	//Table customer_cart
	private Long id;
	private Long cartId;
	private Long customerId;
	private Long businessId;
	private String urlName;
	private String token;
	private boolean isLive;
	 
	//Table business_cart
	private String cartName;
	private String apiKey;
	private String sharedSecret;
	
	
	private Customer customer;
	
	//web purpose
	private Long carrierId;
	private Long serviceId;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the cartId
	 */
	public Long getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the businessId
	 */
	public Long getBusinessId() {
		return businessId;
	}

	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return the urlName
	 */
	public String getUrlName() {
		return urlName;
	}

	/**
	 * @param urlName the urlName to set
	 */
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the isLive
	 */
	public boolean isLive() {
		return isLive;
	}

	/**
	 * @param isLive the isLive to set
	 */
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the cartName
	 */
	public String getCartName() {
		return cartName;
	}

	/**
	 * @param cartName the cartName to set
	 */
	public void setCartName(String cartName) {
		this.cartName = cartName;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return the sharedSecret
	 */
	public String getSharedSecret() {
		return sharedSecret;
	}

	/**
	 * @param sharedSecret the sharedSecret to set
	 */
	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}

	/**
	 * @return the carrierId
	 */
	public Long getCarrierId() {
		return carrierId;
	}

	/**
	 * @param carrierId the carrierId to set
	 */
	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}

	/**
	 * @return the serviceId
	 */
	public Long getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
}
