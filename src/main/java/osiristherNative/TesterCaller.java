package osiristherNative;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DesiresDesigner on 17.02.15.
 */

public class TesterCaller {
    private int currentStatusCode;

    public String testExec(String execName, int taskID){
        try {
            System.out.println( "java -DEXEC='" + execName +
                    "' -jar  ~/soft/FitNesse/fitnesse-standalone.jar -d ~/Projects/Osiristher -c '" + taskID + "?test&format=text'");
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "java -DEXEC='" + execName +
                    "' -jar  ~/soft/FitNesse/fitnesse-standalone.jar -d ~/Projects/Osiristher -c '" + taskID + "?test&format=text'");
            Process p = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String s;
            while ((s = reader.readLine()) != null) {
                parseLine(s);
                //System.out.println("Script output: " + s);
            }
            /*String errors = "";
            while ((s = stdError.readLine()) != null) {
                System.out.println("Script error_output: " + s);
                errors += s;
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; // ToDo correct
    }

    private String parseLine(String line){
        if (line.startsWith(".")){
            System.out.println("Correct");
        } else if (line.startsWith("F")){
            Pattern pattern = Pattern.compile("R:([0-9]*)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            float right = Integer.parseInt(matcher.group(1));

            pattern = Pattern.compile("W:([0-9]*)");
            matcher = pattern.matcher(line);
            matcher.find();
            float wrong = Integer.parseInt(matcher.group(1));
            return new DecimalFormat("##.##").format((right / (wrong + right) * 100)) + "% of tests passed";
        } else if (line.startsWith("X")){
            return "Exception in fixture";
        }
        return "FATAL ERROR";
    }
}
