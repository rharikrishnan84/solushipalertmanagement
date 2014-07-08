package com.meritconinc.shiplinx.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TimeZone;

public final class TimeZonesFactory
{
  // hardcode this for now
  private final static String[] supportedRegions =
  {
//    "Africa",
//    "America",
//    "Antarctica",
//    "Arctic",
    "Asia",
//    "Atlantic",
//    "Australia",
//    "Brazil",
    "Canada",
//    "Chile",
//    "Europe",
//    "Indian",
//    "Mexico",
//    "Mideast",
//    "Pacific"
			"US"
  };

//  private static Collection supportedTimeZones = createSupportedTimeZones();

 
  public static String[] getSupportedRegions()
  {
    return supportedRegions;
  }

  public static Collection getSupportedTimeZones()
  {
    return createSupportedTimeZones();
  }

  private static Collection createSupportedTimeZones()
  {
    // get all Java TimeZone IDs & sort it
    String[] time_zone_ids = TimeZone.getAvailableIDs();
    Arrays.sort(time_zone_ids);
    
    Collection supported_regions = Arrays.asList(supportedRegions);
    Collection supported_zones   = new ArrayList();

    // create a collection of supported TimeZone
    for (int i = 0; i < time_zone_ids.length; i++)
    {
      int slash_position = time_zone_ids[i].indexOf("/");
      if (slash_position > 0)
      {
        // get Region name
        String time_zone_id = time_zone_ids[i];
        String region       = time_zone_id.substring(0, slash_position);
        // check if Region is supported
        if (supported_regions.contains(region))
        {
          // create custom Display Name, eg Canada/Pacific (PST)
          String time_zone_name = time_zone_id + " (" +
            TimeZone.getTimeZone(time_zone_id).getDisplayName(false, TimeZone.SHORT)
            + ")";

          // add this TimeZone
          TimeZoneBean time_zone = new TimeZoneBean(time_zone_id, time_zone_name);
          supported_zones.add(time_zone);
        }
      }
    }
    supported_zones.add(new TimeZoneBean("GMT", "Greenwich Mean Time (GMT)"));
    return supported_zones;
  }
}
