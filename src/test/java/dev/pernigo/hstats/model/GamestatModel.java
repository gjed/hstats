/* $Id$
 *
 * Copyright(C) 2022 [michele.bianchi@openinnovation.it]
 * All Rights Reserved
 */
package dev.pernigo.hstats.model;

/**
 * @author marco
 * @created Dec 22, 2022
 */
public class GamestatModel
{
  private GamereportModel gamereport;

  private String teamId;
  private String teamName;
  private String playerNumber;
  private String playerName;
  private String playerRole;

  private String pt; // points
  private String g; // goals
  private String a; // assists
  private String pim;// penalty minutes
  private String fo; // ?
  private String plusMinus;

  // goalies only
  private String ge; // goals allowed
  private String sh; // total shots (goals included)
  private String min;
  private String sPerc; // saves % = (tr - ge) / tr

  /**
   * @param teamId2
   * @param teamName2
   */
  public GamestatModel(GamereportModel gamereport, String teamId, String teamName)
  {
    super();
    this.gamereport = gamereport;
    this.teamId = teamId;
    this.teamName = teamName;
  }


  /**
   * @return the teamId
   */
  // @JsonProperty(required = false, value="teamId")
  public String getTeamId()
  {
    return teamId;
  }


  /**
   * @param teamId the teamId to set
   */
  public void setTeamId(String teamId)
  {
    this.teamId = teamId;
  }


  /**
   * @return the teamName
   */
  // @JsonProperty(required = false, value="teamName")
  public String getTeamName()
  {
    return teamName;
  }


  /**
   * @param teamName the teamName to set
   */
  public void setTeamName(String teamName)
  {
    this.teamName = teamName;
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
   * @return the playerRole
   */
  // @JsonProperty(required = false, value="playerRole")
  public String getPlayerRole()
  {
    return playerRole;
  }


  /**
   * @param playerRole the playerRole to set
   */
  public void setPlayerRole(String playerRole)
  {
    this.playerRole = playerRole;
  }


  /**
   * @return the pt
   */
  // @JsonProperty(required = false, value="pt")
  public String getPt()
  {
    return pt;
  }


  /**
   * @param pt the pt to set
   */
  public void setPt(String pt)
  {
    this.pt = pt;
  }


  /**
   * @return the g
   */
  // @JsonProperty(required = false, value="g")
  public String getG()
  {
    return g;
  }


  /**
   * @param g the g to set
   */
  public void setG(String g)
  {
    this.g = g;
  }


  /**
   * @return the a
   */
  // @JsonProperty(required = false, value="a")
  public String getA()
  {
    return a;
  }


  /**
   * @param a the a to set
   */
  public void setA(String a)
  {
    this.a = a;
  }


  /**
   * @return the pim
   */
  // @JsonProperty(required = false, value="pim")
  public String getPim()
  {
    return pim;
  }


  /**
   * @param pim the pim to set
   */
  public void setPim(String pim)
  {
    this.pim = pim;
  }


  /**
   * @return the fo
   */
  // @JsonProperty(required = false, value="fo")
  public String getFo()
  {
    return fo;
  }


  /**
   * @param fo the fo to set
   */
  public void setFo(String fo)
  {
    this.fo = fo;
  }


  /**
   * @return the plusMinus
   */
  // @JsonProperty(required = false, value="plusMinus")
  public String getPlusMinus()
  {
    return plusMinus;
  }


  /**
   * @param plusMinus the plusMinus to set
   */
  public void setPlusMinus(String plusMinus)
  {
    this.plusMinus = plusMinus;
  }


  /**
   * @return the ge
   */
  // @JsonProperty(required = false, value="ge")
  public String getGe()
  {
    return ge;
  }


  /**
   * @param ge the ge to set
   */
  public void setGe(String ge)
  {
    this.ge = ge;
  }


  /**
   * @return the tr
   */
  // @JsonProperty(required = false, value="tr")
  public String getSh()
  {
    return sh;
  }


  /**
   * @param sh the tr to set
   */
  public void setSh(String sh)
  {
    this.sh = sh;
  }


  /**
   * @return the sPerc
   */
  // @JsonProperty(required = false, value="sPerc")
  public String getsPerc()
  {
    return sPerc;
  }


  /**
   * @param sPerc the sPerc to set
   */
  public void setsPerc(String sPerc)
  {
    this.sPerc = sPerc;
  }


  /**
   * @return the min
   */
  // @JsonProperty(required = false, value="min")
  public String getMin()
  {
    return min;
  }


  /**
   * @param min the min to set
   */
  public void setMin(String min)
  {
    this.min = min;
  }


  /*
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "Gamestat [team=" + teamName + ", #" + playerNumber + " " + playerName + " (" + playerRole + ") pt=" + pt + ", g=" + g + ", a=" + a + ", pim=" + pim + ", fo=" + fo + ", plusMinus="
        + plusMinus + ", ge=" + ge + ", tr=" + sh + ", sPerc=" + sPerc + "]";
  }


}
