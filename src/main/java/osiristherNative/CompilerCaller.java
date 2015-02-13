/**
 * Created by desiresdesigner on 13.02.15.
 */
package osiristherNative;

import java.io.*;

public class CompilerCaller {
    public String compile(String fullFileName, String dirShortName) throws IOException {
        try {
            String[] fileNameParts = fullFileName.split("\\.");
            String fileName = fileNameParts[0];
            String fileExtension = fileNameParts[1];
            //String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            if (fileExtension.equals("cpp") || fileExtension.equals("cc")){
                callGCC(fileName, dirShortName);
                return "well done - " + fileName; // ToDo; fix to correct
            }
            else
                return "E: unknown language;";
        } catch (ArrayIndexOutOfBoundsException e){
            return "E: file without extension;";
        }
    }

    private String callGCC(String fileShortName, String dirShortName) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "./compile_gcc.sh " + fileShortName + ' ' + dirShortName);
        pb.directory(new File("/home/desiresdesigner/Projects/Osiristher/src/main/java/osiristherNative/CompileScripts")); // ToDo: remember to change this dependency when deploy it on alert server
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String s;
        while ((s = reader.readLine()) != null) {
            System.out.println("Script output: " + s);
        }

        while ((s = stdError.readLine()) != null) {
            System.out.println("Script error_output: " + s);
        }
        return "";
    }
}
