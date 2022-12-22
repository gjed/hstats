package dev.pernigo.hstats.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import dev.pernigo.hstats.SampleJUnitTest;

/**
 * Sample page
 */
public class SeasonPage extends Page {

  private static final String SSXPATH = "//a[contains(@class, 'header_temp_selected')]";
  private static final String SEXPATH = "//a[contains(@class, 'select_temporada')]";
  private static final String CEXPATH = "//a[contains(@class, 'listado_competiciones_fila')]";

  private String id = "temp_33";

  @FindBy(xpath = SSXPATH)
  private WebElement seasonsSelector;

  @FindBy(xpath = SEXPATH)
  private List<WebElement> seasonsElements;

  @FindBy(xpath = CEXPATH)
  private List<WebElement> championshipElements;

  public SeasonPage(WebDriver webDriver) {
    super(webDriver);
  }

  /**
   * @return the seasons
   */
  public SeasonPage navigateToSeason(WebElement newSeasonElem)
  {
    wait.until(ExpectedConditions.elementToBeClickable(seasonsSelector));
    seasonsSelector.click();
    wait.until(ExpectedConditions.elementToBeClickable(newSeasonElem));
    newSeasonElem.click();
    SeasonPage newSeason = PageFactory.initElements(driver, SeasonPage.class);
    newSeason.setId(newSeasonElem.getAttribute("id"));
    return newSeason;
  }

  /*
   *
   */
  public ChampionshipPage navigateToChampionship(WebElement championship) throws Exception
  {
    wait.until(ExpectedConditions.elementToBeClickable(championship));
    championship.click();
    Thread.sleep(SampleJUnitTest.LAG);
    return PageFactory.initElements(driver, ChampionshipPage.class);
  }


  /**
   * @return the seasons elements
   */
  public List<WebElement> getSeasonsElements()
  {
    List<WebElement> se = new ArrayList<>();
    try
    {
      se = seasonsElements
          .stream()
          .filter(c -> c.getAttribute("class").contains("select_temporada"))
          .toList();
    }
    catch (StaleElementReferenceException e)
    {
      seasonsElements = driver.findElements(By.className("select_temporada"));
      se = seasonsElements
          .stream()
          .filter(c -> c.getAttribute("class").contains("select_temporada"))
          .toList();
    }

    return se;
  }


  /**
   * @return the championship elements
   */
  public List<WebElement> getChampionshipElements()
  {
    List<WebElement> cs = new ArrayList<>();

    try
    {
      cs = championshipElements
          .stream()
          .filter(c -> c.getAttribute("class").contains(id))
          .toList();
    }
    catch (StaleElementReferenceException e)
    {
      championshipElements = driver.findElements(By.className("listado_competiciones_fila"));
      cs = championshipElements
          .stream()
          .filter(c -> c.getAttribute("class").contains(id))
          .toList();
    }

    return cs;
  }

  public String getCode()
  {
    return seasonsSelector.getText();
  }

  /**
   * @param id the id to set
   */
  protected void setId(String id)
  {
    this.id = id;
  }

  /**
   * @return the seasonsSelector
   */
  //@JsonProperty(required = false, value="seasonsSelector")
  public WebElement getSeasonsSelector()
  {
    return seasonsSelector;
  }



}
