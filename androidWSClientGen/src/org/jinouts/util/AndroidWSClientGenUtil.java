package org.jinouts.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AndroidWSClientGenUtil {
    public static boolean isCXFBinValid(File cxfBinFile) {
        if (cxfBinFile == null || !cxfBinFile.exists()) {
            return false;
        }

        // if the file wsdl2java.bat not exists
        String wsdlBatFullPath = cxfBinFile.getAbsolutePath() + File.separator + AndroidWSClientGenProp.WSDL2JAVA;
        System.out.println("WSDL bat file " + wsdlBatFullPath);
        if (!new File(wsdlBatFullPath).exists()) {
            return false;
        }

        return true;
    }

    public static boolean isCXFHOMEValid() {
        // check where there is cxf_home
        String command = "echo " + AndroidWSClientGenProp.CXF_HOME;
        try {
            String output = executeCommandAndGetOutput(command);
            if (output == null || output.contains(AndroidWSClientGenProp.CXF_HOME)) {
                return false;
            } else {
                System.out.println(output);
                String cxfBinPath = output + File.separator + "bin";
                File cxfFile = new File(cxfBinPath);
                if (isCXFBinValid(cxfFile)) {
                    AndroidWSClientGenProp.CXF_HOME_BIN_FULLPATH = cxfBinPath;
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Process createAndExecuteProcess(String command) throws IOException {
        String system = System.getProperty("os.name").toLowerCase();
        if ("unix".equals(system) || "mac os x".equals(system)) {
            return Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", command});
        } else if ("windows".equals(system)) {
            return Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", command});
        } else {
            throw new RuntimeException("Unknown OS [" + system + "]");
        }
    }

    public static int executeCommandAndGetReturnCode(String command) throws Exception {
        Process proc = createAndExecuteProcess(command);
        logForProc(proc);
        return proc.waitFor();
    }

    public static String executeCommandAndGetOutput(String command) throws Exception {
        Process proc = createAndExecuteProcess(command);
        logForProc(proc);
        return getOuputMessageOfCommand(proc.getInputStream());
    }

    public static void logForProc(Process proc) {
        // any error message?
        StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");

        // any output?
        StreamGobbler outputGobbler = new
                StreamGobbler(proc.getInputStream(), "OUTPUT");

        // kick them off
        errorGobbler.start();
        outputGobbler.start();
    }

    public static class StreamGobbler extends Thread {
        InputStream is;
        String type;

        StreamGobbler(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        public void run() {
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null)
                    System.out.println(type + ">" + line);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static String getOuputMessageOfCommand(InputStream is) {
        String message = "";
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(">>" + line);
                message += line;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return message;
    }
}
