/* $Id$
 *
 * Copyright(C) 2022 [michele.bianchi@openinnovation.it]
 * All Rights Reserved
 */
package dev.pernigo.hstats.factory;

import java.util.HashMap;
import org.openqa.selenium.WebElement;
import dev.pernigo.hstats.model.SeasonModel;

/**
 *
 * @author marco
 * @created Dec 17, 2022
 */
public class SeasonFactory
{

  private static HashMap<String, SeasonModel> seasons = null;

  /*
   * Return a {@link SeasonModel} from the given WebElement if it already
   * exists. Otherwise it creates one.
   * It assumes that the WebElement instance contains the code of the season.
   *
   * @return a season instance
   */
  public static SeasonModel getSeason(WebElement we) {
    if (seasons == null)
    {
      seasons = new HashMap<>();
    }
    SeasonModel s = seasons.get(we.getText());
    if (s == null) {
      s = new SeasonModel(we.getText());
      seasons.put(s.getCode(), s);
    }
    return s;
  }
}
