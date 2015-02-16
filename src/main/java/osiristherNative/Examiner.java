package osiristherNative;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    // ToDo: queue with results

    Examiner(int userID, int taskID, String source, Language lang){
        cc = new CompilerCaller();

        this.userID = userID;
        this.taskID = taskID;
        this.source = source;
        this.lang = lang;
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
        } catch(SecurityException se) {
            se.printStackTrace();
            // ToDo: tell Native, that exam failed
        } catch (IOException e) {
            e.printStackTrace();
            // ToDo: tell Native, that exam failed
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
            // ToDo: tell Native, that exam failed
        }
    }
}
