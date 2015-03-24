package com.meritconinc.mmr.dao.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.MessageDAO;
import com.meritconinc.mmr.model.common.CountryVO;
import com.meritconinc.mmr.model.common.LocaleVO;
import com.meritconinc.mmr.utilities.security.UserUtil;

public class MessageDAOImpl extends SqlMapClientDaoSupport implements MessageDAO {

  /**
   * Get message template
   * 
   * @param messageId
   * @param locale
   * @return message template
   */
  public String getMessage(String messageId, String locale) {
    Map map = new HashMap();
    map.put("messageId", messageId);
    map.put("locale", locale);
    return (String) getSqlMapClientTemplate().queryForObject("getMessage", map);
  }

  public List<LocaleVO> getLocales() {
    List<String> ret = new ArrayList<String>();
    List<LocaleVO> localeVOs = getSqlMapClientTemplate().queryForList("getLocales");
    return localeVOs;
  }

  public List<LocaleVO> getLanguagesByLocale(String locale) {
    Map map = new HashMap();
    map.put("locale", locale);
    List<String> ret = new ArrayList<String>();
    List<LocaleVO> localeVOs = getSqlMapClientTemplate().queryForList("getLanguagesByLocale", map);
    return localeVOs;
  }

  public String getMessageForApp(String messageId, String locale, int isFmk) {
    Map map = new HashMap();
    map.put("messageId", messageId);
    map.put("locale", locale);
    map.put("isFmk", new Integer(isFmk));
    return (String) getSqlMapClientTemplate().queryForObject("getMessageForApp", map);
  }

  public List<CountryVO> getCountries(String locale) {
    Map map = new HashMap();
    map.put("locale", locale);
    // map.put("isFmk", new Integer(isFmk));

    List<CountryVO> countries = this.getSqlMapClientTemplate().queryForList("getCountries", map);
    return countries;
  }

  public String getCountryName(String countryCode, String locale) {
    Map map = new HashMap();
    map.put("locale", locale);
    map.put("countryCode", countryCode);
    // map.put("isFmk", new Integer(isFmk));

    String country = (String) this.getSqlMapClientTemplate().queryForObject("getCountryName", map);
    return country;

  }

@Override
public String getPath(String url) {
	String urlPath =null;
	try{
	 urlPath = (String) this.getSqlMapClientTemplate().queryForObject("getPath", url);
	   
}
	catch(Exception e){
		e.printStackTrace();
	}
	 return urlPath;
}

@Override
public void saveResource(String msgId, String msgContent, String locale) {
	Map map = new HashMap();
    map.put("msgId", msgId);
    map.put("msgContent", msgContent);
    map.put("locale", locale);
    try {
        getSqlMapClientTemplate().insert("addResource", map);
      } catch (Exception e) {
        // log.debug("-----Exception-----"+e);
        e.printStackTrace();
     }
}
 
@Override
public LocaleVO getLocaleByMsgId(String msgId) {
	Map map = new HashMap();
    map.put("msgId", msgId);
   map.put("locale", UserUtil.getMmrUser().getLocale());
	List<LocaleVO> localeVO = (List<LocaleVO>)this.getSqlMapClientTemplate().queryForList("getResourceByMsgId", map);
	if(localeVO!=null && localeVO.size() >0){
		return localeVO.get(0);
	}else{
		return new LocaleVO();
}
}

}