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

        testerCaller tc = new testerCaller();
        tc.testExec("ex/main.o", 1);

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
