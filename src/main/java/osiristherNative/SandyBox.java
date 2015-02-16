/**
 * Created by desiresdesigner on 13.02.15.
 */
package osiristherNative;

import java.io.IOException;
import java.util.LinkedList;

enum Language {CPP, JAVA};

public class SandyBox {

    public static void main(String args[ ]) throws IOException, InterruptedException {
        /*CompilerCaller cc = new CompilerCaller();
        System.out.println(cc.compile("test.cc", "dir"));*/
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

        System.out.println("Hello world");
        Thread t = new Thread(new Examiner(1, 17, source, Language.CPP, resultsList));
        t.start();

        synchronized (t) {
            t.wait();
        }

        System.out.println(resultsList.removeLast());
    }
}
