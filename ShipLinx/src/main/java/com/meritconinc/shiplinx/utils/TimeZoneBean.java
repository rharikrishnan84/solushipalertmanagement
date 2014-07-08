package com.meritconinc.shiplinx.utils;

import java.io.Serializable;

public class TimeZoneBean implements Serializable
{
  // Java TimeZone ID
  private String id;
  // custom display name
  private String name;

/*
  // long style display name, eg Pacific Standard Time
  private String longDisplayName;
  // short style display name, eg PST
  private String shortDisplayName;
*/

  TimeZoneBean()
  {
  }

  TimeZoneBean(String id, String name)
  {
    setId(id);
    setName(name);
  }

  public void setId(String id)
  { 
    this.id = id;
  }
    
  public String getId()
  {
    return this.id;
  }
    
  public void setName(String name)
  {
    this.name = name;
  }
    
  public String getName()
  {
    return this.name;
  }
  
}
