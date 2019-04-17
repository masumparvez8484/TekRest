package com.teksystems.restsolutions.service;

import com.teksystems.restsolutions.domain.Country;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static org.apache.http.protocol.HTTP.USER_AGENT;
import static com.teksystems.restsolutions.utils.TekUtils.*;

public class CapitalCityResource {
    final static Logger logger= Logger.getLogger(CapitalCityResource.class);
    public static String WEB_RESOURCES="web.resources";
    static ObjectMapper mapper = new ObjectMapper();
    static CapitalCityResource capitalCityResource= new CapitalCityResource();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        outer: while (input.hasNext()){
            final String country=input.next();
            Map<String, String> capitals=null;
            inner: for(String resource: getPropAsList(WEB_RESOURCES)){
                Optional<String> responseOption= capitalCityResource.call(resource+country);
                if(responseOption.isPresent()){
                    capitals=getCapitalCities(responseOption.get());
                    if(!capitals.isEmpty()){
                        for(Map.Entry<String, String> entry: capitals.entrySet()){
                            logger.info("country: "+ entry.getKey()+"    ---   capital: "+ entry.getValue());
                        }
                    }

                    break inner;
                }
            }
            if(capitals==null || capitals.isEmpty() ){
                logger.info("Please check and try again...");
            }
        }
    }

    public Optional<String> call(final String url){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response;
        try {
            response = client.execute(request);
//            System.out.println("Response Code : "
//                    + response.getStatusLine().getStatusCode());
            StringBuffer result = new StringBuffer();
            if(response.getStatusLine().getStatusCode()==200){
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                String line ;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
//                logger.info("the response data is : " + result);
                return Optional.of(result.toString());
            }
        } catch (IOException e) {
           logger.info("Unable to call resource: " + e.getMessage());
        }
        return Optional.empty();
    }

    public static Map<String, String> getCapitalCities(final String response){
        Map<String, String> capitals= new HashMap<>();
        try {
            List<Country> countries=mapper.readValue(response, new TypeReference<List<Country>>(){});
            for(Country country:countries){
                capitals.put(country.getName(),country.getCapital());
            }
        } catch (IOException e) {
            try {
                Country country=mapper.readValue(response, new TypeReference<Country>(){});
                capitals.put(country.getName(),country.getCapital());
            } catch (IOException e1) {
                logger.error("Unable to parse the response: " +  e.getMessage());
            }
        }
        return capitals;
    }


}
