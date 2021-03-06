package com.meritconinc.mmr.dao;

import java.util.List;

import com.meritconinc.mmr.model.common.CountryVO;
import com.meritconinc.mmr.model.common.LocaleVO;

public interface MessageDAO {

  /**
   * 
   * @param messageId
   * @param locale
   * @return
   */
  public String getMessage(String messageId, String locale);

  public List<LocaleVO> getLocales();

  public String getMessageForApp(String messageId, String locale, int isFmk);

  public List<CountryVO> getCountries(String locale);

  public String getCountryName(String countryCode, String locale);

  public List<LocaleVO> getLanguagesByLocale(String locale);

public String getPath(String url);

public void saveResource(String msgId,String msgContent,String locale);

public LocaleVO getLocaleByMsgId(String msgId);
public void updateResource(String msgId, String htmlContent, String locale);
}