package osiristherNative;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by desiresdesigner on 17.02.15.
 */
public class testerCaller {

    public String testExec(String execName, int taskID){
        try {
            //"java -DEXEC='ex/main.o' -jar  ~/soft/FitNesse/fitnesse-standalone.jar -d ~/Projects/Osiristher -c \"example?test&format=text\""
            System.out.println( "java -DEXEC='" + execName +
                    "' -jar  ~/soft/FitNesse/fitnesse-standalone.jar -d ~/Projects/Osiristher -c '" + taskID + "?test&format=text'");
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "java -DEXEC='" + execName +
                    "' -jar  ~/soft/FitNesse/fitnesse-standalone.jar -d ~/Projects/Osiristher -c '" + taskID + "?test&format=text'");
            //pb.directory(new File("/home/desiresdesigner/Projects/Osiristher/src/main/java/osiristherNative/CompileScripts")); // ToDo: remember to change this dependency when deploy it on alert server
            Process p = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println("Script output: " + s);
            }
            String errors = "";
            while ((s = stdError.readLine()) != null) {
                System.out.println("Script error_output: " + s);
                errors += s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; // ToDo correct
    }
}
