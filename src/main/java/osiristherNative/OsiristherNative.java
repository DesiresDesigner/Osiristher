package osiristherNative;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DesiresDesigner on 16.02.15.
 */


public class OsiristherNative {
    private LinkedList<String> resultsList;
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    OsiristherNative(LinkedList<String> resultsList){
        this.resultsList = resultsList;
    }

    public void testSource(int userID, int taskID, String source, Language lang){
        Runnable examiner = new Examiner(userID, taskID, source, lang, resultsList);
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
