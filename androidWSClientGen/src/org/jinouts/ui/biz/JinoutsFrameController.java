package org.jinouts.ui.biz;

import org.apache.commons.io.FileUtils;
import org.jinouts.util.AndroidWSClientGenProp;
import org.jinouts.util.AndroidWSClientGenUtil;

import java.io.File;

public class JinoutsFrameController {

    private File getCxfWSDL2JavaDir(File cxfBinFile) {
        if (cxfBinFile != null) {
            return cxfBinFile;
        } else {
            return new File(AndroidWSClientGenProp.CXF_HOME_BIN_FULLPATH);
        }
    }

    private void runWsdlToJava(File cxfBinFile, File jaxbBindingFile, File tempDir, String wsdlUrl) {
        File cxfWSDL2JavaDir = getCxfWSDL2JavaDir(cxfBinFile);
        try {
            String command = String.format("%s -ant -frontend jaxws21 -client -d %s -b %s %s", new File(cxfWSDL2JavaDir, "wsdl2java").getAbsolutePath(),
                    tempDir.getAbsolutePath(), jaxbBindingFile.getAbsolutePath(), wsdlUrl);
            int exitVal = AndroidWSClientGenUtil.executeCommandAndGetReturnCode(command);
            if (exitVal != 0) {
                throw new RuntimeException("WSDL2Java failed '" + exitVal + "'");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static File createDir(String absolutePath) {
        return createDir(new File(absolutePath));
    }

    private static File createDir(File dir) {
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("'" + dir + "'' is not directory");
        }
        return dir;
    }

    public File generateJavaFromWSDL(File cxfBinFile, File destDir, String wsdlUrl) {
        try {
            File jaxbBindingFile = new File(AndroidWSClientGenProp.jaxbBindingPath);
            File tempDir = createDir(AndroidWSClientGenProp.tempDirPath);
            destDir = createDir(new File(destDir, AndroidWSClientGenProp.wsClientStub));

            runWsdlToJava(cxfBinFile, jaxbBindingFile, tempDir, wsdlUrl);

            // now modified the cxf generated file
            CXFToJinoutsWSConverter.modifyImportOfFile(tempDir, destDir);

            File libDir = new File(AndroidWSClientGenProp.libDirPath);
            // now copy the library to the dist dir
            FileUtils.copyDirectoryToDirectory(libDir, destDir);

            if (AndroidWSClientGenProp.deleteTemp) {
                // now delete the temp file
                FileUtils.cleanDirectory(tempDir);
            }
            return destDir;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
