/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess.*;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.ling.Datum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Administrator
 */
public class UI extends JFrame {
    public ColumnDataClassifier cdc;

    @Autowired
    public void setCDC(ColumnDataClassifier cdc) {
        this.cdc = cdc;
    }

    FolderInfo folderInfo = FolderInfo.getFolderInfo();
    Map<String, String> filePathMap = folderInfo.filePathMap;

    public UI() {
        super("Intelligent Document Classify");
        setSize(400, 200);
        setLocation(500, 100);
        Container c = getContentPane();
        c.setBackground(Color.BLUE);
        JMenu file = new JMenu("File");
        JMenuItem resetClassifier = new JMenuItem("Reset Classifier");
        JMenuItem openTrainFolderUpdate = new JMenuItem("Open Train Folder to Update");
        JMenuItem openTrainFolderOnce = new JMenuItem("Open Train Folder for once test");
        JMenuItem openTestFolder = new JMenuItem("Open Test Folder");
        JMenuItem quit = new JMenuItem("Quit");
        file.add(resetClassifier);
        file.add(openTrainFolderUpdate);
        file.add(openTrainFolderOnce);
        file.add(openTestFolder);
        file.add(quit);
        JMenu run = new JMenu("Run");
        JMenuItem predictFile = new JMenuItem("Predict File");
        JMenuItem classifyFolder = new JMenuItem("Classify Folder");
        run.add(predictFile);
        run.add(classifyFolder);
        JMenuBar mb = new JMenuBar();
        mb.add(file);
        mb.add(run);
        c.add(mb, BorderLayout.NORTH);
        JTextArea ta = new JTextArea();
        c.add(ta, BorderLayout.CENTER);
        setVisible(true);
        Font f = new Font("sans-serif", Font.PLAIN, 5);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);


        resetClassifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resetChoose = JOptionPane.showConfirmDialog(ta, "Are you sure to clean the classifier?", "Reset Tip", JOptionPane.OK_CANCEL_OPTION);
                if (resetChoose == JOptionPane.OK_OPTION) {
                    new File(Constant.DATAPATH.value + File.separator + "model.txt").delete();
                    new File(Constant.DATAPATH.value + File.separator + "allTrainDataSet.txt").delete();
                    FileOperation.deleteDir(Constant.DATAPATH.value + File.separator + "permanent");
                    new File(Constant.DATAPATH.value + File.separator + "permanent").mkdir();
                    int successTip = JOptionPane.showConfirmDialog(ta, "Reset successfully!", "Result", JOptionPane.CLOSED_OPTION);
                    ClassifierOperation.scanDataSet(cdc);
                }
            }
        });
        openTrainFolderUpdate.addActionListener(new ActionListener() { // get train data path, transfer to train data type, run training function
            public void actionPerformed(ActionEvent e) {
                // create single train file
                if (!new File(Constant.DATAPATH.value).exists()) new File((Constant.DATAPATH.value)).mkdirs();
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                    try {
                        System.out.println(cdc.toString());
                        File folder = chooser.getSelectedFile();
                        String desPathStr = Constant.DATAPATH.value + File.separator + "permanent" + File.separator + folder.getName() + new SimpleDateFormat("yyyy.MM.dd").format(new Date()) + ".txt";
                        if (!new File(desPathStr).exists())
                            FileTransfer.formatFolder(folder, desPathStr, folder.toString().length());
                        ClassifierOperation.scanDataSet(cdc);
                        new File(Constant.DATAPATH.value + File.separator + "allTrainDataSet.txt").delete();
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }
        });
        openTrainFolderOnce.addActionListener(new ActionListener() { // get train data path, transfer to train data type, run training function
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                    File folder = chooser.getSelectedFile();
                    String folderPath = folder.toString();
                    Date dNow = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd.hh:mm:ss");
                    String desPathStr = folderPath + File.separator + "StanfordTrain" + File.separator + folder.getName() + ft.format(dNow) + ".txt";


                    if (!new File(desPathStr).exists()) {
                        FileTransfer.formatFolder(folder, desPathStr, folderPath.length());
                    }
                    String trainFile = desPathStr;
                    System.out.println(trainFile);
                    try {
                        cdc.trainClassifier(trainFile);
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }
        });
        openTestFolder.addActionListener(new ActionListener() { // get train data path, transfer to train data type, run training function
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                    File folder = chooser.getSelectedFile();
                    String folderPath = folder.toString();
                    int j = folderPath.lastIndexOf(File.separator);
                    String desPathStr = folderPath.substring(0, j) + File.separator + "test.txt";
                    if (!new File(desPathStr).exists()) {
                        FileTransfer.formatFolder(folder, desPathStr, folderPath.length());
                    }
                    String testFile = desPathStr;
                    System.out.println(testFile);
                    try {
                        cdc.testClassifier(testFile);
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }
        });
        classifyFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int counter = 1;
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                    File folder = chooser.getSelectedFile();
                    String folderPath = folder.toString();
                    int j = folderPath.lastIndexOf(File.separator);
                    String desPathStr = folderPath.substring(0, j) + File.separator + "predict.txt";
                    FileTransfer.formatFolder(folder, desPathStr, folderPath.length());
                    String filePath = desPathStr;
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(filePath));
                        String str = "";
                        String getAll = "";
                        while ((str = br.readLine()) != null) {
                            Datum datum = cdc.makeDatumFromLine(str);
                            String[] words = str.split("\\s+");
                            String oldPath = "";
                            if (folderPath.contains(words[0])) oldPath = folderPath + File.separator + words[1];
                            else oldPath = folderPath + File.separator + words[0] + File.separator + words[1];
                            String cls = (String) cdc.classOf(datum);
                            String newPath = cls.toLowerCase().trim();
                            filePathMap.put(oldPath, newPath);
                            getAll += oldPath + "               " + cls + "\r\n";
                        }
                        ta.setText("Success!" + counter++);
                        new File(desPathStr).delete();
                        br.close();
                        FileOperation.reset(folderPath);
                        new File(desPathStr).delete();
                    } catch (Exception ee) {
                        System.out.println();
                        ee.printStackTrace();
                    }
                }
            }
        });
//        openFile.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fc = new JFileChooser();
//                fc.showOpenDialog(fc);
//                try {
//                    BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile().getAbsolutePath()));
//                    String getAll = "";
//                    while ((br.readLine()) != null) {
//                        getAll += br.readLine() + "\r\n";
//                    }
//                    ta.setText(getAll);
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int exitChoose = JOptionPane.showConfirmDialog(ta, "Are you sure to close?", "Close Tip", JOptionPane.OK_CANCEL_OPTION);
                if (exitChoose == JOptionPane.OK_OPTION) {
                    System.exit(0);
                } else {
                    return;
                }
            }
        });
        predictFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                Runtime r = Runtime.getRuntime();
//                Process p = r.exec("test.java");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                    File file1 = chooser.getSelectedFile();
                    String str = "";
                    try {
                        str = FileTransfer.formatFile(file1);
                        Datum datum = cdc.makeDatumFromLine(str);
                        String cls = cdc.classOf(datum);
                        ta.setText(cls);
                        System.out.println(cls);
                    } catch (Exception ex) {
                        System.out.println(str);
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runtime r = Runtime.getRuntime();
                try {
                    Process p = r.exec("javac test.java");
                } catch (IOException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//        File f = new File("test.java");
//        long t = f.lastModified();
    }


    public static void main(String[] args) {
//        new UI();
    }
}
