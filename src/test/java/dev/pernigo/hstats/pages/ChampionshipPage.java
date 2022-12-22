package dev.pernigo.hstats.pages;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Sample page
 */
public class ChampionshipPage extends Page {

  public static final String XP2LOAD = "//a[contains(@class, 'volver_competiciones')]";
  private static final String GRXPATH = "//tr[contains(@class, 'team_class') and @gamedate and ./td/i[contains(@class, 'game_report')]]";
  private static final String B2SXPATH = "//a[contains(@class, 'volver_competiciones')]";

  @FindBy(xpath = B2SXPATH)
  private WebElement backToSeasonLink;

  @FindBy(xpath = GRXPATH)
  private List<WebElement> gamereportElements;

  public ChampionshipPage(WebDriver webDriver) {
    super(webDriver);
  }

  /**
   * @return the seasons
   */
  public SeasonPage navigateBackToSeason() throws Exception
  {
    wait.until(ExpectedConditions.elementToBeClickable(backToSeasonLink));
    backToSeasonLink.click();
    Thread.sleep(1000);
    return PageFactory.initElements(driver, SeasonPage.class);
  }

  /**
   * @return the seasons
   */
  public GamereportPage navigateToGamereport(WebElement gamereportElem) throws Exception
  {
    WebElement gmLink = gamereportElem.findElement(By.xpath("td/i[contains(@class, 'game_report')]"));
    wait.until(ExpectedConditions.elementToBeClickable(gmLink));
    gmLink.click();
    Thread.sleep(1000);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GamereportPage.XP2LOAD)));
    return PageFactory.initElements(driver, GamereportPage.class);
  }

  /**
   * @return the gamereportElem
   */
  public List<WebElement> getGamereportElements()
  {
    List<WebElement> grs = gamereportElements;
    try
    {
      gamereportElements.stream().forEach(c -> c.getText());
    }
    catch (StaleElementReferenceException e)
    {
      grs = driver.findElements(By.xpath(GRXPATH));
    }

    return grs;
  }



}
