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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import dev.pernigo.hstats.model.*;
import dev.pernigo.hstats.pages.GamereportPage;


/**
 * @author marco
 * @created Dec 17, 2022
 */
public class GamereportFactory
{

  private static final String GDEXPATH = "ancestor::tbody/preceding-sibling::thead[@class='head_jornada']";
  private static final String CPEXPATH = "ancestor::table/preceding-sibling::div[@class='div_titulo_fase_idc']";
  private static final String ICONCSS = "tr > td > i[class~='game_report']";
  private static final Pattern datePattern = Pattern.compile("(\\d+/\\d+/\\d+)");
  private static final Pattern timePattern = Pattern.compile("(\\d+:\\d+)");
  private static final Pattern refPattern = Pattern.compile("(\\w+, \\w+)");

  private static HashMap<String, GamereportModel> gamereports = null;
  private static Logger logger = LoggerFactory.getLogger(GamereportFactory.class);

  /*
   * Generate a {@link GamereportModel} from the given WebElement. With some Jsoup
   */
  public static GamereportModel getGamereport(ChampionshipModel championship, String externalHTML, WebElement we) throws Exception
  {
    if (gamereports == null)
    {
      gamereports = new HashMap<>();
    }
    Document externalFragment = Jsoup.parse(externalHTML, "", Parser.xmlParser());

    String idp = externalFragment.select(ICONCSS).get(0).attr("idp");
    GamereportModel gm = gamereports.get(idp);
    if (gm == null)
    {
      gm = new GamereportModel(championship);
      gm.setIdp(idp);
      String[] gmClasses = externalFragment.firstChild().attr("class").split(" ");
      gm.setTeamHomeId(gmClasses[0]);
      gm.setTeamAwayId(gmClasses[1]);

      gm.setIdc(externalFragment.select(ICONCSS).get(0).attr("idc"));

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
    }

    return gm;
  }


  /*
   * Generate a {@link GamereportModel} from the given WebElement.
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
      String[] gmClasses = we.getAttribute("class").split(" ");
      gm.setTeamHomeId(gmClasses[0]);
      gm.setTeamAwayId(gmClasses[1]);
      gm.setIdc(we.findElement(By.xpath("td/i[contains(@class, 'game_report')]")).getAttribute("idc"));

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

    }

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


  /**
   * @param gameEventElements
   * @param gamereport
   */
  public static void extractGameEvents(List<WebElement> gameEventElements, GamereportModel gamereport)
  {

    for (WebElement gee : gameEventElements)
    {
      String eventType = gee.findElement(By.xpath("td/div[contains(@class, 'evento')]")).getText().toLowerCase();
      if (eventType.startsWith("gol") || eventType.startsWith("goal"))
      {
        createGoalEvent(gee, gamereport);
      }
      else if (eventType.startsWith("penalty") || eventType.startsWith("falta") || eventType.startsWith("falli"))
      {
        createPenaltyEvent(gee, gamereport);
      }
      else if (eventType.startsWith("timeout") || eventType.startsWith("tiempo muerto"))
      {
        createTimeoutEvent(gee, gamereport);
      }
      else if (eventType.startsWith("cambio de portero") || eventType.startsWith("goalkeeper change") || eventType.startsWith("sostituzione portiere"))
      {
        createGoalieChangeEvent(gee, gamereport);
      }
      else
      {
        throw new IllegalArgumentException("Unexpected value: " + gee.findElement(By.xpath("child::span[@class='lang_label lang_en']")).getText());
      }
    }

  }

  public static void processGameReport(GamereportPage gamereportPage, GamereportModel gamereport)
  {
    Document gameHeaderFrg = gamereportPage.getGameHeaderHtml();
    List<Element> gameHeaderTabs = gameHeaderFrg.select("table");
    List<Element> headerTab1data = gameHeaderTabs.get(0).select("td");
    List<Element> headerTab2data = gameHeaderTabs.get(1).select("td");
    Document gameEventsFrg = gamereportPage.getGameEventsHtml();

    String label = headerTab1data.get(0).text().replace("\n", " ");
    Matcher matchDate = datePattern.matcher(label);
    String date = matchDate.find() ? matchDate.group() : "";
    Matcher matchTime = timePattern.matcher(label);
    String starttime = matchTime.find() ? matchTime.group() : "";
    String location = headerTab1data.get(1).text();
    Matcher refMatcher = refPattern.matcher(headerTab1data.get(2).text());
    List<String> refs = new ArrayList<>();
    while (refMatcher.find())
    {
      refs.add(refMatcher.group());
    }
    gamereport.setLabel(label);
    gamereport.setDate(date);
    gamereport.setStarttime(starttime);
    gamereport.setLocation(location);
    gamereport.setReferees(refs);

    gamereport.setTeamHomeName(headerTab2data.get(0).text());
    gamereport.setTeamHomeLogoUrl(headerTab2data.get(1).select("img").get(0).attr("src"));
    gamereport.setTeamHomeScore(headerTab2data.get(2).select("#home_score").text());
    gamereport.setTeamAwayName(headerTab2data.get(4).text());
    gamereport.setTeamAwayLogoUrl(headerTab2data.get(3).select("img").attr("src"));
    gamereport.setTeamAwayScore(headerTab2data.get(2).select("#away_score").text());


  }


  /**
   * @param gee
   * @param te
   */
  private static void createEvent(WebElement we, GameeventModel e)
  {
    e.setPeriod(we.findElement(By.xpath("td/div[@class='game_view_indcidencias_period']")).getText());
    e.setTime(we.findElement(By.xpath("td/div[@class='game_view_incidencias_time']")).getText());
    e.setTeamId(we.findElement(By.xpath("td/a[@class='nombre_ficha_jugador_plus link_ficha_player_game']")).getAttribute("team_id"));
  }


  /**
   * @param gee
   * @param gamereport
   */
  private static void createTimeoutEvent(WebElement gee, GamereportModel gamereport)
  {
    GameeventTimeout te = new GameeventTimeout(gamereport);
    createEvent(gee, te);
    gamereport.addEvent(te);
  }


  /**
   * @param gee
   * @param gamereport
   */
  private static void createGoalieChangeEvent(WebElement gee, GamereportModel gamereport)
  {
    GameeventGoalieChange gce = new GameeventGoalieChange(gamereport);
    createEvent(gee, gce);

    List<WebElement> goalies = gee.findElements(By.xpath("child::a[contains(@class, 'nombre_ficha_jugador_plus')]"));
    gce.setTeamId(goalies.get(0).getAttribute("team_id"));
    gce.setGoalieInName(goalies.get(0).getAttribute("player_name").replace(",", ""));
    gce.setGoalieOutName(goalies.get(1).getAttribute("player_name").replace(",", ""));

    gamereport.addEvent(gce);
  }


  /**
   * @param gee
   * @param gamereport
   */
  private static void createPenaltyEvent(WebElement gee, GamereportModel gamereport)
  {
    GameeventPenalty pe = new GameeventPenalty(gamereport);
    createEvent(gee, pe);

    pe.setPlayerNumber(gee.findElement(By.xpath("td/div[@class='game_view_incidencias_dorsal']")).getText());
    pe.setPlayerName(gee.findElement(By.xpath("td/a[@class='nombre_ficha_jugador_plus link_ficha_player_game']")).getAttribute("player_name").replace(",", ""));
    pe.setMinutes(gee.findElement(By.xpath("td/div[@class='game_view_incidencias_min_sancion']")).getText().replace("'", ""));
    pe.setType(gee.findElement(By.xpath("td/span[@class='texto_gris_11']")).getText());

    gamereport.addEvent(pe);
  }


  /**
   * @param gee
   * @param gamereport
   */
  private static void createGoalEvent(WebElement gee, GamereportModel gamereport)
  {
    GameeventGoal ge = new GameeventGoal(gamereport);
    createEvent(gee, ge);

    ge.setPlayerGoalNumber(gee.findElement(By.xpath("td/div[@class='game_view_incidencias_dorsal']")).getText());
    ge.setPlayerGoalName(gee.findElement(By.xpath("td/a[@class='nombre_ficha_jugador_plus link_ficha_player_game']")).getAttribute("player_name").replace(",", ""));


    List<WebElement> assistElem = gee.findElements(By.xpath("td/div/a[@class='nombre_ficha_jugador_plus']"));
    if (assistElem.size() > 1)
    {
      ge.setPlayerAssistName(assistElem.get(0).getAttribute("player_name").replace(",", ""));
      ge.setPlayerAssistNumber(gee.findElements(By.xpath("td/div[a[@class='nombre_ficha_jugador_plus']]")).get(0).getText().split("#")[1]);
    }

    ge.setPartialScore(gee.findElement(By.xpath("td/div[@class='game_view_incidencias_result']")).getText());
    ge.setIsEmptyNet(gee.getText().contains("EMPTY NET") ? "true" : "false");

    gamereport.addEvent(ge);
  }

}

