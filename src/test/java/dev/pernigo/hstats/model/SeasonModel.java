/* $Id$
 *
 * Copyright(C) 2022 [michele.bianchi@openinnovation.it]
 * All Rights Reserved
 */
package dev.pernigo.hstats.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author marco
 * @created Dec 17, 2022
 */
public class SeasonModel
{
  private String code;
  private Collection<ChampionshipModel> championships;

  /**
   * @param text
   */
  public SeasonModel(String code)
  {
    super();
    this.code = code;
  }

  /**
   * @return the code
   */
  //@JsonProperty(required = false, value="code")
  public String getCode()
  {
    return code;
  }

  /*
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "SeasonModel [code=" + code + "]";
  }

  /**
   * @return the championships
   */
  //@JsonProperty(required = false, value="championships")
  public Collection<ChampionshipModel> getChampionships()
  {
    return championships;
  }

  /**
   * @return the championships
   */
  //@JsonProperty(required = false, value="championships")
  public void addChampionship(ChampionshipModel championshipModel)
  {
    if (championships == null)
    {
      championships = new ArrayList<>();
    }
    championships.add(championshipModel);
  }

}
