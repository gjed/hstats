package dev.pernigo.hstats;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.pernigo.hstats.factory.ChampionshipFactory;
import dev.pernigo.hstats.factory.GamereportFactory;
import dev.pernigo.hstats.factory.SeasonFactory;
import dev.pernigo.hstats.model.ChampionshipModel;
import dev.pernigo.hstats.model.GamereportModel;
import dev.pernigo.hstats.model.SeasonModel;
import dev.pernigo.hstats.pages.ChampionshipPage;
import dev.pernigo.hstats.pages.GamereportPage;
import dev.pernigo.hstats.pages.SeasonPage;


public class SampleJUnitTest extends JUnitTestBase
{
  public static final long LAG = 500;
  private static final Logger logger = LoggerFactory.getLogger(SampleJUnitTest.class);
  private SeasonPage seasonpage;
  private ChampionshipPage championshipage;
  private GamereportPage gamereportpage;

  @Test
  public void testHomePageHasATitle() throws Exception
  {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(baseUrl);
    seasonpage = PageFactory.initElements(driver, SeasonPage.class);
    List<WebElement> seasonElements = driver.findElements(By.className("select_temporada"));
    for (WebElement seasonElement : seasonElements)
    {
      seasonpage = seasonpage.navigateToSeason(seasonElement);
      SeasonModel season = SeasonFactory.getSeason(seasonpage.getSeasonsSelector());
      logger.info("Scraping season {}", season.getCode());
      List<WebElement> championshipElements = seasonpage.getChampionshipElements();
      for (WebElement championshipElement : championshipElements)
      {
        championshipage = seasonpage.navigateToChampionship(championshipElement);
        ChampionshipModel championship = ChampionshipFactory.getChampionship(season, championshipElement);
        logger.info("Scraping championship {} - {}", season.getCode(), championship.getHtmlName());

        List<WebElement> gamereportElements = championshipage.getGamereportElements();
        for (WebElement gamereportElement : gamereportElements)
        {

          GamereportModel gamereport = GamereportFactory.getGamereport(championship, gamereportElement);
          gamereportpage = championshipage.navigateToGamereport(gamereportElement);
          GamereportFactory.processGameReport(gamereportpage, gamereport);
          championship.addGamereport(gamereport);
          logger.info("Scraping game {} - {} - {} - {} {}-{} {}",
              season.getCode(), championship.getHtmlName(), gamereport.getLabel(),
              gamereport.getTeamHomeName(), gamereport.getTeamHomeScore(),
              gamereport.getTeamAwayScore(), gamereport.getTeamAwayName());
          championshipage = gamereportpage.navigateBackToChampionship();
        }
        season.addChampionship(championship);
        seasonpage = championshipage.navigateBackToSeason();
      }
    }
  }
}
