package osiristherNative;

import osiristherNative.entities.IntFLG;
import osiristherNative.exceptions.FixtureException;

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
    private float passedPercent = 0;

    public float testExec(String execName, int taskID, IntFLG exceptionsFlag) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "java -DEXEC='" + execName +
                "' -jar  ~/soft/FitNesse/fitnesse-standalone.jar -d ~/Projects/Osiristher -c '" + taskID + "?test&format=text'");
        Process p = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String s;
        while ((s = reader.readLine()) != null) {
            try {
                float curRes = parseLine(s);
                if (curRes > -1) {
                    if (passedPercent == 0)
                        passedPercent = parseLine(s);
                    else
                        passedPercent = (passedPercent + parseLine(s)) / 2;
                }
            } catch (FixtureException e) {
                exceptionsFlag.incFLG(); // ToDo: logging of caught exceptions
                //e.printStackTrace();
            }
            //System.out.println("Script output: " + s);
        }

        /*String errors = "";
        while ((s = stdError.readLine()) != null) {
            System.out.println("Script error_output: " + s);
            errors += s;
        }*/

        return passedPercent;
    }

    private float parseLine(String line) throws FixtureException {
        if (line.startsWith(".")){
            return 100;
        } else if (line.startsWith("F")){
            Pattern pattern = Pattern.compile("R:([0-9]*)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            double right = Integer.parseInt(matcher.group(1));

            pattern = Pattern.compile("W:([0-9]*)");
            matcher = pattern.matcher(line);
            matcher.find();
            double wrong = Integer.parseInt(matcher.group(1));
            return Float.parseFloat(new DecimalFormat("##.##").format((right / (wrong + right) * 100)));
        } else if (line.startsWith("X")){
            throw new FixtureException();
        } else
            return -1;
    }
}
