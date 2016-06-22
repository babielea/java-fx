package com.project6.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by aslan.emre on 22.06.2016.
 */
public class ConfigLoader {
    private static ConfigLoader ourInstance = new ConfigLoader();
    private static Properties props = loadConfig();
    private static String hostname = props.getProperty("database.hostname");
    private static String port = props.getProperty("database.port");
    private static String dbname = props.getProperty("database.name");
    private static String username = props.getProperty("database.username");
    private static String password = props.getProperty("database.password");


    private ConfigLoader() {
    }

    public static ConfigLoader getInstance() {
        return ourInstance;
    }

    public static Properties getConfig() {
        return props;
    }

    private static Properties loadConfig() {
        Properties loadedProps = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("config.properties");
        try {
            loadedProps.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedProps;
    }

    public void saveConfig(Properties properties) {
        URL url = getClass().getResource("/config.properties");
        File file = new File(url.getPath());

        try (FileWriter writer = new FileWriter(file)) {

            properties.store(writer, "host settings");
        } catch (IOException ex) {
            // I/O error
        }
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConfigLoader.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ConfigLoader.username = username;
    }

    public static String getDbname() {
        return dbname;
    }

    public static void setDbname(String dbname) {
        ConfigLoader.dbname = dbname;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        ConfigLoader.port = port;
    }

    public static String getHostname() {
        return hostname;
    }

    public static void setHostname(String hostname) {
        ConfigLoader.hostname = hostname;
    }
}
