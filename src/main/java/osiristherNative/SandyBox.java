/**
 * Created by desiresdesigner on 13.02.15.
 */
package osiristherNative;

import java.io.IOException;

public class SandyBox {
    public static void main(String args[ ]) throws IOException {
        CompilerCaller cc = new CompilerCaller();
        System.out.println(cc.compile("test.cc", "dir"));
        //System.out.println("Hello world");
    }
}
