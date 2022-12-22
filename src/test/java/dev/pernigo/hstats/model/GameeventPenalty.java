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
public class GameeventPenalty extends GameeventModel
{

  private String playerName;
  private String playerNumber;
  private String minutes;
  private String type;

  /**
   * @param gamereport
   */
  public GameeventPenalty(GamereportModel gamereport)
  {
    super(gamereport);
  }


  /**
   * @return the playerName
   */
  // @JsonProperty(required = false, value="playerName")
  public String getPlayerName()
  {
    return playerName;
  }


  /**
   * @param playerName the playerName to set
   */
  public void setPlayerName(String playerName)
  {
    this.playerName = playerName;
  }


  /**
   * @return the playerNumber
   */
  // @JsonProperty(required = false, value="playerNumber")
  public String getPlayerNumber()
  {
    return playerNumber;
  }


  /**
   * @param playerNumber the playerNumber to set
   */
  public void setPlayerNumber(String playerNumber)
  {
    this.playerNumber = playerNumber;
  }


  /**
   * @return the minutes
   */
  // @JsonProperty(required = false, value="minutes")
  public String getMinutes()
  {
    return minutes;
  }


  /**
   * @param minutes the minutes to set
   */
  public void setMinutes(String minutes)
  {
    this.minutes = minutes;
  }


  /**
   * @return the type
   */
  // @JsonProperty(required = false, value="type")
  public String getType()
  {
    return type;
  }


  /**
   * @param type the type to set
   */
  public void setType(String type)
  {
    this.type = type;
  }


}
