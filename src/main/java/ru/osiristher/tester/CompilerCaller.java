/**
 * Created by DesiresDesigner on 13.02.15.
 */
package ru.osiristher.tester;

import ru.osiristher.properties.Config;
import ru.osiristher.tester.exceptions.ConfigException;
import ru.osiristher.tester.exceptions.GCCException;
import ru.osiristher.tester.exceptions.LackOfExpansionException;
import ru.osiristher.tester.exceptions.UnknownLanguageException;

import java.io.*;

public class CompilerCaller {
    public String compile(String fullFileName, String dirShortName) throws IOException, UnknownLanguageException, LackOfExpansionException, GCCException, ConfigException {
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

    private String callGCC(String fileShortName, String dirShortName) throws IOException, GCCException, ConfigException {
        System.out.println("./compile_gcc.sh " +
                Config.getProp("BasePath") + '/' + Config.getProp("ResourcesPath") +
                ' ' + fileShortName + ' ' + dirShortName);
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "./compile_gcc.sh " +
                Config.getProp("BasePath") + '/' + Config.getProp("ResourcesPath") +
                ' ' + fileShortName + ' ' + dirShortName);
        pb.directory(new File(Config.getProp("BasePath") + '/' + Config.getProp("ScriptsPath")));
        Process p = pb.start();
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String s;

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