package com.teksystems.restsolutions.service;

import com.teksystems.restsolutions.domain.Country;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Map;
import java.util.Optional;

public class CapitalCityResourceTest  {

    final static Logger logger= Logger.getLogger(CapitalCityResourceTest.class);
    CapitalCityResource capitalCityResource;
    Country country;

    @BeforeSuite
    public void testBeforeSuite() {
    }

    @AfterSuite
    public void testAfterSuite() {
    }

    @BeforeTest
    public void testBeforeTest() {
        capitalCityResource= new CapitalCityResource();
        country = new Country();
        country.setName("italy");
        country.setCapital("Rome");

    }

    @Test(priority = 0)
    public void testCallWithGoodCode(){
        //checking for co
        Optional<String> response=capitalCityResource.call("https://restcountries.eu/rest/v2/alpha/co");
        Assert.assertTrue(response.isPresent());
    }

    @Test(priority = 1)
    public void testCallWithBadCode(){
        Optional<String> response=capitalCityResource.call("https://restcountries.eu/rest/v2/alpha/cojsfjasdfjasfj");
        Assert.assertFalse(response.isPresent());
    }

    @Test(priority = 2)
    public void testCallWithGoodName(){
        Optional<String> response=capitalCityResource.call("https://restcountries.eu/rest/v2/name/italy");
        Assert.assertTrue(response.isPresent());
    }

    @Test(priority = 3)
    public void testWithBadName(){
        Optional<String> response=capitalCityResource.call("https://restcountries.eu/rest/v2/name/adasdasdas");
        Assert.assertFalse(response.isPresent());
    }

    @Test(priority = 4)
    public void testgetCapitalCitiesWithGoodCode(){
        //Data for italy
        final String countryDataAsArray="[{\"name\":\"Italy\",\"topLevelDomain\":[\".it\"],\"alpha2Code\":\"IT\",\"alpha3Code\":\"ITA\",\"callingCodes\":[\"39\"],\"capital\":\"Rome\",\"altSpellings\":[\"IT\",\"Italian Republic\",\"Repubblica italiana\"],\"region\":\"Europe\",\"subregion\":\"Southern Europe\",\"population\":60665551,\"latlng\":[42.83333333,12.83333333],\"demonym\":\"Italian\",\"area\":301336.0,\"gini\":36.0,\"timezones\":[\"UTC+01:00\"],\"borders\":[\"AUT\",\"FRA\",\"SMR\",\"SVN\",\"CHE\",\"VAT\"],\"nativeName\":\"Italia\",\"numericCode\":\"380\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}],\"languages\":[{\"iso639_1\":\"it\",\"iso639_2\":\"ita\",\"name\":\"Italian\",\"nativeName\":\"Italiano\"}],\"translations\":{\"de\":\"Italien\",\"es\":\"Italia\",\"fr\":\"Italie\",\"ja\":\"イタリア\",\"it\":\"Italia\",\"br\":\"Itália\",\"pt\":\"Itália\",\"nl\":\"Italië\",\"hr\":\"Italija\",\"fa\":\"ایتالیا\"},\"flag\":\"https://restcountries.eu/data/ita.svg\",\"regionalBlocs\":[{\"acronym\":\"EU\",\"name\":\"European Union\",\"otherAcronyms\":[],\"otherNames\":[]}],\"cioc\":\"ITA\"}]";
        Map<String,String> map=CapitalCityResource.getCapitalCities(countryDataAsArray);
        Assert.assertEquals(map.get("Italy"),"Rome");
    }

    @Test(priority = 4)
    public void testgetCapitalCitiesWithGoodCodeCount(){
        //Data for italy
        final String countryDataAsArray="[{\"name\":\"Italy\",\"topLevelDomain\":[\".it\"],\"alpha2Code\":\"IT\",\"alpha3Code\":\"ITA\",\"callingCodes\":[\"39\"],\"capital\":\"Rome\",\"altSpellings\":[\"IT\",\"Italian Republic\",\"Repubblica italiana\"],\"region\":\"Europe\",\"subregion\":\"Southern Europe\",\"population\":60665551,\"latlng\":[42.83333333,12.83333333],\"demonym\":\"Italian\",\"area\":301336.0,\"gini\":36.0,\"timezones\":[\"UTC+01:00\"],\"borders\":[\"AUT\",\"FRA\",\"SMR\",\"SVN\",\"CHE\",\"VAT\"],\"nativeName\":\"Italia\",\"numericCode\":\"380\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}],\"languages\":[{\"iso639_1\":\"it\",\"iso639_2\":\"ita\",\"name\":\"Italian\",\"nativeName\":\"Italiano\"}],\"translations\":{\"de\":\"Italien\",\"es\":\"Italia\",\"fr\":\"Italie\",\"ja\":\"イタリア\",\"it\":\"Italia\",\"br\":\"Itália\",\"pt\":\"Itália\",\"nl\":\"Italië\",\"hr\":\"Italija\",\"fa\":\"ایتالیا\"},\"flag\":\"https://restcountries.eu/data/ita.svg\",\"regionalBlocs\":[{\"acronym\":\"EU\",\"name\":\"European Union\",\"otherAcronyms\":[],\"otherNames\":[]}],\"cioc\":\"ITA\"}]";
        Map<String,String> map=CapitalCityResource.getCapitalCities(countryDataAsArray);
        Assert.assertEquals(map.size(),1);
    }

    @Test(priority = 5)
    public void testgetCapitalCitiesWithBadCode(){
         Map<String,String> map=CapitalCityResource.getCapitalCities("");
        Assert.assertTrue(map.isEmpty());
    }

    @Test(priority = 6)
    public void testgetCapitalCitiesWithGoodName(){
        final String countryDataAsArray="[{\"name\":\"Italy\",\"topLevelDomain\":[\".it\"],\"alpha2Code\":\"IT\",\"alpha3Code\":\"ITA\",\"callingCodes\":[\"39\"],\"capital\":\"Rome\",\"altSpellings\":[\"IT\",\"Italian Republic\",\"Repubblica italiana\"],\"region\":\"Europe\",\"subregion\":\"Southern Europe\",\"population\":60665551,\"latlng\":[42.83333333,12.83333333],\"demonym\":\"Italian\",\"area\":301336.0,\"gini\":36.0,\"timezones\":[\"UTC+01:00\"],\"borders\":[\"AUT\",\"FRA\",\"SMR\",\"SVN\",\"CHE\",\"VAT\"],\"nativeName\":\"Italia\",\"numericCode\":\"380\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}],\"languages\":[{\"iso639_1\":\"it\",\"iso639_2\":\"ita\",\"name\":\"Italian\",\"nativeName\":\"Italiano\"}],\"translations\":{\"de\":\"Italien\",\"es\":\"Italia\",\"fr\":\"Italie\",\"ja\":\"イタリア\",\"it\":\"Italia\",\"br\":\"Itália\",\"pt\":\"Itália\",\"nl\":\"Italië\",\"hr\":\"Italija\",\"fa\":\"ایتالیا\"},\"flag\":\"https://restcountries.eu/data/ita.svg\",\"regionalBlocs\":[{\"acronym\":\"EU\",\"name\":\"European Union\",\"otherAcronyms\":[],\"otherNames\":[]}],\"cioc\":\"ITA\"}]";
        Map<String,String> map=CapitalCityResource.getCapitalCities(countryDataAsArray);
        Assert.assertEquals(map.get("Italy"),"Rome");
    }

    @Test(priority = 7)
    public void testgetCapitalCitiesWithBadName(){
        Map<String,String> map=CapitalCityResource.getCapitalCities("");
        Assert.assertTrue(map.isEmpty());
    }
    @AfterTest
    public void testAfterTest() {
        logger.info("Release resource .....");
    }
}
