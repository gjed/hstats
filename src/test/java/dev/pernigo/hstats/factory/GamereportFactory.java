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
  @Deprecated
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

  /**
   * @param gee
   * @param te
   */
  private static void createEvent(Element ee, GameeventModel e)
  {
    e.setPeriod(ee.selectFirst("td > div[class='game_view_indcidencias_period']").text());
    e.setTime(ee.selectFirst("td > div[class='game_view_incidencias_time']").text());
    e.setTeamId(ee.selectFirst("td > a[class='nombre_ficha_jugador_plus link_ficha_player_game']").attr("team_id"));
  }


  /**
   * @param ee
   * @param gamereport
   */
  private static void createTimeoutEvent(Element ee, GamereportModel gamereport)
  {
    GameeventTimeout te = new GameeventTimeout(gamereport);
    createEvent(ee, te);
    gamereport.addEvent(te);
  }


  /**
   * @param ee
   * @param gamereport
   */
  private static void createGoalieChangeEvent(Element ee, GamereportModel gamereport)
  {
    GameeventGoalieChange gce = new GameeventGoalieChange(gamereport);
    createEvent(ee, gce);

    List<Element> goalies = ee.select("a[class*='nombre_ficha_jugador_plus']");
    gce.setTeamId(goalies.get(0).attr("team_id"));
    gce.setGoalieInName(goalies.get(0).attr("player_name").replace(",", "").toUpperCase());
    gce.setGoalieOutName(goalies.get(1).attr("player_name").replace(",", "").toUpperCase());

    gamereport.addEvent(gce);
  }


  /**
   * @param ee
   * @param gamereport
   */
  private static void createPenaltyEvent(Element ee, GamereportModel gamereport)
  {
    GameeventPenalty pe = new GameeventPenalty(gamereport);
    createEvent(ee, pe);

    pe.setPlayerNumber(ee.selectFirst("td > div[class='game_view_incidencias_dorsal']").text());
    pe.setPlayerName(ee.selectFirst("td > a[class='nombre_ficha_jugador_plus link_ficha_player_game']").attr("player_name").replace(",", "").toUpperCase());
    pe.setMinutes(ee.selectFirst("td > div[class='game_view_incidencias_min_sancion']").text().replace("'", ""));
    pe.setType(ee.selectFirst("td > br > span[class='texto_gris_11']").text().toUpperCase());

    gamereport.addEvent(pe);
  }


  /**
   * @param ee
   * @param gamereport
   */
  private static void createGoalEvent(Element ee, GamereportModel gamereport)
  {
    GameeventGoal ge = new GameeventGoal(gamereport);
    createEvent(ee, ge);

    ge.setPlayerGoalNumber(ee.selectFirst("td > div[class='game_view_incidencias_dorsal']").text());
    ge.setPlayerGoalName(ee.selectFirst("td > a[class='nombre_ficha_jugador_plus link_ficha_player_game']").attr("player_name").replace(",", "").toUpperCase());


    Element assistElem = ee.selectFirst("td > div > a[class='nombre_ficha_jugador_plus']");
    if (assistElem != null)
    {
      ge.setPlayerAssistName(assistElem.attr("player_name").replace(",", "").toUpperCase());
      ge.setPlayerAssistNumber(ee.selectFirst("td > div > a[class='nombre_ficha_jugador_plus'] + span").text().replace("#", ""));
    }

    ge.setPartialScore(ee.selectFirst("td > div[class='game_view_incidencias_result']").text());
    ge.setIsEmptyNet(ee.text().contains("EMPTY NET") ? "true" : "false");

    gamereport.addEvent(ge);
  }

  public static void processGameReport(GamereportPage gamereportPage, GamereportModel gamereport)
  {
    Document gameHeaderFrg = gamereportPage.getGameHeaderHtml();
    Document gameEventsFrg = gamereportPage.getGameEventsHtml();
    List<Document> gameStatsFrg = gamereportPage.getGameStatsHtml();

    processGameHeader(gamereport, gameHeaderFrg);
    processGameEvents(gamereport, gameEventsFrg);
    processGameStats(gamereport, gameStatsFrg);

  }


  /**
   * @param gamereport
   * @param gameStatsFrg
   */
  private static void processGameStats(GamereportModel gamereport, List<Document> gameStatsFrg)
  {
    for (Document stats : gameStatsFrg)
    {
      String teamName = stats.selectFirst(".nombre_equipo_thickbox_stats").text();
      String teamId = gamereport.getTeamId(teamName);
      List<Element> statBlocks = stats.select("tbody");

      List<Element> statPlayerRows = statBlocks.get(0).select("tr");
      List<Element> statGoalieRows = statBlocks.get(1).select("tr");
      for (Element row : statPlayerRows)
      {
        List<Element> data = row.select("td");
        GamestatModel stat = new GamestatModel(gamereport ,teamId, teamName);

        String playerNumber = data.get(0).text();
        String playerRole = data.get(1).text();
        String playerName = data.get(3).text().toUpperCase();
        String pt = data.get(4).text();
        String g = data.get(5).text();
        String a = data.get(6).text();
        String pim = data.get(7).text();
        String fo = data.get(8).text();
        String plusMinus = data.get(9).text();

        stat.setPlayerNumber(playerNumber);
        stat.setPlayerRole(playerRole);
        stat.setPlayerName(playerName);
        stat.setPt(pt);
        stat.setG(g);
        stat.setA(a);
        stat.setPim(pim);
        stat.setFo(fo);
        stat.setPlusMinus(plusMinus);
        gamereport.addStat(stat);
      }

      for (Element row : statGoalieRows)
      {
        List<Element> data = row.select("td");
        GamestatModel stat = new GamestatModel(gamereport ,teamId, teamName);

        String playerNumber = data.get(0).text();
        String playerRole = data.get(1).text();
        String playerName = data.get(3).text().toUpperCase();
        String ge = data.get(4).text();
        String sh = data.get(5).text();
        String sPerc = data.get(6).text();
        String pim = data.get(7).text();
        String min = data.get(8).text();
        String plusMinus = data.get(9).text();

        stat.setPlayerNumber(playerNumber);
        stat.setPlayerRole(playerRole);
        stat.setPlayerName(playerName);
        stat.setGe(ge);
        stat.setSh(sh);
        stat.setsPerc(sPerc);
        stat.setPim(pim);
        stat.setMin(min);
        stat.setPlusMinus(plusMinus);
        gamereport.addStat(stat);
      }

    }


  }


  /**
   * @param gamereport
   * @param gameEventsFrg
   */
  private static void processGameEvents(GamereportModel gamereport, Document gameEventsFrg)
  {
    //process event list
    List<Element> eventElements = gameEventsFrg.select("table > tbody > tr");
    for (Element ee : eventElements)
    {
      String eventType = ee
          .selectFirst("td > div[class*='evento']")
          .text()
          .toLowerCase();
      if (eventType.startsWith("gol") || eventType.startsWith("goal"))
      {
        createGoalEvent(ee, gamereport);
      }
      else if (eventType.startsWith("penalty") || eventType.startsWith("falta") || eventType.startsWith("falli"))
      {
        createPenaltyEvent(ee, gamereport);
      }
      else if (eventType.startsWith("timeout") || eventType.startsWith("tiempo muerto"))
      {
        createTimeoutEvent(ee, gamereport);
      }
      else if (eventType.startsWith("cambio de portero") || eventType.startsWith("goalkeeper change") || eventType.startsWith("sostituzione portiere"))
      {
        createGoalieChangeEvent(ee, gamereport);
      }
      else
      {
        throw new IllegalArgumentException("Unexpected value: " + ee.selectFirst("span[class='lang_label lang_en']").text());
      }
    }
  }


  /**
   * @param gamereport
   * @param gameHeaderFrg
   */
  private static void processGameHeader(GamereportModel gamereport, Document gameHeaderFrg)
  {
    // Process game header
    List<Element> gameHeaderTabs = gameHeaderFrg.select("table");
    List<Element> headerTab1data = gameHeaderTabs.get(0).select("td");
    List<Element> headerTab2data = gameHeaderTabs.get(1).select("td");

    String label = headerTab1data.get(0).text().replace("\n", " ").replace("JORNADA ", "");
    Matcher matchDate = datePattern.matcher(label);
    String date = matchDate.find() ? matchDate.group() : "";
    Matcher matchTime = timePattern.matcher(label);
    String starttime = matchTime.find() ? matchTime.group() : "";
    String location = headerTab1data.get(1).text();
    Matcher refMatcher = refPattern.matcher(headerTab1data.get(2).text());
    List<String> refs = new ArrayList<>();
    while (refMatcher.find())
    {
      refs.add(refMatcher.group().replace(",", "").toUpperCase());
    }
    gamereport.setLabel(label);
    gamereport.setDate(date);
    gamereport.setStarttime(starttime);
    gamereport.setLocation(location);
    gamereport.setReferees(refs);

    gamereport.setTeamHomeName(headerTab2data.get(0).text().toUpperCase());
    gamereport.setTeamHomeLogoUrl(headerTab2data.get(1).select("img").get(0).attr("src"));
    gamereport.setTeamHomeScore(headerTab2data.get(2).select("#home_score").text());
    gamereport.setTeamAwayName(headerTab2data.get(4).text().toUpperCase());
    gamereport.setTeamAwayScore(headerTab2data.get(2).select("#away_score").text());
    gamereport.setTeamAwayLogoUrl(headerTab2data.get(3).select("img").attr("src"));
  }





}

