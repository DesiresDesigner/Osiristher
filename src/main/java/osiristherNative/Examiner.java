package osiristherNative;

import osiristherNative.exceptions.GCCException;
import osiristherNative.exceptions.LackOfExpansionException;
import osiristherNative.exceptions.UnknownLanguageException;

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

    Examiner(int userID, int taskID, String source, Language lang, LinkedList<String> resultsList){
        cc = new CompilerCaller();

        this.userID = userID;
        this.taskID = taskID;
        this.source = source;
        this.lang = lang;
        this.resultsList = resultsList;
        dirShortName = Integer.toString(userID);
    }

    public String saveSource(){ // ToDo privet
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
        try{
            if (!dir.exists()) {
                dir.mkdir();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(dirPath + '/' + fullFileName, false));
            writer.write(source);
            writer.close();
        } catch (IOException e) {
            sendResultMessage(2, 1, "I/O problems while saving source");
        }

        return fullFileName;
    }

    @Override
    public void run() {

        saveSource();

        String execToTest;
        try {
            execToTest = cc.compile(fullFileName, dirShortName);
            System.out.println(execToTest);
        } catch (IOException e) {
            sendResultMessage(1, 3, "I/O problems when calling gcc");
        } catch (UnknownLanguageException e) {
            sendResultMessage(1, 1, "Unknown extension of source file - " + e.getExtension());
        } catch (LackOfExpansionException e) {
            sendResultMessage(1, 2, "no extension of source file");
        } catch (GCCException e) {
            sendResultMessage(0, 1, "gcc compile error - " + e.toString());
        }

        sendResultMessage(0, 0, "Testing module is being developed right now");
    }

    private void sendResultMessage(int moduleID, int errorID, String message){
        String res = Integer.toString(moduleID) + '.' + Integer.toString(errorID) + ": " + message;
        putResult(res);
    }

    private synchronized void putResult(String result){
        resultsList.addFirst(result);
    }
}