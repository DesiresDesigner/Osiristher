package osiristherNative.fixtures.example;

/**
 * Created by DesiresDesigner on 17.02.15.
 */

import fitlibrary.DoFixture;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
//import fitlibrary.Fixture;

public class exampleFixture extends DoFixture {
    String fileName;

    public void setFileName(String name){
        fileName = name;
    }

    public boolean testFromFileAndCompareWith(String input, String output){
        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "./run_cpp.sh " + "ex/main.o" + " example/" + input);
            pb.directory(new File("/home/desiresdesigner/Projects/Osiristher/src/main/java/osiristherNative/CompileScripts")); // ToDo: remember to change this dependency when deploy it on alert server
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

            if (errors.equals("")) {
                Scanner programOutput = new Scanner(reader);
                Scanner correctOutput = new Scanner(new File("src/main/java/osiristherNative/fixtures/example/" + output));

                while (correctOutput.hasNext()) {
                    if (!programOutput.hasNext() || (correctOutput.nextInt() != programOutput.nextInt())) {
                        return false;
                    }
                }
            } else {
                System.out.println(errors);
                return false;
            }

            return true;
        }  catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
