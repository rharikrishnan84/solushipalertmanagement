package com.meritconinc.shiplinx.api;

import java.io.IOException;
import java.security.InvalidKeyException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.meritconinc.shiplinx.api.Util.ShopifyUtil;
import com.opensymphony.xwork2.util.ResolverUtil.IsA;

/**
 * Servlet Filter implementation class ShopifyAPIAccessFilter
 */
public class ShopifyAPIAccessFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ShopifyAPIAccessFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		  boolean isValid =true;
	/*	try {
			   // isValid = ShopifyUtil.authedicateRequest(request);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(isValid){
			chain.doFilter(request, response);
			System.out.println("secure");
		}else{
			System.out.println("HACKER access");
		}
			
		//
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
