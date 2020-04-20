package org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

//this is a crucial part, easy to create bugs
public class FileTransfer {
    public static Set<String> localVisited = new HashSet<>();
    public static Set<String> totalVisited = new HashSet<>();

    public static void formatFolder(File srcFile, String desPathStr, int index) {
        File[] files = srcFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                formatFolder(file, desPathStr, index);
            } else {
                String fileName = file.getName();
                String filePath = file.getAbsolutePath();
                String className = "";
                String id = "";
                try {
                    System.out.println(filePath + "   " + fileName + "  " + index);
                    int fileNameCursor = filePath.length() - fileName.length() - 1;
                    if (fileNameCursor <= index) {
                        className = filePath.substring(filePath.lastIndexOf(File.separator, fileNameCursor - 1) + 1, fileNameCursor);
                    } else className = filePath.substring(index + 1, fileNameCursor);
                    id = className + "\t" + fileName + "\t" + file.length() + "\t";
                    if (localVisited.add(id)) {
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                        BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"));
                        FileWriter fw = new FileWriter(desPathStr, true);
                        fw.write(id);    // a\b   c.txt  content \n
                        while (in.ready()) {
                            String line = in.readLine().replaceAll("\n", "");
                            fw.append(line + " ");
                        }
                        fw.write("\n");
                        fw.flush();
                        bis.close();
                        in.close();
                        fw.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("This file has problem " + filePath + " class name is" + className);
                }
            }
        }
    }

    public static String formatFile(File srcFile) {
        if (!srcFile.isFile()) return "";
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("class" + "\t" + srcFile.getName() + "\t" + srcFile.length() + "\t");
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
            BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"));
            while (in.ready()) {
                String line = in.readLine().replaceAll("\n", "");
                sb.append(line + " ");
            }
            sb.append("\n");
            in.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static String formatString(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("class" + "\t" + "name" + "\t" + s.length() + "\t");
        sb.append(s + " \n");
        return sb.toString();
    }

    public static void mergeDataSet(File srcFile, String desFilePath) {
        File[] files = srcFile.listFiles();
        if (files == null || files.length == 0) return;
        for (File file : files) {
            if (file.isDirectory()) mergeDataSet(file, desFilePath);
            else {
                try {
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                    BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"));
                    FileWriter fw = new FileWriter(desFilePath, true);
                    while (in.ready()) {
                        String line = in.readLine();
                        String[] words = line.split("\t");
                        String id = words[0].trim() + "\t" + words[1].trim() + "\t" + words[2].trim() + "\t";
                        System.out.println(id);
                        if (!totalVisited.add(id)) continue;
                        System.out.println(id);
                        fw.append(line);
                        fw.append("\n");
                    }
                    fw.flush();
                    bis.close();
                    in.close();
                    fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
}


//                try {
//                    FileInputStream fis = new FileInputStream(file);
//                    FileOutputStream fos = new FileOutputStream(desPathStr);
//                    fos.write((loc + "\t").getBytes());
//
//
//
//                    int len = 0;
//                    byte datas[] = new byte[1024 * 8];
//                    while ((len = fis.read(datas)) != -1) {
//                        fos.write(datas, 0, len);
//                        fos.flush();
//                    }
//                    fos.write(("\n").getBytes());
//                    fis.close();
//                    fos.close();
//                }
//                catch (Exception e) {
//                }
//            }


