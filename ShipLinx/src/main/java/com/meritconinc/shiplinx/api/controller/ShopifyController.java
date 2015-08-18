package com.meritconinc.shiplinx.api.controller;

/**
 *@author selvaganesh
 * 10/07/2015
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.json.JSONException;
import org.apache.tools.ant.taskdefs.Sleep;
import org.json.JSONObject;
import org.syntax.jedit.InputHandler.next_char;

import com.a.a.a.g.m.s;
import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.model.admin.EcommerceLog;
import com.meritconinc.mmr.model.admin.EcommerceStore;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.action.ProductManagerAction;
import com.meritconinc.shiplinx.action.ShipmentAction;
import com.meritconinc.shiplinx.api.Util.ShopifyUtil;
import com.meritconinc.shiplinx.api.model.Destination;
import com.meritconinc.shiplinx.api.model.Item;
import com.meritconinc.shiplinx.api.model.Line_items;
import com.meritconinc.shiplinx.api.model.Origin;
import com.meritconinc.shiplinx.api.model.Shipping_address;
import com.meritconinc.shiplinx.api.model.Shop;
import com.meritconinc.shiplinx.api.model.ShopifyProduct;
import com.meritconinc.shiplinx.api.model.ShopifyRateRequest;
import com.meritconinc.shiplinx.api.model.ShopifyRateResponce;
import com.meritconinc.shiplinx.api.model.ShopifyShippingOrder;
import com.meritconinc.shiplinx.api.model.ShopifyShop;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.ProductManagerDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.PackageTypes;
import com.meritconinc.shiplinx.model.ProductPackageMap;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.ProductManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.google.gson.Gson;
import com.meritconinc.shiplinx.model.Charge;
 


public class ShopifyController implements Runnable {

	private static final Logger log = LogManager
			.getLogger(ShopifyController.class);

	AddressDAO addressDAO = (AddressDAO) MmrBeanLocator.getInstance().findBean(
			"addressDAO");
	ProductManagerDAO productManagerDAO = (ProductManagerDAO) MmrBeanLocator
			.getInstance().findBean("productManagerDAO");
	private String code;
	private String shop;
	private EcommerceStore ecomStore;
	private Service service;
	private Long ServiceId;
	private String zip;
	private String country;
	private ShopifyRateRequest shopifyRateRequest;
	private String city;
	private List<Package> shipPackes;
	private Long customerId;
	private List<Item> reqItems;
	private EcommerceLog ecomLog;
	private Long createShipmentWebhookId;
	private Long cancelShipmentWebhookId;
	private boolean packageMap;

	public ShopifyController(String shop, String code) {
		this.shop = shop;
		this.code = code;
	}

	public ShopifyController() {
		// TODO Auto-generated constructor stub
	}

	public ShopifyController(EcommerceLog log2) {
		// TODO Auto-generated constructor stub
		this.setEcomLog(log2);
	}

	public ShopifyController(ShopifyRateRequest shopifyRateRequest) {
		// TODO Auto-generated constructor stub
		this.shopifyRateRequest = shopifyRateRequest;
	}

	/**
	 * 
	 * @param ratingList
	 * @param order
	 * @param storeurl
	 * @return
	 * @throws org.json.JSONException
	 * @throws JSONException
	 */
	public String castResponceForShopify(List<Rating> ratingList,
			ShippingOrder order, String storeurl, EcommerceStore store2, ShopifyRateRequest shopifyRateRequest2)
			throws org.json.JSONException, JSONException {
		// TODO Auto-generated method stub
		List<ShopifyRateResponce> shopifyResponceList = new ArrayList<ShopifyRateResponce>();
		List<Rating> filtedRate = getFilteredRate(ratingList, storeurl);
				filtedRate=this.applyFreeShipMarkup(filtedRate,store2,order,shopifyRateRequest2);
		 		if (filtedRate != null && filtedRate.size() > 0) {

			for (Rating rate : filtedRate) {

				ShopifyRateResponce shopifyresponce = new ShopifyRateResponce();
				shopifyresponce.setCurrency(order.getCurrency());
				shopifyresponce.setService_code(Long.toString(rate
						.getServiceId()));
				shopifyresponce.setTotal_price(rate.getTotalChargeLocalCurrency());
												if(rate.getTransitDays()!=0 && rate.getTransitDays()>1){
													shopifyresponce.setService_name(rate.getServiceName()  +" -  "+rate.getTransitDays() +" Days ");
												}else if(rate.getTransitDays()==1){
													shopifyresponce.setService_name(rate.getServiceName()  +" -  "+rate.getTransitDays() +" Day ");
												}else{
													shopifyresponce.setService_name(rate.getServiceName());
												}
				shopifyResponceList.add(shopifyresponce);
				System.out.println(shopifyresponce.getService_name() + "====>"
						+ shopifyresponce.getTotal_price() + "/- "
						+ shopifyresponce.getCurrency());
			}
		}

		 		
				JSONObject jsonresponce = new JSONObject(shopifyResponceList);
								 String jsonresString=jsonresponce.toString();
								
								Gson gs=new Gson();
								
								String js=gs.toJson(shopifyResponceList);
				
		Long ss = (Long) ActionContext.getContext().getSession()
				.get("startTime");
		Long dd = System.currentTimeMillis();
		double ddd = (dd - ss) * 0.001d;
		log.debug(ddd + " Seconds");
		String jsons="{ \"rates\":"+js+"}";
						System.out.println(jsons);
						return jsons;
	
	}

	/**
	 * 
	 * @param ratingList
	 * @param storeUrl
	 * @return
	 */

	private List<Rating> getFilteredRate(List<Rating> ratingList,
			String storeUrl) {
		// TODO Auto-generated method stub
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
		EcommerceStore store = eCommerceDAO.getEcomStorebyStoreUrl(storeUrl);

		List<Rating> returnRate = null;
		if (store != null && ratingList != null) {
			List<Rating> cheptest = null;
			List<Rating> fastest = null;

			if (store.isFastest()) {
				fastest = ShopifyUtil.getFastestRate(ratingList);
				returnRate = new ArrayList<Rating>();
				if (fastest != null && fastest.size() > 0) {
					returnRate.add(fastest.get(0));
				}
			} else if (store.isChepest()) {
				cheptest = ShopifyUtil.getChepestRate(ratingList);
				returnRate = new ArrayList<Rating>();
				if (cheptest != null && cheptest.size() > 0) {
					returnRate.add(cheptest.get(0));
				}

			} else if (store.isBothService()) {
				fastest = ShopifyUtil.getFastestRate(ratingList);
				cheptest = ShopifyUtil.getChepestRate(ratingList);
				returnRate = new ArrayList<Rating>();
				if (fastest != null && fastest.size() > 0) {
					returnRate.add(fastest.get(0));
				}
				if (cheptest != null && cheptest.size() > 0) {
					returnRate.add(cheptest.get(0));
				}

			}
		}

		return returnRate;
	}

	/**
	 * Cast the shopify request to ShippingOrder
	 * @param shopifyRateRequest
	 * @return ShippingOrder
	 * @throws Exception
	 */
	ShippingOrder castShopifyRequestToShippingOrder(
			ShopifyRateRequest shopifyRateRequest) throws Exception {
		// TODO Auto-generated method stub

		ShopifyShop shop = (ShopifyShop) ActionContext.getContext()
				.getSession().get("SHOPIFY_STORE");
		if (shop == null) {
			ShopifyUtil.setStoreDetails(shopifyRateRequest.getStoreName());
			shop = (ShopifyShop) ActionContext.getContext().getSession()
					.get("SHOPIFY_STORE");
		}
		CustomerManager customerService = (CustomerManager) MmrBeanLocator
				.getInstance().findBean("customerService");
		Long customerId = this.customerId;
		if (customerId == null) {
			customerId = (Long) ActionContext.getContext().getSession()
					.get("SHOPIFY_CUSTOMER_ID");
			if (customerId == null) {
				customerId = ShopifyUtil
						.getCustomerIdForShopifyStore("https://"
								+ shopifyRateRequest.getStoreName());
				ActionContext.getContext().getSession()
						.put("SHOPIFY_CUSTOMER_ID", customerId);
			}
		}
		ShippingOrder order = new ShippingOrder();
		order.setCustomerId(customerId);
		shop.setCustomerId(customerId);
		order.setCustomer(customerService.getCustomerInfoByCustomerId(order
				.getCustomerId()));
		order.setFromAddress(storeAddress(shop, shopifyRateRequest.getOrigin(),
				customerId));
		updateStoreAddress(order.getCustomerId(), order.getFromAddress());
		order.setToAddress(getToAddressShopifyReq(shopifyRateRequest
				.getDestination()));
		if (order.getToAddress().getPhoneNo() == null) {
			order.getToAddress()
					.setPhoneNo(order.getFromAddress().getPhoneNo());
		}
		order.setCurrency(shopifyRateRequest.getCurrency());
		order.setPackageTypeId(getPackageType());
		if (this.packageMap == false) {
		}
		order.setPackages(getPackageFromShopifyReq(shopifyRateRequest
				.getItems()));
		return order;
	}

	/**
	 * This method is logic for mapping the product's with it's corresponding
	 * package dynamically
	 * @author selva
	 * @param customerId
	 * @param items
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Package> getPackageFromPackageMap(Long customerId,
			List<Item> items) {
		// TODO Auto-generated method stub
		List<Package> shipPackage = new ArrayList<Package>();
		Map<Item, Products> productItemMap = getProductItemMap(items,
				customerId);
		Map<String, Item> fullPack = new HashMap<String, Item>();
		Map<String, Item> partialPack = new HashMap<String, Item>();
		Map<String, Item> mergePack = new HashMap<String, Item>();
		// Map(PackageId,Item)
		List<Long> products = new ArrayList<Long>();
		// Get a set of the entries
		Set productSet = productItemMap.entrySet();
		Iterator i = productSet.iterator();
		while (i.hasNext()) {

			PackageTypes package1 = null;
			Map.Entry me = (Map.Entry) i.next();
			Products pr = (Products) me.getValue();
			Item item = (Item) me.getKey();
			if (item != null && pr != null) {
				item.setProductSolushipId(pr.getProductId());
				products.add(pr.getProductId());
				package1 = productManagerDAO.findPackageTypesByProductId(
						pr.getProductId(), customerId);

				String mapkey1 = "fullPack" + pr.getProductId();
				String mapkey2 = "paritialPack" + pr.getProductId();
				Long quanity = Long.parseLong(item.getQuantity());
				if (package1 != null) {

					String mapkey3 = "packFull" + package1.getPackageTypeId();
					String mapkey4 = "packPart" + package1.getPackageTypeId();
					Long maxCapcity = productManagerDAO
							.getMaximumQunaityOfPackageType(
									package1.getPackageTypeId(),
									pr.getProductId(), customerId);
					if (quanity > maxCapcity) {
						Long divFactor = (quanity / maxCapcity);
						Long remainFactor = (quanity % maxCapcity);
						if (remainFactor > 0) {
							Item mergeItem = mergePack.get(mapkey4);
							List<Long> cartVarients = new ArrayList<Long>();
							Item remainItem = getnewItem(item);
							remainItem.setRemainSlice(remainFactor);
							int q = Integer.parseInt(remainItem.getQuantity());
							float dd = (float) ShopifyUtil.gramToLBS(Double
									.parseDouble(remainItem.getGrams()));
							float we = (dd * remainFactor);
							remainItem.setWeidthlb(we);
							cartVarients.add(Long.parseLong(remainItem
									.getVariant_id()));
							remainItem.setPackageTypesId(package1
									.getPackageTypeId());
							remainItem.setProductSolushipId(pr.getProductId());
							if (mergeItem != null
									&& mergeItem.getPackageTypesId() == package1
											.getPackageTypeId()) {
								List<Long> mergeVarients = null;
								float weight = (float) ShopifyUtil
										.gramToLBS(Double.parseDouble(mergeItem
												.getGrams()));
								weight += ShopifyUtil.gramToLBS(Double
										.parseDouble(mergeItem.getGrams()));
								mergeItem.setWeidthlb(weight);
								Double g = Double.parseDouble(mergeItem
										.getGrams())
										+ Double.parseDouble(mergeItem
												.getGrams());
								mergeVarients = mergeItem.getCartVarients();
								if (mergeVarients == null) {
									mergeVarients = new ArrayList<Long>();
								}

								mergeVarients.addAll(cartVarients);
								mergeItem.setCartVarients(mergeVarients);
								mergeItem.setGrams(g.toString());
								partialPack.put(mapkey2, mergeItem);
								for (Package p : shipPackage) {
									Long id = Long.parseLong(p.getReference2());
									if (id == package1.getPackageTypeId()) {

										float dd1 = Float.parseFloat(p
												.getWeight().toString())
												+ remainItem.getWeidthlb();
										p.setWeight(new BigDecimal(dd1));
										p.setVarientIds(mergeVarients);
									}
								}
							} else {
								remainItem.setCartVarients(cartVarients);
								mergePack.put(mapkey4, remainItem);
								partialPack.put(mapkey2, remainItem);
								shipPackage.addAll(addPackageByQunity(package1,
										remainItem));
							}
						}
						List<Long> cartVarients = new ArrayList<Long>();
						Item mergeItem = mergePack.get(mapkey3);
						Item sliceItem = null;
						sliceItem = getnewItem(item);
						int q = Integer.parseInt(sliceItem.getQuantity());
						float dd = (float) ShopifyUtil.gramToLBS(Double
								.parseDouble(sliceItem.getGrams()));
						q = (int) (q - remainFactor);
						float we = (dd * q) / divFactor;
						sliceItem.setWeidthlb(we);

						sliceItem.setProductSolushipId((pr.getProductId()));
						sliceItem
								.setPackageTypesId(package1.getPackageTypeId());
						cartVarients.add(Long.parseLong(sliceItem
								.getVariant_id()));

						sliceItem.setPackageSlice(divFactor);
						if (mergeItem != null
								&& mergeItem.getPackageTypesId() == package1
										.getPackageTypeId()) {
							List<Long> mergeVarients = null;
							mergeVarients = mergeItem.getCartVarients();
							float weight = mergeItem.getWeidthlb();
							weight += sliceItem.getWeidthlb();
							mergeItem.setWeidthlb(weight);
							if (mergeVarients == null) {
								mergeVarients = new ArrayList<Long>();
							}
							mergeVarients.addAll(cartVarients);
							mergeItem.setCartVarients(mergeVarients);
							fullPack.put(mapkey1, mergeItem);
							for (Package p : shipPackage) {
								Long id = Long.parseLong(p.getReference2());
								if (id == package1.getPackageTypeId()) {

									float dd1 = Float.parseFloat(p.getWeight()
											.toString())
											+ sliceItem.getWeidthlb();
									p.setWeight(new BigDecimal(dd1));
									p.setVarientIds(mergeVarients);
								}
							}
						} else {
							sliceItem.setCartVarients(cartVarients);
							mergePack.put(mapkey3, sliceItem);
							fullPack.put(mapkey1, sliceItem);
							shipPackage.addAll(addPackageByQunity(package1,
									sliceItem));
						}

					} else {
						List<Long> cartVarients = new ArrayList<Long>();
						Item lessItem = null;
						lessItem = getnewItem(item);
						lessItem.setPackageTypesId(package1.getPackageTypeId());
						lessItem.setProductSolushipId(pr.getProductId());
						cartVarients.add(Long.parseLong(lessItem
								.getVariant_id()));
						int q = Integer.parseInt(lessItem.getQuantity());
						float dd = (float) ShopifyUtil.gramToLBS(Double
								.parseDouble(lessItem.getGrams()));
						lessItem.setWeidthlb(dd * q);
						Item mergeItem = mergePack.get(mapkey3);
						if (mergeItem != null
								&& mergeItem.getPackageTypesId() == package1
										.getPackageTypeId()) {

							List<Long> mergeVarients = null;

							mergeVarients = mergeItem.getCartVarients();
							if (mergeVarients == null) {
								mergeVarients = new ArrayList<Long>();
							}
							float weight = mergeItem.getWeidthlb();
							weight += lessItem.getWeidthlb();
							mergeItem.setWeidthlb(weight);
							mergeVarients.addAll(cartVarients);
							mergeItem.setCartVarients(mergeVarients);
							fullPack.put(mapkey1, mergeItem);

							for (Package p : shipPackage) {
								Long id = Long.parseLong(p.getReference2());
								if (id == package1.getPackageTypeId()) {

									float dd1 = Float.parseFloat(p.getWeight()
											.toString())
											+ lessItem.getWeidthlb();
									p.setWeight(new BigDecimal(dd1));
									p.setVarientIds(mergeVarients);
								}
							}

						} else {

							lessItem.setCartVarients(cartVarients);
							mergePack.put(mapkey3, lessItem);
							fullPack.put(mapkey1, lessItem);
							shipPackage.addAll(addPackageByQunity(package1,
									lessItem));

						}

					}

				}else{
					List<Package> packs=addUnMappedProduct(item);
					if(packs!=null && packs.size()>0){
						shipPackage.addAll(packs);
					}
				}
			}

		}

		if(shipPackage!=null && shipPackage.size()>0){
			for (Package pack : shipPackage) {
				 if(pack.getReference1()!=null){
					PackageTypes pty = productManagerDAO
							.findPackageTypesByProductId(
									Long.parseLong(pack.getReference1()),
									customerId);
					double dd = pty.getPackageWeight()
							+ Double.parseDouble(pack.getWeight().toString());
					pack.setWeight(new BigDecimal(dd));
				 }
			}
		}
		return shipPackage;
	}

	private List<Package> addUnMappedProduct(Item item) {
		// TODO Auto-generated method stub
		List<Item> items=new ArrayList<Item>();
		items.add(item);
		List<Package> packages=getPackageFromShopifyReq(items);
		return packages;
	}

	private Item getnewItem(Item item) {
		// TODO Auto-generated method stub
		Item it = new Item();
		it.setWeidthlb(item.getWeidthlb());
		it.setGrams(item.getGrams());
		it.setName(item.getName());
		it.setPackageSlice(item.getPackageSlice());
		it.setPackageTypesId(item.getPackageTypesId());
		it.setPrice(item.getPrice());
		it.setProduct_id(item.getProduct_id());
		it.setProductSolushipId(item.getProductSolushipId());
		it.setProperties(item.getProperties());
		it.setQuantity(item.getQuantity());
		it.setRemainSlice(item.getRemainSlice());
		it.setSku(item.getSku());
		it.setVariant_id(item.getVariant_id());
		it.setVendor(item.getVendor());
		return it;
	}

 

	private Collection<? extends Package> addPackageByQunity(
			PackageTypes package1, Item addItem) {
		// TODO Auto-generated method stub
		List<Package> addPacks = new ArrayList<Package>();

		if (addItem.getPackageSlice() == null
				&& addItem.getRemainSlice() == null) {
			addPacks.add(addPackage(addItem, package1));
			return addPacks;
		} else if (addItem.getPackageSlice() != null
				&& addItem.getPackageSlice() > 0) {
			for (int i = 0; i < addItem.getPackageSlice(); i++) {
				addPacks.add(addPackage(addItem, package1));
			}
			return addPacks;
		} else if (addItem.getRemainSlice() != null
				&& addItem.getRemainSlice() > 0) {
			Package pp = addPackage(addItem, package1);
			addPacks.add(pp);
			return addPacks;
		}
		return addPacks;
	}

	Package addPackage(Item addItem, PackageTypes package1) {

		Package pack = new Package();
		pack.setHeight(new BigDecimal(package1.getPackageHeight()));
		pack.setWidth(new BigDecimal(package1.getPackageWidth()));
		pack.setLength(new BigDecimal(package1.getPackageLength()));
		pack.setVarientIds(addItem.getCartVarients());
		System.out.println(pack.getVarientIds());
		pack.setVarientQuantity(addItem.getCartVarients().size());
		double singleWeight = 0d;
		singleWeight = addItem.getWeidthlb();
		pack.setWeight(new BigDecimal(singleWeight).setScale(2,
				BigDecimal.ROUND_HALF_UP));
		pack.setReference1(addItem.getProductSolushipId().toString());
		pack.setReference2(Long.toString(package1.getPackageTypeId()));

		return pack;

	}

	private Map<Item, Products> getProductItemMap(List<Item> items,
			long customerId) {
		// TODO Auto-generated method stub

		Map<Item, Products> productItem = new HashMap<Item, Products>();

		for (Item item : items) {
			Products p = new Products();
			p.setReference1(item.getVariant_id());
			p.setCustomerId(customerId);
			p.setSkuId(item.getSku());
			Products product = productManagerDAO.searchUniuqeProduct(p);
			if (product != null) {
				productItem.put(item, product);
			}
		}

		return productItem;
	}

	private void updateStoreAddress(Long customerId, Address fromAddress) {
		// TODO Auto-generated method stub
		if (customerId != null && fromAddress != null) {
			List<Address> addressList = addressDAO.findaddressbyid(customerId);
			Address address = null;
			if (addressList != null && addressList.size() > 0) {
				address = addressList.get(0);
				if (address != null) {
					fromAddress.setAddressId(address.getAddressId());
					fromAddress.setCustomerId(customerId);
					addressDAO.edit(fromAddress);
				}
			}

		}

	}

	/**
	 * 
	 * @param shop
	 * @param origin
	 * @param customerId
	 * @return
	 */
	private Address storeAddress(ShopifyShop shop, Origin origin,
			Long customerId) {

		Shop sh = shop.getShop();
		if (shop != null && origin != null && sh!=null) {
			Address ad = new Address();

			String city = sh.getCity();
			ad.setAddress1(sh.getAddress1());
			ad.setProvinceCode(sh.getProvince_code());
			ad.setCountryCode(sh.getCountry_code());
			ad.setContactName(sh.getName());
			ad.setAbbreviationName(sh.getName());
			ad.setPostalCode(sh.getZip());
			/*
			 * try { city = ShopifyUtil .findCity(ad.getPostalCode(),
			 * ad.getCountryCode()); } catch (Exception e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			ad.setCity(city);
			ad.setEmailAddress(shop.getShop().getEmail());
			ad.setAbbreviationName(shop.getShop().getShop_owner());
			ad.setPhoneNo(sh.getPhone());
			ad.setCustomerId(shop.getCustomerId());
			return ad;
		} else if (customerId != 0) {
			Address address = new Address();
			address.setCustomerId(customerId);
			List<Address> addressList = addressDAO.searchAddresses(address);
			return addressList.get(0);

		}

		return null;
	}

	/**
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	private Address getToAddressShopifyReq(Destination destination) {
		// TODO Auto-generated method stub
		Address address = new Address();
		if (destination != null) {
			address.setAddress1(destination.getAddress1());
			if (destination.getAddress3() != null
					&& !destination.getAddress3().equals("")) {
				address.setAddress2(destination.getAddress2() + " ,"
						+ destination.getAddress3());
			} else {
				address.setAddress2(destination.getAddress2());
			}
			address.setCountryCode(destination.getCountry());
			address.setFaxNo(destination.getFax());
			address.setPhoneNo(destination.getPhone());
			address.setProvinceCode(destination.getProvince());
			address.setPostalCode(destination.getPostal_code());
			return address;
		}
		return null;
	}

	/**
	 * 
	 * @param items
	 * @return
	 */
	private List<Package> getPackageFromShopifyReq(List<Item> items) {
		// TODO Auto-generated method stub
		List<Package> packageList = new ArrayList<Package>();
		if (items != null && items.size() > 0) {
			for (Item item : items) {
				Package pack = new Package();

				pack.setHeight(new BigDecimal(1));
				pack.setLength(new BigDecimal(1));
				pack.setWidth(new BigDecimal(1));
				Double singleWeight = ShopifyUtil.gramToLBS(Double
						.parseDouble(item.getGrams()));
				singleWeight = singleWeight
						* Long.parseLong(item.getQuantity());

				pack.setWeight(new BigDecimal(singleWeight).setScale(2,
						BigDecimal.ROUND_HALF_UP));
				packageList.add(pack);

			}
		}
		return packageList;
	}

	/**
	 * 
	 * @return
	 */
	private PackageType getPackageType() {
		// TODO Auto-generated method stub
		// need clarification forn every ecommerce Package
		PackageType pct = new PackageType();
		pct.setPackageTypeId((long) ShiplinxConstants.PACKAGE_TYPE_PACKAGE);
		pct.setName(ShiplinxConstants.PACKAGE_TYPE_PACKAGE_STRING);
		return pct;
	}

	/*********************************** CREATE ORDER ************************************/

	/**
	 * 
	 * @param shopifyOrder
	 * @return
	 * @throws Exception
	 */
	ShippingOrder getShippingOrderFromShopify(ShopifyShippingOrder shopifyOrder)
			throws Exception {
		// TODO Auto-generated method stub
		ShippingOrder so = new ShippingOrder();
		CarrierServiceManager carrierServiceManager = (CarrierServiceManager) MmrBeanLocator
				.getInstance().findBean("carrierServiceManager");
		CustomerManager customerService = (CustomerManager) MmrBeanLocator
				.getInstance().findBean("customerService");
		BusinessDAO businessDAO = (BusinessDAO) MmrBeanLocator.getInstance()
				.findBean("businessDAO");
		// neets to get customerid from E-commerce integration
		Long serviceId = Long.parseLong(shopifyOrder.getShipping_lines().get(0)
				.getCode());
		Service service = carrierServiceManager.getService(serviceId);

		so.setCustomerId(ShopifyUtil.getCustomerIdForShopifyStore("https://"
				+ shopifyOrder.getStoreName()));
		so.setCustomer(customerService.getCustomerInfoByCustomerId(so
				.getCustomerId()));
		so.setPackageTypeId(getPackageType());
		so.setServiceId(serviceId);
		so.setCarrierId(service.getCarrierId());
		so.setCarrier(service.getCarrier());
		so.setService(service);
		so.setBilledWeightUOM("3");
		so.setBusinessId(1L);
		so.setMasterCarrierName(so.getCarrier().getName());
		so.setMasterTrackingNum(so.getCarrier().getTrackingURL());
		so.setCharges(null);
		so.setBusiness(businessDAO.getBusiessById(1L));
		so.setFromAddress(getFromAddressFromShopifyReq(shopifyOrder
				.getStoreName()));
		so.setToAddress(getToAddressFromShopifyReq(shopifyOrder));
		so.setDocsOnly(false);
		so.setScheduledShipDate_web(shopifyOrder.getCreated_at());
		so.setScheduledAt(ShopifyUtil.getDateInTimestamp(so
				.getScheduledShipDate_web()));
		so.setScheduledShipDate(so.getScheduledAt());
		so.setPackages(getPackageFromShopifyReq(getListItem(shopifyOrder)));
		so.setCurrency(shopifyOrder.getCurrency());
		return so;
	}

	/**
	 * 
	 * @param storeName
	 * @return
	 */
	private Address getFromAddressFromShopifyReq(String storeName) {
		// TODO Auto-generated method stub

		// need to get the address Id of the store.
		Long customerId = ShopifyUtil.getCustomerIdForShopifyStore("https://"
				+ storeName);
		if (customerId != 0) {
			Address ad = new Address();
			ad.setCustomerId(customerId);
			List<Address> addressList = addressDAO.searchAddresses(ad);
			Address address = addressList.get(0);
			if (address != null && address.getContactName() == null) {
				address.setContactName(address.getAbbreviationName());
			}
			return address;

		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param shopifyorder
	 * @return
	 */
	private Address getToAddressFromShopifyReq(ShopifyShippingOrder shopifyorder) {
		// TODO Auto-generated method stub
		Shipping_address shipAddress = shopifyorder.getShipping_address();
		Address address = new Address();
		String city = null;
		if (this.city != null) {
			city = this.city;
		} else {
			try {
				city = ShopifyUtil.findCity(shipAddress.getZip(),
						shipAddress.getCountry_code());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				city = null;
			}

			if (city == null) {
				city = shipAddress.getCity();
			}
		}
		if (shipAddress != null) {
			address.setAddress1(shipAddress.getAddress1());

			address.setAddress2(shipAddress.getAddress2());

			address.setCity(city);
			address.setCountryCode(shipAddress.getCountry_code());
			address.setContactName(shipAddress.getCountry());
			if (shipAddress.getPhone() == null) {
				shipAddress.setPhone("");
			}
			address.setPhoneNo(shipAddress.getPhone());
			address.setProvinceCode(shipAddress.getProvince_code());
			address.setPostalCode(shipAddress.getZip());
			address.setAbbreviationName(shipAddress.getFirst_name() + ","
					+ shipAddress.getLast_name());
			address.setContactName(address.getAbbreviationName());

		}
		return address;
	}

	/**
	 * 
	 * @param shopifyOrder
	 * @return
	 */
	private List<Item> getListItem(ShopifyShippingOrder shopifyOrder) {
		// TODO Auto-generated method stub
		List<Item> items = new ArrayList<Item>();
		if (shopifyOrder != null && shopifyOrder.getLine_items() != null) {
			for (Line_items item : shopifyOrder.getLine_items()) {

				Item it = new Item();
				it.setFulfillment_service(item.getFulfillment_service());
				it.setGrams(item.getGrams().toString());
				it.setName(item.getName());
				it.setPrice(item.getPrice());
				it.setQuantity(item.getQuantity().toString());
				it.setSku(item.getSku());
				// it.setTaxable(item.getTaxable());
				it.setVendor(item.getVendor());
				it.setVariant_id(item.getVariant_id().toString());

				// it.setRequires_shipping(item.getRequires_shipping());
				items.add(it);
			}

		}
		return items;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public EcommerceStore getEcomStore() {
		return ecomStore;
	}

	public void setEcomStore(EcommerceStore ecomStore) {
		this.ecomStore = ecomStore;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Long getServiceId() {
		return ServiceId;
	}

	public void setServiceId(Long serviceId) {
		ServiceId = serviceId;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Thread for Storeing AccessKey, get city from api, calculated shipping
	 * package Log
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

		if (this.customerId != null && this.reqItems != null
				&& this.shopifyRateRequest != null) {
			if (this.packageMap) {
				this.shipPackes = getPackageFromPackageMap(customerId, reqItems);
				log.debug("========================PACKAGE MAP THREAD  ====================");
				if(this.shipPackes!=null && this.shipPackes.size()>0)
				log.debug("===============Mappded Packages ="+this.shipPackes.size());
			}
			log.debug("======================== Thread   setting to city ====================");
			try {
				this.city = ShopifyUtil.findCity(shopifyRateRequest
						.getDestination().getPostal_code(), shopifyRateRequest
						.getDestination().getCountry());
				// ShopifyUtil.setStoreDetails(shopifyRateRequest.getStoreName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			if (this.getShop() != null && this.code != null) {
				log.debug("==================== Thread getting asscess key ============================");
				String accessKey = null;
				try {
					accessKey = ShopifyUtil.getAccessKeyForShopify(
							this.getShop(), this.getCode());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (accessKey != null) {
					String url = this.shop;
					if (!url.startsWith("https://")) {
						url = "https://" + url;
					}
					
					EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
							.findBean("eCommerceDAO");
					EcommerceStore st=eCommerceDAO.getEcomStorebyStoreUrl(url);
					if(st!=null && st.getAccessKey()==null){
						// update access key
						ShopifyUtil.addAccesKeyForStore(url, accessKey);
						// Rate service
						String rateserviceId = ShopifyUtil
								.setCustomerCarrierForShopify(url, accessKey);
						// Create Shipment service
						String createServiceId = ShopifyUtil
								.setOrderCreationWebhooksForShopify(url, accessKey);
						// cancel shipment Service
						String cancelServiceId = ShopifyUtil
								.setOrderCancelWebhooksForShopify(url, accessKey);
						
						// Un install shopify app
						String uninstallId = ShopifyUtil
								.ShopifyAppUninstallWebhook(url, accessKey);
						
						EcommerceStore store = new EcommerceStore();
						store.setUrl(url);
						store.setRateServiceId(rateserviceId);
						store.setCancelShipWebhookId(cancelServiceId);
						store.setCreateShipWebhookId(createServiceId);
 						ShopifyUtil.updateServices(store);
						
					}
					synchProducts(url, accessKey);
				}
			}
			if (this.getEcomLog() != null) {
				log.debug("Thread   saving ecom log");

				EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator
						.getInstance().findBean("eCommerceDAO");
				eCommerceDAO.insertEcommerceLog(getEcomLog());
			}

		}

	}

	private void synchProducts(String url, String accessKey) {
		// TODO Auto-generated method stub
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
		ProductManager productManagerService = (ProductManager) MmrBeanLocator
				.getInstance().findBean("productManagerService");
		EcommerceStore store = eCommerceDAO.getEcomStorebyStoreUrl(url);
		if (store != null) {
			ShopifyProduct products = ShopifyUtil.synchProducts(
					store.getAccessKey(), store.getUrl());
			products.setCustomerId(store.getCustomerId());
			productManagerService.sycnShopifyProducts(products);
		}

	}

	public ShopifyRateRequest getShopifyreq(ShopifyShippingOrder shopifyOrder) {
		// TODO Auto-generated method stub

		ShopifyRateRequest srq = new ShopifyRateRequest();
		srq.setCurrency(shopifyOrder.getCurrency());
		srq.setStoreName(shopifyOrder.getStoreName());
		Destination ds = new Destination();
		ds.setAddress1(shopifyOrder.getShipping_address().getAddress1());
		ds.setAddress2(shopifyOrder.getShipping_address().getAddress2());
		ds.setAddress3("");
		ds.setCity(shopifyOrder.getShipping_address().getCity());
		ds.setCompany_name(shopifyOrder.getShipping_address().getCompany());
		ds.setCountry(shopifyOrder.getShipping_address().getCountry_code());
		ds.setFax(shopifyOrder.getShipping_address().getPhone());
		ds.setPhone(shopifyOrder.getShipping_address().getPhone());
		ds.setPostal_code(shopifyOrder.getShipping_address().getZip());
		ds.setProvince(shopifyOrder.getShipping_address().getProvince_code());

		ds.setName(shopifyOrder.getShipping_address().getName());
		srq.setDestination(ds);
		srq.setOrigin(new Origin());
		srq.setItems(getListItem(shopifyOrder));

		return srq;
	}

	public ShopifyRateRequest getShopifyRateRequest() {
		return shopifyRateRequest;
	}

	public void setShopifyRateRequest(ShopifyRateRequest shopifyRateRequest) {
		this.shopifyRateRequest = shopifyRateRequest;
	}

	public EcommerceLog getEcomLog() {
		return ecomLog;
	}

	public void setEcomLog(EcommerceLog ecomLog) {
		this.ecomLog = ecomLog;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Package> getShipPackes() {
		return shipPackes;
	}

	public void setShipPackes(List<Package> shipPackes) {
		this.shipPackes = shipPackes;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Item> getReqItems() {
		return reqItems;
	}

	public void setReqItems(List<Item> reqItems) {
		this.reqItems = reqItems;
	}

	public Long getCreateShipmentWebhookId() {
		return createShipmentWebhookId;
	}

	public void setCreateShipmentWebhookId(Long createShipmentWebhookId) {
		this.createShipmentWebhookId = createShipmentWebhookId;
	}

	public Long getCancelShipmentWebhookId() {
		return cancelShipmentWebhookId;
	}

	public void setCancelShipmentWebhookId(Long cancelShipmentWebhookId) {
		this.cancelShipmentWebhookId = cancelShipmentWebhookId;
	}

	public boolean isPackageMap() {
		return packageMap;
	}

	public void setPackageMap(boolean packageMap) {
		this.packageMap = packageMap;
	}
	
	
	
	
	

	public List<Rating> applyFreeShipMarkup(List<Rating> ratingList, EcommerceStore store, ShippingOrder order, ShopifyRateRequest shopifyRateRequest2) {
		// TODO Auto-generated method stub
		//free ship setting
		
		List<Rating> rates=null;
		if(order!=null && store!=null    && ratingList!=null && ratingList.size()>0){
			
			ratingList=setFinalRates(ratingList);
			rates=ratingList;
			if(rates!=null && rates.size()>0){
			
			
				if(store.getMarkupLevel()!=null && store.getMarkupType()!=null && store.getMarkupLevel()==1){
					if(store.getMarkupType()==1){
						ratingList=applyMarkpByFlatRate(store.getFlatMarkup(),true,ratingList);
					}else if(store.getMarkupType()==2){
						ratingList=applyMarkpByFlatRate(store.getFlatMarkup(),false,ratingList);
					}
					
				}else if(store.getMarkupLevel()!=null && store.getMarkupType()!=null && store.getMarkupLevel()==2){
					if(store.getMarkupType()==1){
						ratingList=applyMarkupByPerc(store.getMarkupPerc(),true,ratingList);
					}else if(store.getMarkupType()==2){
						ratingList=applyMarkupByPerc(store.getMarkupPerc(),false,ratingList);
					}
				}
					rates=ratingList;
				
		}
			
			if(store.isFreeshipRequired()){
				//free shipping
				if(store.getCompareFreeship()!=null && store.getFreeShipType()!=null 
						&& store.getWeight()!=null && store.getFreeShipType()==1){ // weight based
				 
					
			    double totalWeight=getTotalWeightFromOrder(shopifyRateRequest2.getItems());	
			      if(store.getCompareFreeship()==2 && totalWeight>store.getWeight()){ 
			    	   rates=applyFreeRates(ratingList,store);
			      }else if(store.getCompareFreeship()==1 && totalWeight<store.getWeight()){
			    	  rates=applyFreeRates(ratingList,store);
			      }
			    	
			    }else if(store.getFreeShipType()!=null && store.getFlatRate()!=null && store.getFreeShipType()==2){ // cost based 
			    	double totalCost=getTotalCostFromRequest(shopifyRateRequest2);
		    	 if(store.getCompareFreeship()!=null && store.getFlatRate()!=null
		    			 && store.getCompareFreeship()==2 && totalCost>store.getFlatRate()){ 
			    	   rates=applyFreeRates(ratingList,store);
				      }else if(store.getCompareFreeship()!=null && store.getFlatRate()!=null
				    		  && store.getCompareFreeship()==1 && totalCost<store.getFlatRate()){
				    	  rates=applyFreeRates(ratingList,store);
				      }
			    }else{
					rates=ratingList;
				}
			    	
			}
			
			
		}
		return rates;
	}

	/**
	 * Markup or down by perc
	 * @param markupPerc
	 * @param b
	 * @param ratingList
	 * @return
	 */
	private List<Rating> applyMarkupByPerc(Double markupPerc, boolean b, List<Rating> ratingList) {
		// TODO Auto-generated method stub
		if(ratingList!=null && ratingList.size()>0 && b){ //markup 
			for(Rating rate:ratingList){
				double totalcharge=rate.getTotalChargeLocalCurrency();	
				if(markupPerc!=null && markupPerc>0){
					double value=markupPerc/100;
					value=value*totalcharge;
					totalcharge+=value;
				}
				rate.setTotalChargeLocalCurrency(totalcharge);
			}
		}else if(ratingList!=null && ratingList.size()>0){ //markdown
			for(Rating rate:ratingList){
				double totalcharge=rate.getTotalChargeLocalCurrency();	
				System.out.println("total :"+totalcharge);
				if(markupPerc!=null && markupPerc>0){
					double value=markupPerc/100;
					value=value*totalcharge;
					totalcharge-=value;
				}
				rate.setTotalChargeLocalCurrency(totalcharge);
			}
		}
		return ratingList;
	}

	private List<Rating> applyMarkpByFlatRate(Double flatMarkup, boolean b, List<Rating> ratingList) {
		// TODO Auto-generated method stub

		if(ratingList!=null && ratingList.size()>0 && b){ //markup 
		for(Rating rate:ratingList){
				double totalcharge=rate.getTotalChargeLocalCurrency();	
				if(flatMarkup!=null && flatMarkup>0){
					double d=flatMarkup*100;
					totalcharge+=d;
				}
				rate.setTotalChargeLocalCurrency(totalcharge);
			}
		}else if(ratingList!=null && ratingList.size()>0){ //markdown
			for(Rating rate:ratingList){
				double totalcharge=rate.getTotalChargeLocalCurrency();	
				System.out.println("total :"+totalcharge);
				if(flatMarkup!=null && flatMarkup>0){
					double d=flatMarkup*100;
					totalcharge-=d;
				}
				rate.setTotalChargeLocalCurrency(totalcharge);
			}
		}
		return ratingList;
	}

	private List<Rating> setFinalRates(List<Rating> ratingList) {
		// TODO Auto-generated method stub
		if(ratingList!=null && ratingList.size()>0){
			for(Rating rate :ratingList){
				double totalCharge=ShopifyUtil.getTotalCharge(rate.getCharges());
				rate.setTotalChargeLocalCurrency(totalCharge);
			}
		}
		return ratingList;
	}

	/**
	 * Appling Free shipping
	 * @param ratingList
	 * @param store 
	 * @return
	 */
	private List<Rating> applyFreeRates(List<Rating> ratingList, EcommerceStore store) {
		// TODO Auto-generated method stub
		if(!store.isBothService()){
			if(ratingList!=null && ratingList.size()>0){
				for(Rating rate:ratingList){
					log.debug("Free shipment of "+rate.getTotalChargeLocalCurrency());
					rate.setCharges(new ArrayList<Charge>());
					rate.setTotalChargeLocalCurrency(0.0d);
				}
			}
		}else if(ratingList!=null && ratingList.size()>0){
		List<Rating>	cheptest = ShopifyUtil.getChepestRate(ratingList);
			if(cheptest!=null && cheptest.size()>0){
				for(Rating rate:cheptest){
					log.debug("Free shipment of "+rate.getTotalChargeLocalCurrency());
					rate.setCharges(new ArrayList<Charge>());
					rate.setTotalChargeLocalCurrency(0.0d);
				}
		}
		}
		
		return ratingList;
	}

	private double getTotalCostFromRequest(
			ShopifyRateRequest shopifyRateRequest2) {
		// TODO Auto-generated method stub
		double totalcost=0.0d;
		if(shopifyRateRequest2!=null && shopifyRateRequest2.getItems()!=null && 
				shopifyRateRequest2.getItems().size()>0){
		for(Item item:shopifyRateRequest2.getItems()){
				 double d=Double.parseDouble(item.getPrice());
				 d=d*Long.parseLong(item.getQuantity());
				 d=d/100;
				 totalcost+=d;
			}
	     }
		log.debug("Total cost spend :"+totalcost);
		return totalcost;
	}

	private double getTotalWeightFromOrder(List<Item> list) {
		// TODO Auto-generated method stub
		double totweight=0.0d;
		
	if(list!=null && list.size()>0){
	for(Item pack:list){
			   double w=ShopifyUtil.gramToLBS(Double.parseDouble(pack.getGrams()));
			    w=w*Long.parseLong(pack.getQuantity());
				totweight+=w;
			}
		}
		log.debug("Total package Weight :"+totweight);
		return totweight;
	}

}
