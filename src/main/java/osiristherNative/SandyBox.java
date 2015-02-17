/**
 * Created by desiresdesigner on 13.02.15.
 */
package osiristherNative;

import osiristherNative.exceptions.GCCException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum Language {CPP, JAVA};

public class SandyBox {

    public static void main(String args[ ]) throws IOException, InterruptedException {

        //ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "pwd");
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "./run_cpp.sh " + "ex/main.o" + " example/1.input");
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

        if (errors.equals("")){
            Scanner programOutput = new Scanner(reader);
            Scanner correctOutput = new Scanner(new File("src/main/java/osiristherNative/fixtures/example/1.output"));

            while (correctOutput.hasNext()) {
                if (!programOutput.hasNext() || (correctOutput.nextInt() != programOutput.nextInt())) {
                    System.out.println("Fail"); // ToDo correct
                    break;
                }
            }

            System.out.println("The end!");
        }


        /*LinkedList<String> resultsList = new LinkedList<String>();
        testHandler handler = new testHandler();
        handler.setResultsList(resultsList);

        String source = "#include <iostream>\n" +
                "#include <string>\n" +
                "#include <cstdio>\n" +
                "#include <algorithm>\n" +
                "\n" +
                "int main(void)\n" +
                "{\n" +
                "    std::cout << \"Hello world!\" << std::endl;\n" +
                "\treturn 0;\n" +
                "}";

        System.out.println("Start performance");
        OsiristherNative on = new OsiristherNative(resultsList);
        on.setHandler(handler);
        on.testSource(1, 17, source + "// 1", Language.CPP);
        on.testSource(2, 17, source + "// 2", Language.JAVA);
        on.testSource(3, 17, source + "// 3", Language.CPP);
        on.testSource(4, 17, source + "// 4", Language.CPP);
        on.testSource(5, 17, source + " 5", Language.CPP);
        on.testSource(6, 17, source + "// 6", Language.CPP);
        on.testSource(7, 17, source + "// 7", Language.CPP);
        on.testSource(8, 17, source + "// 8", Language.CPP);
        on.free();
        System.out.println("End performance");*/
    }
}
