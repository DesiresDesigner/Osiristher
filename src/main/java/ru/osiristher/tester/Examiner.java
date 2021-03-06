package ru.osiristher.tester;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import ru.osiristher.properties.Config;
import ru.osiristher.tester.entities.IntFLG;
import ru.osiristher.tester.exceptions.ConfigException;
import ru.osiristher.tester.interfaces.Handleable;
import ru.osiristher.tester.codes.Language;

/**
 * Created by desiresdesigner on 16.02.15.
 */
public class Examiner implements  Runnable {
    private CompilerCaller cc;
    private TesterCaller tc;

    private String fullFileName;
    private String dirShortName;
    private int userID;
    private int taskID;
    private String source;
    private Language lang;
    private LinkedList<String> resultsList;

    private boolean needToBeProcessed = false;
    private Handleable handler;

    Examiner(int userID, int taskID, String source, Language lang, LinkedList<String> resultsList){
        cc = new CompilerCaller();
        tc = new TesterCaller();

        this.userID = userID;
        this.taskID = taskID;
        this.source = source;
        this.lang = lang;
        this.resultsList = resultsList;
        dirShortName = Integer.toString(taskID);
    }

    public void setHandler(Handleable handler){
        this.handler = handler;
        needToBeProcessed = true;
    }

    private String saveSource() throws IOException, ConfigException {
        fullFileName = Integer.toString(userID) + "_" + Integer.toString(taskID) + "_" + Long.toString(System.currentTimeMillis() / 1000L);
        //String dirPath = "src/main/resources/SourceCode/" + Integer.toString(taskID);
        String dirPath = Config.getProp("BasePath") + "/src/main/resources/SourceCode/" + Integer.toString(taskID);
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
        } catch (Exception e) {
            String name = e.getClass().getSimpleName();
            if (name.equals("IOException")) {
                sendResultMessage(2, 1, "I/O problems while saving source");
            } else if (name.equals("ConfigException")) {
                sendResultMessage(5, 1, "I/O problems while opening config");
            }
            return;
        }

        String execToTest = "";
        try {
            execToTest = cc.compile(fullFileName, dirShortName);
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
            return;
        }

        try {
            IntFLG exceptionsFLG = new IntFLG();
            float result = tc.testExec(dirShortName + '/' + execToTest, taskID, exceptionsFLG);
            int errorCode = 0;
            if (exceptionsFLG.getFlg() > 0)
                errorCode = 4;
            if (result == 100.00)
                sendResultMessage(errorCode, 0, "100% passed, E:" + exceptionsFLG.getFlg());
            else {
                sendResultMessage(errorCode, 2, result + "% passed, E:" + exceptionsFLG.getFlg());
            }
        } catch (IOException e) {
            sendResultMessage(3, 1, "I/O problems when calling Tester");
        } catch (ConfigException e){
            sendResultMessage(5, 1, "I/O problems while opening config");
        }
    }

    private void sendResultMessage(int moduleID, int errorID, String message){
        String res = Integer.toString(moduleID) + '.' + Integer.toString(errorID) +
                '.' + Integer.toString(userID) + '.' + Integer.toString(taskID) + ": " + message;
        putResult(res);
    }

    private synchronized void putResult(String result){
        resultsList.addFirst(result);

        if (needToBeProcessed)
            handler.handle();
    }
}