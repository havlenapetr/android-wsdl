/**
 *
 */
package org.jinouts.ui.biz;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author asraf
 */
public class CXFToJinoutsWSConverter {

    //public static final String SOURCE_DIR = "D:\\andPro\\SoapUIRND\\temp";
    //public static final String DEST_DIR = "D:\\andPro\\SOAPUIRND\\ws-dest";


    static void modifyImportOfFile(File sourceDir, File destDir) throws Exception {
        List<File> fileList = (List<File>) FileUtils.listFiles(sourceDir, null, true);
        int sourceDirLen = sourceDir.getAbsolutePath().length();
        for (File file : fileList) {
            String fileFUllPath = file.getAbsolutePath();
            List<String> changedSourceLines = changeSource(fileFUllPath);
            File destFile = new File(destDir, fileFUllPath.substring(sourceDirLen));
            FileUtils.writeLines(destFile, changedSourceLines);
            System.out.println("File : " + destFile);
        }
    }

    static List<String> changeSource(String fullFilePath) throws Exception {
        List<String> lineList = FileUtils.readLines(new File(fullFilePath));

        int i = 0;
        String line;
        while (i < lineList.size() && (line = lineList.get(i)) != null) {
            if (line.contains("import ") || line.contains("@")) {
                String replacedLine = modifyImports(line);
                lineList.set(i, replacedLine);

            } else if (line.contains("public class")) {
                if (line.contains("extends Service")) {
                    String replacedLine = modifyClassDec(line);
                    lineList.set(i, replacedLine);
                }
                break;
            }
            i++;
        }

        return lineList;
    }

    static String modifyImports(String line) {
        if (line.contains("javax")) {
            //javax.xml.datatype
            // if not the xml gergorian calendar
            if (!line.contains("javax.xml.datatype")) {
                line = line.replace("javax", "org.jinouts");
            }
        }
        return line;
    }

    static String modifyClassDec(String line) {
        return line.replace("extends Service", "extends org.jinouts.ws.JinosService");
    }

}
