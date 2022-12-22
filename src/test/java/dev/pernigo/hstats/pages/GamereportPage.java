package dev.pernigo.hstats.pages;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import dev.pernigo.hstats.SampleJUnitTest;

/**
 * Sample page
 */
public class GamereportPage extends Page {

  public static final String XP2LOAD = "//div[@id='close_sidgad_thickbox']";
  public static final String GEXPATH = "//div[@id='game_report_inicidencias']";
  public static final String GSXPATH = "//table[@class='competiciones_tabla_basic']";

  @FindBy(id = "close_sidgad_thickbox")
  private WebElement backToChampionshipLink;

  @FindBy(id = "partido_data_ppal")
  private WebElement gameHeaderElement;

  @FindBy(xpath = GEXPATH)
  private WebElement gameEventsElement;

  @FindBy(xpath = GSXPATH)
  private List<WebElement> gameStatsElements;

  public GamereportPage(WebDriver webDriver) {
    super(webDriver);
  }

  /**
   * @return the seasons
   */
  public ChampionshipPage navigateBackToChampionship() throws Exception
  {
    wait.until(ExpectedConditions.elementToBeClickable(backToChampionshipLink));
    backToChampionshipLink.click();
    Thread.sleep(SampleJUnitTest.LAG);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ChampionshipPage.XP2LOAD)));
    return PageFactory.initElements(driver, ChampionshipPage.class);
  }

  public WebElement getGameHeaderElement()
  {

    try
    {
      gameHeaderElement.getText();
    }
    catch (StaleElementReferenceException e)
    {
      gameHeaderElement = driver.findElement(By.id("partido_data_ppal"));
    }
    return gameHeaderElement;
  }

  public WebElement getGameEventsElement()
  {

    try
    {
      gameEventsElement.getText();
    }
    catch (StaleElementReferenceException e)
    {
      gameEventsElement = driver.findElement(By.xpath(GEXPATH));
    }

    return gameEventsElement;
  }

  public List<WebElement> getGameStatsElements()
  {

    try
    {
      gameStatsElements.stream().forEach(c -> c.getText());
    }
    catch (StaleElementReferenceException e)
    {
      gameStatsElements = driver.findElements(By.xpath(GSXPATH));
    }

    return gameStatsElements;
  }

  public Document getGameHeaderHtml()
  {
    return Jsoup.parse(getGameHeaderElement().getAttribute("outerHTML"), "", Parser.xmlParser());
  }

  public Document getGameEventsHtml()
  {
    return Jsoup.parse(getGameEventsElement().getAttribute("outerHTML"), "", Parser.xmlParser());
  }

  public List<Document> getGameStatsHtml()
  {
    List<Document> stats = new ArrayList<>();
    getGameStatsElements().stream()
        .forEach(e -> stats.add(Jsoup.parse(e.getAttribute("outerHTML"), "", Parser.xmlParser())) );
    return stats;
  }

}
