package com.teksystems.restsolutions.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TekUtils {

    public static Properties prop;
    final static Logger logger= Logger.getLogger(TekUtils.class);
    static{
        prop = new Properties();
        final String fileName="config.properties";
        try( InputStream input=TekUtils.class.getClassLoader().getResourceAsStream(fileName);){
//            final String fileName="config"+System.getProperty("env")+".properties";
            if(input==null){
                logger.error("Unable to load file : "+ fileName +". Exiting Application");
                System.exit(1);
            }
            prop.load(input);
        } catch(IOException e){
            logger.error("Unable to load the properties : " + e.getMessage());
            System.exit(1);
        }
    }

    public static String getProp(final String propertyName){
        return prop.getProperty(propertyName);
    }

    public static List<String> getPropAsList(final String propertyName){

        return Arrays.asList(prop.getProperty(propertyName).split(","));
    }

}
