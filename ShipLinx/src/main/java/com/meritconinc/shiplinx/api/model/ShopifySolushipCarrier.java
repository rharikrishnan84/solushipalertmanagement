package com.meritconinc.shiplinx.api.model;


public class ShopifySolushipCarrier {
	 
	    private String id; 
	   
	    private String name;

	    private String callback_url;

	    private String format;

	    private String service_discovery;
	    
	    private String carrier_service_type;
	    private Boolean active;
	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public String getCallback_url ()
	    {
	        return callback_url;
	    }

	    public void setCallback_url (String callback_url)
	    {
	        this.callback_url = callback_url;
	    }

	    public String getFormat ()
	    {
	        return format;
	    }

	    public void setFormat (String format)
	    {
	        this.format = format;
	    }

	    public String getService_discovery ()
	    {
	        return service_discovery;
	    }

	    public void setService_discovery (String service_discovery)
	    {
	        this.service_discovery = service_discovery;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [name = "+name+", callback_url = "+callback_url+", format = "+format+", service_discovery = "+service_discovery+"]";
	    }

		public String getCarrier_service_type() {
			return carrier_service_type;
		}

		public void setCarrier_service_type(String carrier_service_type) {
			this.carrier_service_type = carrier_service_type;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

 
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
