package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckGeoSorting extends TestBase {

    @Test
    //task 1a
    public void checkCountriesSorting() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> currentCountries = driver.findElements(By.cssSelector("form[name = countries_form] table.dataTable td:nth-of-type(5) a"));
        checkSorting(currentCountries);
    }

    @Test
    //task 1b
    public void checkCountriesGeoZonesSorting() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> links = driver.findElements(By.xpath("//tr[@class='row'][./td[6][text() != '0']]/td[5]/a"));
        List<String> linksList = new ArrayList<>();
        links.stream().forEach((country) -> linksList.add(country.getAttribute("href")));
        for (int i = 0; i < linksList.size(); i++) {
            driver.get(linksList.get(i));
            List<WebElement> regions = driver.findElements(By.xpath("//table[@id = 'table-zones']//td[3][text()]"));
            checkSorting(regions);
        }
    }

    @Test
    //task 1b. Other way
    public void checkCountriesGeoZonesSortingOtherWay() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countriesWithRegions = driver.findElements(By.xpath("//tr[@class='row'][./td[6][text() != '0']]"));
        for (int i = 0; i < countriesWithRegions.size(); i++) {
            countriesWithRegions = driver.findElements(By.xpath("//tr[@class='row'][./td[6][text() != '0']]"));
            countriesWithRegions.get(i).findElement(By.xpath(".//a")).click();
            List<WebElement> regions = driver.findElements(By.xpath("//table[@id = 'table-zones']//td[3][text()]"));
            checkSorting(regions);
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        }
    }

    //task 2
    @Test
    public void checkGeoZonesSorting() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement>  countriesList = driver.findElements(By.cssSelector("form[name=geo_zones_form] table td:nth-of-type(3) a"));
        List<String> linksList = new ArrayList<>();
        countriesList.stream().forEach((country) -> linksList.add(country.getAttribute("href")));
        for (int i = 0; i < linksList.size(); i++) {
            driver.get(linksList.get(i));
            List<WebElement> countryZones = driver.findElements(By.xpath("//select[contains(@name, 'zone_code')]/option[@selected]"));
            checkSorting(countryZones);
        }
    }

    private void checkSorting(List<WebElement> currentGeoUnits) {
        List<String> listOfGeoUnitsNames = new ArrayList<>();
        currentGeoUnits.stream().forEach((geoUnit) -> listOfGeoUnitsNames.add(geoUnit.getText()));
        List<String> sortedGeoUnitsNames = new ArrayList<>(listOfGeoUnitsNames);
        Collections.sort(sortedGeoUnitsNames);
        Assert.assertTrue("Wrong sorting", listOfGeoUnitsNames.equals(sortedGeoUnitsNames));
    }
}
