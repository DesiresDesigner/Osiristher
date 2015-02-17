/**
 * Created by desiresdesigner on 13.02.15.
 */
package osiristherNative;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum Language {CPP, JAVA};

public class SandyBox {

    public static void main(String args[ ]) throws IOException, InterruptedException {
        LinkedList<String> resultsList = new LinkedList<String>();

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
        on.testSource(1, 17, source + "// 1", Language.CPP);
        on.testSource(2, 17, source + "// 2", Language.CPP);
        on.testSource(3, 17, source + "// 3", Language.CPP);
        on.testSource(4, 17, source + "// 4", Language.CPP);
        on.testSource(5, 17, source + "// 5", Language.CPP);
        on.testSource(6, 17, source + "// 6", Language.CPP);
        on.testSource(7, 17, source + "// 7", Language.CPP);
        on.testSource(8, 17, source + "// 8", Language.CPP);

        on.freeNow();
        System.out.println("End performance");
        //System.out.println(resultsList.removeLast());

        /*ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable r1 = new testThread(1);
        Runnable r2 = new testThread(2);
        Runnable r3 = new testThread(3);
        Runnable r4 = new testThread(4);

        executor.execute(r1);
        executor.execute(r2);
        executor.execute(r3);
        executor.execute(r4);*/
    }
}
