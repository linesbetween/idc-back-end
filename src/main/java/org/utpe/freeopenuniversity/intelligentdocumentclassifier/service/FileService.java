package org.utpe.freeopenuniversity.intelligentdocumentclassifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans.FileUploaded;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.dao.FileDao;


@Service
public class FileService {

    @Autowired
    FileDao fileDao;

    public String saveFile(FileUploaded fileUploaded) {
        try {
            fileDao.save(fileUploaded);
            return "file saved in database";
        } catch (Exception e) {
            e.printStackTrace();
            return "file NOT saved in database";
        }
    }
}
