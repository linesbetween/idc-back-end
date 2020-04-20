package org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess;

import java.io.*;
import java.util.*;

public class FileOperation {
    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */
    public static void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    public static void mkdir(String path) {
        File fd = null;
        try {
            fd = new File(path);
            System.out.println(fd.toString());
            if (!fd.exists()) {
                fd.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fd = null;
        }
    }

    public static void copyFile(String srcPathStr, String desPathStr) {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf(File.separator) + 1); //目标文件地址
        //System.out.println("源文件:" + newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println(srcPathStr + "目标文件地址:" + desPathStr);
        try {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024 * 8];//创建搬运工具
            int len = 0;//创建长度
            while ((len = fis.read(datas)) != -1)//循环读取数据
            {
                fos.write(datas, 0, len);
            }
            fis.close();//释放资源
            fos.close();//释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveFile(String oldPath, String newPath) {
        try {
            File file = new File(oldPath); //源文件
            if (file.renameTo(new File(newPath + file.getName()))) //源文件移动至目标文件目录
            {
                System.out.println("File is moved successful!");//输出移动成功
            } else {
                System.out.println("File is failed to move !");//输出移动失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reset(String folderPath) {
        FolderInfo folderInfo = FolderInfo.getFolderInfo();
        Map<String, String> filePathMap = folderInfo.filePathMap;
        System.out.println(filePathMap.size() + " ");
        for (String oldPath : filePathMap.keySet()) {
            try {
                int i = folderPath.lastIndexOf(File.separator);
                String newPath = folderPath.substring(0, i) + File.separator + "Classify" + File.separator + filePathMap.get(oldPath);
                mkdir(newPath);
                copyFile(oldPath, newPath);
            } catch (Exception e) {
                //e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        File dirFile = new File("C:\\Users\\rocky\\IdeaProjects\\DocumentClassify\\data");
//        FilenameFilter filter = new FilenameFilter() {
//
//            @Override
//            public boolean accept(File dir, String name) {
//                // TODO Auto-generated method stub
//                return name.endsWith("1");
//            }
//        };
//        List<File> list = new ArrayList<File>();
//        try {
//            getFile(dirFile, filter, list);//查找符合条件的文件
//            WriteToFile(list);//将查找到的指定格式的文件放入指定的文件夹中
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        mkdir("C:\\Users\\rocky\\IdeaProjects\\DocumentClassify\\data\\mkdir\\a");
        copyFile("C:\\Users\\rocky\\IdeaProjects\\DocumentClassify\\data\\20news1.prop", "C:\\Users\\rocky\\IdeaProjects\\DocumentClassify\\data\\reset");
        moveFile("C:\\Users\\rocky\\IdeaProjects\\DocumentClassify\\data\\convert-to-stanford-classifier.csh", "C:\\Users\\rocky\\IdeaProjects\\DocumentClassify\\data\\reset\\convert-to-stanford-classifier.csh");

    }

    public static void getFile(File dir, FilenameFilter filter, List<File> list) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory())//是文件夹则递归查询
                getFile(file, filter, list);
            else {
                if (filter.accept(dir, file.getName()))//是文件则将文件放入list列表中
                    list.add(file);
            }
        }
    }

    public static void WriteToFile(List<File> list) throws IOException {
        String dirString = "C:\\Users\\rocky\\IdeaProjects\\DocumentClassify\\data\\reset";//将查找到的.mobi格式文件存放于此
        FileInputStream fis = null;
        FileOutputStream fos = null;
        int num = 0;
        byte[] bt = new byte[1024];
        try {
            for (File file : list) {
                fis = new FileInputStream(file);//读文件
                fos = new FileOutputStream(new File(dirString, file.getName()));//写文件
                System.out.println(file.getAbsolutePath());
                while ((num = fis.read(bt)) != -1) {
                    fos.write(bt, 0, num);
                    fos.flush();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
        }
    }
}
