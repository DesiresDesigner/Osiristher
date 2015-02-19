package osiristherNative;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import osiristherNative.codes.*;
import osiristherNative.interfaces.Handleable;

/**
 * Created by DesiresDesigner on 16.02.15.
 */


public class OsiristherNative {
    private LinkedList<String> resultsList;
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    boolean handlerIsPresent = false;
    Handleable handler;

    OsiristherNative(LinkedList<String> resultsList){
        this.resultsList = resultsList;
    }

    public void setHandler(Handleable handler){
        this.handler = handler;
        handlerIsPresent = true;
    }

    public void testSource(int userID, int taskID, String source, Language lang){
        Examiner examiner = new Examiner(userID, taskID, source, lang, resultsList);

        if (handlerIsPresent)
            examiner.setHandler(handler);

        executor.execute(examiner);
        //return ""; // ToDo correct
    }

    public void free(){
        executor.shutdown();
        //while (!executor.isTerminated()){}
    }

    public void freeNow(){
        executor.shutdownNow();
        //while (!executor.isTerminated()){}
    }
    /*public String convertResult(){ // ToDo privet
        return ""; // ToDo correct
    }*/
}
