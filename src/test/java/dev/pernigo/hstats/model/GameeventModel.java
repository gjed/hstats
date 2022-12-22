/* $Id$
 *
 * Copyright(C) 2022 [michele.bianchi@openinnovation.it]
 * All Rights Reserved
 */
package dev.pernigo.hstats.model;

/**
 * @author marco
 * @created Dec 18, 2022
 */
public class GameeventModel
{
  private GamereportModel gamereport;

  private String period;
  private String time;
  private String teamId;


  /**
   * @param gamereport
   */
  public GameeventModel(GamereportModel gamereport)
  {
    super();
    this.gamereport = gamereport;
  }


  /**
   * @return the gamereport
   */
  // @JsonProperty(required = false, value="gamereport")
  public GamereportModel getGamereport()
  {
    return gamereport;
  }


  /**
   * @param gamereport the gamereport to set
   */
  public void setGamereport(GamereportModel gamereport)
  {
    this.gamereport = gamereport;
  }


  /**
   * @return the period
   */
  // @JsonProperty(required = false, value="period")
  public String getPeriod()
  {
    return period;
  }


  /**
   * @param period the period to set
   */
  public void setPeriod(String period)
  {
    this.period = period;
  }


  /**
   * @return the time
   */
  // @JsonProperty(required = false, value="time")
  public String getTime()
  {
    return time;
  }


  /**
   * @param time the time to set
   */
  public void setTime(String time)
  {
    this.time = time;
  }


  /**
   * @return the team
   */
  // @JsonProperty(required = false, value="team")
  public String getTeamId()
  {
    return teamId;
  }


  /**
   * @param teamId the team to set
   */
  public void setTeamId(String teamId)
  {
    this.teamId = teamId;
  }


}
