/* $Id$
 *
 * Copyright(C) 2022 [michele.bianchi@openinnovation.it]
 * All Rights Reserved
 */
package dev.pernigo.hstats.factory;

import java.util.HashMap;
import org.openqa.selenium.WebElement;
import dev.pernigo.hstats.model.ChampionshipModel;
import dev.pernigo.hstats.model.SeasonModel;


/**
 * @author marco
 * @created Dec 17, 2022
 */
public class ChampionshipFactory
{
  private static HashMap<String, ChampionshipModel> championships = null;

  /*
   * Generate a {@link ChampionshipModel} from the given WebElement. It assumes that the WebElement instance is the link to the championship
   * @return a new championship instance
   */
  public static ChampionshipModel getChampionship(SeasonModel season, WebElement we) {
    if (championships == null)
    {
      championships = new HashMap<>();
    }

    String idc = we.getAttribute("id");
    ChampionshipModel ch = championships.get(idc);
    if (ch == null)
    {
      ch = new ChampionshipModel(season);
      ch.setIdc(idc);
      ch.setHtmlName(we.getAttribute("name"));
      ch.setHtmlClass(we.getAttribute("class"));
      ch.setHtmlId(idc);
      ch.setHtmlLogo(we.getAttribute("logo"));
      ch.setConfigParams(we.getAttribute("config_params"));
      season.addChampionship(ch);
      championships.put(idc, ch);
    }

    return ch;
  }
}
