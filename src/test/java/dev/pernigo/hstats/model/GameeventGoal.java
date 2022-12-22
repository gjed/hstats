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
public class GameeventGoal extends GameeventModel
{

  private String playerGoalName;
  private String playerGoalNumber;
  private String playerAssistName;
  private String playerAssistNumber;
  private String partialScore;
  private String isEmptyNet;

  /**
   * @param gamereport
   */
  public GameeventGoal(GamereportModel gamereport)
  {
    super(gamereport);
  }


  /**
   * @return the playerGoalName
   */
  // @JsonProperty(required = false, value="playerGoalName")
  public String getPlayerGoalName()
  {
    return playerGoalName;
  }


  /**
   * @param playerGoalName the playerGoalName to set
   */
  public void setPlayerGoalName(String playerGoalName)
  {
    this.playerGoalName = playerGoalName;
  }


  /**
   * @return the playerGoalNumber
   */
  // @JsonProperty(required = false, value="playerGoalNumber")
  public String getPlayerGoalNumber()
  {
    return playerGoalNumber;
  }


  /**
   * @param playerGoalNumber the playerGoalNumber to set
   */
  public void setPlayerGoalNumber(String playerGoalNumber)
  {
    this.playerGoalNumber = playerGoalNumber;
  }


  /**
   * @return the playesAssistName
   */
  // @JsonProperty(required = false, value="playesAssistName")
  public String getPlayerAssistName()
  {
    return playerAssistName;
  }


  /**
   * @param playesAssistName the playesAssistName to set
   */
  public void setPlayerAssistName(String playesAssistName)
  {
    this.playerAssistName = playesAssistName;
  }


  /**
   * @return the playesAssistNumber
   */
  // @JsonProperty(required = false, value="playesAssistNumber")
  public String getPlayerAssistNumber()
  {
    return playerAssistNumber;
  }


  /**
   * @param playesAssistNumber the playesAssistNumber to set
   */
  public void setPlayerAssistNumber(String playesAssistNumber)
  {
    this.playerAssistNumber = playesAssistNumber;
  }


  /**
   * @return the partialScoreHome
   */
  // @JsonProperty(required = false, value="partialScoreHome")
  public String getPartialScore()
  {
    return partialScore;
  }


  /**
   * @param partialScoreHome the partialScoreHome to set
   */
  public void setPartialScore(String partialScore)
  {
    this.partialScore = partialScore;
  }


  /**
   * @return the isEmptyNet
   */
  //@JsonProperty(required = false, value="isEmptyNet")
  public String getIsEmptyNet()
  {
    return isEmptyNet;
  }


  /**
   * @param isEmptyNet the isEmptyNet to set
   */
  public void setIsEmptyNet(String isEmptyNet)
  {
    this.isEmptyNet = isEmptyNet;
  }




}
