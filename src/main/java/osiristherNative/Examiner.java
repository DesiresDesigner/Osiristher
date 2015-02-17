package osiristherNative;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by desiresdesigner on 16.02.15.
 */
public class Examiner implements  Runnable {
    private CompilerCaller cc;
    //ToDo: private TesterCaller tc;

    private String fullFileName;
    private String dirShortName;
    private int userID;
    private int taskID;
    private String source;
    Language lang;
    LinkedList<String> resultsList;

    boolean needToBbeProcessed = false;
    Handleable handler;

    Examiner(int userID, int taskID, String source, Language lang, LinkedList<String> resultsList){
        cc = new CompilerCaller();

        this.userID = userID;
        this.taskID = taskID;
        this.source = source;
        this.lang = lang;
        this.resultsList = resultsList;
        dirShortName = Integer.toString(userID);
    }

    public void setHandler(Handleable handler){
        this.handler = handler;
        needToBbeProcessed = true;
    }

    private String saveSource() throws IOException {
        fullFileName = Integer.toString(userID) + "_" + Integer.toString(taskID) + "_" + Long.toString(System.currentTimeMillis() / 1000L);
        String dirPath = "src/main/resources/SourceCode/" + Integer.toString(userID);
        switch (lang) {
            case CPP:
                fullFileName += ".cc";
                break;
            case JAVA:
                fullFileName += ".java";
                break;
            default:
                fullFileName += ".cc";
                break;
        }

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(dirPath + '/' + fullFileName, false));
        writer.write(source);
        writer.close();

        return fullFileName;
    }

    @Override
    public void run() {
        try {
        saveSource();
        } catch (IOException e) {
            sendResultMessage(2, 1, "I/O problems while saving source");
            if (needToBbeProcessed)
                handler.handle();
        }

        String execToTest = "";
        try {
            execToTest = cc.compile(fullFileName, dirShortName);
            //System.out.println(execToTest);
        } catch (Exception e){
            String name = e.getClass().getSimpleName();
            if (name.equals("IOException")) {
                sendResultMessage(1, 3, "I/O problems when calling gcc");

            } else if (name.equals("UnknownLanguageException")) {
                sendResultMessage(1, 1, "Unknown extension of source file - " + e.getMessage());

            } else if (name.equals("LackOfExpansionException")) {
                sendResultMessage(1, 2, "no extension of source file");

            } else if (name.equals("GCCException")) {
                sendResultMessage(0, 1, "gcc compile error - " + e.toString());
            }

            if (needToBbeProcessed)
                handler.handle();
            return;
        }

        sendResultMessage(0, 0, "Testing module is being developed right now");

        if (needToBbeProcessed)
            handler.handle();
    }

    private void sendResultMessage(int moduleID, int errorID, String message){
        String res = Integer.toString(moduleID) + '.' + Integer.toString(errorID) +
                '.' + Integer.toString(userID) + '.' + Integer.toString(taskID) + ": " + message;
        putResult(res);
    }

    private synchronized void putResult(String result){
        resultsList.addFirst(result);
    }
}