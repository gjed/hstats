/* $Id$
 *
 * Copyright(C) 2022 [michele.bianchi@openinnovation.it]
 * All Rights Reserved
 */
package dev.pernigo.hstats.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author marco
 * @created Dec 17, 2022
 */
public class GamereportModel
{
  private ChampionshipModel championship;


  private String idp; // id partido
  private String idc; // id competicion

  private String gameday;
  private String phase;
  private String label;
  private String date;
  private String starttime;
  private String location;
  private List<String> referees;

  private String teamHomeId;
  private String teamHomeName;
  private String teamHomeLogoUrl;
  private String teamHomeScore;
  private String teamAwayId;
  private String teamAwayName;
  private String teamAwayLogoUrl;
  private String teamAwayScore;

  private Collection<GameeventGoal> goals;
  private Collection<GameeventPenalty> fouls;
  private Collection<GameeventTimeout> timeouts;
  private Collection<GameeventGoalieChange> goalieChanges;
  private Collection<GamestatModel> stats;

  /**
   * @param championship2
   */
  public GamereportModel(ChampionshipModel championship)
  {
    super();
    this.championship = championship;
  }


  public void addEvent(GameeventGoal goal)
  {
    if (goals == null)
    {
      goals = new ArrayList<>();
    }
    goals.add(goal);
  }


  public void addEvent(GameeventPenalty foul)
  {
    if (fouls == null)
    {
      fouls = new ArrayList<>();
    }
    fouls.add(foul);
  }


  public void addEvent(GameeventTimeout to)
  {
    if (timeouts == null)
    {
      timeouts = new ArrayList<>();
    }
    timeouts.add(to);
  }


  public void addEvent(GameeventGoalieChange gce)
  {
    if (goalieChanges == null)
    {
      goalieChanges = new ArrayList<>();
    }
    goalieChanges.add(gce);
  }


  public void addStat(GamestatModel stat)
  {
    if (stats == null)
    {
      stats = new ArrayList<>();
    }
    stats.add(stat);
  }


  /**
   * @return the championship
   */
  // @JsonProperty(required = false, value="championship")
  public ChampionshipModel getChampionship()
  {
    return championship;
  }


  /**
   * @return the idp
   */
  // @JsonProperty(required = false, value="idp")
  public String getIdp()
  {
    return idp;
  }


  /**
   * @param idp the idp to set
   */
  public void setIdp(String idp)
  {
    this.idp = idp;
  }


  /**
   * @return the idc
   */
  // @JsonProperty(required = false, value="idc")
  public String getIdc()
  {
    return idc;
  }


  /**
   * @param idc the idc to set
   */
  public void setIdc(String idc)
  {
    this.idc = idc;
  }


  /**
   * @return the gameday
   */
  // @JsonProperty(required = false, value="gameday")
  public String getGameday()
  {
    return gameday;
  }


  /**
   * @param gameday the gameday to set
   */
  public void setGameday(String gameday)
  {
    this.gameday = gameday;
  }


  /**
   * @return the phase
   */
  // @JsonProperty(required = false, value="phase")
  public String getPhase()
  {
    return phase;
  }


  /**
   * @param phase the phase to set
   */
  public void setPhase(String phase)
  {
    this.phase = phase;
  }


  /**
   * @return the label
   */
  // @JsonProperty(required = false, value="label")
  public String getLabel()
  {
    return label;
  }


  /**
   * @param label the label to set
   */
  public void setLabel(String label)
  {
    this.label = label;
  }


  /**
   * @return the date
   */
  // @JsonProperty(required = false, value="date")
  public String getDate()
  {
    return date;
  }


  /**
   * @param date the date to set
   */
  public void setDate(String date)
  {
    this.date = date;
  }


  /**
   * @return the starttime
   */
  // @JsonProperty(required = false, value="starttime")
  public String getStarttime()
  {
    return starttime;
  }


  /**
   * @param starttime the starttime to set
   */
  public void setStarttime(String starttime)
  {
    this.starttime = starttime;
  }


  /**
   * @return the location
   */
  // @JsonProperty(required = false, value="location")
  public String getLocation()
  {
    return location;
  }


  /**
   * @param location the location to set
   */
  public void setLocation(String location)
  {
    this.location = location;
  }


  /**
   * @return the referees
   */
  // @JsonProperty(required = false, value="referees")
  public List<String> getReferees()
  {
    return referees;
  }


  /**
   * @param referees the referees to set
   */
  public void setReferees(List<String> referees)
  {
    this.referees = referees;
  }


  /**
   * @return the teamHomeId
   */
  // @JsonProperty(required = false, value="teamHomeId")
  public String getTeamHomeId()
  {
    return teamHomeId;
  }


  /**
   * @param teamHomeId the teamHomeId to set
   */
  public void setTeamHomeId(String teamHomeId)
  {
    this.teamHomeId = teamHomeId;
  }


  /**
   * @return the teamAwayId
   */
  // @JsonProperty(required = false, value="teamAwayId")
  public String getTeamAwayId()
  {
    return teamAwayId;
  }


  /**
   * @param teamAwayId the teamAwayId to set
   */
  public void setTeamAwayId(String teamAwayId)
  {
    this.teamAwayId = teamAwayId;
  }


  /**
   * @return the teamHomeName
   */
  // @JsonProperty(required = false, value="teamHomeName")
  public String getTeamHomeName()
  {
    return teamHomeName;
  }


  /**
   * @param teamHomeName the teamHomeName to set
   */
  public void setTeamHomeName(String teamHomeName)
  {
    this.teamHomeName = teamHomeName;
  }


  /**
   * @return the teamHomeLogoUrl
   */
  // @JsonProperty(required = false, value="teamHomeLogoUrl")
  public String getTeamHomeLogoUrl()
  {
    return teamHomeLogoUrl;
  }


  /**
   * @param teamHomeLogoUrl the teamHomeLogoUrl to set
   */
  public void setTeamHomeLogoUrl(String teamHomeLogoUrl)
  {
    this.teamHomeLogoUrl = teamHomeLogoUrl;
  }


  /**
   * @return the teamAwayName
   */
  // @JsonProperty(required = false, value="teamAwayName")
  public String getTeamAwayName()
  {
    return teamAwayName;
  }


  /**
   * @param teamAwayName the teamAwayName to set
   */
  public void setTeamAwayName(String teamAwayName)
  {
    this.teamAwayName = teamAwayName;
  }


  /**
   * @return the teamAwayLogoUrl
   */
  // @JsonProperty(required = false, value="teamAwayLogoUrl")
  public String getTeamAwayLogoUrl()
  {
    return teamAwayLogoUrl;
  }


  /**
   * @param teamAwayLogoUrl the teamAwayLogoUrl to set
   */
  public void setTeamAwayLogoUrl(String teamAwayLogoUrl)
  {
    this.teamAwayLogoUrl = teamAwayLogoUrl;
  }


  /**
   * @return the teamHomeScore
   */
  // @JsonProperty(required = false, value="teamHomeScore")
  public String getTeamHomeScore()
  {
    return teamHomeScore;
  }


  /**
   * @param teamHomeScore the teamHomeScore to set
   */
  public void setTeamHomeScore(String teamHomeScore)
  {
    this.teamHomeScore = teamHomeScore;
  }


  /**
   * @return the teamAwayScore
   */
  // @JsonProperty(required = false, value="teamAwayScore")
  public String getTeamAwayScore()
  {
    return teamAwayScore;
  }


  /**
   * @param teamAwayScore the teamAwayScore to set
   */
  public void setTeamAwayScore(String teamAwayScore)
  {
    this.teamAwayScore = teamAwayScore;
  }


  /**
   * @param teamName
   * @return
   */
  public String getTeamId(String teamName)
  {
    String id = null;
    if (teamName.equals(teamHomeName))
    {
      id = teamHomeId;
    }
    else if (teamName.equals(teamAwayName))
    {
      id = teamAwayId;
    }
    else
    {
      throw new IllegalArgumentException("Unexpected value: " + teamName + " is not a team of this game (" + label + ")");
    }

    return id;
  }

}
