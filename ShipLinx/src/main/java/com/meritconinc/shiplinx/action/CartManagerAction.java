package com.meritconinc.shiplinx.action;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.Cart;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.service.CartManager;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CartManagerAction extends BaseAction
  implements Preparable, ServletRequestAware, ServletResponseAware
{
  private static final long serialVersionUID = 1306201213001L;
  private static final Logger log = LogManager.getLogger(CartManagerAction.class);
  private HttpServletRequest request;
  private HttpServletResponse response;
  private Cart cart;
  private CartManager cartManager;
  private CustomerManager customerService;
  private CarrierServiceManager carrierServiceManager;
  private Collection<Cart> availableCarts = new ArrayList();
  private List<Boolean> select;

  public CartManager getCartManager()
  {
    return this.cartManager;
  }

  public void setCartManager(CartManager cartManager)
  {
    this.cartManager = cartManager;
  }

  public CustomerManager getCustomerService()
  {
    return this.customerService;
  }

  public void setCustomerService(CustomerManager customerService)
  {
    this.customerService = customerService;
  }

  public CarrierServiceManager getCarrierServiceManager()
  {
    return this.carrierServiceManager;
  }

  public void setCarrierServiceManager(CarrierServiceManager carrierServiceManager)
  {
    this.carrierServiceManager = carrierServiceManager;
  }

  public Collection<Cart> getAvailableCarts()
  {
    return this.availableCarts;
  }

  public void setAvailableCarts(Collection<Cart> availableCarts)
  {
    this.availableCarts = availableCarts;
  }

  public List<Boolean> getSelect()
  {
    return this.select;
  }

  public void setSelect(List<Boolean> select)
  {
    this.select = select;
  }

  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }

  public void setServletResponse(HttpServletResponse response)
  {
    this.response = response;
  }

  public Cart getCart() {
    Cart cart = (Cart)getSession().get("cart");
    if (cart == null) {
      cart = new Cart();
      cart.setBusinessId(Long.valueOf(UserUtil.getMmrUser().getBusinessId()));
      cart.setCustomerId(Long.valueOf(UserUtil.getMmrUser().getCustomerId()));
      setCart(cart);
    }
    return cart;
  }

  public void setCart(Cart cart)
  {
    getSession().put("cart", cart);
  }

  public List<Cart> getCustomerCartList()
  {
    return (List)getSession().get("customerCartList");
  }

  public void setCustomerCartList(List<Cart> customerCartList)
  {
    getSession().put("customerCartList", customerCartList);
  }

  public void prepare()
    throws Exception
  {
    setAvailableCarts();
  }

  public String addNewCart()
  {
    log.debug("Inside-----------addNewCart() method--------------");
    searchCart();
    refresh();

    return "success";
  }

  public String save()
    throws Exception
  {
    log.debug(" CHECK METHOD IN CART SAVE ***********");

    this.cart = getCart();

    String shopifyStep1 = String.valueOf(this.request.getParameter("step1"));
    String selectedCartName = this.cart.getCartName();
    String enteredURL = this.cart.getUrlName();
    String cartApiKey = "";
    String cartSharedSecret = "";
    String shopifyStep2TempCode = "";
    try
    {
      if ((selectedCartName == null) || (selectedCartName.length() == 0)) {
        addActionError(getText("error.cart.name"));
        return "input";
      }

      if ((enteredURL == null) || (enteredURL.length() == 0)) {
        addActionError(getText("error.cart.store.url"));
        return "input";
      }if ((!enteredURL.contains(".myshopify.com/admin")) && (selectedCartName.equalsIgnoreCase("Shopify"))) {
        addActionError(getText("error.cart.valid.store.url", new String[] { selectedCartName, MessageUtil.getMessage("shopify.cart.validate.url") }));
        return "input";
      }
      if (checkIfAlreadyPresent(enteredURL))
      {
        addActionError(getText("error.cart.url.used", new String[] { enteredURL }));
        return "input";
      }

      if ((this.availableCarts != null) && (this.availableCarts.size() > 0)) {
        Cart cart = new Cart();
        cart = getSelectedCartObject(selectedCartName, this.availableCarts);
        cartApiKey = cart.getApiKey();
        cartSharedSecret = cart.getSharedSecret();
      }

      if ((shopifyStep1.equalsIgnoreCase("true")) && (selectedCartName.equalsIgnoreCase("Shopify")) && (cartApiKey != null) && (cartApiKey.length() > 0))
      {
        StringBuffer sbAuthenticationStep1URL = new StringBuffer();
        sbAuthenticationStep1URL.append(enteredURL);
        sbAuthenticationStep1URL.append("/oauth/authorize?client_id=" + cartApiKey + "&scope=" + "read_orders,write_orders");
        log.debug("Shopify Authetication Stage I URL is::" + sbAuthenticationStep1URL.toString());

        this.response.sendRedirect(this.response.encodeRedirectURL(sbAuthenticationStep1URL.toString()));
      }
      else if ((!shopifyStep1.equalsIgnoreCase("true")) && (selectedCartName.equalsIgnoreCase("Shopify")))
      {
        shopifyStep2TempCode = getTempCodeFromRequestParameter();

        if ((shopifyStep2TempCode != null) && (shopifyStep2TempCode.length() > 0)) {
          String accessToken = getAccessToken(enteredURL, cartApiKey, cartSharedSecret, shopifyStep2TempCode);
          Cart cart = new Cart();
          if ((this.availableCarts != null) && (this.availableCarts.size() > 0)) {
            cart = getSelectedCartObject(selectedCartName, this.availableCarts);
            cart.setToken(accessToken);
            cart.setUrlName(enteredURL);
            cart.setBusinessId(Long.valueOf(UserUtil.getMmrUser().getBusinessId()));
            cart.setCustomerId(Long.valueOf(UserUtil.getMmrUser().getCustomerId()));
            this.cartManager.saveCustomerCartDetail(cart);
            addActionMessage(getText("cart.install.successfully", new String[] { cart.getCartName() }));
            return searchCart();
          }
        } else {
          addActionError(getText("error.cart.install", new String[] { this.cart.getCartName() }));
          return "input";
        }
      } else {
        addActionError(getText("error.cart.install", new String[] { this.cart.getCartName() }));
        return "input";
      }
    } catch (ShiplinxException ex) {
      log.error("Exception ocurred during the save() method in CartManagerAction::", ex);
      addActionError(getText("customer.cart.save.fail", new String[] { this.cart.getUrlName(), this.cart.getCartName() }));

      return "input";
    }
    return "success";
  }

  private boolean checkIfAlreadyPresent(String cartUrl)
  {
    try {
      List<Cart> customerCartList = new ArrayList<Cart>();
      if (getLoginUser().getCustomerId() > 0L) {
        customerCartList = this.cartManager.getCustomerCartByCustomerIdandBusinessId(UserUtil.getMmrUser().getCustomerId(), UserUtil.getMmrUser().getBusinessId());

        for (Cart c : customerCartList)
        {
          if (cartUrl.trim().equalsIgnoreCase(c.getUrlName()))
            return true;
        }
      }
    } catch (Exception e) {
      log.error("error occured while checking if the entered URL is already present.");
    }
    return false;
  }

  public void setAvailableCarts()
  {
    WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((ServletContext)ActionContext.getContext().get("com.opensymphony.xwork2.dispatcher.ServletContext"));
    this.cartManager = ((CartManager)context.getBean("cartManager"));
    if (getLoginUser().getCustomerId() > 0L)
      this.availableCarts = this.cartManager.getCartListByBusinessId(UserUtil.getMmrUser().getBusinessId());
  }

  public String searchCart()
  {
    log.debug("Inside searchCart() method of CartManagerAction");
    try {
      List customerCartList = new ArrayList();
      if (getLoginUser().getCustomerId() > 0L) {
        customerCartList = this.cartManager.getCustomerCartByCustomerIdandBusinessId(UserUtil.getMmrUser().getCustomerId(), UserUtil.getMmrUser().getBusinessId());
        setCustomerCartList(customerCartList);
      }
    } catch (ShiplinxException ex) {
      log.error("Exception ocurred during the searchCart() method in CartManagerAction::", ex);
      addActionError(getText("customer.cart.search.fail"));
      return "input";
    }
    return "success";
  }

  public String getAllUnshippedOrder()
  {
    log.debug("Inside getAllUnshippedOrder() method of CartManagerAction");
    refresh();

    this.cart = getCart();
    if (this.carrierServiceManager != null) {
      if (getSession().get("CARRIERS") == null) {
        initCarrierList();
        this.cart.setCarrierId(Long.valueOf(-1L));
      }
      if (getSession().get("SERVICES") == null) {
        List services = getCarrierServices(this.cart.getCarrierId());
        getSession().put("SERVICES", services);
      }
    }
    List orderList = new ArrayList();
    Cart customerCart = new Cart();
    ShippingOrder so = getShippingOrder();
    String cartOrderId = "";
    int cartIndex = 0;
    try {
      if (this.request.getParameter("index") != null) {
        cartIndex = Integer.parseInt(this.request.getParameter("index"));
        List customerCartList = getCustomerCartList();
        customerCart = (Cart)customerCartList.get(cartIndex);

        if (this.request.getParameter("orderId") != null) {
          cartOrderId = this.request.getParameter("orderId");
        }
        if (customerCart != null) {
          orderList = this.cartManager.getAllUnshippedOrder(so, customerCart, cartOrderId);
        }
        if ((orderList != null) && (orderList.size() > 0)) {
          setShipments(orderList); } else {
          if ((cartOrderId != null) && (cartOrderId.length() > 0)) {
            addActionError(getText("error.cart.specific.order.not.found", new String[] { cartOrderId, customerCart.getUrlName() }));
            return "input";
          }
          addActionError(getText("error.cart.all.order.not.found", new String[] { customerCart.getUrlName() }));
          return "input";
        }
      }
    } catch (ShiplinxException ex) {
      log.error("Exception ocurred during the getAllUnshippedOrder() method in CartManagerAction::", ex);
      if ((cartOrderId != null) && (cartOrderId.length() > 0))
        addActionError(getText("unshipped.cart.specific.order.fail", new String[] { cartOrderId, customerCart.getUrlName() }));
      else
        addActionError(getText("unshipped.cart.all.orders.fail", new String[] { customerCart.getUrlName() }));
      return "input";
    }
    this.request.setAttribute("fromCart", "cartCreateShipment");
    return "success";
  }

  public String createShipments() throws Exception
  {
    try
    {
      log.debug("Inside createShipments() method of CartManagerAction");

      this.request.setAttribute("fromCart", "cartCreateShipment");
      if ((this.select != null) && (this.select.size() > 0)) {
        List saveShipments = new ArrayList();
        List shipments = getShipments();
        for (int i = 0; i < this.select.size(); i++)
        {
          if ((this.select.get(i) != null) && (((Boolean)this.select.get(i)).booleanValue())) {
            ShippingOrder so = (ShippingOrder)shipments.get(i);
            saveShipments.add(so);
          }
        }
        if (saveShipments.size() > 0) {
          shipments = this.cartManager.createBatchShipments(saveShipments);
          if (shipments != null) {
            getSession().remove("shipments");
            setShipments(shipments);
            addActionMessage(getText("unship.shipment.saved.sucessfully"));
          } else {
            addActionError(getText("select.valid.shipments.for.creation"));
            return "input";
          }
        } else {
          addActionError(getText("select.valid.shipments.for.creation"));
          return "input";
        }
      } else {
        addActionError(getText("select.valid.shipments.for.creation"));
        return "input";
      }
    } catch (ShiplinxException ex) {
      log.error("Exception ocurred during the save() method in CartManagerAction::", ex);
      addActionError(getText("customer.cart.save.fail"));
      return "input";
    }

    this.request.setAttribute("fromCart", "cartProcessShipment");

    return "success";
  }

  public String processShipmentNow() throws Exception
  {
    try {
      log.debug("Inside processShipmentNow() method of CartManagerAction");
      this.request.setAttribute("fromCart", "cartProcessShipment");

      this.cart = getCart();
      String s = this.request.getParameter("markup.serviceId");
      if (!StringUtil.isEmpty(s))
        this.cart.setServiceId(Long.valueOf(Long.parseLong(s)));
      if ((this.cart != null) && (this.cart.getCarrierId() != null) && (this.cart.getServiceId() != null) && (this.cart.getCarrierId().longValue() > 0L) && (this.cart.getServiceId().longValue() > 0L))
      {
        List processShipments = new ArrayList();
        List shipments = getShipments();
        if ((this.select != null) && (this.select.size() > 0)) {
          for (int i = 0; i < this.select.size(); i++)
          {
            if ((this.select.get(i) != null) && (((Boolean)this.select.get(i)).booleanValue())) {
              ShippingOrder so = (ShippingOrder)shipments.get(i);
              processShipments.add(so);
            }
          }
          if (processShipments.size() > 0) {
            shipments = this.cartManager.processShipmentNow(processShipments, this.cart);
            if (shipments != null) {
              addActionMessage(getText("shipment.processing.started.check.track.search"));
              getSession().remove("shipments");
              setShipments(shipments);
            }
          } else {
            addActionError(getText("no.valid.shipment.found.for.batch.shipment.processing"));
            return "input";
          }
        } else {
          addActionError(getText("select.valid.shipments.for.processing"));
          return "input";
        }
      } else {
        addActionError(getText("select.carrier.service.for.batch.shipment.processing"));
        return "input";
      }
    } catch (ShiplinxException ex) {
      log.error("Exception ocurred during the save() method in CartManagerAction::", ex);
      addActionError(getText("customer.cart.save.fail"));
      return "input";
    }
    return "success";
  }

  private void initCarrierList()
  {
    List cList = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
    Carrier c = new Carrier();
    c.setId(Long.valueOf(-1L));
    c.setName("");
    cList.add(0, c);
    getSession().put("CARRIERS", cList);
  }

  private List<Service> getCarrierServices(Long carrierCode)
  {
    List sList = this.carrierServiceManager.getServicesForCarrier(carrierCode);
    Service s = new Service();
    s.setId(Long.valueOf(-1L));
    s.setName("");
    s.setCarrierId(carrierCode);
    sList.add(0, s);
    getSession().put("SERVICES", sList);
    return sList;
  }

  public List<ShippingOrder> getShipments() {
    return (List)getSession().get("shipments");
  }

  public void setShipments(List<ShippingOrder> shipments) {
    getSession().put("shipments", shipments);
  }

  public ShippingOrder getShippingOrder() {
    ShippingOrder order = (ShippingOrder)getSession().get("shippingOrder");
    try {
      if (order == null) {
        order = new ShippingOrder();
        order.setBusinessId(UserUtil.getMmrUser().getBusinessId());
        order.setCustomerId(Long.valueOf(UserUtil.getMmrUser().getCustomerId()));
        order.setCustomer(this.customerService.getCustomerInfoByCustomerId(order.getCustomerId().longValue()));
        order.setFromAddress(order.getCustomer().getAddress());
        setShippingOrder(order);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return order;
  }
  public void setShippingOrder(ShippingOrder shippingOrder) {
    getSession().put("shippingOrder", shippingOrder);
  }

  private String getTempCodeFromRequestParameter()
  {
    String shopifyStep2TempCode = "";
    Enumeration paramNames = this.request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String paramName = (String)paramNames.nextElement();
      String[] paramValues = this.request.getParameterValues(paramName);
      if (paramName.equalsIgnoreCase("code")) {
        shopifyStep2TempCode = paramValues[0];
        log.debug("paramName::" + paramName);
        log.debug("paramValues::" + paramValues[0]);
        break;
      }
    }
    return shopifyStep2TempCode;
  }

  private String getAccessToken(String enteredURL, String cartApiKey, String cartSharedSecret, String shopifyStep2Code)
  {
    StringBuffer sbAuthenticationStep2URL = new StringBuffer();
    String accessToken = "";
    try
    {
      if ((shopifyStep2Code != null) && (shopifyStep2Code.length() > 0) && (cartApiKey != null) && (cartApiKey.length() > 0) && (cartSharedSecret != null) && (cartSharedSecret.length() > 0)) {
        sbAuthenticationStep2URL.append(enteredURL);
        sbAuthenticationStep2URL.append("/oauth/access_token");
        log.debug("Shopify Authetication Stage II URL is::" + sbAuthenticationStep2URL.toString());

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(sbAuthenticationStep2URL.toString());

        List nameValuePairs = new ArrayList(1);
        nameValuePairs.add(new BasicNameValuePair("client_id", cartApiKey));
        nameValuePairs.add(new BasicNameValuePair("client_secret", cartSharedSecret));
        nameValuePairs.add(new BasicNameValuePair("code", shopifyStep2Code));

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line = "";
        while ((line = rd.readLine()) != null) {
          log.debug("Response from Shopify for Permanant Access Token is::" + line);
          if (line.contains("access_token")) {
            log.debug("Line ::" + line);

            line = line.replaceAll("\\{", "");
            line = line.replaceAll("\\}", "");
            line = line.replaceAll("\\\"", "");

            String[] token = line.split("\\:");
            log.debug("After split token[0] ::" + token[0]);
            log.debug("After split token[1]  ::" + token[1]);
            accessToken = token[1];
          }
        }
      }
    } catch (Exception ex) {
      log.error("Exception occured to getAccessToken() method in CartManagerAction::", ex);
      throw new ShiplinxException(ex.getMessage());
    }
    return accessToken;
  }

  private Cart getSelectedCartObject(String selectedCartName, Collection<Cart> cartList)
  {
    for (Cart cart : cartList) {
      if (cart.getCartName().equalsIgnoreCase(selectedCartName)) {
        return cart;
      }
    }
    return null;
  }

  private void refresh()
  {
    getSession().remove("cart");
    getSession().remove("CARRIERS");
    getSession().remove("SERVICES");
    getSession().remove("shipments");
    getSession().remove("shippingOrder");
  }
}
