package ru.osiristher.tester.fixtures.example;

/**
 * Created by DesiresDesigner on 17.02.15.
 */

import fitlibrary.DoFixture;
import ru.osiristher.tester.fixtures.Result;
import ru.osiristher.tester.fixtures.runner.CppRunner;

import java.io.IOException;

public class ExampleFixture extends DoFixture {
    String fileName;

    public void setFileName(String name){
        fileName = name;
    }

    public boolean testFromSet(String setName){
        try {
            CppRunner cr = new CppRunner();
            Result r = cr.runWithFileInput(fileName, "1/testName/" + setName);
            if (r.getExitCode() == 0)
                return true;
            else
                return false; // ToDo: logging module instead System.out
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
