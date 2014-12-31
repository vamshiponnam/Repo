package com.ebay.Ebay_BDD;



import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

public class CommonFunctions {

    private static String ConfigProperty;
    private static String Config_file = CurrentDirectory() + "/config.properties";
    public static String operatingsystem;

    public static Logger Log(String LogString) throws Exception {
        PropertyConfigurator.configure("log4j.properties");
        final Logger Log = Logger.getLogger("Log:");
        Log.info(LogString);
        return Log;
    }

    public static String CurrentDirectory() {
        String CurDir = System.getProperty("user.dir");
        return CurDir;
    }

    public static String getOS() {
        operatingsystem = System.getProperty("os.name");
        return operatingsystem;
    }

    public static String ReadPropertiesFile(String fileName, String key) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public static String SetPropertiesFile(String fileName, String key, String value) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prop.setProperty(key, value);
        return prop.getProperty(key);
    }

    public static String getProperty(String Property) {
        if (System.getProperty(Property) == null || System.getProperty(Property).isEmpty()){
            ConfigProperty = ReadPropertiesFile(getConfig_file(),Property);
        } else {
            ConfigProperty = SetPropertiesFile(getConfig_file(), Property, System.getProperty(Property).toLowerCase());
        }
        return ConfigProperty;
    }

    public static String getConfig_file() {
        return Config_file;
    }

    public static void setConfig_file(String config_file) {
        Config_file = config_file;
    }

    public static String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public String getTomorrowsDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getTodaysDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public String getYesterdaysDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
