/**
 * Created by desiresdesigner on 13.02.15.
 */
package ru.osiristher.tester;

import ru.osiristher.tester.exceptions.GCCException;
import ru.osiristher.tester.exceptions.LackOfExpansionException;
import ru.osiristher.tester.exceptions.UnknownLanguageException;

import java.io.*;

public class CompilerCaller {
    public String compile(String fullFileName, String dirShortName) throws IOException, UnknownLanguageException, LackOfExpansionException, GCCException {
        try {
            String[] fileNameParts = fullFileName.split("\\.");
            String fileName = fileNameParts[0];
            String fileExtension = fileNameParts[1];
            if (fileExtension.equals("cpp") || fileExtension.equals("cc")){
                return callGCC(fileName, dirShortName);
            }
            else {
                throw new UnknownLanguageException(fileExtension);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            throw new LackOfExpansionException();
        }
    }

    private String callGCC(String fileShortName, String dirShortName) throws IOException, GCCException {
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "./compile_gcc.sh " + fileShortName + ' ' + dirShortName);
        pb.directory(new File("/home/desiresdesigner/Projects/Osiristher/src/main/java/ru/osiristher/tester/ShellScripts")); // ToDo: remember to change this dependency when deploy it on alert server
        Process p = pb.start();
        //BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String s;
        /*while ((s = reader.readLine()) != null) {
            System.out.println("Script output: " + s);
        }*/

        String errors = "";
        while ((s = stdError.readLine()) != null) {
            //System.out.println("Script error_output: " + s);
            errors += s;
        }

        if (!errors.equals(""))
            throw new GCCException(errors);

        return fileShortName + ".o";
    }
}