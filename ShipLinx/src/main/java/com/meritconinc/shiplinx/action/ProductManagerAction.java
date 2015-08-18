package com.meritconinc.shiplinx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cwsi.eshipper.carrier.ups.rate.ResponseDocument.Response;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.PackageTypes;
import com.meritconinc.shiplinx.model.ProductLine;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.WarehouseLocation;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.ProductManager;
import com.meritconinc.shiplinx.utils.PDFRenderer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import java.util.Collections;
import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.model.admin.EcommerceStore;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.api.Util.ShopifyUtil;
import com.meritconinc.shiplinx.api.model.ShopifyProduct;
import com.meritconinc.shiplinx.model.ProductPackageMap;
import com.meritconinc.shiplinx.model.UnitOfMeasure;


import javax.servlet.http.HttpServletResponse;
public class ProductManagerAction extends BaseAction implements Preparable,ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID	= 2105201224001L;
	private static final Logger log = LogManager.getLogger(CustomerManagerAction.class);
	private ProductManager productManagerService;
	private CustomerManager customerManagerService;
	public HttpServletRequest request;
	public HttpServletResponse response;
	
	private List<Products> productList;
	
	public void setProductList(List<Products> productList) {
				this.productList = productList;
			}
	private List<ProductLine> productLineList;
	
	private List<PackageTypes> packageTypesList;
	private Map<String, Long> productSearchResult = new HashMap<String, Long>();
	
	private Map<String, Long> productLineSearchResult = new HashMap<String, Long>();
	
	private Map<String, Long> productByProductLineSearchResult = new HashMap<String, Long>();
	
	//private Map<String, String> productPopulateResult = new HashMap<String, String>();
	
	public Map session = (Map) ActionContext.getContext().get("session");
	
	private List<ProductPackageMap> productPackageMapList;
	 
	private ProductPackageMap productPackageMap;
	
	 public void setServletResponse(HttpServletResponse httpServletResponse) {
	    	this.response = httpServletResponse;     
	    }
	
	public String listProducts()
	{
		
		/*		log.debug("Inside listProducts() method of ProductManagerAction");
		String strmethod = request.getParameter("method");
		try {
			if(strmethod!=null)
			{
				getSession().remove("products");
				return SUCCESS;
			}
			String strSrch = request.getParameter("searchString");
			String customerId = String.valueOf(UserUtil.getMmrUser().getCustomerId());
			log.debug("Search string is : " + strSrch);
			if(customerId==null) //don't return any addresses, this should not happen
				return SUCCESS;
					
			Products p = new Products();
			p.setProductDescription(strSrch);
			p.setCustomerId(Long.valueOf(customerId));
			
			List<Products> productsList = productManagerService.searchProducts(p,false);
					
			for(Products prods: productsList)
			{
					productSearchResult.put(prods.getProductDescription()+" - "+prods.getProductHarmonizedCode(),prods.getProductId());
			}
			
		} catch (Exception e) {
			log.debug("---------------Exception in ListProducts()..."+e);
		}*/
		
		/*String strmethod = request.getParameter("method");	
		Products products = getProducts();
		ProductLine productLine = getProductLine();
		Customer customer= new Customer();
		try {
			if(strmethod ==null)
			{
				getSession().remove("products");
				return SUCCESS;
			}
			productList = productManagerService.getProductsList(products);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;*/
		
			
		String strmethod = request.getParameter("method");
		try {
			/*if(strmethod!=null)
			{
				getSession().remove("products");
				return SUCCESS;
			}*/
			String strSrch = request.getParameter("searchString");
			String customerId = String.valueOf(UserUtil.getMmrUser().getCustomerId());
			log.debug("Search string is : " + strSrch);
			if(customerId==null) //don't return any addresses, this should not happen
				return SUCCESS;
					
			Products p = new Products();
			p.setProductDescription(strSrch);
			p.setCustomerId(Long.valueOf(customerId));
			
			productList = productManagerService.searchProducts(p,false);
					
			for(Products prods: productList)
			{
					productSearchResult.put(prods.getProductDescription()+" - "+prods.getProductHarmonizedCode(),prods.getProductId());
			}
			
		} catch (Exception e) {
			log.debug("---------------Exception in ListProducts()..."+e);
		}
		getSession().put("CountryList", MessageUtil.getCountriesList());
		return SUCCESS;
			
		}
		
	
	
	public String populateProductsList()
	{
		log.debug("Inside populateProductsList() method of ProductManagerAction");
		String strProductId = request.getParameter("productId");
		String strProductDesc = request.getParameter("product_desc");
		String strProductHCode = request.getParameter("product_hcode");
		String CID = String.valueOf(UserUtil.getMmrUser().getCustomerId());
		
		try {
			if(strProductId!=null && strProductId.trim().length()>0)
			{
				Products products = new Products();
				if(CID!=null)
					products.setCustomerId(Long.valueOf(CID));
				if(strProductDesc!=null)
					products.setProductDescription(strProductDesc);
				if(strProductHCode!=null)
					products.setProductHarmonizedCode(strProductHCode);
				if(strProductId!=null)
					products.setProductId(Long.valueOf(strProductId));
				
				List<Products> productList = productManagerService.searchProducts(products,true);
			
			
				for(Products prods: productList)
				{
					products.setProductHarmonizedCode(prods.getProductHarmonizedCode());
					products.setProductDescription(prods.getProductDescription());
					products.setUnitPrice(prods.getUnitPrice());
					products.setOriginCountry(prods.getOriginCountry());
				}
				this.setProducts(products);
			}
		} catch (Exception e) {
			log.debug("---------------Exception in populateProductsList()..."+e);
		}
		return SUCCESS;
	}
	
	public String newProduct()
	{
		log.debug("Inside newProduct() method of ProductManagerAction");
		try {
			getSession().remove("edit");
			getSession().remove("products");
			Products products = new Products();
			String CustomerId = String.valueOf(UserUtil.getMmrUser().getCustomerId());
			products.setCustomerId(Long.valueOf(CustomerId));
			
			UserDAO  userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
			               List<UnitOfMeasure> uom = userDAO.unitOfMeasure();
			               /*User unitofmeasure = userDAO.findunitofmeasure(user1.getUsername());
			               if (user1 != null && unitofmeasure.getUnitmeasure() == 2) {
			                 for (int i = 0; i < uom.size(); i++) {
			                   if (unitofmeasure != null
			                       && unitofmeasure.getUnitmeasure() == uom.get(i).getUnitOfMeasureId()) {
			                     Collections.swap(uom, 0, i);
			                   }
			                 }
			               }*/
			               getSession().put("UOM", uom);
			
		} catch (Exception e) {
			log.debug("---------------Exception in newProduct()..."+e);
		}
		getSession().put("CountryList", MessageUtil.getCountriesList());
		return SUCCESS;
	}
	
	public String deleteProduct()
	{
		log.debug("Inside deleteProduct() method of ProductManagerAction");	
				
		String strPid = request.getParameter("productId");
		try {
			Products products = getProducts();
			products.setProductId(Long.valueOf(strPid));
			
			productManagerService.deleteProducts(products);
			
			addActionMessage(getText("product.delete.successfully"));
			
		} catch (Exception e) {
			log.debug("---------------Exception in deleteProduct()..."+e);
			addActionError(getText("product.delete.failed"));
		}
		
		return getProductsList();
	}
	
	public String getProductsList()
	{
		log.debug("Inside getProductsList() method of ProductManagerAction");
		String strmethod = request.getParameter("method");	
		Products products = getProducts();
		ProductLine productLine = getProductLine();
		Customer customer= new Customer();
		
		try {
			
			if(request.getParameter("cid")!=null && !request.getParameter("cid").toString().equals("null"))
			{
				long lCustomerId = Long.valueOf(request.getParameter("cid").toString());
				if(lCustomerId!=0)
				{
					products.setCustomerId(lCustomerId);
					customer=customerManagerService.getCustomerInfoByCustomerId(lCustomerId);
				}
				else
				{
					products.setCustomerId(UserUtil.getMmrUser().getCustomerId());
					customer=customerManagerService.getCustomerInfoByCustomerId(UserUtil.getMmrUser().getCustomerId());
				}
				getSession().put("customerName",customer.getName());
				request.setAttribute("cust_name", customer.getName());
			}
			productLine.setProductLineId(products.getProductLineId());
			productLineList = productManagerService.getProductLineList(productLine);
			for(ProductLine pl: productLineList)
			{
				productLine.setLineName(pl.getLineName());
			}
			this.setProducts(products);
			if(strmethod==null)		
				productList = productManagerService.getProductsList(products);
			else
				getSession().remove("products");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String editProduct()
	{
		log.debug("Inside editProduct() method of ProductManagerAction");	
		getSession().put("edit", "true");
		
		String strPid = request.getParameter("productId");
		
		try 
		{
			Products products = getProducts();
			products.setProductId(Long.valueOf(strPid));
			List<Products> productList = productManagerService.searchProducts(products,true);
			WarehouseLocation warehouseLocation = new WarehouseLocation();
			
			ProductLine productLine = getProductLine();
			Customer customer = new Customer();
			
			for(Products p: productList)
			{
				products.setProductName(p.getProductName());
				products.setProductDescription(p.getProductDescription());
				products.setProductHarmonizedCode(p.getProductHarmonizedCode());
				products.setProductCode(p.getProductCode());
				products.setUnitPrice(p.getUnitPrice());
				products.setOriginCountry(p.getOriginCountry());
				products.setPrimaryLocationId(p.getPrimaryLocationId());
				productLine.setProductLineId(p.getProductLineId());
				customer = customerManagerService.getCustomerInfoByCustomerId(p.getCustomerId());
			}
			
			/*List<ProductLine> productLineList = productManagerService.getProductLineListById(productLine);
			warehouseLocation.setLocationId(products.getPrimaryLocationId());
			List<WarehouseLocation> whloclist = warehouseManagerService.getWarehouseLocationInfoByLocationId(warehouseLocation);
			if(productLineList.size()>0)
			{
				request.setAttribute("plname", productLineList.get(0).getLineName());
				request.setAttribute("plnameid", productLineList.get(0).getProductLineId());
			}
			request.setAttribute("cname", customer.getName());
			request.setAttribute("cnameid", customer.getId());
			if(whloclist.size()>0)
			{
				request.setAttribute("primaryloc", whloclist.get(0).getLocationName()+" : "+whloclist.get(0).getWarehouse().getWarehouseName());
				request.setAttribute("primarylocId", whloclist.get(0).getLocationId());
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return SUCCESS;
	}
	
	public String addOrUpdateProducts()
	{
		log.debug("Inside addOrUpdateProducts() method of ProductManagerAction");
		long lcustomerId=0;
		long lProductLineId=0;
		long lPrimaryLocId=0;
		try {
			
				if(request.getParameter("cid")!=null && !request.getParameter("cid").toString().equals("null"))
					lcustomerId = Long.valueOf(request.getParameter("cid"));
				if(request.getParameter("plid")!=null && !request.getParameter("plid").toString().equals("null"))
					lProductLineId = Long.valueOf(request.getParameter("plid"));
				if(request.getParameter("primaryloc")!=null && !request.getParameter("primaryloc").toString().equals("null"))
					lPrimaryLocId = Long.valueOf(request.getParameter("primaryloc"));
				
				
				//Edit functionality: Editing an existing product
				if(getSession().get("edit")!=null)
				{
					Products products = getProducts();
					if(lcustomerId!=0)
						products.setCustomerId(lcustomerId);
					else
						products.setCustomerId(UserUtil.getMmrUser().getCustomerId());
					//ProductLine productLine = getProductLine();
					products.setProductLineId(lProductLineId);
					products.setPrimaryLocationId(lPrimaryLocId);
					
					productManagerService.addOrUpdate(products,false); //only edit, set to false if we are not adding
					
					addActionMessage(getText("product.save.successfully"));
				}
				//Add Functionality: Adding a new product
				else
				{
					Products products = getProducts();
					if(lcustomerId!=0)
						products.setCustomerId(lcustomerId);
					else
						products.setCustomerId(UserUtil.getMmrUser().getCustomerId());
					//ProductLine productLine = getProductLine();
					products.setProductLineId(lProductLineId);
					products.setPrimaryLocationId(lPrimaryLocId);
					
					productManagerService.addOrUpdate(products,true); // set to true to add a new product
					
					addActionMessage(getText("product.create.successfully"));
					
				}
				getSession().remove("products");
				getSession().remove("edit");
			
		} catch (Exception e) {
			log.debug("---------------Exception in addOrUpdateProducts()..."+e);
			addActionError(getText("product.save.failed"));
		}
		return getProductsList();
	}
	
	public String listProductLines()
	{
		log.debug("Inside listProductLines() method of ProductManagerAction");
		try {
			String strSearch = request.getParameter("searchString");
			ProductLine productLine = getProductLine();
			productLine.setLineName(strSearch);
			
			productLineList = productManagerService.getProductLineList(productLine);
			
			for(ProductLine pl: productLineList)
			{
				productLineSearchResult.put(pl.getLineName(), pl.getProductLineId());
			}
			
		} catch (Exception e) {
			log.debug("---------------Exception in listProductLines()..."+e);
		}
		return SUCCESS;
	}
	
	public String listProductsByProductLine()
	{
		log.debug("Inside listProductsByProductLine() method of ProductManagerAction");
		long lprodlinekey=0;
		long lCustomerId = 0;
		try 
		{
			String strSearch = request.getParameter("searchString");
			lCustomerId = UserUtil.getMmrUser().getCustomerId();
			Products products = getProducts();
			products.setProductName(strSearch);
			products.setCustomerId(lCustomerId);
			
			if(request.getParameter("productLineId")!=null)
				lprodlinekey = Long.valueOf(request.getParameter("productLineId")+"");
			products.setProductLineId(lprodlinekey);
			
			productList = productManagerService.getProductsByProductLineId(products);
			for(Products p: productList)
			{
				productByProductLineSearchResult.put(p.getProductName(), p.getProductId());
			}
		} 
		catch (Exception e) 
		{
			log.debug("---------------Exception in listProductsByProductLine()..."+e);
		}
		return SUCCESS;
		
	}
	
	public String showProductLine()
	{
		log.debug("Inside showProductLine() method of ProductManagerAction");
		getSession().remove("edit");
		getSession().remove("productline");
		String strRole = UserUtil.getMmrUser().getUserRole();
		ProductLine productLine = getProductLine();
		Customer customer = new Customer();
		try {
			
			if(strRole.equals("busadmin"))
			{
				if(request.getParameter("cid")!=null)
				{
					long lCustomerId = Long.valueOf(request.getParameter("cid").toString());
					productLine.setCustomerId(lCustomerId);
					customer = customerManagerService.getCustomerInfoByCustomerId(lCustomerId);
					getSession().put("customerName",customer.getName());
				}
			}
			else
			{
				long lCustomerId = UserUtil.getMmrUser().getCustomerId();
				productLine.setCustomerId(lCustomerId);
				customer = customerManagerService.getCustomerInfoByCustomerId(lCustomerId);
				getSession().put("customerName",customer.getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
		productLineList = productManagerService.getProductLineList(productLine);
		
		return SUCCESS;
	}
	
	public String showEditProductLines()
	{
		log.debug("Inside showEditProductLines() method of ProductManagerAction");
		try {
			getSession().put("edit", "true");
			
			long lProductLineId = Long.valueOf(request.getParameter("productLineId"));
			ProductLine productLine = getProductLine();
			productLine.setProductLineId(lProductLineId);
			
			List<ProductLine> plist = productManagerService.getProductLineListById(productLine);
			
			for(ProductLine pl: plist)
			{
				productLine.setLineCode(pl.getLineCode());
				productLine.setLineName(pl.getLineName());
				productLine.setLineDescription(pl.getLineDescription());
			}
			
		} catch (Exception e) {
			log.debug("---------------Exception in showEditProductLines()..."+e);
		}
		return SUCCESS;
	}
	
	public String editProductLines()
	{
		log.debug("Inside editProductLines() method of ProductManagerAction");
		try {
			boolean boolEdit = Boolean.valueOf(request.getParameter("edit"));
			String strRole = UserUtil.getMmrUser().getUserRole();
			ProductLine productLine = getProductLine();
			//reset
			//resetProductLine(productLine);
			if(boolEdit) // Edit existing productLine
			{
				long lProductLineId = Long.valueOf(request.getParameter("productLineId"));
				
				productLine.setProductLineId(lProductLineId);
			}
			
			if(strRole.equals("busadmin"))
			{
				if(request.getParameter("cid")!=null)
				{
					long lCustomerId = Long.valueOf(request.getParameter("cid").toString());
					productLine.setCustomerId(lCustomerId);
				}
			}
			else
			{
				long lCustomerId = UserUtil.getMmrUser().getCustomerId();
				productLine.setCustomerId(lCustomerId);
			}
			
			productManagerService.addOrUpdateProductLine(productLine, boolEdit);
			
			getSession().remove("edit");
			getSession().remove("productline");
			if(boolEdit)
				addActionMessage(getText("productLine.save.successfully"));
			else
				addActionMessage(getText("productLine.create.successfully"));
			
		} catch (Exception e) {
			log.debug("---------------Exception in editProductLines()..."+e);
			addActionError(getText("productLine.save.failed"));
		}
		return getProductLinesList();
	}
	
	public String deleteProductLines()
	{
		log.debug("Inside deleteProductLines() method of ProductManagerAction");
		try {
			String strRole = UserUtil.getMmrUser().getUserRole();
			ProductLine productLine = getProductLine();
			
			long lProductLineId = Long.valueOf(request.getParameter("productLineId"));
			productLine.setProductLineId(lProductLineId);
			
			if(strRole.equals("busadmin"))
			{
				if(request.getParameter("cid")!=null)
				{
					long lCustomerId = Long.valueOf(request.getParameter("cid").toString());
					productLine.setCustomerId(lCustomerId);
				}
			}
			else
			{
				long lCustomerId = UserUtil.getMmrUser().getCustomerId();
				productLine.setCustomerId(lCustomerId);			
			}		
			
			productManagerService.deleteProductLine(productLine);
			
			addActionMessage(getText("productLine.deleted.successfully"));
		} catch (Exception e) {
			log.debug("---------------Exception in deleteProductLines()..."+e);
			addActionError(getText("productLine.deleted.failed"));
		}
		 
		return getProductLinesList();
	}
	
	public String getProductLinesList()
	{
		log.debug("Inside getProductsList() method of ProductManagerAction");
		String strmethod = request.getParameter("method");	
		ProductLine productLine = getProductLine();
		Customer customer= new Customer();
		try {
			
			if(request.getParameter("cid")!=null)
			{
				long lCustomerId = Long.valueOf(request.getParameter("cid").toString());
				productLine.setCustomerId(lCustomerId);
				customer=customerManagerService.getCustomerInfoByCustomerId(lCustomerId);
				getSession().put("customerName",customer.getName());
			}
			this.setProductLine(productLine);
			if(strmethod==null)		
				productLineList = productManagerService.getProductLineList(productLine);
			else
				getSession().remove("productline");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String listPackages()
	{
		
		log.debug("Inside listPackages() method of ProductManagerAction");
		String strmethod = request.getParameter("method");
		try {
			
			String customerId = String.valueOf(UserUtil.getMmrUser().getCustomerId());
			
			if(customerId==null) //don't return any addresses, this should not happen
				return SUCCESS;
					
			PackageTypes pt = new PackageTypes();
			pt.setCustomerId(Long.valueOf(customerId));
			
			packageTypesList = productManagerService.searchPackageTypes(pt);
					
			
		} catch (Exception e) {
			log.debug("---------------Exception in listPackages()..."+e);
		}
		return SUCCESS;
		
	}
	
	public String populatePackageTypes()
	{
		log.debug("Inside populatePackageTypes() method of ProductManagerAction");
		
		String strIndex = request.getParameter("index");
		String customerId = String.valueOf(UserUtil.getMmrUser().getCustomerId());
		
		PackageTypes pt = new PackageTypes();
		pt.setCustomerId(Long.valueOf(customerId));
		
		packageTypesList = productManagerService.searchPackageTypes(pt);
		PackageTypes packageTypes = getPackageTypes();
		for(PackageTypes ptypes : packageTypesList)
		{
			if(ptypes.getPackageTypeId()==Long.valueOf(strIndex))
			{
				packageTypes.setPackageDesc(ptypes.getPackageDesc());
				packageTypes.setPackageLength(ptypes.getPackageLength());
				packageTypes.setPackageWidth(ptypes.getPackageWidth());
				packageTypes.setPackageHeight(ptypes.getPackageHeight());
				packageTypes.setPackageWeight(ptypes.getPackageWeight());
			}
		}
		return SUCCESS;
	}
	
	public String goToAddNewPackage()
	{
		log.debug("Inside goToAddNewPackage() method of ProductManagerAction");
		getSession().remove("packageTypes");
		getSession().remove("edit");
		 UserDAO  userDAO = (UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
		            List<UnitOfMeasure> uom = userDAO.unitOfMeasure();
		            getSession().put("UOM", uom);
		return SUCCESS;
	}
	
	public String editPackageType()
	{
		log.debug("Inside editPackageType() method of ProductManagerAction");
		getSession().put("edit", true);
		
		String packageTypeId = request.getParameter("pid");
		PackageTypes packageTypes = getPackageTypes();
		packageTypesList = productManagerService.fetchAPackageById(Long.valueOf(packageTypeId));
		for(PackageTypes pt : packageTypesList)
		{
			packageTypes.setPackageTypeId(pt.getPackageTypeId());
			packageTypes.setPackageName(pt.getPackageName());
			packageTypes.setPackageDesc(pt.getPackageDesc());
			packageTypes.setPackageLength(pt.getPackageLength());
			packageTypes.setPackageWidth(pt.getPackageWidth());
			packageTypes.setPackageHeight(pt.getPackageHeight());
			packageTypes.setPackageWeight(pt.getPackageWeight());
		}
		setPackageTypes(packageTypes);
		return SUCCESS;
	}
	
	public String deletePackageType()
	{
		log.debug("Inside deletePackageType() method of ProductManagerAction");
		boolean deleted = false;
		String packageTypeId = request.getParameter("pid");
		deleted = productManagerService.deletePackageType(Long.valueOf(packageTypeId));
		if(deleted)
			addActionMessage(MessageUtil.getMessage("packagetype.delete.successfully"));
		
		return listPackages();
	}
	
	public String addOrUpdatePackageTypes()
	{
		log.debug("Inside addOrUpdatePackageTypes() method of ProductManagerAction");
		try {
				PackageTypes packageTypes = getPackageTypes();
				packageTypes.setCustomerId(UserUtil.getMmrUser().getCustomerId());
			
				//Edit functionality: Editing an existing pacakge type
				if(request.getParameter("edit")!=null)
				{
					productManagerService.addOrUpdatePackageTypes(packageTypes,false); //only edit, set to false if we are not adding
					
					addActionMessage(MessageUtil.getMessage("packagetype.save.successfully"));
				}
				//Add Functionality: Adding a new package type
				else
				{
					productManagerService.addOrUpdatePackageTypes(packageTypes,true); // set to true to add a new product
					
					addActionMessage(MessageUtil.getMessage("packagetype.create.successfully"));
					getSession().remove("packageTypes");
					getSession().remove("edit");
				}
			
		} catch (Exception e) {
			log.debug("---------------Exception in addOrUpdateProducts()..."+e);
			addActionError(getText("product.save.failed"));
		}
		return listPackages();
	}
		
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public ProductManager getProductManagerService() {
		return productManagerService;
	}

	public void setProductManagerService(ProductManager productManagerService) {
		this.productManagerService = productManagerService;
	}

	public Map<String, Long> getProductSearchResult() {
		return productSearchResult;
	}

	public void setProductSearchResult(Map<String, Long> productSearchResult) {
		this.productSearchResult = productSearchResult;
	}
	
	public Products getProducts() {
		Products product = (Products)getSession().get("products");
		if (product == null) {
			product = new Products();
			product.setCustomerId(UserUtil.getMmrUser().getCustomerId());
			setProducts(product);
		}
		return product;
	}
	
	public ProductLine getProductLine()
	{
		ProductLine productLine = (ProductLine) getSession().get("productline");
		if(productLine == null)
		{
			productLine = new ProductLine();
			productLine.setCustomerId(UserUtil.getMmrUser().getCustomerId());
			setProductLine(productLine);
		}
		return productLine;
	}
	
	public void setProducts(Products products) 
	{
		getSession().put("products", products);
	}
	
	public void setProductLine(ProductLine productLine) 
	{
		getSession().put("productline", productLine);
	}
	
	
	public PackageTypes getPackageTypes()
	{
		PackageTypes packageTypes = (PackageTypes) getSession().get("packageTypes");
		if(packageTypes == null)
		{
			packageTypes = new PackageTypes();
			packageTypes.setCustomerId(UserUtil.getMmrUser().getCustomerId());
			setPackageTypes(packageTypes);
		}
		return packageTypes;
	}
	
	public void setPackageTypes(PackageTypes packageTypes) 
	{
		getSession().put("packageTypes", packageTypes);
	}
			
	
	public List<Products> getProductList()
	{
		return productList;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public List<ProductLine> getProductLineList() {
		return productLineList;
	}

	public void setProductLineList(List<ProductLine> productLineList) {
		this.productLineList = productLineList;
	}

	public CustomerManager getCustomerManagerService() {
		return customerManagerService;
	}

	public void setCustomerManagerService(CustomerManager customerManagerService) {
		this.customerManagerService = customerManagerService;
	}

	public Map<String, Long> getProductLineSearchResult() {
		return productLineSearchResult;
	}

	public void setProductLineSearchResult(Map<String, Long> productLineSearchResult) {
		this.productLineSearchResult = productLineSearchResult;
	}

	public Map<String, Long> getProductByProductLineSearchResult() {
		return productByProductLineSearchResult;
	}

	public void setProductByProductLineSearchResult(
			Map<String, Long> productByProductLineSearchResult) {
		this.productByProductLineSearchResult = productByProductLineSearchResult;
	}

	public List<PackageTypes> getPackageTypesList() {
		return packageTypesList;
	}

	public void setPackageTypesList(List<PackageTypes> packageTypesList) {
		this.packageTypesList = packageTypesList;
	}
	
	
	
	public String printproducts(){
		
		log.debug("Inside getProductsList() method of ProductManagerAction");
		String type = request.getParameter("type");	
		String strmethod = request.getParameter("method");	
		Products products = getProducts();
		ProductLine productLine = getProductLine();
		Customer customer= new Customer();
		try {
			
			if(request.getParameter("cid")!=null && !request.getParameter("cid").toString().equals("null"))
			{
				long lCustomerId = Long.valueOf(request.getParameter("cid").toString());
				if(lCustomerId!=0)
				{
					products.setCustomerId(lCustomerId);
					customer=customerManagerService.getCustomerInfoByCustomerId(lCustomerId);
				}
				else
				{
					products.setCustomerId(UserUtil.getMmrUser().getCustomerId());
					customer=customerManagerService.getCustomerInfoByCustomerId(UserUtil.getMmrUser().getCustomerId());
				}
				getSession().put("customerName",customer.getName());
				request.setAttribute("cust_name", customer.getName());
			}
			productLine.setProductLineId(products.getProductLineId());
			productLineList = productManagerService.getProductLineList(productLine);
			for(ProductLine pl: productLineList)
			{
				productLine.setLineName(pl.getLineName());
			}
			this.setProducts(products);
				
				List<Products> prod= productManagerService.getProductsList(products);
				if("xml".equalsIgnoreCase(type)){
					String shippingLabelFileName = getUniqueTempxmlFileName("products");
					write_XML_File(prod,shippingLabelFileName);
					response.setContentType("application/xml");
					response.setHeader("Content-Disposition",
							"attachment;filename=products.xml");
					File xmlFile = new File(shippingLabelFileName);
					FileInputStream fileInputStream = new FileInputStream(xmlFile);
					/*ServletContext ctx =ServletActionContext.getServletContext();
					InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
					int read=0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
				 
					while((read = fileInputStream.read(bytes))!= -1){
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();	
				}else if("csv".equalsIgnoreCase(type)){
					
					String shippingLabelFileName = getUniqueTempcsvFileName("products");
					
					FileWriter writer = new FileWriter(shippingLabelFileName);
					generateCsvFile(prod,writer);
					response.setContentType("application/csv");
					response.setHeader("Content-Disposition",
							"attachment;filename=products.csv");
					File csvFile = new File(shippingLabelFileName);
					FileInputStream fileInputStream = new FileInputStream(csvFile);
					/*ServletContext ctx =ServletActionContext.getServletContext();
					InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
					int read=0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
				 
					while((read = fileInputStream.read(bytes))!= -1){
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();	
				  
				}else if("xl".equalsIgnoreCase(type)){
					String shippingLabelFileName = getUniqueTempxlFileName("products");
					
					createxlfile(prod,shippingLabelFileName);
					response.setContentType("application/msexcel");
					response.setHeader("Content-Disposition",
							"attachment;filename=products.xls");
					File xlFile = new File(shippingLabelFileName);
					FileInputStream fileInputStream = new FileInputStream(xlFile);
					/*ServletContext ctx =ServletActionContext.getServletContext();
					InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
					int read=0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
				 
					while((read = fileInputStream.read(bytes))!= -1){
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();	
				}
					
				 
				
				
			
		}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return SUCCESS;
		
		
		
		
		
		
	}
	
	 private ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public  void write_XML_File(List<Products> productList,String shippingLabelFileName){
		 
		ArrayList<String> srcList = new ArrayList<String>();
		  
		 DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
		 DocumentBuilder builder=null;
		try {
			builder = docBuilder.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		 Document doc = builder.newDocument();
		 Element shipingList = doc.createElement("shippings");
		 doc.appendChild(shipingList);
			for(Products product :productList){
		  
	
		         Element log1 = doc.createElement("Products");
		         shipingList.appendChild(log1);
	
		         /*Attr attr = doc.createAttribute("id");
		         attr.setValue(removeNull(Long.toString(sOrder.getId())));*/
		         
		         //.appendChild(log1);
		         
		         
		         Element name = doc.createElement("ProductName");
		         
		         name.appendChild(doc.createTextNode(removeNull(product.getProductName())));
		         log1.appendChild(name);
		         
		         Element productdesc = doc.createElement("ProductDescription");
		         productdesc.appendChild(doc.createTextNode(removeNull(product.getProductDescription())));
		         log1.appendChild(productdesc);
	
		         Element Harmonizedcode = doc.createElement("HarmonizedCode");
		         Harmonizedcode.appendChild(doc.createTextNode(removeNull(product.getProductHarmonizedCode())));
		         log1.appendChild(Harmonizedcode);
	
		         Element ocountry = doc.createElement("OriginCountry");
		         ocountry.appendChild(doc.createTextNode(removeNull(product.getOriginCountry())));
		         log1.appendChild(ocountry);
		         
		         Element unitprice = doc.createElement("UnitPrice");
		         unitprice.appendChild(doc.createTextNode(removeNull(String.valueOf(product.getUnitPrice()))));
		         log1.appendChild(unitprice);
		}
			try{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	          Transformer transformer = transformerFactory.newTransformer();
	           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	           DOMSource source = new DOMSource(doc);
	           StreamResult result = new StreamResult(new File(shippingLabelFileName));
	           
	           transformer.transform(source, result);
	           
			}catch(Exception e){
				e.printStackTrace();
			}
			
			System.out.println("File saved for product!");
	 }
	 
	 
	 private  void generateCsvFile(List<Products> productList,FileWriter writer)
	   {
		try
		{
			ArrayList<String> srcList = new ArrayList<String>();
			
		    
		    writer.append("ProductName");
		    writer.append(',');
		    writer.append("ProductDescription");
		    writer.append(',');
		    writer.append("HarmonizedCode");
		    writer.append(',');
		    writer.append("OriginCountry");
		    writer.append(',');
		    writer.append("UnitPrice");
		    writer.append('\n');
		    
		    for(Products pList :productList){
		    
	 
		    writer.append(removeNull(pList.getProductName()));
		    writer.append(',');
	        writer.append(removeNull(pList.getProductDescription()));
	        writer.append(',');
	        writer.append(removeNull(pList.getProductHarmonizedCode()));   
	        writer.append(',');
	        writer.append(removeNull(pList.getOriginCountry())); 
	        writer.append(',');
	        writer.append(removeNull(String.valueOf(pList.getUnitPrice()))); 
	        writer.append('\n');
	    
		 
	 
		    //generate whatever data you want
	 
		   
		}
		   
		    
		  
							
		writer.flush();
	    writer.close();
		   
	    System.out.println("csv generated successfully");
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	    }
	 
	 
	 public void createxlfile(List<Products> productList,String shippingLabelFileName) throws IOException{
		
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
        HSSFRow rowhead=   sheet.createRow((short)0);
        rowhead.createCell((short) 0).setCellValue("Name");
        rowhead.createCell((short) 1).setCellValue("Description");
        rowhead.createCell((short) 2).setCellValue("HCode");
        rowhead.createCell((short) 3).setCellValue("OCountry");
        rowhead.createCell((short) 4).setCellValue("Unit Price");
        int i=1;
        for(Products pList :productList){
        	
        HSSFRow row=   sheet.createRow((short)i);
        row.createCell((short) 0).setCellValue(removeNull(pList.getProductName()));
        row.createCell((short) 1).setCellValue(removeNull(pList.getProductDescription()));
        row.createCell((short) 2).setCellValue(removeNull(pList.getProductHarmonizedCode()));
        row.createCell((short) 3).setCellValue(removeNull(pList.getOriginCountry()));
        row.createCell((short) 4).setCellValue(removeNull(String.valueOf(pList.getUnitPrice())));
        i++;
		 }
        
        FileOutputStream fileOut =  new FileOutputStream(shippingLabelFileName);
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("Your excel file has been generated!");
        

    }
	 
	 
		private String removeNull(String text){
			if(null==text){
				return "";
			}
			return text;

	}
		
		public String getUniqueTempcsvFileName(String fName)
		{
			Date curDateTime = new Date();
			String tempPath = System.getProperty("java.io.tmpdir");
			File path = new File( tempPath );
			if ( !path.exists() )
				path.mkdirs();
			
			return tempPath + File.separator + fName + curDateTime.getTime() + ".csv";
		}	 
		
		public String getUniqueTempxmlFileName(String fName)
		{
			Date curDateTime = new Date();
			String tempPath = System.getProperty("java.io.tmpdir");
			File path = new File( tempPath );
			if ( !path.exists() )
				path.mkdirs();
			
			return tempPath + File.separator + fName + curDateTime.getTime() + ".xml";
		}	 
		
		public String getUniqueTempxlFileName(String fName)
		{
			Date curDateTime = new Date();
			String tempPath = System.getProperty("java.io.tmpdir");
			File path = new File( tempPath );
			if ( !path.exists() )
				path.mkdirs();
			
			return tempPath + File.separator + fName + curDateTime.getTime() + ".xls";
		}
		
		

   public String synchShopifyProduct(){
           EcommerceDAO eCommerceDAO=(EcommerceDAO)MmrBeanLocator.getInstance().findBean("eCommerceDAO");
         long customerId=0;
           if(UserUtil.getMmrUser()!=null){
             customerId=UserUtil.getMmrUser().getCustomerId();
           } 
           if(customerId!=0){
             
             EcommerceStore store=eCommerceDAO.getEcommrceStoreByCustomer(customerId);
             if(store!=null){
               ShopifyProduct products=ShopifyUtil.synchProducts(store.getAccessKey(),store.getUrl());
               if(products!=null){
                 products.setCustomerId(customerId);
                 productManagerService.sycnShopifyProducts(products);
                 return  listProducts();
               }
             }
           }
           return SUCCESS;
         }
         
         public String packageMap(){
         String customerId = String.valueOf(UserUtil.getMmrUser().getCustomerId());
         if(customerId==null) //don't return any addresses, this should not happen
             return SUCCESS;
           Products p = new Products();
           p.setCustomerId(Long.valueOf(customerId));
           productList = productManagerService.searchProducts(p,false);
           PackageTypes pt = new PackageTypes();
           pt.setCustomerId(Long.valueOf(customerId));
           packageTypesList = productManagerService.searchPackageTypes(pt);
           ProductPackageMap Packagemap=new ProductPackageMap();
           Packagemap.setCustomerId(Long.parseLong(customerId));
           Packagemap.setProductId(null);
           productPackageMapList=productManagerService.searchProductPackageMap(Packagemap);
           return SUCCESS;
         }
     
         public String addPackageMap(){
           String edit=(String) getSession().get("editPackageMap");  
           if(edit==null){
             if(this.productPackageMap!=null){
               this.productPackageMap.setCustomerId(UserUtil.getMmrUser().getCustomerId());
                List<ProductPackageMap> ppcMap=productManagerService.searchProductPackageMap(this.productPackageMap);
                if(ppcMap!=null && ppcMap.size()>0){
                  packageMap();
                  addActionError("This product Already Added");
                  productPackageMap=new ProductPackageMap();
                  return INPUT;
                }
                addActionMessage(MessageUtil.getMessage("packageMap.save.successfully"));
                productManagerService.addPackageMap(productPackageMap);
             }
           }else if(edit!=null && edit.equals("true")){
                Long productPackageId=(Long) getSession().get("packageMapId1");
                this.productPackageMap.setProductPackageId(productPackageId);
                addActionMessage(MessageUtil.getMessage("packageMap.update.successfully"));
                productManagerService.updateProductPackageMap(this.productPackageMap);
              getSession().remove("editPackageMap");
              getSession().remove("packageMapId1");
           }
           packageMap();
           return SUCCESS;
         }
         public String deletePackageMap(){
            String packageMapId=request.getParameter("PackageMapId");
              productManagerService.deleteProductPackageMap(Long.parseLong(packageMapId));
            packageMap();
            addActionMessage("Package Map Deleted SuccessFully.");
           return SUCCESS;
         }
         @SuppressWarnings("unchecked")
         public String editPackageMap(){
           
                 String packageMapId=request.getParameter("PackageMapId");
                 getSession().put("packageMapId1",Long.parseLong(packageMapId));
                 packageMap();
                 this.productPackageMap=productManagerService.getProductPackageMapById(Long.parseLong(packageMapId));
                 getSession().put("editPackageMap", "true");
           return SUCCESS;
         }
         public List<ProductPackageMap> getProductPackageMapList() {
           return productPackageMapList;
         }
     
         public void setProductPackageMapList(List<ProductPackageMap> productPackageMapList) {
           this.productPackageMapList = productPackageMapList;
         }
     
         public ProductPackageMap getProductPackageMap() {
           return productPackageMap;
         }
     
         public void setProductPackageMap(ProductPackageMap productPackageMap) {
           this.productPackageMap = productPackageMap;
         }
       
   

}