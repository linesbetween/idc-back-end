package org.utpe.freeopenuniversity.intelligentdocumentclassifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans.FileUploaded;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.dao.FileDao;

import java.io.File;


@Service
public class FileService {

    @Autowired
    FileDao fileDao;

    public String saveFile(MultipartFile file, String color) {
        try {
            FileUploaded fileUploaded = new FileUploaded(file, color);
            fileDao.insert(fileUploaded);
            return "file saved in database";
        } catch (Exception e) {
            e.printStackTrace();
            return "file NOT saved in database";
        }
    }
}
