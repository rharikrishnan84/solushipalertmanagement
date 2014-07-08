package com.meritconinc.mmr.web;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.PropertyDAO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;

public class RolesAccessorFactory {
	public static RolesAccessor getRolesAccessorImplementation() {
		// by now
		// possible implementations:
		// RolesAccessorXMLImpl, RolesAccessorDbImpl
		// the choice is configurable from database
		// default is XML configuration
		PropertyDAO propertyDAO = (PropertyDAO)MmrBeanLocator.getInstance().findBean("propertyDAO");
		String tagConfigImpl = propertyDAO.readProperty(Constants.SYSTEM_SCOPE, "SECTION_TAG_IMPLEMENTATION");
		if (tagConfigImpl == null){
			return new RolesAccessorXMLImpl(); //default
		}
		if (tagConfigImpl.equalsIgnoreCase("db")){
			return new RolesAccessorDbImpl();
		}
		else if (tagConfigImpl.equalsIgnoreCase("xml")){
			return new RolesAccessorXMLImpl();
		}
		else {
			return new RolesAccessorXMLImpl(); //default
		}
	} 
}
