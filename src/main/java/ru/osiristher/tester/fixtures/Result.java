package ru.osiristher.tester.fixtures;

import java.util.LinkedList;

/**
 * Created by DesiresDesigner on 26.02.15.
 */

/*
Exit code
0 - ok
1 - unexpected end
2 - wrong answer
 */

public class Result {
    private int exitCode;
    private int execTimeMS;
    private int execMem;
    private LinkedList<String> errors;
    private int errorsCNT = 0;

    public Result() {
        execMem = 0;
        execTimeMS = 0;
        exitCode = 0;
        errors = new LinkedList<String>();
    }

    public int getExecTimeMS() {
        return execTimeMS;
    }

    public void setExecTimeMS(int execTimeMS) {
        this.execTimeMS = execTimeMS;
    }

    public int getExecMem() {
        return execMem;
    }

    public void setExecMem(int execMem) {
        this.execMem = execMem;
    }

    public void addError (int str, String have, String expected){
        errors.addLast("On " + str + "line: " + "have: " + have + ", expected: " + expected + ';');
    }

    public void setCntToNull(){
        errorsCNT = 0;
    }

    public boolean haveError(){
        return errorsCNT < errors.size();
    }

    public String getNextError(){
        return errors.get(errorsCNT++);
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }
}
