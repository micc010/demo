package com.github.rogerli.generate;

import com.github.rogerli.generate.config.ToolConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Runner {

    public static void main(String[] args) {
        String configurationLocation;
        if (args.length == 0) {
            configurationLocation = Class.class.getClass().getResource("/").getPath() + File.separator + "config.properties";
        } else {
            configurationLocation = args[0];
        }

        ToolConfiguration appConfiguration = new ToolConfiguration();
        try {
            appConfiguration.loadParam(configurationLocation);

            MybatisTool mybatisTool = new MybatisTool();
            mybatisTool.setToolConfiguration(appConfiguration);
            mybatisTool.generate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
