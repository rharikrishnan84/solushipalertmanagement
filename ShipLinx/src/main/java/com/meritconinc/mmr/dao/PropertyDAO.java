package com.meritconinc.mmr.dao;

import java.util.List;

import com.meritconinc.mmr.model.common.PropertyVO;

public interface PropertyDAO {

  /**
   * 
   * @param scope
   * @param name
   * @return
   */
  public String readProperty(String scope, String name);

  public List<PropertyVO> readProperties();

  public void updateProperty(String scope, String name, String value);

  public String readTextProperty(String scope, String name);

  public List<PropertyVO> readTextProperties();

  public void updateTextProperty(String scope, String name, String value);
  
  public PropertyVO getPath(String language);

}