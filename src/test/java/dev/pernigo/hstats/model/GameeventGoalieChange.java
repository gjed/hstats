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
public class GameeventGoalieChange extends GameeventModel
{
  private String goalieInName;
  private String goalieOutName;


  /**
   * @param gamereport
   */
  public GameeventGoalieChange(GamereportModel gamereport)
  {
    super(gamereport);
  }


  /**
   * @return the goalieInName
   */
  // @JsonProperty(required = false, value="goalieInName")
  public String getGoalieInName()
  {
    return goalieInName;
  }


  /**
   * @param goalieInName the goalieInName to set
   */
  public void setGoalieInName(String goalieInName)
  {
    this.goalieInName = goalieInName;
  }


  /**
   * @return the goalieOutName
   */
  // @JsonProperty(required = false, value="goalieOutName")
  public String getGoalieOutName()
  {
    return goalieOutName;
  }


  /**
   * @param goalieOutName the goalieOutName to set
   */
  public void setGoalieOutName(String goalieOutName)
  {
    this.goalieOutName = goalieOutName;
  }


}
