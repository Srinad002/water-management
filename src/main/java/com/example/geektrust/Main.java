package com.example.geektrust;

import com.example.geektrust.appconfig.ApplicationConfig;
import com.example.geektrust.commands.CommandInvoker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Main class for execution of application
 */
public class Main {

    /**
     * main to execute with fileName passed through args
     * @param args
     */
    public static void main(String[] args)  {
        if (args.length!=0) {
            run(args[0]);
        }
	}

    /**
     * Run method execute all commands by reading the file
     * @param fileName
     */
	public static void run(String fileName) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.loadData();
        CommandInvoker commandInvoker = applicationConfig.registerCommands();

        try {
            // read file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            // read line
            String line = bufferedReader.readLine();
            while(line != null) {
                commandInvoker.execute(Arrays.asList(line.split(" ")));
                // read next line
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
