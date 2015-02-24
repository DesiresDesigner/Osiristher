package ru.osiristher.tester.fixtures.example;

/**
 * Created by DesiresDesigner on 17.02.15.
 */

import fitlibrary.DoFixture;
import ru.osiristher.properties.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//import fitlibrary.Fixture;

public class ExampleFixture extends DoFixture {
    String fileName;

    public void setFileName(String name){
        fileName = name;
    }

    public boolean testFromSet(String setName){ // ToDo: logging module instead System.out
        try {
            /*try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                //Handle exception
            }*/
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "./run_cpp.sh " +
                    Config.getProp("BasePath") + '/' + Config.getProp("ResourcesPath") + ' ' +
                    Config.getProp("BasePath") + '/' + Config.getProp("TestingDataPath") + ' ' +
                    fileName + " 1/testName/" + setName + "/input/content.txt");
            pb.directory(new File(Config.getProp("BasePath") + '/' + Config.getProp("ScriptsPath")));
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String s;
            /*while ((s = reader.readLine()) != null) {
                System.out.println("Script output: " + s);
            }*/
            String errors = "";
            while ((s = stdError.readLine()) != null) {
                System.out.println("Script error_output: " + s);
                errors += s;
            }

            if ("".equals(errors)) {
                Scanner programOutput = new Scanner(reader);
                Scanner correctOutput = new Scanner(new File(Config.getProp("BasePath") + '/' + Config.getProp("TestingDataPath") + "/1/testName/" + setName + "/output/content.txt"));

                while (correctOutput.hasNext()) {
                    if (!programOutput.hasNext() || (correctOutput.nextInt() != programOutput.nextInt())) {
                        System.out.println("1, Student code is incorrect: (maybe here will be a part of failed test)");
                        return false;
                    }
                }
            } else {
                System.out.println("1, Student runtime error: " + errors);
                return false;
            }

            return true;
        }  catch (IOException e) {
            System.out.println("2, Fixture I/O Exception:");
            e.printStackTrace();
            return false;
        } catch (Exception e){
            System.out.println("2, Fixture Exception:");
            e.printStackTrace();
            return false;
        }
    }

}
