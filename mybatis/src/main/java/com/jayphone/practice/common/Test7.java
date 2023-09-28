package com.jayphone.practice.common;

import org.apache.ibatis.io.Resources;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test7 {
    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        String config = "./generatorConfig.xml";
        File configFile;
        try {
            configFile = Resources.getResourceAsFile(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration configuration;
        try {
            configuration = configurationParser.parseConfiguration(configFile);
        } catch (IOException | XMLParserException e) {
            throw new RuntimeException(e);
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator;
        try {
            myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
