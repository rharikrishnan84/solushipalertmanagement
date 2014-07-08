package com.meritconinc.mmr.common;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.meritconinc.mmr.model.common.PropertyVO;
import com.meritconinc.mmr.service.PropertyService;

public class DbPropertiesFactoryBean implements FactoryBean, InitializingBean {
  private PropertyService propertyService;

  private Properties properties;

  public void setPropertyService(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  public void afterPropertiesSet() {
    // use the "dataSource" to read in properties
    // store the "properties" in the instance variable
    properties = new Properties();
    List<PropertyVO> props = propertyService.readProperties();
    for (PropertyVO propVO : props) {
      String propName = propVO.getScope() + "." + propVO.getName();
      properties.setProperty(propName, propVO.getDbValue());
    }
  }

  public Object getObject() {
    return this.properties;
  }

  public Class getObjectType() {
    return Properties.class;
  }

  public boolean isSingleton() {
    return true;
  }
}
