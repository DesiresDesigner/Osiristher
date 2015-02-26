package ru.osiristher.tester.fixtures.runner;

import ru.osiristher.properties.Config;
import ru.osiristher.tester.exceptions.ConfigException;
import ru.osiristher.tester.fixtures.Result;

import java.io.*;
import java.util.Scanner;

/**
 * Created by DesiresDesigner on 26.02.15.
 */
public class CppRunner {

    public Result runWithFileInput(String execName, String setName)
            throws IOException, InterruptedException, ConfigException {
        System.out.println("./run_cpp.sh " + ' ' +
                Config.getProp("BasePath") + '/' + Config.getProp("ResourcesPath") +
                "/ExecFiles/" + execName + ' ' +
                Config.getProp("BasePath") + '/' + Config.getProp("TestingDataPath")
                + '/' + setName + "/input/content.txt");
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "./run_cpp.sh " + ' ' +
                Config.getProp("BasePath") + '/' + Config.getProp("ResourcesPath") +
                    "/ExecFiles/" + execName + ' ' +
                Config.getProp("BasePath") + '/' + Config.getProp("TestingDataPath")
                    + '/' + setName + "/input/content.txt");
        pb.directory(new File(Config.getProp("BasePath") + '/' + Config.getProp("ScriptsPath")));
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String s, errors = "";
        while ((s = stdError.readLine()) != null) {
            System.out.println("Script error_output: " + s);
            errors += s;
        }


        if ("".equals(errors)) {
            Scanner programOutput = new Scanner(reader);
            Scanner correctOutput = new Scanner(new File(Config.getProp("BasePath") + '/' +
                    Config.getProp("TestingDataPath") + '/' + setName + "/output/content.txt"));
            return checkMatches(programOutput, correctOutput);
        } else {

        }

        return null; //ToDo
    }

    private Result checkMatches(Scanner output, Scanner expected) {
        int valCNT = 0;
        Result r = new Result();
        //r.setExecMem(0); // ToDO
        //r.setExecTimeMS(0); // ToDo

        while (expected.hasNext()) {
            ++valCNT;
            if (!output.hasNext()) {
                r.setExitCode(1);
                r.addError(valCNT, "END OF OUTPUT", expected.next());
                return r;
            } else {
                int have = output.nextInt(), need = expected.nextInt();
                if (have != need) {
                    r.setExitCode(2);
                    r.addError(valCNT, Integer.toString(have), Integer.toString(need));
                }
            }
        }

        return r;
    }
}
