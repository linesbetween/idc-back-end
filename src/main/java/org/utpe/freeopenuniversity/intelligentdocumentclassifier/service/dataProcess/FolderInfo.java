package org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess;

import java.util.HashMap;
import java.util.Map;

public class FolderInfo {
    public Map<String, String> filePathMap;
    private static FolderInfo folderInfo = new FolderInfo();
    private FolderInfo() {
        filePathMap = new HashMap<>();
    }
    public static FolderInfo getFolderInfo() {
        return folderInfo;
    }
//    public void fillFilePaths(File dirFile) {
//        File[] files = dirFile.listFiles();
//        for (File file : files) {
//            if (file.isDirectory())//是文件夹则递归查询
//                fillFilePaths(file);
//            else {
//                filePaths.put(file.getName(), file.getAbsolutePath());
//            }
//        }
//    }
}
