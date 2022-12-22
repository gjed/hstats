/* $Id$
 *
 * Copyright(C) 2022 [michele.bianchi@openinnovation.it]
 * All Rights Reserved
 */
package dev.pernigo.hstats.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.pernigo.hstats.model.ChampionshipModel;
import dev.pernigo.hstats.model.GamereportModel;


/**
 * @author marco
 * @created Dec 17, 2022
 */
public class GameeventFactory
{

  private static final String GDEXPATH = "ancestor::tbody/preceding-sibling::thead[@class='head_jornada']";
  private static final String CPEXPATH = "ancestor::table/preceding-sibling::div[@class='div_titulo_fase_idc']";
  private static final Pattern datePattern = Pattern.compile("(\\d+/\\d+/\\d+)");
  private static final Pattern timePattern = Pattern.compile("(\\d+:\\d+)");
  private static final Pattern refPattern = Pattern.compile("(\\w+, \\w+)");

  private static HashMap<String, GamereportModel> gamereports = null;
  static private Logger logger = LoggerFactory.getLogger(GameeventFactory.class);

  /*
   * Generate a {@link GamereportModel} from the given WebElement. It assumes
   * that the WebElement instance is the link to the game report
   * @return a new gamereport instance
   */
  public static GamereportModel getGamereport(ChampionshipModel championship, WebElement we)
  {
    if (gamereports == null)
    {
      gamereports = new HashMap<>();
    }
    String idp = we.findElement(By.xpath("td/i[contains(@class, 'game_report')]")).getAttribute("idp");
    GamereportModel gm = gamereports.get(idp);
    if (gm == null)
    {
      gm = new GamereportModel(championship);
      gm.setIdp(idp);
      gm.setIdc(we.findElement(By.xpath("td/i[contains(@class, 'game_report')]")).getAttribute("idc"));

    }

    List<WebElement> gamedayElement = we.findElements(By.xpath(GDEXPATH));
    if (gamedayElement.size() < 1)
    {
      logger.info("Gameday not found for gamereport {}, using generic", idp);
      gm.setGameday("");
    }
    else if (gamedayElement.size() > 1)
    {
      logger.debug("Found more than one gameday element for gamereport {}, using first", idp);
      gm.setGameday(gamedayElement.get(0).getText());
    }
    else
    {
      gm.setGameday(gamedayElement.get(0).getText());
    }

    List<WebElement> phaseElement = we.findElements(By.xpath(CPEXPATH));
    if (phaseElement.size() < 1)
    {
      logger.debug("Phase not found for gamereport {}, using generic", idp);
      gm.setGameday("");
    }
    else if (phaseElement.size() > 1)
    {
      logger.debug("Found more than one phase element for gamereport {}, using fisrt", idp);
      gm.setPhase(phaseElement.get(0).getText());
    }
    else
    {
      gm.setPhase(phaseElement.get(0).getText());
    }
    logger.debug("phase={} gameday={}", gm.getPhase(), gm.getGameday());
    return gm;
  }


  /*
   * Extract the game data from the webElement
   * @return the same gamereport instance
   */
  public static GamereportModel extractGameData(WebElement we, GamereportModel gm)
  {
    List<WebElement> tables = we.findElements(By.xpath("table"));
    List<WebElement> tab1Data = tables.get(0).findElements(By.tagName("td"));
    List<WebElement> tab2Data = tables.get(1).findElements(By.tagName("td"));

    String label = tab1Data.get(0).getText().replace("\n", " ");
    Matcher matchDate = datePattern.matcher(label);
    String date = matchDate.find() ? matchDate.group() : "";
    Matcher matchTime = timePattern.matcher(label);
    String starttime = matchTime.find() ? matchTime.group() : "";
    String location = tab1Data.get(1).getText();
    Matcher refMatcher = refPattern.matcher(tab1Data.get(2).getText());
    List<String> refs = new ArrayList<>();
    while (refMatcher.find())
    {
      refs.add(refMatcher.group());
    }
    gm.setLabel(label);
    gm.setDate(date);
    gm.setStarttime(starttime);
    gm.setLocation(location);
    gm.setReferees(refs);

    gm.setTeamHomeName(tab2Data.get(0).getText());
    gm.setTeamHomeLogoUrl(tab2Data.get(1).findElement(By.tagName("img")).getAttribute("src"));
    gm.setTeamHomeScore(tab2Data.get(2).findElement(By.id("home_score")).getText());
    gm.setTeamAwayName(tab2Data.get(4).getText());
    gm.setTeamAwayLogoUrl(tab2Data.get(3).findElement(By.tagName("img")).getAttribute("src"));
    gm.setTeamAwayScore(tab2Data.get(2).findElement(By.id("away_score")).getText());
    return gm;
  }

}

