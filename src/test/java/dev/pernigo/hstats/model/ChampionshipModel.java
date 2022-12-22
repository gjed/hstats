/* $Id$
 *
 * Copyright(C) 2022 [michele.bianchi@openinnovation.it]
 * All Rights Reserved
 */
package dev.pernigo.hstats.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author marco
 * @created Dec 17, 2022
 */
public class ChampionshipModel
{
  private SeasonModel season;
  private Collection<GamereportModel> gamereports = null;

  private String idc;
  private String htmlName;
  private String htmlClass;
  private String htmlId;
  private String htmlLogo;
  private String configParams;

  /**
   * @param season
   */
  public ChampionshipModel(SeasonModel season)
  {
    super();
    this.season = season;
  }


  /**
   * @return the season
   */
  //@JsonProperty(required = false, value="season")
  public SeasonModel getSeason()
  {
    return season;
  }

  /**
   * @return the championships
   */
  //@JsonProperty(required = false, value="championships")
  public void addGamereport(GamereportModel gamereportModel)
  {
    if (gamereports == null)
    {
      gamereports = new ArrayList<>();
    }
    gamereports.add(gamereportModel);
  }

  /**
   * @return the code
   */
  // @JsonProperty(required = false, value="code")
  public String getIdc()
  {
    return idc;
  }


  /**
   * @param code the code to set
   */
  public void setIdc(String code)
  {
    this.idc = code;
  }


  /**
   * @return the html_name
   */
  // @JsonProperty(required = false, value="html_name")
  public String getHtmlName()
  {
    return htmlName;
  }


  /**
   * @param html_name the html_name to set
   */
  public void setHtmlName(String html_name)
  {
    this.htmlName = html_name;
  }


  /**
   * @return the htmlClass
   */
  // @JsonProperty(required = false, value="htmlClass")
  public String getHtmlClass()
  {
    return htmlClass;
  }


  /**
   * @param htmlClass the htmlClass to set
   */
  public void setHtmlClass(String htmlClass)
  {
    this.htmlClass = htmlClass;
  }


  /**
   * @return the htmlId
   */
  // @JsonProperty(required = false, value="htmlId")
  public String getHtmlId()
  {
    return htmlId;
  }


  /**
   * @param htmlId the htmlId to set
   */
  public void setHtmlId(String htmlId)
  {
    this.htmlId = htmlId;
  }


  /**
   * @return the htmlLogo
   */
  // @JsonProperty(required = false, value="htmlLogo")
  public String getHtmlLogo()
  {
    return htmlLogo;
  }


  /**
   * @param htmlLogo the htmlLogo to set
   */
  public void setHtmlLogo(String htmlLogo)
  {
    this.htmlLogo = htmlLogo;
  }


  /**
   * @return the configParams
   */
  // @JsonProperty(required = false, value="configParams")
  public String getConfigParams()
  {
    return configParams;
  }


  /**
   * @param configParams the configParams to set
   */
  public void setConfigParams(String configParams)
  {
    this.configParams = configParams;
  }


  /*
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "ChampionshipModel [code=" + idc + "]";
  }


}
